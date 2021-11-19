package lee.t.code.link;

//删除链表中的节点
//请编写一个函数，用于 删除单链表中某个特定节点 。在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。
//
//题目数据保证需要删除的节点 不是末尾节点 。
//
//作者：力扣 (LeetCode)
//链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnarn7/
public class DeleteNode {
    public void solution(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
        if (node.next.next != null) {
            node.next.next = null;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
