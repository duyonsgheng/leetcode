package custom.code_2022_10;

/**
 * @ClassName LeetCode_1700
 * @Author Duys
 * @Description
 * @Date 2022/10/19 9:06
 **/
// 1700. 无法吃午餐的学生数量
public class LeetCode_1700 {
    public int countStudents(int[] students, int[] sandwiches) {
        // students = [1,1,0,0], sandwiches = [0,1,0,1]
        // 分别记录 喜欢圆形和方形的 , 学生队列无所谓，
        int n = students.length;
        int s1 = 0;
        int s0 = 0;
        for (int i : students) {
            s1 += i;
        }
        s0 = n - s1;
        for (int i = 0; i < n; i++) {
            if (sandwiches[i] == 0 && s0 > 0) {
                s0--;
            } else if (sandwiches[i] == 1 && s1 > 0) {
                s1--;
            } else
                break;
        }
        return s0 + s1;
    }
}
