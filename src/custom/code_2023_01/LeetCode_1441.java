package custom.code_2023_01;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1441
 * @Author Duys
 * @Description
 * @Date 2023/1/5 15:58
 **/
// 1441. 用栈操作构建数组
public class LeetCode_1441 {
    // 1 3
    //
    public static List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int len = target.length;
        int pre = 0;
        for (int i = 0; i < len; i++) {
            int diff = target[i] - pre;
            if (diff == 1) {
                ans.add("Push");
            } else {
                while (diff > 1) {
                    ans.add("Push");
                    ans.add("Pop");
                    diff--;
                }
                ans.add("Push");
            }
            pre = target[i] - pre;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int n = 3;
        List<String> list = buildArray(arr, n);
        System.out.println(list);
    }
}
