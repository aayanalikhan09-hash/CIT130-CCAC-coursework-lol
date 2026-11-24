import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager{
    private Integer hierarchy = 4;
    protected static final ArrayList<String> permissions = new ArrayList<>(List.of("Read","Write","Move","Delete"));
    protected ArrayList<String> localPermissions = new ArrayList<>();

    public FileManager(){
        updatePerms();
    }
    //probably going to add ui elements to constructor later
    public FileManager(int perms){
        this.hierarchy = perms;
        updatePerms();

    }
    
    public ArrayList<String> getPerms() throws IOException{
        //returns object's hierarchy compared to all other objects
        return localPermissions;
    }

    public int getHierarchy() throws IOException{
        return hierarchy;
    }

    protected final void updatePerms(){
        //update permissions based on hierarchy
        if (hierarchy == -1){
            System.out.println("No permissions granted");
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
                System.out.println("Authorization: Failed - No move permission granted - FileManager");
            }
    }

    //unused because the file code replaces the files anyways, so it works out, but keeping it just in case future code might be able to utilize it
    public void deleteFile(String pathName, boolean confirmed) throws IOException{
        if(localPermissions.contains("Delete")){
            //double checks for file deletion because deletion is permanent
            System.out.println("Authorization: Successful");
            if(confirmed){
                System.out.println("File Deleted");
                Files.delete(Paths.get(pathName)); 
            }else{
                System.out.println("File not deleted");
            }
        }else{
            System.out.println("Authorization: Failed - No delete permission granted - FileManager");
        }
    }

    public List<String> readFile(String pathName) throws IOException{
        if(permissions.subList(0, hierarchy).contains("Read")){
            System.out.println("Authorization: Successful");
            return Files.readAllLines(Path.of(pathName));
        }else{
            System.out.println("Authorization: Failed - No read permission granted - FileManager");
            List<String> tempList = new ArrayList<>();
            return tempList;
        }
    }

    @Override
    public String toString(){
        return hierarchy.toString();
    }


}
