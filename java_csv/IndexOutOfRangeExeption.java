/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author c1maming
 */
 
 /** Class IndexOutOfRangeExeption extends the Exception class will be thrown if 
 * the user enters a index that is out of range.
 * 
 */
class IndexOutOfRangeExeption extends Exception {
    
     /** Constructor() of  IndexOutOfRangeExeption Class
     * 
     * 
     * prints a log message that warn the index user entered is out of range. 
     */
    public IndexOutOfRangeExeption(){
       System.out.println("Error: Index out of range");
    }
}
