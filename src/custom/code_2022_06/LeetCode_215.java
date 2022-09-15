package custom.code_2022_06;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_215
 * @Author Duys
 * @Description
 * @Date 2022/6/10 14:33
 **/
// 215. 数组中的第K个最大元素
// https://leetcode.cn/problems/kth-largest-element-in-an-array/
public class LeetCode_215 {

    // 使用堆来做
    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k <= 0) {
            return Integer.MAX_VALUE;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        for (int i : nums) {
            heap.add(i);
        }
        while (!heap.isEmpty() && k > 1) {
            heap.poll();
            k--;
        }
        return heap.isEmpty() ? Integer.MAX_VALUE : heap.poll();
    }

    // 使用快排方式
    public static int findKthLargest1(int[] arr, int k) {
        return process(arr, 0, arr.length - 1, arr.length - k - 1);
    }

    public static int process(int[] arr, int l, int r, int index) {
        if (l == r) {
            return arr[l];
        }
        // 随机找一个
        int p = arr[l + (int) (Math.random() * (r - l + 1))];
        int[] partition = partition(arr, l, r, p);
        if (index >= partition[0] && index <= partition[1]) {
            return arr[index];
        } else if (index > partition[1]) {
            return process(arr, partition[1] + 1, r, index);
        } else {
            return process(arr, l, partition[0] - 1, index);
        }
    }

    // 荷兰国旗问题
    public static int[] partition(int[] arr, int l, int r, int p) {
        int left = l - 1;
        int right = r + 1;
        int cur = l;
        // 三个区域
        while (cur < right) {
            if (arr[cur] > p) {
                swap(arr, --right, cur);
            } else if (arr[cur] < p) {
                swap(arr, ++left, cur++);
            } else {
                cur++;
            }
        }
        return new int[]{left + 1, right - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        // [3,2,1,5,6,4]
        //2
        int[] arr = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(findKthLargest1(arr, k));
    }
}
