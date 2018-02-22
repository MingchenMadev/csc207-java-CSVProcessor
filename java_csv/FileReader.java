/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
/**
 *
 * @author c0dongyv
 */
public class FileReader 
{
    /** This class handles files, it takes a file and convert it to a large
     * paragraph of string.
`   * 
    * @throws FileNotFoundException		The exception happens when 
     * a file doesn't exist in the folder.
    */
    public String tablestr = "";
    /** Constructor of FileReader takes a file and store the information inside
     as a large string of paragraph
     */
    public String headerstr;
    public FileReader(String filename, boolean hasheader) throws FileNotFoundException{
            File f = new File(filename);
            Scanner scanner = new Scanner(f);
            if (hasheader){
                headerstr = scanner.nextLine();
            }
            else{
                tablestr += scanner.hasNextLine();
                while (scanner.hasNextLine()){
                    tablestr += ("\n" + scanner.nextLine());
                }
            }
    }
    /** Return a string that the constructor constructs from a file 
1    * No parameters
     * @return			a very large string of paragraph
     */
    public String getString(){
        return tablestr;
    }
}
