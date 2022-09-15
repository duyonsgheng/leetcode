package duys_code.day_01;

import java.io.File;
import java.util.Stack;

/**
 * @ClassName CountFile_Code_02
 * @Author Duys
 * @Description
 * @Date 2021/9/13 17:26
 **/
public class Code_02_CountFile {
    /**
     * 给定一个文件目录的路径，
     * 写一个函数统计这个目录下所有的文件数量并返回
     * 隐藏文件也算，但是文件夹不算
     */

    public static int countFile(String path) {
        File root = new File(path);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        // 只放文件夹
        Stack<File> stack = new Stack<>();
        stack.push(root);
        int count = 0;
        while (!stack.isEmpty()) {
            File pop = stack.pop();
            for (File file : pop.listFiles()) {
                // 是文件则文件数+1
                if (file.isFile()) {
                    count++;
                }
                // 如果是文件夹，放入栈里
                if (file.isDirectory()) {
                    stack.push(file);
                }
            }
        }
        return count;
    }
}
