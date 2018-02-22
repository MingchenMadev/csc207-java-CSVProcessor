import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
/** Class CSVProcessor is a class that repeatedly prompt user for command
 * and deal with their input to check which command the user is trying to call 
 * in their syntax and pass it down to CSVCommands, and then active the commands
 * the user try to call until they exit the program.
 * 
 */
public class CSVProcessor {
     /** Repeatedly ask the user for inputs
     * 
     * 
     * 
     * @throws InvalidInputException   The Exception that the user input is Invalid
     * @throws TableNotDefinedException    The Exception that table is not defined
     * @throws IndexOutOfRangeException The Exception that index is out of range
     * @param r1            the first row to combine with
     * @param  r2           the second row to combine with
     * @return new_row       a new row that is a combination of the two rows
    */
    
     public static void main(String [] args) throws TableNotDefinedException, InvalidInputException, IndexOutOfRangeExeption{
        CSVCommands commands = new CSVCommands();
        while(true){
            Scanner scanner = new Scanner(System.in );
            System.out.println("Please enter your command:");
            String next = scanner.nextLine();
            System.out.println(next);
            ArrayList<String> input = commands.toArray(next);
            if (input.get(0).equals("print")){
                System.out.println("You have selected: print command");
                commands.print(input);
            }
            else if (input.get(0).equals("save")){
                System.out.println("You have selected: save command");
                commands.save(input);
            }
            else if(input.get(0).contains("=")){
                System.out.println("You have selected: assignment command");
                commands.assign(input);
            }
            else if(input.get(0).equals("load")){
                System.out.println("You have selected: load command");
                try{
                    commands.load(input);
                }
                catch(FileNotFoundException e){
                    System.err.println("Error: " + e.getMessage());
                }
            }
            else if (input.get(0).equals("exit")){
                System.out.println("You have exited the program");
                break;
            }

        }
    }
}