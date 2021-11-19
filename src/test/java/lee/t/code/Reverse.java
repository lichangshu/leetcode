package lee.t.code;

import org.junit.Assert;
import org.junit.Test;

public class Reverse {

    public int solution(int x) {
        int v = 0;
        while (x != 0) {
            int f = x % 10;
            int next = v;
            v = v * 10 + f;
            if ((v - f) / 10 != next) {
                return 0;
            }
            x = x / 10;
        }
        return v;
    }

    public int solutionByOverflow(int x) {
        int v = 0;
        while (x != 0) {
            int f = x % 10;
            if (overflow(v, f)) {
                return 0;
            }
            v = v * 10 + f;
            x = x / 10;
        }
        return v;
    }

    public boolean overflow(int mul, int v) {
        if (mul < 0) {
            return overflowMin(mul, v);
        } else {
            return overflowMax(mul, v);
        }
    }

    private boolean overflowMax(int mul, int v) {
        int max = Integer.MAX_VALUE / 10;
        if (mul < max) {
            return false;
        } else if (mul == max) {
            return v > Integer.MAX_VALUE % 10;
        }
        return true;
    }

    private boolean overflowMin(int mul, int v) {
        int max = Integer.MIN_VALUE / 10;
        if (mul > max) {
            return false;
        } else if (mul == max) {
            return v < Integer.MIN_VALUE % 10;
        }
        return true;
    }

    @Test
    public void test() {
        {
            int v = solution(120);
            Assert.assertEquals(21, v);
        }
        {
            int v = solution(1000000);
            Assert.assertEquals(1, v);
        }
        {
            int v = solution(1234);
            Assert.assertEquals(4321, v);
        }
        {
            int v = solution(123);
            Assert.assertEquals(321, v);
        }
        {
            int v = solution(123456789);
            Assert.assertEquals(987654321, v);
        }
        {
            int v = solution(-123);
            Assert.assertEquals(-321, v);
        }
        {
            Assert.assertEquals(0, solution(Integer.MIN_VALUE));
            Assert.assertEquals(0, solution(Integer.MAX_VALUE));
        }
    }
}
