package custom.code_2022_11;

/**
 * @ClassName LeetCode_1053
 * @Author Duys
 * @Description
 * @Date 2022/11/8 10:43
 **/
// 1053. 交换一次的先前排列
public class LeetCode_1053 {
    public int[] prevPermOpt1(int[] arr) {
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                int j = i + 1;
                int index = i + 1;
                while (j < arr.length && arr[j] < arr[i]) {
                    index = arr[j] > arr[j - 1] ? j : index;
                    j++;
                }
                int tmp = arr[i];
                arr[i] = arr[index];
                arr[index] = tmp;
                break;
            }
        }
        return arr;
    }
}
