package duys_code.day_34;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_04_315_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/
 * @Date 2021/12/7 9:51
 **/
public class Code_04_315_LeetCode {
    // 给你一个整数数组 nums ，按要求返回一个新数组counts 。数组 counts 有该性质： counts[i] 的值是nums[i] 右侧小于nums[i] 的元素的数量。
    //

    // mergeSort过程中来实现
    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length <= 0) {
            return ans;
        }
        // 给每一个位置初始化一下
        for (int i = 0; i < nums.length; i++) {
            ans.add(0);
        }
        // 只有一个数，那比较个鸡毛啊
        if (nums.length < 2) {
            return ans;
        }
        // 参加排序的，也就是去进行排序的
        Node[] arr = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new Node(nums[i], i);
        }
        process(arr, 0, arr.length - 1, ans);
        return ans;
    }

    public static void process(Node[] arr, int l, int r, List<Integer> ans) {
        if (l == r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        process(arr, l, m, ans);
        process(arr, m + 1, r, ans);
        merge(arr, l, m, r, ans);
    }

    public static void merge(Node[] arr, int l, int m, int r, List<Integer> ans) {
        Node[] help = new Node[r - l + 1];
        // 这个是控制help怎么填的
        int end = help.length - 1;
        int i1 = m; // 从中间开始往下
        int i2 = r; // 从右边开始往下
        while (i1 >= l && i2 >= m + 1) {
            // 如果中间的是大于了右边的，算上当前一共有多少个了
            if (arr[i1].value > arr[i2].value) {
                ans.set(arr[i1].index, ans.get(arr[i1].index) + i2 - m);
            }
            // 因为是从后往前的，谁大拷贝谁
            System.out.println("e : " + end + "  i1 : " + i1 + "  i2: " + i2);
            help[end--] = arr[i1].value > arr[i2].value ? arr[i1--] : arr[i2--];
        }
        // 剩下的拷贝下来
        while (i1 >= l) {
            help[end--] = arr[i1--];
        }
        while (i2 >= m + 1) {
            help[end--] = arr[i2--];
        }
        // 最后拷贝回去
        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    public static class Node {
        public int value;
        public int index;

        public Node(int v, int i) {
            value = v;
            index = i;
        }
    }

}
