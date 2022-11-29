package custom.code_2022_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_1222
 * @Author Duys
 * @Description
 * @Date 2022/11/28 14:13
 **/
// 1222. 可以攻击国王的皇后
public class LeetCode_1222 {
    int[][] dist = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    List<List<Integer>> ans;
    boolean[] visited;
    int[][] arr;

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        // 国王向着8个方向移动
        arr = new int[8][8];
        int n = queens.length;
        visited = new boolean[8];
        for (int i = 0; i < n; i++) {
            arr[queens[i][0]][queens[i][1]] = 1; // 皇后
        }
        ans = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            process(king[0], king[1], dist[i], i);
        }
        return ans;
    }

    public void process(int cx, int cy, int[] dis, int index) {
        if (cx < 0 || cx >= 8 || cy < 0 || cy >= 8) {
            return;
        }
        if (arr[cx][cy] == 1 && !visited[index]) {
            ans.add(Arrays.asList(cx, cy));
            visited[index] = true;
        } else {
            process(cx + dis[0], cy + dis[1], dis, index);
        }
    }

    public static void main(String[] args) {
        LeetCode_1222 lc = new LeetCode_1222();
        int[][] arr = {{0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}};
        int[] k = {0, 0};
        List<List<Integer>> list = lc.queensAttacktheKing(arr, k);
        System.out.println(list);
    }
}
