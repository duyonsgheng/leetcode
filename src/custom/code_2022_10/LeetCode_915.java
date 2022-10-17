package custom.code_2022_10;

/**
 * @ClassName LeetCode_915
 * @Author Duys
 * @Description
 * @Date 2022/10/17 16:03
 **/
// 915. 分割数组
public class LeetCode_915 {
    public static void main(String[] args) {
        int[] arr = {2, 1, 3};
        System.out.println(partitionDisjoint(arr));
    }

    public static int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        int leftMax = nums[0];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            // 记录遍历过程中遇到的最大
            max = Math.max(max, nums[i]);
            // 如果当前的值是大于等于我左边遍历过的最大值，那么当前值可以放入右边去。
            if (nums[i] >= leftMax) {
                continue;
            }
            // 如果小于我遇到了的最大值，必须要放在左边去
            leftMax = max;
            pos = i;
        }
        return pos + 1;
    }


    public int[] partition(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, l};
        }
        int less = l - 1;
        int big = r;
        int cur = l;
        while (cur < big) {
            if (arr[cur] < arr[r]) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > arr[r]) {
                swap(arr, --big, cur);
            } else {
                cur++;
            }
        }
        swap(arr, big, r);
        return new int[]{less + 1, big};
    }

    public void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
