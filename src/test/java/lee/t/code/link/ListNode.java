package lee.t.code.link;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void printTails() {
        System.out.println(tails());
    }

    public StringBuilder tails() {
        ListNode nx = this;
        StringBuilder sb = new StringBuilder();
        while (nx != null) {
            sb.append(nx.val).append(", ");
            nx = nx.next;
        }
        return sb;
    }

    public int[] toArray() {
        List<Integer> list = new ArrayList<>();
        ListNode nx = this;
        while (nx != null) {
            list.add(nx.val);
            nx = nx.next;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}