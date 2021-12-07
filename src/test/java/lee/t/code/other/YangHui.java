package lee.t.code.other;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <p>
 * <p>
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 示例 2:
 * <p>
 * 输入: numRows = 1
 * 输出: [[1]]
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= numRows <= 30
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xncfnv/
 */
public class YangHui {
    enum Solution {
        loop {
            @Override
            public List<List<Integer>> generate(int numRows) {
                List<List<Integer>> res = new ArrayList<>(numRows);
                List<Integer> last = Collections.singletonList(1);
                res.add(last);
                for (int i = 1; i < numRows; i++) {
                    List<Integer> r = new ArrayList<>(i + 1);
                    r.add(1);
                    for (int j = 1; j < i; j++) {
                        r.add(last.get(j) + last.get(j - 1));
                    }
                    r.add(1);
                    res.add(r);
                    last = r;
                }
                return res;
            }
        },
        ;

        abstract public List<List<Integer>> generate(int numRows);
    }

    @Test
    public void test() {
        int[] nums = new int[]{5, 1};
        String[] res = new String[]{"[[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1]]", "[[1]]"};
        for (Solution value : Solution.values()) {
            for (int i = 0; i < nums.length; i++) {
                Assert.assertEquals(res[i], value.generate(nums[i]).toString());
            }
        }
    }
}
