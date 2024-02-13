import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Customer {
    private static final String[] doughs = {"Thick dough", "Thin dough"};
    private static final String[] toppings = {"Cheese", "Pineapple", "Mushroom", "Ham", "Seafood","Bacon"};
    public static String show_GenerateMenu = "";

    public static String generateMenu() {
        Random random = new Random();
        String dough = doughs[random.nextInt(doughs.length)]; //random doughs

        //random toppings
        Set<String> selectToppings = new HashSet<>();
        while (selectToppings.size() < 2) {
            String topping = toppings[random.nextInt(toppings.length)];
            selectToppings.add(topping);
        }
        String topping_String = String.join(" ", selectToppings);

        show_GenerateMenu = "Customer ordered: " + dough + " " + topping_String + " pizza";

        return show_GenerateMenu;
    }

    public static String Menu_for_check() {
        String[] menuParts = show_GenerateMenu.split(" ");

        return menuParts[2] +" " + menuParts[4] +" "+ menuParts[5];
    }
    
}
