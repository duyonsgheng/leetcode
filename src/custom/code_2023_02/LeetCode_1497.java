package custom.code_2023_02;

/**
 * @ClassName LeetCode_1497
 * @Author Duys
 * @Description
 * @Date 2023/2/2 16:11
 **/
// 1497. 检查数组对是否可以被 k 整除
public class LeetCode_1497 {
    public boolean canArrange(int[] arr, int k) {
        int n = arr.length;
        if (n % 2 != 0) {
            return false;
        }
        int[] mod = new int[k];
        for (int num : arr) {
            // 如果存在负数
            mod[(num % k + k) % k]++;
        }
        for (int i = 1; i + i < k; i++) {
            if (mod[i] != mod[k - i]) {
                return false;
            }
        }
        return mod[0] % 2 == 0;
    }

    public static void main(String[] args) {
        System.out.println((1 % 5 + 5) % 5);
        System.out.println((9 % 5 + 5) % 5);
        //  num % k
        // (num % k + k )%k
    }
}
