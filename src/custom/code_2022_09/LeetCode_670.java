package custom.code_2022_09;

/**
 * @ClassName LeetCode_670
 * @Author Duys
 * @Description
 * @Date 2022/9/6 11:32
 **/
// 670. 最大交换
public class LeetCode_670 {

    public int maximumSwap(int num) {
        int[] arr = new int[10];
        int index = 0;
        while (num > 0) {
            arr[index++] = num % 10;
            num /= 10;
        }
        // 高位在后面
        for (int i = index - 1; i > 0; i--) {
            // 去前面找更大的数字，交换
            int max = arr[i];
            int k = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] >= max) {
                    max = arr[j];
                    k = j;
                }
            }
            if (k != -1 && arr[i] != arr[k]) {
                swap(arr, i, k);
                break;
            }

        }
        int ans = 0;
        for (int i = index - 1; i >= 0; i--) {
            ans *= 10;
            ans += arr[i];
        }
        return ans;
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
