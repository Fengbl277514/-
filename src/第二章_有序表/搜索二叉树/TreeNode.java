package 第二章_有序表.搜索二叉树;

public class TreeNode {
     private Integer val;
     TreeNode parent;
     TreeNode left;
     TreeNode right;


    public TreeNode(Integer val, TreeNode parent, TreeNode left, TreeNode right) {
        this.val = val;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }


}
