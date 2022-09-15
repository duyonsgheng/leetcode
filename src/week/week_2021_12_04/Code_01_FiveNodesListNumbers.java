package week.week_2021_12_04;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Code_01_FiveNodesListNumbers
 * @Author Duys
 * @Description
 * @Date 2022/4/14 14:55
 **/

// 来自美团
// 给定一个无向图
// 从任何一个点x出发，比如有一条路径: x -> a -> b -> c -> y
// 这条路径上有5个点并且5个点都不一样的话，我们说(x,a,b,c,y)是一条合法路径
// 这条合法路径的代表，就是x,a,b,c,y所组成的集合，我们叫做代表集合
// 如果从b到y，还有一条路径叫(b,a,c,x,y)，那么(x,a,b,c,y)和(b,a,c,x,y)是同一个代表集合
// 返回这个无向图中所有合法路径的代表集合数量
// 题目给定点的数量n <= 15，边的数量m <= 60
// 所有的点编号都是从0~n-1的
public class Code_01_FiveNodesListNumbers {

    // 思路：
    // 1.我们把任何一个合法的路径都定义成一个整数，就是用二进制位来表示
    // 2.关键是我们怎么去做？已知条件是点的数量比较少，我们可以利用这一点来，深度优先遍历

    // map中已经是这种形式的
    // map[0] 是0可以去的点
    // map[1] 是1可以去的点
    public static int costSum(int[][] map) {
        // 点得数量
        int n = map.length;
        // 把合法得路径都搞成整数存下来
        Set<Integer> set = new HashSet<>();
        // 从每一个点出发，最多走5步
        for (int start = 0; start < n; start++)
            dfs(0, 0, start, map, set);
        return set.size();
    }

    public static void dfs(int status, int step, int cur, int[][] map, Set<Integer> set) {
        // 如果没走过的直接走上去
        if ((status & (1 << cur)) == 0) {
            step++; // 已经走了一步了
            status |= (1 << cur);
            if (step == 5) {
                set.add(status);
            } else {
                // 继续走去
                for (int next : map[cur]) {
                    dfs(status, step, next, map, set);
                }
            }
        }
    }
}
