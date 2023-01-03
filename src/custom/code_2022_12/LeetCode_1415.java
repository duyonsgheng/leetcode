package custom.code_2022_12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName LeetCode_1415
 * @Author Duys
 * @Description
 * @Date 2022/12/28 16:30
 **/
// 1415. 长度为 n 的开心字符串中字典序第 k 小的字符串
public class LeetCode_1415 {
    List<String> list;
    char[] arr = new char[]{'a', 'b', 'c'};

    public String getHappyString(int n, int k) {
        list = new ArrayList<>();
        char[] path = new char[n];
        build(0, path, n);
        if (list.size() < k) {
            return "";
        }
        Collections.sort(list);
        return list.get(k - 1);
    }

    public void build(int index, char[] path, int n) {
        if (index == n) {
            list.add(new String(path));
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (index == 0 || arr[i] != path[index - 1]) {
                path[index] = arr[i];
                build(index + 1, path, n);
            }
        }
    }
}
