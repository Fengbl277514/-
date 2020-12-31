package 第二章_有序表.SB树;

public class Test {
    public static void main(String[] args) {
        SizeBalancedTree s=new SizeBalancedTree();
        s.put("a",1);
        s.put("b",1);
        s.put("c",1);
        s.put("d",1);
        s.put("e",1);
        s.put("f",1);
        System.out.println(s.get("c"));
    }
}
