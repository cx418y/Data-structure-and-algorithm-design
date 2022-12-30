import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[]args){
        System.out.println(minWindowIII("ABCDAEF","AA"));
    }


    public static String minWindowIII(String S, String T) {
        if (S.length()<T.length()||T.length()==0) {
            return "";
        }
        // 共256个字符，所以初始一个256的数组代替map

        String s = S.toUpperCase();
        String t = T.toUpperCase();
        int[] td = new int[256];
        for (char c : t.toCharArray()) {
            td[c]++;
        }

        // 找到的满足条件的匹配字符串的起止索引
        int first = -1;
        int end = 0;
        int minLen = s.length();

        // 标示当前遍历的起始位置
        int start = 0;

        // 标示找到的匹配个数
        int found = 0;

        // target的长度
        int tLen = t.length();

        // source的map
        int[] sd = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            sd[curChar]++;

            // td中存在该元素的空缺，则计数器+1
            if (sd[curChar] <= td[curChar]) {
                found++;
            }

            if (found == tLen) {
                // 从开头删除不在t中或者数量大于在t中的字符；
                while (start <= i && sd[s.charAt(start)] > td[s.charAt(start)]) {
                    sd[s.charAt(start)]--;
                    start++;
                }

                // 第一次发现 或者 判断当前发现的字符串是否小于之前发现的
                if (first<0 || i-start+1 < minLen) {
                    first = start;
                    end = i;
                    minLen = i - start + 1;
                }

                // 把start前移，当前sd的缓存去除当前start指向的元素，found计数器减1
                sd[s.charAt(start)]--;
                found--;
                start++;
            }
        }

        if (first >= 0) {
            return s.substring(first, end + 1);
        }

        return "";

    }
}
