package duys.class_08_06;

/**
 * @ClassName Manacher_01
 * @Author Duys
 * @Description 解决回文字串问题
 * @Date 2021/8/11 13:15
 **/
public class Manacher_01 {

    /**
     * 1.回文半径（回文半径数组）
     * 2.回文直径
     * 3.最大的扩展位置 -R
     * 4.R对应的中心点 -C
     */

    /**
     *  #1#2#3#3#2#1
     *
     * 1.当前位置是不再R的范围内 - 没有优化方案
     * 2.当前的位置在R的范围内
     * 那么存在当前位置 i 在R范围内有一个i' 位置，因为i'在R范围内，那么i'是已经求过回文半径了，在回文半径数组中放着的
     * 然后根据i' 来分情况
     * 当当前位置i在R范围内，可以优化，否则不能优化
     *  2.1 如果i'的回文范围在R范围内，那么i的回文范围已经知道了，就是i'的答案 （O(1)）
     *  2.2 如果i'的回文范围不在R范围内，回文半径就是i到R的距离 (O(1))
     *  2.3 如果i'的回文范围左边界与R范围的左边界重合，(O(N))那么i'的区域也是当前的在R的范围内的，
     *  那么这一段区域是不需要验证的，需要验证的是i 不需要验证的区域的左边界前一个位置和有边界的后一个位置是否相等，可能在i的区域会扩的更多
     */
}
