package custom.code_2023_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_1452
 * @Author Duys
 * @Description
 * @Date 2023/1/16 15:07
 **/
//1452. 收藏清单
public class LeetCode_1452 {
    // [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
    //1 <= favoriteCompanies.length <= 100
    //1 <= favoriteCompanies[i].length <= 500
    //1 <= favoriteCompanies[i][j].length <= 20
    // 数据量允许遍历
    // 0 1 4
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Integer> ans = new ArrayList<>();
        int n = favoriteCompanies.size();
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            List<String> cur1 = favoriteCompanies.get(i);
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                List<String> cur2 = favoriteCompanies.get(j);
                Set<String> cur = new HashSet<>(cur2);
                if (cur.containsAll(cur1)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans.add(i);
            }
        }
        return ans;
    }

}
