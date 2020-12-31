package 第二章_有序表.跳表;

import java.util.ArrayList;

public class SkipListMap {
    public static final double RANDOM=  0.5;
    private Node head;
    private Integer size;
    private Integer maxLevel;

    public SkipListMap() {
        head=new Node(null,null);
        head.next_nodes.add(null);
        size=0;
        maxLevel=0;
    }

    //找到第0层比key小的最右的那个位置
    private Node mostRightLessNode(String key){
        if (key==null){
            return null;
        }
        int level=maxLevel;
        Node cur=head;
        while (level>=0){
            cur=mostRightLessNodeInLevel(key,cur,level--);
        }
        return cur;
    }

    //现在来到的是cur节点，并来到当前节点的level层，找到当前层比key小最右的位置
    private Node mostRightLessNodeInLevel(String otherKey,Node cur,Integer level){
        Node next = cur.next_nodes.get(level);
        while (next!=null&&next.isLessKey(otherKey)){
            cur=next;
            next=next.next_nodes.get(level);
        }
        return cur;
    }

    //查询是否包含key
    public boolean containsKey(String key){
        //先找到小于key的最右那个
        Node less=mostRightLessNode(key);
        //再找到他的下一个
        Node next=less.next_nodes.get(0);
        //如果下一个==key，就说明包含key,没有就说名比key大
        return next!=null&&next.isEqualKey(key);
    }

    //添加一个元素
    public void put(String key,Integer val){
        if (key==null){
            return;
        }
        //看是否存在，存在就更新
        Node less=mostRightLessNode(key);
        Node next=less.next_nodes.get(0);
        if (next!=null&&next.isEqualKey(key)){
            next.val=val;
        }else {
            //不存在，size先++
            size++;
            int newLevel=0;
            //随机生成层数
            while (Math.random()>RANDOM){
                newLevel++;
            }
            //生成新节点，注意其实还是只在最底层（o层有key和val），上面层只有下一跳
            Node node=new Node(key,val);
            //看是否大与头节点的层数，大于就补位头节点null,后面会设置为新节点的
            while (maxLevel<newLevel){
                head.next_nodes.add(null);
                maxLevel++;
            }
            //先创建next_nodes,先用null填充
            for (int i = 0; i <= newLevel; i++) {
                node.next_nodes.add(null);
            }
            //按那条小于线，在每一层插入下一跳
            Node cur=head;
            Integer level=maxLevel;
            while (level>=0){
                cur=mostRightLessNodeInLevel(key,cur,level);
                if (level<=newLevel){
                    node.next_nodes.set(level,cur.next_nodes.get(level));
                    //如果是头节点的话就会将null变为新节点
                    cur.next_nodes.set(level,node);
                }
                level--;
            }
        }
    }

    //删除一个元素
    public void delete(String key){
        if (containsKey(key)){
            size--;
            int level=maxLevel;
            Node pre=head;
            while (level>=0){
                pre=mostRightLessNodeInLevel(key,pre,level);
                Node next=pre.next_nodes.get(level);
                if (next!=null&&next.isEqualKey(key)){
                    pre.next_nodes.set(level,next.next_nodes.get(level));
                }
                if (level!=0&&pre==head&&next==null){
                    maxLevel--;
                    head.next_nodes.remove(level);
                }
                level--;
            }
        }
    }

    public int get(String key){
        if (containsKey(key)){
            Node pre= mostRightLessNode(key);
            return pre.next_nodes.get(0).val;
        }
        throw  new RuntimeException("没找到key值对应的value");
    }

    private class  Node{
        String key;
        Integer val;
        //每层的下一跳
        ArrayList<Node> next_nodes;

        public Node(String key, Integer val) {
            this.key = key;
            this.val = val;
            next_nodes=new ArrayList<>();
        }

        //这两个函数就是迷惑行为，看不懂，head是最小的
        public boolean isLessKey(String otherKey){
            return otherKey!=null&&(key==null||otherKey.compareTo(key)>0);
        }

        public boolean isEqualKey(String otherKey){
            return  (otherKey==null&&key==null)|| (otherKey!=null&&key!=null&&key.compareTo(otherKey)==0);
        }
    }
}
