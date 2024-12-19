# Week 6 Pair Exercise: Some Recipes, Inc

## Context

You've recently accepted a contract from a recipe-sharing startup, Some Recipes, Inc., to create a prototype of a detailed recipe view for a new version of its site.

## Application Overview

The detailed recipe view you're creating consists of 3 sections: recipe details, nutrition information, and reviews.

- The recipe details section contains the name and image for the recipe, its yield, calories per serving, the recipe's source and a link to it, and a list of ingredients that a user can toggle between Imperial and Metric units.

- The nutrition section lists the given nutrient data and toggles the amounts for the recipe's total or per serving.

- The reviews section lists the review data for the recipe and displays the average rating of all reviews.

### Wireframe
![Wireframe](/images/wireframe.png)

## Starting Code

Currently, only part of the recipe details section is complete, the recipe name and image display. It's up to you to complete the rest of this prototype.

Start the application, then look at the provided code in `App.vue` & `RecipeDetail.vue`. Your work includes completing the RecipeDetail component, then creating new components for the Nutrition and Reviews sections.

## Requirements

### Recipe Details

The `RecipeDetail` component has three `section` elements in the Vue template, one for each part shown in the wireframe: title, info, and ingredients. 

The title section is almost complete. You must add the ability for a user to hide or show the recipe's image when they click the `<a>` tag with the text: `(hide image)`. When the image is hidden, alter text displayed to say `(show image)`.

When completed, the info section displays: 
- Yield
- Calories per serving
- Name of its source
- A link to take a user to the recipe's source page
    - Clicking the link opens the page in a new tab

When completed, the ingredients section lists the ingredient data from the recipe. This section also includes buttons to toggle the display between metric and imperial measurements. 

Note: The `recipe` data property already contains the JSON for the recipe details. It comes from the `/assets/RecipeData.js` file. 


### Nutrition

Create a new component to list the recipe's nutrition information. 

Add this new component to `App.vue`, placing it in the `section` with `id="nutrition"`.

This component requires data from both the `RecipeData.js` and `NutritionData.js` files in assets. You can import these into your component using the same approach as the RecipeDetail component:
```javascript
import recipeData from "../assets/RecipeData";

export default {
    data() {
        return {
            recipe: recipeData.getRecipeData(),
        }
    }
}
```

List the nutrition information including the label, amount, and unit for each nutrient provided. Show amounts as a whole number by removing the decimal amount.

> Note: The nutrition data provided is for the total recipe amount. You must divide the amount by the recipe's yield (found in `RecipeData.js`) to get the amount per serving.

### Reviews

Create a new component to list the recipe's reviews. 

Add this new component to `App.vue`, placing it in the `section` with `id="rating"`.

This component requires data from the `ReviewData.js` file in assets. 

The Reviews section displays the average review rating after the heading, as shown in the wireframe. Then list each review provided, including the rating, the reviewer's name, location, and the review text for each review.

### Styling Notes

Use the `title-btn` class on links and buttons to pick up the styles provided in `App.vue`.

It's fine to display each review's rating as a number. However, if you want more of a challenge, try representing the rating using icons. There is a star icon in the `assets` folder. Repeat the icon (using a `v-for`) to show how many "stars" the reviewer gave the recipe.
