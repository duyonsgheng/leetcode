package custom.code_2022_11;

/**
 * @ClassName LeetCode_1111
 * @Author Duys
 * @Description
 * @Date 2022/11/15 9:53
 **/
// 1111. 有效括号的嵌套深度
public class LeetCode_1111 {
    public int[] maxDepthAfterSplit(String seq) {
        int dept = 0;
        int n = seq.length();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                dept++;
                ans[i] = dept % 2;
            } else {
                ans[i] = dept % 2;
                dept--;
            }
        }
        return ans;
    }
}
