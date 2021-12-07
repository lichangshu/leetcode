package lee.t.code.design;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *  
 * <p>
 * 示例:
 * <p>
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *  
 * <p>
 * 提示：
 * <p>
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnkq37/
 */
public class RomanToInt {

    public enum Solution {
        array {
            // 执行用时： 2-3 ms , 在所有 Java 提交中击败了 98.43% 的用户 内存消耗： 38.5-38.6 MB , 在所有 Java 提交中击败了 62.70% 的用户
            @Override
            public int romanToInt(String s) {
                // s = s.toUpperCase();
                if (s.length() == 1) {
                    return VALUE[s.charAt(0) - 'A'];
                }
                int sum = 0;
                int i = 1;
                for (; i < s.length(); i++) {
                    int cur = VALUE[s.charAt(i) - 'A'];
                    int pre = VALUE[s.charAt(i - 1) - 'A']; // pre 可以使用一个变量, 实测 没有 性能提升
                    if (pre < cur) {
                        sum += cur - pre;
                        i++;
                    } else {
                        sum += pre;
                    }
                }
                if (i == s.length()) {
                    int cur = VALUE[s.charAt(i - 1) - 'A'];
                    sum += cur;
                }
                return sum;
            }
        },
        MAP {
            // 执行用时：3-4 ms, 在所有 Java 提交中击败了98.43%的用户内存消耗：38.3-38.6 MB, 在所有 Java 提交中击败了68.35%的用户
            @Override
            public int romanToInt(String s) {
                if (s.length() == 1) {
                    return keys.get(s.charAt(0));
                }
                int sum = 0;
                int i = 1;
                for (; i < s.length(); i++) {
                    int cur = keys.get(s.charAt(i));
                    int pre = keys.get(s.charAt(i - 1));// pre 可以使用一个变量, 实测 没有 性能提升
                    if (pre < cur) {
                        sum += cur - pre;
                        i++;
                    } else {
                        sum += pre;
                    }
                }
                if (i == s.length()) {
                    int cur = keys.get(s.charAt(i - 1));
                    sum += cur;
                }
                return sum;
            }
        },
        ;

        public abstract int romanToInt(String s);
    }

    @Test
    public void test() {
        String[] strs = {"III", "CMXCIX", "XLIX",};
        int[] sum = {3, 999, 49};
        for (Solution value : Solution.values()) {
            for (int i = 0; i < strs.length; i++) {
                Assert.assertEquals(sum[i], value.romanToInt(strs[i]));
            }
        }
    }


    private static final Map<String, Integer> map = new HashMap<>();

    //所有可能的都列出来
    static {
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);
    }

    private static final Map<Character, Integer> keys = new HashMap<>();

    //所有可能的都列出来
    static {
        keys.put('I', 1);
        keys.put('V', 5);
        keys.put('X', 10);
        keys.put('L', 50);
        keys.put('C', 100);
        keys.put('D', 500);
        keys.put('M', 1000);
    }

    //                                           A  B    C    D  E  F  G  H  I  G  K   L     M  N  O  P  Q  R  S  T  U  V  W   X  Y  Z
    private static final int[] VALUE = new int[]{0, 0, 100, 500, 0, 0, 0, 0, 1, 0, 0, 50, 1000, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 10, 0, 0};
}
