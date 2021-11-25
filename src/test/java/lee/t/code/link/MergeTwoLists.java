package lee.t.code.link;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 提示：
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 */
public class MergeTwoLists {

    public ListNode solution(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode nx = head;
        while (true) {
            if (l1 == null && l2 == null) {
                break;
            }
            if (l2 == null || (l1 != null && l1.val <= l2.val)) {
                nx.next = l1;
                if (l2 == null) break;
                l1 = l1.next;
            } else {
                nx.next = l2;
                if (l1 == null) break;
                l2 = l2.next;
            }
            nx = nx.next;
        }
        return head.next;
    }

    @Test
    public void testSolution() {
        assertNull(solution(null, null));
        ListNode r = ListNode.create(10);
        assertEquals(10, r.length());

        assertLinkAsc(solution(r, null), 10);
        assertLinkAsc(solution(null, r), 10);

        assertLinkAsc(solution(ListNode.create(5), ListNode.create(3)), 8);
        assertLinkAsc(solution(ListNode.create(5), ListNode.create(3)), 8);
    }

    public void assertLinkAsc(ListNode head, int len) {
        ListNode next = head;
        int i = 1;
        while (next.next != null) {
            assertTrue(next.val <= next.next.val);
            next = next.next;
            i++;
        }
        assertEquals(len, i);
    }
}
