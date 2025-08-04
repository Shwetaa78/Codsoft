
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public String deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return "₹" + amount + " deposited successfully.";
        } else {
            return "Invalid deposit amount.";
        }
    }

    public String withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return "₹" + amount + " withdrawn successfully.";
        } else if (amount > balance) {
            return "Insufficient balance.";
        } else {
            return "Invalid withdrawal amount.";
        }
    }
}

public class ATMGui extends JFrame implements ActionListener {
    private BankAccount account;
    private JTextField amountField;
    private JButton depositBtn, withdrawBtn, checkBtn;
    
    public ATMGui() {
        account = new BankAccount(1000);  // Default starting balance

        setTitle("ATM Interface");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Enter Amount (₹):");
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label);

        amountField = new JTextField();
        add(amountField);

        JPanel buttonPanel = new JPanel();
        depositBtn = new JButton("Deposit");
        withdrawBtn = new JButton("Withdraw");
        checkBtn = new JButton("Check Balance");

        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        checkBtn.addActionListener(this);

        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(checkBtn);

        add(buttonPanel);

        JLabel note = new JLabel("Initial balance: ₹1000", JLabel.CENTER);
        add(note);
        
        setLocationRelativeTo(null); // center the window
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = amountField.getText();
        double amount = 0;

        if (!input.isEmpty()) {
            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (e.getSource() == depositBtn) {
            String result = account.deposit(amount);
            JOptionPane.showMessageDialog(this, result);
        } else if (e.getSource() == withdrawBtn) {
            String result = account.withdraw(amount);
            JOptionPane.showMessageDialog(this, result);
        } else if (e.getSource() == checkBtn) {
            JOptionPane.showMessageDialog(this, "Current Balance: ₹" + account.getBalance());
        }
    }

    public static void main(String[] args) {
        new ATMGui();
    }
}