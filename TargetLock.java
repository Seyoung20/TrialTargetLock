import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// --- ABSTRACTION: The base template for all food items ---
abstract class FoodItem {
    // ENCAPSULATION: Private fields
    private String name;
    private String healthLevel;
    private String reason;
    private final String category;
    private String preparationMethod; // ADDED from Tame.java

    // Constructor
    public FoodItem(String name, String healthLevel, String reason, String category, String preparationMethod) {
        this.name = name;
        this.healthLevel = healthLevel;
        this.reason = reason;
        this.category = category;
        this.preparationMethod = preparationMethod;
    }

    // ENCAPSULATION: Getters and Setters
    public String getName() { return name; }
    public String getHealthLevel() { return healthLevel; }
    public String getReason() { return reason; }
    public String getCategory() { return category; }
    public String getPreparationMethod() { return preparationMethod; } // ADDED

    public void setPreparationMethod(String preparationMethod) { // ADDED
        this.preparationMethod = preparationMethod;
    }

    // ABSTRACTION: Abstract method to force subclasses to implement a food-specific display detail
    public abstract String getFoodSpecificDetail();
    
    // POLYMORPHISM: Goal impact method (Abstract from Tame, implemented here for central logic)
    public abstract String getGoalImpact(String userGoal);
    
    // ABSTRACTION: Abstract method for specific recommendations (From TargetLock)
    public abstract String getRecommendations(String userGoal);
}

// --- INHERITANCE (Subclass 1): High-protein items ---
class MeatItem extends FoodItem {
    private final String proteinSource;

    // Constructor
    public MeatItem(String name, String healthLevel, String reason, String preparationMethod, String proteinSource) {
        super(name, healthLevel, reason, "Meat & Protein", preparationMethod); // Updated with preparationMethod
        this.proteinSource = proteinSource;
    }

    // POLYMORPHISM: Overriding goal impact (Refined logic from Tame.java)
    @Override
    public String getGoalImpact(String userGoal) {
        if (userGoal.equals("Gain Muscle (High Protein)") && getHealthLevel().contains("Healthy")) {
            return "This high-protein food is **excellent** for muscle building!";
        } else if (userGoal.equals("Lose Weight (Low Calorie/Fat)") && getHealthLevel().contains("Healthy")) {
            return "Lean protein helps maintain muscle while losing fat!";
        } else if (userGoal.equals("Heart Health") && getHealthLevel().contains("Healthy")) {
            return "Lean protein supports cardiovascular health!";
        }
        
        if (getHealthLevel().contains("Healthy") || getHealthLevel().contains("Moderately Healthy")) {
            return "This food generally supports your '" + userGoal + "' goal.";
        } else {
            return "This food may challenge your '" + userGoal + "' goal. Choose leaner/less processed options.";
        }
    }
    
    // ABSTRACTION Implementation
    @Override
    public String getFoodSpecificDetail() {
        return "Protein Source: " + proteinSource;
    }

    // ABSTRACTION Implementation: Specific recommendations (Refined from Tame.java getRecommendation logic)
    @Override
    public String getRecommendations(String userGoal) {
        if (getPreparationMethod().contains("Fried") || getHealthLevel().equals("Unhealthy")) {
            return "Recommendation: Swap for grilled chicken, fish, or tofu to drastically reduce saturated fat and sodium.";
        }
        if (userGoal.equals("Heart Health") && getName().contains("Beef")) {
             return "Recommendation: Choose **extra lean** cuts of beef or opt for plant-based sources 1-2 times a week to lower cholesterol.";
        }
        if (userGoal.equals("Gain Muscle (High Protein)")) {
            return "Recommendation: Excellent protein choice! Complement with complex carbs and plenty of water.";
        }
        return "Recommendation: Keep your portions moderate and pair with a generous serving of vegetables for maximum nutrient absorption.";
    }
}

// --- INHERITANCE (Subclass 2): General produce ---
class ProduceItem extends FoodItem {
    private final boolean isHighFiber;

    // Constructor for Fruits/Vegetables/Snacks (Standard)
    public ProduceItem(String name, String healthLevel, String reason, String category, boolean isHighFiber) {
        super(name, healthLevel, reason, category, "Raw/Cooked");
        this.isHighFiber = isHighFiber;
    }
    
