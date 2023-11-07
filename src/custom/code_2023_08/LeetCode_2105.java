package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2105
 * @date 2023年08月17日
 */
// 2105. 给植物浇水 II
// https://leetcode.cn/problems/watering-plants-ii/
public class LeetCode_2105 {
    // [2,2,3,3], capacityA = 3, capacityB = 4
    //  1     1
    //    1 1
    public static int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int n = plants.length;
        int l = 0, r = n - 1;
        int lc = capacityA, rc = capacityB;
        int ans = 0;
        while (l < r) {
            if (lc < plants[l]) {
                ans++;
                lc = capacityA - plants[l++];
            } else {
                lc -= plants[l++];
            }
            if (rc < plants[r]) {
                ans++;
                rc = capacityB - plants[r--];
            } else {
                rc -= plants[r--];
            }

        }
        // 还剩下
        if (n % 2 == 1) {
            if (lc >= rc && lc < plants[l]) {
                ans++;
            } else if (lc < rc && rc < plants[l]) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(minimumRefill(new int[]{2, 3, 3}, 5, 5));
    }
}
