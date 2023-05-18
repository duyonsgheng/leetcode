package week.week_2023_05_02;

import java.util.Arrays;

/**
 * @ClassName Code_04_LeetCode_698
 * @Author Duys
 * @Description
 * @Date 2023/5/15 14:36
 **/
// 698. 划分为k个相等的子集
public class Code_04_LeetCode_698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        return partitionK(new int[k], sum / k, nums, nums.length - 1);
    }

    // 基于桶排序
    public static boolean partitionK(int[] packet, int target, int[] nums, int index) {
        if (index < 0) {
            return true;
        }
        int num = nums[index];
        int len = packet.length;
        for (int i = 0; i < len; i++) {
            // 没超过
            if (packet[i] + num <= target) {
                packet[i] += num;
                // 后续可以完成，那么有效的
                if (partitionK(packet, target, nums, index - 1)) {
                    return true;
                }
                packet[i] -= num;
                // 排掉哪些相同值的桶
                while (i + 1 < len && packet[i] == packet[i + 1]) i++;
            }
        }
        return false;
    }
}