    // POLYMORPHISM: Overriding goal impact (Refined logic from Tame.java)
    @Override
    public String getGoalImpact(String userGoal) {
        if ((userGoal.equals("Lose Weight (Low Calorie/Fat)") || userGoal.equals("Improve Digestion")) && isHighFiber) {
            return "High fiber content is **ideal** for weight control and improving digestion!";
        } else if (userGoal.equals("Boost Immunity") || userGoal.equals("Heart Health")) {
            return "Rich in vitamins and antioxidants for immune and heart support!";
        } else if (userGoal.equals("Maintain Energy (Balanced)")) {
            return "Provides steady energy through complex carbs and fiber!";
        }
        
        if (getHealthLevel().contains("Healthy")) {
            return "This food generally supports your '" + userGoal + "' goal.";
        } else {
            return "This food may challenge your '" + userGoal + "' goal. Choose whole fruits/veggies.";
        }
    }
    
    // ABSTRACTION Implementation
    @Override
    public String getFoodSpecificDetail() {
        return "Fiber Status: " + (isHighFiber ? "High Fiber" : "Standard Fiber");
    }

    // ABSTRACTION Implementation: Specific recommendations (Refined from Tame.java getRecommendation logic)
    @Override
    public String getRecommendations(String userGoal) {
        if (getCategory().equals("Snacks") && !getName().contains("Fresh fruit")) {
            return "Recommendation: Focus on whole, unprocessed fruits and vegetables for snacks (e.g., carrots, apples).";
        }
        if (userGoal.equals("Improve Digestion") && !isHighFiber) {
            return "Recommendation: Increase intake of foods like legumes and leafy greens to boost fiber.";
        }
        return "Recommendation: Consume a variety of colors daily to maximize nutrient intake (Eat the rainbow!).";
    }
}

// --- INHERITANCE (Subclass 3): Drinks ---
class DrinkItem extends FoodItem {
    private final boolean isCaffeinated;

    // Constructor
    public DrinkItem(String name, String healthLevel, String reason, boolean isCaffeinated) {
        super(name, healthLevel, reason, "Drinks", "Liquid");
        this.isCaffeinated = isCaffeinated;
    }
    
    // ABSTRACTION Implementation
    @Override
    public String getFoodSpecificDetail() {
        return "Caffeine: " + (isCaffeinated ? "Yes" : "No");
    }
    
    // POLYMORPHISM: Overriding to provide stress/sleep/energy advice (Refined logic from Tame.java)
    @Override
    public String getGoalImpact(String userGoal) {
        if (userGoal.equals("Reduce Stress") || userGoal.equals("Improve Mood")) {
            if (isCaffeinated) {
                return "Caffeine may **interfere** with stress reduction and mood stability. Limit consumption.";
            } else {
                return "Hydration supports better mood and stress management!";
            }
        } else if (userGoal.equals("Increase Energy") && isCaffeinated) {
            return "Provides a natural energy boost. Use wisely to support your goal, but avoid late in the day.";
        } else if (getHealthLevel().equals("Unhealthy") && 
                   (userGoal.equals("Lose Weight (Low Calorie/Fat)") || userGoal.equals("Heart Health"))) {
            return "High sugar intake from this drink strongly **hinders** your '" + userGoal + "' goal. Switch to water.";
        }
        
        if (getHealthLevel().contains("Healthy")) {
            return "This drink supports your '" + userGoal + "' goal.";
        } else {
            return "Consider switching to water or unsweetened options.";
        }
    }

    // ABSTRACTION Implementation: Specific recommendations
    @Override
    public String getRecommendations(String userGoal) {
        if (getHealthLevel().equals("Unhealthy")) {
            return "Recommendation: Replace sugary drinks with water, unsweetened tea, or sparkling water with lemon to help with weight and heart goals.";
        }
        if (isCaffeinated) {
            return "Recommendation: Limit consumption to the morning and switch to water in the afternoon for better stress and sleep management.";
        }
        return "Recommendation: Hydration is key! Try to drink water consistently throughout the day to help maintain energy.";
    }
}

// --- INHERITANCE (Subclass 4): CUSTOM NUTRITION ITEMS (From Tame.java) ---
class CustomNutritionItem extends FoodItem {
    private double calories, sugar, fat, protein;

