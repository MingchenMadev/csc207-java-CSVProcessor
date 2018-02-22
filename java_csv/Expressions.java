import java.util.*;


/** Class Expressions is a class that consists of six expression - columns, rows
 * join, natural join, order by, product. The CSVCommand pass information to 
 * this class to evaluate the expression on the right side of the equation.
 * 
 */
public class Expressions {
    
    /** Constructor of Expression Class */ 
    public Expressions(){
         
     }
     /**
     * Returns a boolean is the input an integer value or not
     * 
     * @catch InvalidInputException   The Exception that the user input is 
     * Invalid
     * @param input                   The user syntax   
     * @return  boolean        True if the user enters a integer, false otherwise
     */
    public boolean isInteger(String input){
        try {
            Integer.parseInt(input);
            return true;
        }
        catch( Exception InvalidInputException ) {
            return false;
        }
    }
    
    /**
     * Return a new table that is a copy of the table referred to by the 
     * variable, with all of its rows, but with only the given columns. 
     * 
     * 
     * 
     * @throws IndexOutOfRangeException The Exception that index is out of range
     * @param table                     The table that is to be copied      
     * @param ints             An integer list with all the given column numbers
     * @return  new_table                 a new table with the given columns
     */
    public Table columns(Table table, int[] ints) throws IndexOutOfRangeExeption{
        Table new_table = new Table();
        for(int n : ints){
            if(n > table.gettype().size()){
                throw new IndexOutOfRangeExeption();
            }
        }
        for (Row r: table.getcontents()){
            new_table.addRow(r.getelements(ints));
        }
        new_table.setheader((Header)table.getHeader().getelements(ints));
        new_table.getHeader().update();
        new_table.settype(select_types(ints, table.gettype()));
        return new_table;
    }
   
    private ArrayList<String> select_types(int[] ints, ArrayList<String> str_list){
        ArrayList<String> output_str_l = new ArrayList<String>();
        for (int i = 0; i < ints.length; i++){
            output_str_l.add(str_list.get(ints[i]));
        }
        return output_str_l;
    }
       /**
     * Return a new table that is a copy of the table referred to by the 
     * variable, with all of its rows, but with only the given columns. 
     * 
     * 
     * @throws IndexOutOfRangeException The Exception that index is out of range
     * @param table    A table to work with      
     * @param strs     An String list with all the given columns to be expressed
     * @return  new_table                 a new table with the given columns
     */
    public Table columns(Table table, String[] strs) throws IndexOutOfRangeExeption{
        Integer tmp;
        ArrayList<Integer> col_arrayl = new ArrayList<Integer>();
        for (String s :strs) {
            tmp = table.getHeader().find_col(s);
            if (tmp != null){
                col_arrayl.add(tmp);
            }
        }
        if (strs.length == col_arrayl.size()){
            int[] output_arry = arraylist_convert(col_arrayl);
            return columns(table, output_arry);
        } else {
            return null;
        }
       
    }
     /** Convert a ArrayList of integers to a array with integers
     * 
     * 
     * 
     * 
     * @param arrayl                  an arraylist of integers
     * @return output                an array of integers
    */
    private int[] arraylist_convert(ArrayList<Integer> arrayl){
        int[] output = new int[arrayl.size()];
        for (int i = 0; i < arrayl.size(); i++){
           output[i] = arrayl.get(i);
        }
        return output;
       
    }
          /**
     * Return a new table that is a copy of the table referred to by the variable, 
     * with all of its rows and columns,but with the rows in ascending order 
     * according to the values in the given column. 
     * 
     * 
     * @throws IndexOutOfRangeException The Exception that index is out of range
     * @param table_copy    A table to work with      
     * @param n     the index of column 
     * @return  new_table                 a new table that is in ascending order
     */
    public Table orderby(Table table_copy,int n) throws IndexOutOfRangeExeption{
        if(n > table_copy.gettype().size()){
                throw new IndexOutOfRangeExeption();
        }
        ArrayList<Integer> int_list = new ArrayList<Integer>();
        Integer tmp_int;
        Table table = table_copy.clone();
        Row tmp = new Row();
        String type = table.gettype().get(n);
        if (type.equals("int")){
            for (Row item :table.getcontents()){
                int_list.add(new Integer(item.get(n)));
            }
            for(int i = 0; i < table.getSize(); i++){
                for(int j = 1; j < (table.getSize() - i); j++){
                    if(int_list.get(j-1) >= int_list.get(j)){
                        tmp_int = int_list.get(j-1);
                        int_list.set(j-1, int_list.get(j));
                        int_list.set(j, tmp_int);
                        tmp = table.getRow(j-1);
                        table.getcontents().set(j-1, table.getRow(j));
                        table.getcontents().set(j, tmp);
                    }
                }
            }
        }
        else {
            for(int i = 0; i < table.getSize(); i++){
                for(int j = 1; j < (table.getSize() - i); j++){
                    if(valueCompare(table.getRow(j-1).get(n), table.getRow(j).get(n),
                     ">=")){
                        tmp = table.getRow(j-1);
                        table.getcontents().set(j-1, table.getRow(j));
                        table.getcontents().set(j, tmp);
                    }
                }
            }
        }
        return table;
    }

