import java.util.Scanner;
public class test {
    //在此完成相应程序
    public static int[][] Matrix(int A[][], int B[][]) {
        Scanner input = new Scanner(System.in);
        int m1 = A.length;
        int m2 = A[0].length;
        int n1 = B.length;
        int n2 = B[0].length;
        int C[][] = new int[m1][n2];
        if (m2 == n1) {
            for (int i = 0; i < m1; i++) {
                for (int j = 0; j < n2; j++) {
                    for (int a = 0, b = 0; a < m2 && b < n1; a++,b++) {
                        C[i][j] += A[i][a] * B[b][j];
                    }
                }
            }
            for (int i = 0; i < m1; i++) {
                for (int j = 0; j < n2; j++) {
                    System.out.print(C[i][j] + " ");
                }
                System.out.println();
            }
            return C;
        } else {
            System.out.println("矩阵不相容");
            return null;
        }

    }
public static void main(String[]args){
        int[][]A = {{1,2,6,0},{1,5,0,1},};
        int[][]B  = {{1,2},{5,1},{0,0}};
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 4; j++) {
            System.out.print(A[i][j] + " ");
        }
        System.out.println();
    }
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 2; j++) {
            System.out.print(B[i][j] + " ");
        }
        System.out.println();
    }
        Matrix(A,B);
}
}


