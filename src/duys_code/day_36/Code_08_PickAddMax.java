package duys_code.day_36;

import java.util.Arrays;

/**
 * @ClassName Code_08_PickAddMax
 * @Author Duys
 * @Description
 * @Date 2021/12/14 13:11
 **/
public class Code_08_PickAddMax {
    // 来自腾讯
    // 给定一个数组arr，当拿走某个数a的时候，其他所有的数都+a
    // 请返回最终所有数都拿走的最大分数
    // 比如: [2,3,1]
    // 当拿走3时，获得3分，数组变成[5,4]
    // 当拿走5时，获得5分，数组变成[9]
    // 当拿走9时，获得9分，数组变成[]
    // 这是最大的拿取方式，返回总分17

    // 思路就是先拿最大的
    // 比如  1 2 3 4 5
    //5拿出 6 7 8 9
    //9拿出 15 16 17
    //17拿出 32 33
    //33 拿出 65
    public static int pick(int[] arr) {
        Arrays.sort(arr);
        int ans = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            ans += (ans << 1) + arr[i];
        }
        return ans;
    }
}
