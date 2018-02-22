import java.util.*;


//marcfiume@gmail.com



public class CSVExpressions {
    
    /**
     * Return a new table that is a copy of the table referred to by the 
     * variable, with all of its rows, but with only the given columns. 
     * 
     * @param tab
     * @param intList
     * @return 
     */
    public Table columns(Table table, int[] ints){
        Table new_table = new Table();
        for (Row r: table.getcontents()){
            new_table.addRow(r.getelements(ints));
        }
        return new_table;
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
     * @param tab
     * @param column
     * @param operator
     * @param comparable
     * @return 
     */
    public Table rows(Table table, int p,int q, String rel){
        Table new_table = new Table();
        for(int i = 0; i < table.getSize(); i++){
            if (valueCompare(table.getRow(i).get(p),table.getRow(i).get(q), rel)){
                new_table.addRow(table.getRow(i));
            }
        }
        return new_table;
    }
    
    public Table product(Table table1, Table table2){
        Table new_table = new Table();
        for(int i = 0; i < table1.getSize(); i++){
            for(int j = 0; j < table2.getSize(); j++){
                Row tmp = CombineRow(table1.getRow(i), table2.getRow(j));
                new_table.addRow(tmp);
            }
        }
        return new_table;
    }   
    private boolean valueCompare(String value1, String value2, String relation){
        String[] comparaison = {"<", "<=", "==", ">=",">"};
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
     
    public Table join(Table table1,Table table2, int a, int b){
        Table tmp = product(table1,table2);
        return rows(tmp,a, b, "==");
    }
    

    public Table naturaljoin(Table table1,Table table2){
        boolean deternminant = true;
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
         new_table.settype(null);
        }
        
        return new_table;
    }
    
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
    public void orderby(Table table,int n){
        Row tmp = new Row();
        for(int i = 0; i < table.getSize(); i++){
            for(int j = 1; j < (table.getSize() - i); j++){
             if(valueCompare(table.getRow(j-1).get(n), table.getRow(j).get(n),
                  ">=")){
                 tmp = table.getRow(j-1);
                 table.contents.set(j-1, table.getRow(j));
                 table.contents.set(j, tmp);
             }
            }
        }
    }
    
}
