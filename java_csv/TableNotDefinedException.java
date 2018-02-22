/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author c1maming
 */

 /** Class TableNotDefinedExeption extends the Exception class will be thrown if 
 * the user calls a table that is not defined yet.
 * 
 */
class TableNotDefinedException extends Exception {
    
     /** Constructor() of  TableNotDefinedException Class
     * 
     * 
     * prints a log message that warn the table is not defined yet. 
     */
    public TableNotDefinedException() {
       System.out.println( "Error:  this table is not defined yet!");
    }
}
