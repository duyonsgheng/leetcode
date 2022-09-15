package custom.code_2022_07;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1331
 * @Author Duys
 * @Description
 * @Date 2022/7/28 8:41
 **/
// 1331. 数组序号转换
public class LeetCode_1331 {

    //
    public static int[] arrayRankTransform(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        Arrays.sort(copy);
        Map<Integer, Integer> sort = new HashMap<>();
        int preSore = 1;
        for (int i = 0; i < copy.length; i++) {
            if (!sort.containsKey(copy[i])) {
                sort.put(copy[i], preSore++);
            }
        }
        int[] ans = new int[arr.length];
        int index = 0;
        for (int a : arr) {
            ans[index++] = sort.get(a);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {37,12,28,9,100,56,80,5,12};
        arrayRankTransform(arr);
    }
}
