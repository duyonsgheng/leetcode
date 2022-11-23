package custom.code_2022_11;

/**
 * @ClassName LeetCode_1742
 * @Author Duys
 * @Description
 * @Date 2022/11/23 9:08
 **/
// 1742. 盒子中小球的最大数量
public class LeetCode_1742 {

    public int countBalls(int lowLimit, int highLimit) {
        // 最大盒子编号 1+2+3+...+9 = 45
        int[] cnt = new int[46];
        int ans = 0;
        for (int i = lowLimit, num = 0, cur = 0; i <= highLimit; i++) {
            num = 0; // 该去的编号
            cur = i;
            while (cur > 0) {
                num += cur % 10;
                cur /= 10;
            }
            cnt[num]++;
            ans = Math.max(ans, cnt[num]);
        }
        return ans;
    }
}
