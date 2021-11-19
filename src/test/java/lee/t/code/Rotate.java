package lee.t.code;

// https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnhhkv/
// 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
//
//你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
//
//作者：力扣 (LeetCode)
//链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnhhkv/

//输入：matrix = [[1,2,3},{4,5,6},{7,8,9]]
//输出：[[7,4,1},{8,5,2},{9,6,3]]

//输入：matrix = [[5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16]]
//输出：[[15,13,2,5},{14,3,4,1},{12,6,8,9},{16,7,10,11]]

//输入：matrix = [[1]]
//输出：[[1]]

import org.junit.Assert;
import org.junit.Test;

//输入：matrix = [[1,2},{3,4]]
//输出：[[3,1},{4,2]]

//  1,   2,   3,   4,   5,   6,   7,   8,   9,
// 10,  11,  12,  13,  14,  15,  16,  17,  18,
// 19,  20,  21,  22,  23,  24,  25,  26,  27,
// 28,  29,  30,  31,  32,  33,  34,  35,  36,
// 37,  38,  39,  40,  41,  42,  43,  44,  45,
// 46,  47,  48,  49,  50,  51,  52,  53,  54,
// 55,  56,  57,  58,  59,  60,  61,  62,  63,
// 64,  65,  66,  67,  68,  69,  70,  71,  72,
// 73,  74,  75,  76,  77,  78,  79,  80,  81,
// ---------> 结果如下
// 73|,  64|,  55|,  46|,  37|,  28|,  19|,  10|,   1|,
// 74|,  65|,  56|,  47|,  38|,  29|,  20|,  11|,   2|,
// 75|,  66|,  57|,  48|,  39|,  30|,  21|,  12|,   3|,
// 76|,  67|,  58|,  49|,  40|,  31|,  22|,  13|,   4|,
// 77|,  68|,  59|,  50|,  41|,  32|,  23|,  14|,   5|,
// 78|,  69|,  60|,  51|,  42|,  33|,  24|,  15|,   6|,
// 79|,  70|,  61|,  52|,  43|,  34|,  25|,  16|,   7|,
// 80|,  71|,  62|,  53|,  44|,  35|,  26|,  17|,   8|,
// 81|,  72|,  63|,  54|,  45|,  36|,  27|,  18|,   9|,


public class Rotate {

    public void solutionByCopy(int[][] matrix) {
        int size = matrix.length;
        int[][] res = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res[j][size - 1 - i] = matrix[i][j];
            }
        }
        for (int i = 0; i < size; i++) {
            matrix[i] = res[i];
        }
    }

    @Test
    public void testSolutionByCopy() {
        int n = 9;
        int[][] ora = new int[9][9];
        for (int i = 0, k = 1; i < n; i++) {
            for (int j = 0; j < n; j++, k++) {
                ora[i][j] = k;
            }
        }
        print(ora);
        solutionByCopy(ora);
    }

    public void solution(int[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size - i - 1; j++) {
                // 最外面一行已经被替换了, 所以 j=i, j<size-i
                // 最后一个已经被换了 所有 size -i -1
                int p1 = matrix[i][j];
                matrix[i][j] = matrix[size - 1 - j][i];
                matrix[size - 1 - j][i] = matrix[size - 1 - i][size - 1 - j];
                matrix[size - 1 - i][size - 1 - j] = matrix[j][size - 1 - i];
                matrix[j][size - 1 - i] = p1;
            }
            print(matrix);
        }
    }

    /**
     * 找个方案是个变态
     *
     * @param matrix
     */
    public void solution45(int[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size / 2; i++) { // 上下翻转
            int[] ints = matrix[i];
            matrix[i] = matrix[size - i - 1];
            matrix[size - i - 1] = ints;
        }
        for (int i = 0; i < size; i++) { // 45度对称
            for (int j = 0; j <= i; j++) {
                int v = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = v;
            }
        }
    }

    @Test
    public void testSolution() {
        int[][] ora = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        solution(ora);
        print(ora);
        int[][] res = new int[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        for (int i = 0; i < res.length; i++) {
            Assert.assertArrayEquals(res[i], ora[i]);
        }
        ora = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        solution(ora);
        print(ora);

        ora = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        solution(ora);
        res = new int[][]{{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}};
        for (int i = 0; i < res.length; i++) {
            Assert.assertArrayEquals(res[i], ora[i]);
        }
    }

    public static void print(int[][] val) {
        for (int[] arr : val) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(String.format("%3d, ", arr[i]));
            }
            System.out.println();
        }
    }
}
