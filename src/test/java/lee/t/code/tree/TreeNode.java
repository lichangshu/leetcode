package lee.t.code.tree;

import org.junit.Assert;
import org.junit.Test;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode create(Integer... v) {
        TreeNode node = new TreeNode(v[0]);
        create(node, 1, v);
        return node;
    }

    public int size() {
        return length(this);
    }

    public static int length(TreeNode node) {
        if (node == null) return 0;
        ;// ;;;;
        int i = length(node.left);
        int r = length(node.right);
        return i + r + 1;
    }

    public static void create(TreeNode node, int index, Integer[] values) {
        if (index < values.length) {
            Integer value = values[index];
            if (value != null) {
                node.left = new TreeNode(value);
                create(node.left, 2 * index + 1, values);
            }
        }
        index++;
        if (index < values.length) {
            Integer value = values[index];
            if (value != null) {
                node.right = new TreeNode(value);
                create(node.right, 2 * index + 1, values);
            }
        }
    }

    public void array(int[] v, int i) {
        v[i] = this.val;
    }

    /**
     * from 1
     *
     * @return
     */
    public int depth() {
        return MaxDepth.Solution.recursion.maxDepth(this);
    }

    public static StringBuilder toArray(TreeNode node, int depth, StringBuilder sb) {
        return null;
    }

    public void printTree() {
//             0
//            / \
//           /   \
//          0     0
//         / \   / \
//        0   0 0   0
    }

    public static class TestThis {

        @Test
        public void testCreate() {
            TreeNode treeNode = TreeNode.create(3, 9, 20, null, null, 15, 7);
            Assert.assertEquals(15, treeNode.right.left.val);
            Assert.assertEquals(7, treeNode.right.right.val);


            treeNode.printTree();
        }
    }
}