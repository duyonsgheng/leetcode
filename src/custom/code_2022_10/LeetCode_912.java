package custom.code_2022_10;

/**
 * @ClassName LeetCode_912
 * @Author Duys
 * @Description
 * @Date 2022/10/17 15:48
 **/
// 912. 排序数组
public class LeetCode_912 {
    public int[] sortArray(int[] nums) {
        // 直接快排
        process(nums, 0, nums.length - 1);
        return nums;
    }

    public void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] range = partition(arr, l, r);
        process(arr, l, range[0] - 1);
        process(arr, range[1] + 1, r);
    }

    public int[] partition(int[] arr, int l, int r) {
        if (r > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, l};
        }
        // 三个区域。小于区域，等于区域，大于区域
        int less = l - 1;
        int big = r;
        int cur = l;
        while (cur < big) {
            // 当前数小于目标
            // 当前数和小于区域下一个交换
            // 当前数跳下一个
            if (arr[cur] < arr[r]) {
                swap(arr, ++less, cur++);
            }
            // 等于区域，当前数跳下一个
            else if (arr[cur] == arr[r]) {
                cur++;
            }
            // 大于当前数，当前数和大于区域前一个交换，当前位置不变
            else {
                swap(arr, --big, cur);
            }
        }
        // r位置作为基准，需要交换，big和r位置
        swap(arr, big, r);
        return new int[]{less + 1, big};
    }

    public void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
