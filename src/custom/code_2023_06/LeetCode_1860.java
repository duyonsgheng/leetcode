package custom.code_2023_06;

/**
 * @ClassName LeetCode_1860
 * @Author Duys
 * @Description
 * @Date 2023/6/8 10:11
 **/
// 1860. 增长的内存泄露
public class LeetCode_1860 {
    public int[] memLeak(int memory1, int memory2) {
        int m = 1;
        while (m <= Math.max(memory1, memory2)) {
            if (memory1 < memory2) {
                memory2 -= m;
            } else {
                memory1 -= m;
            }
            m++;
        }
        return new int[]{m, memory1, memory2};
    }

}
