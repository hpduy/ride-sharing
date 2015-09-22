package com.bikiegang.ridesharing.servlet.broadcast;

/**
 * Created by hpduy17 on 9/21/15.
 */
public class Temp {
    public static void main(String... agrs) {
//        System.out.print(sumUocSo(20));
//        cau1();
//        cau2();
         cau3();
//        cau4();
    }

    public static void cau1() {
        int count = 0;
        for (int i = 1; i < 1000; i++) {
            if (isSNT(i)) {
                //System.out.print(i+" ");
                count++;
            }
        }
        System.out.println("Cau 1: " + count);
    }

    public static void cau2() {
        int count = 0;
        for (int i = 1; i < 1000; i++) {
            if (isSNT(i) && isMirrorNumber(i)) {
                //System.out.print(i+" ");
                count++;
            }
        }
        System.out.println("Cau 2: " + count);
    }

    public static void cau3() {
        int count = 0;
        for (int q = 0; q < 10000; q++) {
            for (int p = 0; p < q; p++) {
                if (sumUocSo(q) == p && sumUocSo(p) == q)
                    count++;
            }
        }
        System.out.println("Cau 3: " + count);
    }

    public static void cau4() {
        int a1 = 2;
        int a2 = 4;
        int a3 = 6;
        int a4 = 8;
        int a5 = 10;
        int a6 = 2;
        int a7 = 4;
        int a8 = 6;
        int count = 0;
        for(int i = 1 ; i <= 4 ; i++){
            switch (i){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }

        }
    }

    public static boolean isSNT(int k) {
        if (k < 2)
            return false;
        if (k == 2)
            return true;
        for (int i = 3; i <= k / 2; i++) {
            if (k % i == 0)
                return false;
        }
        return true;
    }

    public static boolean isMirrorNumber(int k) {
        if (k < 10)
            return true;
        int n = 0;
        int temp = k;
        while (temp > 0) {
            n = n * 10 + temp % 10;
            temp = temp / 10;
        }
        return n == k;
    }

    public static int sumUocSo(int k) {
        int sum = 0;
        for (int i = 2; i <= k/2; i++)
            if (k % i == 0)
                sum += i;
        return sum + 1 + k;
    }
}
