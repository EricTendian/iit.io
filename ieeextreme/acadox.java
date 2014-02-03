import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        Scanner in = new Scanner(System.in).useDelimiter(" ");
        try {
            while (in.hasNext()) {
                String s = in.next();
                if      (s.equals("+")) {
                    int c = stack.pop() + stack.pop();
                    stack.push(c > 0xFFFF ? 0xFFFF : c);
                }
                else if (s.equals("-")) {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b-a<0000 ? 0 : b-a);
                }
                else if (s.equals("&")) stack.push(stack.pop() & stack.pop());
                else if (s.equals("|")) stack.push(stack.pop() | stack.pop());
                else if (s.equals("~")) stack.push(~stack.pop());
                else if (s.equals("X")) stack.push(stack.pop() ^ stack.pop());
                else stack.push(Integer.parseInt(s, 16));
            }
            
            System.out.println(String.format("%04X", stack.pop()));
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}