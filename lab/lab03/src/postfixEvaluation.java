/*
代码说明：输入的后缀表达式数字与运算符之间要用一个空格隔开

*/

import java.util.Stack;

public class postfixEvaluation {
    public static void main(String[]args){
        String a = postfixEvaluation("2 sin");
        System.out.println(a);
    }

    public static String postfixEvaluation(String postfix){
        Stack<String> stack = new Stack<String>();
        String[] stringArr = postfix.split(" ");
        for(int i =0;i<stringArr.length;i++){
            if(stringArr[i].matches("\\d{1,}.{0,}")){
                stack.push(stringArr[i]);
            }
            else if(stringArr[i].matches("[-|+|*|/|^|%]")||stringArr[i].equals("sin")||stringArr[i].equals("cos")||stringArr[i].equals("tan")||stringArr[i].equals("cot")||stringArr[i].equals("sec")||stringArr[i].equals("csc")){
                double a = 0;
                double top = Double.parseDouble(stack.pop());
                double second = 0;
                if(!stack.isEmpty()) {
                     second = Double.parseDouble(stack.pop());
                }
                switch (stringArr[i]){
                    case "+":  a = second + top; break;
                    case "-":  a = second - top; break;
                    case "*":  a = second * top;break;
                    case "/":  a = second / top;break;
                    case "^":  a = Math.pow(second, top);break;
                    case "%":  a = second % top;break;
                    case "sin":  a = Math.sin(top);break;
                    case "cos":  a = Math.cos(top);break;
                    case "tan":  a = Math.tan(top);break;
                    case "cot":  a = Math.cos(top)/Math.sin(top);break;
                    case "sec":  a = 1.0/Math.cos(top);break;
                    case "csc":  a = 1.0/Math.sin(top);break;
                    default: break;
                }
                stack.push(a+"");
            }
        }
        return stack.peek();
    }
}
