import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MenuGenerator {
    private static final String[] doughs = {"Thick dough", "Thin dough"};
    private static final String[] toppings = {"Cheese", "Pineapple", "Mushroom", "Ham", "Seafood","Bacon"};
    public static String lastGeneratedMenu = "";

    public static String generateMenu() {
        Random random = new Random();
        String dough = doughs[random.nextInt(doughs.length)];
        Set<String> selectedToppings = new HashSet<>();
        while (selectedToppings.size() < 2) {
            String topping = toppings[random.nextInt(toppings.length)];
            selectedToppings.add(topping);
        }
        String toppingString = String.join(" ", selectedToppings);
        lastGeneratedMenu = "Customer ordered: " + dough + " " + toppingString + " pizza";
        return lastGeneratedMenu;
    }

    public static String getLastGeneratedMenu() {
        String[] menuParts = lastGeneratedMenu.split(" ");
        // String[] partsss = parts[3].split(" pizza");
        // String[] menuParts = parts[1].split(" ");

        return menuParts[2] +" " + menuParts[4] +" "+ menuParts[5];
    }
    
    public static void main(String[] args) {
        System.out.println(generateMenu());
        System.out.println(getLastGeneratedMenu());
    }
}
