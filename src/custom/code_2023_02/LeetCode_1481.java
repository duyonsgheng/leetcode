package custom.code_2023_02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName LeetCode_1481
 * @Author Duys
 * @Description
 * @Date 2023/2/1 15:51
 **/
// 1481. 不同整数的最少数目
public class LeetCode_1481 {
    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int num : arr) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        List<Integer> nums = new ArrayList<>(cnt.keySet());
        Collections.sort(nums, (a, b) -> cnt.get(a).compareTo(cnt.get(b)));
        for (int num : nums) {
            int cur = cnt.get(num);
            if (k < cur) {
                break;
            }
            k -= cur;
            cnt.remove(num);
        }
        return cnt.size();
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 1, 1, 3, 3, 2};
        System.out.println(findLeastNumOfUniqueInts(arr, 3));
    }
}