    public CustomNutritionItem(String name, String healthLevel, String reason,
                               double calories, double sugar, double fat, double protein) {
        super(name, healthLevel, reason, "Custom", 
              String.format("C:%.0f P:%.1f S:%.1f F:%.1f", calories, protein, sugar, fat));
        this.calories = calories;
        this.sugar = sugar;
        this.fat = fat;
        this.protein = protein;
    }

    @Override
    public String getFoodSpecificDetail() {
        return String.format("Calories: %.0f, Protein: %.1fg, Sugar: %.1fg, Fat: %.1fg",
                calories, protein, sugar, fat);
    }

    // POLYMORPHISM: Goal impact with detailed macro analysis (From Tame.java)
    @Override
    public String getGoalImpact(String userGoal) {
        if (userGoal.equals("Lose Weight (Low Calorie/Fat)")) {
            if (calories <= 200 && fat <= 8) {
                return "Low calorie + low fat = **perfect** for weight loss!";
            } else {
                return "Reduce calories/fat for better weight loss results.";
            }
        } else if (userGoal.equals("Gain Muscle (High Protein)")) {
            if (protein >= 20) {
                return "High protein content = **excellent** for muscle growth!";
            } else {
                return "Aim for 20g+ protein per serving for muscle gains.";
            }
        } else if (userGoal.equals("Maintain Energy (Balanced)")) {
            if (protein >= 8 && sugar <= 10) {
                return "Balanced macros provide steady, sustained energy!";
            } else {
                return "Balance protein and carbs for better energy stability.";
            }
        } else if (userGoal.equals("Heart Health")) {
            if (fat <= 8 && calories <= 300) {
                return "Low fat + moderate calories supports heart health!";
            } else {
                return "Lower saturated fat intake for better heart protection.";
            }
        }
        
        if (getHealthLevel().contains("Healthy")) {
            return "This food supports your '" + userGoal + "' goal!";
        } else {
            return "Consider healthier alternatives for your '" + userGoal + "' goal.";
        }
    }
    
    // ABSTRACTION Implementation: Specific recommendations
    @Override
    public String getRecommendations(String userGoal) {
        if (getHealthLevel().equals("Unhealthy")) {
             return "Recommendation: Look for alternatives with lower sugar/fat and higher protein/fiber. Re-enter a cleaner version.";
        }
        if (userGoal.equals("Gain Muscle (High Protein)") && protein < 15) {
             return "Recommendation: To maximize muscle gain, aim for a higher protein content (20g+) per serving.";
        }
        return "Recommendation: Keep tracking your macronutrients to maintain this **Healthy** balance.";
    }
}


// --- Utility Class for Food Creation and Input Management (Factory Pattern Concept) ---
class FoodItemFactory {
    
    // EXCEPTION HANDLING: Custom exception for flow control
    public static class GoBackException extends Exception {}
    
