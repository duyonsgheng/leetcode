package custom.code_2022_09;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1592
 * @Author Duys
 * @Description
 * @Date 2022/9/7 10:35
 **/
// 1592. 重新排列单词间的空格
public class LeetCode_1592 {

    public static String reorderSpaces(String text) {
        char[] arr = text.toCharArray();
        int word = 0;
        int space = 0;
        List<String> words = new ArrayList<>();
        int l = 0;
        int r = 0;
        int n = arr.length;
        while (l < n && r < n) {
            StringBuilder builder = new StringBuilder();
            while (r < n && arr[r] != ' ') {
                builder.append(arr[r]);
                r++;
            }
            //
            if (!"".equals(builder.toString())) {
                words.add(builder.toString());
            }
            while (r < n && arr[r] == ' ') {
                space++;
                r++;
            }
        }
        StringBuilder builder = new StringBuilder();
        word = words.size();
        if (word == 1) {
            builder.append(words.get(0));
            for (int i = 0; i < space; i++) {
                builder.append(" ");
            }
        } else {
            // 相邻单词空格
            int sum = space;
            int s = space / (word - 1);
            for (int i = 0; i < word - 1; i++) {
                builder.append(words.get(i));
                // 紧接着几个空格
                for (int j = 0; j < s; j++) {
                    builder.append(" ");
                }
                sum -= s;
            }
            builder.append(words.get(word - 1));
            for (int i = 0; i < sum; i++) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String text = "  walks  udp package   into  bar a";
        System.out.println(reorderSpaces(text));
    }
}
