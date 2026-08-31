import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class StudentAcademicCalculator {
    public static boolean notShutDown = true;
    public static int option;
    public static String user;
    public static double myScores[] = new double[5];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Student Academic Calculator");
        System.out.println("---------------------------");
        user = username_request(input);
        myScores = getScores(input);
         do {
            UI(input);

        } while(notShutDown);

        //exit message
        System.out.println("Thank you for using the Student Academic Calculator! Goodbye, " + user + "!");
        //close input stream to save resources
        input.close();
    }













        public static void UI(Scanner input){
            //menu for user to choose from
        System.out.println("Hello " + user + "!\nHow can we help you today?\nEnter a digit that corresponds with the task you wanted to complete:\n1.Statistics on my provided scores\n2.Letter grade based on my provided scores\n3.Start a new user\n4.Quit the program");
        try{
            //try to get a number - if failed, warn user and have them try again until you get a VALID number
            option = input.nextInt();
        } catch(Exception e){
            System.out.println("Invalid input! Please enter an integer from 1-4!");
            input.next();
        }        
        while(!((option < 5) && (option > 0))){
            System.out.println("Hello " + user + "!\nHow can we help you today?\nEnter a digit that corresponds with the task you wanted to complete:\n1.Statistics on my provided scores\n2.Letter grade based on my provided scores\n3.Start a new user\n4.Quit the program");
            try{
                option = input.nextInt();
            } catch(Exception e){
                System.out.println("Invalid input! Please enter an integer from 1-4!");
                input.next();
            }
        }
        if(option == 1){
            //Average,highest, and lowest scores for user
            System.out.println(user + "'s\n" + String.format("Average Score: %f\nHighest Score: %f\nLowest Score: %f", calculateAverage(myScores), findHighestScore(myScores),findLowestScore(myScores)));
        }else if(option == 2){
            //Letter grade for user
            System.out.println(user + "'s\n" + String.format("Letter Grade: %c", determineLetterGrade(calculateAverage(myScores))));
        }else if(option == 3){
            //create new user and request new scores - future potential expansion - create a database of all users so that old users can reaccess old data
            input.nextLine();
            user = username_request(input);
            myScores = getScores(input);
        }else if(option == 4){
            //killswitch for code
            notShutDown = false;
        }
    }







    /*
     * Prompts the user to enter five assignment scores.
     * Each score must be between 0 and 100.
     */
    public static double[] getScores(Scanner input) {
        double[] scores = new double[5];

        System.out.println("Before we continue, please enter the five scores you would like to process.");
        for (int i = 0; i < scores.length; i++) {
            scores[i] = getValidScore(input, i + 1);
        }

        return scores;
    }
    public static String username_request(Scanner input){
        System.out.print("Enter username: ");
        String username = input.nextLine();
        return username;
    }







    /*
     * Validates that each score is within the acceptable range.
     */
    public static double getValidScore(Scanner input, int assignmentNumber) {
        double score;
        boolean stepComplete = false;
        while (!stepComplete) {
            try{
                System.out.print("Enter score for assignment " + assignmentNumber + " between 0 and 100: ");
                score = input.nextDouble();
                if (score >= 0 && score <= 100) {
                    stepComplete = true;
                    return score;
                } else {
                    System.out.println("Invalid input! Please enter a double from 0-100!");
                    input.nextLine();

                }   
                }
            catch(Exception e){
                System.out.println("Invalid input! Please enter a double from 0-100!");
                input.nextLine();
            }
        }
        return -1.0;
    }











    /*
     * Calculates the average of all assignment scores.
     */
    public static double calculateAverage(double[] scores) {
        double total = 0;

        for (double score : scores) {
            total += score;
        }

        return total / scores.length;
    }








    /*
     * Finds the highest score in the array.
     */
    public static double findHighestScore(double[] scores) {
        double highest = scores[0];

        for (double score : scores) {
            if (score > highest) {
                highest = score;
            }
        }

        return highest;
    }










    /*
     * Finds the lowest score in the array.
     */
    public static double findLowestScore(double[] scores) {
        double lowest = scores[0];

        for (double score : scores) {
            if (score < lowest) {
                lowest = score;
            }
        }

        return lowest;
    }









    /*
     * Determines the letter grade using a standard grading scale.
     */
    public static char determineLetterGrade(double average) {
        if (average >= 90){
            return 'A';
        }else if (average >= 80){
            return 'B';
        }else if (average >= 70){
            return 'C';
        }else if (average >= 60){
            return 'D';
        }else{
            return 'F';
        }

    }

}