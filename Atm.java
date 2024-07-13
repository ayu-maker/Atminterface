import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class representing the user's bank account
class BankAccount {
    private double userbalance;

    public BankAccount(double initialBalance) {
        this.userbalance = initialBalance;
    }

    public double getBalance() {
        return userbalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            userbalance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= userbalance) {
            userbalance -= amount;
            return true;
        }
        return false;
    }
}

// Class representing the ATM machine with a GUI
class ATMGUI {
    private BankAccount account;
    private JFrame frame;
    private JTextField amountField;
    private JLabel messageLabel;

    public ATMGUI(BankAccount account) {
        this.account = account;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("ATM Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 400);
        frame.setLayout(new GridLayout(5, 1));

        JLabel welcome = new JLabel("Welcome to the ATM", SwingConstants.CENTER);
        frame.add(welcome);

        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.BOLD, 28));
        frame.add(amountField);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });
        frame.add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });
        frame.add(depositButton);

        JButton balanceButton = new JButton("Check Balance");
        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleCheckBalance();
            }
        });
        frame.add(balanceButton);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        frame.add(messageLabel);

        frame.setVisible(true);
    }

    private void handleWithdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (account.withdraw(amount)) {
                messageLabel.setText("Withdrawal successful. New balance: Rs. " + account.getBalance());
            } else {
                messageLabel.setText("Withdrawal failed. Insufficient balance");
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter a valid amount.");
        }
    }

    private void handleDeposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            messageLabel.setText("Deposit successful. New balance:  Rs. " + account.getBalance());
        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter a valid amount.");
        }
    }

    private void handleCheckBalance() {
        messageLabel.setText("Your current balance is: Rs. " + account.getBalance());
    }
}

// Main class to run the ATM with GUI
public class Atm {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(100000); // Initial balance
        new ATMGUI(account);
    }
}
