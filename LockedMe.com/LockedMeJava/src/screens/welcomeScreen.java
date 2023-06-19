package screens;

import java.util.ArrayList;
import java.util.Scanner;

import fileOperations.fileOperations;

public class welcomeScreen implements screenInterface {

    private ArrayList<String> options = new ArrayList<>();

    public welcomeScreen(){
        options.add("1. Display All the Files");
        options.add("2. Display the File Menu");
        options.add("3. Quit Application");
        Show();
    }

    @Override
    public void Show() {
        System.out.println("Main Menu");
        for(String option: options){
            System.out.println(option);
        }
    }

    @Override
    public void GetUserInput() {
        Scanner input = new Scanner(System.in);
        int inputOption = 0;

        do {
            System.out.println("Enter an option:");
            inputOption = input.nextInt();
            this.NavigateOption(inputOption);
        } while (inputOption != 3);

        input.close();
    }
    

    @Override
    public void NavigateOption(int option) {
       switch (option) {
        case 1:
            fileOperations fileOperationsObj = new fileOperations();
            fileOperationsObj.showFiles();
            this.Show();
            break;
        case 2:
            filesOptionScreen showFileOptionScreen = new filesOptionScreen();
            showFileOptionScreen.GetUserInput();
            this.Show();
            break;
        default:
            if(option==3){
                System.out.println("Thank you for using LockedMe, hope to see you again.");
                break;
            }
            else{
                System.out.println("Please enter a valid option");
                break;
            }
       }
    }

}
    
