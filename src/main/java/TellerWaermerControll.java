import java.util.Scanner;

public class TellerWaermerControll {



    public static void main(String[] args) {
        // declare a variable that will store the user input
        String userInput;
        String parameterInput;
        TellerWaermer tellerWaermer = new TellerWaermer(TellerWaermerTyp.SMALL);
        TellerWaermerResponseCode response;
        //declate a scanner object to read the command line input by user
        Scanner sn = new Scanner(System.in);

        //loop the utility in loop until the user makes the choice to exit
        while (true) {
            //Print the options for the user to choose from
            System.out.println("*****Available Commands*****");
            System.out.println("Start machine: 'ON'");
            System.out.println("Turn machine off: 'OFF'");
            System.out.println("Look if a plate is on top: 'PEEK'");
            System.out.println("Take a plate: 'PULL'");
            System.out.println("Put a new plate in the machine: 'PUSH'");
            System.out.println("To quit enter 'EXIT'");
            // Prompt the use to make a choice
            System.out.println("Enter your choice:");

            //Capture the user input in scanner object and store it in a pre declared variable
            userInput = sn.next();

            //Check the user input
            switch (userInput.toUpperCase()) {
                case "ON":
                    response = tellerWaermer.turnOn();
                    System.out.println(response);
                    break;
                case "OFF":
                    response = tellerWaermer.turnOff();
                    System.out.println(response);
                    break;
                case "PEEK":
                    response = tellerWaermer.peek();
                    System.out.println(response);
                    break;
                case "PULL":
                    System.out.println("Enter the number of plates you want to take:");
                    parameterInput = sn.next();
                    response = tellerWaermer.pullTeller(Integer.parseInt(parameterInput));
                    System.out.println(response);
                    break;
                case "PUSH":
                    System.out.println("Enter the number of plates you want to put in the machine:");
                    parameterInput = sn.next();
                    response = tellerWaermer.pushTeller(Integer.parseInt(parameterInput));
                    System.out.println(response);
                    break;
                case "EXIT":
                    //exit from the program
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    //inform user in case of invalid choice.
                    System.out.println("Invalid choice. Read the options carefully...");
            }
        }
    }

}
