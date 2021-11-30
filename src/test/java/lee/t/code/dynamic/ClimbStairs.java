package lee.t.code.dynamic;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn854d/
 */
public class ClimbStairs {
    public enum Solution {
        // f(n)=f(n-1)+f(n-2)(这里n>2)种跳法。, 这是个 `斐波那契` 数列
        recursion { // TIME out

            @Override
            public int climbStairs(int n) {
                if (n == 1) {
                    return 1;
                } else if (n == 2) {
                    return 2;
                }
                int n1 = climbStairs(n - 1);
                int n2 = climbStairs(n - 2);
                return n1 + n2;
            }
        }, recursionFibonacci {
            @Override
            public int climbStairs(int n) {
                return fibonacci(n + 1, 1, 1);
            }

            public int fibonacci(int n, int fn1, int fn2) { // 斐波那契
                if (n <= 1)
                    return fn1;
                return fibonacci(n - 1, fn2, fn1 + fn2);
            }
        },
        fibonacci {
            @Override
            public int climbStairs(int n) {
                if (n < 2) return n;
                int sum = 1;
                for (int j = 1, next = 1; j < n; j++) {
                    int v = sum;
                    sum += next;
                    next = v;
                }
                return sum;
            }
        },
        recursionCache { // TIME out
            private Map<Integer, Integer> map = new HashMap<>();

            @Override
            public int climbStairs(int n) {
                if (n == 1) {
                    return 1;
                } else if (n == 2) {
                    return 2;
                }
                Integer v = map.get(n);
                if (v == null) {
                    int n1 = climbStairs(n - 1);
                    int n2 = climbStairs(n - 2);
                    v = n1 + n2;
                    map.put(n, v);
                }
                return v;
            }
        },
        math { // 斐波那契的计算公式

            @Override
            public int climbStairs(int n) {
                double sqrt = Math.sqrt(5);
                return (int) ((Math.pow((1 + sqrt) / 2, n + 1) - Math.pow((1 - sqrt) / 2, n + 1)) / sqrt);
            }
        };

        public abstract int climbStairs(int n);
    }

    @Test
    public void test() {
        int[] sum = new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55,};
        for (Solution value : Solution.values()) {
            for (int i = 1; i < 10; i++) {
                Assert.assertEquals(sum[i], value.climbStairs(i));
            }
        }
    }
}
