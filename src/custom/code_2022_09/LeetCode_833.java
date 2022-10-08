package custom.code_2022_09;

import java.util.Arrays;

/**
 * @ClassName LeetCode_833
 * @Author Duys
 * @Description
 * @Date 2022/9/26 18:07
 **/
// 833. 字符串中的查找与替换
public class LeetCode_833 {

    // s = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = indices.length;
        int[][] count = new int[n][2];
        // 把字符串中的位置和 indexes 下标对应起来
        for (int i = 0; i < n; i++) {
            count[i][0] = indices[i];
            count[i][1] = i;
        }
        Arrays.sort(count, (a, b) -> a[0] - b[0]);
        char[] arr = s.toCharArray();
        int last = 0; // 记录上一次处理到哪里了
        int len = arr.length;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int start = count[i][0];
            int src = count[i][1];
            builder.append(new String(arr, last, start - last));
            last = start;
            // 原来字符串中对应了多少字符
            int size = sources[src].length();
            // 防止溢出，把原来字符串对应的数据取出来
            String tar = new String(arr, start, Math.min(size, len - start));
            // 匹配上了
            if (tar.equals(sources[src])) {
                builder.append(targets[src]);
                last = start + size;
            }
        }
        builder.append(new String(arr, last, arr.length - last));
        return builder.toString();
    }
}
