abstract class Account {
    protected String accNumber;
    protected double accBalance;

    public Account(String accNumber, double accBalance) {
        this.accNumber = accNumber;
        this.accBalance = accBalance;
    }

    public void addFunds(double amount) {
        if (amount > 0) {
            accBalance += amount;
            System.out.println("Successfully added ₹" + amount + ". Updated balance: ₹" + accBalance);
        } else {
            System.out.println("Error: Please enter a valid amount.");
        }
    }

    public abstract void deductFunds(double amount);
}


interface MoneyTransfer {
    void sendMoney(Account recipient, double amount);
}


class SavingsAcc extends Account implements MoneyTransfer {
    private static final double MIN_REQUIRED_BALANCE = 500;

    public SavingsAcc(String accNumber, double accBalance) {
        super(accNumber, accBalance);
    }

    
    public void deductFunds(double amount) {
        if (accBalance - amount >= MIN_REQUIRED_BALANCE) {
            accBalance -= amount;
            System.out.println("Debited ₹" + amount + ". Available balance: ₹" + accBalance);
        } else {
            System.out.println("Error: Maintain minimum ₹500 balance.");
        }
    }


    public void sendMoney(Account recipient, double amount) {
        if (accBalance - amount >= MIN_REQUIRED_BALANCE) {
            accBalance -= amount;
            recipient.addFunds(amount);
            System.out.println("Sent ₹" + amount + " to " + recipient.accNumber + ". Remaining balance: ₹" + accBalance);
        } else {
            System.out.println("Transfer failed: Insufficient funds.");
        }
    }
}


class CurrentAcc extends Account implements MoneyTransfer {
    private static final double MAX_OVERDRAFT = -5000;

    public CurrentAcc(String accNumber, double accBalance) {
        super(accNumber, accBalance);
    }

    public void deductFunds(double amount) {
        if (accBalance - amount >= MAX_OVERDRAFT) {
            accBalance -= amount;
            System.out.println("Debited ₹" + amount + ". Remaining balance: ₹" + accBalance);
        } else {
            System.out.println("Error: Overdraft limit exceeded.");
        }
    }


    public void sendMoney(Account recipient, double amount) {
        if (accBalance - amount >= MAX_OVERDRAFT) {
            accBalance -= amount;
            recipient.addFunds(amount);
            System.out.println("Sent ₹" + amount + " to " + recipient.accNumber + ". Current balance: ₹" + accBalance);
        } else {
            System.out.println("Error: Overdraft limit exceeded, transfer denied.");
        }
    }
}


public class BankingApplication {
    public static void main(String[] args) {
        SavingsAcc savings = new SavingsAcc("SAV123", 5000);
        CurrentAcc current = new CurrentAcc("CUR456", 2000);

        savings.addFunds(1000); // Balance updated: ₹6000
        current.deductFunds(3000); // Overdraft utilized, balance: ₹-1000

        savings.sendMoney(current, 1500); // Savings: ₹4500, Current: ₹500
        current.sendMoney(savings, 6000); // Should fail due to overdraft
    }
}