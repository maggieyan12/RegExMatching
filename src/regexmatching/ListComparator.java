/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexmatching;

import java.util.Comparator;

/**
 *
 * @author myan
 */
public class ListComparator implements Comparator<State>{
    
    // compares two state objects based on hashcode
    // used to sort each arraylist of states
    @Override
    public int compare(State s1, State s2) {
        return Integer.compare(s1.hashCode(), s2.hashCode());
    }
}
