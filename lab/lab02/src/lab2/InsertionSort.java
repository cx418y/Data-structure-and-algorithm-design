package lab2;
import java.util.ArrayList;
import java.util.List;

public class InsertionSort {
    public static void main(String[]args) {
        double[] g = new double[5];
        for (int i = 0; i < 5; i++) {
            g[i] = Math.random() * 10;
        }
        insertionSort(g);
        //printArray(g);
        InsertionSort[] a = new InsertionSort[5];

        String []h = {"hubf","bhy","bhyk","bhh","acodk"};
        insertionSort(h);
        printArray(h);
    }

    public static int[] insertionSort(int[] nums) {
        for (int n = 1; n < nums.length; n++) {
            for (int i = n; i > 0; i--) {
                if (nums[i] < nums[i - 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = temp;
                } else break;
            }
        }
    return nums;
    }

    public static double[] insertionSort(double[] nums) {
        for (int n = 1; n < nums.length; n++) {
            for (int i = n; i > 0; i--) {
                if ((nums[i] - nums[i - 1])<0.00000000001) {
                    double temp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = temp;
                } else break;
            }
        }
        return nums;
    }

    public static String[] insertionSort(String[] nums) {
        for (int n = 1; n < nums.length; n++) {
            for (int i = n; i > 0; i--) {
                if (compareLess(nums[i],nums[i - 1])) {
                    String temp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = temp;
                } else break;
            }
        }
        return nums;
    }

    public static boolean compareLess(String a,String b){
        boolean result = true;
        a.toLowerCase();
        b.toLowerCase();
        char[] m = a.toCharArray();
        char[] n = b.toCharArray();
        for(int i = 0;i<m.length&&i<n.length;i++){
            if(Integer.valueOf(m[i])>Integer.valueOf(n[i])){
                result = false;
                break;
            }
            else if(Integer.valueOf(m[i])<Integer.valueOf(n[i])){
                result = true;
                break;
            }
            else {}
            if((i==m.length-1)||(i==n.length-1)){
                if(m.length>=n.length){
                    result = false;
                }
                else result = true;
            }
        }
        return result;
    }

    public static void printArray(String[] nums){
        List<String> res = new ArrayList<>();
        for (int i=0;i < nums.length;i++) {
            res.add(nums[i]);
        }
        System.out.println(res);
    }
}
