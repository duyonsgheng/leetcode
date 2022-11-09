package custom.code_2022_11;

/**
 * @ClassName LeetCode_1081
 * @Author Duys
 * @Description
 * @Date 2022/11/8 13:21
 **/
//1081.不同字符的最小子序列
public class LeetCode_1081 {
    public String smallestSubsequence(String s) {
        boolean[] visited = new boolean[26];
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (!visited[cur - 'a']) {
                // 如果之前的决定导致了字符偏大，保证每一个字符都是尽可能再剩下中选择小的
                while (buffer.length() > 0 && buffer.charAt(buffer.length() - 1) > cur) {
                    if (count[buffer.charAt(buffer.length() - 1) - 'a'] > 0) {
                        visited[buffer.charAt(buffer.length() - 1) - 'a'] = false;
                        buffer.deleteCharAt(buffer.length() - 1);
                    } else break;
                }
                visited[cur - 'a'] = true;
                buffer.append(cur);
            }
            count[cur - 'a']--;
        }
        return buffer.toString();
    }
}
