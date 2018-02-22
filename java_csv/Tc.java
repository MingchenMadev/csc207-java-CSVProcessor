import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Table {
    ArrayList<Column> contents = new ArrayList<Column>();
    String[] types;
    
    public Table (String[] type){
        this.types = type;
        for (int i = 0; i < types.length; i++){
            
            if (type.equals("int")){
                contents.add(new Column<Integer>());
            }else{
                contents.add(new Column<String>());
            }
       
        }
    }
    
    public String get_type(int i){
        return this.types[i];
    }
    
    public void addRow(String[] row){
        String type;
        for (int j = 0; j < row.length; j++){
                type = this.get_type(j);
                if (type.equalsIgnoreCase("int")){
                    this.contents.get(j).insert(new Integer(row[j]));
                }
                else{
                    this.contents.get(j).insert(row[j]);
                }
            }
    }
    
    public void load(String myString){
        String [] myRow = myString.split("\n");
        for(int i = 0; i < myRow.length; i++){
            String[] myCol = myRow[i].split(",");
            addRow(myCol);
         
        }
    }
    
    public Column getCols(int i){
        return this.contents.get(i);
    }   
    
    public Table columns(int[] ints){
        String[] type = new String[ints.length];
        for (int i = 0; i < ints.length; i++){
            type[i] =  this.get_type(ints[i]);
        }
        Table result = new Table(type);
        for (int i = 0; i < ints.length; i++){
            result.addCol(this.getCols(ints[i]));
        }
        return result;
    }
    public void addCol(Column myCol){
        contents.add(myCol);
    }
    //Hey, This is createCol, we need to use it to create our cols
    public ArrayList createCol(int i, ArrayList<ArrayList<String>> l){
        ArrayList<String> tmp = new ArrayList<String>();
        for(int w = 0; w < l.size();w ++){
            tmp.add(l.get(w).get(i));
        }
        return tmp;
    }

    //public 
    public ArrayList<String> splitRow(String row){
        String [] tmp = row.split(",");
        ArrayList<String> result = new ArrayList<String>(Arrays.asList(tmp));
        return result;
    }
    

    public ArrayList<String> joinRows(ArrayList<String> row1, ArrayList<String> row2){
        
        ArrayList<String> result = new ArrayList<String>();
        for (int w = 0; w < row2.size(); w++){
            result.add(row1.get(w));
        }
        for (int w = 0; w < row2.size(); w++){
            result.add(row2.get(w));
        }
        return result;
    }
    
    public Table product(Table other){
        ArrayList<ArrayList<String>> result_row = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> result_col = new ArrayList<ArrayList<String>>();
        for (int w = 0; w < this.getRows().size(); w++){
            ArrayList<String> row = this.getRows().get(w);
            for (int i = 0; i < other.getRows().size(); i++){
                result_row.add(addRows(row, other.getRows().get(i)));
            }
        } 
        for (int w = 0; w < result_row.get(0).size(); w++){
            result_col.add(createCol(w,result_row));
        }
        return new Table(result_row, result_col);
    }

    public Table join(Table tab, int a, int b){
        Table output;
        return output;
    }
    public Table NaturalJoin(Table other_tab){
        Table output;
        return output;
    }
    
    public Table OrderBy(int i){
        Table output;
        return output;
    }
    
    public Table Rows(int p, int q, String relation){
        Table output;
        return output;
    }
}
