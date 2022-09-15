package custom.code_2022_07;

/**
 * @ClassName LeetCode_223
 * @Author Duys
 * @Description
 * @Date 2022/7/13 9:51
 **/
// 223. 矩形面积
// 给你 二维 平面上两个 由直线构成且边与坐标轴平行/垂直 的矩形，请你计算并返回两个矩形覆盖的总面积。
public class LeetCode_223 {
    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // 矩形1.左下点(ax1,xy1) 右上点(ax2,ay2)
        // 举行2.左下点(bx1,by1) 右上点(bx2,by2)
        // 根据投影算
        int a1 = (ax2 - ax1) * (ay2 - ay1);
        int a2 = (bx2 - bx1) * (by2 - by1);
        // 算投影
        int oW = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        int oH = Math.min(ay2, by2) - Math.max(ay1, by1);
        int oA = Math.max(oH, 0) * Math.max(oH, 0);
        return a1 + a2 - oA;
    }
}
