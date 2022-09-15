package custom.code_2022_06;

/**
 * @ClassName LeetCode_137
 * @Author Duys
 * @Description
 * @Date 2022/6/29 17:48
 **/
// 137. 只出现一次的数字 II
// 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
public class LeetCode_137 {


    // [2,3,4,5,5,4,3,2,4,3,2,5,7]
    // 0 - 2
    // 0 0 0 0 0  0 0 0  0 0 0 0 0
    // 把每一个二进制位置上出现1的次数统计
    public static int singleNumber(int[] nums) {
        int[] count = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += num & 1;
                num >>>= 1;
            }
        }
        int ans = 0;
        int m = 3;
        for (int i = 0; i < 32; i++) {
            ans <<= 1;
            ans |= count[31 - i] % m;
        }
        return ans;
    }
}
