
import java.util.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author c1maming
 */
public class Header extends Row{
    HashMap<String,Integer> lookup = new HashMap<String,Integer>();
    public Header(String s){
        String [] wl = s.split(",");
        this.getcontents().addAll(Arrays.asList(wl));
        this.update();
    }
    public void update(){
        this.lookup = new HashMap<String,Integer>();
        for(int i = 0; i < this.getLen();i++){
            lookup.put(this.get(i), i);
        }
    }
    public Integer find_col(String s){
        if (lookup.containsKey(s)){
            return lookup.get(s);
        } else {
            return null;
        }
    }
    public HashMap<Integer, Integer> selectcommon(Header h){
        HashMap<Integer, Integer> correlation = new HashMap<Integer, Integer>();
        for(int j = 0; j < this.getLen();j++){
            for(int i = 0; i < h.getLen();i++){
                if (this.get(i).equals(h.get(j))){
                    correlation.put(j, i);
                }
            }
        }
        return correlation;
    }
}
