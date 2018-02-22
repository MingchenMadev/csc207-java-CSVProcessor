/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 *
 * @author c0dongyv
 */
/*public class Column<T>{
    //String f;
    //Table t = new Table(f);
    ArrayList<T> col = new ArrayList<T>();
    public Column(){
    }
    public Column(ArrayList input_col){
        col = input_col; 
    }
    public void insert(T item){
        col.add(item);
    }
    public T get(int i){
        return col.get(i);
    }
    public int getLen(){
        return col.size();
    }
}*/
public class Column{
    ArrayList<String> contents = new ArrayList<String>();
    String name;
    public Column(){
    }
    public Column(String s){
        String [] wl = s.split(",");
        contents.addAll(Arrays.asList(wl));
    }    
    public Column(ArrayList<String> acolst){
      contents = acolst;
    }
    public void insert(String item){
        contents.add(item);
    }
    public String get(int i){
        return contents.get(i);
    }
    public int getLen(){
        return contents.size();
    }
}
