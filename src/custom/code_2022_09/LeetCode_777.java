package custom.code_2022_09;

/**
 * @ClassName LeetCode_777
 * @Author Duys
 * @Description
 * @Date 2022/9/19 17:38
 **/
public class LeetCode_777 {
    //start和end相应位置相等。
    //start和end相应位置是R和X，后续start和end中r和x的数量相同，且不能出现L
    //start和end相应位置是X和L，后续start和end中l和x的数量相同，且不能出现R
    //其它都是false
    public boolean canTransform(String start, String end) {
        int n = start.length();
        char[] starts = start.toCharArray();
        char[] ends = end.toCharArray();
        int index = 0;
        while (index < n) {
            char start1 = starts[index];
            char end1 = ends[index];
            if (start1 == end1) {
                index++;
            } else if (start1 == 'R' && end1 == 'X') {
                index++;
                int r = 1;
                while (index < n && r > 0) {
                    start1 = starts[index];
                    end1 = ends[index];
                    if (start1 == 'L' || end1 == 'L') {
                        return false;
                    }
                    if (start1 == 'R') {
                        r++;
                    }
                    if (end1 == 'R') {
                        r--;
                    }
                    index++;
                }
                if (r != 0) {
                    return false;
                }
            } else if (start1 == 'X' && end1 == 'L') {
                index++;
                int l = 1;
                while (index < n && l > 0) {
                    start1 = starts[index];
                    end1 = ends[index];
                    if (start1 == 'R' || end1 == 'R') {
                        return false;
                    }
                    if (start1 == 'L') {
                        l--;
                    }
                    if (end1 == 'L') {
                        l++;
                    }
                    index++;
                }
                if (l != 0) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
