package lee.t.code.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 第一个错误的版本
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * <p>
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * <p>
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnto1s/
 */
public class FirstBadVersion {

    public enum Solution {
        loop {
            @Override
            public int firstBadVersion(int n) {
                for (int from = 0, toEx = n, i = 0; ; i++) {
                    int less = toEx - from;
                    int target = from + less / 2;
                    if (from >= toEx) {
                        return from;
                    }
                    boolean v = isBadVersion(target);
                    if (v) {
                        toEx = target;
                    } else {
                        from = target + 1;
                    }
                }
            }
        };

        public abstract int firstBadVersion(int n);

        int error = 0;

        public boolean isBadVersion(int version) {
            return version >= error;
        }
    }

    @Test
    public void test() {
        for (Solution value : Solution.values()) {
            for (int i = 0; i < 10000; i++) {
                int max = i + (int) (Math.random() * i);
                value.error = (int) (Math.random() * max);
                int v = value.firstBadVersion(max);
                Assert.assertEquals(value.error, v);
            }
        }
    }

    @Test
    public void test1() {
        Assert.assertEquals(100 >> (1 + 1), 100 >> 1 + 1);
    }
}
