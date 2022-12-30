/*
代码说明：
支持整数和小数，可以解决+、-、*、/、mod(%)、^、sin、cos、tan、cot、csc、sec的运算,以及括号（），中括号【】和花括号{}；
输入的表达式数字与运算符号之间要用一个空格隔开
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InfixToPostfix {
public static void main(String[]args){
    StringBuffer a = infixToPostfix("{ 1 + [ 2 * 3 + ( 8 - 4 ) / 2 ] }");
    System.out.println(a);
}

    private static final Map<String, Integer> basic = new HashMap<String, Integer>();
     static {
         basic.put("-", 1);
         basic.put("+", 1);

         basic.put("*", 2);
         basic.put("/", 2);
         basic.put("%",2);

         basic.put("^",4);

         basic.put("sin",3);
         basic.put("cos",3);
         basic.put("tan",3);
         basic.put("cot",3);
         basic.put("csc",3);
         basic.put("sec",3);

         basic.put("(", 0);
         basic.put("{", 0);
         basic.put("[", 0);

         //在运算中  （）的优先级最高，但是此处因程序中需要 故设置为0
             }

    public static StringBuffer infixToPostfix(String infix){
        Stack<String> stack = new Stack<String>();
        String[] stringArr = infix.split(" ");
        StringBuffer result = new StringBuffer();

        for(int i =0;i<stringArr.length;i++){
            if(stringArr[i].matches("\\d{1,}.{0,}")){
                result.append(stringArr[i]+" ");
            }
            else if(stringArr[i].matches("[-|+|*|/|(|)|^|%|{|}]")||stringArr[i].equals("[")||stringArr[i].equals("]")||stringArr[i].equals("sin")||stringArr[i].equals("cos")||stringArr[i].equals("tan")||stringArr[i].equals("cot")||stringArr[i].equals("sec")||stringArr[i].equals("csc")){
                if(stack.isEmpty()||stringArr[i].equals("(")||stringArr[i].equals("[")||stringArr[i].equals("{")){
                    stack.push(stringArr[i]);
                }else if(stringArr[i].equals(")")){
                    for(int size = stack.size();size>0&&(!stack.peek().equals("("));size--) {
                        result.append(stack.pop());
                        result.append(" ");
                    }
                    stack.pop();  //将"("移出stack;
                }
                else if(stringArr[i].equals("]")){
                    for(int size = stack.size();size>0&&(!stack.peek().equals("["));size--) {
                        result.append(stack.pop());
                        result.append(" ");
                    }
                    stack.pop();  //将"("移出stack;
                }else if(stringArr[i].equals("}")){
                    for(int size = stack.size();size>0&&(!stack.peek().equals("{"));size--) {
                        result.append(stack.pop());
                        result.append(" ");
                    }
                    stack.pop();  //将"("移出stack;
                }
                else {
                    if(basic.get(stringArr[i])>basic.get((stack.peek()))){
                        stack.push(stringArr[i]);
                    }
                    else{
                        for(int size = stack.size();size>0&&(basic.get(stringArr[i])<=basic.get((stack.peek())));size--) {
                            result.append(stack.pop()+" ");
                        }
                        stack.push(stringArr[i]);
                    }
                }
            }
            else System.out.println("undefined");
        }
        if(!stack.isEmpty()){
            for(int size = stack.size();size>0;size--){
                result.append(stack.pop()+" ");
            }
        }
        return result;
    }

}
