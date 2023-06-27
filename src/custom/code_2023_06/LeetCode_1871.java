package custom.code_2023_06;

/**
 * @ClassName LeetCode_1871
 * @Author Duys
 * @Description
 * @Date 2023/6/12 13:14
 **/
// 1871. 跳跃游戏 VII
public class LeetCode_1871 {
    // i + minJump <= j <= min(i + maxJump, s.length - 1)
    // s[j] == '0'
    // 差分
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) != '0') {
            return false;
        }
        int[] diff = new int[n];
        diff[0] = 1;
        diff[1] = -1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            if (sum > 0 && s.charAt(i) == '0') {
                // 统计min到max之间有没有路，如果有路，则可以走，否则断掉
                if (i + minJump < n)
                    diff[i + minJump] += 1;
                if (i + maxJump + 1 < n)
                    diff[i + maxJump + 1] += -1;
            }
        }
        return sum > 0;
    }
}
