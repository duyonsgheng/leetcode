package custom.code_2023_01;

/**
 * @ClassName LeetCode_2283
 * @Author Duys
 * @Description
 * @Date 2023/1/11 14:23
 **/
// 2283. 判断一个数的数字计数是否等于数位的值
public class LeetCode_2283 {
    public boolean digitCount(String num) {
        int[] count = new int[10];
        char[] arr = num.toCharArray();
        for (char c : arr) {
            count[c - '0']++;
        }
        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i] - '0';
            if (count[i] != cur) {
                return false;
            }
        }
        return true;
    }
}
