package binary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<TreeNode> st = new Stack();
        st.push(q);
        st.push(p);
        while (!st.isEmpty()) {
            TreeNode left = st.pop();
            TreeNode right = st.pop();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            } else if (left.val != right.val) {
                return false;
            }

            st.push(left.left);
            st.push(right.left);
            st.push(left.right);
            st.push(right.right);
        }
        return true;
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) {
            return true;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            boolean isSame = isSameTree(node, subRoot);
            if (isSame) {
                return true;
            }
            if (node.left != null) {
                nodeQueue.offer(node.left);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
            }
        }
        return false;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        if (root == null) {
            return result;
        }
        String s = "xx";
//        s.toCharArray()
        Stack<TreeNode> stackA = new Stack<>();
        Stack<TreeNode> stackB = new Stack<>();
        stackA.push(root);

        int i = 0;
        TreeNode node = null;
        while (!stackA.isEmpty() || !stackB.isEmpty()) {
            int size = 0;
            List<Integer> list = new ArrayList();

            if (i % 2 == 0) {
                size = stackA.size();
            } else {
                size = stackB.size();
            }
            while (size-- > 0) {
                if (i % 2 == 0) {
                    node = stackA.pop();
                    if (node.left != null) {
                        stackB.push(node.left);
                    }
                    if (node.right != null) {
                        stackB.push(node.right);
                    }
                } else {
                    node = stackB.pop();
                    if (node.right != null) {
                        stackA.push(node.right);
                    }
                    if (node.left != null) {
                        stackA.push(node.left);
                    }
                }
                if (node.val != null) {
                    list.add(node.val);
                }
            }
            result.add(list);
            i++;
        }
        return result;
    }

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) == -1 ? false : true;
    }

    public int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = getHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }
        return Math.abs(leftHeight - rightHeight) > 1 ? -1 : 1 + Math.max(leftHeight, rightHeight);
    }


}
