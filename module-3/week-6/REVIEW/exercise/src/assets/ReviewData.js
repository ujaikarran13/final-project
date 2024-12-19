export default {
    getReviewData() {
        return reviews.sort((a,b) => {
            return b.rating - a.rating;
        });
    }
}
const reviews = [
    {
        "recipeId": 1,
        "id": 1,
        "name": "Queen B",
        "location": "Sheep Meadow, NY",
        "body": "Tastes great, but I like to substitute the sugar with honey",
        "rating": 4
    },
    {
        "recipeId": 1,
        "id": 2,
        "name": "John D",
        "location": "Paris, TX",
        "body": "These cookies are amazing! I made a few changes since I love dark chocolate. I substituted dark chocolate chips, 53% cacao, for the suggested chips. I scooped 1/4 cup batter for each cookie, arranging the mounds 3 inches apart, with 6 cookies on each baking sheet. I did not flatten the cookies and baked them for approximately 13-15 minutes at 375. I let the cookies cool on the baking sheet to maintain these huge cookie's (5\" diameter) shape. They were a great hit with my dinner guests and family that same night and still wonderfully delicious the next day!",
        "rating": 3
    },
    {
        "recipeId": 1,
        "id": 3,
        "name": "Trogdor B",
        "location": "Peasantry, FL",
        "body": "These cookies burn every time",
        "rating": 2
    },
    {
        "recipeId": 1,
        "id": 4,
        "name": "Ashley F",
        "location": "Edison, NJ",
        "body": "If I can't have love I want these chocolate chip cookies",
        "rating": 5
    }
]