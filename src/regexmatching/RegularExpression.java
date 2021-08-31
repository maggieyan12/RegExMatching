/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexmatching;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author myan
 */
public class RegularExpression {

    final static int Match = -1;
    final static int Split = 267;
    static int listid;
    static ArrayList<State> l1 = new ArrayList<State>();
    static ArrayList<State> l2 = new ArrayList<State>();
    static BinaryTree  b = new BinaryTree();
    
    // converts infix to postfix 
    public static String toPost(String regex) {
         int count = 0;
         int nalt = 0;
         String postfix = "";
         Stack<Group> paren = new Stack<Group>();
         
         // inserts '.' for explicit concatenation operator
         for (int i = 0; i < regex.length(); i++) {
             switch(regex.charAt(i)) {
                 case('('):
                     if (count > 1) {
                         count--;
                         postfix += '.';
                     }
                     paren.push(new Group (count, nalt));
                     count = 0;
                     nalt = 0;
                     break;
                 case ('|'):
                     if (count == 0) {
                         return null;
                     }
                     count--;
                     while ((count) > 0) {
                         postfix += '.';
                         count--;
                     }
                     nalt++;
                     break;
                 case (')'):
                     if (count == 0) {
                         return null;
                     }
                     count--;
                     while ((count) > 0) {
                         postfix += '.';
                         count--;
                     }
                     for (int j = nalt; j > 0; j--) {
                         postfix += '|';
                     }
                     Group g1 = paren.pop();
                     nalt = g1.nalt;
                     count = g1.natom;
                     count++;
                     break;
                 case ('*'):
                 case ('+'):
                 case ('?'):
                     if (count  == 0) {
                         return null;
                     }
                     postfix += regex.charAt(i);
                     break;
                 default:
                     if (count > 1) {
                         count--;
                         postfix += ".";
                     }
                     postfix += regex.charAt(i);
                     count++;
                     break;
             }
         }
         count--;
         while ((count) > 0) {
             postfix += ".";
             count--;
         }
         for (int k = nalt; k > 0; k--) {
             postfix += '|';
         }
         return postfix;
    }
    
    // converts postfix to NFA
    public static State toNFA(String postfix) {
        
        Stack<Fragment> frag = new Stack<Fragment>();
        State s;
        
        if (postfix == null) {
            return null;
        }
        
        // process and build NFA from characters
        for (int i = 0; i < postfix.length(); i++) {
            switch(postfix.charAt(i)) {
                default:           
                    s = new State (postfix.charAt(i), null, null);
                    ArrayList<State> list1 = new ArrayList<State>();
                    list1.add(s);
                    frag.push(new Fragment(s, list1));
                    break;
                case '.':              // concatenation 
                    Fragment e2 = frag.pop();
                    Fragment e1 = frag.pop();
                    
                    for (int j = 0; j < e1.out.size(); j++) {
                        if (e1.out.get(j).c == Split) {
                            e1.out.get(j).out2 = e2.start;
                        } else {
                            e1.out.get(j).out1 = e2.start;
                        }
                    }
                    frag.push(new Fragment(e1.start, e2.out));
                    break;
                case '+':               // one or more 
                    Fragment e = frag.pop();
                    s = new State(Split, e.start, null);
                    for (int j = 0; j < e.out.size(); j++) {
                        e.out.get(j).out1 = s;
                    }
                    list1 = new ArrayList<State>();
                    list1.add(s);
                    frag.push(new Fragment(e.start, list1));
                    break;
                case '*':              // zero or more
                    e = frag.pop();
                    s = new State(Split, e.start, null);
                    for (int j = 0; j < e.out.size(); j++) {
                        e.out.get(j).out1 = s;
                    }
                    list1 = new ArrayList<State>();
                    list1.add(s);
                    frag.push(new Fragment(s, list1));
                    break;
                case '?':              // zero or one
                    e = frag.pop();
                    s = new State(Split, e.start, null);
                    list1 = new ArrayList<State>();
                    list1.add(s);
                    for (int j = 0; j < list1.size(); j++) {
                        e.out.add(list1.get(j));
                    }
                    frag.push(new Fragment(s, e.out));
                    break;
                case '|':              // alternation
                    e2 = frag.pop();
                    e1 = frag.pop();
                    s = new State(Split, e1.start, e2.start);
                    e1.out.addAll(e2.out);
                    frag.push(new Fragment(s, e1.out));
                    break;
            }
        }
        
        // adds matchstate 
        Fragment e = frag.pop();
        for (int j = 0; j < e.out.size(); j++) {
            if (e.out.get(j).out1 == null) {
                e.out.get(j).out1 = new State(Match, null, null);
            } else {
                e.out.get(j).out2 = new State(Match, null, null);
            }   
        }
        return e.start;
    }
    
