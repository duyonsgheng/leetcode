package custom.code_2022_09;

/**
 * @ClassName LeetCode_838
 * @Author Duys
 * @Description
 * @Date 2022/9/28 10:38
 **/
// 838. 推多米诺
public class LeetCode_838 {
    // 直接模拟
    public String pushDominoes(String dominoes) {
        char[] arr = dominoes.toCharArray();
        int n = arr.length;
        int i = 0;
        char left = 'L';
        while (i < n) {
            int j = i;
            // 没有被推动过
            while (j < n && arr[j] == '.') {
                j++;
            }
            char right = j < n ? arr[j] : 'R';
            if (left == right) { // 方向相同
                while (i < j) {
                    arr[i++] = left;
                }
            } else if (left == 'R' && right == 'L') { // 方向相反了，往中间走
                int k = j - 1;
                while (i < k) {
                    arr[i++] = 'R';
                    arr[k--] = 'L';
                }
            }
            left = right;
            i = j + 1;
        }
        return new String(arr);
    }
}

