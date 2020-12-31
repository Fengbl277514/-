package 第二章_有序表.搜索二叉树;

import 第一章_哈希与哈希表.一致性hash.Node;

public class SearchTree {
    private TreeNode root;
    private Integer size;

    //添加一个元素
    public void insert(Integer key){
        if (root==null){
            root=new TreeNode(key,null,null,null);
            size++;
            return;
        }
        //寻找该挂到到那个节点上
        TreeNode near = getNear(key);
        if (key>near.getVal()){
            near.right=new TreeNode(key,near,null,null);
        }else {
            near.left=new TreeNode(key,near,null,null);
        }
        size++;
    }

    //查找一个元素
    public TreeNode search(Integer key){
        TreeNode node=root;
        while (node!=null&&node.getVal()!=null&&node.getVal()!=key){
            if (key<node.getVal()){
                node=node.right;
            }else {
                node=node.left;
            }
        }
        return node;
    }


    //删除一个元素,并返回对应的节点
    public TreeNode delete(Integer key){
        TreeNode node = search(key);
        if (node!=null){
            return delete(node);
        }else {
            return null;
        }
    }

    //有四种情况:有右无左
    private TreeNode delete(TreeNode node){
        TreeNode nodeToReturn=null;
        if (node.right==null&&node.left==null){//情况一：是叶节点
            TreeNode parent=node.parent;
            if (node.getVal()>parent.getVal()){
                parent.right=null;
            }else {
                parent.left=null;
            }
            node.parent=null;
            nodeToReturn=node;
        }else if (node.right==null){//情况二:只有左节点，则让其左孩子替换掉他
            nodeToReturn=replace(node,node.left);
        }else if (node.left==null){//情况三：只有右节点，则让其右孩子替换掉他
            nodeToReturn=replace(node,node.right);
        }else {//情况四：左右节点都有，则可以让其右子树最左的节点替换他
            TreeNode left=getLeft(node.right);
            if (left!=node.right){
                replace(left,left.right);
                left.parent=node.parent;
                left.right=node.right;
                left.right.parent=left;
            }
            replace(node,left);
            left.left=node.left;
            node.left.parent= left.left;
            //nodeToReturn=
        }
        return  null;
    }

    //获得右子树的最左节点
    private TreeNode getLeft(TreeNode node){
        while (node!=null){
            node=node.left;
        }
        return node;
    }


    //替换函数
    private TreeNode replace(TreeNode node,TreeNode replaceNode){
        TreeNode parent = node.parent;
        replaceNode.parent=parent;
        if (replaceNode.getVal()>parent.getVal()){
            parent.right=replaceNode;
        }else {
            parent.left=replaceNode;
        }
        node.left=null;
        node.right=null;
        node.parent=null;
        return node;
    }










    //返回一个key该插到那个节点
    private TreeNode getNear(Integer key){
        TreeNode node=root;
        while (node!=null){
            if (key<node.getVal()){
                node=node.left;
            }else{
                node=node.right;
            }
        }
        return node;
    }
}
