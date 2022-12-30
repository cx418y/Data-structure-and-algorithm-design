import java.util.Arrays;

public class A {

        public static void merge(int[] a, int low,int mid,int high) {



            //建立左右两个数组

            int[] L=new int[mid-low+1];

            int[] R=new int[high-mid];

            //给左右数组赋值

            int i1,j1;

            for (i1= 0,j1=low; i1< L.length; i1++)

                L[i1]=a[j1++];

            int i2,j2;

            for (i2= 0,j2=mid+1; i2< R.length; i2++)

                R[i2]=a[j2++];

            //拿来放临时数据

            int[] temp=new int[high-low+1];

            int i3=0;

            int j3=0;

            int k1=0;

            while (i3<L.length&&j3<R.length) {

                if (L[i3]<R[j3]) {

                    temp[k1++]=L[i3++];

                }else {

                    temp[k1++]=R[j3++];

                }

            }

            //放剩下未放完的左数据(此时右数据已经放完)

            if (i3<mid-low+1&&j3>=high-mid) {

                for (int j5= i3; j5 <mid-low+1; j5++,k1++) {

                    temp[k1]=L[j5];

                }

            }



            //放剩下未放完的右数据(此时左数据已经放完)

            if (j3<high-mid&&i3>=mid-low+1) {

                for (int i5= j3; i5 <high-mid; i5++,k1++) {

                    temp[k1]=R[i5];

                }

            }

            if (k1<=high ) {

                for (int j4 = 0; j4 < temp.length; j4++) {

                    a[j4]=temp[j4];

                }



            }



        }



        public static void mergeSort(int[] a,int lo,int hig) {

            if (lo<hig) {

                int mid=(hig+lo)/2;

                int[] Le=new int[mid+1];

                int[] Ri=new int[hig-mid];

                mergeSort(Le,lo,mid);

                mergeSort(Ri,mid+1,hig);

                merge(a, lo, mid, hig);

            }

        }

        //测试一下

        public static void main(String[] args) {

            int[] aa= {22,5,3,3,8,3,0,5,6,11};

            mergeSort(aa,0,aa.length-1);

            System.out.println(Arrays.toString(aa));

        }

    }