    // check whether state list contains match state
    public static int isMatch(ArrayList<State> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).match()) {
                return 1;
            }
        }
        return 0;
    }
    
    // adds provided state to the list, following unlabeled arrows
    public static void addState(ArrayList<State> list, State s) {
        if (s == null || s.lastlist == listid) {
            return;
        }
        s.lastlist = listid;
        if (s.c == Split) {
            addState(list, s.out1);
            addState(list, s.out2);
            return;
        }
        list.add(s);
    }
    
    // steps through the NFA from current list of states 
    // to the next list of states the NFA will be in 
    // after processing given character
    public static void step (ArrayList<State> clist, int c, ArrayList<State> nlist) {
        State s;
        
        listid++;
        nlist.clear();
        for (int i = 0; i < clist.size(); i++) {
            s = clist.get(i);
            if (s.c == c) {
                addState(nlist, s.out1);
            }
        }
    }
    
    // creates initial state list given a state 
    public static ArrayList<State> startlist(State start, ArrayList<State> list) {
        listid++;
        addState(list, start);
        return list;
    }
    
    // converts postfix to NFA state machine
    // matches string with state machine
    // **used for testing purposes**
    public static int matchPostfix(String postfix, String string) {
        State start = toNFA(postfix);
        return matching(start, string);
    }
    
    // matches each character in string with NFA state machine
    // returns 1 if it's a match, 0 if not
    public static int matching(State start, String s) {
        int c;
        ArrayList<State> clist = startlist(start, l1);
        ArrayList<State> nlist = l2;
        ArrayList<State> t;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            step(clist, c, nlist);
            // swaps clist and nlist to reuse for next character
            t = clist;
            clist = nlist;
            nlist = t;
        }
        return isMatch(clist);
    }
    
    // steps through NFA to return corresponding DFA
    public static DState nextstate(DState d, int c) {
        step(d.l, c, l1);
        return d.next[c] = dstate(l1);
    }
    
    // corresponding DFA state to NFA start list
    public static DState startdstate(State start) {
        return dstate(startlist(start, l1));
    }
    
    // converts postfix to NFA state machine
    // returns 1 if DFA matches string, 0 if not
    // **used for testing purposes**
    public static int matchingDFA(String postfix, String s) {
        State start = toNFA(postfix);
        DState d = startdstate(start);
        return matchDFA(d, s);
    }
    
    // matches string with DFA 
    // returns 1 if match, 0 if not
    public static int matchDFA(DState start, String s) {
        int c;
        DState d = start;
        DState next;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            // computes next states as needed for cache
            if ((next = d.next[c]) == null) {
                next = nextstate(d, c);
            }
            d = next;
        }
        return isMatch(d.l);
    }
    
    // returns a dstate for any given list
    public static DState dstate(ArrayList<State> l) {
        ListComparator listcmp = new ListComparator();
        Collections.sort(l, listcmp);
        
        DState d = b.containsNode(l);
        if (d != null) {
            return d;
        }
        
        // creates a new dstate with a copy of list if necessary
        d = new DState(l);
        b.add(d.l);
        return d;
    }
    
}
