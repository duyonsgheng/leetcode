package custom.code_2022_11;

/**
 * @ClassName LeetCode_1190
 * @Author Duys
 * @Description
 * @Date 2022/11/22 15:41
 **/
// 1190. 反转每对括号间的子串
public class LeetCode_1190 {
    public static String reverseParentheses(String s) {
        Node node = process(s.toCharArray(), 0);
        return node.value.toString();
    }

    public static Node process(char[] arr, int index) {
        Node cur = new Node();
        StringBuffer buffer = new StringBuffer();
        while (index < arr.length && arr[index] != ')') {
            if (arr[index] != '(') {
                buffer.append(arr[index]);
                index++;
            } else {
                Node next = process(arr, index + 1);
                if (next != null) {
                    buffer.append(next.value.reverse());
                    index = next.end + 1;
                }
            }
        }
        cur.end = index;
        cur.value = buffer;
        return cur;
    }

    static class Node {
        int end;
        StringBuffer value;
    }

    public static void main(String[] args) {
        String s = "(ed(et(oc))el)";
        System.out.println(reverseParentheses(s));
        String s1 = "a(bcdefghijkl(mno)p)q";
        System.out.println(reverseParentheses(s1));
    }
}
