package custom.code_2022_12;

// 1375. 二进制字符串前缀一致的次数
public class LeetCode_1375 {

    // 出现的最大数字等于下标+1，说明前面已经被1填满了
    // flips = [3,2,4,1,5]
    // 0 0 0 0 0
    // 0 0 1 0 0 max = 3 , i = 1
    // 0 1 1 0 0 max = 3 , i = 2
    // 0 1 1 1 0 max = 4 , i = 3
    // 1 1 1 1 0 max = 4 , i = 4
    // 1 1 1 1 1 max = 5 , i = 5
    public int numTimesAllBlue(int[] flips) {
        int max = -1;
        int ans = 0;
        for (int i = 0; i < flips.length; i++) {
            max = Math.max(flips[i], max);
            if (max == i + 1) {
                ans++;
            }
        }
        return ans;
    }
}
