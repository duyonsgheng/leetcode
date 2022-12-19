package custom.code_2022_12;

/**
 * @ClassName LeetCode_1358
 * @Author Duys
 * @Description
 * @Date 2022/12/13 17:19
 **/
//1358. 包含所有三种字符的子字符串数目
public class LeetCode_1358 {
    public int numberOfSubstrings(String s) {
        char[] arr = s.toCharArray();
        int ans = 0;
        int l = 0, r = 0, status = 0;
        int n = s.length();
        int limit = (1 << 3) - 1;
        int[] cnt = new int[3];
        while (r < n) {
            // 如果当前的前缀不满足，继续往右
            while (status != limit && r < n) {
                int index = arr[r] - 'a';
                if (cnt[index] == 0) {
                    status |= (1 << index);
                }
                cnt[index]++;
                r++;
            }
            // 当前字串的前缀已经满足了，那么以当前前缀开头的字串有多少个
            int rang = 0;
            while (status == limit) {
                int index = arr[l] - 'a';
                rang++;
                if (--cnt[index] == 0) {
                    status ^= (1 << index);
                }
                l++;
            }
            ans += (n - r + 1) * rang;
        }
        return ans;
    }

    public static void main(String[] args) {
        int l = (1 << 3) - 1;
        System.out.println(l);
    }
}
