package week.week_2022_01_01;

/**
 * @ClassName Code_01_ABDisappear
 * @Author Duys
 * @Description
 * @Date 2022/4/12 13:08
 **/


// 来自阿里
// 给定一个只由'a'和'b'组成的字符串str，
// str中"ab"和"ba"子串都可以消除，消除之后剩下字符会重新靠在一起，继续出现可以消除的子串...
// 你的任务是决定一种消除的顺序，最后让str消除到尽可能的短
// 返回尽可能的短的剩余字符串
public class Code_01_ABDisappear {

    // 来一个暴力得尝试
    public static String disappear(String str) {
        String ans = str;
        for (int i = 1; i < str.length(); i++) {
            // i = 1 , 0 1 位置是不是 a b 都全  剩下 2...
            // i = 2, 1 2 位置是不是a b 都全 剩下 0 3 ...
            // i= 3 , 2 3位置是不是a b都全，剩下0 1 4
            // i-1 和 i能不能消除
            boolean hasA = str.charAt(i - 1) == 'a' || str.charAt(i) == 'a';
            boolean hasB = str.charAt(i - 1) == 'b' || str.charAt(i) == 'b';
            if (hasA && hasB) {
                String ansStr = disappear(str.substring(0, i - 1) + str.substring(i + 1));
                if (ansStr.length() < ans.length()) {
                    ans = ansStr;
                }
            }
        }
        return ans;
    }

    // 来一个贪心
    // 如果字符串中a的个数比b的个数要多，那么一定剩下的是a，否则剩下的是b
    public static String disappear2(String str) {
        int countA = 0;
        char[] chars = str.toCharArray();
        for (char ch : chars) {
            countA += ch == 'a' ? 1 : -1;
        }
        StringBuilder sb = new StringBuilder();
        char rest = countA > 0 ? 'a' : 'b';
        countA = Math.abs(countA);
        for (int i = 0; i < countA; i++) {
            sb.append(rest);
        }
        return sb.toString();
    }


    // 利用栈，如果前一个是和自己相反的，直接弹出
    public static String disappear3(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;

        // 用数组结构自己实现栈
        int[] stack = new int[n];
        int size = 0;
        for (int i = 0; i < n; i++) {
            boolean hasA = size != 0 && chars[stack[size - 1]] == 'a';
            boolean hasB = size != 0 && chars[stack[size - 1]] == 'b';
            hasA |= chars[i] == 'a';
            hasB |= chars[i] == 'b';
            if (hasA && hasB) {
                size--;
            } else {
                stack[size++] = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(chars[stack[i]]);
        }
        return sb.toString();
    }

    // 为了测试
    public static String randomString(int len, int varible) {
        char[] str = new char[len];
        for (int i = 0; i < str.length; i++) {
            str[i] = (char) ((int) (Math.random() * varible) + 'a');
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        String str = randomString(22, 2);
        System.out.println(disappear(str));
        System.out.println(disappear2(str));
        System.out.println(disappear3(str));
    }
}
