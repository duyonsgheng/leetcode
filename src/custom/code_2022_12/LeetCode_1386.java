package custom.code_2022_12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1386
 * @Author Duys
 * @Description
 * @Date 2022/12/26 15:18
 **/
// 1386. 安排电影院座位
public class LeetCode_1386 {

    // 0 0 0   0 0 0 0   0 0 0
    // 没有被占位的行可以安排两个
    // 有占用的行，看看能安排几个
    // 首位置和末尾位置不用管。用8个二进制位置表示2到9之间的占用情况
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int left = 0b11110000;
        int mid = 0b11000011;
        int right = 0b00001111;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] seat : reservedSeats) {
            if (seat[1] >= 2 && seat[1] <= 9) {
                // 相同的行，之前有没有占用情况。把之前的算上
                int pre = map.getOrDefault(seat[0], 0);
                int cur = pre | (1 << (seat[1] - 2));
                map.put(seat[0], cur);
            }
        }
        // 一共有几行被占用了
        // 没被占用的可以安排两个家庭
        int ans = (n - map.size()) * 2;
        for (int key : map.keySet()) {
            int bit = map.get(key);
            if (((bit | left) == left) || ((bit | right) == right) || ((bit | mid) == mid)) {
                ans += 1;
            }
        }
        return ans;
    }
}
