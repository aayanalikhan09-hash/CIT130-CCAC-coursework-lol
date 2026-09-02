import java.util.ArrayList;
import java.util.HashMap;

public class StudentEnrollmentProgram {
    public static void main(String[] args){

        Enrollment enroller = new Enrollment();
        HashMap<Integer,ArrayList<Integer>> databaseID = new HashMap<>();
        HashMap<Integer,String> names = new HashMap<>();
        HashMap<Integer,String> courses = new HashMap<>();

        //different test cases for different errors
/*
        Course bio101 = new Course(52,"Biology101",4.0,30);
        */
       /*
        Course calcBC = new Course(6792,"AP Calc BC",4.0, -5);
        */
       /*
        Course compSci = new Course(1437,"Computer Science",3.65,8);
        */
       /*
        Course physics = new Course(6792,"AP Physics",4.0,-5);
        */

        //creating courses and setting their parameters
        Course physEd = new Course(4927, "Physical Education", 2.0, 30);
        physEd.loadNameIntoDatabase(courses);

        Course cooking = new Course(2433, "Cooking", 2.0, 10);
        cooking.loadNameIntoDatabase(courses);

        Course algebra = new Course(6935,"Algebra",4.0,1);
        algebra.setCourseID(7000);
        algebra.loadNameIntoDatabase(courses);

        //creating students and their parameters
        Student jonnyBoi = new Student(9926,"Jonny","Boi","Biology");
        jonnyBoi.loadNameIntoDatabase(names);

        enroller.setCourseRequest(databaseID, jonnyBoi.getStudentID(), algebra, jonnyBoi);
        enroller.setCourseRequest(databaseID, jonnyBoi.getStudentID(), physEd, jonnyBoi);

        Student bobbyRay = new Student(3865, "Bobby", "Ray", "Chemistry");
        bobbyRay.loadNameIntoDatabase(names);

        //make a course request that will either pass or fail based on signups
        enroller.setCourseRequest(databaseID, bobbyRay.getStudentID(), algebra, bobbyRay);
        enroller.setCourseRequest(databaseID, bobbyRay.getStudentID(), cooking, bobbyRay);

        //print out all signups for each individual
        enroller.getAllStudentSignups(databaseID, names, courses);

        //show off error handling and stuff
        Course physics = new Course(6792,"AP Physics",4.0,-5);

    }

}
