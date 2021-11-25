package lee.t.code.link;

import org.junit.Assert;
import org.junit.Test;

public class ReverseList {
    public enum Solution {
        loop {
            @Override
            public ListNode reverseList(ListNode head) {
                if (head == null) {
                    return null;
                }
                ListNode next = head.next;
                ListNode curr = head;
                curr.next = null;
                while (true) {
                    if (next == null) {
                        return curr;
                    }
                    ListNode nx = next.next;
                    next.next = curr;
                    curr = next;
                    next = nx;
                }
            }
        }, recursion {
            @Override
            public ListNode reverseList(ListNode head) {
                if (head == null) {
                    return null;
                }
                ListNode nx = head.next;
                head.next = null;
                return recursion(head, nx);
            }

            public ListNode recursion(ListNode curr, ListNode next) {
                if (next != null) {
                    ListNode n = next.next;
                    next.next = curr;
                    return recursion(next, n);
                }
                return curr;
            }
        };

        public abstract ListNode reverseList(ListNode head);
    }

    @Test
    public void testReverseList() {
        for (int i = 1; i < 10; i++) {
            var head = ListNode.create(i);
            head = Solution.recursion.reverseList(head);
            int[] res = new int[i];
            for (int j = 0; j < i; j++) {
                res[j] = i - j;
            }
            Assert.assertArrayEquals(res, head.toArray());
        }
    }
}
