package lee.t.code.link;

import org.junit.Assert;
import org.junit.Test;

public class IsPalindrome {
    public enum Solution {
        loop2loop { // timeout
            @Override
            public boolean isPalindrome(final ListNode head) {
                int len = 0;
                for (ListNode next = head; next != null; len++) {
                    next = next.next;
                }
                ListNode next = head;
                boolean hw = true;
                for (int i = 0; next != null; i++) {
                    ListNode back = next;
                    for (int j = i; j < len - i - 1; j++) {
                        back = back.next;
                    }
                    if (next.val != back.val) {
                        hw = false;
                        break;
                    }
                    next = next.next;
                }
                return hw;
            }
        }, revert { // 反转链表 // 86.63%,77.37%

            @Override
            public boolean isPalindrome(final ListNode head) {
                int len = 0;
                for (ListNode next = head; next != null; len++) {
                    next = next.next;
                }
                int p = len / 2;
                ListNode next = head;
                for (int i = 0; i < p; i++) {
                    next = next.next;
                }

                // 反转链表
                ListNode h2 = next;
                ListNode nx = h2.next;
                h2.next = null;
                while (true) {
                    if (nx == null) {
                        break;
                    }
                    ListNode val = nx.next;
                    nx.next = h2;
                    h2 = nx;
                    nx = val;
                }

                // 比较两个链表
                ListNode n1 = head;
                ListNode n2 = h2;
                boolean res = true;
                while (true) {
                    if (n1 == null || n2 == null) {
                        break;
                    }
                    if (n1.val != n2.val) {
                        res = false;
                        break;
                    }
                    n1 = n1.next;
                    n2 = n2.next;
                }
                return res;
            }
        };

        public abstract boolean isPalindrome(ListNode head);

    }


    @Test
    public void testSolution() {
        ListNode nodes;
        nodes = ListNode.createByArray(1, 2, 3, 4, 5, 4, 3, 2, 1);
        Assert.assertTrue(Solution.revert.isPalindrome(nodes));
        nodes = ListNode.createByArray(1, 2, 3, 4, 5, 6, 3, 2, 1);
        Assert.assertFalse(Solution.revert.isPalindrome(nodes));
        nodes = ListNode.createByArray(1, 2, 3, 4, 4, 3, 2, 1);
        Assert.assertTrue(Solution.revert.isPalindrome(nodes));
        nodes = ListNode.createByArray(1);
        Assert.assertTrue(Solution.revert.isPalindrome(nodes));
    }
}