    /**
     * Helper method to process user choice and check for "Go Back"
     * @param scanner The Scanner object.
     * @param prompt The message to display to the user.
     * @param maxChoice The highest valid index number (excluding the "Go Back" option).
     * @return The validated integer choice.
     * @throws NumberFormatException if the input is not a number.
     * @throws GoBackException if the user enters 'back' or the 'Go Back' number.
     */
    private static int getChoice(Scanner scanner, String prompt, int maxChoice) throws NumberFormatException, GoBackException {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("back")) {
                throw new GoBackException();
            }
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Invalid input. Please enter a number.");
                continue;
            }
            // maxChoice + 1 is always the 'Go Back' option
            if (choice == maxChoice + 1) {
                throw new GoBackException();
            }
            if (choice < 1 || choice > maxChoice) {
                System.out.println("[ERROR] Number out of range. Please try again.");
                continue;
            }
            return choice;
        }
    }
    
    // Method to display a formatted menu header (From TargetLock.java)
    private static void printMenuHeader(String title) {
        int width = 78;
        int padding = (width - title.length()) / 2;
        String dashes = new String(new char[width]).replace('\0', '-');
        System.out.println("\n+" + dashes + "+");
        System.out.printf("|%s%s%s|\n", 
            new String(new char[padding]).replace('\0', ' '), 
            title, 
            new String(new char[width - title.length() - padding]).replace('\0', ' '));
        System.out.println("+" + dashes + "+");
    }

    // --- ProduceItem Factory Methods ---
    public static FoodItem getFruits(Scanner scanner) throws NumberFormatException, GoBackException {
        printMenuHeader("Fruits Menu");
        String[] items = {"Mangoes", "Bananas", "Watermelon", "Pineapple", "Papaya", "Citrus fruits"};
        for (int i = 0; i < items.length; i++) {
            System.out.printf("| %-76s |\n", (i + 1) + ". " + items[i]);
        }
        System.out.printf("| %-76s |\n", (items.length + 1) + ". Go Back to Main Menu");
        System.out.println("+" + "-".repeat(78) + "+");
        int choice = getChoice(scanner, "Enter choice (" + (items.length + 1) + " to go back): ", items.length);
        return new ProduceItem(items[choice - 1], "Healthy", "Rich in vitamins and minerals.", "Fruits", true);
    }

    public static FoodItem getVegetables(Scanner scanner) throws NumberFormatException, GoBackException {
        printMenuHeader("Vegetables Menu");
        String[] items = {"Leafy greens", "Root vegetables", "Squash family", "Beans", "Bitter vegetables"};
        for (int i = 0; i < items.length; i++) {
            System.out.printf("| %-76s |\n", (i + 1) + ". " + items[i]);
        }
        System.out.printf("| %-76s |\n", (items.length + 1) + ". Go Back to Main Menu");
        System.out.println("+" + "-".repeat(78) + "+");
        int choice = getChoice(scanner, "Enter choice (" + (items.length + 1) + " to go back): ", items.length);
        return new ProduceItem(items[choice - 1], "Healthy", "Rich in fiber and vitamins.", "Vegetables", true);
    }
    
    public static FoodItem getSnacks(Scanner scanner) throws NumberFormatException, GoBackException {
        printMenuHeader("Snacks Menu");
        String[] items = {"Bread & pastries", "Noodles (Instant)", "Rice cakes / desserts", "Chips / Crisps", "Fresh fruit (as snack)"};
        for (int i = 0; i < items.length; i++) {
            System.out.printf("| %-76s |\n", (i + 1) + ". " + items[i]);
        }
        System.out.printf("| %-76s |\n", (items.length + 1) + ". Go Back to Main Menu");
        System.out.println("+" + "-".repeat(78) + "+");

        int choice = getChoice(scanner, "Enter choice (" + (items.length + 1) + " to go back): ", items.length);
        String foodName = items[choice - 1];
        String healthLevel = "Moderately Healthy";
        String reason = "Often high in carbs. Watch portions.";

        if (foodName.contains("Chips") || foodName.contains("Noodles")) {
            healthLevel = "Unhealthy";
            reason = "High in fat, sodium, and preservatives.";
        } else if (foodName.contains("Fresh fruit")) {
            healthLevel = "Healthy";
            reason = "Natural sugars and fiber.";
            return new ProduceItem(foodName, healthLevel, reason, "Snacks", true);
        }

        return new ProduceItem(foodName, healthLevel, reason, "Snacks", false);
    }


    // --- MeatItem Factory Methods (The Tame.java logic is implemented here) ---
    public static FoodItem getMeatAndProtein(Scanner scanner) throws NumberFormatException, GoBackException {
        printMenuHeader("Meat & Protein Menu");
        String[] options = {
                "Chicken", "Pork", "Beef", "Seafood", "Processed meat", "Eggs", "Tofu"
        };
        for (int i = 0; i < options.length; i++) {
            System.out.printf("| %-76s |\n", (i + 1) + ". " + options[i]);
        }
        System.out.printf("| %-76s |\n", (options.length + 1) + ". Go Back to Main Menu");
        System.out.println("+" + "-".repeat(78) + "+");

        int choice = getChoice(scanner, "Enter choice (" + (options.length + 1) + " to go back): ", options.length);
        switch (choice) {
            case 1:
            case 2:
                return getPreparedMeat(scanner, choice == 1 ? "Chicken" : "Pork");
            case 3:
                return new MeatItem("Beef (Lean)", "Moderately Healthy", "Good source of protein and iron; watch fat content.", "N/A", "Red Meat");
            case 4:
                return new MeatItem("Seafood", "Healthy", "Lean protein, high in Omega-3.", "N/A", "Fish/Shellfish");
            case 5:
                return new MeatItem("Processed meat", "Unhealthy", "High in preservatives and sodium.", "N/A", "Mixed");
            case 6:
                return getPreparedEggs(scanner);
            case 7:
                return new MeatItem("Tofu", "Healthy", "Low-fat, plant-based protein.", "Steamed", "Plant-based");
            default:
                throw new GoBackException();
        }
    }

    private static FoodItem getPreparedMeat(Scanner scanner, String type) throws NumberFormatException, GoBackException {
        printMenuHeader("Select preparation method for " + type);
        String[] methods = {
                "Fried (Deep) - High oil",
                "Grilled / Baked - Lean",
                "Boiled / Steamed - Very low fat"
        };
        for (int i = 0; i < methods.length; i++) {
            System.out.printf("| %-76s |\n", (i + 1) + ". " + methods[i]);
        }
        System.out.printf("| %-76s |\n", (methods.length + 1) + ". Go Back to Protein Menu");
        System.out.println("+" + "-".repeat(78) + "+");

        int prepChoice = getChoice(scanner, "Enter choice (" + (methods.length + 1) + " to go back): ", methods.length);
        switch (prepChoice) {
            case 1:
                return new MeatItem(type + " (Fried)", "Unhealthy", "High in oil and saturated fat.", "Fried", type);
            case 2:
                return new MeatItem(type + " (Grilled)", "Healthy", "Lean cooking method.", "Grilled", type);
            case 3:
                return new MeatItem(type + " (Boiled)", "Healthy", "Very low fat, high moisture.", "Boiled", type);
            default:
                throw new GoBackException();
        }
    }

    private static FoodItem getPreparedEggs(Scanner scanner) throws NumberFormatException, GoBackException {
        printMenuHeader("Select preparation method for Eggs");
        String[] methods = {
                "Boiled - Minimal fat",
                "Fried / Scrambled (with oil/butter) - Added fat"
        };
        for (int i = 0; i < methods.length; i++) {
            System.out.printf("| %-76s |\n", (i + 1) + ". " + methods[i]);
        }
        System.out.printf("| %-76s |\n", (methods.length + 1) + ". Go Back to Protein Menu");
        System.out.println("+" + "-".repeat(78) + "+");

        int prepChoice = getChoice(scanner, "Enter choice (" + (methods.length + 1) + " to go back): ", methods.length);
        switch (prepChoice) {
            case 1:
                return new MeatItem("Eggs (Boiled)", "Healthy", "High in protein and low in fat.", "Boiled", "Eggs");
            case 2:
                return new MeatItem("Eggs (Fried)", "Moderately Healthy", "Cooking in oil/butter adds fat.", "Fried", "Eggs");
            default:
                throw new GoBackException();
        }
    }


    // --- DrinkItem Factory Methods ---
    public static FoodItem getDrinks(Scanner scanner) throws NumberFormatException, GoBackException {
        printMenuHeader("Drinks Menu");
        String[] items = {"Water", "Milk", "Coffee (Black)", "Soft drinks (Soda)", "Iced tea (Sweetened)", "Fruit Juices (Sweetened)"};
        for (int i = 0; i < items.length; i++) {
            System.out.printf("| %-76s |\n", (i + 1) + ". " + items[i]);
        }
        System.out.printf("| %-76s |\n", (items.length + 1) + ". Go Back to Main Menu");
        System.out.println("+" + "-".repeat(78) + "+");

        int choice = getChoice(scanner, "Enter choice (" + (items.length + 1) + " to go back): ", items.length);
        String foodName = items[choice - 1];
        String healthLevel = "Healthy";
        String reason = "Hydrating";
        boolean isCaffeinated = false;

        if (foodName.contains("Soft drinks") || foodName.contains("Iced tea") || foodName.contains("Fruit Juices")) {
            healthLevel = "Unhealthy";
            reason = "Very high in sugar and calories.";
        } else if (foodName.contains("Coffee")) {
            isCaffeinated = true;
            reason = "Provides energy, but is a stimulant.";
        }

        return new DrinkItem(foodName, healthLevel, reason, isCaffeinated);
    }
    
    // --- CustomNutritionItem Factory Methods (Refined Tame.java logic) ---
    public static FoodItem getCustomFoodDetails(Scanner scanner) {
        System.out.println("\n================================================================================");
        System.out.println("CUSTOM FOOD ENTRY - NUTRITIONAL ANALYSIS");
        System.out.println("================================================================================");

        System.out.print("Enter food name: ");
        String name = scanner.nextLine().trim();

        double calories = readNumericInput(scanner, "Enter calories (kcal): ");
        double sugar = readNumericInput(scanner, "Enter sugar (g): ");
        double fat = readNumericInput(scanner, "Enter fat (g): ");
        double protein = readNumericInput(scanner, "Enter protein (g): ");

        String healthLevel = analyzeHealthLevel(calories, sugar, fat, protein);
        String reason = generateReason(calories, sugar, fat, protein, healthLevel);

        return new CustomNutritionItem(name, healthLevel, reason, calories, sugar, fat, protein);
    }
    
    // Helper for reading numeric input in Custom Food
    private static double readNumericInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value < 0) throw new NumberFormatException("Value cannot be negative");
                return value;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Please enter a valid positive number.");
            }
        }
    }
    
    // Analysis logic from Tame.java
    private static String analyzeHealthLevel(double calories, double sugar, double fat, double protein) {
        int score = 0;

        if (calories <= 200) score += 2;
        else if (calories <= 400) score += 1;
        else score -= 1;

        if (sugar <= 5) score += 2;
        else if (sugar <= 15) score += 0;
        else score -= 2;

        if (fat <= 8) score += 2;
        else if (fat <= 18) score += 0;
        else score -= 2;

        if (protein >= 15) score += 2;
        else if (protein >= 8) score += 1;

        if (score >= 4) return "Healthy";
        else if (score >= 1) return "Moderately Healthy";
        else return "Unhealthy";
    }

    // Reason generation logic from Tame.java
    private static String generateReason(double calories, double sugar, double fat, double protein, String healthLevel) {
        StringBuilder reason = new StringBuilder();

        if (healthLevel.equals("Healthy")) {
            reason.append("Excellent nutritional balance. ");
            if (protein >= 15) reason.append("High protein. ");
            if (sugar <= 5) reason.append("Low sugar. ");
            if (calories <= 200) reason.append("Low calorie. ");
        } else if (healthLevel.equals("Moderately Healthy")) {
            reason.append("Good balance with improvement potential. ");
            if (sugar > 15) reason.append("Moderate sugar. ");
            if (protein >= 8) reason.append("Good protein. ");
        } else {
            reason.append("Needs nutritional improvement. ");
            if (sugar > 25 || fat > 25) reason.append("High sugar/fat. ");
        }

        return reason.toString().trim();
    }
}


