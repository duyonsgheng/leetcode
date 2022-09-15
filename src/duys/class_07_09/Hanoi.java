package duys.class_07_09;

/**
 * @ClassName Hanoi
 * @Author Duys
 * @Description 汉诺塔问题
 * @Date 2021/7/9 15:11
 **/
public class Hanoi {

    public static void main(String[] args) {
        hanoi1(3);
    }

    public static void hanoi1(int n) {
        leftToRight(n);
    }

    /**
     * 题意给一个N 请问怎么设计
     */
    public static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("移动 1 从 左 到 右");
            return;
        }
        // 先把左边 n- 1 的 放入到中间
        leftToMind(n - 1);
        // 这一步类似移动直接打印
        System.out.println("移动 " + n + " 从 左 到 右");

        // 然后把中间n-1 的放到右边去
        mindToRight(n - 1);

    }

    private static void mindToRight(int n) {
        if (n == 1) {
            System.out.println("移动 1 从 中 到 右");
            return;
        }
        // 从中间到右边
        // 先把中间 n-1 放到左边
        mindToLeft(n - 1);
        //然后把中间最大的放入到右边
        System.out.println("移动 " + n + " 从 中 到 右");
        // 然后把左边的 n-1 放入到右边
        leftToRight(n - 1);
    }

    private static void mindToLeft(int n) {
        if (n == 1) {
            System.out.println("移动 1 从 中 到 左");
            return;
        }
        mindToRight(n - 1);
        System.out.println("移动 " + n + " 从 中 到 左");
        // 最后把n-1 移动到左边
        rightToLeft(n - 1);
    }

    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("移动 1 从 右 到 左");
            return;
        }
        rightToMind(n - 1);
        System.out.println("移动 " + n + " 从 右 到 左");
        // 最后把n-1 移动到左边
        mindToLeft(n - 1);
    }

    private static void leftToMind(int n) {
        if (n == 1) {
            System.out.println("移动 1 从 左 到 中");
            return;
        }
        // 从左到中间
        // 先把左边 n-1 放到左边
        leftToRight(n - 1);
        //然后把左边最大的放入到中间
        System.out.println("移动 " + n + " 从 左 到 中");
        // 然后把右边的 n-1 放入到中间
        rightToMind(n - 1);
    }

    private static void rightToMind(int n) {
        if (n == 1) {
            System.out.println("移动 1 从 右 到 中");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("移动 " + n + " 从 右 到 中");
        // 最后把n-1 移动到左边
        leftToMind(n - 1);
    }

    public static void hanoi2(int n) {
        func(3, "一柱子", "三柱子", "二柱子");
    }

    public static void func(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("移动 1 从 " + from + " 到 " + to);
        } else {
            // 先把 n-1 从from移动到 other
            func(n - 1, from, other, to);
            // 直接移动 n 从from 到 to
            System.out.println("移动 " + n + "  从 " + from + " 到 " + to);
            // 把other位置上的n-1 移动到to上去
            func(n - 1, other, to, from);
        }
    }
}
