package custom.code_2022_05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName LeetCode_71
 * @Author Duys
 * @Description
 * @Date 2022/5/10 13:22
 **/
// 71. 简化路径
public class LeetCode_71 {
    public static String simplifyPath(String path) {
        if (path == null || path.length() <= 0) {
            return path;
        }
        char[] str = path.toCharArray();
        char[] nstr = new char[str.length];
        int index = 0;
        nstr[index++] = str[0];
        for (int i = 1; i < str.length; i++) {
            char pre = str[i - 1];
            char cur = str[i];
            if (pre == '/' && cur == '/') {
                continue;
            } else {
                nstr[index++] = str[i];
            }
        }
        LinkedList<String> queue = new LinkedList<>();
        int l = 1;
        int r = 1;
        // / home/foo/
        while (r != index) {
            if (nstr[r++] == '/') {
                queue.add(String.valueOf(nstr, l, r - l - 1));
                l = r;
            }
        }
        if (index - l > 0) {
            queue.add(String.valueOf(nstr, l, index - l));
        }
        List<String> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            String cur = queue.pollFirst();
            if (cur.equals(".")) {
                continue;
            } else if (cur.equals("..")) {
                if (ans.isEmpty()) {
                    continue;
                } else {
                    ans.remove(ans.size() - 1);
                }
            } else {
                ans.add("/" + cur);
            }
        }
        StringBuffer sb = new StringBuffer();
        if (ans.size() == 0) {
            sb.append("/");
        } else {
            for (String s : ans) {
                sb.append(s);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        char arr[] = new char[]{'/', 'a', '/'};
        System.out.println(simplifyPath("/a//b////c/d//././/.."));
    }
}
