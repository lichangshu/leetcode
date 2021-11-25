package lee.t.code.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn08xg/
 * <p>
 * 树中节点数目范围在[1, 104] 内
 * -231 <= Node.val <= 231 - 1
 */
public class IsValidBST {
    public enum Solution {
        recursion {
            @Override
            public boolean isValidBST(TreeNode root) {
                return isValidBST(null, root, null);
            }

            public boolean isValidBST(TreeNode min, TreeNode cur, TreeNode max) {
                if (cur != null) {
                    if (min != null && min.val >= cur.val) {
                        return false;
                    }
                    if (max != null && max.val <= cur.val) {
                        return false;
                    }
                    {
                        TreeNode node = cur.left;
                        if (node != null) {
                            boolean depth = isValidBST(min, node, cur);
                            if (!depth) {
                                return false;
                            }
                        }
                    }
                    {
                        TreeNode node = cur.right;
                        if (node != null) {
                            boolean depth = isValidBST(cur, node, max);
                            if (!depth) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
        };

        public abstract boolean isValidBST(TreeNode root);
    }

    @Test
    public void testSolution() {
        for (Solution solution : Solution.values()) {
            Assert.assertTrue(solution.isValidBST(null));
            TreeNode treeNode;
            treeNode = TreeNode.create(2, 1, 3);
            Assert.assertTrue(solution.isValidBST(treeNode));
            treeNode = TreeNode.create(5, 1, 4, null, null, 3, 6);
            Assert.assertFalse(solution.isValidBST(treeNode));
            treeNode = TreeNode.create(5, 4, 6, null, null, 3, 7);
            Assert.assertFalse(solution.isValidBST(treeNode));
        }
    }
}
