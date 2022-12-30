public class Combine {
    public static int combine(int[] array){
        int n = 30;
        int maxMum = array[0];
        if(array.length<=n){
            maxMum = MaxmumSubarray.bruteForce(array);
        }else {
            int start = 0, end = 0;
            int sum0;
            int[] sum = new int[array.length];

            //长度小于n的子数组用暴力求解
            for(int i = 0;i<n;i++){
                sum0 = array[i];
                for(int j=i-1;j>=0;j--){
                    sum0 += array[j];
                    if(sum0>maxMum){
                        maxMum = sum0;
                        end = i;
                        start = j;
                    }
                }
                if(i==n-1){
                    sum[i] = sum0;
                }
            }

            //剩余部分用动态规划
            int current_start = 0;
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

            System.out.println("The maxMum is "+maxMum+", from "+start+" to " +end);
        }

        return maxMum;
    }
}
