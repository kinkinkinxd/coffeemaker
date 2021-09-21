package edu.ncsu.csc326.coffeemaker;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.TestCase.*;

public class CucumberTest {
    private CoffeeMaker coffeeMaker;
    Inventory inventory;
    Exception exception;

    @Given("coffeemaker is ready to use")
    public void coffeemakerIsReadyToUse() {
        coffeeMaker = new CoffeeMaker();
        inventory = new Inventory();
    }

    @When("I add {int} coffee, {int} milk, {int} sugar, and {int} chocolate to our inventory")
    public void iAddCoffeeMilkSugarAndChocolateToOurInventory(Integer amt1, Integer amt2, Integer amt3, Integer amt4) {
        try {
            coffeeMaker.addInventory(amt1.toString(), amt2.toString(), amt3.toString(), amt4.toString());
        } catch (Exception e) {
            exception = e;
        }
    }



    @Then("inventory has {int} coffee, {int} milk, {int} sugar, and {int} chocolate to our inventory")
    public void inventoryHasCoffeeMilkSugarAndChocolateToOurInventory(Integer amt1, Integer amt2, Integer amt3, Integer amt4) {
          String str = "Coffee: " + amt1.toString() + "\nMilk: " + amt2.toString() + "\nSugar: " +amt3.toString() + "\nChocolate: " +amt4.toString() + "\n";
          assertEquals(str, coffeeMaker.checkInventory());
    }



    @When("I add {int} recipe to the recipe book")
    public void iAddRecipeToTheRecipeBook(Integer int1) {
        for (int i = 0; i < int1; i++) {
            Recipe recipe = new Recipe();
            recipe.setName("Recipe " + (i+1));
            coffeeMaker.addRecipe(recipe);
        }
    }



    @Then("recipe book has {int} recipes")
    public void recipeBookHasRecipes(Integer int1) {
        int recipeCount = 0;
        for (Recipe recipe: coffeeMaker.getRecipes()) {
            if (recipe != null) {
                if (recipe.getClass() == Recipe.class) recipeCount++;
            }
        }
        assertEquals(int1.intValue(), recipeCount);
    }


}
