public class MaxmumSubarray {

    public static int bruteForce(int[] array){
        int start = 0, end = 0;
        int maxMum = array[0];
        int sum;
        for(int i = 0;i<array.length;i++){
            sum = array[i];
            for(int j=i-1;j>=0;j--){
                sum += array[j];
                if(sum>maxMum){
                    maxMum = sum;
                    end = i;
                    start = j;
                }

            }
        }
        System.out.println("The maximum is "+maxMum+", from "+start+" to " +end);
        return maxMum;
    }

    public static  int  recursive(int[] array){

        int maxMum = array[0];
        int []sum = new int[array.length];
        sum[0] = array[0];
        int start =0;
        int end = 0;
        int current_start = 0;
        for(int i =1;i<array.length;i++){
            if(sum[i-1]>0){
                sum[i] = sum[i-1]+array[i];
            }else {
                sum[i] = array[i];
                current_start = i;
            }
            if(sum[i]>maxMum){
                maxMum =sum[i];
                start = current_start;
                end = i;
            }
        }
        System.out.println("The maximum is "+maxMum+", from "+start+" to " +end);
        return maxMum;
    }

    public static int combine(int[] array,int n){
        int maxMum = array[0];
        if(array.length<=n){
            maxMum = bruteForce(array);
        }else {
            int start = 0, end = 0,current_start = 0;
            int sum0;
            int maxi;
            int[] sum = new int[array.length];

            //长度小于n的子数组用暴力求解
            for(int i = 0;i<n;i++) {
                sum0 = array[i];
                if (i == n - 1) {
                    maxi = array[i];
                    sum[i] = maxi;
                    current_start = i;
                    for (int j = i - 1; j >= 0; j--) {
                        sum0 += array[j];
                        if (sum0 > maxMum) {
                            maxMum = sum0;
                            end = i;
                            start = j;
                        }
                        if(sum0 > maxi){
                            maxi = sum0;
                            sum[i] = maxi;
                            current_start = j;
                        }
                    }

                }
                else {
                for (int j = i - 1; j >= 0; j--) {
                    sum0 += array[j];
                    if (sum0 > maxMum) {
                        maxMum = sum0;
                        end = i;
                        start = j;
                    }
                }
            }
            }

            //剩余部分用动态规划

            for(int i =n;i<array.length;i++){
                if(sum[i-1]>0){
                    sum[i] = sum[i-1]+array[i];
                }else {
                    sum[i] = array[i];
                    current_start = i;
                }
                if(sum[i]>maxMum){
                    maxMum =sum[i];
                    start = current_start;
                    end = i;
                }
            }

            System.out.println("The maximum is "+maxMum+", from "+start+" to " +end);
        }

        return maxMum;
    }



    public static void main(String[] args){
        int [] a = {0,13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7,-11};

        int size = 3000;
       // int n = 40;
        int [] b = new int [size];
        for(int i = 0; i<b.length;i++){
            b[i] =(int)(100*(Math.random()-0.5));
            System.out.print(b[i]+" ");
        }

        System.out.println();
        System.out.println("size = "+size);
        long begin = System.nanoTime();
        bruteForce(b);
        long finish = System.nanoTime();
        System.out.println("bruteForce: "+(finish-begin)+"ns");

        long begin2 = System.nanoTime();
        recursive(b);
        long finish2 = System.nanoTime();
        System.out.println("recursive: "+(finish2-begin2)+"ns");


        long begin3 = System.nanoTime();
        combine(b,20);
        long finish3 = System.nanoTime();
        System.out.println("combine20: "+(finish3-begin3)+"ns");

        long begin4 = System.nanoTime();
        combine(b,25);
        long finish4 = System.nanoTime();
        System.out.println("combine25: "+(finish4-begin4)+"ns");

        long begin5 = System.nanoTime();
        combine(b,30);
        long finish5 = System.nanoTime();
        System.out.println("combine30: "+(finish5-begin5)+"ns");

        long begin6 = System.nanoTime();
        combine(b,35);
        long finish6 = System.nanoTime();
        System.out.println("combine35: "+(finish6-begin6)+"ns");





    }

}
