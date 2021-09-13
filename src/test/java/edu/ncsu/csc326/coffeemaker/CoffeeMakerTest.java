/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modifications 
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to 
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Kittitouch Ingkasompob
 */
public class CoffeeMakerTest {
	
	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;

	/**
	 * CoffeeMaker with mock RecipeBook
	 */
	private  CoffeeMaker coffeeMakerMock;

	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;

	private RecipeBook recipeBook;
	private Recipe[] recipes;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker} 
	 * object we wish to test.
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();
		
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");

		recipeBook = mock(RecipeBook.class);
		recipes = new Recipe[]{recipe1, recipe2, recipe3, recipe4};
		coffeeMakerMock = new CoffeeMaker(recipeBook, new Inventory());

	}

	/**
	 * Inventory can have only three recipe
	 * When we add only one recipe
	 * Then it will not return false when add the recipe
	 */
	@Test
	public void testAddRecipe() {
		assertTrue(coffeeMaker.addRecipe(recipe1));
	}

	/**
	 * Inventory can have only three recipe
	 * When we add recipe to inventory more than three recipe
	 * Then it will return false and no more recipe will be added
	 */
	@Test
	public void testAddOnlyThreeRecipe() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertFalse(coffeeMaker.addRecipe(recipe4));
	}

	/**
	 * Inventory can have only one recipe per name
	 * When we add same recipe to inventory
	 * Then it will return false and not allow recipe that have same name to be added
	 */
	@Test
	public void testAddDuplicateRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertFalse(coffeeMaker.addRecipe(recipe1));
	}

	/**
	 * Inventory can delete by index
	 * When we delete recipe in the inventory
	 * Then the recipe that have same index will be delete
	 */
	@Test
	public void testDeleteRecipe() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		assertEquals("Coffee", coffeeMaker.deleteRecipe(0));
	}

	/**
	 * Empty recipe book will be return null
	 * When no recipe and perform delete method
	 */
	@Test
	public void testDeleteEmptyRecipeList() {
		assertNull(coffeeMaker.deleteRecipe(0));
	}



	/**
	 * Given one recipe in Coffee Maker
	 * When delete recipe with index out of bound
	 * It will return null
	 */
	@Test
	public void testDeleteOutOfBoundIndexRecipe() {
		coffeeMaker.addRecipe(recipe1);
		String recipeStr = coffeeMaker.deleteRecipe(10);
		assertNull(recipeStr);
	}



	/**
	 * When edit recipe in recipe lists
	 * Recipe that have same index will change to new recipe
	 */
	@Test
	public void testEditRecipe() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.editRecipe(0, recipe4);
		Recipe[] recipes = coffeeMaker.getRecipes();
		assertEquals(recipes[0], recipe4);
	}

	/**
	 * When edit empty recipe
	 * It will return null
	 */
	@Test
	public void testEditEmptyRecipe() {
		assertNull(coffeeMaker.editRecipe(0, recipe2));
	}

	/**
	 * When edit recipes with index out of bound
	 * It will return null
	 */
	@Test
	public void testEditIndexOutOfBoundRecipe() {
		coffeeMaker.addRecipe(recipe3);
		coffeeMaker.addRecipe(recipe2);
		assertNull(coffeeMaker.editRecipe(10,recipe2));
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
		coffeeMaker.addInventory("1","2","20","1");
		// When sugar more than 0 it will fail to add inventory
	}


	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative 
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "asdf", "3");
	}

	/**
	 * When check the inventory it will display with the string
	 * So all item in the inventory should equal to 15 when started
	 */
	@Test
	public void testCheckInventory() {
		assertEquals(coffeeMaker.checkInventory(),"Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n");
	}

	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than 
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void testMakeCoffeePurchaseMoreThanCost() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}

	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying less than
	 * 	 * 		the coffee costs
	 * 	 Then we not get the coffee but get all money back.
	 */
	@Test
	public void testMakeCoffeePurchaseLessThanCost() {
		coffeeMaker.addRecipe(recipe3);
		assertEquals(50,coffeeMaker.makeCoffee(0,50));
	}

	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying equal than
	 * 	 * 		the coffee costs
	 * 	 Then we not get the coffee but don't get any money.
	 */
	@Test
	public void testMakeCoffeePurchaseEqualToCost() {
		coffeeMaker.addRecipe(recipe3);
		assertEquals(0, coffeeMaker.makeCoffee(0,100));
	}

	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe but the recipe is not
	 * 	 * 		enough
	 * 	 Then we not get the coffee but don't get any money.
	 */
	@Test
	public void testMakeCoffeeWithNotEnoughInventory() {
		coffeeMaker.addRecipe(recipe2);
		assertEquals(100, coffeeMaker.makeCoffee(0,100));

	}

	/**
	 * Given null recipe of coffee
	 * Then we get the money back
	 */
	@Test
	public void testMakeCoffeeWithNullRecipe() {
		int change = coffeeMaker.makeCoffee(0, 100); // point to null recipe
		assertEquals(100, change);
	}

	/**
	 * Test purchase beverage with mock
	 */
	@Test
	public void testPurchaseBeverageWithMock() {
		when(recipeBook.getRecipes()).thenReturn(recipes);
		assertEquals(50, coffeeMakerMock.makeCoffee(0,100));
		verify(recipeBook, atLeastOnce()).getRecipes();
	}

	/**
	 * Test purchase beverage with not enough money with mock
	 */
	@Test
	public void testPurchaseBeverageNotEnoughMoneyWithMock() {
		when(recipeBook.getRecipes()).thenReturn(recipes);
		assertEquals(20,coffeeMakerMock.makeCoffee(0,20));
		verify(recipeBook, atLeastOnce()).getRecipes();
	}

	/**
	 * Test purchase beverage with not enough inventory with mock
	 */
	@Test
	public void testPurchaseBeverageNotEnoughInventoryWithMock() {
		when(recipeBook.getRecipes()).thenReturn(recipes);
		assertEquals(1000,coffeeMakerMock.makeCoffee(1,1000));
		verify(recipeBook, atLeastOnce()).getRecipes();
	}

	/**
	 * Test purchase beverage with null recipe
	 */
	@Test
	public void testPurchaseBeverageWithNullRecipe() {
		Recipe[] nullRecipe = new Recipe[] {null};
		when(recipeBook.getRecipes()).thenReturn(nullRecipe);
		assertEquals(100,coffeeMakerMock.makeCoffee(0,100));
		assertEquals(50,coffeeMakerMock.makeCoffee(0,50));
		verify(recipeBook, times(2)).getRecipes();
	}

	/**
	 * Test number of get method called with mock
	 */
	@Test
	public void testNumberOfTimeGetMethodCalled() {
		recipes[0] = mock(Recipe.class);
		recipes[1] = mock(Recipe.class);
		when(recipeBook.getRecipes()).thenReturn(recipes);
		coffeeMakerMock.makeCoffee(0, 100);
		// selected recipe
		verify(recipes[0], atLeastOnce()).getAmtChocolate();
		verify(recipes[0], atLeastOnce()).getAmtCoffee();
		verify(recipes[0], atLeastOnce()).getAmtMilk();
		verify(recipes[0], atLeastOnce()).getAmtSugar();
		verify(recipes[0], atLeastOnce()).getPrice();
		// not selected recipe
		verify(recipes[1], never()).getAmtChocolate();
		verify(recipes[1], never()).getAmtCoffee();
		verify(recipes[1], never()).getAmtMilk();
		verify(recipes[1], never()).getAmtSugar();
		verify(recipes[1], never()).getPrice();
	}
}
