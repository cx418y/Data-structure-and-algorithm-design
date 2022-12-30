import com.sun.deploy.util.BlackList;

import javax.xml.soap.Node;
import java.io.*;

public class RBT {
    private final Node nil = new Node(null,null,BLACK,null,null,null);
    public Node root = nil;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    //定义Node类
    public static class Node{
        private String key, value;
        private Node parent,leftChild,rightChild;
        private boolean color;

        public Node(String key,String value, boolean color,Node parent,Node leftChild,Node rightChild){
            this.key = key;
            this.value = value;
            this.color = color;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.parent = parent;

        }

        public String getKey(){
            return this.key;
        }
        public String getValue(){
            return this.value;
        }
        public Boolean getColor(){
            return this.color;
        }
    }


    //左旋
    public void left_rotate(RBT T,Node x){
        Node y = x.rightChild;
        x.rightChild = y.leftChild;//过继

        if(y.leftChild!=T.nil) {
            y.leftChild.parent = x;
        }

        y.parent = x.parent;
        if(x.parent == T.nil){
            T.root = y;
        }
        else if(x == x.parent.leftChild){
            x.parent.leftChild = y;
        }
        else{
            x.parent.rightChild = y;
        }
        y.leftChild = x;
        x.parent = y;
    }

    //右旋
    public void right_rotate(RBT T,Node y){
        Node x = y.leftChild;
        y.leftChild = x.rightChild;

        if(x.rightChild!=T.nil){
            x.rightChild.parent = y;
        }

        x.parent = y.parent;
        if(y.parent == T.nil){
            T.root = x;
        }
        else if(y == y.parent.leftChild){
            y.parent.leftChild = x;
        }
        else{
            y.parent.rightChild = x;
        }
        x.rightChild = y;
        y.parent = x;
    }

    //插入
    public void insert(RBT T,Node z){
        if(z.key.equals(search(T,T.root,z.key).key)){
            System.out.println("Insert-Error: key conflict");
            return;
        }
        z.leftChild = T.nil;
        z.rightChild = T.nil;
        Node y = T.nil;
        Node x = T.root;
        while(x!=T.nil){
            y = x;
            if (compareKey_Less(z.key,x.key))
                x = x.leftChild;

            else x = x.rightChild;
        }
        z.parent = y;
        if(y == T.nil) {
            T.root = z;
        }
        else if(compareKey_Less(z.key,y.key))
            y.leftChild = z;

        else y.rightChild = z;
        z.leftChild = T.nil;
        z.rightChild = T.nil;
        z.color = RED;

        insert_fixup(T,z);
    }

    //插入修复
    public void insert_fixup(RBT T,Node z) {
        if (z.parent == T.nil) {
            z.color = BLACK;
        } else {
            while (z.parent.color == RED) {
                if (z.parent == z.parent.parent.leftChild) {
                    Node y = z.parent.parent.rightChild;

                    //case_1:
                    if (y.color == RED) {
                        z.parent.color = BLACK;
                        y.color = BLACK;
                        z.parent.parent.color = RED;
                        z = z.parent.parent;
                    }

                    //case_2:
                    else {
                        if (z == z.parent.rightChild) {
                            z = z.parent;
                            left_rotate(T, z);
                        }

                        //case_3:
                        z.parent.color = BLACK;
                        z.parent.parent.color = RED;
                        right_rotate(T, z.parent.parent);
                    }
                } else {
                    Node y = z.parent.parent.leftChild;

                    //case_1:
                    if (y.color == RED) {
                        z.parent.color = BLACK;
                        y.color = BLACK;
                        z.parent.parent.color = RED;
                        z = z.parent.parent;
                    }

                    //case_2:
                    else {
                        if (z == z.parent.leftChild) {
                            z = z.parent;
                            right_rotate(T, z);
                        }

                        //case_3:
                        z.parent.color = BLACK;
                        if (z.parent.parent != null) {
                            z.parent.parent.color = RED;
                            left_rotate(T, z.parent.parent);
                        }
                    }
                }
            }
        }
        T.root.color = BLACK;
    }

    //删除节点
    public void delete(RBT T,Node z){
        Node y = z;
        Node x;
        boolean y_original_color = y.color;
        if (z.leftChild == T.nil){
            x = z.rightChild;
            transplant(T,z,z.rightChild);
        }
        else if(z.rightChild == T.nil){
            x = z.leftChild;
            transplant(T,z,z.leftChild);
        }
        else{
            y = minmun(T,z.rightChild);//z的后继
            y_original_color = y.color;
            x = y.rightChild;
            if(y.parent == z){
                x.parent = y;
            }
            else{
                transplant(T,y,y.rightChild);
                y.rightChild = z.rightChild;
                y.rightChild.parent = y;
            }
            transplant(T,z,y);
            y.leftChild = z.leftChild;
            y.leftChild.parent = y;
            y.color = z.color;
        }
        if(y_original_color == BLACK){
            delete_fixup(T,x);
        }
    }

