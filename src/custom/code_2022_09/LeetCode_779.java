package custom.code_2022_09;

/**
 * @ClassName LeetCode_779
 * @Author Duys
 * @Description
 * @Date 2022/9/20 13:04
 **/
// 779. 第K个语法符号
public class LeetCode_779 {
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        int ans = 0;
        if ((k & 1) == 1) {
            // 找到上一行是怎么来的
            int last = kthGrammar(n - 1, (k + 1) >> 1);
            if (last == 1) {
                ans = 1;
            }
        } else {
            int last = kthGrammar(n - 1, k >> 1);
            if (last == 1) {
                ans = 1;
            }
        }
        return ans;
    }
}
