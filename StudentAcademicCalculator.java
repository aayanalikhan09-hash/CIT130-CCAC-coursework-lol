import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class StudentAcademicCalculator {
    public static boolean notShutDown = true;
    public static int option;
    public static String user;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Student Academic Calculator");
        System.out.println("---------------------------");
        user = username_request(input);
         do {
            UI(input);

        } while(notShutDown);

        System.out.println("Thank you for using the Student Academic Calculator! Goodbye, " + user + "!");
        input.close();
    }

    /*
     * Prompts the user to enter five assignment scores.
     * Each score must be between 0 and 100.
     */
    public static double[] getScores(Scanner input) {
        double[] scores = new double[5];

        for (int i = 0; i < scores.length; i++) {
            scores[i] = getValidScore(input, i + 1);
        }

        return scores;
    }
    public static String username_request(Scanner scammer){
        System.out.print("Enter username: ");
        String username = scammer.nextLine();
        return username;
    }
    public static void UI(Scanner scammer){
        System.out.println("Hello " + user + "!\nHow can we help you today?\nEnter a digit that corresponds with the task you wanted to complete:\n1.Statistics on my provided scores\n2.Letter grade based on my provided scores\n3.Start a new user\n4.Quit the program");
        try{
            option = scammer.nextInt();
        } catch(Exception e){
            System.out.println("Invalid input! Please enter an integer from 1-4!");
            scammer.next();
        }        
        while(!((option < 5) && (option > 0))){
            System.out.println("Hello " + user + "!\nHow can we help you today?\nEnter a digit that corresponds with the task you wanted to complete:\n1.Statistics on my provided scores\n2.Letter grade based on my provided scores\n3.Start a new user\n4.Quit the program");
            try{
                option = scammer.nextInt();
            } catch(Exception e){
                System.out.println("Invalid input! Please enter an integer from 1-4!");
                scammer.next();
            }
        }
        if(option == 1){
            double[] myScores = getScores(scammer);
            System.out.println(user + "'s\n" + String.format("Average Score: %f\nHighest Score: %f\nLowest Score: %f", calculateAverage(myScores), findHighestScore(myScores),findLowestScore(myScores)));
        }else if(option == 2){
            double[] myScores = getScores(scammer);
            System.out.println(user + "'s\n" + String.format("Letter Grade: %c", determineLetterGrade(calculateAverage(myScores))));
        }else if(option == 3){
            scammer.nextLine();
            user = username_request(scammer);
            System.out.println(user);
        }else if(option == 4){
            notShutDown = false;
        }
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
        }
        return 'E';

    }

    /*
     * Displays the final results for one student.
     */

}