package lee.t.code;

import org.junit.Assert;
import org.junit.Test;

public class ReverseString {
    public void solution(char[] s) {
        int f = 0;
        int t = s.length - 1;
        for (; f < t; f++, t--) {
            char c1 = s[f];
            s[f] = s[t];
            s[t] = c1;
        }
    }
    @Test
    public void test(){
        char[] chars = "0123456789".toCharArray();
        solution(chars);
        Assert.assertArrayEquals("9876543210".toCharArray(), chars);
    }
}
