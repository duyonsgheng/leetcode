package week.week_2022_09_02;

/**
 * @ClassName Code_04_RunThroughZero1
 * @Author Duys
 * @Description
 * @Date 2022/9/15 14:08
 **/
// 贪心的解法
// 有一个数组包含0、1、2三种值，
// 有n次修改机会，第一种将所有连通的1变为0，修改次数-1
// 第二种将所有连通的2变为1或0，修改次数-2，
// 返回n次修改机会的情况下连通的0最长能是多少？
// 1 <= arr长度 <=10^6
// 0 <= 修改机会 <=10^6
public class Code_04_RunThroughZero1 {

    // 如果只是想压线AC这个题，贪心就可以了 二分
    // 如果想做到更好的复杂度。hard中的hard
    public static int cost(int[] arr, int l, int r) {
        // 分析，整个数组的情况我们可以分为四种
        //1.0联通区域，不用管
        //2.1联通区域，代价可算
        //3.2联通 代价可算
        //4.1，2混合，代价是2倍的2的联通区域+1的联通区域
        int num_0 = 0;
        int num_2 = 0;
        int n = r - l + 1;
        for (int i = l; i <= r; i++) {
            num_0 += arr[i] == 0 ? 1 : 0;
            num_2 += arr[i] == 2 ? 1 : 0;
        }
        if (num_0 == n) {
            return 0;
        }
        if (num_2 == n) {
            return 2;
        }
        // 2联通区域有几个
        int area_2 = arr[l] == 2 ? 1 : 0;
        for (int i = l; i < r; i++) {
            if (arr[i] != 2 && arr[i + 1] == 2) {
                area_2++;
            }
        }
        boolean has_1 = false;
        int area_1 = 0; // 1的连通区域
        for (int i = l; i <= r; i++) {
            if (arr[i] == 0) {
                if (has_1) {
                    area_1++;
                }
                has_1 = false;
            }
            if (arr[i] == 1) {
                has_1 = true;
            }
        }
        if (has_1) {
            area_1++;
        }
        return 2 * area_2 + area_1;
    }
}
