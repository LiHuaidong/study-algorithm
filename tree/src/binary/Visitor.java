package binary;

import java.util.ArrayList;
import java.util.List;
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
//
        visitor.postOrderVisitor(treeNode);
        System.out.println();
        visitor.postOrder(treeNode);
    }

    public void preOrderVisitor(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.value != null) {
            System.out.print(root.value);
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
            if (node.value != null) {
                System.out.print(node.value);
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
        if (root.value != null) {
            System.out.print(root.value);
            System.out.print(" -> ");
        }
        inOrderVisitor(root.right);
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode node = root;
        while (!stack.isEmpty()) {
            if (node.left != null) {
                stack.push(node.left);
                node = node.left;
            } else {
                node = stack.pop();
                if (node.value != null) {
                    System.out.print(node.value);
                    System.out.print(" -> ");
                }
                if (node.right != null) {
                    stack.push(node.right);
                    node = node.right;
                }
            }

        }
    }

    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            stack.pop();
            result.add(node.getValue());

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
        if (root.value != null) {
            System.out.print(root.value);
            System.out.print(" -> ");
        }
    }
}
