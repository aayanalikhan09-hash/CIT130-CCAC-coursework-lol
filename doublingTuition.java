
import java.util.Scanner;

public class doublingTuition {
    public static void main(String[] args) {
        System.out.println("Enter current year tuition: ");
        Scanner io = new Scanner(System.in);
        String loans = io.nextLine();
        System.out.println("Rate  Number of Years");
        for(double i = 1; i <= 8; i++){
            //use rule of 72 for finding time for loan to double
            System.out.printf((i+2)*1.0 + "%%        %.2f%n", (72.0/(i+2)));
        }
        io.close();
    }

}

//Aayan Khan - Assignment 3
