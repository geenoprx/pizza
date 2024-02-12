import java.util.ArrayList;
import java.util.Arrays;

public class PizzaValidator {
    private ArrayList<String> selectedToppings;
    private String selectedDough;
    private String generatedPizza;

    public PizzaValidator(String selectedDough, ArrayList<String> selectedToppings, String generatedPizza) {
        this.selectedDough = selectedDough;
        this.selectedToppings = selectedToppings;
        this.generatedPizza = generatedPizza;
        System.out.println("hello pizzaValidation"+generatedPizza);
    }

    public boolean validatePizza() {
        String[] generatedPizzaIngredients = generatedPizza.split(" ");
        String[] selectDough = selectedDough.split(" ");
        System.out.println("hello validate pizza");
        System.out.println(Arrays.toString(generatedPizzaIngredients));
        System.out.println(Arrays.toString(selectDough));
        System.out.println(selectedToppings);
        if (!selectDough[0].equalsIgnoreCase(generatedPizzaIngredients[0])) {
            return false;
        }
        for (int i = 1; i < generatedPizzaIngredients.length; i++) {
            if(!(selectedToppings.contains(generatedPizzaIngredients[i]))){
                return false ;
            }
        }
        return true;
        // // Check if all selected toppings match 
        // for (String topping : selectedToppings) {
        //     boolean found = false;
        //     if (topping.equalsIgnoreCase(generatedPizzaIngredients[1]) ) {
        //         if(topping.equalsIgnoreCase(generatedPizzaIngredients[2])){
        //             found = true;
        //         }
        //         else{
        //             return false;
        //         }
        //     }

        //     if (topping.equalsIgnoreCase(generatedPizzaIngredients[2]) ) {
        //         if(topping.equalsIgnoreCase(generatedPizzaIngredients[1])){
        //             found = true;
        //         }
        //         else{
        //             return false;
        //         }
        //     }
        
        //     if (!found) {
        //         return false;
        //     }
        // }

        // return true;
    }
}

