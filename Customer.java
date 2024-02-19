import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Customer {
    private static final String[] doughs = {"Thick dough", "Thin dough"};
    // private static final String[] toppings = {"Cheese", "Pineapple", "Mushroom", "Ham", "Seafood","Bacon"};
    public static String show_GenerateMenu = "";
    public static String toptopings = "";

    public static String generateMenu() {
        Random random = new Random();
        String dough = doughs[random.nextInt(doughs.length)]; //random doughs
    
        String[] selectedToppings;
    
        switch(random.nextInt(4)) {
            case 0:
                selectedToppings = new String[]{"Cheese", "Pineapple", "Ham", "Bacon"};
                break;
            case 1:
                selectedToppings = new String[]{"Cheese", "Pineapple", "Bacon", "Seafood"};
                break;
            case 2:
                selectedToppings = new String[]{"Cheese", "Mushroom", "Ham", "Bacon"};
                break;
            case 3:
                selectedToppings = new String[]{"Cheese", "Pineapple", "Mushroom", "Seafood"};
                break;
            default:
                selectedToppings = new String[]{};
        }

        String topping_String = String.join(" ", selectedToppings);
        show_GenerateMenu =  dough + " " + topping_String ;

        String[] Part = topping_String.split(" ");

        String[] Hawaiian = {"Cheese", "Pineapple", "Ham","Bacon"};
        String[] Seafood_Cocktail = {"Cheese", "Pineapple", "Bacon", "Seafood"};
        String[] Super_Deluxe = {"Cheese", "Mushroom", "Ham","Bacon"};
        String[] Seafood_Paradise = {"Cheese", "Pineapple", "Mushroom", "Seafood",};
    
        Set<String> Toppings = new HashSet<>(Arrays.asList(Part).subList(1, 4));

        if (Arrays.asList(Hawaiian).containsAll(Toppings)) {
            toptopings = "Hawaiian";
        } else if (Arrays.asList(Seafood_Cocktail).containsAll(Toppings)) {
            toptopings =  "Seafood Cocktail";
        } else if (Arrays.asList(Super_Deluxe).containsAll(Toppings)) {
            toptopings =  "Super Deluxe";
        } else if (Arrays.asList(Seafood_Paradise).containsAll(Toppings)) {
            toptopings =  "Seafood Paradise";
        } 
    
        return  "Customer ordered: " + dough + " " + toptopings  + " pizza";

    }

    public static String Menu_for_check() {
        String[] menuParts = show_GenerateMenu.split(" ");

        return menuParts[0] +" " + menuParts[2] +" "+ menuParts[3] + " " + menuParts[3] + " " + menuParts[4];
    }
    public static void setString(){
        show_GenerateMenu = "";
    }
    
}