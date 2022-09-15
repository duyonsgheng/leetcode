package duys_code.day_35;

import java.util.Arrays;

/**
 * @ClassName Code_09_WatchMovieMaxTime
 * @Author Duys
 * @Description TODO
 * @Date 2021/12/10 13:26
 **/
public class Code_09_WatchMovieMaxTime {
    // 来自小红书
    // 一场电影开始和结束时间可以用一个小数组来表示["07:30","12:00"]
    // 已知有2000场电影开始和结束都在同一天，这一天从00:00开始到23:59结束
    // 一定要选3场完全不冲突的电影来观看，返回最大的观影时间
    // 如果无法选出3场完全不冲突的电影来观看，返回-1
    // 我们吧07：30 这种化成每一天的0-1440分钟的哪一个具体的分钟
    public static int maxEnjoy2(int[][] movies) {
        // 根据开始时间排序
        Arrays.sort(movies, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        return process(movies, 0, 0, 3);
    }

    // 返回最大的观影分钟数
    public static int process(int[][] movies, int index, int time, int res) {
        if (index == movies.length) {
            return res == 0 ? 0 : -1;
        }
        // 不看当前电影
        int p1 = process(movies, index + 1, time, res);

        int next = movies[index][0] >= time && res > 0 ? process(movies, index + 1, movies[index][1], res - 1) : -1;
        int p2 = next != -1 ? movies[index][1] - movies[index][0] + next : -1;
        return Math.max(p1, p2);
    }
}
