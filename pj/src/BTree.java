import java.io.*;

public class BTree {
    public BTNode root = null;

    public static final int DEFAULT_T = 2;
    public static final int t=2 ; //度数


    //存键值对的类
    public class KeyValue{
        public String key, value;
        public KeyValue(String key,String value){
            this.key = key;
            this.value = value;
        }
    }

    public static class BTNode{
        // 非根节点的最少关键字个数
        public static int minKeyNum = t - 1;
        // 非根节点的最大关键字个数
        public static int maxKeyNum = 2 * t - 1;

        public boolean isLeaf;  //判断是否是叶子节点
        public KeyValue[] keys; //键值对数组
        public BTNode[]children; //子节点数组
        public int currentKeyNum; //节点当前存的键值对的数量

        //节点的构造函数
        public BTNode(){
            isLeaf = true;
            currentKeyNum = 0;
            keys = new KeyValue[t+1];
            children = new BTNode[t+2];
        }

        public static BTNode getChildNodeAtIndex(BTNode x, int keyID,int nDirection){
            if(x.isLeaf){
                return null;
            }
            keyID+=nDirection;
            if((keyID<0)||(keyID>x.currentKeyNum)){
                throw new IllegalArgumentException();
            }
            return x.children[keyID];
        }
        public static BTNode getLeftSiblingAtIndex(BTNode parentNode, int keyIdx) {
            return getChildNodeAtIndex(parentNode, keyIdx, -1);
        }
        public static BTNode getRightSiblingAtIndex(BTNode parentNode, int keyIdx) {
            return getChildNodeAtIndex(parentNode, keyIdx, 1);
        }

        public static boolean hasLeftSiblingAtIndex(BTNode parentNode, int keyIdx) {
            if (keyIdx - 1 < 0) {
                return false;
            } else {
                return true;
            }
        }

        public static boolean hasRightSiblingAtIndex(BTNode parentNode, int keyIdx) {
            if (keyIdx + 1 > parentNode.currentKeyNum) {
                return false;
            } else {
                return true;
            }
        }
    }


    //搜索
    public KeyValue search(String key) {
        BTNode x = this.root;
        while (x != null ){
            int index = binarySearch(x,key);
            if (index < x.currentKeyNum && key.equals(x.keys[index].key)){
                return x.keys[index];
            }else {
                x = x.children[index];
            }
        }
        return null;
    }

    public int binarySearch(BTNode btNode, String key){
        KeyValue []keys = btNode.keys;
        int i=0;//左指针
        int j=btNode.currentKeyNum-1;//右指针
        while (i <= j){
            int mid = (j-i)/2+i;
            if (compareKey_Less(key,btNode.keys[mid].key)){
                j = mid-1;
            }else if (key.equals(btNode.keys[mid].key)){
                return mid;
            }else {
                i = mid+1;
            }
        }
        return i;
    }




    public String get(BTree T,String key){
        if(search(key) == null){
            return "Error:key missing";
        }
        else{
            return search(key).value;
        }
    }

    public void put(String key,String value) {
        if (search(key) == null) {
            insert(key, value);
            System.out.println(key + " is not in the BTree, now is has been inserted!");
        } else {
            System.out.println("The value of " + key + " has been updated!");
            search(key).value = value;
        }
    }

    //打印：
    public void dump(BTNode x) {
        if (x != null) {
                for (int i=0;i<x.currentKeyNum;i++){
                    dump(x.children[i]);
                    System.out.println(x.keys[i].key+":"+x.keys[i].value);
                    if (i == x.currentKeyNum-1 && x.children[i+1] != null){
                        dump(x.children[i+1]);
                    }
                }
            }
        }

    //插入：
    public void insert(String key,String value){
        if(root == null){
            root = new BTNode();
            root.isLeaf = true;
            root.currentKeyNum = 1;
            root.keys[0] = new KeyValue(key,value);
        }else if(search(key)!=null){
           System.out.println("Error: Ket conflict————"+key);
        }
        else {
            root = insert(root,key,value);
        }
    }



