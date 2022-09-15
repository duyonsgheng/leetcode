package duys_code.day_45;

import java.util.Arrays;

/**
 * @ClassName Code_01_SplitBuildingBlock
 * @Author Duys
 * @Description 京东的笔试
 * @Date 2021/10/18 13:41
 **/
public class Code_01_SplitBuildingBlock {
    /**
     * // 来自京东笔试
     * // 小明手中有n块积木，并且小明知道每块积木的重量。现在小明希望将这些积木堆起来
     * // 要求是任意一块积木如果想堆在另一块积木上面，那么要求：
     * // 1) 上面的积木重量不能小于下面的积木重量
     * // 2) 上面积木的重量减去下面积木的重量不能超过x
     * // 3) 每堆中最下面的积木没有重量要求
     * // 现在小明有一个机会，除了这n块积木，还可以获得k块任意重量的积木。
     * // 小明希望将积木堆在一起，同时希望积木堆的数量越少越好，你能帮他找到最好的方案么？
     * // 输入描述:
     * // 第一行三个整数n,k,x，1<=n<=200000，0<=x,k<=1000000000
     * // 第二行n个整数，表示积木的重量，任意整数范围都在[1,1000000000]
     * // 样例输出：
     * // 13 1 38
     * // 20 20 80 70 70 70 420 5 1 5 1 60 90
     * // 1 1 5 5 20 20 60 70 70 70 80 90 420 -> 只有1块魔法积木，x = 38
     * // 输出：2
     * // 解释：
     * // 两堆分别是
     * // 1 1 5 5 20 20 (50) 60 70 70 70 80 90
     * // 420
     * // 其中x是一个任意重量的积木，夹在20和60之间可以让积木继续往上搭
     */
    // 先来一个启发性的解答
    // 参数意义：
    // arr - 从小到大的排列数组，表示积木 x-表示最大的差值 都是固定参数
    // 当前来到了第index号积木，积木重量是arr[index]
    // r是魔法积木的剩余个数
    // 潜台词是index之前的所有决定已经做好了，index是这一个堆的，index所在这个堆的开头之前已经决定了
    public static int first(int[] arr, int x, int index, int r) {
        // 已经来到了最后位置了，返回当前只需要一堆
        if (index == arr.length - 1) {
            return 1;
        }
        // 还有积木
        // 因为我们从0开始的
        if (arr[index + 1] - arr[index] <= x) { // 说明当前位置和下一个贴在一切的，不需要分家，也不需要使用魔法积木
            return first(arr, x, index + 1, r);
        } else {
            // 分两种情况，分家或者使用魔法积木黏在一起
            // 分家，不使用魔法积木
            int p1 = first(arr, x, index + 1, r);
            int p2 = Integer.MAX_VALUE;
            // 这里有问题，比如当前x是5 75 - 91 需要几块？是不是4块，所以91-75/5 向上取整 91-75-1/5
            int need = ((arr[index + 1] - arr[index]) - 1) / x;
            if (r >= need) {
                p2 = first(arr, x, index + 1, r - need);
            }
            return Math.min(p1, p2);
        }
    }

    // 最优解
    public static int minSplit(int[] arr, int k, int x) {
        Arrays.sort(arr);
        int len = arr.length;
        // 把差值记录下来，
        int[] needs = new int[len];
        int size = 0;
        // 有多少堆，最后来needs数组中看看怎么弥合，比较划算
        int splits = 1;
        // 看看实际需要分多少堆
        for (int i = 1; i < len; i++) {
            if (arr[i] - arr[i - 1] > x) {
                needs[size++] = arr[i] - arr[i - 1];
                splits++;
            }
        }
        // 如果只有一堆，或者x差值是0(所有的积木重量一致)，亦或者k-魔法积木数量是0
        if (splits == 1 || x == 0 || k == 0) {
            return splits;
        }
        // 看看弥合哪些地方最划算
        // 从小打到的弥合，这样子能消耗最少的魔法积木数量来弥合更多的堆
        Arrays.sort(needs, 0, size);
        for (int i = 0; i < size; i++) {
            // 依然是向上取整
            int need = (needs[i] - 1) / x;
            if (k >= need) {
                splits--;
                k -= need;
            } else
                break;
        }
        return splits;
    }
}
