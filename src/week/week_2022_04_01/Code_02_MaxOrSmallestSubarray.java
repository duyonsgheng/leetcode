package week.week_2022_04_01;

/**
 * @ClassName Code_02_MaxOrSmallestSubarray
 * @Author Duys
 * @Description
 * @Date 2022/4/7 9:57
 **/
// 找到非负数组中拥有"最大或的结果"的最短子数组
public class Code_02_MaxOrSmallestSubarray {

    // 分析：这个题扎眼一看，就是一个动态规划，看子数组以当前位置结束，能不能往前推多远能达到 | 的结果是max
    // 其实这就是一个窗口，因为 如果我们从数组中  0 - 15 位置 能打得到max，那么不可能在 0 - 16 才得出答案
    public static int longest(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = 0;
        for (int num : arr) {
            max |= num;
        }
        if (max == 0) { // 我一个也不选，不就是0了
            return 1;
        }
        int n = arr.length;
        // ans 最大就是整个数组长度
        int ans = n;
        // 我们统计二进制位置上1出现的次数
        int[] counts = new int[32];
        int l = 0;
        int cur = 0;
        for (int r = 0; r < n; r++) {
            cur = add(counts, arr[r]);
            // 缩进一下窗口
            while (cur == max) {
                cur = delete(counts, arr[l++]);
            }
            // 从上面的饿while中出来，说明找到了一个刚刚 =max 的子数组
            if (l > 0) {
                cur = add(counts, --l);
            }
            if (cur == max) {
                ans = Math.min(r - l + 1, ans);
            }
        }
        return ans;
    }

    public static int add(int[] counts, int num) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            // num的二进制位置加到我们的counts数组中去
            counts[i] += (num & (1 << i)) != 0 ? 1 : 0;
            ans |= ((counts[i] > 0) ? 1 : 0) << i;
        }
        return ans;
    }

    public static int delete(int[] counts, int num) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            // num的二进制位置加到我们的counts数组中去
            counts[i] -= (num & (1 << i)) != 0 ? 1 : 0;
            ans |= ((counts[i] > 0) ? 1 : 0) << i;
        }
        return ans;
    }
}
