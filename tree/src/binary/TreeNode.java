package binary;

public class TreeNode {

    Integer val;

    TreeNode left;

    TreeNode right;

    public TreeNode(Integer val) {
        this.val = val;
    }

    public TreeNode(TreeNode left, TreeNode right, Integer val) {
        this.left = left;
        this.right = right;
        this.val = val;
    }

    public static TreeNode initTree() {
        TreeNode one = new TreeNode(null, null, 1);
        TreeNode two = new TreeNode(one, null, 2);
        TreeNode four = new TreeNode(null, null, 4);

        TreeNode three = new TreeNode(two, four, 3);

        TreeNode six = new TreeNode(null, null, 6);
        TreeNode eight = new TreeNode(null, null, 8);
        TreeNode seven = new TreeNode(six, eight, 7);

        TreeNode five = new TreeNode(three, seven, 5);
        return five;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
