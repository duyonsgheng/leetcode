package custom.code_2022_11;

/**
 * @ClassName LeetCode_1662
 * @Author Duys
 * @Description
 * @Date 2022/11/1 9:14
 **/
// 1662. 检查两个字符串数组是否相等
public class LeetCode_1662 {

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return String.join("", word1).equals(String.join("", word2));
    }
}
