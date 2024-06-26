package io.dev.v2.practice;

import io.dev.v2.util.BinaryTreeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class LevelOrderTraversalTest {
    @Test
    void shouldTraverseInLevelOrder() {
        int[] arr = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        //           0  1  2  3  4  5   6   7   8   9   10  11  12  13  14


        BinaryTreeUtil tree = new BinaryTreeUtil();
        BinaryTreeUtil.TreeNode root = tree.build(arr);
            Assertions.assertNotNull(root);
            assertEquals(12, root.val);

        BinaryTreeUtil.TreeNode rootNode = tree.getRoot();
            Assertions.assertSame(root, rootNode);
            assertEquals(root, rootNode);
        tree.print(" ", root);

        LevelOrderTraversal subject = new LevelOrderTraversal();
        List<List<Integer>> lists = subject.levelOrder(root);
        assertEquals(4, lists.size());

    }


}
