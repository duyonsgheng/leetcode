package custom.code_2023_01;

/**
 * @ClassName LeetCode_2180
 * @Author Duys
 * @Description
 * @Date 2023/1/6 9:20
 **/
// 2180. 统计各位数字之和为偶数的整数个数
public class LeetCode_2180 {
    public int countEven(int num) {
        int ans = 0;
        for (int i = 1; i <= num; i++) {
            if (process(i)) {
                ans++;
            }
        }
        return ans;
    }

    public boolean process(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum % 2 == 0;
    }
}
