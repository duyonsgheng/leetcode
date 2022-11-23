package custom.code_2022_11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName LeetCode_1169
 * @Author Duys
 * @Description
 * @Date 2022/11/21 11:31
 **/
// 1169. 查询无效交易
public class LeetCode_1169 {


    public static List<String> invalidTransactions(String[] transactions) {
        // ["alice,20,800,mtv","alice,50,100,beijing"]
        List<String> ans = new ArrayList<>();
        int n = transactions.length;
        String[][] infos = new String[n][4];
        Map<String, List<Integer>> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            infos[i] = transactions[i].split(",");
            String name = infos[i][0], time = infos[i][1], money = infos[i][2], city = infos[i][3];
            if (Integer.valueOf(money) > 1000) {
                set.add(i);
            }
            List<Integer> orDefault = map.getOrDefault(name, new ArrayList<>());
            for (int names : orDefault) {
                String[] info = infos[names];
                if (!info[3].equals(city) && Math.abs(Integer.valueOf(info[1]) - Integer.valueOf(time)) <= 60) {
                    set.add(names);
                    set.add(i);
                }
            }
            orDefault.add(i);
            map.put(name, orDefault);
        }
        for (int i : set) {
            ans.add(transactions[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] transactions = {"alice,20,800,mtv", "alice,50,100,beijing"};
        invalidTransactions(transactions);
    }
}
