package lee.t.code.design;

import org.junit.Assert;
import org.junit.Test;

import java.util.BitSet;

public class CountPrimes {
    // 使用标准的 埃拉托斯特尼 埃氏筛选法 -
    enum Solution {
        odd { // 奇数, 等差数列, 使用加法比乘法消耗 CPU 更少

            // 执行用时： 126 ms, 在所有 Java 提交中击败了 44.62% 的用户 内存消耗：37.8 MB, 在所有 Java 提交中击败了 98.82%的用户
            @Override
            public int countPrimes(int n) {
                if (n <= 2) return 0;
                int max = n / 2; // 只记录奇数
                boolean[] checked = new boolean[max];
                // var checked = new int[max];
                checked[0] = n <= 2;
                // i from 1, int v = i * 2 + 1, `v` from 3, 2 是素数, 这是个特例, checked[0] 标识
                for (int i = 1; i < max; i++) {
                    if (checked[i]) {
                        continue;
                    }
                    // 这里直接 count,  int count++;
                    // checked[i] = false;
                    int v = i + i + 1;
                    /**
                     * 下面使用加法
                     */
                    for (int k = v + v + v; k < n; k += v + v) {
                        int p = k / 2;
                        if (p >= max) {
                            break;
                        }
                        checked[p] = true;
                    }
                }
                int count = 0;
                for (int i = 0; i < max; i++) {
                    if (!checked[i]) {
                        //System.out.print(i * 2 + 1 + ", ");
                        count++;
                    }
                }
                //System.out.println(" ;;; " + count);
                return count;
            }
        },
        oddDirect { // 直接 count 少 for 循环一次

            // 执行用时：70 ms, 在所有 Java 提交中击败了75.40%的用户内存消耗：38.1 MB, 在所有 Java 提交中击败了98.80%的用户
            @Override
            public int countPrimes(int n) {
                if (n <= 2) return 0;
                int max = n / 2; // 只记录奇数
                boolean[] checked = new boolean[max];
                int count = 1;
                for (int i = 1; i < max; i++) {
                    if (checked[i]) {
                        continue;
                    }
                    count++;
                    // System.out.print(i * 2 + 1 + ", ");
                    int v = i + i + 1;
                    for (int k = v + v + v; k < n; k += v + v) {
                        int p = k >> 1; // 位运算 66 个测试用例 提速 20ms !
                        if (p >= max) {
                            break;
                        }
                        checked[p] = true;
                    }
                }
                // System.out.println(" ;;; " + count);
                return count;
            }
        },
        oddWithBitSet { // BitSet 内存更少, CPU 太慢

            // 执行用时：344 ms, 在所有 Java 提交中击败了5.06%的用户内存消耗：37.6 MB, 在所有 Java 提交中击败了98.93%的用户

            @Override
            public int countPrimes(int n) {
                if (n <= 1) return 0;
                int max = n / 2; // 只记录奇数
                BitSet checked = new BitSet(max);
                // var checked = new int[max];
                checked.set(0, n <= 2);
                // i from 1, int v = i * 2 + 1, `v` from 3, 2 是素数, 这是个特例, checked[0] 标识
                for (int i = 1; i < max; i++) {
                    if (checked.get(i)) {
                        continue;
                    }
                    // checked[i] = false;
                    int v = i + i + 1;
                    /**
                     * 下面使用加法
                     */
                    for (int k = v + v + v; k < n; k += v + v) {
                        int p = k / 2;
                        if (p >= max) {
                            break;
                        }
                        checked.set(p);
                    }
                }
                int count = 0;
                for (int i = 0; i < max; i++) {
                    if (!checked.get(i)) {
                        // System.out.print(i * 2 + 1 + ", ");
                        count++;
                    }
                }
                // System.out.println(" ;;; " + count);
                return count;
            }
        },
        oddWithMul { // 数列, 使用加法比乘法消耗 CPU 更少

            //执行用时：128 ms, 在所有 Java 提交中击败了43.28%的用户内存消耗：38 MB, 在所有 Java 提交中击败了98.80% 的用户
            @Override
            public int countPrimes(int n) {
                if (n <= 1) return 0;
                int max = n / 2; // 只记录奇数
                boolean[] checked = new boolean[max];
                // var checked = new int[max];
                checked[0] = n <= 2;
                // i from 1, int v = i * 2 + 1, `v` from 3, 2 是素数, 这是个特例, checked[0] 标识
                for (int i = 1; i < max; i++) {
                    if (checked[i]) {
                        continue;
                    }
                    // checked[i] = false;
                    int v = i + i + 1;
                    /**
                     * 下面使用乘法
                     */
                    for (int k = 3; k < max; k += 2) {
                        int p = v * k / 2;
                        if (p >= max) {
                            break;
                        }
                        checked[p] = true;
                    }
                }
                int count = 0;
                for (int i = 0; i < max; i++) {
                    if (!checked[i]) {
                        //System.out.print(i * 2 + 1 + ", ");
                        count++;
                    }
                }
                //System.out.println(" ;;; " + count);
                return count;
            }
        },
        loop {
            // 执行用时：160 ms, 在所有 Java 提交中击败了34.82%的用户内存消耗：42.8 MB, 在所有 Java 提交中击败了60.81%的用户
            @Override
            public int countPrimes(int n) {
                if (n <= 1) return 0;
                boolean[] checked = new boolean[n];
                checked[0] = checked[1] = true;
                for (int i = 2; i < n; i++) {
                    if (checked[i]) {
                        continue;
                    }
                    for (int p = i + i; p < n; p += i) {
                        checked[p] = true;
                    }
                }
                int count = 0;
                for (int i = 0; i < n; i++) {
                    if (!checked[i]) {
                        count++;
                    }
                }
                return count;
            }
        },
        ;

        public abstract int countPrimes(int n);
    }

    @Test
    public void test() {
        int[] ns = {0, 1, 2, 3, 4, 7, 10, 100, 100000,};
        int[] rs = {0, 0, 0, 1, 2, 3, 4, 25, 9592};
        for (Solution value : Solution.values()) {
            for (int i = 0; i < ns.length; i++) {
                System.out.println(value.name());
                Assert.assertEquals(rs[i], value.countPrimes(ns[i]));
            }
        }
    }
}
