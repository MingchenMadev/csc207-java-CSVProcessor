/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author c1maming
 */
import java.util.*;
public class Type extends Row{
    HashMap<String,Integer> lookup = new HashMap<String,Integer>();
    public Type(String s){
        String [] wl = s.split(",");
        contents.addAll(Arrays.asList(wl));
        this.update();
    }
    public void update(){
        this.lookup = new HashMap<String,Integer>();
        for(int i = 0; i < this.getLen();i++){
            lookup.put(this.get(i), i);
        }
    }

}

