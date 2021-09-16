package ds.patterns.dp.problems;

import java.util.Arrays;

// https://youtu.be/ZI17bgz07EE
// https://gist.github.com/SuryaPratapK/665ea0e02c1560c7d70346ed4ae794bc
public class CoinChange {

    public int coinChang(int amount){
        int[] dp = new int[amount+1];
        dp[0] = 0;  // use 0 coins to make 0 cents

        // fill up the rest of the array
        for (int i=1; i<dp.length; i++){
            dp[i] = dp[i-1] +1;

            if(i-2 >= 0){
                dp[i] = Math.min(dp[i], dp[i-2]+1);
            }

            if(i-5 >= 0){
                dp[i] = Math.min(dp[i], dp[i-5]+1);
            }
        }
        System.out.println(Arrays.asList(dp));
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChange obj = new CoinChange();
        System.out.println(obj.coinChang(5));
    }
}
