package duys.class_08_12;

/**
 * @ClassName Morris_01
 * @Author Duys
 * @Description Morris遍历
 * @Date 2021/8/12 15:17
 **/
public class Morris_01 {
    /**
     * 一种遍历二叉树的方式，并且时间复杂度O(N)，额外空间复杂度O(1)
     * 通过利用原树中大量空闲指针的方式，达到节省空间的目的
     *
     *
     * 细节：
     * 假设来到当前节点cur，开始时cur来到头节点位置
     * 1）如果cur没有左孩子，cur向右移动(cur = cur.right)
     * 2）如果cur有左孩子，找到左子树上最右的节点mostRight：
     * 	a.如果mostRight的右指针指向空，让其指向cur，
     * 	然后cur向左移动(cur = cur.left)
     * 	b.如果mostRight的右指针指向cur，让其指向null，
     * 	然后cur向右移动(cur = cur.right)
     * 3）cur为空时遍历停止
     */

    /**
     * 如果节点有左树，到两次，没有左树就到一次
     */
}
