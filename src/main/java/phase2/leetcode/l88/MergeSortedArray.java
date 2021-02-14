package phase2.leetcode.l88;

import java.util.Arrays;

public class MergeSortedArray {

    public static void main(String[] args) {
        Integer[] nums1 = new Integer[] {3, 5, 7};
        Integer[] nums2 = new Integer[] {2, 4, 6};
        Integer[] nums1Ext = new Integer[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, nums1Ext, 0, nums1.length);

        merge(nums1Ext, nums1.length, nums2, nums2.length);

    }

    /**
     * 1, 3, 5, 7, null, null, null
     *        (i1)
     *                        (curr)
     * 2, 4, 6
     *      (i2)
     *
     * @param nums1Ext
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(Integer[] nums1Ext, int m, Integer[] nums2, int n) {
        // System.out.println(Arrays.toString(nums1bak));
        System.out.println(Arrays.toString(nums1Ext));
        System.out.println(Arrays.toString(nums2));
        int i1 = m - 1;
        int i2 = n - 1;
        int cur = nums1Ext.length - 1;
        while (i2 >= 0) {
            if (i1 >= 0 && nums1Ext[i1] > nums2[i2]) {
                nums1Ext[cur--] = nums1Ext[i1--];
            } else {  // i1 < 0 || nums1Ext[i1] <= nums2[i2]
                nums1Ext[cur--] = nums2[i2--];
            }
        }

        System.out.println(Arrays.toString(nums1Ext));

    }
}
