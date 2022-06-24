package binary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Visitor {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.initTree();
        Visitor visitor = new Visitor();

//        visitor.preOrderVisitor(treeNode);
//        System.out.println();
//        visitor.preOrder(treeNode);

//        visitor.inOrderVisitor(treeNode);
//        System.out.println();
//        visitor.inOrder(treeNode);

//        visitor.postOrderVisitor(treeNode);
//        System.out.println();
//        visitor.postOrder(treeNode);

        visitor.levelOrder(treeNode);
    }

    public void preOrderVisitor(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.val != null) {
            System.out.print(root.val);
            System.out.print(" -> ");
        }
        preOrderVisitor(root.left);
        preOrderVisitor(root.right);
    }

    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val != null) {
                System.out.print(node.val);
                System.out.print(" -> ");
            }

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public void inOrderVisitor(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrderVisitor(root.left);
        if (root.val != null) {
            System.out.print(root.val);
            System.out.print(" -> ");
        }
        inOrderVisitor(root.right);
    }

    /**
     * 中序遍历左边节点不为空就一直访问左侧节点
     * 当左侧节点为空时弹出根节点，访问根节点的右节点
     * 使用列表保存节点数据
     *
     * @param root
     */
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                result.add(node.getVal());
                node = node.right;
            }
        }

        for (Integer value : result) {
            System.out.print(value + " -> ");
        }
    }

    /**
     * 后序遍历使用列表把数据结果保存下来
     *
     * @param root
     */
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.getVal());

            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }

        int i = result.size() - 1;
        while (i >= 0) {
            System.out.print(result.get(i) + " -> ");
            i--;
        }
    }

    public void postOrderVisitor(TreeNode root) {
        if (root == null) {
            return;
        }

        postOrderVisitor(root.left);
        postOrderVisitor(root.right);
        if (root.val != null) {
            System.out.print(root.val);
            System.out.print(" -> ");
        }
    }

    public void levelOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            int len = queue.size();
            while (len > 0) {
                TreeNode tmp = queue.poll();
                item.add(tmp.val);

                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
                len--;
            }
            result.add(item);
        }
    }

    /**
     * 判断二叉树是否对称
     *
     * @param root
     * @return
     */
    boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null && right == null) {
            return true;
        } else if (left.val != right.val) {
            return false;
        }

        boolean outside = compare(left.left, right.right);
        boolean inside = compare(left.right, right.left);
        boolean isSame = outside && inside;

        return isSame;
    }

    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.right);
        stack.push(root.left);
        while (!stack.isEmpty()) {
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();
            if (left == null && right == null) {
                continue;
            }

            if (left == null || right == null || (left.val != right.val)) {
                return false;
            }

            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }

    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth1(root.left);
        int rightDepth = maxDepth1(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                } else {
                    if (node.left != null) {
                        queue.offer(node.left);
                    } else {
                        queue.offer(node.right);
                    }
                }
            }
        }
        return depth;
    }

    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = minDepth1(root.left);
        int rightDepth = minDepth1(root.right);

        if (root.left == null && root.right != null) {
            return 1 + rightDepth;
        }

        if (root.left != null && root.right == null) {
            return 1 + leftDepth;
        }

        return 1 + Math.min(leftDepth, rightDepth);
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                result++;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return result;
    }

    public boolean isBalance(TreeNode root) {
        return -1 == getHeight(root);
    }

    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) { // 使用-1代表不平衡
            return -1;
        }
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) { // 使用-1代表不平衡
            return -1;
        }

        // 左右高度相差大于1
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public List<List<Integer>> traversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<Integer> path = new ArrayList<>();
        path.add(root.getVal());
        if (root.left != null) {
            path.add(root.left.val);
        }
        if (root.right != null) {

        }
        return null;
    }

}
