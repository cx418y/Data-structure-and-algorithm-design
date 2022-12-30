package lab2;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    public static void merge(int[] nums, int start, int mid, int end) {
        //建立左右两个数组
        int[] left = new int[mid - start + 1];
        int[] right = new int[end - mid];

        //给左右两个数组赋值
        for (int i = start, j = 0; i <= mid; i++, j++) {
            left[j] = nums[i];
        }
        for (int i = mid + 1, j = 0; i <= end; i++, j++) {
            right[j] = nums[i];
        }

        //建立临时数组temp归并左右两个数组
        int[] temp = new int[end - start + 1];
        int i = 0, j = 0, k = 0;
        while ((j < left.length) && (k < right.length)) {
            if (left[j] < right[k]) {
                temp[i] = left[j];
                j++;
            } else {
                temp[i] = right[k];
                k++;
            }
            i++;
        }

        //判断左右两个数组是否有未归并到temp中的
        if (j < left.length) {
            for (; j <= left.length - 1; j++, i++) {
                temp[i] = left[j];
            }
        }
        if (k < right.length) {
            for (; k <= right.length - 1; k++, i++) {
                temp[i] = right[k];
            }
        }

        for (int n = 0, m = start; n < i; n++, m++) {
            nums[m] = temp[n];
        }

    }

    public static int[] mergeSort(int[] nums, int start, int end) {
        if (start<end) {
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid);//对左边数组进行递归分割
            mergeSort(nums, mid + 1, end);//对右边数组进行递归分割
            merge(nums, start, mid, end);//合并数组
        }
        return nums;
    }

    public static void merge(double[] nums, int start, int mid, int end) {
        //建立左右两个数组
        double[] left = new double[mid - start + 1];
        double[] right = new double[end - mid];

        //给左右两个数组赋值
        for (int i = start, j = 0; i <= mid; i++, j++) {
            left[j] = nums[i];
        }
        for (int i = mid + 1, j = 0; i <= end; i++, j++) {
            right[j] = nums[i];
        }

        //建立临时数组temp归并左右两个数组
        double[] temp = new double[end - start + 1];
        int i = 0, j = 0, k = 0;
        while ((j < left.length) && (k < right.length)) {
            if ((left[j]-right[k])<0.00000000001) {
                temp[i] = left[j];
                j++;
            } else {
                temp[i] = right[k];
                k++;
            }
            i++;
        }

        //判断左右两个数组是否有未归并到temp中的
        if (j < left.length) {
            for (; j <= left.length - 1; j++, i++) {
                temp[i] = left[j];
            }
        }
        if (k < right.length) {
            for (; k <= right.length - 1; k++, i++) {
                temp[i] = right[k];
            }
        }

        for (int n = 0, m = start; n < i; n++, m++) {
            nums[m] = temp[n];
        }

    }

    public static double[] mergeSort(double[] nums, int start, int end) {
        if (start<end) {
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid);//对左边数组进行递归分割
            mergeSort(nums, mid + 1, end);//对右边数组进行递归分割
            merge(nums, start, mid, end);//合并数组
        }
        return nums;
    }

    public static void merge(String[] nums, int start, int mid, int end) {
        //建立左右两个数组
        String[] left = new String[mid - start + 1];
        String[] right = new String[end - mid];

        //给左右两个数组赋值
        for (int i = start, j = 0; i <= mid; i++, j++) {
            left[j] = nums[i];
        }
        for (int i = mid + 1, j = 0; i <= end; i++, j++) {
            right[j] = nums[i];
        }

        //建立临时数组temp归并左右两个数组
        String[] temp = new String[end - start + 1];
        int i = 0, j = 0, k = 0;
        while ((j < left.length) && (k < right.length)) {
            if (compareLess(left[j],right[k])) {
                temp[i] = left[j];
                j++;
            } else {
                temp[i] = right[k];
                k++;
            }
            i++;
        }

        //判断左右两个数组是否有未归并到temp中的
        if (j < left.length) {
            for (; j <= left.length - 1; j++, i++) {
                temp[i] = left[j];
            }
        }
        if (k < right.length) {
            for (; k <= right.length - 1; k++, i++) {
                temp[i] = right[k];
            }
        }

        for (int n = 0, m = start; n < i; n++, m++) {
            nums[m] = temp[n];
        }

    }

    public static String[] mergeSort(String[] nums, int start, int end) {
        if (start<end) {
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid);//对左边数组进行递归分割
            mergeSort(nums, mid + 1, end);//对右边数组进行递归分割
            merge(nums, start, mid, end);//合并数组
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
}

