package com.zzb.q1;

public class Q1 {

    public static void main(String[] args) {
        int[] params = new int[]{3, 5, 9, 11};
        int[] result = new Solution().twoSum(params, 16);

        for(int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}

/*
题目：两数之和
链接：https://leetcode.cn/problems/two-sum/
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            int firstNum = nums[i];

            for (int j = i+1; j < nums.length; j++) {
                int secondNum = nums[j];

                int sum = firstNum + secondNum;
                if (sum == target) {
                    int[] result = new int[2];
                    result[0] = i;
                    result[1] = j;

                    return result;
                }
            }
        }

        return null;
    }
}
