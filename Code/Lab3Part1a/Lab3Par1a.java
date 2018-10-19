/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author Indra Bhurtel
 */
public class Lab3Par1a {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        

        Scanner input=new Scanner(System.in);
        int exactStartTime = 0;
        int exactBreakStartTime = 0;
        int exactBreakEndTime = 0;
        int exactEndTime = 0;
        System.out.print("Exact Start Time : ");
        exactStartTime=input.nextInt();
        System.out.print("Exact Break Start Time : ");
        exactBreakStartTime=input.nextInt();
        System.out.print("Exact Break End Time : ");
        exactBreakEndTime=input.nextInt();
        System.out.print("Exact End Time : ");
        exactEndTime=input.nextInt();
        double recStartTime = change(exactStartTime);
        double recBreakStartTime = change(exactBreakStartTime);
        double recBreakEndTime = change(exactBreakEndTime);
        double recEndTime = change(exactEndTime);
        double res = (recEndTime - recStartTime) - (recBreakEndTime - recBreakStartTime);
        double hoursToPay = calculateHoursToPay(res);
        System.out.printf("%8s%20s%10s\n","Time","Start","End");
        System.out.printf("%8s%10s%10s%10s%10s\n","Data:","Start","Break","Break","End");
        System.out.printf("%8s%10d%10d%10d%10d\n","Exact",exactStartTime,exactBreakStartTime,exactBreakEndTime,exactEndTime);
        System.out.printf("%8s%10.0f%10.0f%10.0f%10.0f\n\n","Recorded",recStartTime,recBreakStartTime,recBreakEndTime,recEndTime);
        System.out.printf("%16s\n","Total");
        System.out.printf("%8s%10.0f\n","hhmm",res);
        System.out.printf("%8s%10d\n","Hours &",((int) res) / 100);
        System.out.printf("%8s%10d\n","Minutes",((int) res) % 100);
        System.out.printf("%8s%10.2f\n","As hours",hoursToPay);
        System.out.printf("%8s%10.2f per hour\n","At rate",8.25f);
        System.out.printf("%8s%10.2f\n","Pay $",hoursToPay*8.25);
       
    }

    public static double change(int num) {
        int n = num % 100;
        if (n <= 7) {
            num = num / 100;
            num = num * 100;
        } else if (n >= 8 && n <= 22) {
            num = num / 100;
            num = num * 100 + 15;
        } else if (n >= 23 && n <= 37) {
            num = num / 100;
            num = num * 100 + 30;
        } else if (n >= 38 && n <= 52) {
            num = num / 100;
            num = num * 100 + 45;
        } else {
            num = num / 100;
            num = num * 100 + 100;
        }
        return num;
    }

    private static double calculateHoursToPay(double res) {
        int temp = ((int) res) % 100;
        switch (temp) {
            case 15:
                res += 10;
                break;
            case 30:
                res += 20;
                break;
            case 45:
                res += 30;
                break;
        }
        return res/100;
    }
}