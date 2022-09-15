package pro;

/**
 * @ClassName KMP
 * @Author Duys
 * @Description
 * @Date 2022/5/25 11:21
 **/
// indexOf
public class KMP {

    // kmp两个步骤-中点啊。。。。

    /**
     * 步骤1：求得s2的nextArray
     * 步骤2：根据求的nextArray然后去求s1中是否包含了s2
     */
    public static int indexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s2.length() > s1.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] next = nextArras(str2);
        // 在课上的例子中，对应s1和s2 分别画圈的位置，y是会在整个循环的中可能会减小
        // 减小的原因是因为当x和y不等了，那么我们需要把y的nextArray信息拿出来，然后y去到nextArray中的信息位置去。继续比对，加速整个比对的流程
        int x = 0;
        int y = 0;
        // 既然x一直在扩大
        // y可能在扩大，也可能在减小
        // 当x越界，但是y还没有越界，不用想了，一定没戏
        // 当x不越界，但是y已经越界了，说明搞定了，从x出发就已经搞定所有了的
        while (x < str1.length && y < str2.length) {
            // 1.一路都相等
            if (str1[x] == str2[y]) {
                x++;
                y++;
            }
            // 从求nextArray的过程我们得知，当next[y] = -1的时候，
            // 代表y 已经来到了0位置了
            else if (next[y] == -1) {
                //y = 0; // 可写可不写
                x++;
            }
            // 既不相等，y也没有达到0位置
            else {
                // 让我们从y的信息位置开始继续比较，y往前走
                y = next[y];
            }
        }

        // 两种越界情况，一句话来概括
        return y == str2.length ? x - y : -1;
    }

    // 求nextArray信息

    /**
     * 约定next[0] =-1 next[1] = 0
     * 从2位置开始
     * 普遍位置的求法
     * 来到i位置，我们看看i-1位置的信息。如果i-1位置是 7长度，那么我们直接看第7位置的字符是不是和i-1位置相等，
     * 如果相等，直接就是 7+1
     * 如果不等，那么我们就去找7位置的信息，看看是不是和i-1相等，如果相等，就是7位置信息+1，否则重复上面的过程
     */
    public static int[] nextArras(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;

        // 从2位置开始
        int index = 2;
        // 我来到当前位置往前找的时候需要找的位置是哪一个位置，初始肯定是0，因为来到2位置需要看1位置的next信息
        int cur = 0; // next[index-2]; 也可以写成这样
        while (index < str2.length) {
            // 如果字符相同，直接就是+1了
            if (str2[index - 1] == str2[cur]) {
                // 这里 如果相同了，直接就是从cur的下一个位置开始和index-1的位置比较了，减少了不必要的迭代流程
                next[index++] = ++cur;
            } else if (cur > 0) {
                cur = next[cur];// 往前走。
            }
            // 如果实在是不等，cur也到了-1了。说明确实不行
            else {
                next[index++] = 0;
            }
        }
        return next;
    }
}
