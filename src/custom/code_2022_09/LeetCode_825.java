package custom.code_2022_09;

import java.util.Arrays;

/**
 * @ClassName LeetCode_825
 * @Author Duys
 * @Description
 * @Date 2022/9/26 14:29
 **/
// 825. 适龄的朋友
public class LeetCode_825 {
    // 1.arr[y] <= 0.5 * arr[x] +7
    // 2.arr[y] > arr[x]
    // 3.arr[y]>100 && arr[x] < 100
    // 条件3再条件2中
    // arr[x] <arr[y]  || arr[y] <= 0.5 * arr[x] +7 找反向的
    // 0.5 * arr[x] +7 < arr[y] <= arr[x]
    // 当 arr[x] <= 14不行
    // 窗口，先顶一个窗口，然后数据有序的。
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int n = ages.length;
        int l = 0;
        int r = 0;
        int ans = 0;
        for (int a : ages) {
            if (a <= 14) continue;
            while (ages[l] <= 0.5 * a + 7) {
                l++;
            }
            while (r + 1 < n && ages[r + 1] <= a) {
                r++;
            }
            // 这样 l到r区间都是满足的
            ans += r - l;
        }
        return ans;
    }
}
