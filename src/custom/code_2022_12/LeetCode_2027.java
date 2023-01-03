package custom.code_2022_12;

/**
 * @ClassName LeetCode_2027
 * @Author Duys
 * @Description
 * @Date 2022/12/27 10:56
 **/
// 2027. 转换字符串的最少操作次数
public class LeetCode_2027 {

    public int minimumMoves(String s) {
        int index = -1, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'X' && i > index) {
                ans++;
                index = i + 2;
            }
        }
        return ans;
    }
}
