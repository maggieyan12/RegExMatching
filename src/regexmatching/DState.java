/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexmatching;

import java.util.ArrayList;

/**
 * DState is a cached NFA state list
 * @author myan
 */
public class DState {
    
    ArrayList<State> l;
    // caches possible "next" states into an array based on input character
    DState[] next = new DState[256];
    DState left;
    DState right;
    
    public DState(ArrayList<State> l) {
        this.l = new ArrayList<State>();
        this.l.addAll(l);
    }
}
