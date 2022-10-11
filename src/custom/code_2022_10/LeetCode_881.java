package custom.code_2022_10;

import java.util.Arrays;

/**
 * @ClassName LeetCode_881
 * @Author Duys
 * @Description
 * @Date 2022/10/11 10:25
 **/
// 881. 救生艇
public class LeetCode_881 {
    public int numRescueBoats(int[] people, int limit) {
        int ans = 0;
        // 排序
        Arrays.sort(people);
        int l = 0;
        int r = people.length - 1;
        while (l <= r) {
            // 从两边往中间算
            // 因为每一艘船只能最多同时坐两个人
            if (people[l] + people[r] <= limit) {
                l++;
            }
            r--;
            ans++;
        }
        return ans;
    }
}
