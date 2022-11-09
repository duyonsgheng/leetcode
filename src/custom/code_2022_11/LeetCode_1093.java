package custom.code_2022_11;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1093
 * @Author Duys
 * @Description
 * @Date 2022/11/9 15:46
 **/
// 1093. 大样本统计
public class LeetCode_1093 {
    public double[] sampleStats(int[] count) {
        double minimum = Integer.MAX_VALUE  // 最小值
                , maximum = Integer.MIN_VALUE  // 最大值
                , mean = 0  // 平均数
                , median = -1  // 中位数
                , mode = -1; // 众数
        int len = 256; // 频率样本长度固定为256
        // 记录出现过的数字的前缀和
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{-1, 0});
        int maxTime = 0;
        for (int i = 0; i < len; i++) {
            if (count[i] == 0) {
                continue;
            }
            // 统计出现过的数字的前缀和
            list.add(new int[]{i, list.get(list.size() - 1)[1] + count[i]});
            if (i < minimum) {
                minimum = i; // 最小
            }
            if (i > maximum) {
                maximum = i; // 最大
            }
            if (count[i] > maxTime) {
                maxTime = count[i];
                mode = i;// 记录众数
            }
        }
        // 所有的数出现的总数
        int total = list.get(list.size() - 1)[1];
        // 求平均数
        for (int i = 0; i < len; i++) {
            mean += (double) i * count[i] / total;
        }
        int tar = total / 2;
        int l = -1;
        int r = list.size();
        int mid = 0;
        // 查找大于等于目标和的最左位置
        while (l + 1 != r) {
            mid = l + r >> 1;
            if (list.get(mid)[1] < tar) {
                l = mid;
            } else {
                r = mid;
            }
        }
        if (total % 2 == 1) {
            if (list.get(r)[1] == tar) {
                median = list.get(r + 1)[0];
            } else {
                median = list.get(r)[0];
            }
        } else {
            if (list.get(r)[1] == tar) {
                median = (double) (list.get(r)[0] + list.get(r + 1)[0]) / 2;
            } else {
                median = list.get(r)[0];
            }
        }
        return new double[]{minimum, maximum, mean, median, mode};
    }
}
