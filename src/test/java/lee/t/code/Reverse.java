package lee.t.code;

import org.junit.Assert;
import org.junit.Test;

public class Reverse {
    public int solution(int s) {
        int v = 0;
        while (s != 0) {
            int f = s % 10;
            v = v * 10 + f;
            s = s / 10;
        }
        return v;
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
            int v = solution(0);
            Assert.assertEquals(0, v);
        }
    }
}
