package duys_code.day_38;

/**
 * @ClassName Code_04_621_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/task-scheduler/
 * @Date 2021/12/17 17:19
 **/
public class Code_04_621_LeetCode {

    /**
     * 先把指令最多的拿出来。因为一定要满足，先把最多的拿出来，然后根据每个相隔K，然后再根据少的一次填入就好了
     * 先词频最大的
     * 然后词频依次变小的往空格去填
     */
    // 1. 先把次数出现最多的记录下来，如果存在多个，那么就这几个最多的当成一体来处理，比如 a3次，b3次  k =3 ab__ab__ab
    // 2. 根据第一步中形成的空格来填空，尽可能的来填满空格
    public int leastInterval(char[] tasks, int n) {
        // 词频统计
        int[] count = new int[256];
        // 出现最多的任务，出现了几次
        int maxCount = 0;
        for (char task : tasks) {
            count[task]++;
            maxCount = Math.max(maxCount, count[task]);
        }
        // 有几种任务出现了最多次数
        int maxTaskCount = 0;
        for (int task = 0; task < 256; task++) {
            if (count[task] == maxCount) {
                maxTaskCount++;
            }
        }
        // 我们的思路是划分成几组，但是最后一组的后面我们不添加__ ,剩下的就是我们需要安排的
        int excluedLast = tasks.length - maxTaskCount;
        // 有多少个空格，统一的把所有的全部当成空格，然后来填
        int spaces = (n + 1) * (maxCount - 1);

        // 看看我们已知的能不能把空格填满
        int restSpaces = spaces - excluedLast >= 0 ? spaces - excluedLast : 0;

        // 如果没有空格剩下，就说明刚刚好，如果有空格剩下，那么就加上空格
        return tasks.length + restSpaces;
    }
}
