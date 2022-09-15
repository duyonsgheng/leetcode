package duys_code.day_27;

import java.util.Arrays;

/**
 * @ClassName Code_02_781_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/rabbits-in-forest/
 * @Date 2021/11/22 14:58
 **/
public class Code_02_781_LeetCode {

    // 比如[1,2,3,1,2,3]
    // 排序后[1,1,2,2,3,3]
    // 0位置说还有1人，1位置说还有1人，那正好两个成为1组
    // 2位置说还有2两人，意思是总共3人，还差一个人，所以3个人，
    public int numRabbits(int[] answers) {
        if (answers == null || answers.length == 0) {
            return 0;
        }
        Arrays.sort(answers);
        int ans = 0;
        int count = 1;
        int cur = answers[0];
        for (int i = 1; i < answers.length; i++) {
            if (answers[i] == cur) {
                count++;
            } else {
                // 如果相等，只计数，不等就结算答案  ((count + cur) / (cur + 1)) 向上取整，表示需要几组，每一组多少人 cur+1
                ans += ((count + cur) / (cur + 1)) * (cur + 1);
                cur = answers[i];
                count = 1;
            }
        }
        return ans + (((count + cur) / (cur + 1)) * (cur + 1));
    }
}
