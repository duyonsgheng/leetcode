package custom.code_2023_04;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1686
 * @Author Duys
 * @Description
 * @Date 2023/4/26 16:07
 **/
// 1686. 石子游戏 VI
public class LeetCode_1686 {
    public static int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = i;
            arr[i][1] = aliceValues[i] + bobValues[i];
        }
        Arrays.sort(arr, (a, b) -> b[1] - a[1]);
        // 偶数次为先手
        int suma = 0;
        int sumb = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                suma += aliceValues[arr[i][0]];
            } else {
                sumb += bobValues[arr[i][0]];
            }
        }
        return suma == sumb ? 0 : (suma > sumb ? 1 : -1);
    }

    public static void main(String[] args) {
        System.out.println(stoneGameVI(new int[]{1, 2}, new int[]{3, 1}));
    }
}
