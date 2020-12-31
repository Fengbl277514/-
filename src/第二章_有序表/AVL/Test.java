package 第二章_有序表.AVL;


public class Test {
    public static void main(String[] args) {
        AVLTree avlTree=new AVLTree();
        avlTree.put("a",1);
        avlTree.put("b",2);
        avlTree.put("c",3);
        avlTree.put("d",3);
        avlTree.put("e",3);
        avlTree.put("f",3);
        avlTree.put("g",3);
        avlTree.put("h",3);
        avlTree.put("z",3);
        System.out.println(avlTree.get("b"));
    }
}
