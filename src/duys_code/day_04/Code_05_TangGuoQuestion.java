package duys_code.day_04;

/**
 * @ClassName Code_05_TangGuoQuestion
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/candy/
 * @Date 2021/9/22 18:36
 **/
public class Code_05_TangGuoQuestion {
    /**
     * 老师想给孩子们分发糖果，有 N个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
     * 每个孩子至少分配到 1 个糖果。
     * 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
     * 那么这样下来，老师至少需要准备多少颗糖果呢？
     */
    /**
     * 需要辅助数组：
     * 遍历的时候，按照从左到右生成left数组，右边的比我大就++ 不比我大就回到1
     * 按照从右到左生成right数组，左边比我的就++，不比我大就回到1.最右边和最左边都是1
     */
    public static int candy(int[] ratings) {
        if (ratings == null || ratings.length < 1) {
            return 0;
        }
        int ans = 0;
        int N = ratings.length;
        int[] left = new int[N];
        left[0] = 1;
        int[] right = new int[N];
        right[N - 1] = 1;
        for (int i = 1, j = N - 2; i < N && j >= 0; i++, j--) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
            if (ratings[j] > ratings[j + 1]) {
                right[j] = right[j + 1] + 1;
            } else {
                right[j] = 1;
            }
        }
        for (int k = 0; k < N; k++) {
            ans += Math.max(left[k], right[k]);
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 2};
        // left = 1 , 1, 2
        System.out.println(candy(arr));
    }


    // 增加一个原则：如果相邻位置相同，则分一样多的糖
    public static int candy2(int[] ratings) {
        if (ratings == null || ratings.length < 1) {
            return 0;
        }
        int ans = 0;
        int N = ratings.length;
        int[] left = new int[N];
        left[0] = 1;
        int[] right = new int[N];
        right[N - 1] = 1;
        for (int i = 1, j = N - 2; i < N && j >= 0; i++, j--) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else if (ratings[i] == ratings[i - 1]) {
                left[i] = left[i - 1];
            } else {
                left[i] = 1;
            }
            if (ratings[j] > ratings[j + 1]) {
                right[j] = right[j + 1] + 1;
            } else if (ratings[j] == ratings[j + 1]) {
            } else {
                right[j] = right[j + 1];
            }
        }
        for (int k = 0; k < N; k++) {
            ans += Math.max(left[k], right[k]);
        }
        return ans;

    }
}