    /**
     * 
     * Return a new table that is a copy of the table referred to by the 
     * variable, with all of its columns, but with only the rows that satisfy 
     * a condition: the value in column p must bear the specified relationship 
     * to q (where p is the first integer given and q is the second integer 
     * given). 
     * 
     * 
     * @throws IndexOutOfRangeException The Exception that index is out of range
     * @param table                       a table to work with
     * @param p                     the index of column
     * @param rel             the relation use to compare 
     * @param valriable             the value to compare with
     * @return   new_table     a new table referred by the variable
     */
  public Table rows(Table table, int p, String rel, String valriable) throws IndexOutOfRangeExeption{
        if(p > table.gettype().size()){
                throw new IndexOutOfRangeExeption();
        }
        Table new_table = new Table();
        Integer compare;
        Integer tmp_num;
        if (isInteger(valriable) && (!table.gettype().get(p).equals("int"))){
            compare = new Integer("value");
            for(int i = 0; i < table.getSize(); i++){
                tmp_num = new Integer(table.getRow(i).get(p));
                if (valueCompare(tmp_num, compare, rel)){
                    new_table.addRow(table.getRow(i));
                }
            }
        } else {
            for(int i = 0; i < table.getSize(); i++){
                if (valueCompare(table.getRow(i).get(p), valriable, rel)){
                    new_table.addRow(table.getRow(i));
                }
            }
        }
        new_table.settype((ArrayList<String>)table.gettype().clone());
        new_table.setheader((Header)table.getHeader().clone());
        return new_table;
    }
     /**
     * 
     * Return a new table that is the Cartesian product of table the two tables 
     * referred to by the variables. If the tables are T1 and T2, the Cartesian 
     * product will have all the columns of T1 and all the columns of T2, 
     * and each row will consist of a row from T1 concatenated to a row from T2. 
     * 
     * 
     * 
     * @param table1                     the first table to work with
     * @param table2                     the second table to work with 
     * @return   new_table     a new table that is the Cartesian product of the 
     * two
     */
    public Table product(Table table1, Table table2){
        Table new_table = new Table();
        for(int i = 0; i < table1.getSize(); i++){
            for(int j = 0; j < table2.getSize(); j++){
                Row tmp = CombineRow(table1.getRow(i), table2.getRow(j));
                new_table.addRow(tmp);
            }
        }
        new_table.setheader((Header)CombineRow(table1.getHeader(),
                table2.getHeader()));
        new_table.getHeader().update();
        new_table.settype(combine_arrayl(table1.gettype(), table2.gettype()));
        return new_table;
    }
         /**
     * 
     * Returns a new arraylist that has the contents of the two arraylist to 
     * work with
     * 
     * 
     * @param al_1                    the first arraylist to work with 
     * @param al_2                     the second arraylist to work with 
     * @return  output            the combination of the contents of the two
     */  
    
