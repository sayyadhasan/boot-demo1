package com.boot.practice.demo;

public class ArrayMedian {
    public static void main(String[] args) {
        ArrayMedian arrayMedian = new ArrayMedian();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("arrayMedian.findMedianSortedArrays(nums1, nums2) = " + arrayMedian.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1.length == 0 || nums1.length > 1000) && (nums2.length == 0 || nums2.length > 1000)) {
            throw new RuntimeException("Incorrect input");
        }

        int[] mergedArray = new int[nums1.length + nums2.length];

        int i = 0;
        int nums1Counter = 0;
        int nums2Counter = 0;

        while (i < (nums1.length + nums2.length)) {

            if (nums1Counter < nums1.length) {
                if (nums1[nums1Counter] < -1000000 || nums1[nums1Counter] > 1000000) {
                    throw new RuntimeException("Incorrect input");
                }

                if (nums2Counter < nums2.length) {
                    if (nums1[nums1Counter] <= nums2[nums2Counter]) {
                        mergedArray[i] = nums1[nums1Counter];
                        nums1Counter++;
                        i++;
                    }
                } else {
                    mergedArray[i] = nums1[nums1Counter];
                    nums1Counter++;
                    i++;
                }
            }

            if (nums2Counter < nums2.length) {
                if (nums2[nums2Counter] < -1000000 || nums2[nums2Counter] > 1000000) {
                    throw new RuntimeException("Incorrect input");
                }

                if (nums1Counter < nums1.length) {
                    if (nums2[nums2Counter] < nums1[nums1Counter]) {
                        mergedArray[i] = nums2[nums2Counter];
                        nums2Counter++;
                        i++;
                    }
                } else {
                    mergedArray[i] = nums2[nums2Counter];
                    nums2Counter++;
                    i++;
                }
            }

        }



        int mergedLength = mergedArray.length;

        if (mergedLength % 2 == 0) {
            return (mergedArray[mergedLength / 2] + mergedArray[mergedLength / 2 - 1]) / 2.0;
        } else {
            return mergedArray[mergedLength / 2];

        }

    }
}
