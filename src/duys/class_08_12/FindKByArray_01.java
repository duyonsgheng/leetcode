package duys.class_08_12;

/**
 * @ClassName FindKByArray_01
 * @Author Duys
 * @Description
 * @Date 2021/8/12 14:01
 **/
public class FindKByArray_01 {
    /**
     * 给定一个无序数组，长度为N，给定一个正整数K，返回从大到小的前k个数（k一定小于N）
     *
     * 使用三种方式：
     * 1.O(N*logN) - 直接快速排序
     * 2.O(N+K*logN)
     * 3.O(N+K*logK)
     */

    /**
     * method - 01
     * O(N*logN) - 直接快速排序
     */

    /**
     * method - 02
     * O(N+K*logN)  首先使用大根堆的时候，从下往上建立堆，是O(N)的，
     * 然后弹出K个，每次弹出大根堆都需要调整，logN的，所以是 N+K*logN
     */

    /**
     * method - 03
     * O(N+K*logK)  首先可以获取到第K个大的数（bfprt算法），然后收集到数组，此时数组是无序的 O(N)
     * 然后这个K个数的数组排序，K*logK的
     * 所以就是O(N+K*logK)
     */
}
