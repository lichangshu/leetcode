package lee.t.code.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <pre>
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * </pre>
 * <p>
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <pre>
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *  </pre>
 * <p>
 * 进阶：
 * <p>
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn7ihv/
 */
public class IsSymmetric {
    public enum Solution {
        recursion {
            @Override
            public boolean isSymmetric(TreeNode root) {
                if (root == null) return true;
                ;//;;;;
                return isSymmetric(root.left, root.right);
            }

            public boolean isSymmetric(TreeNode left, TreeNode right) {
                if (left == right) {
                    return true;
                } else if (left == null || right == null) return false;
                ;//;;;
                if (left.val != right.val) {
                    return false;
                }
                boolean sym = isSymmetric(left.left, right.right);
                if (sym) {
                    sym = isSymmetric(left.right, right.left);
                }
                return sym;
            }
        }, width {
            @Override
            public boolean isSymmetric(TreeNode root) {
                if (root == null) return true;
                ;//;;;;
                List<TreeNode> list = new ArrayList<>();
                list.add(root);
                for (int size = 0; ; ) {
                    var i = size;
                    size = list.size();
                    boolean symmetric = symmetric(i, list);
                    if (symmetric) {
                        break;
                    }
                    boolean v = hw(size, list);
                    if (!v) {
                        return false;
                    }
                }
                return true;
            }

            public boolean symmetric(int from, List<TreeNode> list) {
                int size = list.size();
                boolean nul = true;
                for (int i = from; i < size; i++) {
                    TreeNode node = list.get(i);
                    if (node == null) node = EMPTY;
                    ;// ;;;;
                    list.add(node.left);
                    list.add(node.right);
                    if (node.left != null) nul = false;
                    if (node.right != null) nul = false;
                }
                return nul;
            }

            boolean hw(int from, List<TreeNode> list) {
                int k = list.size() - 1;
                for (int i = from; i < k; i++, k--) {
                    TreeNode n = list.get(i);
                    TreeNode r = list.get(k);
                    if (n == null) {
                        if (r != null) {
                            return false;
                        }
                    } else {
                        if (r == null) {
                            return false;
                        } else if (n.val != r.val) {
                            return false;
                        }
                    }
                }
                return true;
            }
        };

        private static final TreeNode EMPTY = new TreeNode();

        public abstract boolean isSymmetric(TreeNode root);
    }

    @Test
    public void testSolution() {
        for (Solution value : Solution.values()) {
            Assert.assertTrue(value.isSymmetric(null));
            TreeNode treeNode;
            treeNode = TreeNode.create(1, 2, 2, 3, 4, 4, 3);
            Assert.assertTrue(value.isSymmetric(treeNode));

            treeNode = TreeNode.create(1, 2, 2, null, 3, null, 3);
            Assert.assertFalse(value.isSymmetric(treeNode));

            treeNode = TreeNode.create(1, 2, 2, null, 3, 3, null);
            Assert.assertTrue(value.isSymmetric(treeNode));
        }
    }
}
