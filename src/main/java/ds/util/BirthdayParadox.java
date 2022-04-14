package ds.util;

/**
 * Suppose that people enter a room one at a time. How people must enter until two share a birthday?
 * Counterintuitively, after 23 people enter the room, there is approximately a 50–50 chance that two share a birthday.
 * This phenomenon is known as the birthday problem or birthday paradox.
 *
 * https://www.youtube.com/watch?v=KtT_cgMzHx8
 *
 * Write a program Birthday.java that takes two integer command-line arguments n and trials
 * and performs the following experiment, trials times:
 *
 * Choose a birthday for the next person, uniformly at random between 0 and n−1.
 * Have that person enter the room.
 * If that person shares a birthday with someone else in the room, stop; otherwise repeat.
 * In each experiment, count the number of people that enter the room.
 * Print a table that summarizes the results (the count i, the number of times that exactly i people enter the room,
 * and the fraction of times that i or fewer people enter the room) for each possible value of i from 1
 * until the fraction reaches (or exceeds) 50%.
 */
public class BirthdayParadox {

    public void howManyEnterUntilTwoShareABirthday(int n, int trials) { // 365, 100

        boolean[] birthdayArray = null; //to keep track of which birthdays have been encountered so far in a single experiment
        int[] timesIPeopleEnteredTheRoom = new int[n + 2];//to keep track of the number of times that exactly i people entered the room across all experiments having same birthday

        for (int j = 0; j < trials; j++) { // 0-99

            birthdayArray = new boolean[n];//for the start of every new trial we want a fresh birthday array or reset array to false
            //choose a random birthday between 0 and n-1
            // j:0, j:1
            for (int i = 0; i < n; i++) { // 0 - 364
                int random = (int) (Math.random() * (n-1));// generating a random birthday // 0/30, 1/30;  0:13, 1:12, 2:12
                //System.out.println(random);
                if (!birthdayArray[random]) {
                    birthdayArray[random] = true;           // [,,,,,30:T,......];  [,,,12:T,13:T,,,,,]
                } else {
                    timesIPeopleEnteredTheRoom[i]++;        //  [0:1, 1:0 ,2:4, 3:2,,,,.....,, 354:1 ]
                    break;
                }
            }

        }
        fractionResult(trials, timesIPeopleEnteredTheRoom);
    }

    public void fractionResult(int trials, int[] timesIPeopleEnteredTheRoom) {
        int k =1;
        double fraction = 0.0;
        double totalSumOfVisits = 0.0;
        while (fraction < 0.5){
            totalSumOfVisits += timesIPeopleEnteredTheRoom[k];
            fraction = totalSumOfVisits/ trials;
            System.out.println(k + "   " +totalSumOfVisits + "   "+ fraction);
            k++;
        }
    }


    public static void main(String[] args) {
        BirthdayParadox object = new BirthdayParadox();
        object.howManyEnterUntilTwoShareABirthday(365,100);
    }
}