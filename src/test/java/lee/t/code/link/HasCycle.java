package lee.t.code.link;

import org.junit.Assert;
import org.junit.Test;

/**
 * 环形链表
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * 提示：
 * <p>
 * 链表中节点的数目范围是 [0, 10^4]
 * -10^5 <= Node.val <= 10^5
 * pos 为 -1 或者链表中的一个 有效索引 。
 * <p>
 * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnwzei/
 */
public class HasCycle {
    public enum Solution {
        value {
            @Override
            public boolean hasCycle(ListNode head) {
                ListNode next = head;
                while (true) {
                    if (next == null) {
                        break;
                    } else {
                        if (next.val == Integer.MAX_VALUE) {
                            return true;
                        }
                        next.val = Integer.MAX_VALUE;
                        next = next.next;
                    }
                }
                return false;
            }
        }, cycle {
            //判断链表是否有环应该是老生常谈的一个话题了，
            // 最简单的一种方式就是快慢指针，
            // 慢指针针每次走一步，
            // 快指针每次走两步，如果相遇就说明有环，如果有一个为空说明没有环。代码比较简单
            @Override
            public boolean hasCycle(ListNode head) {
                if (head == null || head.next == null) {
                    return false;
                }
                ListNode slow = head;
                ListNode fast = head.next;
                while (true) {
                    if (slow == null) {
                        break;
                    }
                    for (int i = 0; i < 2; i++) {
                        if (fast == null) {
                            return false;
                        }
                        if (slow == fast) {
                            return true;
                        }
                        fast = fast.next;
                    }
                    slow = slow.next;
                }
                return false;
            }
        };

        public abstract boolean hasCycle(ListNode head);
    }

    @Test
    public void testSolution() {
        ListNode head;
        for (Solution value : Solution.values()) {
            head = ListNode.create(100);
            Assert.assertFalse(value.hasCycle(null));
            Assert.assertFalse(value.hasCycle(head));
            head = ListNode.create(1);
            head.next = head;
            Assert.assertTrue(value.hasCycle(head));
            head = ListNode.create(2);
            head.next.next = head;
            Assert.assertTrue(value.hasCycle(head));
            head = ListNode.create(3);
            head.next.next.next = head.next;
            Assert.assertTrue(value.hasCycle(head));
        }
    }
}
