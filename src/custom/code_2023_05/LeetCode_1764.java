package custom.code_2023_05;

/**
 * @ClassName LeetCode_1764
 * @Author Duys
 * @Description
 * @Date 2023/5/19 13:19
 **/
// 1764. 通过连接另一个数组的子数组得到一个数组
public class LeetCode_1764 {
    public boolean canChoose(int[][] groups, int[] nums) {
        int i = 0;
        for (int k = 0; k < nums.length && i < groups.length; ) {
            // 如果当前满足，那么k跳过当前子数组长度，
            if (check(groups[i], nums, k)) {
                k += groups[i].length;
                i++;
            } else {
                // 如果不满足，那么k去k+1的位置，匹配子数组
                k++;
            }
        }
        return i == groups.length;
    }

    public boolean check(int[] curs, int[] nums, int k) {
        if (k + curs.length > nums.length) {
            return false;
        }
        for (int i = 0; i < curs.length; i++) {
            if (curs[i] != nums[k + i]) {
                return false;
            }
        }
        return true;
    }
}
