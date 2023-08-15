package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2048
 * @date 2023年08月03日
 */
// 2048. 下一个更大的数值平衡数
// https://leetcode.cn/problems/next-greater-numerically-balanced-number/
public class LeetCode_2048 {
    public static int nextBeautifulNumber(int n) {
        for (int i = n + 1; i < Integer.MAX_VALUE; i++) {
            int num = i;
            int[] arr = new int[10];
            while (num > 0) {
                int last = num % 10;
                arr[last]++;
                num /= 10;
            }
            boolean flag = true;
            for (int j = 0; j < 10; j++) {
                if (arr[j] != 0 && arr[j] != j) {
                    flag = !flag;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(nextBeautifulNumber(1));
    }
}
