/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexmatching;

import java.util.Scanner;

/**
 *
 * @author myan
 */
public class RegExMatching {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        // user input for regular expression
        System.out.println("Enter a regular expression:");
        String inputString = s.nextLine();
        
        // converts input string into postfix with concatenation
        String postfixString = RegularExpression.toPost(inputString);

        System.out.println(postfixString);
        
        // converts postfix string into an nfa state machine
        State NFA = RegularExpression.toNFA(postfixString);
        
        // user input for string matching
        System.out.println("Enter a string: ");
        String inputString2 = s.nextLine();
        
        // matches string with the nfa state machine
        // returns 1 for match, 0 for non-match
        int output = RegularExpression.matching(NFA, inputString2);
        System.out.println(output);
        
        // caches nfa state machine into a dfa with binary tree
        // returns 1 for match, 0 for nonmatch
        int output2 = RegularExpression.matchDFA(RegularExpression.startdstate(NFA), inputString2);
        System.out.println(output2);
    }
    
}
