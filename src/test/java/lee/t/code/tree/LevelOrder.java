package lee.t.code.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * 相关
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnldjj/
 */
public class LevelOrder {
    public enum Solution {
        width {
            @Override
            public List<List<Integer>> levelOrder(TreeNode root) {
                List<List<Integer>> res = new ArrayList<>();
                if (root == null) return res;
                ; // ;;;;
                List<TreeNode> rt = new ArrayList<>(1);
                rt.add(root);
                for (List<TreeNode> ch = rt; !ch.isEmpty(); ) {
                    List nodes = ch;
                    ch = children(ch);
                    for (int i = 0; i < nodes.size(); i++) {
                        TreeNode n = (TreeNode) nodes.get(i);
                        nodes.set(i, n.val);
                    }
                    res.add(nodes);
                }
                return res;
            }

            public List<TreeNode> children(List<TreeNode> nodes) {
                List<TreeNode> res = new ArrayList<>(nodes.size() * 2);
                for (TreeNode node : nodes) {
                    if (node.left != null) {
                        res.add(node.left);
                    }
                    if (node.right != null) {
                        res.add(node.right);
                    }
                }
                return res;
            }
        }, recursion {
            @Override
            public List<List<Integer>> levelOrder(TreeNode root) {
                List<List<Integer>> res = new ArrayList<>();
                if (root == null) return res;
                ; // ;;;;
                res.add(new ArrayList<>());
                re(res, 0, root);
                return res;
            }

            public void re(List<List<Integer>> res, int depth, TreeNode root) {
                if (root != null) {
                    res.get(depth).add(root.val);
                    if (root.left != null || root.right != null) {
                        if (res.size() <= depth + 1) {
                            res.add(new ArrayList<>());
                        }
                        re(res, depth + 1, root.left);
                        re(res, depth + 1, root.right);
                    }
                }
            }
        };

        public abstract List<List<Integer>> levelOrder(TreeNode root);
    }

    @Test
    public void testSolution() {
        List<List<Integer>> lists;
        TreeNode treeNode;
        for (Solution solution : Solution.values()) {
            lists = solution.levelOrder(null);
            Assert.assertEquals("[]", lists.toString());
            treeNode = TreeNode.create(3, 9, 20, null, null, 15, 7);
            lists = solution.levelOrder(treeNode);
            Assert.assertEquals("[[3], [9, 20], [15, 7]]", lists.toString());
        }
    }
}
