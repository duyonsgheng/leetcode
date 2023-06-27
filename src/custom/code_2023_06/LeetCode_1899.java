package custom.code_2023_06;

/**
 * @ClassName LeetCode_1899
 * @Author Duys
 * @Description
 * @Date 2023/6/26 9:26
 **/
// 1899. 合并若干三元组以形成目标三元组
public class LeetCode_1899 {

    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int t = 0b111, ans = 0;
        int a = target[0], b = target[0], c = target[0];
        for (int[] tri : triplets) {
            int t1 = tri[0], t2 = tri[1], t3 = tri[2], cur = 0;
            if (t1 > a || t2 > b || t3 > c) {
                continue;
            }
            if (t1 == a) {
                cur |= 0b1;
            }
            if (t2 == b) {
                cur |= 0b10;
            }
            if (t3 == c) {
                cur |= 0b100;
            }
            ans |= cur;
        }
        return ans == t;
    }
}
