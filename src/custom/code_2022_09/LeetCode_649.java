package custom.code_2022_09;

/**
 * @ClassName LeetCode_649
 * @Author Duys
 * @Description
 * @Date 2022/9/5 15:32
 **/
public class LeetCode_649 {
    public String predictPartyVictory(String senate) {
        if (senate == null || senate.length() <= 0) {
            return null;
        }
        char[] arr = senate.toCharArray();
        int RNums = 0; // R阵营的总人数
        int DNums = 0; // D阵营的总人数
        int banR = 0; // 当前R阵营被ban的人
        int banD = 0; // 当前D阵营被ban的人
        int banTotalR = 0; // R阵营被ban的总人数
        int banTotalD = 0; // D阵营的ban的总人数
        int n = arr.length;
        boolean flag = true;
        while (true) {
            for (int i = 0; i < n; i++) {
                char cur = arr[i];
                if (cur == 'R') { // 当前是R阵营的
                    if (flag) {// 没遍历结束
                        RNums++; // R阵营人数增加
                    }
                    // 如果当前没ban的R阵营的人没有
                    // 那么我就不需要区抵消D阵营的人做出的决定，直接ban D阵营的人
                    if (banR == 0) {
                        banD++;
                        banTotalD++;
                        if (banTotalD == DNums && !flag) {
                            return "Radiant";
                        }
                    } else {
                        // 否则，我就需要抵消D阵营ban的R阵营的人的决定
                        banR--;
                        arr[i] = 'r';
                    }
                } else if (cur == 'D') {
                    if (flag) {
                        DNums++;
                    }
                    if (banD == 0) {
                        banR++;
                        banTotalR++;
                        if (banTotalR == RNums && !flag) {
                            return "Dire";
                        }
                    } else {
                        banD--;
                        arr[i] = 'd';
                    }
                }
            }
            //遍历结束
            flag = false;
            if (banTotalD >= DNums) {
                return "Radiant";
            }
            if (banTotalR >= RNums) {
                return "Dire";
            }
        }
    }
}
