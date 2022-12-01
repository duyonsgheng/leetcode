package custom.code_2022_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName LeetCode_1253
 * @Author Duys
 * @Description
 * @Date 2022/11/29 17:17
 **/
// 1253. 重构 2 行二进制矩阵
public class LeetCode_1253 {
    // 0行 sum = upper
    // 1行 sum = lower
    // 每一列和为colsum[i]
    public static List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> rows1 = new ArrayList<>();
        List<Integer> rows2 = new ArrayList<>();
        int sum = 0;
        for (int num : colsum) {
            sum += num;
        }
        if (sum != (upper + lower)) {
            return ans;
        }
        // 把列和为1 的先填了
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 2) {
                rows1.add(1);
                rows2.add(1);
                upper--;
                lower--;
            } else {
                rows1.add(0);
                rows2.add(0);
            }
        }
        // 然后填哪些可以为0的
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 1) {
                if (upper > 0) {
                    rows1.set(i, 1);
                    upper--;
                } else {
                    rows2.set(i, 1);
                    lower--;
                }
            }
        }
        // 如果搞不定
        if (upper != 0 || lower != 0) {
            return ans;
        }
        ans.add(rows1);
        ans.add(rows2);
        return ans;
    }


    public static void main(String[] args) {
        int upper = 5, lower = 5, colsum[] = {2, 1, 2, 0, 1, 0, 1, 2, 0, 1};
        List<List<Integer>> list = reconstructMatrix(upper, lower, colsum);
        System.out.println(list);
    }
}
