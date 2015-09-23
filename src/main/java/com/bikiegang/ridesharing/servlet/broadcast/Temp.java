package com.bikiegang.ridesharing.servlet.broadcast;

/**
 * Created by hpduy17 on 9/21/15.
 */
public class Temp implements Runnable {
    public int runTarget = 1;
    public static boolean run1 = false;
    public static boolean run2 = false;
    public static boolean run3 = false;
    public static boolean run4 = false;
    public Temp(int runTarget) {
        this.runTarget = runTarget;
    }

    public Temp() {
    }

    public static void main(String... agrs) {
//        Temp t1 = new Temp(1);
//        Thread cau1 = new Thread(t1);
//        cau1.start();
//
//        Temp t2 = new Temp(2);
//        Thread cau2 = new Thread(t2);
//        cau2.start();
//
//        Temp t3 = new Temp(3);
//        Thread cau3 = new Thread(t3);
//        cau3.start();
//
//        Temp t4 = new Temp(4);
//        Thread cau4 = new Thread(t4);
//        cau4.start();
//
//        while (!(run1&&run2&&run3&&run4)){
//
//        }
        new Temp().cau1();
    }

    @Override
    public void run() {
        switch (runTarget) {
            case 1:
                cau1();
                run1 = true;
                break;
            case 2:
                cau2();
                run2 = true;
                break;
            case 3:
                cau3();
                run3 = true;
                break;
            case 4:
                cau4();
                run4 = true;
                break;
        }
    }

    public void cau1() {
        int count = 0;
        for (int i = 1; i < 100000000; i++) {
            if (LaSoNguyenTo(i)) {
                count++;
            }
        }
        System.out.println("Cau 1: " + count);
    }


    public void cau2() {
        int dem = 0;
        for (int i = 1; i < 100000000; i++) {
            if (LaSoNguyenTo(i) && LaSoDoiXung(i)) {
                //System.out.print(i+" ");
                dem++;
            }
        }
        System.out.println("Cau 2: " + dem);
    }

    public void cau3() {
        int dem = 0;
        int n = 1000000;
        int [] arrSumUocSo = new int[n];
        for(int i = 1; i < n ; i++){
            arrSumUocSo[i] = TinhTongUocSo(i);
        }
        for (int q = 1; q < n; q++) {
            if(arrSumUocSo[q] < q){
                int p = arrSumUocSo[q];
                if(arrSumUocSo[p] == q)
                    dem++;
            }
        }
        System.out.println("Cau 3: " + dem);
    }

    public void cau4() {
        int dem = 0;
        int[] chuso = {2, 4, 6, 8, 10, 2, 4, 6};
        for (int s1 = 1; s1 <= 4; s1++) {
            for (int s2 = 1; s2 <= 4; s2++) {
                for (int s3 = 1; s3 <= 4; s3++) {
                    for (int s4 = 1; s4 <= 4; s4++) {
                        for (int s5 = 1; s5 <= 4; s5++) {
                            for (int s6 = 1; s6 <= 4; s6++) {
                                    int[] dau = {s1, s2, s3, s4, s5, s6};
                                    if (BieuThucBangNhau(dau, chuso))
                                        dem++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Cau 4: " + dem);
    }

    public boolean BieuThucBangNhau(int[] dau, int[] chuso) {
        double result = chuso[0];
        for (int i = 0; i < dau.length; i++) {
            switch (dau[i]) {
                case 1:
                    result += chuso[i + 1];
                    break;
                case 2:

                    result -= chuso[i + 1];
                    break;
                case 3:

                    result *= chuso[i + 1];
                    break;
                case 4:

                    result /= chuso[i + 1];
                    break;
            }
        }

        if (result == chuso[chuso.length - 1]) {
            return true;
        }
        return false;

    }

    public int TongCacChuSo(int k) {
        int sum = 0;
        while (k != 0) {
            sum += k % 10;
            k /= 10;
        }
        return sum;
    }

    public boolean LaSoNguyenTo(int k) {
        if (k < 2)
            return false;
        if (k == 2)
            return true;
        for (int i = 3; i <= Math.sqrt(k); i++) {
            if (k % i == 0)
                return false;
        }
        return true;
    }

    public boolean LaSoDoiXung(int k) {
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

    public int TinhTongUocSo(int k) {
        int sum = 0;
        for (int i = 2; i <= k / 2; i++)
            if (k % i == 0)
                sum += i;
        return sum + 1;
    }
}
