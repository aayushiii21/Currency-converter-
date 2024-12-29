import java.util.Scanner;

class Currency {
    private String code;
    private double rate;

    public Currency(String code, double rate) {
        this.code = code;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public double getRate() {
        return rate;
    }
}

class CurrencyConverter {
    public static double convert(Currency from, Currency to, double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        return amount * to.getRate() / from.getRate();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create Currency objects with initial exchange rates
        Currency usd = new Currency("USD", 1.0); // Base currency
        Currency eur = new Currency("EUR", 0.85); 
        Currency gbp = new Currency("GBP", 0.72); 

        System.out.println("Available Currencies:");
        System.out.println("- USD");
        System.out.println("- EUR");
        System.out.println("- GBP");

        while (true) {
            try {
                System.out.print("Enter the amount to convert: ");
                double amount = scanner.nextDouble();

                System.out.print("Enter the currency to convert from: ");
                String fromCode = scanner.next().toUpperCase();

                System.out.print("Enter the currency to convert to: ");
                String toCode = scanner.next().toUpperCase();

                Currency fromCurrency = getCurrency(fromCode);
                Currency toCurrency = getCurrency(toCode);

                double convertedAmount = CurrencyConverter.convert(fromCurrency, toCurrency, amount);
                System.out.println(amount + " " + fromCurrency.getCode() + " = " + convertedAmount + " " + toCurrency.getCode());
                break; // Exit the loop after a successful conversion

            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Invalid input. Please try again.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        scanner.close();
    }

    private static Currency getCurrency(String code) {
        switch (code) {
            case "USD":
                return new Currency("USD", 1.0);
            case "EUR":
                return new Currency("EUR", 0.85);
            case "GBP":
                return new Currency("GBP", 0.72);
            default:
                throw new IllegalArgumentException("Invalid currency code.");
        }
    }
}