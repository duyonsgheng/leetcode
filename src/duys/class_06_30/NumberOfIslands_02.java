package duys.class_06_30;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName NumberOfIslands_02
 * @Author Duys
 * @Description 并查集的方式来做
 * @Date 2021/6/30 15:19
 **/
public class NumberOfIslands_02 {
    /**
     * 只是查询每个数的左上就可以，那么在整个矩阵中，第0行 和第0列单独处理，因为第0行没有上，第0列没有左
     */
    public static int numIslands(char[][] board) {
        int row = board.length;// 行
        int col = board[0].length; // 列
        Dot[][] dots = new Dot[row][col];
        List<Dot> dotList = new ArrayList<>();
        // 转化了，把之前的属于 '1'这个字符串的变成了 Dot的内存地址，
        //这里就已经保证了dots这个二维数据不会出现越界或者相应位置不存在的情况
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }
        UnionFindByMap un = new UnionFindByMap(dotList);
        // 第一个循环处理的是第0行
        for (int i = 1; i < row; i++) {
            // 因为处理的都是左 上 第0行 没有上，只有左边，如果当前位置 和 左边位置都是 ‘1’ 那么进行合并
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                un.union(dots[i - 1][0], dots[i][0]);
            }
        }
        // 第二个寻魂处理的是第0列
        for (int i = 1; i < col; i++) {
            // 因为是第0列，没有左边，只要 当前位置是1  当前位置的上边是1 和就合并
            if (board[0][i - 1] == '1' && board[0][i] == '1') {
                un.union(dots[0][i - 1], dots[0][i]);
            }
        }

        // 其他位置的左 上
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1') {
                    // 是上边
                    if (board[i - 1][j] == '1') {
                        un.union(dots[i][j], dots[i - 1][j]);
                    }
                    // 是左边
                    if (board[i][j - 1] == '1') {
                        un.union(dots[i][j], dots[i][j - 1]);
                    }
                }
            }
        }

        return un.getStes();
    }


    // 在map中如果全是 '1' 不好区分，所以使用一个空对象，来进行占位
    public static class Dot {
    }
}
