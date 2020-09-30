package lee.t.code;

import org.junit.Assert;
import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * 寻找两个正序数组的中位数
 * <br/>
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * <br/>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 */
public class MedianOfTwoSortedArrays {

    /**
     * O(M+N) / O(1)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            throw new IllegalArgumentException("nums1 / nums2 length > 0");
        }
        int n1 = 0, n2 = 0;
        int max = (nums1.length + nums2.length) / 2;
        int[] v = {0, 0};
        for (int i = 1; ; i++) {
            Integer v1 = n1 < nums1.length ? nums1[n1] : null;
            Integer v2 = n2 < nums2.length ? nums2[n2] : null;
            int val;
            if (bg(v1, v2)) {
                val = nums2[n2];
                n2++;
            } else {
                val = nums1[n1];
                n1++;
            }
            if (i == max) {
                v[0] = val;
            } else if (i > max) {
                v[1] = val;
                if ((nums1.length + nums2.length) % 2 == 1) {
                    v[0] = val;
                    break;
                }
                break;
            }
        }
        return (v[0] + v[1]) / 2.0;
    }

    private static boolean bg(Integer o1, Integer o2) {
        if (o1 == null) {
            return true;
        } else if (o2 == null) {
            return false;
        } else {
            return o1 > o2;
        }
    }

    @Test
    public void test() {
        {
            double val = new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[]{1}, new int[]{2, 3, 4, 5, 6, 7});
            Assert.assertEquals(val, 4, 0.001);
        }
        {
            double val = new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[]{1}, new int[]{2, 3, 4, 5, 6, 7, 8});
            Assert.assertEquals(val, 4.5, 0.001);
        }
    }
}
