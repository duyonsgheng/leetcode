package custom.code_2022_12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName LeetCode_1333
 * @Author Duys
 * @Description
 * @Date 2022/12/12 15:28
 **/
// 1333. 餐厅过滤器
public class LeetCode_1333 {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<int[]> list = new ArrayList<>();
        for (int[] arr : restaurants) {
            if ((arr[2] == 1 || veganFriendly == 0) && arr[3] <= maxPrice && arr[4] <= maxDistance) {
                list.add(new int[]{arr[0], arr[1]});
            }
        }
        Collections.sort(list, (a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]);
        List<Integer> ans = new ArrayList<>();
        for (int[] arr : list) {
            ans.add(arr[0]);
        }
        return ans;
    }
}
