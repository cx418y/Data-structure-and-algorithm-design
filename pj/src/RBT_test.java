import java.util.Scanner;

public class RBT_test {
    public static void main(String[]args){
        try {
        boolean quit = false;
        RBT T = new RBT();
        System.out.println("COMMAND：INSERT(key,value)，DELETE(key)，GET(key)，PUT(key,value)，DUMP()，LOAD(filename)");
        System.out.println("You can input 'Q' to quit");
        while (!quit) {
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();

            if (command.equals("Q")) {
                System.out.println("see you next time!");
                quit = true;
            } else if (command.startsWith("INSERT(") && command.endsWith(")") && command.contains(",")) {
                int index1 = command.indexOf("(");
                int index2 = command.indexOf(",");
                int index3 = command.indexOf(")");
                String key = command.substring(index1 + 1, index2);
                String value = command.substring(index2 + 1, index3);
                RBT.Node x = new RBT.Node(key, value, false, null, null, null);
                T.insert(T, x);
            } else if (command.startsWith("PUT(") && command.endsWith(")") && command.contains(",")) {
                int index1 = command.indexOf("(");
                int index2 = command.indexOf(",");
                int index3 = command.indexOf(")");
                String key = command.substring(index1 + 1, index2);
                String value = command.substring(index2 + 1, index3);
                T.put(T, key, value);
            } else if (command.startsWith("GET(") && command.endsWith(")")) {
                int index1 = command.indexOf("(");
                int index2 = command.indexOf(")");
                String key = command.substring(index1 + 1, index2);
                System.out.println(T.get(T, key));
            } else if (command.startsWith("DELETE(") && command.endsWith(")")) {
                int index1 = command.indexOf("(");
                int index2 = command.indexOf(")");
                String key = command.substring(index1 + 1, index2);
                RBT.Node x = T.search(T, T.root, key);
                if (x.getKey() == null) {
                    System.out.println("Error: key missing!");
                } else T.delete(T, x);
            } else if (command.startsWith("LOAD(") && command.endsWith(")")) {
                int index1 = command.indexOf("(");
                int index2 = command.indexOf(")");
                String filename = command.substring(index1 + 1, index2);
                T.LOAD(T, filename);
                T.dump(T.root);
            } else if (command.equals("DUMP()")) {
                T.dump(T.root);
            } else {
                System.out.println("Please enter the right command");
            }

        }
        }catch (Exception e){
            System.out.println("Exception");
        }
        }
}
