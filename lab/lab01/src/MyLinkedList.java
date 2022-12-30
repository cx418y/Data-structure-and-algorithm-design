import javax.xml.soap.Node;

class MyLinkedList {
    public static int length;
    private Node head,tail;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        this.head = new Node(-1,null);
        tail = head;
        length = 0;
    }
    private class Node{
        int val;
        Node next;
        Node(int val,Node next){
            this.val = val;
            this.next = next;
        }
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index>=length){
            return -1;
        }
        else if(index==0){
            return head.val;
        }
        else{
            Node p = head;
            for(int n = 0;n<index;n++){
                    p=p.next;
            }
            return p.val;
        }
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        if(head.val == -1){
            head.val=val;
        }
        else {
            Node p = new Node(val, head);
            head = p;
        }
        length++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node p = new Node(val,null);
        if(head.val==-1){
            head = p;
            tail = p;
        }
        else if(tail.val==-1){
            tail = p;
        }
        else {
            tail.next = p;
            tail = p;
        }
        length++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index==0){
            addAtHead(val);
            length++;
        }
        else if(index==length-1){
            addAtTail(val);
            length++;
        }else if(index>=length){
            System.out.print(-1);
        }
        else {
            Node p0 = head;
            for(int n = 1; n<=index; n++){
                p0=p0.next;
            }
            Node p = new Node(val,p0.next);
            p0.next = p;
            length++;
        }

    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index==0){
            if(head.val==-1){
                System.out.println(-1);
            }
            else {
                head = head.next;
                length--;
            }
        }
        else if(index>=length){
            System.out.println(-1);
        }else {
            Node p0 = head;
            Node temp = p0;
            for(int n = 1;n<=index;n++){
                temp = p0;
                p0 = p0.next;
            }
            temp.next = p0.next;
            length--;
            if(p0.next==null){
                tail=temp;
            }
        }
    }

    public static void main(String args[]){
        MyLinkedList obj = new MyLinkedList();
        int param_1 = obj.get(0);
        obj.addAtHead(2);
        obj.addAtHead(1);
        obj.addAtTail(3);
        obj.addAtIndex(1,10);
        obj.deleteAtIndex(4);
        System.out.print(obj.get(0));
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 **/