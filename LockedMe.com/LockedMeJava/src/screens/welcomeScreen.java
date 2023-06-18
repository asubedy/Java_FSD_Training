package screens;

import java.util.ArrayList;
import java.util.Scanner;

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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
       switch (option) {
        case 1:
            this.ShowFiles();
            this.Show();
            break;
        case 2:
            // show file opions, need to make modular
            System.out.println("File Options will appear here");
            break;
        default:
            System.out.println("Please enter a valid option");
            break;
       }
    }

    public void ShowFiles() {

        System.out.println("List of Files: ");

    }
    
}
