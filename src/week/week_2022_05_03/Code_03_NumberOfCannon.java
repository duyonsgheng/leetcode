package week.week_2022_05_03;

import java.util.TreeMap;

/**
 * @ClassName Code_03_NumberOfCannon
 * @Author Duys
 * @Description
 * @Date 2022/5/19 13:43
 **/
// 给定一个数组arr，表示从早到晚，依次会出现的导弹的高度
// 大炮打导弹的时候，如果一旦大炮定了某个高度去打，那么这个大炮每次打的高度都必须下降一点
// 1) 如果只有一个大炮，返回最多能拦截多少导弹
// 2) 如果所有的导弹都必须拦截，返回最少的大炮数量
public class Code_03_NumberOfCannon {

    // 我们的第一问就是求一个最长递减子序列
    // 我们把数组倒叙后，就是求一个最长递增子序列了
    public static int max(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int n = arr.length;
        // 这里的ends表示长度为 ends的i+1长度的最长递增子序列是多长
        int[] nums = new int[n];
        for (int i = n - 1, j = 0; i >= 0; i--, j++) {
            nums[j] = arr[i];
        }
        int[] ends = new int[n];
        ends[0] = nums[0];
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        int max = 1;// 已经有一个最长递增子序列了
        //  2 4 3 1 5
        for (int i = 1; i < n; i++) {
            l = 0; // 要去ends中二分使用的
            r = right;
            // 去ends中找 小于等于 arr[i]的数长度是多少
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    // 最后找到了的这儿l就是刚刚小于当前arr[i]的位置
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            max = Math.max(l + 1, max);
        }
        return max;
    }

    //  2) 如果所有的导弹都必须拦截，返回最少的大炮数量
    // 有序表 ，当来到i位置，看看有没有 >= arr[i]的离arr[i]最近的，用这个高度来拦截，然后使用了高度-1
    public static int min(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        // key - 大炮的高度  value - 有几门
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : arr) {
            // 如果没有大于我高度的大炮，那么，就新添加一门
            if (map.ceilingEntry(num + 1) == null) {
                map.put(Integer.MAX_VALUE, 1);
            }
            int key = map.ceilingKey(num + 1);
            if (map.get(key) > 1) { // 有多门
                map.put(key, map.get(key) - 1);
            } else {
                map.remove(key);
            }
            // 上面的都处理好了，然后当前高度的炮增加一门
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int num : map.values()) {
            ans += num;
        }
        return ans;
    }
}
