import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String input = in.next();
		
		int a = Integer.parseInt(input.substring(0,input.indexOf(',')));
		int b = Integer.parseInt(input.substring(input.indexOf(',')+1));
		
		int counter = 0;
		for(int i = a; i <= b; i++) {
			if(palindrome(binary(i))) {
				counter++;
			}
		}
		System.out.print(counter);
    }
	
	
	public static String binary(int a) {
		if (a == 0) {
            return "0"; 
        }

        final StringBuilder result = new StringBuilder();
        int current = a;

        while (current != 0) {
            result.append(current & 0x1);
            current = current >> 1;
        }

        return result.reverse().toString();
		
	}
	
	public static boolean palindrome(String number) {
		char[] input = number.toCharArray();
		int length = number.length();
		char[] original = Arrays.copyOf(input, length);

	    char temp;

	    for (int i = 0; i < length / 2; i++) 
	    {
	        temp = input[i];
	        input[i] = input[length - 1 - i];
	        input[length - 1 - i] = temp;
	    }
	    return Arrays.equals(original, input);
	}
}