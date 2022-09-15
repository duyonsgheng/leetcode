package duys_code.day_35;

/**
 * @ClassName Code_11_
 * @Author Duys
 * @Description TODO
 * @Date 2021/12/10 13:41
 **/
public class Code_11_CircleCandy {

    // 来自网易
    // 给定一个正数数组arr，表示每个小朋友的得分
    // 任何两个相邻的小朋友，如果得分一样，怎么分糖果无所谓，但如果得分不一样，分数大的一定要比分数少的多拿一些糖果
    // 假设所有的小朋友坐成一个环形，返回在不破坏上一条规则的情况下，需要的最少糖果数

    // 分糖果经典问题的解法是，从左边往右边遍历，然后从右边往左边遍历，最后去两次遍历，每一个位置的最大
    // 也就是左边遍历的时候有一个坡，右边遍历的时候有一个坡，

    // 这里小朋友围城了一圈
    // 首先找到最小的，这事整个价值的低洼点
    // 比如 2 3 5 2 4 1 6 3 4 5 -> 1 6 3 4 5 2 3 5 2 4 1 最后一个位置可以不要，
    public static int minCandy(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int n = arr.length;
        int minIndex = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] <= arr[lastIndex(i, n)] && arr[i] <= arr[nextIndex(i, n)]) {
                minIndex = i;
                break;
            }
        }
        int[] nums = new int[n + 1];
        for (int i = 0; i <= n; i++, minIndex = nextIndex(minIndex, n)) {
            nums[i] = arr[minIndex];
        }

        // 根据经典的求法
        int[] left = new int[n + 1];
        left[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (nums[i] > nums[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int[] right = new int[n + 1];
        right[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }

    public static int lastIndex(int i, int n) {
        return i == 0 ? (n - 1) : (i - 1);
    }

    public static int nextIndex(int i, int n) {
        return i == n - 1 ? 0 : (i + 1);
    }
    public static void main(String[] args) {
        int[] arr = { 3, 4, 2, 3, 2 };
        System.out.println(minCandy(arr));
    }
}
