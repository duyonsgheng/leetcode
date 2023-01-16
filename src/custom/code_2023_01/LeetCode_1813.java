package custom.code_2023_01;

/**
 * @ClassName LeetCode_1813
 * @Author Duys
 * @Description
 * @Date 2023/1/16 11:01
 **/
// 1813. 句子相似性 III
public class LeetCode_1813 {

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        if (sentence1.equals(sentence2)) {
            return true;
        }
        // My name is Haley
        // My Haley
        String[] arr1 = sentence1.split(" ");
        String[] arr2 = sentence2.split(" ");
        int i = 0;
        int j = 0;
        // 从前往后
        while (i < arr1.length && i < arr2.length && arr1[i].equals(arr2[i])) {
            i++;
        }
        // 从后往前
        while (j < arr1.length - i && j < arr2.length - i && arr1[arr1.length - j - 1].equals(arr2[arr2.length - j - 1])) {
            j++;
        }
        return i + j == Math.min(arr1.length, arr2.length);
    }

}
