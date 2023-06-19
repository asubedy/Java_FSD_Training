package screens;

import java.util.ArrayList;
import java.util.Scanner;

import fileOperations.fileOperations;

public class filesOptionScreen implements screenInterface {
	fileOperations fileOperationObj = new fileOperations();

	private ArrayList<String> options = new ArrayList<>();
    

    public filesOptionScreen() {
    	options.add("1. Add a File");
        options.add("2. Delete A File");
        options.add("3. Search A File");
        options.add("4. Return to Menu");
        Show();
    }

    @Override
    public void Show() {
       System.out.println("File Options");
        for(String option: options){
            System.out.println(option);
        }
    }

    @Override
    public void GetUserInput() {
       Scanner fileOptionInput = new Scanner(System.in);
        int inputOption = 0;

        do {
            System.out.println("Enter an option:");
            inputOption = fileOptionInput.nextInt();
            this.NavigateOption(inputOption);
        } while (inputOption != 4);
    }

    @Override
    public void NavigateOption(int option) {
       switch(option) {

            case 1: // Add File
                fileOperationObj.addFiles();
                this.Show();
                break;
    
            case 2: // Delete File
                fileOperationObj.deleteFile();
                this.Show();
                break;
    
            case 3: // Search File
                fileOperationObj.searchFile();
                this.Show();
                break;
            default:
                if(option!=4){
                System.out.println("Enter Valid Option!");}

                // to clear the console and start a fresh main menu
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;
    }
}


    
}
