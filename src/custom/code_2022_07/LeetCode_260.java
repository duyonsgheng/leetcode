package custom.code_2022_07;

/**
 * @ClassName LeetCode_260
 * @Author Duys
 * @Description
 * @Date 2022/7/13 14:52
 **/
// 260. 只出现一次的数字 III
// 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
public class LeetCode_260 {

    // 解法1：hash表
    // 解法2：亦或
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int a = 0;
        int b = 0;
        int lsb = (xor == Integer.MIN_VALUE ? xor : xor & (-xor));
        for (int num : nums) {
            if ((num & lsb) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }
}
