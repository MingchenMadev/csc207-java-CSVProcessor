import java.util.*;
/** Class Table is a table that consists of rows as its contents and
 *  store informations in it.
 */
public class Table {
    private ArrayList<Row> contents = new ArrayList<Row>();// stores rows in this table
    private Header header;// The headers of this table
    ArrayList<String> types;// The type list of each column in this table
    /** Constructor of a empty Table() */
    public Table (){   
    }
    
    /**
     * 
     * This method is a toString() method for table
     * 
     * @return str          table contents as a string 
     */
    @Override
    public String toString(){
        String str = "";
        if (header != null){
            str += (header.toString() + "\n");
        }
        for(Row r:this.contents){
            str += (r.toString() + "\n");
        }
        return str.trim();
    }
     /**
     * 
     * This method assigns a new header to this table
     * 
     * @param h       a header of our ; 
     * @return void        assign a header to our table 
     */
    public void setheader(Header h){
        if(header != null){
            header = h;
        }
    }
     /**
     * 
     * This method updates the type of each column in this table
     * 
     * @param type_list       a list of types of this table 
     * @return void        update this a list of the type of each column of this
     * table
     */
    public void settype(ArrayList<String> type_list){
        types = type_list;
    }
     /**
     * 
     * This method gets the types of this table
     * 
     * 
     * @return types      a list of type of each column of this table
     */
    public ArrayList<String> gettype(){
        return types;
    }
     /**
     * 
     * This method adds a row to the end of this table
     * 
     * @param row       a row with its contents 
     * @return void        update the contents of table by adding a row 
     */
    public void addRow(Row row){
        contents.add(row);
    }
    
     /**
     * 
     * This method gets the contents of the i-th row
     * 
     * @param i      the row number 
     * @return row       the contents of the specific row
     */
    public Row getRow(int i){
        return contents.get(i);
    }
     /**
     * 
     * This method return the size of this table
     * 
     * 
     * @return contents.size()       the size of this table
     */
    public int getSize(){
        return contents.size();
    }
     /**
     * 
     * This method return the contents of this table
     *  
     * @return contents    the contents of this table
     */
    public ArrayList<Row> getcontents(){
        return contents;
    }
     /**
     * 
     * This method returns the header of this table
     *  
     * @return Header        the header of this table
     */
    public Header getHeader(){
        return header;
    }
     /**
     * 
     * This method returns a duplication of this table
     * 
     * 
     * @return output_table        clone of this table
     */
     public Table clone(){
        Table output_table = new Table();
        output_table.settype((ArrayList<String>)types.clone());
        output_table.setheader((Header)this.getHeader().clone());
        for(Row row :this.contents){
            output_table.addRow(row.clone());
        }
        return output_table;
    }
     /**
     * 
     * This method clears all the contents of this table
     * 
     * @return void        clear the table
     */
    public void clear(){
        this.contents = new ArrayList<Row>();
        this.header = null;
    }
    public static void main(String[] args){
        String s = "define <name> ( <columndefinitions> )";
        String [] wl = s.split("[(< ,>)]");
        ArrayList<String> contents = new ArrayList<String>(Arrays.asList(wl));
        ArrayList<String> c = new ArrayList<String>();
        for(int i = 0; i<contents.size();i++){
            //contents.set(i,contents.get(i).trim());
            if(!contents.get(i).equals("")){
                c.add(contents.get(i));
            }
        }
        System.out.println(c);
        String [] tmpstring = "1, 2, 3, 4".split(", ");
        //System.out.println(tmpstring);
        for(String str: tmpstring){
            System.out.println(str);
        }
        System.out.println(tmpstring[0]);
    }
}

    

