package lee.t.code.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * 将有序数组转换为二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 *
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xninbt/
 */
public class SortedArrayToBST {
    public enum Solution {
        recursion {
            @Override
            public TreeNode sortedArrayToBST(int[] nums) {
                if (nums == null || nums.length == 0) return null;
                ;// ;;;
                return sortedArrayToBST(nums, 0, nums.length);
            }

            public TreeNode sortedArrayToBST(int[] nums, int from, int toEx) {
                if (from >= toEx) return null;
                int dalt = (toEx - from) / 2;
                int index = from + dalt;
                TreeNode node = new TreeNode();
                node.val = nums[index];
                node.left = sortedArrayToBST(nums, from, index);
                node.right = sortedArrayToBST(nums, index + 1, toEx);
                return node;
            }
        };

        public abstract TreeNode sortedArrayToBST(int[] nums);
    }

    /**
     * len = 1 --> depth = 1
     *
     * @param len
     * @return
     */
    public static int depth(int len) {
        for (int i = 0; ; i++) {
            if (len == 0) {
                return i;
            }
            len = len >>> 1;
        }
    }

    public static int min2x(int len) {
        for (int i = 0, v = 0; ; i++) {
            v = v << 1 + 1;
            if (v >= len) {
                return v;
            }
        }
    }

    @Test
    public void testDepth() {
        Assert.assertEquals(0, depth(0));
        Assert.assertEquals(1, depth(1));
        Assert.assertEquals(2, depth(3));
        Assert.assertEquals(3, depth(4));
        Assert.assertEquals(8, depth(0xFF));
        Assert.assertEquals(31, depth(0x7FFFFFFF));
        Assert.assertEquals(31, depth(Integer.MAX_VALUE));
    }

    @Test
    public void testSolution() {
        TreeNode tree = Solution.recursion.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println(tree);
    }
}
