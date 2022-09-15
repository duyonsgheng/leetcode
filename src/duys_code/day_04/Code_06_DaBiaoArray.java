package duys_code.day_04;

/**
 * @ClassName Code_06_DaBiaoArray
 * @Author Duys
 * @Description
 * @Date 2021/9/22 19:16
 **/
public class Code_06_DaBiaoArray {
    /**
     * 生成长度为size的达标数组，什么叫达标？
     * 达标：对于任意的 i<k<j，满足 [i] + [j] != [k] * 2
     * 给定一个正数size，返回长度为size的达标数组
     */
    /**
     * 2a + ac ! = 2 * 2b
     * 2a+1 + 2c+1 != 2 *(2b+1)
     */
    /**
     * 一个奇数+一个偶数一定不是某一个数的2倍
     * 比如现在需要搞定一个size为7的数组
     * 那么先搞定 7/2向上取整 为4长度的，
     * 搞定为4的长度需要搞定 2长度的
     * 搞定为2长度的需要搞定 1长度的
     */
    public static int[] find(int size) {
        if (size == 1) {
            return new int[]{1};
        }
        int mid = (size + 1) >> 1;
        int base[] = find(mid);
        int ans[] = new int[size];
        int index = 0;
        // 奇数域
        for (; index < mid; index++) {
            ans[index] = base[index] * 2 - 1;
        }
        // 偶数域
        for (int i = 0; index < size; i++, index++) {
            ans[index] = base[i] * 2;
        }
        return ans;
    }
}