// --- Main Application (Using TargetLock.java structure) ---
public class TargetLock{
    private static Scanner scanner = new Scanner(System.in);
    private static int streakCounter = 0;
    // Made public static for use in FoodItem classes' getRecommendations method
    public static String userGoal = ""; 
    
    private static final String[] STREAK_MESSAGES = {
        "Keep up the great work!", "Every healthy choice counts!", "You're doing amazing!", 
        "Consistency is key to success!", "Small steps lead to big changes!", 
        "Your dedication is inspiring!", "Stay focused on your goals!", 
        "Health is wealth—keep investing!", "Proud of your progress!", "Keep shining with healthy choices!"
    };

    public static void main(String[] args) {
        System.out.println("\n+==============================================================================+");
        System.out.println("|                       WELCOME TO TARGETLOCK!                                 |");
        System.out.println("|                     Your Personal Food Analysis Guide                        |");
        System.out.println("+==============================================================================+");
        selectTargetGoal();

        while (true) {
            FoodItem selectedFood = null; 
            try {
                selectedFood = selectFoodItem();
                
                if (selectedFood != null) {
                    displayResults(selectedFood);
                    // Only increment streak if a valid food item was selected
                    streakCounter++; 
                }
            } catch (FoodItemFactory.GoBackException e) {
                System.out.println("\n[INFO] Returning to the main food selection menu...");
            } catch (NumberFormatException e) {
                System.out.println("\n[ERROR] Invalid input. Please enter a valid number for your choice. Try again.");
            } catch (Exception e) {
                System.out.println("\n[FATAL ERROR] An unexpected error occurred: " + e.getMessage());
            }

            // --- Input Validation Loop ---
            String response = "";
            boolean validInput = false;
            while (!validInput) {
                System.out.println("\n--------------------------------------------------------------------------------");
                System.out.println("Do you want to log another food item? (YES / NO)");
                System.out.print("Response: ");
                response = scanner.nextLine().trim().toLowerCase();
                System.out.println("--------------------------------------------------------------------------------");

                if (response.equals("yes") || response.equals("no")) {
                    validInput = true;
                } else {
                    System.out.println("\n[ERROR] Invalid option. Please type 'yes' or 'no'.");
                }
            }
            
            if (response.equals("no")) {
                System.out.println("\n================================================================================");
                System.out.println("Goodbye! Stay focused on your '" + userGoal + "' goal!");
                System.out.println("Your final logged streak was: " + streakCounter + " items.");
                System.out.println("================================================================================");
                break;
            }
        }
        scanner.close();
    }

