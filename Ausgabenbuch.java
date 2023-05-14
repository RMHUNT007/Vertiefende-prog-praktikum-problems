import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ausgabenbuch{
    private Map<String, Map <String,Double>> shopExpenses;


    public Ausgabenbuch() {
        shopExpenses = new HashMap<>();
    }

    public void add(String shop, String category, double amount) {
        shopExpenses.putIfAbsent(shop, new HashMap<>());
        Map<String, Double> shopCategories = shopExpenses.get(shop);
        shopCategories.put(category, shopCategories.getOrDefault(category, 0.0) + amount);
        System.out.printf("Hinzugefügt zum Shop %s in der Kategorie %s: %.2f%n", shop, category, amount);
    }

    public void report(String type) {
        if (type.equals("shop")) {
            for (Map.Entry<String, Map<String, Double>> entry : shopExpenses.entrySet()) {
                String shop = entry.getKey();
                Map<String, Double> categories = entry.getValue();
                double total = categories.values().stream().mapToDouble(Double::doubleValue).sum();
                System.out.printf("%s: %.2f%n", shop, total);
            }
        } else if (type.equals("category")) {
            Map<String, Double> categoryExpenses = new HashMap<>();
            for (Map<String, Double> categories : shopExpenses.values()) {
                for (Map.Entry<String, Double> entry : categories.entrySet()) {
                    String category = entry.getKey();
                    double amount = entry.getValue();
                    categoryExpenses.put(category, categoryExpenses.getOrDefault(category, 0.0) + amount);
                }
            }
            for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
                String category = entry.getKey();
                double total = entry.getValue();
                System.out.printf("%s: %.2f%n", category, total);
            }
        } else {
            System.out.println("Ungültiger Report-Typ!");
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ausgabenbuch ist bereit für die Eingaben");
    
        while (true) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ");
    
            if (parts[0].equals("exit")) {
                System.out.println("Bye.");
                break;
            } else if (parts[0].equals("add")) {
                if (parts.length != 4) {
                    System.out.println("Ungültige Anzahl an Parametern für add-Kommando!");
                    continue;
                }
                String shop = parts[1];
                String category = parts[2];
                double amount = Double.parseDouble(parts[3]);
                add(shop, category, amount);
            } else if (parts[0].equals("report")) {
                if (parts.length != 2) {
                    System.out.println("Ungültige Anzahl an Parametern für report-Kommando!");
                    continue;
                }
                String reportType = parts[1];
                report(reportType);
            } else {
                System.out.println("Ungültiges Kommando!");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Ausgabenbuch ausgabenbuch = new Ausgabenbuch();
        ausgabenbuch.run();
    }
    

    
     
}