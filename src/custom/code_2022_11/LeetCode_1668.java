package custom.code_2022_11;

/**
 * @ClassName LeetCode_1668
 * @Author Duys
 * @Description
 * @Date 2022/11/3 13:37
 **/
// 1668. 最大重复子字符串
public class LeetCode_1668 {
    public static int maxRepeating(String sequence, String word) {
        int n = sequence.length();
        int m = word.length();
        if (n < m) {
            return 0;
        }
        int limit = n / m;
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i <= limit) {
            sb.append(word);
            if (!sequence.contains(sb.toString())) {
                break;
            }
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        String sequence = "abababc", word = "ab";
        System.out.println(maxRepeating(sequence, word));
    }
}
