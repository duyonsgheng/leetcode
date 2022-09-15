package week.week_2022_04_03;

/**
 * @ClassName Code_01_MaxOneNumbers
 * @Author Duys
 * @Description
 * @Date 2022/4/19 15:09
 **/

// 小红书
// 3.13 笔试
// 数组里有0和1，一定要翻转一个区间，翻转：0变1，1变0
// 请问翻转后可以使得1的个数最多是多少？
public class Code_01_MaxOneNumbers {

    public static int maxOneNumbers(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int ans = 0;
        int n = arr.length;
        for (int num : arr) {
            ans += num;
        }
        // 整体来做一个，把=0的全部变成1，=1的变成-1
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] == 0 ? 1 : -1;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            // 把之前算过1的地方 减去。找到一个最大的。就是我们需要翻转的
            cur += arr[i];
            max = Math.max(cur, max);
            cur = cur < 0 ? 0 : cur;
        }
        return max + ans;
    }
}
