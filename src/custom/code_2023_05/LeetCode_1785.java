package custom.code_2023_05;

/**
 * @ClassName LeetCode_1785
 * @Author Duys
 * @Description
 * @Date 2023/5/22 9:56
 **/
// 1785. 构成特定和需要添加的最少元素
public class LeetCode_1785 {
    // 如果sum > goal 需要添加负数
    // 如果sum < goal 需要添加正数
    // 向上取整
    public int minElements(int[] nums, int limit, int goal) {
        long pre = 0;
        for (int num : nums)
            pre += num;
        long diff = Math.abs(pre - goal);
        return (int) ((diff + limit - 1) / limit);
    }
}
