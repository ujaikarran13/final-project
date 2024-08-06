package com.techelevator;

public class Exercise06_YourJustDesserts {

    /*
     Desserted Island Diner believes a meal isn't a meal without dessert. All meals come with
     a free dessert.

     There are three categories of free desserts: "standard", "special", and "ginormous".

     The meal amount determines the dessert category: <= $10 (standard), <= $15 (special),
     > $15 (ginormous). If it is your birthday, an additional $5 is added when calculating
     the category to potentially bump you up to the next delicious category.

     Note: coinage is difficult to obtain on an island, so all money amounts are in whole dollars.

     yourJustDesserts(5, false) → "standard"
     yourJustDesserts(5, true) → "standard"
     yourJustDesserts(7, false) → "standard"
     yourJustDesserts(7, true) → "special"
     yourJustDesserts(10, false) → "standard"
     yourJustDesserts(10, true) → "special"
     yourJustDesserts(11, false) → "special"
     */
    public String yourJustDesserts(int mealAmount, boolean isBirthday) {
        int adjustedMealAmount = isBirthday ? mealAmount + 5 : mealAmount;

        String result = "standard";

        if (adjustedMealAmount > 10 && adjustedMealAmount <= 15) {
            result = "special";
        } else if (adjustedMealAmount > 15) {
            result = "ginormous";
        }

        return result;
    }
}
