package custom.code_2023_06;

/**
 * @ClassName LeetCode_1864
 * @Author Duys
 * @Description
 * @Date 2023/6/9 14:18
 **/
// 1864. 构成交替字符串需要的最小交换次数
public class LeetCode_1864 {
    public int minSwaps(String s) {
        char[] arr = s.toCharArray();
        int c0 = 0, c1 = 0;
        for (char c : arr) {
            if (c == '0') {
                c0++;
            } else {
                c1++;
            }
        }
        if (Math.abs(c0 - c1) > 1) {
            return -1;
        }
        if (c0 > c1) {
            // 0开头，那么偶数位上应该都是0，出现了1，说明要被替换
            return process(arr, '1');
        } else if (c1 > c0) {
            // 1开头，那么偶数位上应该都是1，出现了0，说明要被替换
            return process(arr, '0');
        } else
            // 一样多的话，那么就都跑一次，取小的
            return Math.min(process(arr, '0'), process(arr, '1'));
    }

    // 偶数位需要被替换掉的 - even
    public int process(char[] arr, char even) {
        int index = 1;
        int ans = 0;
        for (int i = 0; i < arr.length; i += 2) {
            if (arr[i] == even) { // 遇到了要被替换的，则就替换掉
                while (index < arr.length && arr[index] == even) {
                    index += 2;
                }
                ans++;
                index += 2;
            }
        }
        return ans;
    }
}
