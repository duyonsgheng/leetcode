package duys_code.day_34;

/**
 * @ClassName Code
 * @Author Duys
 * @Description
 * @Date 2021/12/7 13:02
 **/
public class Code {
    // 全局的结果行号控制
    public static int preAns = -1;

    // 在arr中选k个数 累加和是rest
    public static int[][] find(int[] arr, int k, int rest) {
        // 先默认长度
        int[][] ans = new int[arr.length][k];
        if (arr == null || arr.length < k) {
            return ans;
        }
        long sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
        }
        if (sum < rest) {
            return ans;
        }
        int[] index = new int[1];
        index[0] = -1;
        process(arr, 0, k, rest, new int[k], 0, ans, index);
        int[][] anss = new int[index[0] + 1][];
        for (int i = 0; i <= index[0]; i++) {
            anss[i] = ans[i];
        }
        return anss;
    }


    // index 当前来到数组的哪一个位置上做选择
    // k 还剩下多少数需要选
    // rest 还剩下多少和需要累加
    // path 每一次选择所作的决定
    // ans 答案
    public static void process(int[] arr, int index, int k, int rest,
                               int[] path, int prePath, int[][] ans, int[] preIndex) {
        if (index == arr.length) { // 来到数组的最后的位置了，检查有效性
            if (k == 0 && rest == 0) { // 之前这个决定是有效的
                preIndex[0]++;
                for (int i = 0; i < path.length; i++) {
                    ans[preIndex[0]][i] = path[i];
                }
            }
            return;
        }

        // 其中有一个不为0，另外一个为0，都没戏了没戏了
        if ((k == 0 && rest != 0) || (k != 0 && rest == 0)) {
            return;
        }
        // 尝试
        // 不要当前位置
        process(arr, index + 1, k, rest, path, prePath, ans, preIndex);
        // 要当前位置，但是要满足条件
        if (rest - arr[index] >= 0) {
            // 深度优先遍历
            path[prePath++] = arr[index];
            process(arr, index + 1, k - 1, rest - arr[index], path, prePath, ans, preIndex);
            // 现场还原
            path[--prePath] = 0;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[][] ans = find(arr, 5, 20);
        for (int[] a : ans) {
            for (Integer b : a) {
                System.out.print(" " + b);
            }
            System.out.println();
        }
    }
}
