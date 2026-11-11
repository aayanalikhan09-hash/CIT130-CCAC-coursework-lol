import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Organizer extends FileManager{
    //using extends to use pre-programmed functionality again without re-writing code
    //no delete perms for organizer
    private static int hierarchy = 3;
    private static ArrayList<String> permissions = new ArrayList<>(List.of("Read","Write","Move","Delete"));
    private ArrayList<String> localPermissions = new ArrayList<>();
    Scanner io = new Scanner(System.in);
    //probably going to add ui elements to constructor later
    public Organizer(){
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
    //organize files in a given folder in alphabetical order - i plan to add more sort order types for user's convenience
    public void orgFolderAlphabetOrder(String folderPath){
        File folderFilePath = new File(folderPath);
        if(folderFilePath.isDirectory() && localPermissions.contains("Move")){
        ArrayList<File> filesList = new ArrayList<>(Arrays.asList(folderFilePath.listFiles()));
        if(filesList != null){
        Collections.sort(filesList);
        for(File fileName : filesList){
            System.out.println(fileName.toString());
            }
        }
        }else{
            System.out.println("Error: Invalid filepath or provided path is not a directory(folder)");
        }
    }
}