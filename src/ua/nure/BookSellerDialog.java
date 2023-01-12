package ua.nure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.LINE_START;

public final class BookSellerDialog extends JFrame {

    public BookSellerDialog(BookSellerAgent agent) {
        super("Book Seller: Books");

        JPanel bookPane = new JPanel(new GridBagLayout());
        bookPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField titleField = new JTextField();
        JTextField priceField = new JTextField();

        bookPane.add(new JLabel("Book title:"), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, LINE_START, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        bookPane.add(titleField, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, LINE_START, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        bookPane.add(new JLabel("Price UAH:"), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, LINE_START, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        bookPane.add(priceField, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, LINE_START, HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(event -> {
            try {
                String title = titleField.getText().trim();
                String price = priceField.getText().trim();
                agent.updateCatalogue(title, Integer.parseInt(price));
                titleField.setText("");
                priceField.setText("");
            } catch (Exception e) {
                showErrorMessageDialog(e.getMessage());
            }
        });

        JPanel actionsPane = new JPanel();
        actionsPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        actionsPane.add(addButton);

        setLayout(new BorderLayout());
        add(bookPane, BorderLayout.CENTER);
        add(actionsPane, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                agent.doDelete();
            }
        });
    }

    private void showErrorMessageDialog(String message) {
        JOptionPane.showMessageDialog(BookSellerDialog.this, "Invalid values. " + message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showDialog() {
        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

