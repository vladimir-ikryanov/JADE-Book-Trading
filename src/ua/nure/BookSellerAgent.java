package ua.nure;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.HashMap;
import java.util.Map;

public class BookSellerAgent extends Agent {
    private Map<String, Integer> catalog;
    private BookSellerDialog dialog;

    @Override
    protected void setup() {
        System.out.println("[" + getAID().getName() + "] agent is ready.");

        catalog = new HashMap<>();
        catalog.put("Cinderella", 150);
        catalog.put("The-Lord-of-the-rings", 350);

        // Register the book-selling service in the yellow pages
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("book-seller");
        sd.setName("JADE-book-trading");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        addBehaviour(new OfferRequestsServer());
        addBehaviour(new PurchaseOrdersServer());

        dialog = new BookSellerDialog(this);
        dialog.showDialog();
    }

    @Override
    protected void takeDown() {
        dialog.dispose();
        System.out.println("[" + getAID().getName() + "] agent is terminated.");
    }

    public void updateCatalogue(String title, int price) {
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("BookSellerAgent.updateCatalogue: title=" + title + ", price=" + price);
                catalog.put(title, price);
            }
        });
    }

    private class OfferRequestsServer extends CyclicBehaviour {
        @Override
        public void action() {
            System.out.println("BookSellerAgent.OfferRequestsServer.action");
            MessageTemplate msgTemplate = MessageTemplate.MatchPerformative(ACLMessage.CFP);
            ACLMessage msg = myAgent.receive(msgTemplate);
            if (msg != null) {
                String title = msg.getContent();
                ACLMessage reply = msg.createReply();

                System.out.println("BookSellerAgent.OfferRequestsServer.action");
                System.out.println("  title=" + title);

                Integer price = catalog.get(title);
                System.out.println("  price=" + price);
                if (price != null) {
                    reply.setPerformative(ACLMessage.PROPOSE);
                    reply.setContent(String.valueOf(price));
                } else {
                    reply.setPerformative(ACLMessage.REFUSE);
                    reply.setContent("not-available");
                }
                myAgent.send(reply);
            } else {
                block();
            }
        }
    }

    private class PurchaseOrdersServer extends CyclicBehaviour {
        public void action() {
            System.out.println("PurchaseOrdersServer.OfferRequestsServer.action");
            MessageTemplate msgTemplate = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
            ACLMessage msg = myAgent.receive(msgTemplate);
            if (msg != null) {
                String title = msg.getContent();
                ACLMessage reply = msg.createReply();

                System.out.println("BookSellerAgent.PurchaseOrdersServer.action");
                System.out.println("  title=" + title);

                Integer price = catalog.remove(title);
                System.out.println("  price=" + price);
                if (price != null) {
                    reply.setPerformative(ACLMessage.INFORM);
                    System.out.println(title + " sold to agent " + msg.getSender().getName());
                } else {
                    reply.setPerformative(ACLMessage.FAILURE);
                    reply.setContent("not-available");
                }
                myAgent.send(reply);
            } else {
                block();
            }
        }
    }
}