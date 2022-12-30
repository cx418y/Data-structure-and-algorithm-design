import javafx.util.Builder;

import java.nio.Buffer;
import java.util.Locale;

public class BeamSearch {

   static class Token{
        StringBuffer name;
        double pro;

        public Token(StringBuffer name, double pro){
            this.name = name;
            this.pro = pro;
        }
    }


    public static Token[][] beamSearch(Token[][] array,int k, int step){

       Token []tokens = new Token[k*k];
       for(int i = 0; i<tokens.length;i++){
           tokens[i] = new Token(new StringBuffer(""),0);
       }

       Token [][]result = new Token[step][k];

        for(int i = 0; i<result.length;i++) {
            for (int j = 0; j < k; j++) {
                result[i][j] = new Token(new StringBuffer(""), 0);
            }
        }

        sort(array[0],0,array[0].length-1);
        for(int i = 0;i<k;i++){
            tokens[i*k].name = array[0][i].name;
            tokens[i*k].pro = array[0][i].pro;
            result[0][i].name = array[0][i].name;
            result[0][i].pro = array[0][i].pro;
        }

        for(int i =1;i<step;i++){
            sort(array[i],0,array[i].length-1);
            for(int j = 0; j<k;j++){
                for(int q = 0;q<k;q++){
                    StringBuffer b = new StringBuffer(result[i-1][j].name);
                    b.append(array[i][q].name);
                    tokens[j*k+q].name = b;
                    tokens[j*k+q].pro = (result[i-1][j].pro) * (array[i][q].pro);

                }
            }
            sort(tokens,0,tokens.length-1);
            for(int m = 0;m<k;m++){
                result[i][m].name = tokens[m].name;
                result[i][m].pro = tokens[m].pro;

            }
        }

        for (int i = 0;i<k;i++){
            System.out.println(result[step-1][i].name+"  Probability is "+result[step-1][i].pro);
        }
        return result;
    }


    public static void sort(Token[] tokens,int p, int r){
        if(p<r){
            int i = p-1;
            int j = p;
            for(;j<r;j++){
                if(tokens[j].pro>=tokens[r].pro){
                    i++;
                    Token temp = tokens[i];
                    tokens[i] = tokens[j];
                    tokens[j] = temp;
                }
            }
            Token temp2 = tokens[r];
            tokens[r] = tokens[i+1];
            tokens[i+1] = temp2;
            sort(tokens,p,i);
            sort(tokens,i+1,r);
        }
    }

    public static void main(String[] args){
        Token [][]a = {
                {new Token(new StringBuffer("A"),0.2),new Token(new StringBuffer("B"),0.5),new Token(new StringBuffer("C"),0.3)},
                {new Token(new StringBuffer("A"),0.5),new Token(new StringBuffer("B"),0.3),new Token(new StringBuffer("C"),0.2)},
                {new Token(new StringBuffer("A"),0.3),new Token(new StringBuffer("B"),0.2),new Token(new StringBuffer("C"),0.5)},
        };
        Token[] b = new Token[2];
        Token[][]result = new Token[4][2];
       beamSearch(a,2,3);
    }
}
