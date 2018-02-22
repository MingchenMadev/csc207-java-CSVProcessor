/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
/**
 *
 * @author c0dongyv
 */
public class FileHandler
{
    /** This class handles files, it takes a file and convert it to a large
     * paragraph of string.
`   * 
    * @throws FileNotFoundException		The exception happens when 
     * a file doesn't exist in the folder.
    */
    private String tablestr = "";

    private String headerstr;
    /** Constructor of FileHandler Class */ 
    public FileHandler(){
        
    }
    /** Returns a large paragraph of String with given filename
     * 
     * 
     * 
     * @throws FileNotFoundException The Exception that  file doesn't exist in the folder
     * @param filename                 the filename of the file
     * @param  hasheader               a boolean of if a file has a header
     * @return tablestr                a large paragraph of string
    */
    public String filereader(String filename, boolean hasheader) throws FileNotFoundException{
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
            return tablestr;
    }
     /** Returns a string that has been read from a file
     * 
     * 
     * 
     * @return tablestr              the contents of the file
    */
    public String getString(){
        return tablestr;
    }
     /** Returns the header of a file as a line of string
     * 
     * 
     * 
     * @return headerstr              the contents of the header
    */
    public String getHeaderstr(){
        return headerstr;
    }
     /** Writes the contents of the table to a file and assign it with the given
     * name
     * 
     * 
     * @throws FileNotFoundException The Exception that  file doesn't exist in the folder
     * @param table                     the filename of the file
     * @param  table                the table to work with
     * @return void                writes a file with the table's contents
    */
    public void write(Table table, String name)throws FileNotFoundException{
        File f1 = new File(name);
        if(f1.exists()){
             f1.delete();   
            }
        try{
            // Create file 
            FileWriter f = new FileWriter(name);

            BufferedWriter out = new BufferedWriter(f);
            for(Row r: table.getcontents()){
                for(String s : r.getcontents()){
                    out.write(s);
                }
                out.write("\n");
            }
            //Close the output stream
            out.close();
        }
        catch (Exception e){//Catch exception if any
             System.err.println("Error: " + e.getMessage());
        }
    }
    public static void main (String [] args){
        try{
            // Create file 
            FileWriter f = new FileWriter("test.txt");
            BufferedWriter out = new BufferedWriter(f);
            out.write("sss");
            out.write("\n");
            out.write("ttt ");
            out.close();
        }
            //Close the output stream

        catch (Exception e){//Catch exception if any
             System.err.println("Error: " + e.getMessage());
        }
        
        
    }

}
