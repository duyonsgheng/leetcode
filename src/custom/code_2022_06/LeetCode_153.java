package custom.code_2022_06;

/**
 * @ClassName LeetCode_153
 * @Author Duys
 * @Description
 * @Date 2022/6/30 13:39
 **/
// 153. 寻找旋转排序数组中的最小值
public class LeetCode_153 {

    // 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。 那就快排啊
    public int findMin(int[] nums) {
        process(0, nums.length - 1, nums);
        return nums[0];
    }

    public static void process(int l, int r, int[] arr) {
        if (l >= r) {
            return;
        }
        int[] rang = range(l, r, arr);
        process(l, rang[0] - 1, arr);
        process(rang[1] + 1, r, arr);
    }

    // 荷兰国旗问题
    public static int[] range(int l, int r, int[] arr) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, l};
        }
        int less = l - 1; // 小于区域
        int more = r; // 大于区域
        int cur = l;
        while (cur < more) {
            if (arr[cur] < arr[r]) {
                // 小于区域往前扩，当前数跳下一个
                swap(++less, cur++, arr);
            } else if (arr[cur] > arr[r]) {
                swap(--more, cur, arr);
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
