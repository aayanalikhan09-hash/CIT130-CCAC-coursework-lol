public class Student {

    private int studentID;
    private String firstName;
    private String lastName;
    private String major;
    
    private int getStudentID(){
        return studentID;
    }
    private String getFullName(){
        return firstName + " " + lastName;
    }
    private String getMajor(){
        return major;
    }

}
