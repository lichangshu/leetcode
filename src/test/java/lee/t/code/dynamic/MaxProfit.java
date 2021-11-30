package lee.t.code.dynamic;

import org.junit.Test;

public class MaxProfit {
    public enum Solution {
        loop2loop {
            @Override
            public int maxProfit(int[] prices) {
                int money = 0;
                for (int buy = 0; buy < prices.length; buy++) {
                    for (int sell = prices.length - 1; sell > buy; sell--) {
                        int v = prices[sell] - prices[buy];
                        if (money < v) {
                            money = v;
                        }
                    }
                }
                return money;
            }
        },
        loop {
            @Override
            public int maxProfit(int[] prices) {
                int money = 0;
                int min = prices[0];
                for (int i = 0; i < prices.length; i++) {
                    if (prices[i] < min) {
                        min = prices[i];
                    }
                    var v = prices[i] - min;
                    if (money < v) {
                        money = v;
                    }
                }
                return money;
            }
        },
        ;

        public abstract int maxProfit(int[] prices);
    }

    @Test
    public void test() {
        int[][] prices = {
                {7, 1, 5, 3, 6, 4},
                {7, 6, 4, 3, 1},
                {2, 1, 4},
        };
        for (Solution value : Solution.values()) {
            for (int[] price : prices) {
                System.out.println(value.maxProfit(price));
            }
        }
    }
}
