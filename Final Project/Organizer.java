import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Organizer extends FileManager{
    //using extends to use pre-programmed functionality again without re-writing code
    //no delete perms for organizer
    //probably going to add ui elements to constructor later
    public Organizer(){
        super(3);
    }

    public void organizeWorkFiles(String pathnameDestination, ArrayList<WorkFile> workFiles) throws IOException {
        if (!localPermissions.contains("Move")) {
            System.out.println("Authorization: Failed - No move permission granted");
            return;
        }
        if (!localPermissions.contains("Write")) {
            System.out.println("Authorization: Failed - No write permission granted");
            return;
        }

        Finder.purgeFileUpdate(pathnameDestination, workFiles);
        System.out.println("Files organized at: " + pathnameDestination);
    }

    //organize files in a given folder in alphabetical order -  i plan to add more sort order types for user's convenience
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

    public static void ZipGiven(){
        //have not learned how to do anything remotely near this, will need to do external research on prototype build
        System.out.println("Zipped!");
    }


}
