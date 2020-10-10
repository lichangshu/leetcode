package lee.t.code;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DivideTwoIntegers {

    // divisor >> i , dividend >> i

    /**
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        long val = positive(Math.abs((long) dividend), Math.abs((long) divisor));
        if ((dividend ^ divisor) < 0) {
            val = 0 - val;
        }
        if (val > Integer.MAX_VALUE || val < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) val;
    }

    public long positive(long dividend, long divisor) {
        if (dividend < divisor) {
            return 0;
        }
        int mv = 1;
        for (int i = 0; ; i++) {
            long v = divisor << i;
            if (dividend < v) {
                break;
            }
            mv = i;
        }
        long val = 1L << mv;
        return val + positive(dividend - (divisor << mv), divisor);
    }

    @Test
    public void test1() {
        Demo2 d = new Demo2();
        {
            int d1 = Integer.MIN_VALUE, d2 = -1;
            int v = d.divide(d1, d2);
            Assert.assertEquals(Integer.MAX_VALUE, v);
        }
        {
            int d1 = 1651497, d2 = -156;
            int v = d.divide(d1, d2);
            Assert.assertEquals(d1 / d2, v);
        }
    }

    /**
     * over time !
     */
    public static class Demo2 {

        public int divide(int dividend, int divisor) {
            long val = positiveSum(Math.abs((long) dividend), Math.abs((long) divisor));
            if ((dividend ^ divisor) < 0) {
                val = 0 - val;
            }
            if (val > Integer.MAX_VALUE || val < Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            return (int) val;
        }

        public long positiveSum(long dividend, long divisor) {
            long count = 0;
            for (long i = 1, sum = 0; ; i++) {
                sum += divisor;
                if (dividend < sum) {
                    break;
                }
                count = i;
            }
            return count;
        }
    }

    @Test
    public void test2() {
        {
            int d1 = Integer.MIN_VALUE, d2 = -1;
            int v = this.divide(d1, d2);
            Assert.assertEquals(Integer.MAX_VALUE, v);
        }
        {
            int d1 = 1651497, d2 = -156;
            int v = this.divide(d1, d2);
            Assert.assertEquals(d1 / d2, v);
        }
    }

}
