package custom.code_2022_12;

/**
 * @ClassName LeetCode_1769
 * @Author Duys
 * @Description
 * @Date 2022/12/2 11:33
 **/
// 1769. 移动所有球到每个盒子所需的最小操作数
public class LeetCode_1769 {
    public static int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] left = new int[n]; // 不算自己，左边来到自己的代价
        int[] right = new int[n]; // 不算自己,右边来到自己的代价
        char[] arr = boxes.toCharArray();
        // 1 1 0 1 0 1
        int num = arr[0] == '1' ? 1 : 0;
        int cost = 0;
        for (int i = 1; i < n; i++) {
            left[i] = num + cost;
            num += arr[i] - '0';
            cost = left[i];
        }
        num = arr[n - 1] == '1' ? 1 : 0;
        cost = 0;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = num + cost;
            num += arr[i] - '0';
            cost = right[i];
        }
        int[] ans = new int[n];
        ans[0] = right[0];
        ans[n - 1] = left[n - 1];
        for (int i = 1; i < n - 1; i++) {
            ans[i] = left[i] + right[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        String boxes = "110101";
        minOperations(boxes);
    }
}
