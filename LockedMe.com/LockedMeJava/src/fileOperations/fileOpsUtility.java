package fileOperations;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fileOpsUtility {
     private static final Pattern INVALID_CHARACTERS_PATTERN = Pattern.compile("[^a-zA-Z0-9_.]");

    public Boolean checkValid(String fileName){
        if (fileName == null || fileName.isEmpty()) {
            return false;
        }

        Matcher matcher = INVALID_CHARACTERS_PATTERN.matcher(fileName);
        return !matcher.find();
    }
     public String getInputString(){
        Scanner in = new Scanner(System.in);
        return(in.nextLine());
    }

    public List<File> getFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            return Arrays.asList(files);
        } else {
            return new ArrayList<>();
        }
    }
    public Boolean fileExists(String directoryPath, String fileName) {
            File file = new File(directoryPath, fileName);
            return file.exists();
    }

    }

