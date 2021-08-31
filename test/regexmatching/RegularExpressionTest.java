/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexmatching;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author myan
 */
public class RegularExpressionTest {
    
    public RegularExpressionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of postfix method, of class RegularExpression.
     */
    @Test
    public void testPostfix() {
        System.out.println("postfix");
        String regex = "a(bb)+a";
        String expResult = "abb.+.a.";
        String result = RegularExpression.toPost(regex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testPostfix2() {
        System.out.println("postfix");
        String regex = "a(bb)|ab+a";
        String expResult = "abb..ab+.a.|";
        String result = RegularExpression.toPost(regex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatch1() {
        System.out.println("match");
        String postfix = "a*b.";
        String string = "aaaab";
        int expResult = 1;
        int result = RegularExpression.matchPostfix(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatch2() {
        System.out.println("match2");
        String postfix = "a+";
        String string = "aa";
        int expResult = 1;
        int result = RegularExpression.matchPostfix(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatch3() {
        System.out.println("match3");
        String postfix = "a?b*.a.b.";
        String string = "abbbab";
        int expResult = 1;
        int result = RegularExpression.matchPostfix(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatch4() {
        System.out.println("match4");
        String postfix = "ab+.b*.a.";
        String string = "abbbbba";
        int expResult = 1;
        int result = RegularExpression.matchPostfix(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatch5() {
        System.out.println("match5");
        String postfix = "abb.+.a.";
        String string = "abba";
        int expResult = 1;
        int result = RegularExpression.matchPostfix(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    @Test
    public void testmatch6() {
        System.out.println("match6");
        String postfix = "abb..ab+.a.|";
        String string = "abbba";
        int expResult = 1;
        int result = RegularExpression.matchPostfix(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatch7() {
        System.out.println("match7");
        String postfix = "abb..ab+.a.|";
        String string = "abbaba";
        int expResult = 0;
        int result = RegularExpression.matchPostfix(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatch8() {
        System.out.println("match8");
        String postfix = "ab.ba.+.a.";
        String string = "abbabaaaa";
        int expResult = 0;
        int result = RegularExpression.matchPostfix(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatch9() {
        System.out.println("match9");
        String postfix = "a*b";
        String string = "aaabbb";
        int expResult = 0;
        int result = RegularExpression.matchPostfix(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatch10() {
        System.out.println("match10");
        String postfix = "a+b.ab.bb.*.|";
        String string = "aaabbb";
        int expResult = 0;
        int result = RegularExpression.matchPostfix(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatch11() {
        System.out.println("match11");
        String postfix = "a?bb.+.a.";
        String string = "aaabbbb";
        int expResult = 0;
        int result = RegularExpression.matchPostfix(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatch12() {
        System.out.println("match12");
        String postfix = "(ab)+bba";
        String string = "aaabb";
        int expResult = 0;
        int result = RegularExpression.matchPostfix(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatchDFA1() {
        System.out.println("DFA1");
        String postfix = "abb.+.a.";
        String string = "abbbba";
        int expResult = 1;
        int result = RegularExpression.matchingDFA(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatchDFA2() {
        System.out.println("DFA2");
        String postfix = "ab.?b+.";
        String string = "abb";
        int expResult = 1;
        int result = RegularExpression.matchingDFA(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatchDFA3() {
        System.out.println("DFA3");
        String postfix = "ab.a*b.|";
        String string = "b";
        int expResult = 1;
        int result = RegularExpression.matchingDFA(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testmatchDFA4() {
        System.out.println("DFA4");
        String postfix = "a+b.b+.";
        String string = "aabbbb";
        int expResult = 1;
        int result = RegularExpression.matchingDFA(postfix, string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
