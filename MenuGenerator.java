import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MenuGenerator {
    private static final String[] doughs = {"thick dough", "thin dough"};
    private static final String[] toppings = {"cheese", "pineapple", "mushroom", "ham", "seafood","bacon"};

    public static String generateMenu() {
        Random random = new Random();
        String dough = doughs[random.nextInt(doughs.length)];
        Set<String> selectedToppings = new HashSet<>();
        while (selectedToppings.size() < 2) {
            String topping = toppings[random.nextInt(toppings.length)];
            selectedToppings.add(topping);
        }
        String toppingString = String.join(", ", selectedToppings);
        return "Customer ordered: " + dough + " , " + toppingString + " Pizza";
    }
    
    public static void main(String[] args) {
        System.out.println(generateMenu());
    }

    
}
