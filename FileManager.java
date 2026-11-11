import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FileManager {
    private static int hierarchy = 4;
    private static ArrayList<String> permissions = new ArrayList<>(List.of("Read","Write","Move","Delete"));
    private ArrayList<String> localPermissions = new ArrayList<>();
    Scanner io = new Scanner(System.in);
    //probably going to add ui elements to constructor later
    public FileManager(){
        updatePerms();
    }
    public ArrayList<String> getPerms(){
    //returns object's hierarchy compared to all other objects
        return localPermissions;
    }
    private void updatePerms(){
        //update permissions based on private hierarchy
        //will be added to every subclass type 
        if (hierarchy == -1){
            System.out.println("you have no perms hahaha");
        }else{
            localPermissions = new ArrayList<>(permissions.subList(0, hierarchy));
        }
    }
    public void moveFile(String pathNameCurrent, String pathNameEnd) throws IOException{
        //check if object has perms and then moves file to path and also has ability to rename file if wanted
        if (localPermissions.contains("Move")){
            System.out.println("Authorization: Successful");
            Files.move(Paths.get(pathNameCurrent), Paths.get(pathNameEnd));
        }else{
            System.out.println("Authorization: Failed - No move permission granted");
        }
    }
    public void deleteFile(String pathName) throws IOException{
        if(localPermissions.contains("Delete")){
            //double checks for file deletion
            System.out.println("Are you sure you want to delete file? [Y/n]");
            String response = io.nextLine();
            if(response.equals("Y")){
                System.out.println("Authorization: Successful");
                System.out.println("File Deleted");
                Files.delete(Paths.get(pathName));
            }else if(response.equals("n")){
                System.out.println("File lives to see another day!(phew)");
            }else{
                while(response != "Y" || response != "n"){
                    System.out.println("Are you sure you want to delete file? [Y/n]");
                    response = io.nextLine();
                    if(response.equals("Y")){
                    System.out.println("File Deleted");
                    Files.delete(Paths.get(pathName));
                    }else if(response.equals("n")){
                    System.out.println("File lives to see another day!(phew)");
                    }
                }
            }
        }else{
            System.out.println("Authorization: Failed - No delete permission granted");
        }
    }
    public List<String> readFile(String pathName) throws IOException{
        if(localPermissions.contains("Read")){
            System.out.println("Authorization: Successful");
            return Files.readAllLines(Path.of(pathName));
        }else{
            System.out.println("Authorization: Failed - No read permission granted");
            List<String> tempList = new ArrayList<>();
            return tempList;
        }
    }
}

