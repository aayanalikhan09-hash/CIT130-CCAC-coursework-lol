import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class Enrollment {

    //uses the provided "databases" to check if someone is already registered or has hit the cap for a certain class
    public boolean setCourseRequest(HashMap<Integer,ArrayList<Integer>> databaseID,int studentID, Course course, Student student){
        if(getCapHitForCourse(course, databaseID) && !(Collections.frequency(student.getListOfCourses(),course.getCourseID()) > 0)){
            //adds course to student's internal list
            student.addToCourseList(course);
            databaseID.put(studentID,student.getListOfCourses());
            return true;
        }else{
            return false;
        }
    }

    //allows user to visualize who is signed up and for what courses
    public void getAllStudentSignups(HashMap<Integer, ArrayList<Integer>> databaseID,HashMap<Integer, String> studentName, HashMap<Integer,String> courseName){
        HashMap<String, ArrayList<String>> studentNameAndCourses = new HashMap<>();

        for(int nameID: databaseID.keySet()){
            //put decoded student courses into a list to make it easy to add all the student's selected courses as a single value
            ArrayList<Integer> encodedStudentCourses = new ArrayList<>(databaseID.get(nameID));
            ArrayList<String> decodeStudentCourses = new ArrayList<>();
            for(int courseID: encodedStudentCourses){
                decodeStudentCourses.add(courseName.get(courseID));
            }
            //iterates through name IDs in hashmap and uses that to figure out the plain text name of both the person and the course
            //then it saves that into a new hashmap and then returns that to the user
            studentNameAndCourses.put(studentName.get(nameID),decodeStudentCourses);
        }
        printRegistrationSummary(studentNameAndCourses);
    }

    //private function that isn't necessary to be public so i kept it private - checks if registration for a course is maxed out by seeing how many people are already registered for said course
    private boolean getCapHitForCourse(Course course, HashMap<Integer, ArrayList<Integer>> databaseID){
        ArrayList<Integer> allSignUpsInOne = new ArrayList<>();
        //iterate through sign up lists per person and iterate through those to add them to one big list to check for all signups for a specific course
        for(ArrayList<Integer> list: databaseID.values()){
            for(int value: list){
                allSignUpsInOne.add(value);
            }
        }
        if (Collections.frequency(allSignUpsInOne,course.getCourseID()) >= course.getCapacity()){
            return false;
        }else{
            return true;
        }
    }

    //another private function that isn't necessary to be public - prints out all the students and their signups by iterating through them
    private void printRegistrationSummary(HashMap<String, ArrayList<String>> namesAndCourses){
        String registrationConcat = "";
        for(String name:namesAndCourses.keySet()){
            for(String enlistedCourse:namesAndCourses.get(name)){
                registrationConcat = enlistedCourse + ", " + registrationConcat;
            }
            registrationConcat = registrationConcat.substring(0, registrationConcat.length() - 2);
            System.out.println(name + "'s Signups: " + registrationConcat);
            registrationConcat = "";
        }
    }

}
