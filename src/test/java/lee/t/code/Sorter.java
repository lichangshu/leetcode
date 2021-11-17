package lee.t.code;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class Sorter {

    public enum IMPL {
        Bubble { // 冒泡

            @Override
            public void sort(int[] arr) {
                for (int k = arr.length; k > 0; k--) {
                    for (int i = 0; i < k - 1; i++) {
                        if (arr[i] > arr[i + 1]) {
                            exchange(arr, i, i + 1);
                        }
                    }
                }
            }
        }, Select {// 选择排序

            @Override
            public void sort(int[] arr) {
                for (int i = 0; i < arr.length; i++) {
                    int index = i;
                    for (int k = i + 1; k < arr.length; k++) {
                        if (arr[index] > arr[k]) {
                            index = k;
                        }
                    }
                    if (index != i) {
                        exchange(arr, i, index);
                    }
                }
            }
        }, Insert {// 插入排序

            @Override
            public void sort(int[] arr) {
                for (int i = 0; i < arr.length; i++) {
                    for (int k = 0; k < i; k++) {
                        if (arr[k] > arr[i]) {
                            int val = arr[i];
                            for (int j = i; j > k; j--) { // 准备插入, 从后向前
                                arr[j] = arr[j - 1];
                            }
                            arr[k] = val;
                        }
                    }
                }
            }
        }, Insert2loop {// 插入排序

            @Override
            public void sort(int[] arr) {
                for (int i = 0; i < arr.length; i++) {
                    int val = arr[i];
                    for (int k = i - 1; k >= 0; k--) {// 从后向前移动`
                        if (arr[k] > val) {
                            arr[k + 1] = arr[k];
                            arr[k] = val;
                        } else {
                            break;
                        }
                    }
                }
            }
        }, Shell { // 希尔排序 cap = length / 2

            @Override
            public void sort(int[] arr) {
                int cap = arr.length / 2;
                for (; cap > 0; cap = cap / 2) {
                    for (int i = cap; i < arr.length; i++) {
                        int val = arr[i];
                        for (int k = i - cap; k >= 0; k -= cap) {
                            if (arr[k] > val) {
                                arr[k + cap] = arr[k];
                                arr[k] = val;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }, Merge { // 归并排序

            @Override
            public void sort(final int[] arr) {
                int[] oragin = arr, temp = new int[arr.length];
                for (int cap = 1; cap < oragin.length; cap *= 2) {
                    for (int i = 0; i < oragin.length; ) {
                        int next = i + cap + cap;
                        merge(oragin, temp, i, i + cap, next);
                        i = next;
                    }
                    int[] o = oragin;
                    oragin = temp;
                    temp = o;
                }
                System.arraycopy(oragin, 0, arr, 0, arr.length);
            }

            // .....12341234........
            // .....|...|...|.......
            public void merge(int[] src, int[] target, int from, int left, int right) {
                left = Math.min(src.length, left);
                right = Math.min(src.length, right);
                int p1 = from, p2 = left, i = from;
                for (; p1 < left && p2 < right; i++) {
                    if (src[p1] < src[p2]) {
                        target[i] = src[p1];
                        p1++;
                    } else {
                        target[i] = src[p2];
                        p2++;
                    }
                }
                for (; p1 < left; i++) {
                    target[i] = src[p1];
                    p1++;
                }
                for (; p2 < right; i++) {
                    target[i] = src[p2];
                    p2++;
                }
            }
        }, Quick { // 快速排序, 基线的左右两侧 ! 算法不对 !

            @Override
            public void sort(int[] arr) {
                quick(arr, 0, arr.length);
            }

            /**
             *
             * @param arr
             * @param from include
             * @param to exclude
             */
            public void quick(int[] arr, int from, int to) {
                if (from + 1 >= to) {
                    return;
                }
                int base = from;
                for (int i = from; i < to; i++) {
                    if (arr[base] > arr[i]) {
                        int val = arr[i];
                        for (int k = i - 1; k >= base; k--) {
                            arr[k + 1] = arr[k];
                        }
                        arr[base] = val;
                        base++;
                    }
                }
                quick(arr, from, base);
                quick(arr, base + 1, to);
            }

        }, Quickly { // 快速排序, 两个指针向中间移动

            @Override
            public void sort(int[] arr) {
                quick(arr, 0, arr.length - 1);
            }

            /**
             *
             * @param arr
             * @param start include
             * @param over include
             */
            public void quick(int[] arr, int start, int over) {
                if (start >= over) return;
                int from = start;
                int to = over;
                int base = arr[from];
                // printArray(arr, from, to);
                while (from < to) {
                    for (; from < to; to--) {
                        if (base >= arr[to]) {
                            arr[from] = arr[to];
                            break;
                        }
                    }
                    for (; from < to; from++) {
                        if (base <= arr[from]) {
                            arr[to] = arr[from];
                            to--;
                            break;
                        }
                    }
                }
                arr[from] = base;
                quick(arr, start, from - 1);
                quick(arr, to + 1, over);
            }
        };

        public abstract void sort(int[] arr);

        public void exchange(int[] arr, int index1, int index2) {
            int val = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = val;
        }
    }

    @Test
    public void testRandomOne() {
        IMPL v = IMPL.Merge;
        int[] arr = new Random().ints(0, 99).limit(18).toArray();
        v.sort(arr);
        printArray(arr);
        assertASC(arr);
    }

    @Test
    public void testRandom() {
        for (IMPL value : IMPL.values()) {
            for (int i = 0; i < 100; i++) {
                int[] arr = new Random().ints(0, 100_000).limit(i).toArray();
                value.sort(arr);
                // printArray(arr);
                assertASC(arr);
            }
        }
    }

    @Test
    public void test() {
        for (IMPL v : IMPL.values()) {
            {
                int[] arr = {1, 34, 53, 78, 12, 456, 87, 234, 56, 90};
                printArray(arr);

                v.sort(arr);
                printArray(arr);
                assertASC(arr);
            }
            {
                int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
                v.sort(arr);
                printArray(arr);
                assertASC(arr);
            }
            {
                int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
                v.sort(arr);
                printArray(arr);
                assertASC(arr);
            }
            {
                int[] arr = {9, 9, 9, 9, 9, 9};
                v.sort(arr);
                printArray(arr);
                assertASC(arr);
            }
            {
                int[] arr = {9};
                v.sort(arr);
                printArray(arr);
                assertASC(arr);
            }
            {
                int[] arr = {};
                v.sort(arr);
                printArray(arr);
                assertASC(arr);
            }
        }
    }

    private void assertASC(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            Assert.assertTrue(arr[i] <= arr[i + 1]);
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static void printArray(int[] arr, int... from) {
        for (int i = 0; i < arr.length; i++) {
            boolean exist = false;
            for (int k : from) {
                exist = k == i;
                if (exist) {
                    break;
                }
            }
            System.out.print(String.format("%s%3d,", exist ? "*" : " ", arr[i]));
        }
        System.out.println();
    }
}
