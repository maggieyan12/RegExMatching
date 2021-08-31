/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexmatching;

import java.util.ArrayList;

/**
 * BinaryTree is used to store DFA states using ArrayList keys
 * @author myan
 */
public class BinaryTree {
    
    DState root;
    
    // compares two arraylists(keys)
    // based on length then elements
    public static int compare(ArrayList<State> a1, ArrayList<State> a2) {
        if (a1.size() < a2.size()) {
            return -1;
        }
        if (a1.size() > a2. size()) {
            return 1;
        }
        for (int i = 0; i < a1.size(); i++) {
            if (a1.get(i).hashCode() < a2.get(i).hashCode()) {
                return -1;
            }
            if (a2.get(i).hashCode() > a2.get(i).hashCode()) {
                return 1;
            }
        }
        return 0;
    }
    
    // search and inserts dstate into the proper
    // location based on arraylist (key)
    private DState addRecursive(DState current, ArrayList<State> value) {
        if (current == null) {
            return new DState(value);
        }
        
        if (compare(value, current.l) < 0) {
            current.left = addRecursive(current.left, value);
        } else if (compare(value, current.l) > 0) {
            current.right = addRecursive (current.right, value);
        } else {
            return current;
        }
        
        return current;
    }
    
    // public method for addRecursive 
    // starting at root node
    public void add(ArrayList<State> value) {
        root = addRecursive(root, value);
    }
    
    // search for specific node within binary tree
    // returns value at node
    private DState findNodeRecursive(DState current, ArrayList<State> value) {
        if (current == null) {
            return null;
        }
        
        if (value.equals(current.l)) {
            return current;
        }
        
        return compare(value, current.l) < 0
                ? findNodeRecursive(current.left, value)
                : findNodeRecursive(current.right, value);
    }
    
    // node search starting from root node
    public DState containsNode(ArrayList<State> value) {
        return findNodeRecursive(root, value);
    }
    
}
