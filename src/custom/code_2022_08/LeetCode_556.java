package custom.code_2022_08;

/**
 * @ClassName LeetCode_556
 * @Author Duys
 * @Description
 * @Date 2022/8/26 14:15
 **/
// 556. 下一个更大元素 III
public class LeetCode_556 {
    // n = 1564
    public static int nextGreaterElement(int n) {
        char[] str = String.valueOf(n).toCharArray();
        int j = -1;
        for (int i = str.length - 1; i > 0; i--) {
            if (str[i - 1] < str[i]) {
                j = i - 1;
                break;
            }
        }
        // 从右往左一直都是递增的，那么就没有满足的情况
        if (j == -1) {
            return -1;
        }
        for (int i = str.length - 1; i > j; i--) {
            if (str[i] > str[j]) { // 把大的往前浮动
                swap(i, j, str);
                break;
            }
        }
        // j+1 到 n-1 位置进行反转
        int left = j + 1;
        int right = str.length - 1;
        while (left < right) {
            swap(left++, right--, str);
        }
        Long v = Long.valueOf(String.valueOf(str));
        if (v > Integer.MAX_VALUE) {
            return -1;
        }
        return v.intValue();
    }

    public static void swap(int i, int j, char[] arr) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int i = 1564;
        System.out.println(nextGreaterElement(i));
    }
}
