/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author c0dongyv
 */
import java.util.*;
public class Row{
/** Class Row is a class that takes a string and make it into a row form
 * 
 */
    //The contents of the row, stores string elements
    private ArrayList<String> contents = new ArrayList<String>();
    /** The constructor of the Row Class */
    public Row(){
    }     
    /** Constructor() of a row using a String input
     * 
     * 
     * 
     * @param s               The string input
    */
    public Row(String s){
        String [] wl = s.split(",");
        contents.addAll(Arrays.asList(wl));
    }    
    public Row(ArrayList<String> arrst){
      contents = arrst;
    }
     /**
     * 
     * Returns the contents of the row
     * 
     * 
     * @return contents       the contents of the row
     */
    public ArrayList<String> getcontents(){
        return contents;
    }
     /**
     * 
     * Adds a string item to the row
     * 
     * 
     * @param item           a string to be added
     * @return void          adds a string item
     */
    public void insert(String item){
        contents.add(item);
    }
     /**
     * 
     * Returns the string item with index i in the row
     * 
     * 
     * @param i                the index of the item in the row
     * @return contents.get(i)          the string item with index iin row
     */
    public String get(int i){
        return contents.get(i);
    }
      /**
     * 
     * Returns the length of a row
     * 
     * 
     * @return contents size()          the size of a row
     */
    public int getLen(){
        return contents.size();
    }
    /**
     * 
     * Returns a new row that is the copy of this row
     * 
     * 
     * @return output_row   copy of the row 
     */
    public Row clone(){
        Row output_row = new Row();
        for(String element :this.contents){
            output_row.insert(element);
        }
        return output_row;
    }
    /**
     * 
     * Returns the string contents of the row 
     * 
     * 
     * 
     * @return str.trim()          the string contents
     */
    @Override
    public String toString(){
        String str = "";
        for(int i = 0;i<this.getLen() - 1 ;i++){
           str += (this.get(i) + ",");

        }
        str += this.get(this.getLen() - 1 );
        return str.trim();
    }     
    /**
     * 
     * Returns a subset of the row the S according to given indexes of the row
     * 
     * 
     * 
     * @param int_list             the index of the elements in the row selected
     * @return new_row   the subset of the row 
     */
    public Row getelements(int[]  int_list){
        Row new_row = new Row();
        for(int col_num : int_list){
            new_row.insert(this.get(col_num));
        }
        return new_row;
    }
}
