import java.util.*;
import java.io.*;

public class CSVCommands {
    private HashMap<String, Table> field = new HashMap<String, Table>();
    Expressions express = new Expressions();
    FileHandler filehandler = new FileHandler();
    public CSVCommands(){        
    }
    public ArrayList<String> getArray(String s){
        String[] myArr = s.split("[()]");
        ArrayList<String> tmp = new ArrayList<String>(Arrays.asList(myArr));
        ArrayList<String> expression = new ArrayList<String>();
        for(int i = 0; i< tmp.size();i++){
            if(tmp.get(i).equals("")){
                expression.add(tmp.get(i));
           }
        }
        return expression;
    }
    public void assign(String s){
        ArrayList<String> expression = this.getArray(s);
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
    

    private Table column_evaluate(ArrayList<String> e){
        Table tmp1 = field.get(e.get(3));
        String [] stringlist = e.get(4).split(", ");
        int [] tmplist = new int[stringlist.length];
        for(int i = 0; i<tmplist.length;i++){
            tmplist[i] = Integer.valueOf(stringlist[i]);
        }
        return express.columns(tmp1, tmplist);
    }
    private Table join_evaluate(ArrayList<String> e){
        Table tmp1 = field.get(e.get(3));
        Table tmp2 = field.get(e.get(4));
        String [] stringlist = e.get(5).split(", ");
        int num1 = Integer.valueOf(stringlist[0]);
        int num2 = Integer.valueOf(stringlist[0]);
        return express.join(tmp1, tmp2, num1, num2);
    }
    private Table product_evaluate(ArrayList<String> e){
        Table tmp1 = field.get(e.get(3));
        Table tmp2 = field.get(e.get(4));
        return express.product(tmp1, tmp2);
    }
    private Table naturalJoin_evaluate(ArrayList<String> e){
        Table tmp1 = field.get(e.get(3));
        Table tmp2 = field.get(e.get(4));
        return express.naturaljoin(tmp1, tmp2);
    }
    private Table rows_evaluate(ArrayList<String> e){
        Table tmp1 = field.get(e.get(3));
        int num1 = Integer.valueOf(e.get(4));
        return express.rows(tmp1, num1, e.get(5), e.get(6));
    }
    private Table orderby_evaluate(ArrayList<String> e){
        Table tmp1 = field.get(e.get(3));
        return express.orderby(tmp1, Integer.valueOf(e.get(4)));
    }
    public void save(String s){
        ArrayList<String> expression = this.getArray(s);
        String name = expression.get(0);
        Table table = field.get(expression.get(1));
        filehandler.write(table, name);
    }
    public void print(String name){
        Table tmp = field.get(name);
        String output = tmp.toString();
        System.out.println(output);
    }
    
    public void exit(){
       
    }
    
    public void define(String name, String ... type){
        ArrayList<Object> typeDef= new ArrayList<Object>();
        if (!defined.containsKey(name)){
            for(String s: type){
                if (s.equals("int")){
                    typeDef.add(1);
                }
                else{      //     String output = tmp.toString();
        System.out.println(output);type is "string"
                    typeDef.add("string");
                }
            }
            defined.put(name, typeDef);
        }
        else{
            // give and error for being already defined.
        }
    }
    
    
    
    
    
        String output = tmp.toString();
        System.out.println(output);
    
    
    public void load(Boolean headed, String name, String filename){

        try{
            FileInputStream fstream = new FileInputStream(filename);
          // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
          
        
        
        // Load the file and store in a StringBuffer.
        
            if(defined.containsKey(name)){
                
                if(headed == true){
                //create a Table with headered columns
                    
                }
            
                else{
                     //create a Table with no headers.
                    loadedTables.put(name, new Table(br, defined.get(name)));      
                }
            }
        
            else{
            //return non-defined error.
            }
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
    
    
 