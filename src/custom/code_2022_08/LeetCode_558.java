package custom.code_2022_08;

/**
 * @ClassName LeetCode_558
 * @Author Duys
 * @Description
 * @Date 2022/8/26 14:58
 **/
//
public class LeetCode_558 {
    // 分治
    public Node intersect(Node quadTree1, Node quadTree2) {
        // 都是子节点
        if (quadTree1.isLeaf && quadTree2.isLeaf) {
            if (quadTree1.val) {
                return quadTree1;
            } else if (quadTree2.val) {
                return quadTree2;
            } else {
                return quadTree1;
            }
        }
        Node ans = new Node();
        ans.topLeft = intersect(quadTree1.isLeaf ? quadTree1 : quadTree1.topLeft, quadTree2.isLeaf ? quadTree2 : quadTree2.topLeft);
        ans.topRight = intersect(quadTree1.isLeaf ? quadTree1 : quadTree1.topRight, quadTree2.isLeaf ? quadTree2 : quadTree2.topRight);
        ans.bottomLeft = intersect(quadTree1.isLeaf ? quadTree1 : quadTree1.bottomLeft, quadTree2.isLeaf ? quadTree2 : quadTree2.bottomLeft);
        ans.bottomRight = intersect(quadTree1.isLeaf ? quadTree1 : quadTree1.bottomRight, quadTree2.isLeaf ? quadTree2 : quadTree2.bottomRight);

        boolean isLeaf = ans.topLeft.isLeaf && ans.topRight.isLeaf && ans.bottomLeft.isLeaf && ans.bottomRight.isLeaf;
        boolean isVal = ans.topLeft.val && ans.topRight.val && ans.bottomLeft.val && ans.bottomRight.val;
        boolean val = ans.topLeft.val || ans.topRight.val || ans.bottomLeft.val || ans.bottomRight.val;
        ans.isLeaf = isLeaf && (isVal || !val);
        ans.val = ans.topLeft.val;// 任意都可以
        if (ans.isLeaf) {
            ans.topLeft = ans.topRight = ans.bottomLeft = ans.bottomRight = null;
        }
        return ans;
    }

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf) {
            val = _val;
            isLeaf = _isLeaf;
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }
}
