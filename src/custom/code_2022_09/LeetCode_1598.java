package custom.code_2022_09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LeetCode_1598
 * @Author Duys
 * @Description
 * @Date 2022/9/9 14:39
 **/
// 1598. 文件夹操作日志搜集器
public class LeetCode_1598 {
    public static int minOperations(String[] logs) {
        List<String> list = new ArrayList<>();
        for (String url : logs) {
            if (url.startsWith(".") && url.length() == 2) {
                continue;
            } else if (url.startsWith(".") && url.length() == 3) {
                if (list.size() != 0) {
                    list.remove(list.size() - 1);
                }
            } else {
                list.add(url);
            }
        }
        return list.size();
    }

    public static void main(String[] args) {
        String[] s1 = {"d1/", "d2/", "../", "d21/", "./"};
        System.out.println(minOperations(s1));

        String[] s2 = {"d1/", "d2/", "./", "d3/", "../", "d31/"};
        System.out.println(minOperations(s2));

        String[] s3 = {"d1/", "../", "../", "../"};
        System.out.println(minOperations(s3));
    }

}
