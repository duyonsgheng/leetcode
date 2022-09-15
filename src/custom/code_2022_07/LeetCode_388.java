package custom.code_2022_07;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_388
 * @Author Duys
 * @Description
 * @Date 2022/7/28 13:09
 **/
// 388. 文件的最长绝对路径
//链接：https://leetcode.cn/problems/longest-absolute-file-path
public class LeetCode_388 {
    // dir \n\t
    //      subdir1 \n \t \t
    //              file1.ext \n\t\t
    //              subsubdir1 \n\t
    //      subdir2 \n\t\t
    //              subsubdir2 \n\t\t\t
    //                  file2.ext
    // 一个 \n\t 同级 \n\t\t 下一级 \n\t\t\t 再下一级
    // dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext
    public static int lengthLongestPath(String input) {
        if (input == null || input.length() < 1) {
            return 0;
        }
        Map<Integer, String> map = new HashMap<>();
        int n = input.length();
        String ans = null;
        for (int i = 0; i < n; ) {
            int level = 0;
            // 先看看在那一层
            while (i < n && input.charAt(i) == '\t' && level++ >= 0) {
                i++;
            }
            int j = i;
            boolean isDir = true;
            while (j < n && input.charAt(j) != '\n') {
                if (input.charAt(j++) == '.') {
                    isDir = false;
                }
            }
            String cur = input.substring(i, j);
            String pre = map.getOrDefault(level - 1, null);
            String path = pre == null ? cur : pre + "/" + cur;
            if (isDir) {
                map.put(level, path);
            } else if (ans == null || path.length() > ans.length()) {
                ans = path;
            }
            i = j + 1;
        }
        return ans == null ? 0 : ans.length();
    }


    public static void main(String[] args) {
        String str = "dir\\n\\tsubdir1\\n\\t\\tfile1.ext\\n\\t\\tsubsubdir1\\n\\tsubdir2\\n\\t\\tsubsubdir2\\n\\t\\t\\tfile2.ext\n";
        char[] chars = str.toCharArray();
        System.out.println(lengthLongestPath(str));
    }
}