    public BTNode insert(BTNode x, String key, String value) {
        if (root == null) {
            root = new BTNode();
            root.currentKeyNum = 1;
            root.keys[0] = new KeyValue(key, value);
            root.isLeaf = true;
            return root;
        } else {
            //如果节点满了，则分裂节点
            if (x.currentKeyNum == BTNode.maxKeyNum) {
                x = split(x);
            }

            //找到键值对要插入的地方
            int possibleID = binarySearch(x, key);
            //System.out.println(possibleID);
            //要插入的key如果已经存在，则返回key;

            // KeyValue possibleKeyValue = x.keys[possibleID];

                //如果当前节点为叶子节点，则直接插入;如果不是叶子结点，则递归直到x成为叶子结点
                if (x.isLeaf) {
                    for (int i = x.currentKeyNum; i > possibleID; i--) {
                        x.keys[i] = x.keys[i - 1];
                    }
                    x.keys[possibleID] = new KeyValue(key, value);
                    x.currentKeyNum++;
                } else {
                    BTNode t = insert(x.children[possibleID], key, value);
                    if (t.currentKeyNum == 1) {
                        for (int i = x.currentKeyNum; i > possibleID; i--) {
                            x.keys[i] = x.keys[i - 1];
                        }
                        x.keys[possibleID] = t.keys[0];
                        for (int i = x.currentKeyNum + 1; i > possibleID + 1; i--) {
                            x.children[i] = x.children[i - 1];
                        }
                        x.children[possibleID] = t.children[0];
                        x.children[possibleID + 1] = t.children[1];
                        x.currentKeyNum++;
                    }
                    t = null;
                }
            return x;
        }
    }

