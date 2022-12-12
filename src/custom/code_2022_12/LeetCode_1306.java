package custom.code_2022_12;

/**
 * @ClassName LeetCode_1306
 * @Author Duys
 * @Description
 * @Date 2022/12/6 13:25
 **/
public class LeetCode_1306 {
    public static boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        return process(arr, start, visited);
    }

    public static boolean process(int[] arr, int index, boolean[] visited) {
        if (index < 0 || index >= arr.length || visited[index]) {
            return false;
        }
        if (arr[index] == 0) {
            return true;
        }
        visited[index] = true;
        return process(arr, index + arr[index], visited) || process(arr, index - arr[index], visited);
    }

    public static void main(String[] args) {
        int arr[] = {3, 0, 2, 1, 2}, start = 2;
        System.out.println(canReach(arr, start));
    }
}
