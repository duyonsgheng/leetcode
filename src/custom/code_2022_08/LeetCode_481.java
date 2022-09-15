package custom.code_2022_08;

/**
 * @ClassName LeetCode_481
 * @Author Duys
 * @Description
 * @Date 2022/8/15 13:24
 **/
// 481. 神奇字符串
public class LeetCode_481 {
    public int magicalString(int n) {
        // 1 22 11 2 1 22 1 22 11 2 11 22
        // 1  2 2  1  1 2 1
        StringBuilder builder = new StringBuilder("122");
        int a = 2;
        int b = 2;
        while (a < n) {
            // 如果之前是1接下来准备添加2，如果是2准备添加1
            int tmp = builder.charAt(a) - '0' == 1 ? 2 : 1;
            int index = 0;
            while (index < builder.charAt(b) - '0') {
                builder.append(tmp);
                index++;
            }
            a = builder.length() - 1;
            b++;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (builder.charAt(i) == '1') {
                ans++;
            }
        }
        return ans;
    }
}
