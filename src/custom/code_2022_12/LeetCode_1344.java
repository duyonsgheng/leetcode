package custom.code_2022_12;

/**
 * @ClassName LeetCode_1344
 * @Author Duys
 * @Description
 * @Date 2022/12/13 11:26
 **/
// 1344. 时钟指针的夹角
public class LeetCode_1344 {
    public double angleClock(int hour, int minutes) {
        int om = 6; // 一个小格子6°
        int oh = 30;// 一个小时总共30°
        double mi = om * minutes;
        double hi = (hour % 12 + minutes / 60.0) * oh;
        double diff = Math.abs(hi - mi);
        return Math.min(diff, 360 - diff);
    }
}
