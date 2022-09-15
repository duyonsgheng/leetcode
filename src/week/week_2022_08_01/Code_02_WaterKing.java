package week.week_2022_08_01;

/**
 * @ClassName Code_02_WaterKing
 * @Author Duys
 * @Description
 * @Date 2022/8/8 13:07
 **/
// 找到数组中的水王数
// 本题来自，大厂刷题班，23节
// 为了讲述下一个题，才重新讲述这个题
// 比较简单
public class Code_02_WaterKing {
    // 每次消掉不同的两个数，并且会消耗掉一点血量，如果数和之前相同，血量增加1点
    public static int waterKing(int[] arr) {
        int cand = 0;
        int hp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (hp == 0) {
                cand = arr[i];
                hp = 1;
            } else if (arr[i] == cand) {
                hp++;
            } else {
                hp--;
            }
        }
        // 说明没有水王数
        if (hp == 0) {
            return -1;
        }
        hp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == cand) {
                hp++;
            }
        }
        return hp > arr.length / 2 ? cand : -1;
    }
}
