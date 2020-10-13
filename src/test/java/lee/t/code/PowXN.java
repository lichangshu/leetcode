package lee.t.code;

import org.junit.Assert;
import org.junit.Test;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <br />
 * https://leetcode-cn.com/problems/powx-n/
 */
public class PowXN {

    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        return pow(x, n);
    }

    private double pow(double x, int n) {
        double next = x, res = 1;
        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) {
                res *= next;
            }
            next *= next;
        }
        return n < 0 ? 1 / res : res;
    }

    @Test
    public void test() {
        {
            double x = 65;
            int n = 7;
            double v = this.myPow(x, n);
            Assert.assertEquals(Math.pow(x, n), v, 0.00000000000000001);
        }
        {
            double x = 0.00001;
            int n = 2147483647;
            double v = this.myPow(x, n);
            Assert.assertEquals(Math.pow(x, n), v, 0.00000000000000001);
        }
        {
            double x = 1.0;
            int n = -2147483648;
            double v = this.myPow(x, n);
            Assert.assertEquals(Math.pow(x, n), v, 0.00000000000000001);
        }
        {
            double x = 65;
            int n = 0;
            double v = this.myPow(x, n);
            Assert.assertEquals(Math.pow(x, n), v, 0.00000000000000001);
        }
        {
            double x = 65;
            int n = -5;
            double v = this.myPow(x, n);
            Assert.assertEquals(Math.pow(x, n), v, 0.00000000000000001);
        }
        {
            double x = 65;
            int n = 8;
            double v = this.myPow(x, n);
            Assert.assertEquals(Math.pow(x, n), v, 0.00000000000000001);
        }
    }
}