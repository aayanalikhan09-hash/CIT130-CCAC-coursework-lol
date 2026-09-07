
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Student {

    //use private variables for more security but with no constructor because we want to set each variable individually
    private String firstName;
    private String lastName;
    private Integer studentID;
    private String password;

    //all function names are fairly self-explanatory
    public boolean setFirstName(String fName){
        if(!emptyErrorTest(fName)){
            System.out.println("Error: Length of both first and last name must be greater than zero - setFirstNameError");
            return false;
        }else{
            this.firstName = fName;
            return true;
        }
    }
    public boolean setLastName(String lName){
        if(!emptyErrorTest(lName)){
            System.out.println("Error: Length of both first and last name must be greater than zero - setLastNameError");
            return false;
        }else{
            this.lastName = lName;
            return true;
        }
    }
    public boolean setStudentID(Integer ID){
        if(!validID(ID)){
            System.out.println("Error: All IDs must be a 9-digit positive integer - setStudentIDError");
            return false;
        }else{
            this.studentID = ID;
            return true;
        }
    }
    public boolean setPassword(String THEpassword){
        if(!validPassword(THEpassword)){
            System.out.println("Error: Password is either empty or not safe - setPasswordError");
            System.out.println("Please ensure the password provided is at least 8 characters long and contains at least 1 uppercase letter, lowercase letter, number, and special character");
            return false;
        }else{
            this.password = THEpassword;
            return true;
        }
    }
    public String getEmail(){
        return genUsername() + "@super.duper.cool.college.edu";
    }
    public boolean emptyErrorTest(String string){
        if (string.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    public boolean validID(Integer ID){
        if((ID.toString().length() != 9) || (ID < 0)){
            return false;
        }else{
            return true;
        }
    }
    public boolean validPassword(String THEpassword){
        if(!emptyErrorTest(THEpassword) || !(isThePasswordSafe(THEpassword))){
            return false;
        }else{
            return true;
        }
    }

    //made private as its not needed outside of this program - same applies for the below functions
    private boolean isThePasswordSafe(String THEpassword){
        if((THEpassword.toString().length() >= 8) && (amISpecial(THEpassword) && hasNum(THEpassword) && amILower(THEpassword) && amIUpper(THEpassword))){
            return true;
        }else{
            return false;
        }
    }
    private boolean amISpecial(String THEpassword){
        //uses special java techniques to check for standard keyboard punctuation
        return THEpassword.matches(".*\\p{Punct}.*");
    }
    private boolean hasNum(String THEpassword){
        Pattern pattern = Pattern.compile("\\d+"); 
        Matcher matcher = pattern.matcher(THEpassword.trim());

        //uses external classes to figure out if there any numbers in the class
        return matcher.find();
    }
    private boolean amIUpper(String THEpassword){
        //turns the password into a list of characters and checks if each character is an uppercase letter
        for(Character character: THEpassword.toCharArray()){
            if(Character.isUpperCase(character)){
                return true;
            }
        }
        return false;    
    }
    private boolean amILower(String THEpassword){
        //turns the password into a list of characters and checks if each character is a lowercase letter
        for(Character character: THEpassword.toCharArray()){
            if(Character.isLowerCase(character)){
                return true;
            }
        }
        return false;
    }
    private String genUsername(){
        //pieces together the bits and pieces of the first name, last name, and ID to create the users email
        String username = (firstName.toString().toLowerCase().charAt(0) + lastName.toString() + studentID.toString().substring(studentID.toString().length() - 3));
        username = username.toLowerCase();
        return username;
    }

}