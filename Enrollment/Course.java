import java.util.HashMap;

public class Course {

    //all variables are private for security purposes
    private int courseID;
    private String title;
    private double credits;
    private int capacity;

    public Course(int courseID, String title, double credits, int capacity){
        this.courseID = courseID;
        this.title = title;
        this.credits = credits;
        this.capacity = capacity;
        
        //doing this to ensure nothing goes awry during the saving of stuff because it essentially acts a test without having to rewrite a bunch of code
        getCourseID();
        getTitle();
        getCredits();
        getCapacity();
    }
    
    //the reason I added the checking statements to the get methods as well is to ensure we dont end up returning nothing to the program and instead end the program so the user can fix whats wrong
    public int getCourseID(){
        //ensures id is within four digits and greater than 999 so as not to complicate things for this limited usecase by allowing the program to use zero as the first digit - certainly something to expand on in the future however
        if((courseID < 10000)&&(courseID > 999)){
            return courseID;
        }else{
            //error message so user knows what to fix - also quits program so as not to save half-finished data
            System.out.println("Error: ID must be within 1000-9999 range - getCourseIDError");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
            return 0;
        }    
    }
    public String getTitle(){
        //basic length of name detection to ensure there are no blank names
        if (title.length() > 0){
            return title;
        }else{
            System.out.println("Error: Course title must be longer than zero characters - getTitleError");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
            return "";
        }
    }
    public double getCredits(){
        //added modulus function to ensure credit amounts mirror those in real life and added credit limit of 4 per course
        if((credits%0.25 == 0) && ((credits <= 4.0) && (credits > 0))){
            return credits;
        }else{
            System.out.println("Error: Course credits value is a non-multiple of 0.25 and/or is not within the range of 0 and 4 - getCreditsError");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
            return -1.0f;
        }
    }
    public int getCapacity(){
        if (capacity > 0){
            return capacity;
        }else{
            System.out.println("Error: Capacity must be greater than zero - getCapacityError");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
            return -1;
        }    
    }
    public void setCourseID(int ID){
        if((ID < 10000)&&(ID > 999)){
            courseID = ID;
        }else{
            System.out.println("Error: ID must be within 1000-9999 range4 - setCourseIDError");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
        }
    }
    public void setTitle(String title_course){
        if (title_course.length() > 0){
            title = title_course;
        }else{
            System.out.println("Error: Course title must be longer than zero characters - setTitleError");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
        }
    }
    public void setCredits(double credits_num){
        if((credits_num%0.25 == 0) && ((credits_num <= 4.0) && (credits_num > 0))){
            credits = credits_num;
        }else{
            System.out.println("Error: Course credits value is a non-multiple of 0.25 and/or is not within the range of 0 and 4 - setCreditsError");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
        }

    }
    public void setCapacity(int cap){
        if (cap > 0){
            capacity = cap;
        }else{
            System.out.println("Error: Capacity must be greater than zero - setCapacityError");
            System.out.println("Fix all errors before continuing");
            System.exit(0);
        }    
    }
    public void loadNameIntoDatabase(HashMap<Integer, String> courses){
        //update course in database so encoding and decoding functions properly
        courses.put(courseID, getTitle());
    }
}
