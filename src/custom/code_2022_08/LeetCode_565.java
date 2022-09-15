package custom.code_2022_08;

/**
 * @ClassName LeetCode_565
 * @Author Duys
 * @Description
 * @Date 2022/8/26 16:12
 **/
//565. 数组嵌套
public class LeetCode_565 {

    // i向nums[i]连线，形成图
    public int arrayNesting(int[] nums) {
        int ans = 0;
        int n = nums.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            while (!visited[i]) {
                visited[i] = true;
                i = nums[i];
                cnt++;
            }
            ans = Math.max(cnt, ans);
        }
        return ans;
    }
}
