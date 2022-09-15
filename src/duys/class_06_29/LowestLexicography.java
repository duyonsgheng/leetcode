package duys.class_06_29;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @ClassName LowestLexicography
 * @Author Duys
 * @Description 贪心之一
 * @Date 2021/6/29 17:19
 **/
public class LowestLexicography {
    /**
     * 题意：
     * 给定一个由字符串组成的数组strs，
     * 必须把所有的字符串拼接起来，
     * 返回所有可能的拼接结果中，字典序最小的结果
     */

    /**
     * 贪心：证明很难，把字符串当成数字处理，证明字符串的拼接也具有传递性
     * 例如： a.b <= b.a  .是字符串的运算符
     * b.c <= c.b
     * a.c <= c.a
     * 1.a*m(b) + b <= b*m(a) + a
     * 2.b*m(c) + c <= c*m(b) + b
     * 1式两边同时 减去 b =>  a * m(b) <= b *m(a) + a-b
     * 2式两边同时 减去 b =>  b * m(c) + c - b <= c *m(b)
     */
    public static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String ans = "";
        for (String str : strs) {
            ans += str;
        }
        return ans;
    }

    // 还可以使用TreeSet
    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        TreeSet<String> ans = process(strs);
        return ans.size() == 0 ? "" : ans.first();
    }

    public static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        if (strs == null || strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nests = removeIndex(strs, i);
            // 拿到数组得第一个元素，然后和数组剩下的元素进行组合
            TreeSet<String> nextAns = process(nests);
            for (String st : nextAns) {
                ans.add(first + st);
            }
        }
        return ans;
    }

    // 已经使用了的元素，就必须从数组中干掉，每个元素只能拼接一次
    public static String[] removeIndex(String[] strs, int index) {
        int newLength = strs.length;
        String[] newStrs = new String[newLength - 1];
        int ansIndex = 0;
        for (int i = 0; i < newLength; i++) {
            if (i != index) {
                newStrs[ansIndex++] = strs[i];
            }
        }
        return newStrs;
    }
}
