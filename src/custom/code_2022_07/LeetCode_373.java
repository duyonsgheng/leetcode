package custom.code_2022_07;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_373
 * @Author Duys
 * @Description
 * @Date 2022/7/21 13:43
 **/
//373. 查找和最小的 K 对数字
// 给定两个以 升序排列 的整数数组 nums1 和 nums2,以及一个整数 k。
//定义一对值(u,v)，其中第一个元素来自nums1，第二个元素来自 nums2。
//请找到和最小的 k个数对(u1,v1), (u2,v2) ... (uk,vk)。
//链接：https://leetcode.cn/problems/find-k-pairs-with-smallest-sums
public class LeetCode_373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // int[] 0位置是nums1中的位置，1位置是nums2中的位置
        // 把和较小的排在前面
        PriorityQueue<int[]> heap = new PriorityQueue<>(k, (a, b) -> {
            return nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums1.length, m = nums2.length;
        for (int i = 0; i < Math.min(n, k); i++) {
            heap.add(new int[]{i, 0});
        }
        while (k-- > 0 && !heap.isEmpty()) {
            int[] cur = heap.poll();
            List<Integer> next = new ArrayList<>();
            next.add(nums1[cur[0]]);
            next.add(nums2[cur[1]]);
            ans.add(next);
            if (cur[1] + 1 < m) {
                heap.add(new int[]{cur[0], cur[1] + 1});
            }
        }
        return ans;
    }
}
