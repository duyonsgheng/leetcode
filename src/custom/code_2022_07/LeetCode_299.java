package custom.code_2022_07;

/**
 * @ClassName LeetCode_284
 * @Author Duys
 * @Description
 * @Date 2022/7/14 15:24
 **/
//
public class LeetCode_299 {
    public static String getHint(String secret, String guess) {
        int bulls = 0;
        int[] count1 = new int[10];
        int[] count2 = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                count1[secret.charAt(i) - '0']++;
                count2[guess.charAt(i) - '0']++;
            }
        }
        int cows = 0;
        for (int i = 0; i < 10; i++) {
            cows += Math.min(count1[i], count2[i]);
        }
        return bulls + "A" + cows + "B";
    }
}
