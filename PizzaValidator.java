import java.util.ArrayList;

public class PizzaValidator {
    private ArrayList<String> selectedToppings;
    private String selectedDough;
    private String generatedPizza;

    public PizzaValidator(String selectedDough, ArrayList<String> selectedToppings, String generatedPizza) {
        this.selectedDough = selectedDough;
        this.selectedToppings = selectedToppings;
        this.generatedPizza = generatedPizza;
        
    }

    public boolean validatePizza() {
        String[] generatedPizzaIngredients = generatedPizza.split(" ");
        String[] selectDough = selectedDough.split(" ");

        //check dough
        if (!selectDough[0].equalsIgnoreCase(generatedPizzaIngredients[0])) {
            return false;
        }

        //check toppings
        for (int i = 1; i < generatedPizzaIngredients.length; i++) {
            if(!(selectedToppings.contains(generatedPizzaIngredients[i]))){
                return false ;
            }
        }
        return true;
        
    }
}

