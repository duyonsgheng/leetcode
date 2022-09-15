package custom.code_2022_08;

/**
 * @ClassName LeetCode_1470
 * @Author Duys
 * @Description
 * @Date 2022/8/29 9:12
 **/
// 1470. 重新排列数组
public class LeetCode_1470 {

    public static int[] shuffle(int[] nums, int n) {
        int[] arr = new int[2 * n];
        for (int i = 0, j = 0; i < 2 * n; i += 2, j++) {
            arr[i] = nums[j];
            arr[i + 1] = nums[j + n];
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 5, 1, 3, 4, 7};
        int n1 = 3;
        int[] shuffle = shuffle(arr1, n1);

        int[] arr2 = {1, 2, 3, 4, 4, 3, 2, 1};
        int n2 = 4;
        int[] shuffle1 = shuffle(arr2, n2);
        System.out.println();
    }
}
