package duys_code.day_22;

import java.util.Stack;

/**
 * @ClassName Code_04_VisibleMountains
 * @Author Duys
 * @Description
 * @Date 2021/11/10 17:03
 **/
public class Code_04_VisibleMountains {
    /**
     * 可见山峰对问题：
     * 一个不含有负数的数组可以代表一圈环形山，每个位置的值代表山的高度
     * 比如， {3,1,2,4,5}、{4,5,3,1,2}或{1,2,4,5,3}都代表同样结构的环形山
     * 山峰A和山峰B能够相互看见的条件为:
     * 1.如果A和B是同一座山，认为不能相互看见
     * 2.如果A和B是不同的山，并且在环中相邻，认为可以相互看见
     * 3.如果A和B是不同的山，并且在环中不相邻，假设两座山高度的最小值为min。
     * 1)如果A通过顺时针方向到B的途中没有高度比min大的山峰，认为A和B可以相互看见
     * 2)如果A通过逆时针方向到B的途中没有高度比min大的山峰，认为A和B可以相互看见
     * 两个方向只要有一个能看见，就算A和B可以相互看见
     * 给定一个不含有负数且没有重复值的数组 arr，请返回有多少对山峰能够相互看见。
     * <p>
     * 进阶问题
     * 给定一个不含有负数但可能含有重复值的数组arr，返回有多少对山峰能够相互看见。
     */
    // 始终 小找大的原则，不会算重，
    // 1. 当arr中无重复值的时候 满足 2N -3 对，先找数组的max 和 次max 还剩下 N -2 个点。N-2中任何一个x都有小找大原则都有2对。那么就是2(N-2) 再加上 次max到max这一对
    // 2. 当arr中有重复值的时候。麻烦。使用单调栈。
    // 2.1 先把最大值压入栈，然后从最大值开始，找，如果遇到比栈顶小的，。直接压栈，如果遇到比栈顶大的，先弹出栈顶，然后结算。
    // 2.2 结算的时候，刚刚弹出的比即将要压栈的小，算一对，刚刚弹出的比当前栈的栈顶小，算一对
    // 2.3 如果即将压入栈的数和当前栈顶相等，栈顶的数变成两个，下一次遇到的时候结算的时候 有几个(k)就是 2*K + (k-1)*k/2
    // 2.4 栈最后剩下的：清算栈流程
    // 2.4.1 当栈里面的元素 > 2 那么公式是一样的 2*K + (k-1)*k/2
    // 2.4.2 当栈里面的元素 <=2 的时候 当弹出倒数第二大的时候 需要看看倒数第一大的有几个：如果有一个 那么就是 1*k + (k-1)*k/2。如果 > 1 个 就是2*K + (k-1)*k/2
    // 2.4.3 当栈弹出最后一个元素的时候。说明是全局最大，对外没有可见的。对内就是 大于1个的时候就是 (k-1)*k/2，小于等于一个的时候就是0对
    // 以上的k是不同元素不同的个数
    public static int getVisibleNum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int maxIndex = 0; // 如果存在最大值有多个记录
        for (int i = 0; i < N; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        // 准备一个栈，我们使用单调栈进行 小找大的更替和结算时间复杂度O(N)
        // 栈中使用保持 大在栈底，小压大的模式
        Stack<Record> stack = new Stack<>();
        //
        stack.push(new Record(arr[maxIndex]));
        // maxIndex 的下一个位置是哪里
        int nextIndex = nextIndex(maxIndex, N);

        // 可见山峰对的数目
        int ans = 0;
        // 遍历开始。从nextIndex开始到maxIndex 一圈结束
        while (nextIndex != maxIndex) {
            // 当前数要进栈，那么需要满足当前数小于栈顶元素
            // 如果不满足这个单调性，那么就需要依次弹出，然后计算
            while (stack.peek().value < arr[nextIndex]) {
                int k = stack.pop().count;
                ans += 2 * k + getCK2(k);
            }
            // 当前数进栈
            // 如果和栈顶元素相等那么就合并个数
            if (stack.peek().value == arr[nextIndex]) {
                stack.peek().count++;
            }
            // 否则直接封装成record 压栈即可
            else {
                stack.push(new Record(arr[nextIndex]));
            }
            // 当前的index结算完了，该去下一个位置了
            nextIndex = nextIndex(nextIndex, N);
        }
        // 这时候我们遍历结束了，需要看看我们的栈里面还有多少记录需要结算的，
        // 栈中遗留元素的清算过程
        // 当栈中元素大于两个的时候。跟上面的结算方式一样
        while (stack.size() > 2) {
            int lastCount = stack.pop().count;
            // 对外的可见山峰对是 2*k ，对内的是c k 2
            ans += lastCount * 2 + getCK2(lastCount);
        }
        // 当只剩下两个时候比较特殊
        if (stack.size() == 2) {
            int count = stack.pop().count;
            int lastCount = stack.peek().count;
            // 如果我地下压的只剩下一个了，那么我对外的就是当前还剩下的
            ans += getCK2(count);// 这是当前的对内的
            // 对外的，如果比我大的只剩下一个了，那没得说，我当前每一个元素到最大的有一对。那就是count
            if (lastCount == 1) {
                ans += count;
            }
            // 大于了一个，意思就是环形的两条线，分别都能找到大于自己，且是不同位置的数
            else {
                ans += count * 2;
            }
        }
        // 最后来清算栈中唯一剩下的这个最大的
        // 唯一剩下最大的，没有对外的，只有对内的
        ans += getCK2(stack.pop().count);
        return ans;
    }

    // 在环形数组中找到下一个位置
    public static int nextIndex(int i, int size) {
        // 环形中，如果i小于size-1的那么说明不是环的开头
        return i < (size - 1) ? (i + 1) : 0;
    }

    // 在k个中选2个的排列组合
    public static int getCK2(int k) {
        if (k <= 1) {
            return 0;
        }
        return k * (k - 1) / 2;
    }

    // 栈中存放的记录
    // value 是数组中的值
    // count 是当前数出现的次数
    public static class Record {
        public int value;
        public int count;

        public Record(int v) {
            value = v;
            count = 1;
        }
    }
}
