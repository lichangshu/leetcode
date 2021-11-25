package lee.t.code.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnd69e/
 */
public class MaxDepth {

    public enum Solution {
        recursion {
            @Override
            public int maxDepth(TreeNode root) {
                if (root == null) {
                    return 0;
                }
                return depth(root);
            }

            public int depth(TreeNode next) {
                if (next == null) {
                    return 0;
                }
                int left = 1 + depth(next.left);
                int right = 1 + depth(next.right);
                return Math.max(left, right);
            }
        }, depth {
            @Override
            public int maxDepth(TreeNode root) {
                return recursion.maxDepth(root);
            }
        }, width {
            @Override
            public int maxDepth(TreeNode root) {
                if (root == null) return 0;
                ; // ;;;;
                List<TreeNode> list = new ArrayList<>();
                list.add(root);
                int i = 1;
                for (int s0 = 0; ; i++) {
                    int s1 = s0;
                    s0 = list.size();
                    maxDepth(s1, list);
                    if (list.size() <= s0) {
                        break;
                    }
                }
                return i;
            }

            public void maxDepth(int from, List<TreeNode> list) {
                int size = list.size();
                for (int i = from; i < size; i++) {
                    TreeNode node = list.get(i);
                    TreeNode left = node.left;
                    if (left != null) {
                        list.add(left);
                    }
                    TreeNode right = node.right;
                    if (right != null) {
                        list.add(right);
                    }
                }
            }
        };

        public abstract int maxDepth(TreeNode root);
    }

    @Test
    public void testSolution() {
        for (Solution value : Solution.values()) {
            Assert.assertEquals(0, value.maxDepth(null));

            TreeNode treeNode;
            treeNode = TreeNode.create(3, 9, 20, null, null, 15, 7);
            Assert.assertEquals(3, value.maxDepth(treeNode));

            treeNode = TreeNode.create(-8, -6, 7, 6, null, null, null, null, 5);
            Assert.assertEquals(4, value.maxDepth(treeNode));
        }
    }
}
