import java.util.*;
import java.io.*;

/** Class CSVCommands is a class that gets the user information from the CSVProcessor
 * and consist of six commands - load, assign, save, print, exit, define
 * In this class, will take the user input and outputs the information according
 * to user's syntax
 */

public class CSVCommands {
    // field is a HashMap that maps the table name with table
    private HashMap<String, Table> field = new HashMap<String, Table>(); 
    // defined is a HashMap that check if the name is defined
    private HashMap<String, ArrayList<String>> defined = new HashMap<String,
            ArrayList<String>>();
    Expressions express = new Expressions();

    /** Constructor of CSVCommands Class */ 
    public CSVCommands(){        
    }
     /**
     * 
     * Return an ArrayList form of the user input
     * 
     * 
     * @param s                Set of input of String taken from the user
     * @return expression        An ArrayList form of user input
     */
    public ArrayList<String> toArray(String s){
        String[] myArr = s.split("[(< ,>)]");
        ArrayList<String> tmp = new ArrayList<String>(Arrays.asList(myArr));
        ArrayList<String> expression = new ArrayList<String>();
        for(int i = 0; i< tmp.size();i++){
            if(tmp.get(i).equals("")){
                expression.add(tmp.get(i));
           }
        }
        return expression;
    }
        /**
     * 
     * This method uses expressions of tables to form new tables and store them 
     * in a HashMap with the corresponding name as it's keys.
     * @throws TableNotDefinedException    The Exception that table is not defined
     * @throws IndexOutOfRangeException The Exception that index is out of range
     * @param expression     An arrayList form of user input
     * @return void        store the assigned table with its corresponding name          
     */
    public void assign(ArrayList<String> expression) throws TableNotDefinedException, IndexOutOfRangeExeption{
        String name = expression.get(0);
        if (expression.get(2).equals("column")){
            field.put(name,column_evaluate(expression));
        } else if (expression.get(2).equals("join")){
            field.put(name, join_evaluate(expression));
        } else if (expression.get(2).equals("product")){
            field.put(name, product_evaluate(expression));
        } else if (expression.get(2).equals("order_by")){
            field.put(name, orderby_evaluate(expression));
        } else if (expression.get(2).equals("rows")){
            field.put(name, rows_evaluate(expression));
        } else if (expression.get(2).equals("naturalJoin")){
            field.put(name, naturalJoin_evaluate(expression));
        } else {
            System.out.println("Failure in recognizing expression!");
        }
            
    }
    
     /**
     * 
     * Return a new table that is made up using column expression according to 
     * user's syntax.
     * 
     * @throws TableNotDefinedException    The Exception that table is not defined
     * @throws IndexOutOfRangeException The Exception that index is out of range
     * @param e       An ArrayList expression of the user input
     * @return table                    A new table using column expression
     */
    private Table column_evaluate(ArrayList<String> e) throws TableNotDefinedException, IndexOutOfRangeExeption{
        Table tmp1 = field.get(e.get(3));
        if(tmp1 == null){
           throw new TableNotDefinedException();
        }
        String [] stringlist = e.get(4).split(", ");
        int [] tmplist = new int[stringlist.length];
        for(int i = 0; i<tmplist.length;i++){
            tmplist[i] = Integer.valueOf(stringlist[i]);
        }
        return express.columns(tmp1, tmplist);
    }
     /**
     * 
     * Return a new table that is made up using join expression according to 
     * user's syntax.
     * 
     * @throws TableNotDefinedException    The Exception that table is not defined
     * @throws IndexOutOfRangeException The Exception that index is out of range
     * @param e       An ArrayList expression of the user input
     * @return table                    A new table  using join expression 
     */
    private Table join_evaluate(ArrayList<String> e) throws TableNotDefinedException, IndexOutOfRangeExeption{
        Table tmp1 = field.get(e.get(3));
        if(tmp1 == null){
           throw new TableNotDefinedException();
        }
        Table tmp2 = field.get(e.get(4));
        if(tmp1 == null){
           throw new TableNotDefinedException();
        }
        String [] stringlist = e.get(5).split(", ");
        int num1 = Integer.valueOf(stringlist[0]);
        int num2 = Integer.valueOf(stringlist[0]);
        return express.join(tmp1, tmp2, num1, num2);
    }
     /**
     * 
     * Return a new table that is made up using product expression according to 
     * user's syntax.
     * 
     * 
     * @throws TableNotDefinedException    The Exception that table is not defined
     * @param e       An ArrayList expression of the user input
     * @return table                    A new table  using product expression 
     */
    private Table product_evaluate(ArrayList<String> e) throws TableNotDefinedException{
        Table tmp1 = field.get(e.get(3));
        if(tmp1 == null){
           throw new TableNotDefinedException();
        }
        Table tmp2 = field.get(e.get(4));
        if(tmp2 == null){
            throw new TableNotDefinedException();
        }
        return express.product(tmp1, tmp2);
    }
     /**
     * 
     * Return a new table that is made up using natural join expression according to 
     * user's syntax.
     * 
     * @throws TableNotDefinedException    The Exception that table is not defined
     * 
     * @param e       An ArrayList expression of the user input
     * @return table                    A new table  using natural join expression 
     */
    private Table naturalJoin_evaluate(ArrayList<String> e) throws TableNotDefinedException{
        Table tmp1 = field.get(e.get(3));
        if(tmp1 == null){
                throw new TableNotDefinedException();
        }
        Table tmp2 = field.get(e.get(4));
        if(tmp2 == null){
                throw new TableNotDefinedException();
        }
        return express.naturaljoin(tmp1, tmp2);
    }
     /**
     * 
     * Return a new table that is made up using rows expression according to 
     * user's syntax.
     * 
     * @throws TableNotDefinedException    The Exception that table is not defined
     * @throws IndexOutOfRangeException The Exception that index is out of range
     * 
     * @param e       An ArrayList expression of the user input
     * @return table                    A new table  using rows expression 
     */
    private Table rows_evaluate(ArrayList<String> e) throws TableNotDefinedException, IndexOutOfRangeExeption{
        Table tmp1 = field.get(e.get(3));
        if(tmp1 == null){
                throw new TableNotDefinedException();
        }
        int num1 = Integer.valueOf(e.get(4));
        return express.rows(tmp1, num1, e.get(5), e.get(6));
    }
     /**
     * 
     * Return a new table that is made up using order_by expression according to 
     * user's syntax.
     * 
     * @throws TableNotDefinedException    The Exception that table is not defined
     * @throws IndexOutOfRangeException The Exception that index is out of range
     *         
     * @param e       An ArrayList expression of the user input
     * @return table                    A new table  using order_by expression 
     */
    private Table orderby_evaluate(ArrayList<String> e) throws TableNotDefinedException, IndexOutOfRangeExeption{
        Table tmp1 = field.get(e.get(3));
        if(tmp1 == null){
                throw new TableNotDefinedException();
        }
        return express.orderby(tmp1, Integer.valueOf(e.get(4)));
    }
     /**
     * 
     * Record the fact that there will be a table with the name given. 
     * That name must not be the name of an existing variable. 
     * Record also that it will have the given number of columns, 
     * with the given types, in the order specified. 
     * 
     * 
     * @throws TableNotDefinedException    The Exception that table is not defined
     * @throws IndexOutOfRangeException The Exception that index is out of range
     * 
     * @param e       An ArrayList expression of the user input
     * @param typelist   An list with the given types
     * @return void        put the name with its corresponding type in a hashmap
     */
    public void define(ArrayList<String> e){
        String name = e.get(1);
        String [] tmp = e.get(2).split(", ");
        List<String> lst = e.subList(2, tmp.length);
        ArrayList<String> output_list = new ArrayList<String>();
        for (String element:lst){
            output_list.add(element);
        }
        defined.put(name, output_list);

    }



