package custom.code_2023_05;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1718
 * @Author Duys
 * @Description
 * @Date 2023/5/8 11:40
 **/
// 1718. 构建字典序最大的可行序列
public class LeetCode_1718 {
    int[] ans;
    // 标记是否找到一个解了
    boolean flag = false;

    public int[] constructDistancedSequence(int n) {
        // 除了1出现1次，2到n出现两次，那么返回数组的长度为2n-1
        ans = new int[2 * n - 1];
        // 和ans等长，用于记录当前遍历的一个序列
        int[] arr = new int[2 * n - 1];
        // 初始为-1，表示这个位置没有被用过
        Arrays.fill(arr, -1);
        // 记录1到n各自是否已经填好了
        boolean[] visited = new boolean[n + 1];
        // 第二个参数0表示先填第一位，优先使用大数填
        dfs(n, 0, arr, visited);
        // 注意最后不能返回arr，arr元素最后都会被重置为-1，所以要多创建一个ans变量
        return ans;
    }

    // 当前在填arr[index]
    private void dfs(int n, int index, int[] arr, boolean[] visited) {
        // 找到一个解了，就不再递归，因为按我们的思路，第一个解一定是字典序最大的
        // 注意，这里flag是必须的，不然会遍历完所有状态，ans就是字典序最小的了
        if (flag) {
            return;
        }
        // arr所有位置都填好了
        if (index == 2 * n - 1) {
            flag = true;
            // 将arr复制到ans，更新ans
            System.arraycopy(arr, 0, ans, 0, 2 * n - 1);
            return;
        }
        // 当前位置index已经填好了，那么就看下一位置
        if (arr[index] != -1) {
            dfs(n, index + 1, arr, visited);
            return;
        }
        // 当前位置index还没填，从n开始递减地看能不能填
        for (int i = n; i >= 1; i--) {
            // i已经用过了，继续看i-1
            if (visited[i]) {
                continue;
            }
            // 到这里表示i没用过，分情况讨论i是不是1
            if (i == 1) {
                // 如果i是1，只需要将arr[index]赋值为i(即1)
                arr[index] = 1;
                visited[i] = true;
                dfs(n, index + 1, arr, visited);
                arr[index] = -1;
                visited[i] = false;
            } else {
                // 如果i不是1，需要将arr[index+i]也赋值为i
                // 当然，如果这里arr[index + i] == -1不成立的话，说明i还是不能放在这里，需要继续循环看i-1
                if (index + i < 2 * n - 1 && arr[index + i] == -1) {
                    // 到了这里说明i可以放在index和index+i处
                    arr[index] = arr[index + i] = i;
                    // 标记i已经填好了
                    visited[i] = true;
                    // 递归
                    dfs(n, index + 1, arr, visited);
                    // 状态重置
                    arr[index] = arr[index + i] = -1;
                    visited[i] = false;
                }
            }
        }
    }

}
