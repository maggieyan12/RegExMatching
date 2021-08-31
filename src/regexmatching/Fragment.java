/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexmatching;

import java.util.ArrayList;

/**
 * Fragments are parts of the NFA state machine
 * @author myan
 */
public class Fragment {
    // contains start state and list of outputs
    State start;
    ArrayList<State> out;
    
    public Fragment(State start, ArrayList<State> out) {
        this.start = start;
        this.out = out;
    }
    
}
