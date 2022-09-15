package custom.code_2022_09;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_722
 * @Author Duys
 * @Description
 * @Date 2022/9/14 13:26
 **/
//722. 删除注释
public class LeetCode_722 {

    public static List<String> removeComments(String[] source) {
        List<String> ans = new ArrayList<>();
        if (source == null || source.length <= 0) {
            return ans;
        }
        boolean pre = false;
        StringBuilder builder = new StringBuilder();
        for (String str : source) {
            int i = 0;
            char[] arr = str.toCharArray();
            if (!pre) {
                builder = new StringBuilder();
            }
            int n = arr.length;
            while (i < arr.length) {
                if (!pre && i + 1 < n && arr[i] == '/' && arr[i + 1] == '*') {
                    pre = true;
                    i++;
                } else if (pre && i + 1 < n && arr[i] == '*' && arr[i + 1] == '/') {
                    pre = false;
                    i++;
                } else if (!pre && i + 1 < n && arr[i] == '/' && arr[i + 1] == '/') {
                    break;
                } else if (!pre) {
                    builder.append(arr[i]);
                }
                i++;
            }
            if (!pre && builder.length() > 0) {
                ans.add(builder.toString());
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] source = {"void func(int k) {", "// this function does nothing /*", "   k = k*2/4;", "   k = k/2;*/", "}"};
        List<String> list1 = removeComments(source);
        List<String> list2 = removeComments1(source);
        String cur = "    /*/ declare members;/**/";
        String strs = cur.substring(0, cur.indexOf("/*")) + cur.substring(cur.lastIndexOf("*/") + 2);
        System.out.println(strs);
    }

    public static List<String> removeComments1(String[] source) {
        List<String> ans = new ArrayList<>();
        if (source == null || source.length <= 0) {
            return ans;
        }
        int n = source.length;
        for (int i = 0; i < n; ) {
            String cur = source[i];
            if (cur.contains("//") || cur.contains("/*") || cur.contains("*/")) {
                if (cur.contains("//")) {
                    if (!cur.startsWith("//")) {
                        ans.add(cur.substring(0, cur.indexOf("//")));
                    }
                    i++;
                } else if (cur.contains("/*")) {
                    // 同一行
                    if (cur.contains("/*") && cur.contains("*/")) {
                        String strs = cur.substring(0, cur.indexOf("/*")) + cur.substring(cur.lastIndexOf("*/") + 2);
                        if (strs != null && strs.length() > 0) {
                            ans.add(strs);
                        }
                        i++;
                    } else {
                        String strs = cur.substring(0, cur.indexOf("/*"));
                        int j = i + 1;
                        String next = null;
                        while (j < n) {
                            next = source[j];
                            if (!next.contains("*/")) {
                                j++;
                            } else {
                                break;
                            }
                        }
                        if (next != null && !next.endsWith("*/")) {
                            ans.add(strs + next.substring(next.indexOf("*/") + 2));
                        }
                        i = j + 1;
                    }
                } else {
                    ans.add(cur);
                    i++;
                }
            } else {
                ans.add(cur);
                i++;
            }
        }
        return ans;
    }
}
