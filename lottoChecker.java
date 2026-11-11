import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class lottoChecker {
    static int enumerator = 0;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("lotto2.txt"));
        ArrayList<Integer> numbers = new ArrayList<>();

        while (scanner.hasNextInt()) {
            numbers.add(scanner.nextInt());
        }
        scanner.close();
        System.out.println(numbers);
        for(int i : numbers){
            enumerator++;
            if(!numbers.contains(enumerator)){
                System.out.println("The tickets don't cover all numbers");
                break;
            }
            if(enumerator == 99){
                System.out.println("The tickets cover all numbers");
            }
        }
    }
}

//Aayan Khan - Assignment 3
