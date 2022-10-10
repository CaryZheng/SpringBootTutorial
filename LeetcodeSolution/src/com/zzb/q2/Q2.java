package com.zzb.q2;

public class Q2 {

    static ListNode buildData(int[] params) {
        ListNode result = null;

        ListNode preNode = null;
        for (int i = 0; i < params.length; i++) {
            int value = params[i];

            ListNode listNode = new ListNode(value);

            if (null == result) {
                result = listNode;
                preNode = listNode;
            } else {
                preNode.next = listNode;
                preNode = listNode;
            }
        }

        return result;
    }

    public static void main(String[] args) {
//        int[] dataList1 = new int[]{2, 4, 3};
//        int[] dataList2 = new int[]{5, 6, 4};

        int[] dataList1 = new int[]{9, 9, 9, 9, 9, 9, 9};
        int[] dataList2 = new int[]{9, 9, 9, 9};

        // 链表1
        ListNode node11 = buildData(dataList1);

        // 链表2
        ListNode node21 = buildData(dataList2);

        ListNode reuslt = new Solution().addTwoNumbers(node11, node21);
        while(null != reuslt) {
            int value = reuslt.val;
            System.out.println(value);

            reuslt = reuslt.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/*
题目：两数相加
链接：https://leetcode.cn/problems/add-two-numbers/
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;

        int count1 = 0;
        ListNode tempL1 = l1;
        while(null != tempL1) {
            ++count1;

            tempL1 = tempL1.next;
        }

        int count2 = 0;
        ListNode tempL2 = l2;
        while(null != tempL2) {
            ++count2;

            tempL2 = tempL2.next;
        }

        if (count2 > count1) {
            ListNode tempNode= l1;
            l1 = l2;
            l2 = tempNode;
        }

        int nextCarryBit = 0;
        ListNode preNode = null;
        while(null != l1) {
            int value1 = l1.val;

            int value2 = 0;
            if(null != l2) {
                value2 = l2.val;
            }

            int bitSum = value1 + value2 + nextCarryBit;
            int bitNum = bitSum % 10;

            ListNode newNode = new ListNode();
            newNode.val = bitNum;
            if (null == preNode) {
                preNode = newNode;
                result = preNode;
            } else {
                preNode.next = newNode;
                preNode = newNode;
            }

            nextCarryBit = bitSum / 10;

            l1 = l1.next;
            if (null != l2) {
                l2 = l2.next;
            }
        }

        if (nextCarryBit > 0) {
            ListNode newNode = new ListNode();
            newNode.val = nextCarryBit;

            preNode.next = newNode;
        }

        return result;
    }
}