package custom.code_2022_12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_1366
 * @Author Duys
 * @Description
 * @Date 2022/12/14 14:50
 **/
// 1366. 通过投票对团队排名
public class LeetCode_1366 {
    public static String rankTeams(String[] votes) {
        Character[] items = new Character[votes[0].length()];
        for (int i = 0; i < votes[0].length(); i++) {
            items[i] = votes[0].charAt(i);
        }
        Map<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            indexMap.put(items[i], i);
        }
        int[][] sort = new int[items.length][items.length];
        for (int i = 0; i < votes.length; i++) {
            for (int j = 0; j < votes[0].length(); j++) {
                if (indexMap.containsKey(votes[i].charAt(j))) {
                    sort[indexMap.get(votes[i].charAt(j))][j]++;
                }
            }
        }
        Arrays.sort(items, (a, b) -> {
            int[] index1 = sort[indexMap.get(a)];
            int[] index2 = sort[indexMap.get(b)];
            for (int i = 0; i < items.length; i++) {
                // 排名靠前的在前面
                if (index1[i] != index2[i]) {
                    return index2[i] - index1[i];
                }
            }
            return a - b;
        });
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < items.length; i++)
            buffer.append(items[i]);
        return buffer.toString();
    }

    public static void main(String[] args) {
        String[] str = {"WXYA", "XYZA", "YAWX"};
        rankTeams(str);
    }
}
