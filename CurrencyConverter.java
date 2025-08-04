import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverter extends JFrame implements ActionListener {
    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;
    private JButton convertButton;

    // Static exchange rates relative to USD
    private final HashMap<String, Double> rates = new HashMap<>();

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 280);
        setLayout(new GridLayout(6, 1, 10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Exchange rates
        rates.put("USD", 1.0);
        rates.put("INR", 83.1);
        rates.put("EUR", 0.92);
        rates.put("GBP", 0.78);
        rates.put("JPY", 144.7);
        rates.put("AUD", 1.5);
        rates.put("CAD", 1.35);
        rates.put("CNY", 7.2);

        JLabel heading = new JLabel("Currency Converter", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        add(heading);

        amountField = new JTextField();
        amountField.setHorizontalAlignment(JTextField.CENTER);
        amountField.setToolTipText("Enter amount");
        add(amountField);

        fromCurrency = new JComboBox<>(rates.keySet().toArray(new String[0]));
        toCurrency = new JComboBox<>(rates.keySet().toArray(new String[0]));
        fromCurrency.setSelectedItem("USD");
        toCurrency.setSelectedItem("INR");

        JPanel comboPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        comboPanel.add(fromCurrency);
        comboPanel.add(toCurrency);
        add(comboPanel);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        add(convertButton);

        resultLabel = new JLabel("Converted amount will appear here", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(resultLabel);

        JLabel footer = new JLabel("Exchange rates are static (based on USD)", JLabel.CENTER);
        footer.setFont(new Font("Arial", Font.ITALIC, 10));
        add(footer);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String from = (String) fromCurrency.getSelectedItem();
        String to = (String) toCurrency.getSelectedItem();
        String amountText = amountField.getText();

        if (from.equals(to)) {
            resultLabel.setText("Choose different currencies.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Enter a valid number.");
            return;
        }

        double inUSD = amount / rates.get(from);
        double converted = inUSD * rates.get(to);

        resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, from, converted, to));
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(CurrencyConverter::new);
    }
}