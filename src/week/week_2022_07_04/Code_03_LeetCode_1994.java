package week.week_2022_07_04;

import java.util.Arrays;

/**
 * @ClassName Code_03_LeetCode_1994
 * @Author Duys
 * @Description
 * @Date 2022/7/28 10:10
 **/
// 1994. 好子集的数目
public class Code_03_LeetCode_1994 {

    // 1.首先按照题意给出的数据范围进行初始化，也是一个状态压缩的题
    // 首先整理出 0到30内的所有满足的数，比如4 8 9 12 16 18 24 25 27 28  这种就完全不符合了，因为质素因子有重复的了
    // 0 2 3 5 6 7 10 11 13 14 15 17 19 20 21 22 23 26 29 30
    // 0 - 00000000
    // 1 - 00000000
    // 2 - 00000001
    // 3 - 00000010
    // 4 - 00000000
    // 5 - 00000100
    // 6 - 00000011 (质数因子是2 3)
    // 7 - 00001000
    // 8 - 00000000
    //10 - 00000101
    // 我们按照status来把数按状态用二进制表示出来
    // 29 - 1000000000
    public int[] primes = {0, 0, 1, 2, 0, 4, 3, 8, 0, 0, 5, 16, 0, 32, 9, 6, 0, 64, 0, 128, 0, 10, 17, 256, 0, 0, 33, 0, 0, 512, 7};
    public int[] counts = new int[31];
    // 因为最小是512
    public int[] status = new int[1 << 10];
    public int mod = 1000000007;

    public int numberOfGoodSubsets(int[] nums) {
        //Arrays.fill(counts, 0);
        //Arrays.fill(status, 0);
        for (int num : nums) {
            counts[num]++;
        }
        status[0] = 1; // 空集
        // 先把1给算上
        for (int i = 0; i < counts[1]; i++) {
            status[0] = (status[0] << 1) % mod;
        }
        // 2到30上选择
        for (int i = 2; i <= 30; i++) {
            // 当前的状态-质数因子状态
            int cur = primes[i];
            if (cur != 0 && counts[i] != 0) {
                // 枚举所有状态的from，因为from最大就是512 也就是29这个质数因子
                for (int from = 0; from < (1 << 10); from++) {
                    if ((from & cur) == 0) {
                        continue;
                    }
                    int to = from | cur;
                    status[to] = (int) (((long) status[to] + ((long) status[from] * counts[i])) % mod);
                    // 如果不mod
                    // status[to] += status[from]*counts[i]
                }

            }
        }
        int ans = 0;
        for (int s = 1; s < (1 << 10); s++) {
            ans = (ans + status[s]) % mod;
        }
        return ans;
    }

    public static void main(String[] args) {
    }
}
