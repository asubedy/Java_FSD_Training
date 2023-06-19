import screens.welcomeScreen;

public class App {
    public static void main(String[] args) throws Exception {
        
        // to clear the console and start a fresh main menu
        // System.out.print("\033[H\033[2J");
        // System.out.flush();

        System.out.println("Hello, This is Lockedme.com ");
        System.out.println("Developed by: Aaditya Narayan Subedy ");

        welcomeScreen welcomeObject = new welcomeScreen();
        welcomeObject.GetUserInput();
    }
}
