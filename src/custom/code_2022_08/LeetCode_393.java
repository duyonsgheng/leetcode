package custom.code_2022_08;

/**
 * @ClassName LeetCode_393
 * @Author Duys
 * @Description
 * @Date 2022/8/8 15:27
 **/
//393. UTF-8 编码验证
public class LeetCode_393 {

    public boolean validUtf8(int[] data) {
        int n = data.length;
        for (int i = 0; i < n; ) {
            int cur = data[i];
            int index = 7;
            // 每个高位有几个1
            while (index >= 0 && ((cur >> index) & 1) == 1) {
                index--;
            }
            int cnt = 7 - index;
            if (cnt == 1 || cnt > 4) {
                return false;
            }
            if (i + cnt - 1 >= n) {
                return false;
            }
            for (int k = i + 1; k < cnt + i; k++) {
                if ((((data[k] >> 7) & 1) == 1) && (((data[k] >> 6) & 1) == 0)) {
                    continue;
                }
                return false;
            }
            if (cnt == 0) {
                i++;
            } else {
                i += cnt;
            }
        }
        return true;
    }
}
