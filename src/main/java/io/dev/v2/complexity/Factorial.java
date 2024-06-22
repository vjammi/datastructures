package io.dev.v2.complexity;

import java.util.ArrayList;
import java.util.List;

public class Factorial {

    public static void main(String[] args) {
        Factorial obj = new Factorial();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> chosen = new ArrayList<>();
        obj.factorial(4, chosen, result);
        System.out.println(result.size() +" "+ result);
    }
    void factorial(int n, List<Integer> chosen, List<List<Integer>> result){
        String indent = getIndent(n);
        if (n == 1) {
            System.out.println(indent +n +" OUT " +chosen);
            result.add(new ArrayList(chosen));
            return;
        }

        for (int i=0; i<n; i++){                    // Within the for loop we iterate n times.
            chosen.add(i);
            factorial(n - 1, chosen, result);             // within the fir loop we make multiple recursive calls
            System.out.println(indent +n +" L(" +i +"-"+ i +") " +chosen);
            chosen.remove(chosen.size() -1);
            System.out.println(indent +n +" R(" +i +"-"+ i +") " +chosen);
        }
    }

    public String getIndent(int N) {
        String S = "";
        for (int i = 0; i < N; i++)
            S = S + "   > ";
        return S;
    }
}
