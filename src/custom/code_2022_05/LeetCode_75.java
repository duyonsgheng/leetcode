package custom.code_2022_05;

/**
 * @ClassName LeetCode_75
 * @Author Duys
 * @Description
 * @Date 2022/5/11 10:09
 **/
// 75. 颜色分类
// 给定一个包含红色、白色和蓝色、共n 个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
//我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
//必须在不使用库的sort函数的情况下解决这个问题。
//链接：https://leetcode.cn/problems/sort-colors
public class LeetCode_75 {

    // 改写快排的方式
    public static void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        process(nums, 0, nums.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] re = partition(arr, l, r);
        process(arr, l, re[0] - 1);
        process(arr, re[1] + 1, r);
    }

    public static int[] partition(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, l};
        }
        // 三个区域
        //
        int less = l - 1;
        int more = r;
        int cur = l;
        while (cur < more) {
            // 三种情况
            if (arr[cur] == arr[r]) {
                cur++;
            } else if (arr[cur] < arr[r]) {
                swap(arr, ++less, cur++);
            } else {
                swap(arr, --more, cur);
            }
        }
        //   2 0 1
        swap(arr, more, cur);
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2};
        sortColors(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void sort_insert(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int cur = i;
            while (cur > 0 && arr[cur] <= arr[cur - 1]) {
                swap(arr, cur, cur - 1);
                cur--;
            }
        }
    }
}
