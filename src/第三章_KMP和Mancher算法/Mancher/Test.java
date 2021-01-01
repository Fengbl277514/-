package 第三章_KMP和Mancher算法.Mancher;

public class Test {
    public static void main(String[] args) {

        Manacher manacher=new Manacher();
        String s="dcbaabc";
        System.out.println(manacher.getMancher(s));
    }
}
