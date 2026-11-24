import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WorkFile extends FileManager{
    private static ArrayList<String> fileData;
    private final String pathname;
    private String softwareName;



    //probably going to add ui elements to constructor later
    public WorkFile(String pathname, String softwareName) throws IOException{
        //only read write commands allowed
        super(2);
        this.pathname = pathname;
        this.softwareName = softwareName;
    }
    
    //need to add perms validation - core functionality still in testing, but seems to be functional, will add validation post thorough testing
    public String getPathName(){
        return pathname;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getFileName() {
        return Paths.get(pathname).getFileName().toString();
    }

    public String getFileExtension() {
        String fileName = getFileName();
        int idxBeforeDot = fileName.lastIndexOf('.');
        if (idxBeforeDot != -1 && idxBeforeDot < fileName.length() - 1) {
            return fileName.substring(idxBeforeDot).toLowerCase();
        }
        return "unknown";
    }

    public void loadFile() throws IOException{
        //loads file data as a bunch of lines
        List<String> fileLines = readFile(pathname);
        ArrayList<String> filesLinesArrayList = new ArrayList<>(fileLines);

        fileData = filesLinesArrayList;
        System.out.println(fileData);
    }

    public void overwriteFile(String location) throws IOException {
        if (localPermissions.contains("Write") && fileData != null) {
            Files.write(Path.of(location), fileData);
        } else {
            System.out.println("Authorization: Failed - No write permission granted - WorkFile");
        }
    }
    
    @Override
    public String toString() {
        return "[" + softwareName + " | " + getFileExtension() + "] " + getFileName();
    }

}
