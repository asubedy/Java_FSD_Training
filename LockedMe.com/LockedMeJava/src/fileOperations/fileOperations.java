package fileOperations;

import java.io.File;
import java.util.Collections;
import java.util.List;


public class fileOperations {
    private static String directoryName = "/Users/aaditya/Documents/Java_FSD_Training/LockedMe.com/files_directory";
    private fileOpsUtility utilFunctions = new fileOpsUtility();

    public void showFiles(){
        List<File> files = utilFunctions.getFilesInDirectory(directoryName);
        Collections.sort(files);

        System.out.println("Files in the directory: ");
        for (File fileName : files) {
            System.out.println(fileName.getName());
        }
    }

    public void addFiles() {
        System.out.println("Please Enter the Filename:");
        String fileName = this.utilFunctions.getInputString();

        System.out.println("You are adding a file named: " + fileName);

        if (!utilFunctions.checkValid(fileName)) {
            System.out.println("Invalid file name. Please use only alphanumeric characters, underscores, or periods.");
            return;
        }

        File file = new File(directoryName, fileName);

        try {
            if (file.exists()) {
                System.out.println("This file already exists.");
            } else {
                if (file.createNewFile()) {
                    System.out.println("File added successfully.");
                } else {
                    System.out.println("Failed to add the file.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while adding the file: " + e.getMessage());
        }
    }

    public void deleteFile() {
        System.out.println("Please Enter the Filename:");
        String fileName = this.utilFunctions.getInputString();

        System.out.println("You are deleting a file named: " + fileName);

        if (!utilFunctions.checkValid(fileName)) {
            System.out.println("Invalid file name. Please use only alphanumeric characters, underscores, or periods.");
            return;
        }

        try {
            if (utilFunctions.fileExists(directoryName, fileName)) {
                File file = new File(directoryName, fileName);

                if (file.delete()) {
                    System.out.println("File deleted successfully.");
                } else {
                    System.out.println("Failed to delete the file.");
                }
            } else {
                System.out.println("File not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the file: " + e.getMessage());
        }
    }

    public void searchFile() {
        System.out.println("Please Enter the Filename:");
        String fileName = this.utilFunctions.getInputString();

        System.out.println("You are searching for a file named: " + fileName);

        if (!utilFunctions.checkValid(fileName)) {
            System.out.println("Invalid file name. Please use only alphanumeric characters, underscores, or periods.");
            return;
        }

        try {
            String directoryPath = directoryName;
            List<File> files = utilFunctions.getFilesInDirectory(directoryPath);

            boolean found = false;

            for (File file : files) {
                if (file.isFile() && file.getName().equals(fileName)) {
                    System.out.println("Found " + fileName);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("File not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while searching for the file: " + e.getMessage());
        }
    }
}
