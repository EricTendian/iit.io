import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> hills = new ArrayList<Integer>();
        in.nextLine();
        String[] hill = in.nextLine().split(" ");
        for (int i=0; i<hill.length; i++) {
            hills.add(Integer.parseInt(hill[i]));
        }
        int highest = 0;
        for (int i=0; i<hills.size(); i++) {
            int newsum = sum(hills, i);
            if (newsum>highest) highest = newsum;
        }
        System.out.println(highest);
    }

    public static int sum(ArrayList<Integer> list, int offset) {
        int sum = 0;
        for (int i=offset; i<list.size(); i++) sum += list.get(i);
        return sum;
    }

    public static void findMaxSubArray(int[] inputArray){

        int maxStartIndex=0;
        int maxEndIndex=0;
        int maxSum = Integer.MIN_VALUE;

        int cumulativeSum= 0;
        int maxStartIndexUntilNow=0;

        for (int currentIndex = 0; currentIndex < inputArray.length; currentIndex++) {

            int eachArrayItem = inputArray[currentIndex];

            cumulativeSum+=eachArrayItem;

            if(cumulativeSum>maxSum){
                maxSum = cumulativeSum;
                maxStartIndex=maxStartIndexUntilNow;
                maxEndIndex = currentIndex;
            }
            else if (cumulativeSum<0){
                maxStartIndexUntilNow=currentIndex+1;
                cumulativeSum=0;
            }
        }

        System.out.println("Max sum         : "+maxSum);
        System.out.println("Max start index : "+maxStartIndex);
        System.out.println("Max end index   : "+maxEndIndex);
    }
}