package duys.class_07_09;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PrintStringArrangement
 * @Author Duys
 * @Description 打印字符串的全排列 - 深度优先遍历，恢复现场
 * @Date 2021/7/9 16:48
 **/
public class PrintStringArrangement {

    /**
     * 现场恢复
     */
    public static List<String> gement1(String s) {

        List<String> ans = new ArrayList<>();
        if (s == null || s.length() <= 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        List<Character> rest = new ArrayList<>();
        for (char c : str) {
            rest.add(c);
        }
        String path = "";
        process1(rest, path, ans);
        return ans;
    }

    public static void process1(List<Character> rest, String path, List<String> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
            return;
        }
        int n = rest.size();
        for (int i = 0; i < n; i++) {
            char cur = rest.get(i);
            rest.remove(i);
            process1(rest, path + cur, ans);
            // 用过知后，要恢复现场，以便下一个迭代不会出现问题
            rest.add(i, cur);
        }

    }

    public static List<String> gement2(String s) {

        List<String> ans = new ArrayList<>();
        if (s == null || s.length() <= 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process2(str, 0, ans);
        return ans;
    }

    public static void process2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        // 从index位置出发，因为index之前的已经处理过了，筛选过了
        for (int i = index; i < str.length; i++) {
            swap(str, index, i);
            process2(str, index + 1, ans);
            // 恢复现场
            swap(str, index, i);
        }
    }


    // 去重

    public static List<String> gement3(String s) {

        List<String> ans = new ArrayList<>();
        if (s == null || s.length() <= 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process3(str, 0, ans);
        return ans;
    }

    public static void process3(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
            return;
        }
        // 从index位置出发，因为index之前的已经处理过了，筛选过了
        boolean[] visited = new boolean[256];
        for (int i = index; i < str.length; i++) {
            if (!visited[str[i]]) { // 神来之笔
                // 本次交换，如果位置已经被尝试过了，就不要再次尝试交换了
                visited[str[i]] = true;
                swap(str, index, i);
                process3(str, index + 1, ans);
                // 恢复现场
                swap(str, index, i);
            }
        }
    }

    public static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }
}
