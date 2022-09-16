package custom.code_2022_09;

import java.util.List;

/**
 * @ClassName LeetCode_738
 * @Author Duys
 * @Description
 * @Date 2022/9/16 10:40
 **/
// 738. 单调递增的数字
public class LeetCode_738 {
    public static void main(String[] args) {
        int i = 332;
        System.out.println(monotoneIncreasingDigits(i));
    }

    public static int monotoneIncreasingDigits(int n) {
        if (n <= 9) {
            return 0;
        }
        String s = String.valueOf(n);
        char[] arr = s.toCharArray();
        // 123245
        int pot = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            int cur = arr[i] - '0';
            int next = arr[i + 1] - '0';
            if (cur > next) {
                pot = i + 1;
                break;
            }
        }
        if (pot == -1) {
            return n;
        }
        // 从这个位置开始需要处理了
        // 先往前处理
        while (pot > 0 && arr[pot - 1] > arr[pot]) {
            arr[pot - 1] -= 1;
            pot--;
        }
        // 然后往后处理
        for (pot++; pot < arr.length; pot++) {
            arr[pot] = '9';
        }
        return Integer.parseInt(String.valueOf(arr));
    }

    // 10007
    public static void process(char[] arr, int i, boolean pre, List<Character> ans) {

    }
}