    //分裂节点:
    private BTNode split(BTNode x){
        int mid = x.currentKeyNum/2;

        BTNode left = new BTNode();
        for(int i = 0;i<mid;i++){
            left.keys[i] = x.keys[i];
            left.children[i] = x.children[i];
        }
        left.children[mid] = x.children[mid];
        left.isLeaf = x.isLeaf;
        left.currentKeyNum = mid;

        BTNode right = new BTNode();
        for(int i = mid+1;i<x.currentKeyNum;i++){
            right.keys[i-mid-1] = x.keys[i];
            right.children[i-mid-1] = x.children[i];
        }
        right.children[x.currentKeyNum-mid-1] = x.children[x.currentKeyNum];;
        right.isLeaf = x.isLeaf;
        right.currentKeyNum = x.currentKeyNum-mid-1;

        BTNode splitTop = new BTNode();
        splitTop.currentKeyNum = 1;
        splitTop.isLeaf = false;
        splitTop.keys[0] = x.keys[mid];
        splitTop.children[0] = left;
        splitTop.children[1] = right;
        return splitTop;
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


    public KeyValue minKey(){
        return minKey(root);
    }

    public KeyValue minKey(BTNode x){
        if(x==null){
            return null;
        }
        if(x.children[0]!=null){
            return minKey(x.children[0]);
        }else {
            return x.keys[0];
        }
    }

    public KeyValue maxKey(BTNode x){
        if(x == null){
            return null;
        }
        if(x.children[x.currentKeyNum]!=null){
            return maxKey(x.children[x.currentKeyNum]);
        }else {
            return x.keys[x.currentKeyNum-1];
        }
    }

    public String delete(String key){
        if(search(key)!=null){

        String value = search(key).value;
        root = delete(root,key);
        System.out.println("成功删除："+key);
        return  value;
        }
        else {
            System.out.println("Error:Key missing!");
            return null;
        }
    }

    public BTNode delete(BTNode x,String key){
        //获取要删除的键可能的位置
        int possibleID = binarySearch(x,key);

        //
        if(x.isLeaf){
            if(possibleID<x.currentKeyNum&&x.keys[possibleID].key.equals(key)){
                for(int i = possibleID;i < x.currentKeyNum-1;i++){
                    x.keys[i] = x.keys[i+1];
                }
                x.keys[x.currentKeyNum-1] = null;
                x.currentKeyNum--;
            }
        }else {
            if(possibleID<x.currentKeyNum&&x.keys[possibleID].key.equals(key)){
                KeyValue keyNeedToSwim = maxKey(x.children[possibleID]);
                x = delete(x,keyNeedToSwim.key);

                possibleID = binarySearch(x,key);
                x.keys[possibleID] = keyNeedToSwim;
            }else {
                BTNode t = delete(x.children[possibleID],key);

                if(t.currentKeyNum<t.minKeyNum){
                    if(BTNode.hasLeftSiblingAtIndex(x,possibleID)){
                        if(BTNode.getLeftSiblingAtIndex(x,possibleID).currentKeyNum>BTNode.maxKeyNum){
                            leftRotate(x,possibleID);
                        }else {
                            leftMerge(x,possibleID);
                        }
                    }else if(BTNode.hasRightSiblingAtIndex(x,possibleID)){
                        if(BTNode.getRightSiblingAtIndex(x,possibleID).currentKeyNum>BTNode.maxKeyNum){
                            rightRotate(x,possibleID);
                        }else {
                            rightMerge(x,possibleID);
                        }
                    }
                    if(x==root&&x.currentKeyNum==0){
                        x = x.children[0];
                    }
                }
            }
        }
        return x;

    }

    private BTNode leftMerge(BTNode x, int possibleIdx) {
        // 获取左节点
        BTNode leftNode = x.children[possibleIdx - 1];
        KeyValue topKey = x.keys[possibleIdx - 1];
        BTNode possibleNode = x.children[possibleIdx];
        leftNode.keys[leftNode.currentKeyNum] = topKey;

        // 将需要合并的结点的键值对全部放入左节点
        for (int i = 0; i < possibleNode.currentKeyNum; i++) {
            leftNode.keys[i + leftNode.currentKeyNum + 1] = possibleNode.keys[i];
        }
        for (int i = 0; i < possibleNode.currentKeyNum + 1; i++) {
            leftNode.children[i + leftNode.currentKeyNum + 1] = possibleNode.children[i];
        }
        leftNode.currentKeyNum += 1 + possibleNode.currentKeyNum;
        // 更新父结点
        for (int i = possibleIdx; i < x.currentKeyNum; i++) {
            x.keys[i - 1] = x.keys[i];
        }
        x.keys[x.currentKeyNum - 1] = null;
        for (int i = possibleIdx; i < x.currentKeyNum; i++) {
            x.children[i] = x.children[i + 1];
        }
        x.children[x.currentKeyNum] = null;
        x.currentKeyNum--;

        return x;
    }
    private BTNode rightMerge(BTNode x, int possibleIdx) {
        return leftMerge(x, possibleIdx + 1);
    }

    private BTNode rightRotate(BTNode x, int possibleIdx) {
        BTNode rightNode = x.children[possibleIdx + 1];
        KeyValue rightKey = rightNode.keys[0];
        BTNode rightFirstNode = rightNode.children[0];

        KeyValue topKey = x.keys[possibleIdx];
        BTNode possibleNode = x.children[possibleIdx];

        possibleNode.keys[possibleNode.currentKeyNum] = topKey;
        possibleNode.children[possibleNode.currentKeyNum + 1] = rightFirstNode;
        possibleNode.currentKeyNum++;

        x.keys[possibleIdx] = rightKey;
        for (int i = 1; i < rightNode.currentKeyNum; i++) {
            rightNode.keys[i - 1] = rightNode.keys[i];
        }
        rightNode.keys[rightNode.currentKeyNum - 1] = null;
        for (int i = 1; i < rightNode.currentKeyNum + 1; i++) {
            rightNode.children[i - 1] = rightNode.children[i];
        }
        rightNode.children[rightNode.currentKeyNum] = null;
        rightNode.currentKeyNum--;

        return x;
    }

    private BTNode leftRotate(BTNode x, int possibleIdx) {
        BTNode leftNode = x.children[possibleIdx - 1];
        KeyValue leftKey = leftNode.keys[leftNode.currentKeyNum - 1];
        BTNode leftLastNode = leftNode.children[leftNode.currentKeyNum];
        KeyValue topKey = x.keys[possibleIdx - 1];

        BTNode possibleNode = x.children[possibleIdx];
        for (int i = possibleNode.currentKeyNum; i > 0; i--) {
            possibleNode.keys[i] = possibleNode.keys[i - 1];
        }
        for (int i = possibleNode.currentKeyNum + 1; i > 0; i--) {
            possibleNode.children[i] = possibleNode.children[i - 1];
        }

        possibleNode.keys[0] = topKey;
        possibleNode.children[0] = leftLastNode;
        possibleNode.currentKeyNum++;

        x.keys[possibleIdx - 1] = leftKey;
        leftNode.keys[leftNode.currentKeyNum- 1] = null;
        leftNode.children[leftNode.currentKeyNum] = null;
        leftNode.currentKeyNum--;

        return x;
    }


    public void LOAD(String filename){
        File file = new File("D:\\大二上\\数据结构与算法设计\\pj\\src\\"+filename);
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            String line ="";
            while ((line=br.readLine()) != null){
                String key = line;
                String value = br.readLine();
                if (search(key) == null) {
                    this.root = insert(root,key,value);
                }else {
                    search(key).value = value;
                }
            }
            System.out.println("读取文件成功！");
            br.close();
        } catch (IOException e) {
            System.out.println("读取文件失败！- 原因：文件路径错误或者文件不存在");
        }
    }

    public static void main(String[]args){
        BTree T = new BTree();


        T.LOAD("windows.txt");
        //T.put("a","a");

        // T.put("agony","a");
        //System.out.println(T.get(T,"ambush"));
        T.dump(T.root);
    }
    }
