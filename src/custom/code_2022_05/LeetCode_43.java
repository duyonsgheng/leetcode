package custom.code_2022_05;

import java.util.Arrays;

/**
 * @ClassName LeetCode_43
 * @Author Duys
 * @Description
 * @Date 2022/5/5 13:36
 **/
// 给定两个以字符串形式表示的非负整数num1和num2，返回num1和num2的乘积，它们的乘积也表示为字符串形式。
//注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
//链接：https://leetcode-cn.com/problems/multiply-strings
public class LeetCode_43 {

    public static String multiply(String num1, String num2) {
        if (num1 == null || num1.length() <= 0 || num2 == null || num2.length() <= 0) {
            return num1;
        }
        String ans = "0";
        if (num1.equals("0") || num2.equals("0")) {
            return ans;
        }
        int l1 = num1.length();
        int l2 = num2.length();
        for (int i = l2 - 1; i >= 0; i--) {
            StringBuilder cur = new StringBuilder();
            int add = 0;
            for (int j = l2 - 1; j > i; j--) {
                cur.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = l1 - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int pro = x * y + add;
                cur.append(pro % 10);
                add = pro / 10;
            }
            if (add != 0) {
                cur.append(add);
            }
            ans = addString(ans, cur.reverse().toString());
        }
        return ans;
    }

    public static String addString(String n1, String n2) {
        int i = n1.length() - 1;
        int j = n2.length() - 1;
        int add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? n1.charAt(i) - '0' : 0;
            int y = j >= 0 ? n2.charAt(j) - '0' : 0;
            int res = x + y + add;
            ans.append(res % 10);
            add = res / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }

    public static void swap(char[] arr, int i, int j) {
        char ca = arr[i];
        arr[i] = arr[j];
        arr[j] = ca;
    }

    public static void main(String[] args) {
        int a = 12;
        int b = 11;
        System.out.println(multiply("11", "11"));
    }
}
