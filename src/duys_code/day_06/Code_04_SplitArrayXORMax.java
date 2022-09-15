package duys_code.day_06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_04_SplitArrayXORMax
 * @Author Duys
 * @Description
 * @Date 2021/9/27 15:30
 **/
public class Code_04_SplitArrayXORMax {
    /**
     * 题意：
     * 网易原题：牛客网的
     * 数组中所有数都异或起来的结果，叫做异或和
     * 给定一个数组arr，可以任意切分成若干个不相交的子数组
     * 其中一定存在一种最优方案，使得切出异或和为0的子数组最多
     * 返回这个最多数量
     */
    // 采用暴力的解答 - O(2^n)
    public static int splitGetMax(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int N = arr.length;
        // 前缀异或和
        int[] eor = new int[N];
        eor[0] = arr[0];
        for (int i = 1; i < N; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        return process1(eor, 0, new ArrayList<>());
    }

    // 暴力解答方法描述
    // eor 前缀和
    // index 当前来到哪一个位置了，index 是决定之前的划分位置，。就是index决定之前得位置结算不结算？
    // parts 表示之前已经划分的位置，
    public static int process1(int[] eor, int index, ArrayList<Integer> parts) {
        int ans = 0;
        // 这已经是最后一个位置了，必须得结算之前得了
        if (index == eor.length) {
            // 深度优先遍历
            parts.add(index);
            ans = eorByParts(eor, parts);
            // 深度优先遍历需要清除现场
            parts.remove(parts.size() - 1);
        }
        // 还有位置，就可以使用可能性
        else {
            // 当前位置不进行结算
            int p1 = process1(eor, index + 1, parts);
            // 当前位置要结算了
            parts.add(index);
            int p2 = process1(eor, index + 1, parts);
            parts.remove(parts.size() - 1);
            ans = Math.max(p1, p2);
        }
        return ans;
    }

    public static int eorByParts(int[] eor, ArrayList<Integer> parts) {
        int l = 0;
        int ans = 0;
        for (int end : parts) {
            if ((eor[end - 1] ^ (l == 0 ? 0 : eor[l - 1])) == 0) {
                ans++;
            }
            l = end;
        }
        return ans;
    }


    // O(N)
    // 假设答案法：假设我得答案是某一个样子，然后看看最后一个位置是什么情况，其他位置又满足什么样得性质

    /**
     * 可能性1：比如当前来到第 23 位置。0 - 17得异或和是100 0-23的异或和也是100，那么这种情况 0-17 , 18-23是一种有效的分割
     * 那么0-17之间还有没有分割，那是17位置的时候应该做的事，我们只需要考虑当前位置的可能性
     * 可能性2：当前的位置已经是0了，那么就看前一个位置的答案是啥，我和前一个位置保持一致，因为在我这个位置上已经切分不出
     */
    public static int maxXor(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[N];

        // 这个map的含义，保存上一次相同异或和的最后出现位置，也就是离当前位置最近的位置
        Map<Integer, Integer> indexMap = new HashMap<>();
        indexMap.put(0, -1);
        int xor = 0;
        for (int i = 0; i < N; i++) {
            xor ^= arr[i];

            // 可能性1
            if (indexMap.containsKey(xor)) {
                int pre = indexMap.get(xor);
                // 为什么+1 呢，因为上面的可能性分析的时候 0-pre是有自己的最佳切分，那么 pre+1 到 i 也有一种，所以+1
                dp[i] = pre == -1 ? 0 : dp[pre] + 1;
            }
            // 可能性 2
            if (i > 1) {
                dp[i] = Math.max(dp[i], dp[i - 1]);
            }
            indexMap.put(xor, i);
        }
        return dp[N - 1];

    }
}
