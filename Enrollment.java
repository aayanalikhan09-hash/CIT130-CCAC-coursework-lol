import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class Enrollment {

    private boolean setCourseRequest(HashMap<Integer,Integer> databaseID,int studentID,int courseID, Course course){
        if(getCapHitForCourse(course, databaseID)){
            databaseID.put(studentID,courseID);
        }else{
            return false;
        }
    }
    private HashMap getAllStudentSignups(Hashmap databaseID,Hashmap studentName, Hashmap courseName){
        Hashmap<String, String> studentNameAndCourses = new HashMap<>();

        for(int nameID in databaseID.keyset()){
            //put decoded student courses into a list to make it easy to add all the student's selected courses as a single value
            ArrayList<int> encodedStudentCourses = databaseID.get(nameID);
            ArrayList<String> decodeStudentCourses = new ArrayList<>;
            for(int courseID in encodedStudentCourses){
                decodeStudentCourses.add(courseName.get(courseID));
            }
            //iterates through name IDs in hashmap and uses that to figure out the plain text name of both the person and the course
            //then it saves that into a new hashmap and then returns that to the user
            studentNameAndCourse.put(studentName.get(nameID),decodeStudentCourses);
        }
        return studentNameAndCourses;
    }
    private boolean getCapHitForCourse(Course course, HashMap databaseID){
        if (Collections.frequency(databaseID.values(),course.courseID) > course.capacity){
            return false;
        }else{
            return true;
        }
    }
}
