import com.sun.jmx.snmp.SnmpUnknownAccContrModelException;

public class ShortestPath {
    public static int[][] matrix;  //邻接矩阵
    public static int[][] path;//辅助矩阵
    public static String[] locationName;

    ShortestPath(int[][]matrix,String[]locationName){
        this.matrix = new int[matrix.length][matrix.length];
        this.path = new int[matrix.length][matrix.length];
        this.locationName = new String[matrix.length];

        for(int i = 0;i<matrix.length;i++){
            this.locationName[i] = locationName[i];
            for(int j = 0;j<matrix[i].length;j++) {
                this.matrix[i][j] = matrix[i][j];
                if (matrix[i][j] != Integer.MAX_VALUE) {
                    this.path[i][j] = i;
                }else this.path[i][j] = -1;
            }
        }
    }

    //Floyd算法实现
    public static void floyd(int[][]matrix){
        for(int k = 0;k<matrix.length;k++){
            for(int i = 0;i<matrix.length;i++){
                for(int j = 0;j<matrix.length;j++){
                    if((matrix[i][k]!=Integer.MAX_VALUE)&&(matrix[k][j]!=Integer.MAX_VALUE)&&matrix[i][j]>(matrix[i][k]+matrix[k][j])){
                        matrix[i][j] = matrix[i][k]+matrix[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
    }

    //打印路径
    public static String printPath(int[][]matrix,int start,int end){
        floyd(matrix);

        if(matrix[start][end]==Integer.MAX_VALUE){
            return "没有相应的路径";
        }
        else {
            StringBuilder a = new StringBuilder();
            String b = "从" + locationName[start] + "到" + locationName[end] + "最短用时为" + matrix[start][end]+"min, 路径为：";
            a.append(b);
            int i = start, j = end;
            a.append(locationName[j]+" <-- ");
            while (path[i][j] != i) {
                j = path[i][j];
                a.append(locationName[j]+" <-- ");
            }
            a.append(locationName[i]);

            String result = a.toString();
            return result;
        }
    }


    public static void main(String[]args){
        int [][] matrix = {
                {0,1,5,3},
                {1,0,Integer.MAX_VALUE,10},
                {5,Integer.MAX_VALUE,0,9},
                {3,10,Integer.MAX_VALUE,0}
        };
        String[]name = {"a","b","c","d"};

        ShortestPath a = new ShortestPath(matrix,name);
        System.out.print(a.printPath(matrix,3,1));
    }
}
