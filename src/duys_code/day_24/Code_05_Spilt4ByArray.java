package duys_code.day_24;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_05_Spilt4ByArray
 * @Author Duys
 * @Description
 * @Date 2021/11/15 14:51
 **/
public class Code_05_Spilt4ByArray {
    /**
     * 给定一个正数数组arr，长度一定大于6（>=7）
     * 一定要选3个数字做分割点，从而分出4个部分，并且每部分都有数
     * 分割点的数字直接删除，不属于任何4个部分中的任何一个。
     * 返回有没有可能分出的4个部分累加和一样大
     * 如：{3,2,3,7,4,4,3,1,1,6,7,1,5,2}
     * 可以分成{3,2,3}、{4,4}、{1,1,6}、{1,5,2}。分割点是不算的！
     */
    // 1.拉一个前缀和出来，比如当前选了i位置，前缀和是100 i位置是9，那么就找前缀和是209的位置在哪里
    // 2.根据第一步获得了两个划分点，第一个在i，第二个在 209的前缀和位置
    // 3.假设前缀和209位置的数是12，那么就需要找前缀和是 309+12的前缀和是哪个位置。然后从这个位置到最后看看是不是 这个区间的数组累加和是不是100
    public static boolean split(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int sum = arr[0];
        // 1位置的前缀和是 arr[0]
        // 2位置的前缀和是 arr[0] + arr[1]
        for (int i = 1; i < arr.length; i++) {
            map.put(sum, i);
            sum += arr[i];
        }
        int leftSum = arr[0]; // 我们的第一刀切的位置
        // 我们要划分成3部分，所以最小的长度都是4
        for (int s1 = 1; s1 < arr.length - 5; s1++) {
            int check = leftSum * 2 + arr[s1];
            if (map.containsKey(check)) {
                // 第二刀
                int s2 = map.get(check);
                check += leftSum + arr[s2];
                if (map.containsKey(check)) {
                    int s3 = map.get(check);
                    if (check + leftSum + arr[s3] == sum) {
                        return true;
                    }
                }
            }
            // 左边部分依次往后划
            leftSum += arr[s1];
        }
        return false;
    }
}
