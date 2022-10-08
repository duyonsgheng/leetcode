package custom.code_2022_09;

/**
 * @ClassName LeetCode_848
 * @Author Duys
 * @Description
 * @Date 2022/9/30 9:13
 **/
// 848. 字母移位
public class LeetCode_848 {
    // 长度相同
    public static String shiftingLetters1(String s, int[] shifts) {
        char[] arr = s.toCharArray();
        int sum = 0;
        int len = shifts.length;
        for (int i = len - 1; i >= 0; i--) {
            sum += shifts[i];
            sum %= 26;
            int cur = arr[i] - 'a';
            cur = (cur + sum) % 26;
            arr[i] = (char) (cur + 'a');
        }
        return new String(arr);
    }

    // 前缀和
    public static String shiftingLetters(String s, int[] shifts) {
        StringBuffer buffer = new StringBuffer();
        int index = 0;
        for (int i : shifts) {
            index = (index + i) % 26;
        }
        for (int i = 0; i < s.length(); i++) {
            int cueIndex = s.charAt(i) - 'a';
            buffer.append((char) ((index + cueIndex) % 26 + 97));
            index = (index - shifts[i]) % 26;
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        String s = "abc";
        int[] a = {3, 5, 9};
        System.out.println(shiftingLetters(s, a));
        System.out.println(shiftingLetters1(s, a));
    }
}
