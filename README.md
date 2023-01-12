# JADE Book Trading

This project represents the "Book Trading" example implementation from [JADE Programming Tutorial for Beginners](https://jade.tilab.com/doc/tutorials/JADEProgramming-Tutorial-for-beginners.pdf).

## Prerequisites

- macOS 13 or higher
- Java 17 or higher
- JADE 4.6.0

## Running

To run the "Book Trading" app that launches two JADE agents: buyer and seller, open macOS **Terminal** and execute the following command line:

```
chmod 755 run_agents.sh
./run_agents.sh
```

## Output

You should see the following dialog and output in the console:

<img width="412" alt="image" src="https://user-images.githubusercontent.com/6960837/212078775-1370a136-5068-4fb5-a3db-fa687983efd6.png">

```
vladimir@JxBM1 JADE-Book-Trading % ./run_agents.sh 
Jan 12, 2023 3:26:20 PM jade.core.Runtime beginContainer
INFO: ----------------------------------
    This is JADE 4.6.0 - revision 6869 of 30-11-2022 14:47:03
    downloaded in Open Source, under LGPL restrictions,
    at http://jade.tilab.com/
----------------------------------------
Jan 12, 2023 3:26:20 PM jade.imtp.leap.LEAPIMTPManager initialize
INFO: Listening for intra-platform commands on address:
- jicp://192.168.3.73:1099

Jan 12, 2023 3:26:20 PM jade.core.AgentContainerImpl init
WARNING: Automatic main-detection mechanism initialization failed (Error setting up multicast socket - Caused by:  Can't assign requested address). Mechanism disabled!
Jan 12, 2023 3:26:21 PM jade.core.BaseService init
INFO: Service jade.core.management.AgentManagement initialized
Jan 12, 2023 3:26:21 PM jade.core.BaseService init
INFO: Service jade.core.messaging.Messaging initialized
Jan 12, 2023 3:26:21 PM jade.core.BaseService init
INFO: Service jade.core.resource.ResourceManagement initialized
Jan 12, 2023 3:26:21 PM jade.core.BaseService init
INFO: Service jade.core.mobility.AgentMobility initialized
Jan 12, 2023 3:26:21 PM jade.core.BaseService init
INFO: Service jade.core.event.Notification initialized
Jan 12, 2023 3:26:21 PM jade.mtp.http.HTTPServer <init>
INFO: HTTP-MTP Using XML parser com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl$JAXPSAXParser
Jan 12, 2023 3:26:21 PM jade.core.messaging.MessagingService boot
INFO: MTP addresses:
http://192.168.3.73:7778/acc
[buyer@192.168.3.73:1099/JADE] agent is ready.
Trying to buy 'Cinderella'...
[seller@192.168.3.73:1099/JADE] agent is ready.
Jan 12, 2023 3:26:21 PM jade.core.AgentContainerImpl joinPlatform
INFO: --------------------------------------
Agent container Main-Container@192.168.3.73 is ready.
--------------------------------------------
Warning: the font "Times" is not available, so "Lucida Bright" has been substituted, but may have unexpected appearance or behavor. Re-enable the "Times" font to remove this warning.
BookSellerAgent.OfferRequestsServer.action
PurchaseOrdersServer.OfferRequestsServer.action
Buyer agent on tick.
Found the following seller agents:
seller@192.168.3.73:1099/JADE
RequestPerformer.action: step=0
RequestPerformer.action: step=1
BookSellerAgent.OfferRequestsServer.action
BookSellerAgent.OfferRequestsServer.action
  title=Cinderella
  price=150
PurchaseOrdersServer.OfferRequestsServer.action
BookSellerAgent.OfferRequestsServer.action
RequestPerformer.action: step=1
RequestPerformer.action: step=2
RequestPerformer.action: step=3
PurchaseOrdersServer.OfferRequestsServer.action
BookSellerAgent.PurchaseOrdersServer.action
  title=Cinderella
  price=150
Cinderella sold to agent buyer@192.168.3.73:1099/JADE
BookSellerAgent.OfferRequestsServer.action
PurchaseOrdersServer.OfferRequestsServer.action
RequestPerformer.action: step=3
Cinderella successfully purchased at price 150 UAH
[buyer@192.168.3.73:1099/JADE] agent is terminated.
```
