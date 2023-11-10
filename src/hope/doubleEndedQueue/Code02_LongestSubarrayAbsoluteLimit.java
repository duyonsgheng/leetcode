package hope.doubleEndedQueue;

/**
 * @author Mr.Du
 * @ClassName Code02_LongestSubarrayAbsoluteLimit
 * @date 2023年11月08日 10:12
 */
// 绝对差不超过限制的最长连续子数组
// 给你一个整数数组 nums ，和一个表示限制的整数 limit
// 请你返回最长连续子数组的长度
// 该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit
// 如果不存在满足条件的子数组，则返回 0
// 测试链接 : https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
public class Code02_LongestSubarrayAbsoluteLimit {
    public static int MAXN = 100001;

    // 窗口内最大值的更新结构（单调队列）
    public static int[] maxDeque = new int[MAXN];

    // 窗口内最小值的更新结构（单调队列）
    public static int[] minDeque = new int[MAXN];

    public static int maxh, maxt, minh, mint;

    public static int[] arr;

    // 窗口内的最大值最小值更新结构
    public static int longestSubarray(int[] nums, int limit) {
        maxh = maxt = minh = mint = 0;
        arr = nums;
        int n = arr.length;
        int ans = 0;
        for (int l = 0, r = 0; l < n; l++) {
            // 如果把r位置的数拉进窗口依然满足
            while (r < n && ok(limit, nums[r])) {
                push(r++);
            }
            ans = Math.max(ans, r - l);
            // l即将++了，l位置的数要出窗口了，更新窗口内的最大值和最小值
            pop(l);
        }
        return ans;
    }

    // 把当前窗口加入数字，窗口内的最大值 - 最小值 是否 <= limit
    // 依然 <= limit ，则为true
    // 如果 > limit 则为false
    public static boolean ok(int limit, int num) {
        int max = maxh < maxt ? Math.max(arr[maxDeque[maxh]], num) : num;
        int min = minh < mint ? Math.min(arr[minDeque[minh]], num) : num;
        return max - min <= limit;
    }

    // r位置的数进入窗口，修改窗口内的最大值和最小值
    public static void push(int r) {
        while (maxh < maxt && arr[maxDeque[maxt - 1]] <= arr[r]) {
            maxt--;
        }
        maxDeque[maxt++] = r;
        while (minh < mint && arr[minDeque[mint - 1]] >= arr[r]) {
            mint--;
        }
        minDeque[mint++] = r;
    }

    // l位置的数要处窗口了，更新窗口内最大值和最小值
    public static void pop(int l) {
        if (maxh < maxt && maxDeque[maxh] == l) {
            maxh++;
        }
        if (minh < mint && minDeque[minh] == l) {
            minh++;
        }
    }
}
