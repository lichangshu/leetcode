package lee.t.code.design;

import org.junit.Assert;
import org.junit.Test;

public class MinStack {
    private Note top = null;

    public MinStack() {
    }

    public void push(int val) {
        top = new Note(val, top);
    }

    public void pop() {
        this.top = this.top.down;
    }

    public int top() {
        return this.top.val;
    }

    public int getMin() {
        return this.top.min.val;
    }

    public static class Note {
        final int val;
        Note down;
        Note min;

        public Note(int val, Note down) {
            this.val = val;
            this.down = down;
            if (down == null || down.min.val > this.val) {
                this.min = this;
            } else this.min = down.min;
        }
    }

    @Test
    public void test() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        Assert.assertEquals(-3, minStack.getMin());
        minStack.pop();
        Assert.assertEquals(0, minStack.top());
        Assert.assertEquals(-2, minStack.getMin());
    }
}
