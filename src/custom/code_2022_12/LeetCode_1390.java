package custom.code_2022_12;

/**
 * @ClassName LeetCode_1390
 * @Author Duys
 * @Description
 * @Date 2022/12/26 16:29
 **/
// 1390. 四因数
public class LeetCode_1390 {
    public int sumFourDivisors(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            int cnt = 0, sum = 0;
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    cnt++;
                    sum += i;
                    if (i * i != num) { // 如果两个因子不等，则把另一个也算上
                        cnt++;
                        sum += num / i;
                    }
                }
            }
            if (cnt == 4) {
                ans += sum;
            }
        }
        return ans;
    }
}
