package custom.code_2022_11;

/**
 * @ClassName LeetCode_1105
 * @Author Duys
 * @Description
 * @Date 2022/11/10 15:18
 **/
// 1105. 填充书架
public class LeetCode_1105 {

    public static int minHeightShelves(int[][] books, int shelfWidth) {
        int i = books.length;
        int[] dp = new int[i];
        return process(books, 0, shelfWidth, dp);
    }

    public static int process(int[][] books, int index, int limit, int[] dp) {
        if (index == books.length) {
            return 0;// 没书了
        }
        if (dp[index] != 0) {
            return dp[index];
        }
        int curH = 0;
        int leftW = limit;
        int minH = Integer.MAX_VALUE;
        // 当前从index位置开始，看看能摆放几本书
        for (int i = index; i < books.length && leftW >= books[i][0]; i++) {
            leftW -= books[i][0]; // 用掉了
            curH = Math.max(curH, books[i][1]);// 更新层高
            minH = Math.min(minH, curH + process(books, i + 1, limit, dp));
        }
        dp[index] = minH;
        return minH;
    }

    public static int minHeightShelves1(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int curH = 0;
            int leftW = shelfWidth;
            int minH = Integer.MAX_VALUE;
            // 当前从index位置开始，看看能摆放几本书
            for (int j = i; j < n && leftW >= books[j][0]; j++) {
                leftW -= books[j][0]; // 用掉了
                curH = Math.max(curH, books[j][1]);// 更新层高
                minH = Math.min(minH, curH + dp[j + 1]);
            }
            dp[i] = minH;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int[][] books = {{1, 1}, {2, 3}, {2, 3}, {1, 1}, {1, 1}, {1, 1}, {1, 2}};
        int shelf_width = 4;
        System.out.println(minHeightShelves(books, shelf_width));
        System.out.println(minHeightShelves1(books, shelf_width));
    }
}
