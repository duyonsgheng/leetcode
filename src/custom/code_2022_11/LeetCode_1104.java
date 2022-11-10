package custom.code_2022_11;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1104
 * @Author Duys
 * @Description
 * @Date 2022/11/10 11:05
 **/
// 1104. 二叉树寻路
public class LeetCode_1104 {

    public List<Integer> pathInZigZagTree(int label) {
        // 计算层
        int level = 1;
        while (end(level) < label) {
            level++;
        }
        int[] ans = new int[level];
        int index = level - 1;
        int cur = label;
        while (index >= 0) {
            ans[index--] = cur;
            int total = (int) Math.pow(2, level - 1);
            int start = start(level);
            int end = end(level);
            if (level % 2 == 0) { // 从右往左
                int j = total / 2;
                for (int i = start; i <= end; i += 2, j--) {
                    if (i == cur || (i + 1 == cur)) {
                        break;
                    }
                }
                int pre = start(level - 1);
                while (j-- > 1) {
                    pre++;
                }
                cur = pre;
            } else {
                // 当前层为奇数层，则当前层节点「从左往右」数值递增，相应计算上一层下标也应该「从左往右」
                int j = 1;
                for (int i = start; i <= end; i += 2, j++) {
                    if (i == cur || (i + 1) == cur) break;
                }
                int pre = end(level - 1);
                while (j-- > 1) pre--;
                cur = pre;
            }
            level--;
        }
        List<Integer> list = new ArrayList<>();
        for (int i : ans) {
            list.add(i);
        }
        return list;
    }

    // 第level层开始的值
    public int start(int level) {
        return (int) Math.pow(2, level - 1);
    }

    public int end(int level) {
        int s = start(level);
        return s * 2 - 1;
    }
}
