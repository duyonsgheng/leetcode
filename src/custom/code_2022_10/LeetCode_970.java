package custom.code_2022_10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName LeetCode_970
 * @Author Duys
 * @Description
 * @Date 2022/10/25 13:44
 **/
public class LeetCode_970 {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        // a^i + b^j = x
        // 来一个i和j的上限
        int n = x == 1 ? 0 : (int) Math.sqrt(bound);//(int) (Math.log10(bound) / Math.log10(x));
        int m = y == 1 ? 0 : (int) Math.sqrt(bound);//(int) (Math.log10(bound) / Math.log10(y));
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int cur = (int) (Math.pow(x, i) + Math.pow(y, j));
                if (cur <= bound) {
                    set.add(cur);
                }
            }
        }
        return new ArrayList<>(set);
    }
}
