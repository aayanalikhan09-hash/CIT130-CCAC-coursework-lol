import java.util.Scanner;

public class LetterGradeCounter {

    public static void main(String[] args) {
        Scanner io = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int students = io.nextInt();
        System.out.print("Enter the scores: ");
        io.nextLine();
        String scores = io.nextLine();
        String[] scoresList = scores.split(" ");
        Integer[] scoresListIntegers = new Integer[students];
        System.out.println(scores);
        for(int i = 0; i < scoresListIntegers.length; i++){
            scoresListIntegers[i] = Integer.parseInt(scoresList[i]);
            System.out.println("Student " + i + " score is " + scoresListIntegers[i] + " and grade is " + determineLetterGrade(scoresListIntegers[i]));
        }
        io.close();
        
    }

    public static char determineLetterGrade(int average) {
        if (average >= 90){
            return 'A';
        }else if (average >= 70){
            return 'B';
        }else if (average >= 60){
            return 'C';
        }else if (average >= 50){
            return 'D';
        }else{
            return 'F';
        }

    }

    

}

//Aayan Khan - Assignment 3
