package custom.code_2022_06;

/**
 * @ClassName LeetCode_164
 * @Author Duys
 * @Description
 * @Date 2022/6/30 14:37
 **/
// 164. 最大间距
// 给定一个无序的数组nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
//您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
//链接：https://leetcode.cn/problems/maximum-gap
public class LeetCode_164 {

    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        process(0, nums.length - 1, nums);
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i] - nums[i - 1], max);
        }
        return max;
    }

    public static void process(int l, int r, int[] arr) {
        if (l >= r) {
            return;
        }
        int[] range = range(l, r, arr);
        process(l, range[0] - 1, arr);
        process(range[1] + 1, r, arr);
    }

    public static int[] range(int l, int r, int[] arr) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, l};
        }
        int less = l - 1;
        int more = r;
        int cur = l;
        while (cur < more) {
            if (arr[cur] > arr[r]) {
                swap(--more, cur, arr);
            } else if (arr[cur] < arr[r]) {
                swap(++less, cur++, arr);
            } else {
                cur++;
            }
        }
        swap(more, r, arr);
        return new int[]{less + 1, more};
    }

    public static void swap(int i, int j, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
