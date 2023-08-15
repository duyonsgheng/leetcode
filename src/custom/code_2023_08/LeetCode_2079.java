package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2079
 * @date 2023年08月11日
 */
// 2079. 给植物浇水
// https://leetcode.cn/problems/watering-plants/
public class LeetCode_2079 {
    public static int wateringPlants(int[] plants, int capacity) {
        // plants = [2, 2, 3, 3],capacity = 5
        // -1 2 2 3 3
        //    1 2
        int st = -1;
        int cur = capacity;
        int n = plants.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (plants[i] == -1) {
                st = i;
            } else {
                if (cur >= plants[i]) { // 如果够，那么直接垮过去
                    cur -= plants[i];
                } else {
                    // 先退回去加水
                    ans += (i - 1 - st) * 2;
                    cur = capacity - plants[i];
                    // 如果存在加满水都还不能满足的情况，那么就是无效的题目，题目保证了
                }
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(wateringPlants(new int[]{2, 2, 3, 3}, 5));
        // plants = [1,1,1,4,2,3], capacity = 4
        System.out.println(wateringPlants(new int[]{1, 1, 1, 4, 2, 3}, 4));
        // plants = [7,7,7,7,7,7,7], capacity = 8
        System.out.println(wateringPlants(new int[]{7, 7, 7, 7, 7, 7, 7}, 8));
    }
}
