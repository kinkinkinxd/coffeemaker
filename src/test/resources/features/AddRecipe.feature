Feature: Add recipe to the recipe book in the coffeemaker
  User can add recipe to the recipe book. recipe book can't have more than three recipes and
  each recipe must be unique

  Background:
    Given coffeemaker is ready to use

  Scenario: add one recipe to the recipe book
  When I add 1 recipe to the recipe book
  Then recipe book has 1 recipes

  Scenario: add two recipe to the recipe book
  When I add 2 recipe to the recipe book
  Then recipe book has 2 recipes

  Scenario: add three recipe to the recipe book
  When I add 3 recipe to the recipe book
  Then recipe book has 3 recipes

  Scenario: add four recipe to the recipe book
  When I add 4 recipe to the recipe book
  Then recipe book has 3 recipes