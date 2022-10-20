package custom.code_2022_10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName LeetCode_937
 * @Author Duys
 * @Description
 * @Date 2022/10/19 9:27
 **/
//937. 重新排列日志文件
public class LeetCode_937 {
    // 字母日志：除标识符之外，所有字均由小写字母组成
    // 数字日志：除标识符之外，所有字均由数字组成
    // 1.所有 字母日志 都排在 数字日志 之前
    // 2.字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序
    // 3.数字日志 应该保留原来的相对顺序。

    // 1.将所有的日志转为一个实体
    // 2.根据题意定义排序规则
    public String[] reorderLogFiles(String[] logs) {
        int n = logs.length;
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Node(logs[i], i));
        }
        Collections.sort(list, (a, b) -> {
            if (a.type != b.type) { // 不是同一个类型
                return a.type - b.type; // 字母再数字的前边
            }
            if (a.type == 1) {
                return a.index - b.index;// 数字的按照原来的顺序排列
            }
            // 内容不同，按照内容排序，内容相同，按照标识排序
            return !a.text.equals(b.text) ? a.text.compareTo(b.text) : a.head.compareTo(b.head);
        });
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) ans[i] = list.get(i).log;
        return ans;
    }

    class Node {
        int type;
        int index;
        String log;
        String head;
        String text;

        public Node(String log, int i) {
            this.log = log;
            index = i;
            int len = log.length();
            int idx = 0;
            while (idx < len && log.charAt(idx) != ' ') idx++;
            head = log.substring(0, idx);
            text = log.substring(idx + 1);
            type = Character.isDigit(text.charAt(0)) ? 1 : 0;
        }
    }
}
