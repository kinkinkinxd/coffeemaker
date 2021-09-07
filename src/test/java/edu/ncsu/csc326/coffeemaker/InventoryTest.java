package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Unit tests for Inventory class.
 *
 * @author Kittitouch Ingkasompob
 */
public class InventoryTest {

    /**
     * The object under test.
     */
    private Inventory inventory;

    /**
     * Initializes inventory
     * @throws RecipeException if there was an error parsing the ingredient
     * 	 * 		amount when setting up the recipe.
     */
    @Before
    public void setUp() throws RecipeException {
         inventory = new Inventory();
    }

    /**
     * Inventory can set amount of item
     * When we set the chocolate with number with non negative
     * Then the chocolate in the inventory will change
     */
    @Test
    public void testSetChocolate() {
        inventory.setChocolate(100);
        assertEquals(100, inventory.getChocolate());
        inventory.setChocolate(-100);// Check negative number
        assertEquals(100,inventory.getChocolate());
        inventory.setChocolate(0);// Check set to 0
        assertEquals(0, inventory.getChocolate());
    }

    /**
     * Inventory can add the chocolate
     * When we add the chocolate with number with non negative
     * Then the chocolate in the inventory will change
     */
    @Test
    public void testAddChocolate() throws InventoryException {
        inventory.addChocolate("100");
        assertEquals(115, inventory.getChocolate());
    }

    /**
     * Inventory can't add the chocolate with negative number
     * When we add the chocolate with negative number
     * @throws InventoryException if there was an error parsing the quanity
     * 	 * 		to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddNegativeChocolate() throws InventoryException {
        inventory.addChocolate("-100");
    }

    /**
     * Inventory can't add the chocolate with non number
     * When we add the chocolate with non number
     * @throws InventoryException if there was an error parsing the quanity
     * 	 * 		to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddChocolateWithNotNumber() throws InventoryException {
        inventory.addChocolate("test");
    }

    /**
     * Inventory can set amount of item
     * When we set the coffee with number with non negative
     * Then the coffee in the item will change
     */
    @Test
    public void testSetCoffee() {
        inventory.setCoffee(100);
        assertEquals(100, inventory.getCoffee());
        inventory.setCoffee(-100);// Check negative number
        assertEquals(100,inventory.getCoffee());
        inventory.setCoffee(0);// Check set to 0
        assertEquals(0, inventory.getCoffee());
    }

    /**
     * Inventory can add the coffee
     * When we add the coffee with number with non negative
     * Then the coffee in the inventory will change
     */
    @Test
    public void testAddCoffee() throws  InventoryException{
        inventory.addCoffee("100");
        assertEquals(115, inventory.getCoffee());
    }

    /**
     * Inventory can't add the coffee with negative number
     * When we add the coffee with negative number
     * @throws InventoryException if there was an error parsing the quanity
     * 	 * 		to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddNegativeCoffee() throws InventoryException{
        inventory.addCoffee("-100");
    }

    /**
     * Inventory can't add the coffee with non number
     * When we add the coffee with non number
     * @throws InventoryException if there was an error parsing the quanity
     * 	 * 		to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddCoffeeWithNotNumber() throws InventoryException{
        inventory.addCoffee("test");
    }

    /**
     * Inventory can set amount of item
     * When we set the milk with number with non negative
     * Then the milk in the item will change
     */
    @Test
    public void testSetMilk(){
        inventory.setMilk(100);
        assertEquals(100, inventory.getMilk());
        inventory.setMilk(-100);// Check negative number
        assertEquals(100,inventory.getMilk());
        inventory.setMilk(0);// Check set to 0
        assertEquals(0, inventory.getMilk());
    }

    /**
     * Inventory can add the milk
     * When we add the milk with number with non negative
     * Then the milk in the inventory will change
     */
    @Test
    public void testAddMilk() throws InventoryException{
        inventory.addMilk("100");
        assertEquals(115, inventory.getMilk());
    }

    /**
     * Inventory can't add the milk with negative number
     * When we add the milk with negative number
     * @throws InventoryException if there was an error parsing the quanity
     * 	 * 		to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddNegativeMilk() throws InventoryException{
        inventory.addMilk("-100");
    }

    /**
     * Inventory can't add the milk with non number
     * When we add the milk with non number
     * @throws InventoryException if there was an error parsing the quanity
     * 	 * 		to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddMilkWithNotNumber() throws InventoryException{
        inventory.addMilk("test");
    }

    /**
     * Inventory can set amount of item
     * When we set the sugar with number with non negative
     * Then the sugar in the item will change
     */
    @Test
    public void testSetSugar(){
        inventory.setSugar(100);
        assertEquals(100, inventory.getSugar());
        inventory.setSugar(-100);// Check negative number
        assertEquals(100,inventory.getSugar());
        inventory.setSugar(0);// Check set to 0
        assertEquals(0, inventory.getSugar());
    }

    /**
     * Inventory can add sugar
     * When we add the sugar with number with non negative
     * Then the sugar in the inventory will change
     */
    @Test
    public void testAddSugar() throws InventoryException{
        inventory.addSugar("100");
        assertEquals(115, inventory.getSugar());
    }

    /**
     * Inventory can't add the sugar with negative number
     * When we add the sugar with negative number
     * @throws InventoryException if there was an error parsing the quanity
     * 	 * 		to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddNegativeSugar() throws InventoryException{
        inventory.addSugar("-100");
    }

    /**
     * Inventory can't add the sugar with non number
     * When we add the sugar with non number
     * @throws InventoryException if there was an error parsing the quanity
     * 	 * 		to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddSugarWithNotNumber() throws InventoryException{
        inventory.addSugar("test");
    }

    /**
     * Inventory can check if enough item to make a recipe
     * When we set item in the recipe
     * Then it will return false because inventory don't have enough item to make the recipe
     * @throws RecipeException  if there was an error parsing the ingredient
     *      * 	 * 		amount when setting up the recipe.
     */
    @Test
    public void testEnoughIngredients() throws RecipeException{
        Recipe recipe = new Recipe();
        assertTrue(inventory.enoughIngredients(recipe));
        assertTrue(inventory.useIngredients(recipe));
        recipe.setAmtChocolate("100");
        assertFalse(inventory.enoughIngredients(recipe));
        assertFalse(inventory.useIngredients(recipe));
        recipe.setAmtChocolate("0");
        recipe.setAmtCoffee("100");
        assertFalse(inventory.enoughIngredients(recipe));
        assertFalse(inventory.useIngredients(recipe));
        recipe.setAmtCoffee("0");
        recipe.setAmtMilk("100");
        assertFalse(inventory.enoughIngredients(recipe));
        assertFalse(inventory.useIngredients(recipe));
        recipe.setAmtMilk("0");
        recipe.setAmtSugar("100");
        assertFalse(inventory.enoughIngredients(recipe));
        assertFalse(inventory.useIngredients(recipe));
    }
}
