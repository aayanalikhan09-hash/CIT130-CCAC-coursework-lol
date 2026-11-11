import java.util.Scanner;

public class Main {
    
    public static void main(String [] args){

        Scanner io = new Scanner(System.in);

        //io for the program
        System.out.print("Enter yearly interest rate, for example 8.25: ");
        double interest = io.nextDouble();
        System.out.print("Enter number of years as an integer, for example 5: ");
        int years = io.nextInt();
        System.out.print("Enter loan amount, for example 120000.95: ");
        double loan = io.nextDouble();

        //return payment stats
        System.out.printf("The monthly payment is $%.2f%n", monthlyPayment.getMonthlyPayment(interest, years, loan));
        System.out.printf("The total payment is $%.2f%n", monthlyPayment.getTotalPayment(interest, years, loan));
        io.close();
    }
}

//Aayan Khan - Assignment 3