    private ArrayList<String> combine_arrayl(ArrayList<String> al_1,
            ArrayList<String> al_2){
        ArrayList<String> output = (ArrayList<String>)al_1.clone();
        for (String item:al_2){
            output.add(item);
        }
        return output;
    }
     /**
     * 
     * Return a boolean of the relation between the two integers values according
     * to the relation sign use to compare with. 
     * 
     * 
     * 
     * @param value1                     the first value to compare with 
     * @param value2                     the second value to compare with 
     * @param relation                   the relation use to compare 
     * @return   boolean     the relation between the two integer values
     */    
    private boolean valueCompare(Integer value1, Integer value2, String relation){
        int int_comp =  value1 - value2;
        if (relation.equals("<")){
            return int_comp < 0;
        }
        else if (relation.equals(">")){
            return int_comp > 0;
        }
        else if (relation.equals("==")){
            return int_comp == 0;
        }
        else if (relation.equals(">=")){
            return int_comp >= 0;
        }
        else if (relation.equals("<=")){
            return int_comp <= 0;
        }
        return false;
    }
         /**
     * 
     * Return a boolean of the relation between the two string values according
     * to the relation sign use to compare with. 
     * 
     * 
     * @param value1                     the first value to compare with 
     * @param value2                     the second value to compare with 
     * @param relation                   the relation use to compare 
     * @return   boolean     the relation between the two string values
     */    
    private boolean valueCompare(String value1, String value2, String relation){
        int str_comp =  value1.compareToIgnoreCase(value2);
        if (relation.equals("<")){
            return str_comp < 0;
        }
        else if (relation.equals(">")){
            return str_comp > 0;
        }
        else if (relation.equals("==")){
            return str_comp == 0;
        }
        else if (relation.equals(">=")){
            return str_comp >= 0;
        }
        else if (relation.equals("<=")){
            return str_comp <= 0;
        }
        return false;
    }
         /**
     * 
     * Return a new table that is the Cartesian product of the two tables 
     * referred to by the variables, but that includes only rows that satisfy a 
     * condition: the value in column p1 from the first table must match the 
     * value from column q1 in the second table, and the value of column p2 from 
     * the first table must match the value from column q2 in the second table, 
     * and so on (where p1, q1, p2, q2, ... are the integers given).  
     * 
     * 
     * @throws IndexOutOfRangeException The Exception that index is out of range
     * @param value1                     the first value to compare with 
     * @param value2                     the second value to compare with 
     * @param a                the index of the row 
     * @param b                   the variable to refer to 
     * @return   table         a new table that is the Cartesian product of 
     * the two tables referred to by the variables 
     */       
    public Table join(Table table1,Table table2, int a, int b) throws IndexOutOfRangeExeption{
        if(a > table1.gettype().size()||b > table2.gettype().size()){
                throw new IndexOutOfRangeExeption();
        }
        Table tmp = product(table1,table2);
        tmp.setheader((Header)CombineRow((Header)table1.getHeader(), table2.getHeader()));
        tmp.getHeader().update();
        tmp.settype(combine_arrayl(table1.gettype(), table2.gettype()));
        return thita_rows(tmp,a, b, "==");
    }
   

