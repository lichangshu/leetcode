package lee.t.code.link;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public int length() {
        int i = 0;
        for (ListNode next = this; next != null; i++) {
            next = next.next;
        }
        return i;
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

    public static final ListNode createRandom(int len) {
        if (len < 1) throw new IllegalArgumentException("LINKED >= 1");
        Random random = new Random();
        ListNode head = new ListNode(random.nextInt(100));
        ListNode next = head;
        for (int i = 1; i < len; i++) {
            next.next = new ListNode(random.nextInt(100));
            next = next.next;
        }
        return head;
    }

    public static final ListNode createByArray(int... val) {
        if (val.length < 1) throw new IllegalArgumentException("LINKED >= 1");
        ListNode head = new ListNode(0);
        ListNode next = head;
        for (int i : val) {
            next.next = new ListNode(i);
            next = next.next;
        }
        return head.next;
    }
}