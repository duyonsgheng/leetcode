package custom.code_2022_08;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_638
 * @Author Duys
 * @Description
 * @Date 2022/8/30 17:19
 **/
//638. 大礼包
public class LeetCode_638 {
    int max;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // 1.直接购买多少钱
        max = max(price, needs);
        dfs(price, special, needs, 0, 0);
        return max;
    }

    public void dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int index, int used) {
        if (used >= max) {
            return; // 当前比直接买还花费更多
        }
        // 来到最后了，礼包选完了，并且used还没达到最大，就全部购买
        if (index == special.size()) {
            used += max(price, needs);
            if (used < max) {
                max = used;
            }
            return;
        }
        List<Integer> cur = special.get(index);
        // 当前礼包可用
        if (check(needs, cur)) {
            List<Integer> updateNeed = new ArrayList<>();
            for (int i = 0; i < needs.size(); i++) {
                // 用了一批了
                updateNeed.add(needs.get(i) - cur.get(i));
            }
            // 价格
            dfs(price, special, updateNeed, index, used + cur.get(needs.size()));
        }
        dfs(price, special, needs, index + 1, used);
    }

    public int max(List<Integer> price, List<Integer> needs) {
        int total = 0;
        for (int i = 0; i < price.size(); i++) {
            total += price.get(i) * needs.get(i);
        }
        return total;
    }

    public boolean check(List<Integer> needs, List<Integer> shop) {
        for (int i = 0; i < shop.size(); i++) {
            // 本来我只需要3件A货物，但是当前大礼包里面有4件A货物了，超了，不能要
            if (needs.get(i) < shop.get(i)) {
                return false;
            }
        }
        return true;
    }
}
