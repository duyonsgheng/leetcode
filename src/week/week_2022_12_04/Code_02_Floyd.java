package week.week_2022_12_04;

/**
 * @ClassName Code_02_Floyd
 * @Author Duys
 * @Description
 * @Date 2022/12/29 10:35
 **/
public class Code_02_Floyd {

    // 三层for循环
    // 第一层 枚举中途点
    // 第二层 枚举from点
    // 第三层 枚举to点
    public void floyd(int[][] distance) {
        int n = distance.length;
        //
        for (int jump = 0; jump < n; jump++) {
            for (int from = 0; from < n; from++) {
                for (int to = 0; to < n; to++) {
                    // 经过jump到from和to都有路
                    // 并且 from到to的距离是大于了 jump到from+ jump到to的距离。那么就更新
                    if (distance[from][jump] != Integer.MIN_VALUE && distance[jump][to] != Integer.MIN_VALUE
                            && distance[from][to] > distance[from][jump] + distance[jump][to]) {
                        distance[from][to] = distance[from][jump] + distance[jump][to];
                    }
                }
            }
        }
    }
}
