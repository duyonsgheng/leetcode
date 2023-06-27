package custom.code_2023_06;

/**
 * @ClassName LeetCode_1904
 * @Author Duys
 * @Description
 * @Date 2023/6/26 10:31
 **/
// 1904. 你完成的完整对局数
public class LeetCode_1904 {
    public static int numberOfRounds1(String loginTime, String logoutTime) {
        int[] start = {Integer.valueOf(loginTime.substring(0, 2)), Integer.valueOf(loginTime.substring(3))};
        int[] end = {Integer.valueOf(logoutTime.substring(0, 2)), Integer.valueOf(logoutTime.substring(3))};
        if (start[0] == end[0] && end[1] - start[1] > 0 && end[1] - start[1] < 15) {
            return 0;
        }
        // 开始时间向上取整，每个小时4次
        int s = start[0] * 4 + (start[1] + 14) / 15;
        // 结束向下取整
        int e = end[0] * 4 + end[1] / 15;
        if (e < s) {
            return 96 - s + e;
        }
        return e - s;
    }

    public static int numberOfRounds(String loginTime, String logoutTime) {
        int[] start = {Integer.valueOf(loginTime.substring(0, 2)), Integer.valueOf(loginTime.substring(3))};
        int[] end = {Integer.valueOf(logoutTime.substring(0, 2)), Integer.valueOf(logoutTime.substring(3))};
        int st = start[0] * 60 + start[1];
        int et = end[0] * 60 + end[1];
        if (et < st) {
            et += 24 * 60;
        }
        // 向下取整
        et = et / 15 * 15;
        return Math.max(0, (et - st) / 15);
    }


    public static void main(String[] args) {
        System.out.println(numberOfRounds("00:01", "00:00"));
    }
}
