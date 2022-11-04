package week.week_2022_11_01;

/**
 * @ClassName Code_04_LeetCode_2375
 * @Author Duys
 * @Description
 * @Date 2022/11/3 11:23
 **/
// 2375. 根据模式串构造最小数字
public class Code_04_LeetCode_2375 {

    public String smallestNumber(String pattern) {
        return String.valueOf(create(pattern.toCharArray(), 0, 0, 0));
    }

    public static int create(char[] arr, int index, int status, int sum) {
        if (index == arr.length + 1) {
            return sum;
        }
        int cur = 0;
        while ((cur = next(status, cur)) != -1) {
            // 第0位开始
            // 或者之前是 I ，那么当前一定大于之前的
            // 或者之前是 D，那么当前一定是小于之前的
            if (index == 0 || (arr[index - 1] == 'I' && sum % 10 < cur) || (arr[index - 1] == 'D' && sum % 10 > cur)) {
                int ans = create(arr, index + 1, status | (1 << cur), sum * 10 + cur);
                if (ans != -1) {
                    return ans;
                }
            }
        }
        return -1;
    }

    public static int next(int status, int num) {
        for (int i = num + 1; i <= 9; i++) {
            if ((status & (1 << i)) == 0) {
                return i;
            }
        }
        return -1;
    }
}
