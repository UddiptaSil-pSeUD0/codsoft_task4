import java.util.*;

class Bank_Acc {
    private String accountNumber;
    private String pin;
    private double balance;

    public Bank_Acc(String accountNumber, String pin, double init_Balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = init_Balance;
    }

    public boolean valid_Acc(String accountNumber, String pin) {
        return this.accountNumber.equals(accountNumber) && this.pin.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: Rs." + balance);
        } else {
            System.out.println("Invalid amount or Insufficient funds.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Updated balance: Rs." + balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }
}

public class AtmInterface_Task4 {
    private Bank_Acc[] accounts;
    private Bank_Acc currentAccount;
    private int loginAttempts;

    public AtmInterface_Task4(Bank_Acc[] accounts) {
        this.accounts = accounts;
        this.currentAccount = null;
        this.loginAttempts = 0;
    }

    public void login(Scanner sc) {
        if (currentAccount != null) {
            System.out.println("\nYou are already logged in.\n");
            return;
        }

        System.out.print("Enter your account number: ");
        String accountNumber = sc.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = sc.nextLine();

        for (Bank_Acc account : accounts) {
            if (account.valid_Acc(accountNumber, pin)) {
                currentAccount = account;
                loginAttempts = 0;
                System.out.println("\nLogin successful.\n");
                return;
            }
        }

        loginAttempts++;
        if (loginAttempts >= 3) {
            System.out.println("\nToo many unsuccessful login attempts. The ATM is locked.");
            System.exit(0);
        } else {
            System.out.println("Invalid account number or PIN. Please try again.\n");
        }
    }

    public void logout() {
        if (currentAccount != null) {
            currentAccount = null;
            System.out.println("\nLogout successful.");
        } else {
            System.out.println("\nYou are not logged in.\n");
        }
    }

    public void checkBalance() {
        if (currentAccount != null) {
            System.out.println("\nYour account balance is: Rs." + currentAccount.getBalance());
        } else {
            System.out.println("\nYou are not logged in.\n");
        }
    }

    public void withdraw(Scanner sc) {
        if (currentAccount != null) {
            System.out.print("Enter the amount to withdraw: ");
            double amount = Double.parseDouble(sc.nextLine());
            currentAccount.withdraw(amount);
        } else {
            System.out.println("\nYou are not logged in.\n");
        }
    }

    public void deposit(Scanner sc) {
        if (currentAccount != null) {
            System.out.print("Enter the amount to deposit: ");
            double amount = Double.parseDouble(sc.nextLine());
            currentAccount.deposit(amount);
        } else {
            System.out.println("\nYou are not logged in.\n");
        }
    }

    
     private static void displayMainMenu() {
        System.out.println(" ───────────────────────────────────────────────── ");
        System.out.println("                     ATM Menu                      ");
        System.out.println(" ───────────────────────────────────────────────── ");
        System.out.println("  1. Login                                         ");
        System.out.println("  2. Check Balance                                 ");
        System.out.println("  3. Withdraw Money                                ");
        System.out.println("  4. Deposit Money                                 ");
        System.out.println("  5. Logout                                        ");
        System.out.println(" ───────────────────────────────────────────────── ");
        System.out.println();
        
    }

    private static int getValidChoice(Scanner sc, int maxChoice) {
        int choice;
        while (true) {
            System.out.print("Enter your choice (1-" + maxChoice + "): ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= 1 && choice <= maxChoice) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return choice;
    }
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Bank_Acc[] accounts = {
                new Bank_Acc("1234567", "1234", 2000),
                new Bank_Acc("1756827", "5763", 900),
                new Bank_Acc("9876321", "6542", 5000),
                new Bank_Acc("1112221", "0100", 15000)
        };
        AtmInterface_Task4 atm = new AtmInterface_Task4(accounts);
        int n=1;

        while (n==1) {
            displayMainMenu();

            int choice = getValidChoice(sc, 5);

            switch (choice) {
                case 1:
                    atm.login(sc);
                    n=1;
                    break;

                case 2:
                    atm.checkBalance();
                    n=1;
                    break;

                case 3:
                    atm.withdraw(sc);
                    n=1;
                    break;

                case 4:
                    atm.deposit(sc);
                    n=1;
                    break;

                case 5:
                    atm.logout();
                    System.out.println("Do you want to Continue the Transaction?\n\nPress '1' for YES or '0' for NO");
                    n=Integer.parseInt(sc.nextLine());
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    n=1;
                    break;
            }
        }
    }

} 