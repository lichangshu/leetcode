package lee.t.code.design;

import java.util.Random;

public abstract class Shuffle {
    protected int[] nums;
    protected static Random r = new Random();

    public Shuffle(int[] nums) {
        this.nums = nums;
    }

    public abstract int[] reset();

    public abstract int[] shuffle();

    public static class Solution extends Shuffle {

        public Solution(int[] nums) {
            super(nums);
        }

        public int[] reset() {
            return nums;
        }

        public int[] shuffle() {
            int[] clone = nums.clone();
            for (int i = 0; i < clone.length; i++) {
                int rd = r.nextInt(clone.length);
                int v = clone[i];
                clone[i] = clone[rd];
                clone[rd] = v;
            }
            return clone;
        }
    }

}
