package 第一章_哈希与哈希表.并查集结构;

import java.util.HashMap;

//包装数据，因为在hashmap中是按值传递的
public class Element {
    private Integer val;

    public Element(Integer val) {
        this.val = val;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
