package duys_code.day_34;

/**
 * @ClassName Code_05_324_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/wiggle-sort-ii/
 * @Date 2021/12/7 10:19
 **/
public class Code_05_324_LeetCode {
    //给你一个整数数组nums，将它重新排列成nums[0] < nums[1] > nums[2] < nums[3]...的顺序。
    //
    //你可以假设所有输入数组都可以得到满足题目要求的结果。

    // 时间复杂度O(N)，额外空间复杂度O(1)
    // 需要两个前置知识
    // 1.在无序数组中找到第K小的数 ，快排的改写方式
    // 2.完美洗牌 就是 L1 L2 L3 L4 R1 R2 R3 R4 -> L1R1 L2R2 L3R3 L4R4
    public static void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int n = nums.length;
        // 我们把数组划分成 小 中 大 三个区域
        findIndexNum(nums, 0, n - 1, n / 2);
        // 偶数
        if ((n & 1) == 0) {
            // 题意要求  < > < >
            // 先洗牌 结果是R1L1 R2L2 ... > < > <
            shuffle(nums, 0, n - 1);
            // 再交换  L1R1 L2R2 ..
            reverse(nums, 0, n - 1);
        } else {
            shuffle(nums, 1, n - 1);
        }
    }

    // 完美洗牌
    public static void shuffle(int[] arr, int l, int r) {
        // 区间有数字 ，切成一块一块的 每一块是 3^k  -1 保证是偶数
        while (r - l + 1 > 0) {
            int len = r - l + 2;
            int step = 3;
            int k = 1;
            while (step <= len / 3) {
                step *= 3;
                k++;
            }
            // 每一次需要处理的长度
            int m = (step - 1) / 2;
            // 中点
            int mid = (l + r) / 2;
            // 先交换
            rotate(arr, l + m, mid, mid + m);

            cycles(arr, l - 1, step, k);
            l = l + step - 1;
        }
    }

    public static void cycles(int[] arr, int base, int step, int k) {
        for (int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int nextIndex = (2 * trigger) % step;
            int cur = nextIndex;
            int record = arr[nextIndex + base];
            int tmp = 0;
            arr[nextIndex + base] = arr[trigger + base];
            while (cur != trigger) {
                nextIndex = (2 * cur) % step;
                tmp = arr[nextIndex + base];
                arr[nextIndex + base] = record;
                cur = nextIndex;
                record = tmp;
            }
        }
    }

    // 在无序数组中找到第K小的数
    public static int findIndexNum(int[] arr, int l, int r, int index) {
        int p = 0;
        int range[] = null;
        while (l < r) {
            p = arr[l + (int) Math.random() * (r - l + 1)];
            range = partition(arr, l, r, p);
            // 命中了
            if (index >= range[0] && index <= range[1]) {
                return arr[index];
            } else if (index < range[0]) {
                r = range[0] - 1;
            } else {
                l = range[1] + 1;
            }
        }
        return arr[l];
    }

    public static int[] partition(int[] arr, int l, int r, int p) {
        // 小于区域
        int less = l - 1;
        // 大于区域
        int more = r + 1;
        int cur = l;
        while (cur < more) {
            // 如果当前数大于了p
            if (arr[cur] > p) {
                // 大于区域往前扩展，把大于区域的前一个和当前数交换
                swap(arr, cur, --more);
            } else if (arr[cur] < p) {
                // 小于区域往前扩，当前数跳下一个
                swap(arr, ++less, cur++);
            } else {
                // 相等，当前数跳下一个
                cur++;
            }
        }
        // 等于区域的开始和结束位置
        return new int[]{less + 1, more - 1};
    }


    public static void rotate(int[] arr, int l, int m, int r) {
        reverse(arr, l, m);
        reverse(arr, m + 1, r);
        reverse(arr, l, r);
    }

    public static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            swap(arr, l++, r--);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
