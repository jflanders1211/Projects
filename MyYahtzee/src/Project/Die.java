package Project;

import java.util.Random;

public class Die {

    // Member variable for the value of the dice
     int dieValue;

    // Constructor for a Dice object, automatic value is set to 1
    public Die()
    {
        this.dieValue = 1;
    }

    // Method for rolling a Dice Object
    public int rollDie()
    {
        // Instance of class Random
        Random rand = new Random();

        // Setting the die's value between 1 and 6
        this.dieValue = rand.nextInt(6) + 1;
        // Returning the value rolled
        return this.dieValue;
    }

    // Method for returning the die's value
    int getDieValue()
    {
        return this.dieValue;
    }
}
