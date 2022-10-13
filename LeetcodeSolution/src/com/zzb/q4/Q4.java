package com.zzb.q4;

public class Q4 {

    public static void main(String[] args) {

//        int[] nums1 = new int[] {1, 3, 15, 21, 31, 41};
//        int[] nums2 = new int[] {2, 30, 40, 50};
        int[] nums1 = new int[] {1, 2};
        int[] nums2 = new int[] {3, 4};
        double finalMedianValue = new Solution().findMedianSortedArrays(nums1, nums2);
        System.out.println(finalMedianValue);

//        new Solution().printTotalMergeDataForTest(nums1, nums2);
    }
}

/*
题目：寻找两个正序数组的中位数
链接：https://leetcode.cn/problems/median-of-two-sorted-arrays/
 */
class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length+nums2.length];

        int middleIndex1 = 0;
        int middleIndex2 = 0;

        int isEvenNumber = result.length % 2;
        if (0 == isEvenNumber) {
            // 偶数
            middleIndex1 = result.length / 2;
            middleIndex2 = middleIndex1 + 1;
        } else {
            // 奇数
            middleIndex1 = result.length / 2 + 1;
            middleIndex2 = middleIndex1;
        }

        Integer midlleValue1 = null;
        Integer midlleValue2 = null;

        int index = 0;
        int num1Index = 0;
        int num2Index = 0;
        while(index < result.length) {
            Integer element1 = null;
            if (num1Index < nums1.length) {
                element1 = nums1[num1Index];
            }

            Integer element2 = null;
            if (num2Index < nums2.length) {
                element2 = nums2[num2Index];
            }

            if (null != element1 && null != element2) {
                if (element1 < element2) {
                    result[index] = element1.intValue();
                    ++num1Index;
                } else {
                    result[index] = element2.intValue();
                    ++num2Index;
                }
            } else if (null != element1) {
                result[index] = element1.intValue();
                ++num1Index;
            } else if (null != element2) {
                result[index] = element2.intValue();
                ++num2Index;
            }

            if (0 == isEvenNumber) {
                // 偶数
                if (index == (middleIndex1-1)) {
                    midlleValue1 = result[index];
                } else if (index == (middleIndex2-1)) {
                    midlleValue2 = result[index];
                }

                if (null != midlleValue1 && null != midlleValue2) {
                    // 结束
                    break;
                }
            } else {
                // 奇数
                if (index == (middleIndex1-1)) {
                    midlleValue1 = result[index];
                    midlleValue2 = midlleValue1;

                    // 结束
                    break;
                }
            }

            ++index;
        }

        double finalMedianValue = (midlleValue1.intValue() + midlleValue2.intValue()) / 2.0;

        return finalMedianValue;
    }

    public void printTotalMergeDataForTest(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length+nums2.length];

        int index = 0;
        int num1Index = 0;
        int num2Index = 0;
        while(index < result.length) {
            Integer element1 = null;
            if (num1Index < nums1.length) {
                element1 = nums1[num1Index];
            }

            Integer element2 = null;
            if (num2Index < nums2.length) {
                element2 = nums2[num2Index];
            }

            if (null != element1 && null != element2) {
                if (element1 < element2) {
                    result[index] = element1.intValue();
                    ++num1Index;
                } else {
                    result[index] = element2.intValue();
                    ++num2Index;
                }
            } else if (null != element1) {
                result[index] = element1.intValue();
                ++num1Index;
            } else if (null != element2) {
                result[index] = element2.intValue();
                ++num2Index;
            }

            ++index;
        }

        printData(result);
    }

    void printData(int[] datas) {
        for (int i = 0; i < datas.length; i++) {
            System.out.print(datas[i]);
            System.out.print(" ");
        }

        System.out.println();
    }
}