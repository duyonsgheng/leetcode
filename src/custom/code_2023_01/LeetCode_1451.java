package custom.code_2023_01;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1451
 * @Author Duys
 * @Description
 * @Date 2023/1/16 14:24
 **/
// 1451. 重新排列句子中的单词
public class LeetCode_1451 {
    public static void main(String[] args) {
        System.out.println(arrangeWords("Keep calm and code on"));
        System.out.println(arrangeWords("To be or not to be"));
    }

    public static String arrangeWords(String text) {
        String[] s = text.split(" ");
        int n = s.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++)
            nodes[i] = new Node(s[i].length(), i);
        Arrays.sort(nodes, (a, b) -> a.len != b.len ? a.len - b.len : a.index - b.index);
        StringBuffer buffer = new StringBuffer();
        String first = s[nodes[0].index];
        char[] arr = first.toCharArray();
        arr[0] = arr[0] >= 'a' && arr[0] <= 'z' ? (char) ((int) arr[0] - 32) : arr[0];
        first = new String(arr);
        buffer.append(first);
        for (int i = 1; i < n; i++) {
            buffer.append(" ");
            buffer.append(s[nodes[i].index].toLowerCase());
        }
        return buffer.toString().trim();
    }

    static class Node {
        int len;
        int index;

        public Node(int l, int i) {
            len = l;
            index = i;
        }
    }
}
