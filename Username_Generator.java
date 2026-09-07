import java.util.Scanner;

public class Username_Generator {

    public static void main(String[] args){
        Student Chihiro = new Student();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Integer number_input = 0;

        System.out.println("Hello user! Welcome to the email generator for superdupercool college!");
        //use the inbuilt error checker to check for errors for the loop without double outputting the error message
        while(!(Chihiro.emptyErrorTest(input))){
            //double check for input reset so as not to pass already wrong information
            input = "";
            System.out.print("Please enter your first name to get started: ");
            //user prompt
            input = (String)user_Input(false,scanner)[0];
            //attempts to set the first name - if theres an error the program will say so and then loop again
            Chihiro.setFirstName(input);
        }//use while loops to keep trying for good input until we get some
        //reset the value of the input
        input = "";
        while(!(Chihiro.emptyErrorTest(input))){
            input = "";
            System.out.print("Please enter your last name: ");
            input = (String)user_Input(false,scanner)[0];
            Chihiro.setLastName(input);          
        }
        //use a different datatype for the ID to make processing easier
        while(!(Chihiro.validID(number_input))){
            number_input = 0;
            System.out.print("Please enter your Student ID: ");
            number_input = (Integer)user_Input(true,scanner)[1];
            Chihiro.setStudentID(number_input);
        }
        input = "";
        while(!(Chihiro.validPassword(input))){
            input = "";
            System.out.print("Please enter the password you want to set for your account: ");
            input = (String)user_Input(false,scanner)[0];
            Chihiro.setPassword(input);
        }
        //output to the user that provides them their email based on the info provided
        System.out.println("All information has been entered successfully! Here is your email that is associated with your account! - " + Chihiro.getEmail());
    }
    //general input statement so as not to be forced to repeat the same logic
    public static Object[] user_Input(Boolean isNumber, Scanner scanner){
        //one for Strings and one for ints
        String processed_input;
        int integer_processed_input;
        try{
            //checks if the program is saying the input should be a number
            if(isNumber){
                integer_processed_input = scanner.nextInt();
                //nextInt leaves a newline character so we just clean that up so as not to cause bugs
                scanner.nextLine();
                processed_input = "";
                //return as a list so we have the ability to return both strings and ints
                return new Object[] {processed_input,integer_processed_input};
            }else{
                processed_input = scanner.nextLine();
                integer_processed_input = -3;
                return new Object[] {processed_input,integer_processed_input};
            }
        }catch(Exception e){
            scanner.next();
            return new Object[] {"",-1};
        }
    }
}


