package custom.code_2022_06;

/**
 * @ClassName LeetCode_136
 * @Author Duys
 * @Description
 * @Date 2022/6/9 16:09
 **/
// 136. 只出现一次的数字
// 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
//说明：
//你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
//链接：https://leetcode.cn/problems/single-number
public class LeetCode_136 {

    // 位运算，
    // 任何数字和0进行亦或都是原数
    // 任务数字和自己亦或都是0
    public static int singleNumber(int[] nums) {
        int num = 0;
        for (int i : nums) {
            num ^= i;
        }
        return num;
    }
}
