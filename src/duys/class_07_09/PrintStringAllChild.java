package duys.class_07_09;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PrintAllChild
 * @Author Duys
 * @Description 打印字符串所有的子序列
 * @Date 2021/7/9 16:41
 **/
public class PrintStringAllChild {
    // s -> "abc" ->

    /**
     * 和二叉树的递归一样，先可能性分析，设计整个迭代过程可能遇到的决策
     *
     * @param str
     * @return
     */
    public static List<String> subs(String str) {
        char[] strs = str.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(strs, 0, ans, path);
        return ans;
    }

    public static void process1(char[] chars, int index, List<String> ans, String path) {
        if (index == chars.length) {
            ans.add(path);
            return;
        }
        // 分两步走
        // 第一步不要index位置的所有结果
        process1(chars, index + 1, ans, path);
        // 第二步要了index位置的所有结果
        process1(chars, index + 1, ans, path + chars[index]);

    }
}
