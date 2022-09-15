package duys.class_06_15;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MaxHappyListNode
 * @Author Duys
 * @Description 多叉树的最大快了值的问题
 * @Date 2021/6/29 16:38
 **/
public class MaxHappyListNode {

    /**
     * 题意：
     * 派对的最大快乐值
     * 规则：
     * 1.如果邀请了a 那么a 的直接上下级都不能再被邀请
     * 2.给一个head，求最大的happy
     *
     * <p>
     * 员工信息的定义如下:
     * class Employee {
     * public int happy; // 这名员工可以带来的快乐值
     * List<Employee> subordinates; // 这名员工有哪些直接下级
     * }
     */

    /**
     * 可能性分析：
     * 1.与x有关
     * 1.1 x来了，那么他的下级就不能来，求一个最大的happy
     * 2.与x无关
     * 2.1 x的下级来了的最大happy
     * 2.2 x的下级不来的最大happy
     */

    /**
     * 信息搜集：
     * 每一个节点而言，来和不来的最大happy
     */

    public static class Employee {
        public int happy;
        public List<Employee> subordinates;

        public Employee(int h) {
            happy = h;
            subordinates = new ArrayList<>();
        }
    }

    public static class Info {
        public int yes;
        public int no;

        public Info(int y, int n) {
            yes = y;
            no = n;
        }
    }

    public static int getMaxHappy(Employee head) {

        Info process = process(head);
        return Math.max(process.no, process.yes);
    }

    public static Info process(Employee x) {
        if (x == null) {
            return new Info(0, 0);
        }
        // 如果没有下级了，那么只能选自己
       /* if (x.subordinates == null) {
            return new Info(x.happy, 0);
        }*/
        // 当前节点的happy
        int yes = x.happy;
        int no = 0;
        for (Employee next : x.subordinates) {
            Info nextInfo = process(next);
            // 当前节点来了，那么就要获取下一级不来的
            yes += nextInfo.no;
            no += Math.max(nextInfo.no, nextInfo.yes);
        }
        return new Info(yes, no);
    }
}
