import java.util.ArrayList;
import java.util.HashMap;

public class Student {

    //all variables are private for security purposes
    private int studentID;
    private String firstName;
    private String lastName;
    private String major;
    private ArrayList<Integer> personalCourseList = new ArrayList<>();

    public Student(int studentID, String firstName, String lastName, String major) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        
        //doing this to ensure nothing goes awry during the saving of stuff because it essentially acts a test without having to rewrite a bunch of code
        getStudentID();
        getFullName();
        getMajor();
    }
    
    //the reason I added the checking statements to the get methods as well is to ensure we dont end up returning nothing to the program and instead end the program so the user can fix whats wrong
    public int getStudentID(){
        //ensures id is within four digits and greater than 999 so as not to complicate things for this limited usecase by allowing the program to use zero as the first digit - certainly something to expand on in the future however
        if((studentID < 10000)&&(studentID > 999)){
            return studentID;
        }else{
            //error message so user knows what to fix - also quits program so as not to save half-finished data
            System.out.println("Error: ID must be within 1000-9999 range1 - getStudentIDError");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
            return -1;
        }    
    }
    public String getFullName(){
        //basic length of name detection to ensure there are no blank names
        if ((firstName.length() > 0) && (lastName.length() > 0)){
            return firstName + " " + lastName;
        }else{
            System.out.println("Error: Length of both first and last name must be greater than zero - getFullNameError");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
            return "";
        }
    }
    public String getMajor(){
        if (major.length() > 0){
            return major;
        }else{
            System.out.println("Error: Length of major must be greater than zero - getMajorError");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
            return "";
        }
    }
    public ArrayList<Integer> getListOfCourses(){
        //retrieve list of courses that specific student is enrolled in as a list of IDs
        return personalCourseList;
    }
    public void setStudentID(int ID){
        if((ID < 10000)&&(ID > 999)){
            studentID = ID;
        }else{
            System.out.println("Error: ID must be within 1000-9999 range - setStudentIDError");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
        }
    }
    public void setFullName(String fName, String lName){
        if ((fName.length() > 0) && (lName.length() > 0)){
            firstName = fName;
            lastName = lName;
        }else{
            System.out.println("Error: Length of both first and last name must be greater than zero - setFullNameError");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
        }

    }
    public void setMajor(String major_student){
        if (major_student.length() > 0){
            major = major_student;
        }else{
            System.out.println("Error: Length of major must be greater than zero - setMajor");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
        }
    }
    public void addToCourseList(Course course){
        //add course IDs to personal ID list
        personalCourseList.add(course.getCourseID());
    }
    public void loadNameIntoDatabase(HashMap<Integer, String> students){
        //update student in database so encoding and decoding functions properly
        students.put(studentID, getFullName());
    }

}
