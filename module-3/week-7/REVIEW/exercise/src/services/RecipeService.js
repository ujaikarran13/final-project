export default {

  getRecipes() {
    return recipes;
  },

  getRecipe(pos) {
    return recipes[pos];
  },

  searchRecipes(searchTerm) {
    return recipes.filter(recipe => recipe.name.toLowerCase().includes(searchTerm.toLowerCase()));
  }
}

const recipes = [
  {
    recipeId: 0,
    name: "Chocolate Chip Cookies",
    imageName:
      "https://assets.epicurious.com/photos/62cc51a66f0eae1af0de3a92/1:1/w_1280,c_limit/ChocolateChipCookies_RECIPE_070722_36748.jpg",
    instructionsUrl:
      "https://www.epicurious.com/recipes/food/views/chocolate-chip-cookies-108703",
    yield: 28.0,
    ingredients: [
      {
        text: "3 cups all-purpose flour",
        quantity: 3,
        measure: "cup",
      },
      {
        text: "1.5 tsps. baking soda",
        quantity: 1.5,
        measure: "teaspoon",
      },
      {
        text: "1.5 tsps. kosher salt",
        quantity: 1.5,
        measure: "teaspoon",
      },
      {
        text: "2 sticks softened unsalted butter",
        quantity: 2,
        measure: "count",
      },
      {
        text: "1.5 cups packed light brown sugar",
        quantity: 1.5,
        measure: "cup",
      },
      {
        text: "1 cup all-purpose sugar",
        quantity: 1,
        measure: "cup",
      },
      {
        text: "3 large eggs",
        quantity: 3,
        measure: "count",
      },
      {
        text: "1.5 tsps. vanilla",
        quantity: 1.5,
        measure: "teaspoon",
      },
      {
        text: "2.5 cups semi-sweet chocolate chips",
        quantity: 2.5,
        measure: "cup",
      },
    ],
  },
  {
    recipeId: 1,
    name: "Kung Pao Chicken",
    imageName:
      "https://assets.epicurious.com/photos/62d550372ce044d81614427d/1:1/w_960,c_limit/KungPaoChicken_RECIPE_071422_37114.jpg",
    instructionsUrl:
      "https://www.epicurious.com/recipes/food/views/kung-pao-chicken-51179600",
    yield: 8.0,
    ingredients: [
      {
        text: "1 tbsp. soy sauce",
        quantity: 1,
        measure: "tablespoon",
      },
      {
        text: "2 tsps. Chinese rice wine or dry sherry",
        quantity: 2,
        measure: "teaspoon",
      },
      {
        text: "1.5 tsps. cornstarch",
        quantity: 1.5,
        measure: "teaspoon",
      },
      {
        text: "1 lb. boneless, skinless, chicken breasts or thighs, cut into 1-inch cubes",
        quantity: 1,
        measure: "pound",
      },
      {
        text: "1 tbsp. Chinese black vinegar",
        quantity: 1,
        measure: "tablespoon",
      },
      {
        text: "1 tsp. soy sauce",
        quantity: 1,
        measure: "teaspoon",
      },
      {
        text: "1 tsp. hoisin sauce",
        quantity: 1,
        measure: "teaspoon",
      },
      {
        text: "1 tsp. sesame oil",
        quantity: 1,
        measure: "teaspoon",
      },
      {
        text: "2 tsps. sugar",
        quantity: 2,
        measure: "teaspoon",
      },
      {
        text: "1 tsp. cornstarch",
        quantity: 1,
        measure: "teaspoon",
      },
      {
        text: "0.5 tsp. ground Sichuan pepper",
        quantity: 1,
        measure: "teaspoon",
      },
      {
        text: "2 tbsps. peanut or vegetable oil",
        quantity: 2,
        measure: "tablespoon",
      },
      {
        text: "8 to 10 dried red chiles",
        quantity: 8,
        measure: "count",
      },
      {
        text: "3 scallions, white and green parts separated, thinly sliced",
        quantity: 3,
        measure: "count",
      },
      {
        text: "2 garlic cloves, minced",
        quantity: 2,
        measure: "count",
      },
      {
        text: "1 tsp. minced or grated fresh ginger",
        quantity: 1,
        measure: "teaspoon",
      },
      {
        text: "0.25 cup unsalted dry-roasted peanuts",
        quantity: 0.25,
        measure: "cup",
      },
    ],
  },
  {
    recipeId: 2,
    name: "Roasted Carrots",
    imageName:
      "https://assets.epicurious.com/photos/630cfb11241d518c2d31e496/1:1/w_1920,c_limit/RoastedCarrots_RECIPE_082522_38970.jpg",
    instructionsUrl:
      "https://www.epicurious.com/recipes/food/views/roasted-carrots-recipe",
    yield: 8.0,
    ingredients: [
      {
        text: "3lbs. small carrots, tops trimmed to 1 inch and carrots peeled",
        quantity: 3,
        measure: "pound",
      },
      {
        text: "2 tbsps. olive oil",
        quantity: 2,
        measure: "tablespoon",
      },
    ],
  },
];