     /**
     * 
     * This method evaluates the expression on the right-hand side, 
     * which will yield a table of results. 
     * Save the table in the variable on the left-hand side
     * if the filename already exist then override it with the new contents
     * 
     * 
     * @throws TableNotDefinedException    The Exception that table is not defined
     * 
     * @param e       An ArrayList expression of the user input
     * @return void               create a file and write the table's 
     * contents to it
     */
    public void save(ArrayList<String> expression)throws TableNotDefinedException{
        try{
            FileHandler filehandler = new FileHandler();
            String name = expression.get(1);
            Table table = field.get(expression.get(2));
            if(table == null){
                throw new TableNotDefinedException();
            }
            filehandler.write(table, name);
        }
        catch(Exception e){
            System.err.print("Error: " + e.getMessage());
        }
    }
     /**
     * 
     * This method prints the contents of the table referred to by variable 
     * to the standard output
     * 
     * 
     * 
     * 
     * @param e       An ArrayList expression of the user input
     * @return void               prints the contents of the table
     * contents to it
     */
    public void print(ArrayList<String> e){
        Table tmp = field.get(e.get(1));
        String output = tmp.toString();
        System.out.println(output);
    }
     /**
     * 
     * Read the file, build a table from its contents, and assign the variable to refer to it. 
     * The file must exist, and must contain data in the simplified csv format we have been dealing with. 
     * Check the data in the csv file to ensure that it conforms to the table definition for the variable. 
     * If the keyword "headed" is included in the command, 
     * treat the first row of the csv file as a header. 
     * 
     * 
     * 
     * @throws TableNotDefinedException    The Exception that table is not defined
     * @throws InvalidInputException   The Exception that the user input is Invalid
     * @param e       An ArrayList expression of the user input
     * @return void               Loads a file's contents to a table
     * contents to it
     */
    public void load(ArrayList<String> e) throws FileNotFoundException, InvalidInputException{
        FileHandler filehandler = new FileHandler();
        boolean hasheader = e.get(1).equals("[headed]");
        String name;
        String filename;
        if (hasheader){
            name = e.get(2);
            filename = e.get(4);
            String tablestr = filehandler.filereader(filename ,hasheader);
            field.put(name, loaded(tablestr, filehandler.getHeaderstr()));
        }
        else{
            name = e.get(1);
            filename = e.get(3);
            String tablestr = filehandler.filereader(filename ,hasheader);
            field.put(name, loaded(tablestr, filehandler.getHeaderstr())); 
        }
    }
     /**
     * 
     * Returns a table that contains all the contents from the file with  
     * the corresponding types and header
     * 
     * 
     * 
     * @throws InvalidInputException   The Exception that the user input is Invalid
     * @param myString       The string gets from the file
     * @param myheader          The header from the file as a string
     * @return void               Loads a file's contents to a table
     * contents to it
     */
    public Table loaded(String myString, String myheader) throws InvalidInputException{
        Table table = new Table();
        String[] myRow = myString.split("\n");
        Header head_title = new Header(myheader);
        table.setheader(head_title);
        ArrayList<Integer> int_cols = new ArrayList<Integer>();
        for (int i = 0; i < table.gettype().size(); i++){
            if (table.gettype().get(i).equals("int")){
                int_cols.add(i);
            }
        }
        for (int i = 0; i < myRow.length; i++){ 
            Row tmp = new Row(myRow[i]);
            for (int n:int_cols){
                if (!exp.isInteger(tmp.get(n))){
                    throw new InvalidInputException(); 
                }
            }
            table.addRow(tmp);
        }
        return table;
    }
    
}
    
    
 