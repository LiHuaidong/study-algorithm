package binary;

import java.util.LinkedList;
import java.util.Queue;

public class TriTreeNode {

    int val;
    TriTreeNode left;
    TriTreeNode mid;
    TriTreeNode right;

    public TriTreeNode(TriTreeNode left, TriTreeNode mid, TriTreeNode right, int val) {
        this.left = left;
        this.mid = mid;
        this.right = right;
        this.val = val;
    }

    public boolean isComplete(TriTreeNode root) {

        if (root == null) {
            return true;
        }

        Queue<TriTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        TriTreeNode node = null;
        while ((node = queue.poll()) != null) {
            queue.offer(node.left);
            queue.offer(node.mid);
            queue.offer(node.right);
        }
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node != null) {
                return false;
            }
        }
        return true;
    }

}
