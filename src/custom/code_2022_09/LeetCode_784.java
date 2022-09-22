package custom.code_2022_09;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName LeetCode_784
 * @Author Duys
 * @Description
 * @Date 2022/9/20 13:14
 **/
// 784. 字母大小写全排列
public class LeetCode_784 {
    public static List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() <= 0) {
            return ans;
        }
        Set<String> anss = new HashSet<>();
        process(s.toCharArray(), 0, anss);
        ans.addAll(anss);
        return ans;
    }

    public static void process(char[] arr, int index, Set<String> ans) {
        if (index == arr.length) {
            ans.add(String.valueOf(arr));
            return;
        }
        process(arr, index + 1, ans);
        char cur = arr[index];
        if (cur >= 'a' && cur <= 'z') {
            arr[index] = (char) (cur - 32);
            ans.add(String.valueOf(arr));
        } else if (cur >= 'A' && cur <= 'Z') {
            arr[index] = (char) (cur + 32);
            ans.add(String.valueOf(arr));
        }
        process(arr, index + 1, ans);
        arr[index] = cur;
    }

    public static void main(String[] args) {
        String a = "a1b1";
        List<String> list = letterCasePermutation(a);
        System.out.println();
    }
}
