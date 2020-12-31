package 第二章_有序表.AVL;

import com.sun.security.auth.NTNumericCredential;
import 第一章_哈希与哈希表.一致性hash.Node;

import java.util.List;

public class AVLTree {
    private Node root;
    private class  Node{
        String key;
        Integer val;
        Node l;
        Node r;
        //高度
        Integer h;

        public Node(String key, Integer val) {
            this.key = key;
            this.val = val;
            this.h=1;
        }
    }

    //添加一个节点
    public void put(String key,Integer val){
        if (key==null){
            return;
        }
        Node node=find_node(key);
        if (node!=null&&node.key.compareTo(key)==0){
            node.val=val;
        }else {
            root=add(root,key,val);
        }
    }

    //添加一个元素
    private Node add(Node cur, String key, Integer val) {
        if (cur==null){
            return new Node(key,val);
        }
        if (root.key.compareTo(key)<0){
             cur.r=add(cur.r,key,val);
        }else if (root.key.compareTo(key)>0){
            cur.l=add(cur.l,key,val);
        }
        return balance(cur);
    }

    public Integer get(String key){
        Node node=find_node(key);
        if (node==null){
            throw new RuntimeException("没找到key对应的value");
        }
        return node.val;
    }

    //平衡，返回新的当前节点
    private Node balance(Node cur){
        if (cur==null){
            return null;
        }
        if (cur.l!=null&&cur.l.l!=null&&cur.r!=null&&cur.l.l.h-cur.r.h>0){
            cur=rightRotate(cur);
        }else if (cur.l!=null&&cur.l.r!=null&&cur.r!=null&&cur.l.r.h-cur.r.h>0){
            cur.l=leftRotate(cur.l);
            cur=rightRotate(cur);
        }else if (cur.l!=null&&cur.r!=null&&cur.r.r!=null&&cur.r.r.h-cur.l.h>0){
            cur=leftRotate(cur);
        }else if (cur.l!=null&&cur.r!=null&&cur.r.l!=null&&cur.r.l.h-cur.l.h>0){
            cur.r=rightRotate(cur.r);
            cur=leftRotate(cur);
        }else if (cur.l== null&&cur.r!=null&&cur.r.h==2){
            cur=leftRotate(cur);
        }else if (cur.r==null&&cur.l!=null&&cur.l.h==2){
            cur=rightRotate(cur);
        }
        updateHigh(cur);
        return cur;
    }

    //更新节点高度
    private void updateHigh(Node cur){
        cur.h=getNodeHigh(cur);
    }

    private int getNodeHigh(Node cur){
        if (cur.r==null&&cur.l==null){
            return 1;
        }
        int lh=0;
        int rh=0;
       if (cur.l!=null){
           lh=getNodeHigh(cur.l);
       }
       if (cur.r!=null){
           rh=getNodeHigh(cur.r);
       }
       return Math.max(lh,rh)+1;
    }

    //右旋
    public Node rightRotate(Node cur){
        Node des=cur.l;
        cur.l=des.r;
        des.r=cur;
        return des;
    }

    //左旋
    public Node leftRotate(Node cur){
        Node des=cur.r;
        cur.r=des.l;
        des.l=cur;
        return des;
    }


    //查找节点
    private Node find_node(String key){
        Node cur=root;
        Node pre=null;
        while (cur!=null){
            pre=cur;
            if (cur.key.compareTo(key)<0){
                cur=cur.r;
            }else if (cur.key.compareTo(key)>0){
                cur=cur.l;
            }else {
               break;
            }
        }
        return pre;
    }
}
