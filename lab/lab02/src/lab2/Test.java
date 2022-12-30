package lab2;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[]args){
        int n = 30000;
        System.out.println("n="+n+":");
        for(int m = 1;m<5;m++) {
            int[] a = new int[n];
            int[] b = new int[n];
            int[] c = new int[n];
            int[] d = new int[n];
            int[] e = new int[n];
            int[] f = new int[n];
            int[] g = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = (int) (Math.random() * n);
                b[i] = a[i];
                c[i] = a[i];
                d[i] = a[i];
                e[i] = a[i];
                f[i] = a[i];
                g[i] = a[i];

            }

            long begin1 = System.nanoTime();
            InsertionSort.insertionSort(a);
            long finish1 = System.nanoTime();
            //printArray(a);
            //System.out.println(insertionSort(a));
            System.out.println("insertionSort:" + (finish1 - begin1) + "ns");

            long begin2 = System.nanoTime();
            MergeSort.mergeSort(b, 0, b.length - 1);
            long finish2 = System.nanoTime();
            //printArray(b);
            System.out.println("mergeSort:" + (finish2 - begin2) + "ns");


            long begin4 = System.nanoTime();
            CombineSort.combineSort(d,45);
            long finish4 = System.nanoTime();
            System.out.println("combineSort45:"+(finish4-begin4)+"ns");

            long begin5 = System.nanoTime();
            CombineSort.combineSort(e,50);
            long finish5 = System.nanoTime();
            System.out.println("combineSort50:"+(finish5-begin5)+"ns");

            long begin6 = System.nanoTime();
            CombineSort.combineSort(e,55);
            long finish6 = System.nanoTime();
            System.out.println("combineSort55:"+(finish6-begin6)+"ns");

            long begin7 = System.nanoTime();
            CombineSort.combineSort(e,60);
            long finish7 = System.nanoTime();
            System.out.println("combineSort60:"+(finish7-begin7)+"ns");

            long begin3 = System.nanoTime();
            CombineSort.combineSort(c,70);
            long finish3 = System.nanoTime();
            System.out.println("combineSort70:"+(finish3-begin3)+"ns");
            System.out.println();
        }

    }

    public static void printArray(int[] nums){
        List<Integer> res = new ArrayList<>();
        for (int i=0;i < nums.length;i++) {
            res.add(nums[i]);
        }

        System.out.println(res);
    }

    public static void printArray(String[] nums){
        List<String> res = new ArrayList<>();
        for (int i=0;i < nums.length;i++) {
            res.add(nums[i]);
        }


        System.out.println(res);
    }
}
