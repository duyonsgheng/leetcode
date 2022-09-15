package duys_code.day_45;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * @ClassName Code_04_2035_MergeArray
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/
 * @Date 2021/10/13 13:31
 **/
public class Code_04_2035_MergeArray {
    /**
     * 题意：
     * 给你一个长度为 2 * n的整数数组。你需要将nums分成两个长度为n的数组，分别求出两个数组的和，并 最小化两个数组和之差的绝对值。nums中每个元素都需要放入两个数组之一。
     * 请你返回最小的数组和之差。
     *  提示：
     *  1 <= n <= 15
     * nums.length == 2 * n
     * -107 <= nums[i] <= 107
     */
    /**
     * 根据提示呢：可以看到我们的每一个数都很大。不能使用动态规划来做，但是我们的数组长度不大，那么我们最先想到的是二分
     */
    public static int minimumDifference(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int size = nums.length;
        int mid = size >> 1;
        //
        HashMap<Integer, TreeSet<Integer>> mapLeft = new HashMap<>();
        HashMap<Integer, TreeSet<Integer>> mapRight = new HashMap<>();
        process(nums, 0, mid, 0, 0, mapLeft);
        process(nums, mid, size, 0, 0, mapRight);
        // 我左边挑选了1个
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int ans = Integer.MAX_VALUE;
        for (int leftNum : mapLeft.keySet()) {
            // 啥意思？ 就是比如左边我挑选了2个，然后拿出所有的和 是 20
            // 因为如果我们分别放入两个数组，最好是两边都是累加和50的，那么最小的差绝对值就是0
            // 数组总共10个 总共累加和是100，那我从右边3两个，然后找最接近30的
            for (int leftSum : mapLeft.get(leftNum)) {
                // 左边拿 leftNum个，右边拿mid-leftNum个，最接近 sum>>1 - leftSum
                Integer rightNum = mapRight.get(mid - leftNum).floor((sum >> 1) - leftSum);
                if (rightNum != null) {
                    int pickSum = rightNum + leftSum;
                    int restSum = sum - pickSum;
                    ans = Math.min(ans, restSum - pickSum);
                }
            }
        }
        return ans;
    }

    // 来到index - end 这个范围取做选择，
    // pick 挑了几个数
    // 挑的这些数的累加和是多少
    // map就是记录所有的挑选个数形成的累加和分别是多少，用有序表记录下来
    // 比如当前arr ->   0 1 2 3      4 5 6 7
    // [0,4)和[4,8)
    // 最后一个位置作为base case的判断条件
    public static void process(int[] arr, int index, int end, int pick, int sum, HashMap<Integer, TreeSet<Integer>> map) {
        if (index == end) {
            if (!map.containsKey(pick)) {
                map.put(pick, new TreeSet<>());
            }
            map.get(pick).add(sum);
        } else {
            // 就两种选择，当前位置要和不要
            process(arr, index + 1, end, pick, sum, map);
            process(arr, index + 1, end, pick + 1, sum + arr[index], map);
        }

    }
}
