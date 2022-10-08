package custom.code_2022_10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @ClassName LeetCode_870
 * @Author Duys
 * @Description
 * @Date 2022/10/8 14:47
 **/
// 870. 优势洗牌
public class LeetCode_870 {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        TreeSet<Integer> set = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
            if (map.get(nums1[i]) == 1) {
                set.add(nums1[i]);
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            // 找到 >= nums2[i] + 1 的最小的数
            Integer ceiling = set.ceiling(nums2[i] + 1);
            if (ceiling == null) {
                ceiling = set.ceiling(-1);
            }
            ans[i] = ceiling;
            map.put(ceiling, map.get(ceiling) - 1);
            if (map.get(ceiling) == 0) {
                set.remove(ceiling);
            }
        }
        return ans;
    }

    public int[] advantageCount2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int minIndex = 0;
        boolean[] visited = new boolean[n];
        int[] ans = new int[n];
        // 对nums1排序
        Arrays.sort(nums1);
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n;
            // 二分去nums1中找到大于等于当前位置的最左侧位置
            while (left <= right) {
                int m = (left + right) >> 1;
                if (nums1[m] < nums2[i]) {
                    left = m + 1;
                } else {
                    right = m;
                }
            }
            // 如果相同，或者使用过了，往后继续
            while (left < n && (visited[left] || nums1[left] == nums2[i])) {
                left++;
            }
            // 没有找到合适的，就使用最小的来
            if (left == n) {
                while (visited[minIndex]) {
                    minIndex++;
                }
                ans[i] = nums1[minIndex];
                visited[minIndex++] = true;
            }
            // 有合适的，就用合适的，并且使用了的位置标记，防止重复用
            else {
                visited[left] = true;
                ans[i] = nums1[left];
            }
        }
        return ans;
    }

    public int[] advantageCount3(int[] nums1, int[] nums2) {
        //minIndex 是最小元素的索引
        int n = nums1.length, minIndex = 0;
        //判断num1的元素 是否使用
        boolean[] visited = new boolean[n];
        int[] ans = new int[n];
        //排序
        Arrays.sort(nums1);
        for (int i = 0; i < n; i++) {
            int left = 0, right = n;
            //二分查找 找最小的大于 num2[i]的元素
            while (left != right) {
                int mid = left + ((right - left) >> 1);
                if (nums1[mid] < nums2[i]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            //是否访问 或者 相同
            while (left < n && (visited[left] || nums1[left] == nums2[i])) {
                left++;
            }
            if (left == n) {
                //没有大于num[i]的元素，取最小的元素
                while (visited[minIndex]) {
                    minIndex++;
                }
                ans[i] = nums1[minIndex];
                visited[minIndex++] = true;
            } else {
                visited[left] = true;
                ans[i] = nums1[left];
            }
        }
        return ans;
    }
}
