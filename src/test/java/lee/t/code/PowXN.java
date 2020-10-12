package lee.t.code;

import org.junit.Assert;
import org.junit.Test;

public class PowXN {
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        long nb = n;
        if (nb < 0) {
            x = 1 / x;
            nb = 0 - nb;
        }
        return pow(x, nb);
    }

    private double pow(double x, long n) {
        if (n > 2L * Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Max 0x7fffffff");
        }
        if (n == 0) {
            return 1;
        }
        double next = x;
        long i = 1;
        for (; ; ) {
            long t = i * 2;
            if (t > n) {
                break;
            }
            i = t;
            next *= next;
        }
        return next * pow(x, n - i);
    }

    @Test
    public void test() {
        {
            double x = 0.00001; int n = 2147483647;
            double v = this.myPow(x, n);
            Assert.assertEquals(Math.pow(x, n), v, 0.00000000000000001);
        }
        {
            double x = 1.0; int n = -2147483648;
            double v = this.myPow(x, n);
            Assert.assertEquals(Math.pow(x, n), v, 0.00000000000000001);
        }
        {
            double x = 0; int n = -5;
            double v = this.myPow(x, n);
            Assert.assertEquals(Math.pow(x, n), v, 0.00000000000000001);
        }
        {
            double x = 65; int n = 0;
            double v = this.myPow(x, n);
            Assert.assertEquals(Math.pow(x, n), v, 0.00000000000000001);
        }
        {
            double x = 65; int n = 7;
            double v = this.myPow(x, n);
            Assert.assertEquals(Math.pow(x, n), v, 0.00000000000000001);
        }
        {
            double x = 65; int n = -5;
            double v = this.myPow(x, n);
            Assert.assertEquals(Math.pow(x, n), v, 0.00000000000000001);
        }
    }
}