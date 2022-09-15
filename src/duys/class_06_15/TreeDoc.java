package duys.class_06_15;

/**
 * @ClassName TreeDoc
 * @Author Duys
 * @Description 二叉树、平衡二叉树、红黑树、B-树、B+树、B*树 的定义
 * @Date 2021/6/15 10:52
 **/
public class TreeDoc {
    /**
     * 二叉树（二叉查找、搜索、排序树）定义和性质
     * 1.若树的左树不为空，则左树上所有的节点都小于它根节点的值。
     * 2.若树的右树不为空，则右树上所有的节点都大于它根节点的值。
     * 3.它的左、右子树也分别是二叉树。
     */

    /**
     * 平衡二叉树（AVL树）定义和性质
     * 1.若树的左树不为空，则左树上所有的节点都小于它根节点的值。
     * 2.若树的右树不为空，则右树上所有的节点都大于它根节点的值。
     * 3.两个子树的高度差的绝对值不超过1
     * 4.左右子树也分别是平衡二叉树
     */

    /**
     * 红黑树的定义和性质
     * 1.首先是一种平衡二叉树
     * 2.每个节点不是红色就是黑色
     * 3.根节点是黑色
     * 4.每个叶子节点是黑色[这里的叶子节点是指为空(nil或者null)的叶子节点]
     * 5.如果一个节点是红色，则它的子节点必须是黑色(父子节点颜色不能相同)
     * 6.从一个节点到该节点所有子孙节点的所有路径上都包含相同数目的黑色节点。
     */

    /**
     * B-树定义和性质
     * 一棵m阶B树是一棵平衡的m路搜索树。
     * 1.根节点至少有两个子女
     * 2.每个非根节点所包含的关键字个数 J 满足 m/2 -1 <= j <= m-1
     * 3.除根节点以外的所有节点(不包括叶子节点)的度数正好是关键字的总数+1，则内部子树个数K 满足 m/2 <=k <= m
     * 4.所有的叶子节点都位于同一层
     */
}
