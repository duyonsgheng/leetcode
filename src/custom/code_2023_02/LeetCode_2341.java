package custom.code_2023_02;

import java.util.Map;

/**
 * @ClassName LeetCode_2341
 * @Author Duys
 * @Description
 * @Date 2023/2/16 11:34
 **/
//2341. 数组能形成多少数对
public class LeetCode_2341 {
    public static int[] numberOfPairs(int[] nums) {
        int[] cnt = new int[101];
        for (int num : nums) {
            cnt[num]++;
        }
        int ans = 0;
        int o = 0;
        for (int i = 0; i < cnt.length; i++) {
            ans += cnt[i] / 2;
            o += cnt[i] % 2;
        }
        return new int[]{ans, o};
    }

    public static void main(String[] args) {
        int[] ints = numberOfPairs(new int[]{1, 1});
        System.out.println(ints[0] + " " + ints[1]);

    }
}
