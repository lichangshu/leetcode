package lee.t.code.link;

import org.junit.Assert;
import org.junit.Test;

public class RemoveNthFromEnd {

    public enum Solution {
        oneLoop {
            @Override
            public ListNode solution(ListNode head, int n) {
                ListNode pod = null;
                ListNode current = head;
                int len = 0;
                for (; ; len++) {
                    if (current == null) {
                        break;
                    }
                    if (len == n) {
                        pod = head;
                    } else if (len > n) {
                        pod = pod.next;
                    }
                    current = current.next;
                }
                if (n > len) {
                    return head;
                }
                if (pod == null) {
                    return head.next;
                }
                pod.next = pod.next.next;
                return head;
            }
        }, recursion {
            @Override
            public ListNode solution(ListNode head, int n) {
                int v = roll(head, n);
                if (v == n) {
                    return head.next;
                }
                return head;
            }

            int roll(ListNode head, int n) {
                if (head == null) {
                    return 0;
                } else {
                    int len = roll(head.next, n);
                    if (len == n) {
                        head.next = head.next.next;
                    }
                    return len + 1;
                }
            }
        };

        public abstract ListNode solution(ListNode head, int n);
    }

    @Test
    public void printRemoveNthFromEnd() {
        ListNode head = create(10);
        head.printTails();
        head = Solution.oneLoop.solution(head, 10);
        head.printTails();

        head = create(2);
        head = Solution.recursion.solution(head, 1);
        head.printTails();
    }

    @Test
    public void testRemoveNthFromEnd() {
        for (Solution val : Solution.values()) {
            ListNode head = create(1);
            head = val.solution(head, 1);
            Assert.assertNull(head);

            head = create(5);
            head.printTails();
            head = val.solution(head, 2);
            head.printTails();
            Assert.assertFalse(head.tails().toString().contains("4"));

            head = create(2);
            head = val.solution(head, 1);
            head.printTails();
            Assert.assertEquals(1, head.val);
        }
    }

    public static final ListNode create(int len) {
        if (len < 1) throw new IllegalArgumentException("LINKED >= 1");
        ListNode head = new ListNode(1);
        ListNode next = head;
        for (int i = 1; i < len; i++) {
            next.next = new ListNode(i + 1);
            next = next.next;
        }
        return head;
    }

}
