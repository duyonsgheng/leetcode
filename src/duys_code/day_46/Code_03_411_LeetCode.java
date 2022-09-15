package duys_code.day_46;

/**
 * @ClassName Code_03_411_leetcode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/minimum-unique-word-abbreviation/
 * @Date 2021/10/18 11:28
 **/
public class Code_03_411_LeetCode {
    /**
     * 这道题的意思：比如有一个target 字符串 和一个字典数组 dict 要求我们吧target 缩写，但是不能和dict里的字典有冲突
     */
    public static int min;
    public static int best;

    public static String minAbbreviation1(String target, String[] dictionary) {
        min = Integer.MAX_VALUE;
        best = 0;
        char[] tar = target.toCharArray();
        int len = tar.length;
        int size = 0;
        for (String dict : dictionary) {
            // 如果长度相等的就记录，因为长度不一样不可能会冲突，缩写的时候之可能会和长度一样的字典里的单词冲突
            if (dict.length() == len) {
                size++;
            }
        }
        int[] words = new int[size];
        int index = 0;
        for (String dict : dictionary) {
            if (dict.length() == len) {
                char[] w = dict.toCharArray();
                int status = 0;
                for (int j = 0; j < len; j++) {
                    if (tar[j] != w[j]) { // 把相同位置，字符不同的标记为1
                        status |= 1 << j;
                    }
                }
                words[index++] = status;
            }
        }
        dfs1(words, len, 0, 0);
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (int i = 0; i < len; i++) {
            if ((best & (1 << i)) != 0) {
                if (count > 0) {
                    builder.append(count);
                }
                builder.append(tar[i]);
                count = 0;
            } else {
                count++;
            }
        }
        if (count > 0) {
            builder.append(count);
        }
        return builder.toString();
    }

    public static void dfs1(int[] words, int len, int fix, int index) {
        //
        if (!canFix(words, fix)) {
            if (index < len) {

                dfs1(words, len, fix, index + 1);
                dfs1(words, len, fix | (1 << index), index + 1);
            }
        } else {
            int ans = abbrLen(fix, len);
            if (ans < min) {
                min = ans;
                best = fix;
            }
        }
    }

    public static int abbrLen(int fix, int len) {
        int ans = 0;
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            if ((fix & (1 << i)) != 0) {
                ans++;
                if (cnt != 0) {
                    ans += (cnt > 9 ? 2 : 1) - cnt;
                }
                cnt = 0;
            } else {
                cnt++;
            }
        }
        if (cnt != 0) {
            ans += (cnt > 9 ? 2 : 1) - cnt;
        }
        return ans;
    }

    public static boolean canFix(int[] words, int fix) {
        for (int w : words) {
            if ((w & fix) == 0) {
                return false;
            }
        }
        return true;
    }
}
