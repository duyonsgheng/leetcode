package duys_code.day_09;

/**
 * @ClassName Code_01_LightProblem
 * @Author Duys
 * @Description 来自头条的：狂难的题
 * @Date 2021/10/12 11:22
 **/
public class Code_01_LightProblem {
    /**
     * 题意：
     * 给定一个数组arr，长度为N，arr中的值不是0就是1。arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
     * 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+1栈灯的状态
     * <p>
     * 问题一：如果N栈灯排成一条直线,请问最少按下多少次开关？
     * i为中间位置时，i号灯的开关能影响i-1、i和i+1
     * 0号灯的开关只能影响0和1位置的灯
     * N-1号灯的开关只能影响N-2和N-1位置的灯
     * <p>
     * 问题二：如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来
     * i为中间位置时，i号灯的开关能影响i-1、i和i+1
     * 0号灯的开关能影响N-1、0和1位置的灯
     * N-1号灯的开关能影响N-2、N-1和0位置的灯
     */
    // 问题一：如果N栈灯排成一条直线,请问最少按下多少次开关？这是一个外部信息简化的问题
    // 提示1：外部信息我们从题意可以提炼出，0位置和N-1位置比较特殊，往往边界的讨论就是从开始和结束位置来的，base case也是从边界位置来的
    public static int min1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        // 如果只有一盏灯，如果是0，需要按，如果是1，不需要按
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        //
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : arr[0] ^ 1;
        }
        // 不改变0位置的状态，不改变也就是1的时候不按
        int p1 = process1(arr, 2, arr[0], arr[1]);
        // 改变0位置的状态，当前来的是1位置选择，改变了0，那么就是在1位置选择的时候按了，1位置按了按钮，0位置和1位置都改变了
        int p2 = process1(arr, 2, arr[0] ^ 1, arr[1] ^ 1);
        if (p2 != Integer.MAX_VALUE) {
            // 本次按了
            p2++;
        }
        return Math.min(p1, p2);
    }

    // 设置函数的参数意义
    // nextIndex表示下一个要处理的位置 比如nextIndex = 2 表示当前来到 1位置做选择，preStatus就是 0位置的状态，curStatus就是1位置的状态
    // preStatus表示之前的状态是啥
    // curStatus表示当前的状态是啥
    public static int process1(int[] arr, int nextIndex, int preStatus, int curStatus) {
        // 来到了N-1位置做选择，如果n-2位置和n-1位置相同的，就可以选择按还是不按，否则不论怎样都搞不定
        if (nextIndex == arr.length) {
            return preStatus != curStatus ? Integer.MAX_VALUE : curStatus ^ 1;
        }
        // 还没到最后
        // 如果之前位置是0，那么我当前必须按了
        if (preStatus == 0) {
            curStatus ^= 1; // 按了，当前位置状态改变。变成了下一个过程的preStatus
            int cur = arr[nextIndex] ^ 1; // 这是变成了下一个过程的当前位置状态
            int next = process1(arr, nextIndex + 1, curStatus, cur);
            return next != Integer.MAX_VALUE ? next + 1 : next;
        }
        // 如果之前的位置已经是1，那么当前位置的时候一定不能按了，按了之前就变了0了，没有可以补救的方法了
        else {
            return process1(arr, nextIndex + 1, curStatus, arr[nextIndex]);
        }
    }

    // 问题二：如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来
    // 这里改变了0位置会影响到 n-1位置，改变了n-1位置也会影响到0位置
    public static int min2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        // 如果只有一盏灯，如果是0，需要按，如果是1，不需要按
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        //
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : arr[0] ^ 1;
        }
        // 因为成环了，所以当只有三个的时候，要么同时亮，否则搞不定
        if (arr.length == 3) {
            return (arr[0] != arr[1] || arr[1] != arr[2]) ? Integer.MAX_VALUE : arr[0] ^ 1;
        }
        // 那么接下来我们就要讨论了 因为0位置会影响n-1.所以我们 0 和 1位置讨论来

        // 0位置变，1位置不变
        int p1 = process2(arr, 3, arr[1] ^ 1, arr[2], arr[0] ^ 1, arr[arr.length - 1] ^ 1);
        // 0位置变，1位置变
        int p2 = process2(arr, 3, arr[1], arr[2] ^ 1, arr[0], arr[arr.length - 1] ^ 1);
        // 0位置不变，1位置变
        int p3 = process2(arr, 3, arr[1] ^ 1, arr[2] ^ 1, arr[0] ^ 1, arr[arr.length - 1]);
        // 0位置不变，1位置不变
        int p4 = process2(arr, 3, arr[1], arr[2], arr[0], arr[arr.length - 1]);
        p1 = p1 != Integer.MAX_VALUE ? p1 + 1 : p1;
        p2 = p2 != Integer.MAX_VALUE ? p2 + 1 : p2;
        p3 = p3 != Integer.MAX_VALUE ? p3 + 1 : p3;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }

    // 下一个位置是，nextIndex
    // 当前位置是，nextIndex - 1 -> curIndex
    // 上一个位置是, nextIndex - 2 -> preIndex   preStatus
    // 当前位置是，nextIndex - 1, curStatus
    // endStatus, N-1位置的状态
    // firstStatus, 0位置的状态
    // 返回，让所有灯都亮，至少按下几次按钮
    // 当前来到的位置(nextIndex - 1)，一定不能是1！至少从2开始
    // nextIndex >= 3
    public static int process2(int[] arr, int nextIndex, int preStatus, int curStatus, int firstStatus, int endStatus) {
        if (nextIndex == arr.length) {
            return (firstStatus != endStatus || firstStatus != preStatus) ? Integer.MAX_VALUE : endStatus ^ 1;
        }
        // 当前位置，nextIndex - 1
        // 当前的状态，叫curStatus
        // 如果不按下按钮，下一步的preStatus, curStatus
        // 如果按下按钮，下一步的preStatus, curStatus ^ 1
        // 如果不按下按钮，下一步的curStatus, arr[nextIndex]
        // 如果按下按钮，下一步的curStatus, arr[nextIndex] ^ 1
        // n-2位置会改变endStatus
        int noNextPreStatus = 0;
        int yesNextPreStatus = 0;
        int noNextCurStatus = 0;
        int yesNextCurStatus = 0;
        int noNextEndStatus = 0;
        int yesNextEndStatus = 0;
        if (nextIndex < arr.length - 1) {// 还没来到n-2位置
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = arr[nextIndex];
            yesNextCurStatus = arr[nextIndex] ^ 1;
        } else if (nextIndex == arr.length - 1) { // 来到了n-2位置了，那么n-2位置按不按会影响到n-1位置，也就是我们的endStatus
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = endStatus; // 这里是在计算n-1位置的当前状态，如果不按，继续保持endStatus
            yesNextCurStatus = endStatus ^ 1; // 如果按了，说明发生改变
            noNextEndStatus = endStatus;
            yesNextEndStatus = endStatus ^ 1;
        }
        if (preStatus == 0) {
            int next = process2(arr, nextIndex + 1, yesNextPreStatus, yesNextCurStatus, firstStatus,
                    nextIndex == arr.length - 1 ? yesNextEndStatus : endStatus);
            return next == Integer.MAX_VALUE ? next : next + 1;
        } else {
            return process2(arr, nextIndex + 1, noNextPreStatus, noNextCurStatus, firstStatus,
                    nextIndex == arr.length - 1 ? noNextEndStatus : endStatus);
        }
    }
}
