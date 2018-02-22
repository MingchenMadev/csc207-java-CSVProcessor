/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author c1maming
 */
 
 /** Class InvalidInputException extends the Exception class will be thrown if 
 * the user enters an invalid input.
 * 
 */
class InvalidInputException extends Exception {
    /** Constructor() of InvalidInputException Class
     * 
     * prints a log message that warn the user their input is valid. 
     */
    public InvalidInputException() {
       System.out.println( "Error:  The input is invalid");
    }
    
}
