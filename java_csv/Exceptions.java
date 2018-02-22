/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author c1maming
 */
public class Exceptions{
    private String message;
    public Exceptions(){
    
    }
    public void InvalidInputException(){
        message = "The input is invalid";
    }
    public void  TypeDoesntMatchException(){
        message = "The input type doesn't match the type defined";
    }
    public void IndexOutOfRangeException(){
        message = "Index out of range";
    }
    public void TableNotFound(){
        message = "Table not found!";
    }
    public void TableNotDefined(){
        message = "Table not defined yet";
    }
    
    public String getmessage(){
        return message;
    }
    
}