     public Table thita_rows(Table table, int p, int q, String rel){
        Table new_table = new Table();
        Integer tmp_num_q;
        Integer tmp_num_p;
        if (table.gettype().get(p).equals("int") &&
                (table.gettype().get(p).equals("int"))){
            for(int i = 0; i < table.getSize(); i++){
                tmp_num_p = new Integer(table.getRow(i).get(p));
                tmp_num_q = new Integer(table.getRow(i).get(q));
                if (valueCompare(tmp_num_p, tmp_num_q, rel)){
                    new_table.addRow(table.getRow(i));
                }
            }
        } else {
            for(int i = 0; i < table.getSize(); i++){
                if (valueCompare(table.getRow(i).get(p),
                        table.getRow(i).get(q), rel)){
                    new_table.addRow(table.getRow(i));
                }
            }
        }
        new_table.settype((ArrayList<String>)table.gettype().clone());
        new_table.setheader((Header)table.getHeader().clone());
        return new_table;
    }
         /**
     * 
     * Return a new table that is the Cartesian product of the two tables 
     * referred by columns to be compared that are determined entirely by column
     * names. If a column in the first table has the exact same name as a 
     * column in the second table, values in those two columns must match. 
     * 
     * 
     * 
     * 
     * @param value1                     the first value to compare with 
     * @param value2                     the second value to compare with 
     * @return  new_table         a new table that is the Cartesian product of 
     * the two tables referred to by the column names
     */        
    public Table naturaljoin(Table table1,Table table2){
        Table new_table = new Table();
        Collection values = null;
        HashMap correlation = table1.getHeader().selectcommon(table2.getHeader());
        for (int i = 0; i < table1.getSize(); i++){
            for (int j = 0; j < table2.getSize(); j++){
                if(naturaljoinable(table1.getRow(i), table2.getRow(j),
                        correlation)){
                    values = correlation.values();
                    Row tmp = Selected_CombineRow(table1.getRow(i),
                            table2.getRow(j), values);
                    new_table.addRow(tmp);
                }
            }
         new_table.setheader((Header)Selected_CombineRow(table1.getHeader(), table2.getHeader(), values));
         new_table.getHeader().update();
         new_table.settype(combine_types(table1.gettype(), table2.gettype(), values));
        }
       
        return new_table;
    }
     /** Combines two types arraylists together with the given index of the types
     * 
     * 
     * 
     * 
     * 
     * @param ar1                 the types arraylist of first table
     * @param  ar2                the types arraylist of second table
     * @param num_list            the index of the types in type arraylist
     * @return output_list         the combined arrayList
    */
    private ArrayList<String> combine_types(ArrayList<String> ar1,
            ArrayList<String> ar2, Collection num_list){
        ArrayList<String> output_list = (ArrayList<String>) ar1.clone();
        for (int i = 0; i < ar2.size(); i++){
            if (!num_list.contains(i)){
                output_list.add(ar2.get(i));
            }
        }
        return output_list;
    }
      /**Compare two rows and see if they are natural joinable,(do they have the
     * exact matching elements)
     * 
     * 
     * @param r1                 the first row compare with
     * @param  r2                the second row to compare with
     * @param hm            the hashmap contains  integer keys and values
     * @return boolean      true if the two rows are natural joinable,false otherwise
    */
    
    
    private boolean naturaljoinable(Row r1, Row r2, HashMap<Integer, Integer> hm){
        int value;               
        for(int key:hm.keySet()){
            value = hm.get(key);
            if(!r1.get(key).equals(r2.get(value))){
                return false;
            }
        }
        return true;
    }
    
     /** Return a new row that contains all the contents of the row with respect
     * to the collection of values
     * 
     * 
     * 
     * 
     * @param r1            the first row to combine with
     * @param  r2           the second row to combine with
     * @param eliminate_col a Collection of values from the hashmap       
     * @return new_row       a new row that is a combination of the two rows
    */
    
    private Row Selected_CombineRow(Row r1, Row r2, Collection eliminate_col){
        Row output_row = new Row();
        for(int i = 0; i< r1.getLen();i++){
            output_row.insert(r1.get(i));
        }
        for(int j = 0; j< r2.getLen();j++){
            if (!eliminate_col.contains(j)){
                output_row.insert(r2.get(j));
            }    
        }
        return output_row;
    }
     /** Returns a new row that contains the contents of the first and second row
     * 
     * 
     * 
     * 
     * 
     * @param r1            the first row to combine with
     * @param  r2           the second row to combine with
     * @return new_row       a new row that is a combination of the two rows
    */
    
    private Row CombineRow(Row r1, Row r2){
        Row new_row = new Row();
        for(int i = 0; i< r1.getLen();i++){
                new_row.insert(r1.get(i));
        }
        for(int j = 0; j< r2.getLen();j++){
                new_row.insert(r2.get(j));
        }
        return new_row;
    }
}
