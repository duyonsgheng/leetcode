package custom.code_2022_07;

import java.util.Arrays;

/**
 * @ClassName LeetCode_274
 * @Author Duys
 * @Description
 * @Date 2022/7/13 17:56
 **/
// 274. H 指数
// 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
public class LeetCode_274 {
    // 就是一个排序
    public static int hIndex(int[] citations) {
        if (citations == null || citations.length < 1) {
            return 0;
        }
        Arrays.sort(citations);
        int h = 0;
        int i = citations.length - 1;
        // 如果当前引用次数 > h 那就就至少是h+1次
        // 一直到h不能继续增加，
        while (i >= 0 && citations[i] > h) {
            i--;
            h++;
        }
        return h;
    }
}
