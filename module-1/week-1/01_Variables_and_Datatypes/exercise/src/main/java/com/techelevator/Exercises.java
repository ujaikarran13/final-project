package com.techelevator;

import org.checkerframework.checker.units.qual.Luminance;

public class Exercises {

    /**
     * For the purposes of this exercise, the following naming rules are tested:
     *
     * Variable names:
     *  - must start with a lowercase character a-z.
     *  - underscore ('_') characters are not allowed.
     *  - dollar sign ('$') characters are not allowed.
     *  - must be at least two characters in length.
     *  - You are encouraged to create descriptive names and are required to camel case them as appropriate.
     *
     * Constant names:
     *  - must start with an uppercase character A-Z.
     *  - dollar sign ('$') characters are not allowed.
     *  - must be at least two characters in length.
     *  - You are encouraged to create descriptive names and are required to upper case them as appropriate.
     *
     * Due to practical limitations, camel case and pascal case are not tested other than checking the first
     * character of the name is lower case for variables and upper case for constants. Your instructor will
     * manually review your solution checking for the correct casing.
     */

	public static void main(String[] args) {

        /* Exercise 1
        1. 4 birds are sitting on a branch. 1 flies away. How many birds are left on
        the branch?
        */
		// ### EXAMPLE:
		int birdsOnABranch = 4;
		int birdsThatFlyAway = 1;
		int birdsRemaining = birdsOnABranch - birdsThatFlyAway;

        /* Exercise 2
        2. There are 6 birds and 3 nests. How many more birds are there than
        nests?
        */
		// ### EXAMPLE:
		int numberOfBirds = 6;
		int numberOfNests = 3;
		int numberOfExtraBirds = numberOfBirds - numberOfNests;

        /* Exercise 3
        3. 3 raccoons are playing in the woods. 2 go home to eat dinner. How
        many raccoons are left in the woods?
        */
		int raccoonsPlaying =  3;
		int raccoonsEat = 1;
		int racoonsRemaining = raccoonsPlaying - raccoonsEat;

        /* Exercise 4
        4. There are 5 flowers and 3 bees. How many less bees than flowers?
        */
		int numberOfFlowers = 5;
		int numberOfBees = 3;
		int beesWithoutFlowers = numberOfFlowers - numberOfBees;

        /* Exercise 5
        5. 1 lonely pigeon was eating breadcrumbs. Another pigeon came to eat
        breadcrumbs, too. How many pigeons are eating breadcrumbs now?
        */
		int numberOfLonelyPigeons = 1;
		int additionalPigeons = 1;
		int totalPigeonsEatingBreadcrumbs = numberOfLonelyPigeons - additionalPigeons;

        /* Exercise 6
        6. 3 owls were sitting on the fence. 2 more owls joined them. How many
        owls are on the fence now?
        */
		int sittingOwls = 3;
		int additionsOwls = 2;
		int totalOwlsOnFence = sittingOwls + additionsOwls;

        /* Exercise 7
        7. 2 beavers were working on their home. 1 went for a swim. How many
        beavers are still working on their home?
        */
		int buildingHomeBeavers = 2;
		int swimmingBeavers = 1;
		int beaversLeftWorking = buildingHomeBeavers - swimmingBeavers;

        /* Exercise 8
        8. 2 toucans are sitting on a tree limb. 1 more toucan joins them. How
        many toucans in all?
        */
		int toucansOnATree = 2;
		int additionalToucans = 1;
		int totalToucans = toucansOnATree + additionalToucans;

        /* Exercise 9
        9. There are 4 squirrels in a tree with 2 nuts. How many more squirrels
        are there than nuts?
        */
		int squirrelsWithNuts = 4;
		int nutsInTree = 2;
		int excessSquirrels = squirrelsWithNuts - nutsInTree;

        /* Exercise 10
        10. Mrs. Hilt found a quarter, 1 dime, and 2 nickels. How much money did
        she find?
        */
		double quarter = 0.25;
		double dime = 0.10;
		double nickel = 0.05;
		double totalMoneyFound = quarter + dime + nickel;

        /* Exercise 11
        11. Mrs. Hilt's favorite first grade classes are baking muffins. Mrs. Brier's
        class bakes 18 muffins, Mrs. MacAdams's class bakes 20 muffins, and
        Mrs. Flannery's class bakes 17 muffins. How many muffins does first
        grade bake in all?
        */
		int mrsBrierMuffins = 18;
		int mrsMacAdamsMuffins = 20;
		int mrsFlanneryMuffins = 17;
		int totalFirstGradeMuffins = mrsBrierMuffins + mrsMacAdamsMuffins + mrsFlanneryMuffins;

        /* Exercise 12
        12. Mrs. Hilt bought a yoyo for 24 cents and a whistle for 14 cents. How
        much did she spend in all for the two toys?
        */
		double mrsHiltYoyo = 0.24;
		double mrsHiltWhistle = 0.14;
		double totalCostOfToys = mrsHiltYoyo + mrsHiltWhistle;


        /* Exercise 13
        13. Mrs. Hilt made 5 Rice Krispies Treats. She used 8 large marshmallows
        and 10 mini marshmallows.How many marshmallows did she use
        altogether?
        */
		int largeMarshmallows = 8;
		int miniMarshmallows = 10;
		int totalMarshmallows = largeMarshmallows + miniMarshmallows;

        /* Exercise 14
        14. At Mrs. Hilt's house, there was 29 inches of snow, and Brecknock
        Elementary School received 17 inches of snow. How much more snow
        did Mrs. Hilt's house have?
        */
		int mrsHiltInchesofSnow = 29;
		int elementaryInchesofSnow = 17;
		int howManyMoreSnowForMrsHilt = mrsHiltInchesofSnow - elementaryInchesofSnow;

        /* Exercise 15
        15. Mrs. Hilt has $10. She spends $3 on a toy truck and $2.50 on a pencil
        case. How much money does she have left?
        */
		double mrsHiltTotalMoney = 10.00;
		double toyTruckPrice = 3.00;
		double pencilCasePrice = 2.50;
		double moneyLeft = mrsHiltTotalMoney - toyTruckPrice - pencilCasePrice;

        /* Exercise 16
        16. Josh had 16 marbles in his collection. He lost 7 marbles. How many
        marbles does he have now?
        */
		int totalMarbles = 16;
		int marblesLost = 7;
		int marbleLeft = totalMarbles - marblesLost;

        /* Exercise 17
        17. Megan has 19 seashells. How many more seashells does she need to
        find to have 25 seashells in her collection?
        */
		int seaShellsNeeded = 25;
		int seaShellsFound = 19;
		int seaShellsLeftToFind = seaShellsNeeded - seaShellsFound;

        /* Exercise 18
        18. Brad has 17 balloons. 8 balloons are red and the rest are green. How
        many green balloons does Brad have?
        */
		int totalBalloons = 17;
		int redBalloons = 8;
		int greenBalloons = totalBalloons - redBalloons;

        /* Exercise 19
        19. There are 38 books on the shelf. Marta put 10 more books on the shelf.
        How many books are on the shelf now?
        */
		int booksOnShelf = 38;
		int numberOfBooksAdded = 10;
		int totalNumberOfBooks = booksOnShelf + numberOfBooksAdded;

        /* Exercise 20
        20. A bee has 6 legs. How many legs do 8 bees have?
        */
		int legsPerBee = 6;
		int numberBees = 8;
		int totalNumberOfLegs = legsPerBee * numberBees;

        /* Exercise 21
        21. Mrs. Hilt bought an ice cream cone for 99 cents. How much would 2 ice
        cream cones cost?
        */
		double costOfAnIceCreamCone = 0.99;
		int iceCreamCones = 2;
		double totalCostOfCOnes = (double)iceCreamCones * costOfAnIceCreamCone;

        /* Exercise 22
        22. Mrs. Hilt wants to make a border around her garden. She needs 125
        rocks to complete the border. She has 64 rocks. How many more rocks
        does she need to complete the border?
        */
		int rocksNeeded = 125;
		int rocksOnHand = 64;
		int rocksLeftToGet= rocksNeeded - rocksOnHand;

        /* Exercise 23
        23. Mrs. Hilt had 38 marbles. She lost 15 of them. How many marbles does
        she have left?
        */
		int marblesTotal = 38;
		int lostMarble = 15;
		int marblesLeft= marblesTotal - lostMarble;


        /* Exercise 24
        24. Mrs. Hilt and her sister drove to a concert 78 miles away. They drove 32
        miles and then stopped for gas. How many miles did they have left to drive?
        */
		int totalMiles = 78;
		int milesDriven = 32;
		int milesLeft = totalMiles - milesDriven;

        /* Exercise 25
        25. Mrs. Hilt spent 1 hour and 30 minutes shoveling snow on Saturday
        morning and 45 minutes shoveling snow on Saturday afternoon. How
        much total time (in minutes) did she spend shoveling snow?
        */
		int minutesShovelingSnowAM = 90;
		int minutesShovelingSnowPM = 45;
		int totalTimeShoveling = minutesShovelingSnowAM + minutesShovelingSnowPM;

        /* Exercise 26
        26. Mrs. Hilt bought 6 hot dogs. Each hot dog cost 50 cents. How much
        money did she pay for all of the hot dogs?
        */
		int totalHotDogs = 6;
		double costPerHotDog = 0.50;
		double costOfTotalHotDogs = (double)totalHotDogs * costPerHotDog;

        /* Exercise 27
        27. Mrs. Hilt has 50 cents. A pencil costs 7 cents. How many pencils can
        she buy with the money she has?
        */
		double totalMoney = 0.50;
		double costOfPencil = 0.07;
		double numberOfPencils = totalMoney / costOfPencil;

        /* Exercise 28
        28. Mrs. Hilt saw 33 butterflies. Some of the butterflies were red and others
        were orange. If 20 of the butterflies were orange, how many of them
        were red?
        */
		int totalButterflies = 33;
		int orangeButterflies = 20;
		int redButterfly = totalButterflies - orangeButterflies;

        /* Exercise 29
        29. Kate gave the clerk $1.00. Her candy cost 54 cents. How much change
        should Kate get back?
        */
		double moneyToClerk = 1.00;
		double costOfCandy = 0.54;
		double changeReceived = moneyToClerk - costOfCandy;

        /* Exercise 30
        30. Mark has 13 trees in his backyard. If he plants 12 more, how many trees
        will he have?
        */
		int treesInBackyard = 13;
		int treesToPlant = 12;
		int totalTreeInYard = treesInBackyard + treesToPlant;

        /* Exercise 31
        31. Joy will see her grandma in two days. How many hours until she sees
        her?
        */
		int hoursPerDay = 24;
		int daysUntilMeetGrandma = 2;
		int hoursToSeeGrandma = hoursPerDay * daysUntilMeetGrandma;

        /* Exercise 32
        32. Kim has 4 cousins. She wants to give each one 5 pieces of gum. How
        much gum will she need?
        */
		int cousinsTotal = 4;
		int piecesOfGum = 5;
		int gumNeeded = cousinsTotal * piecesOfGum;

        /* Exercise 33
        33. Dan has $3.00. He bought a candy bar for $1.00. How much money is
        left?
        */
		double totalCash = 3.00;
		double candyBar = 1.00;
		double changeLeft = totalCash - candyBar;

        /* Exercise 34
        34. 5 boats are in the lake. Each boat has 3 people. How many people are
        on boats in the lake?
        */
		int boatsInLake = 5;
		int peoplePerBoat = 3;
		int totalPeopleOnBoats = boatsInLake * peoplePerBoat;


        /* Exercise 35
        35. Ellen had 380 legos, but she lost 57 of them. How many legos does she
        have now?
        */
		int totalLegos = 380;
		int legosLost = 57;
		int legosLeft = totalLegos - legosLost;

        /* Exercise 36
        36. Arthur baked 35 muffins. How many more muffins does Arthur have to
        bake to have 83 muffins?
        */
		int muffinsBaked = 35;
		int TotalMuffinsNeeded = 83;
		int muffinsLeft = TotalMuffinsNeeded - muffinsBaked;

        /* Exercise 37
        37. Willy has 1400 crayons. Lucy has 290 crayons. How many more
        crayons does Willy have then Lucy?
        */
		int WillysTotalCrayons = 1400;
		int LucysTotalCrayons = 290;
		int HowManyCrayonsDoesWillyHave = WillysTotalCrayons - LucysTotalCrayons;

        /* Exercise 38
        38. There are 10 stickers on a page. If you have 22 pages of stickers, how
        many stickers do you have?
        */
		int stickersOnPage = 10;
		int numberOfPages = 22;
		int totalNumberOfStickers = stickersOnPage * numberOfPages;

        /* Exercise 39
        39. There are 100 cupcakes for 8 children to share. How much will each
        person get if they share the cupcakes equally?
        */
		int numberOfChildren = 8;
		int numberOfCupcakes = 100;
		double cupcakePerPerson = (double) numberOfCupcakes / (double) numberOfChildren;

        /* Exercise 40
        40. She made 47 gingerbread cookies which she will distribute equally in
        tiny glass jars. If each jar is to contain six cookies, how many
        cookies will not be placed in a jar?
        */
		int numberOfCookies = 47;
		int cookiesPerJar = 6;
		int cookiesNotPlacedInJar = numberOfCookies % cookiesPerJar;


        /* Exercise 41
        41. She also prepared 59 croissants which she plans to give to her 8
        neighbors. If each neighbor received an equal number of croissants,
        how many will be left with Marian?
        */
		int croissantsTotal = 59;
		int neighborsTotal = 8;
		int croissantsRemaining = croissantsTotal % neighborsTotal;

        /* Exercise 42
        42. Marian also baked oatmeal cookies for her classmates. If she can
        place 12 cookies on a tray at a time, how many trays will she need to
        prepare 276 oatmeal cookies at a time?
        */
		int totalCookies = 276;
		int cookiesPerTray = 12;
		int traysNeeded = totalCookies / cookiesPerTray;

        /* Exercise 43
        43. Marian’s friends were coming over that afternoon so she made 480
        bite-sized pretzels. If one serving is equal to 12 pretzels, how many
        servings of bite-sized pretzels was Marian able to prepare?
        */
		int totalPretzels = 480;
		int oneServing = 12;
		int totalServings = totalPretzels / oneServing;

        /* Exercise 44
        44. Lastly, she baked 53 lemon cupcakes for the children living in the city
        orphanage. If two lemon cupcakes were left at home, how many
        boxes with 3 lemon cupcakes each were given away?
        */
		int totalCupcakes = 53;
		int cupcakesLefthome= 2;
		int cupcakesPerBox = 3;
		int numberOfBoxes= totalCupcakes - cupcakesLefthome / cupcakesPerBox;

        /* Exercise 45
        45. Susie's mom prepared 74 carrot sticks for breakfast. If the carrots
        were served equally to 12 people, how many carrot sticks were left
        uneaten?
        */
		int totalCarrots = 74;
		int totalPeople = 12;
		int remainingCarrots = totalCarrots % totalPeople;

        /* Exercise 46
        46. Susie and her sister gathered all 98 of their teddy bears and placed
        them on the shelves in their bedroom. If every shelf can carry a
        maximum of 7 teddy bears, how many shelves will be filled?
        */
		int totalTeddyBears = 98;
		int bearPerShelf = 7;
		int shelvesFilled = totalTeddyBears / bearPerShelf;

        /* Exercise 47
        47. Susie’s mother collected all family pictures and wanted to place all of
        them in an album. If an album can contain 20 pictures, how many
        albums will she need if there are 480 pictures?
        */
		int totalPictures = 480;
		int picturesPerAlbum = 20;
		int albumsNeeded = totalPictures / picturesPerAlbum;

        /* Exercise 48
        48. Joe, Susie’s brother, collected all 94 trading cards scattered in his
        room and placed them in boxes. If a full box can hold a maximum of 8
        cards, how many boxes were filled and how many cards are there in
        the unfilled box?
        */
		int totalCards = 94;
		int cardsPerBox = 8;
		int totalBoxesFilled = totalCards / cardsPerBox;
		int cardsRemaining = totalCards % cardsPerBox;

        /* Exercise 49
        49. The Milky Way galaxy contains 300 billion stars. The Andromeda galaxy
        contains 1 trillion stars. How many stars do the two galaxies contain combined?
        */
		long milkyWayStars = 300000000000L;
		long andromedaGalaxyStars = 1000000000000L;
		long totalStars = milkyWayStars * andromedaGalaxyStars;


        /* Exercise 50
        50. Cristina baked 17 croissants. If she planned to serve this equally to
        her seven guests, how many will each have?
        */
		int totalCroissants = 17;
		int totalGuests = 7;
		int croissantsPerGuest = totalCroissants / totalGuests;

	    /* Exercise 51
	    51. Bill and Jill are house painters. Bill can paint a standard room in 2.15 hours, while Jill averages
	    1.90 hours. How long will it take the two painters working together to paint 5 standard rooms?
	    Hint: Calculate the rate at which each painter can complete a room (rooms / hour), combine those rates, 
	    and then divide the total number of rooms to be painted by the combined rate.
	    */
     	double billPaintRatePerHour = 2.15;
		 double jillPaintRatePerHour = 1.90;
		 double totalRooms = 5;
		 double totalTime = (billPaintRatePerHour + jillPaintRatePerHour) / totalRooms;




	/* Exercise 52
	    52. Create and assign variables to hold a first name, last name, and middle initial. Using concatenation,
		build an additional variable to hold the full name in the order of last name, first name, middle initial. The
		last and first names should be separated by a comma followed by a space, and the middle initial must end
		with a period. Use "Grace", "Hopper, and "B" for the first name, last name, and middle initial.
		Example: "John", "Smith, "D" —> "Smith, John D."
	    */
		String firstName = "Grace";
		String lastName = "Hopper";
		String middleInitial = "B";
		System.out.println(lastName+"," +firstName + middleInitial+".");

	    /* Exercise 53
	    53. The distance between New York and Chicago is 800 miles, and the train has already travelled 537 miles.
	    What percentage of the trip as a whole number has been completed?
	    */
		int totalMilesToChicago = 800;
		int milesTraveled = 537;
		int percentageCOmplated = milesTraveled / totalMilesToChicago;
	}

}
