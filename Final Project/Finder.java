
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;



public class Finder extends FileManager{

    // Scans a folder and returns matching WorkFiles grouped by software name
    public static Map<String, ArrayList<WorkFile>> findAndGroupFiles(
            String pathname,
            Map<String, ArrayList<String>> softwareCategories) throws IOException {

        Map<String, ArrayList<WorkFile>> groupedFiles = new LinkedHashMap<>();
        Queue<File> folders = new ArrayDeque<>();
        File basefolder = new File(pathname);

        if (!basefolder.exists() || !basefolder.isDirectory()) {
            System.out.println("Invalid source directory: " + pathname);
            return groupedFiles;
        }

        //take keys and add them with a list to the map so its easy to return as sw-file pairs
        for (String software : softwareCategories.keySet()) {
            groupedFiles.put(software, new ArrayList<>());
        }

        folders.add(basefolder);

        while (!folders.isEmpty()) {
            File currentFolder = folders.poll();
            File[] files = currentFolder.listFiles();

            if (files == null) {
                continue; // skip over empty, unreadable, or restricted folders so we dont crash the program
            }

            for (File file : files) {
                if (file.isDirectory()) {
                    folders.add(file);
                } else {
                    String lowerName = file.getName().toLowerCase();

                    //iterating through each group and its extensions and seeing if any of them match the file in question
                    for (Map.Entry<String, ArrayList<String>> category : softwareCategories.entrySet()) {
                        String softwareName = category.getKey();
                        ArrayList<String> extensions = category.getValue();
                        boolean matched = false;

                        for (String type : extensions) {
                            //normalizing all extensions provided for consistency and so theres less variability
                            String normalizedType = type.startsWith(".") ? type.toLowerCase() : "." + type.toLowerCase();
                            if (lowerName.endsWith(normalizedType)) {
                                //if it matches, make it a workfile and add it to the map
                                WorkFile wf = new WorkFile(file.getAbsolutePath(), softwareName);
                                groupedFiles.get(softwareName).add(wf);
                                matched = true;
                                break;
                            }
                        }

                        if (matched) {
                            break; //stop the checking process once we find a match
                        }
                    }
                }
            }
        }

        return groupedFiles;
    }

    //legacy code which ill keep since it works, but im not going to be using it
    public static ArrayList<File> findTheFiles(String pathname, ArrayList<String> fileTypes){
        ArrayList<File> potentialWorkFiles = new ArrayList<>();
        //using this specific class because it allows for objects to be added anywhere within the "queue" and doesn't throw an error
        Queue<File> folders = new ArrayDeque<>();
        File basefolder = new File(pathname);

        for(File file : basefolder.listFiles()){
            //checks if the current object is a folder, if so adds it to check later for more files
            if(file.isDirectory()){
                folders.add(file);
            }else{
                //checks if the file ends with any of the custom file types the user listed
                for(String type : fileTypes){
                    if(file.getName().toLowerCase().endsWith(type.toLowerCase())){
                        potentialWorkFiles.add(file);
                    }
                }
            }
        }
        //checks and sees if the queue of folders is empty yet
        while (!folders.isEmpty()) {
            File currentFolder = folders.poll();
            File[] files = currentFolder.listFiles();
            
            if (files == null) {
                continue; // Skip empty folders + not readable ones + zipped ones
            }

            for (File file : files) {
                if (file.isDirectory()) {
                    folders.add(file);
                } else {
                    String lowerName = file.getName().toLowerCase();
                    for (String type : fileTypes) {
                        //checks if the file name contains the data type we're looking for
                        if (lowerName.endsWith(type)) {
                            potentialWorkFiles.add(file);
                            break;
                        }
                    }
                }
            }
        }
        
        return potentialWorkFiles;
    }

    public static void purgeFileUpdate(String pathnameDestination, ArrayList<WorkFile> workFiles) throws IOException {
        Path destination = Paths.get(pathnameDestination);
        System.out.println(destination);
        Files.createDirectories(destination);

        for (WorkFile file : workFiles) {
            Path source = Paths.get(file.getPathName());
            System.out.println(source);

            //just remove the dots in front of the labels to make the folder names look nicer
            String extLabel = file.getFileExtension().replaceFirst("^\\.", "");
            System.out.println(extLabel);

            //making all the paths nice and organized with the order going destination,software type,and filetype
            Path targetDir = destination.resolve(file.getSoftwareName()).resolve(extLabel);
            Files.createDirectories(targetDir);
            System.out.println(targetDir);

            Path targetPath = targetDir.resolve(source.getFileName());
            System.out.println(targetPath);

            //copy over data and replace any existing files
            Files.copy(source, targetPath, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Done! hehe");
        }
    }
}
