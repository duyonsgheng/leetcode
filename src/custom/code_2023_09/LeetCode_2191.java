package custom.code_2023_09;

import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2191
 * @date 2023年09月07日
 */
// 2191. 将杂乱无章的数字排序
// https://leetcode.cn/problems/sort-the-jumbled-numbers/
public class LeetCode_2191 {
    public static int[] sortJumbled(int[] mapping, int[] nums) {
        // 0 -替换后的数字
        // 1 -在数组的位置
        // 2 -原来的数字
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{split(nums[i], mapping), i, nums[i]});
        }
        int[] ans = new int[n];
        int index = 0;
        while (!queue.isEmpty()) {
            ans[index++] = queue.poll()[2];
        }
        return ans;
    }

    public static int split(int num, int[] mapping) {
        if (num == 0) {
            return mapping[0];
        }
        int len = len(num);
        int offset = 1;
        int ans = 0;
        while (--len != 0) {
            offset *= 10;
        }
        while (offset != 0) {
            int cur = (num / offset) % 10;
            ans *= 10;
            ans += mapping[cur];
            offset /= 10;
        }
        return ans;
    }

    // 98765
    // 10000
    public static int len(int num) {
        int ans = 0;
        int tmp = num;
        while (tmp != 0) {
            tmp /= 10;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        // sortJumbled(new int[]{8, 9, 4, 0, 2, 1, 3, 5, 7, 6}, new int[]{991, 338, 38});
        // [0,1,2,3,4,5,6,7,8,9] [0,999999999]
        sortJumbled(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{0, 999999999});
    }
}
