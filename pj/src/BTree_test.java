import java.security.Key;
import java.util.Scanner;

public class BTree_test {
    public static void main(String[]args){
        try{
        boolean quit = false;
        BTree T = new BTree();
        System.out.println("COMMAND：INSERT(key,value)，DELETE(key)，GET(key)，PUT(key,value)，DUMP()，LOAD(filename)");
        System.out.println("You can input 'Q' to quit");
        while (!quit) {
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();

            if (command.equals("Q")){
                System.out.println("see you next time!");
                quit = true;
            }
            else if(command.startsWith("INSERT(")&&command.endsWith(")")&&command.contains(",")){
                int index1 = command.indexOf("(");
                int index2 = command.indexOf(",");
                int index3 = command.indexOf(")");
                String key = command.substring(index1+1,index2);
                String value = command.substring(index2+1,index3);
                T.insert(key,value);
            }
            else if(command.startsWith("PUT(")&&command.endsWith(")")&&command.contains(",")){
                int index1 = command.indexOf("(");
                int index2 = command.indexOf(",");
                int index3 = command.indexOf(")");
                String key = command.substring(index1+1,index2);
                String value = command.substring(index2+1,index3);
                T.put(key,value);
            }
            else if(command.startsWith("GET(")&&command.endsWith(")")){
                int index1 = command.indexOf("(");
                int index2 = command.indexOf(")");
                String key = command.substring(index1+1,index2);
                System.out.println(T.get(T,key));
            }
            else if(command.startsWith("DELETE(")&&command.endsWith(")")){
                int index1 = command.indexOf("(");
                int index2 = command.indexOf(")");
                String key = command.substring(index1+1,index2);
                 T.delete(key);
            }
            else if (command.startsWith("LOAD(")&&command.endsWith(")")){
                int index1 = command.indexOf("(");
                int index2 = command.indexOf(")");
                String filename = command.substring(index1+1,index2);
                T.LOAD(filename);
                T.dump(T.root);
            }
            else if (command.equals("DUMP()")){
                T.dump(T.root);
            }
            else {
                System.out.println("Please enter the right command");
            }
        }
    }catch (Exception e){
            System.out.println("Exception");
        }
    }
}