    private static void selectTargetGoal() {
        System.out.println("\n********************************************************************************");
        System.out.println("* SELECT YOUR TARGET HEALTH GOAL                                               *");
        System.out.println("********************************************************************************");
        String[] goals = {
            "Lose Weight (Low Calorie/Fat)", "Gain Muscle (High Protein)", "Maintain Energy (Balanced)",
            "Improve Mood", "Heart Health", "Reduce Stress", "Increase Energy", "Improve Digestion", "Boost Immunity"
        };
        for (int i = 0; i < goals.length; i++) {
            System.out.printf("| %-76s |\n", (i + 1) + ". " + goals[i]);
        }
        System.out.println("********************************************************************************");

        while (userGoal.isEmpty()) {
            System.out.print("Enter the number of your goal: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= goals.length) {
                    userGoal = goals[choice - 1];
                    System.out.println("\n[SUCCESS] Goal set to: >>> " + userGoal.toUpperCase() + " <<<");
                } else {
                    System.out.println("[WARNING] Invalid number. Please choose between 1 and " + goals.length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Invalid input. Please enter a number.");
            }
        }
    }

    private static FoodItem selectFoodItem() throws FoodItemFactory.GoBackException, NumberFormatException {
        System.out.println("\n+------------------------------------------------------------------------------+");
        System.out.println("|           LOG A NEW FOOD ITEM - CATEGORY SELECTION                           |");
        System.out.println("+------------------------------------------------------------------------------+");
        System.out.printf("| %-76s |\n", "1. Fruits");
        System.out.printf("| %-76s |\n", "2. Vegetables");
        System.out.printf("| %-76s |\n", "3. Meat & Protein");
        System.out.printf("| %-76s |\n", "4. Drinks");
        System.out.printf("| %-76s |\n", "5. Snacks");
        System.out.printf("| %-76s |\n", "6. Custom Food (Manual Entry)");
        System.out.printf("| %-76s |\n", "7. Exit Application"); 
        System.out.println("+------------------------------------------------------------------------------+");

        System.out.print("Enter the number of the category: ");
        int categoryChoice = Integer.parseInt(scanner.nextLine());

        FoodItem result = null;
        switch (categoryChoice) {
            case 1: result = FoodItemFactory.getFruits(scanner); break;
            case 2: result = FoodItemFactory.getVegetables(scanner); break;
            case 3: result = FoodItemFactory.getMeatAndProtein(scanner); break;
            case 4: result = FoodItemFactory.getDrinks(scanner); break; 
            case 5: result = FoodItemFactory.getSnacks(scanner); break;
            case 6: result = FoodItemFactory.getCustomFoodDetails(scanner); break;
            case 7: 
                System.out.println("Exiting TargetLock. Goodbye!");
                System.exit(0); 
            default:
                System.out.println("Invalid category choice. Returning to menu.");
                return null;
        }
        return result;
    }

    // --- DISPLAY METHODS (FROM TargetLock.java for good formatting) ---

    private static List<String> wrapText(String text, int maxWidth) {
        List<String> lines = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            lines.add("");
            return lines;
        }

        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (currentLine.length() == 0) {
                currentLine.append(word);
            } else if (currentLine.length() + 1 + word.length() <= maxWidth) {
                currentLine.append(" ").append(word);
            } else {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            }
        }
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }
        return lines;
    }
    
    private static void printWrappedLine(String label, String content, String contentPrefix, int labelWidth, int totalWidth) {
        int contentWidth = totalWidth - 4; // 74 total space inside borders
        int contentColumnWidth = contentWidth - labelWidth; 
        
        if (labelWidth == contentWidth) {
            contentColumnWidth = contentWidth; 
        }
        
        int maxTextLength = contentColumnWidth - contentPrefix.length();
        
        List<String> wrappedLines = wrapText(content, maxTextLength);
        
        if (wrappedLines.isEmpty()) {
            wrappedLines.add("");
        }

        String firstLine = wrappedLines.get(0);
        
        // --- 1. Print the Header Line ---
        if (labelWidth < contentWidth) {
             // Two-column layout (e.g., REASON)
             System.out.printf("| %-"+labelWidth+"s | %s%-"+maxTextLength+"s|\n", 
                label, contentPrefix, firstLine);
        } else {
             // Full-width layout (e.g., RECOMMENDATION/GOAL IMPACT)
             System.out.printf("| %-"+contentWidth+"s   |\n", label); 
             
             // Print the first line of content
             System.out.printf("| %s%-"+maxTextLength+"s   |\n", 
                contentPrefix, firstLine); 
        }
        
        // --- 2. Print subsequent wrapped content lines ---
        if (wrappedLines.size() > 1) {
            for (int i = 1; i < wrappedLines.size(); i++) {
                 String line = wrappedLines.get(i);
                 // Subsequent lines use the full 74 width, padded to maxTextLength
                 System.out.printf("| %s%-"+maxTextLength+"s   |\n", 
                    contentPrefix, line);
            }
        }
    }

    private static void displayResults(FoodItem food) {
        System.out.println("\n********************************************************************************");
        System.out.println("* ANALYSIS REPORT                                                              *");
        System.out.println("********************************************************************************");
        
        // Fixed Width Fields (30 | 45)
        System.out.printf("| %-30s | %-43s |\n", "ITEM:", food.getName());
        System.out.printf("| %-30s | %-43s |\n", "CATEGORY:", food.getCategory()); 
        
        // Conditional formatting based on health level
        String healthLevelDisplay = food.getHealthLevel();
        if (food.getHealthLevel().contains("Healthy")) {
            healthLevelDisplay = "[GOOD] " + healthLevelDisplay;
        } else {
            healthLevelDisplay = "[CAUTION] " + healthLevelDisplay;
        }

        System.out.printf("| %-30s | %-43s |\n", "HEALTH LEVEL:", healthLevelDisplay);
        
        // Specific Detail
        String detailLabel = food.getCategory().equals("Custom") ? "MACRO DETAILS:" : "SPECIFIC DETAIL:";
        System.out.printf("| %-30s | %-43s |\n", detailLabel, food.getFoodSpecificDetail()); 
        
        System.out.println("+------------------------------------------------------------------------------+");
        
        // REASON (Two Column Layout: Label Width 30)
        printWrappedLine("REASON:", food.getReason(), " ", 30, 78);
        
        // HEALTH RECOMMENDATION (Full Width Layout: Label Width 74)
        System.out.println("+------------------------------------------------------------------------------+");
        printWrappedLine("HEALTH RECOMMENDATION:", food.getRecommendations(userGoal), "=> ", 74, 78);
        
        System.out.println("+==============================================================================+");
        
        // GOAL IMPACT (Full Width Layout: Label Width 74)
        printWrappedLine("GOAL IMPACT (Target: " + userGoal.toUpperCase() + "):", food.getGoalImpact(userGoal), "=> ", 74, 78);
        System.out.println("+==============================================================================+");

        // --- STREAK LOGIC ---
        int currentStreak = streakCounter + 1;
        int messageIndex = streakCounter % STREAK_MESSAGES.length;
        String motivationalMessage = STREAK_MESSAGES[messageIndex];
        String streakMessage = String.format("Streak %d: %s", currentStreak, motivationalMessage);
        
        System.out.printf("| %-76s |\n", "PROGRESS: " + streakMessage);
        System.out.println("********************************************************************************");
    }
}