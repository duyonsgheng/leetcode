package custom.code_2022_12;

/**
 * @ClassName LeetCode_1832
 * @Author Duys
 * @Description
 * @Date 2022/12/13 9:21
 **/
// 1832. 判断句子是否为全字母句
public class LeetCode_1832 {
    public boolean checkIfPangram(String sentence) {
        char[] arr = sentence.toCharArray();
        int[] cnt = new int[26];
        for (char c : arr) {
            cnt[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
