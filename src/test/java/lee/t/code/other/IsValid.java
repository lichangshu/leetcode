package lee.t.code.other;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 * <p>
 * 输入：s = "{[]}"
 * 输出：true
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 * 相关标签
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnbcaj/
 */
public class IsValid {

    enum Solution {
        loop {
            @Override
            public boolean isValid(String s) {
                LinkedList<Character> list = new LinkedList<>();
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    switch (c) {
                        case '(':
                        case '[':
                        case '{':
                            list.push(c);
                            break;
                        case ')':
                            if (Character.valueOf('(').equals(list.peek())) {
                                list.pop();
                                break;
                            } else return false;
                        case ']':
                            if (Character.valueOf('[').equals(list.peek())) {
                                list.pop();
                                break;
                            } else return false;
                        case '}':
                            if (Character.valueOf('{').equals(list.peek())) {
                                list.pop();
                                break;
                            } else return false;
                        default:
                            return false;
                    }
                }
                return list.isEmpty();
            }
        },
        ;

        abstract public boolean isValid(String s);
    }

    @Test
    public void test() {
        String[] nums = new String[]{"[][]([{}])", "[", "{}", "[(])", "([)]", "]]", "({{{{}}}))"};
        var res = new boolean[]{true, false, true, false, false, false, false};
        for (Solution value : Solution.values()) {
            for (int i = 0; i < nums.length; i++) {
                System.out.println(value.name() + ", " + nums[i]);
                Assert.assertEquals(res[i], value.isValid(nums[i]));
            }
        }
    }
}