    //最小值
    public Node minmun(RBT T,Node x){
        while (x.leftChild!=T.nil)
            x = x.leftChild;
        return x;
    }

    //变换
    public void transplant(RBT T ,Node u, Node v){
        if(u.parent == T.nil){
            T.root = v;
            v.parent = T.nil;
        }
        else if(u == u.parent.leftChild){
            u.parent.leftChild = v;
            v.parent = u.parent;
        }
        else{
            u.parent.rightChild = v;
            v.parent = u.parent;
        }

    }

    //删除修复
    public void delete_fixup(RBT T,Node x) {
        Node w;
        while (x != T.root && x.color == BLACK) {
            if (x == x.parent.leftChild) {
                w = x.parent.rightChild;
                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    left_rotate(T, x.parent);
                    w = x.parent.rightChild;
                }
                if (w.leftChild.color == BLACK && w.rightChild.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else if (w.rightChild.color == BLACK) {
                    w.leftChild.color = BLACK;
                    w.color = RED;
                    right_rotate(T, w);
                    w = x.parent.rightChild;
                }

                w.color = x.parent.color;
                x.parent.color = BLACK;
                w.rightChild.color = BLACK;
                left_rotate(T, x.parent);
                x = T.root;
            } else {
                w = x.parent.leftChild;
                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    right_rotate(T, x.parent);
                    w = x.parent.leftChild;
                }
                if (w.rightChild.color == BLACK && w.leftChild.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    if (w.leftChild.color == BLACK) {
                        w.rightChild.color = BLACK;
                        w.color = RED;
                        left_rotate(T, w);
                        w = x.parent.leftChild;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.leftChild.color = BLACK;
                    right_rotate(T, x.parent);
                    x = T.root;
                }
            }
        }
        x.color = BLACK;
    }

    //查找
    public Node search (RBT T,Node x,String key){
        while (x != this.nil && !key.equals(x.key)){
            if (compareKey_Less(key,x.key)){
                x = x.leftChild;
            }else {
                x = x.rightChild;
            }
        }
        return x;
    }

    //得到值
    public String get(RBT T,String key){
        if(search(T,T.root,key)!=T.nil){
            return search(T,T.root,key).value;
        }
        else {
            return "Error:key missing";
        }
    }

    //更新值
    public void put(RBT T,String key, String val){
        if(search(T,T.root,key)!=T.nil){
            System.out.println("The value of "+key+" has been updated!");
            search(T,T.root,key).value = val;
        }
        else{
            Node x = new Node(key,val,RED,null,null,null);
            insert(T,x);
            System.out.println(key+" is not in the BST, now is has been insertde!");
        }
    }


    //全部输出
    public void dump(Node x){
        if(x!=null){
            dump(x.leftChild);
            if(x.key!=null) {
                System.out.println(x.key + ": " + x.value);
            }
            dump(x.rightChild);
        }
    }

    //比较关键字
    public boolean compareKey_Less (String a,String b){
        boolean result = true;
        a = a.toLowerCase();
        b = b.toLowerCase();
        char[] m = a.toCharArray();
        char[] n = b.toCharArray();
        for(int i = 0;i<m.length&&i<n.length;i++){
            if(Integer.valueOf(m[i])>Integer.valueOf(n[i])){
                result = false;
                break;
            }
            else if(Integer.valueOf(m[i])<Integer.valueOf(n[i])){
                result = true;
                break;
            }
            else {}
            if((i==m.length-1)||(i==n.length-1)){
                if(m.length>=n.length){
                    result = false;
                }
                else result = true;
            }
        }
        return result;
    }

    public void LOAD(RBT T,String filename){
        File file = new File("D:\\大二上\\数据结构与算法设计\\pj\\src\\"+filename);
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            String line ="";
            while ((line=br.readLine()) != null){
                String key = line;
                String value = br.readLine();

                if(search(T,T.root,key)!=T.nil){
                    search(T,T.root,key).value = value;
                }
                else{
                    Node x = new Node(key,value,RED,null,null,null);
                    insert(T,x);
                }
            }
            System.out.println("读取文件成功！");
            br.close();
        } catch (IOException e) {
            System.out.println("读取文件失败");
        }
    }

    public static void main(String[] args){
        RBT T1 = new RBT();
        Node x = new Node("frequency","a",RED,null,null,null);
        Node x2 = new Node("b","b",RED,null,null,null);
        Node x3 = new Node("c","c",RED,null,null,null);
        Node x4 = new Node("d","j",RED,null,null,null);

/*
        T1.insert(T1,x);
        T1.insert(T1,x2);
        T1.insert(T1,x4);
        T1.insert(T1,x3);
        T1.put(T1,"a","j");
       // String a = T1.get(T1,T1.root,"a");
        //System.out.println(a);

      // T1.delete(T1,x2);
        //T1.delete(T1,x3);


 */


        T1.LOAD(T1,"windows.txt");
        T1.dump(T1.root);
    }
}
