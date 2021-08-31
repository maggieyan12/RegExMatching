/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexmatching;

/**
 *
 * @author myan
 */
public class State {
    int c;
    State out1;
    State out2;
    int lastlist;
    
    public State(int c, State out1, State out2) {
        this.c = c;
        this.out1 = out1;
        this.out2 = out2;
        this.lastlist = 0;
    }
    
    public boolean match() {
        if (this.c == -1) {
            return true;
        }
        return false;
    }
}
