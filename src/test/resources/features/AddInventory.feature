Feature: Add item to the inventory in the coffeemaker
  User can add item to the inventory. the inventory have start with 15 coffee,chocolate,sugar, and milk
  when added the item in the inventory will increase.


  Background:
  Given coffeemaker is ready to use

  Scenario: add coffee to the inventory
  When I add 10 coffee, 0 milk, 0 sugar, and 0 chocolate to our inventory
  Then inventory has 25 coffee, 15 milk, 15 sugar, and 15 chocolate to our inventory

  Scenario: add milk to the inventory
  When I add 0 coffee, 10 milk, 0 sugar, and 0 chocolate to our inventory
  Then inventory has 15 coffee, 25 milk, 15 sugar, and 15 chocolate to our inventory

  Scenario: add sugar to the inventory
  When I add 0 coffee, 0 milk, 10 sugar, and 0 chocolate to our inventory
  Then inventory has 15 coffee, 15 milk, 25 sugar, and 15 chocolate to our inventory

  Scenario: add chocolate to the inventory
  When I add 0 coffee, 0 milk, 0 sugar, and 10 chocolate to our inventory
  Then inventory has 15 coffee, 15 milk, 15 sugar, and 25 chocolate to our inventory