package com.zzb.q3;

public class Q3 {

    public static void main(String[] args) {
//        String s = "abcabcbb";
//        String s = "bbbbb";
        String s = "pwwkew";

        int longestSubCount = new Solution().lengthOfLongestSubstring(s);
        System.out.println(longestSubCount);
    }
}

/*
题目：无重复字符的最长子串
链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (0 == s.length()) {
            return 0;
        }

        int longestSubCount = 1;
//        String longestSubString = String.valueOf(s.charAt(0));

        StringBuilder stringBuilder = new StringBuilder();
        int count = s.length();
        for(int i = 0; i < count; i++) {
            stringBuilder.setLength(0);

            char element = s.charAt(i);

            stringBuilder.append(element);
            for (int j = i+1; j < count; j++) {
                char element2 = s.charAt(j);

                int isFind = stringBuilder.indexOf(String.valueOf(element2));
                if (-1 == isFind) {
                    // 没有重复
                    stringBuilder.append(element2);
                    if (stringBuilder.length() > longestSubCount) {
                        longestSubCount = stringBuilder.length();

//                        longestSubString = stringBuilder.toString();
                    }
                } else {
                    // 重复
                    break;
                }
            }
        }

        return longestSubCount;
    }
}
