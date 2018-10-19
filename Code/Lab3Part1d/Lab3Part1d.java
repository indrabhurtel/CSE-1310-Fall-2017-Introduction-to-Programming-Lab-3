/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Indra Bhurtel
 */
public class Lab3Part1d {
	public final static String FILENAME="input.txt";
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		readFile();
	}

	private static void readFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String sCurrentLine;
			int i=1;
			while ((sCurrentLine = br.readLine()) != null) {
				process(i,sCurrentLine);
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void process(int index,String sCurrentLine) {
		int exactStartTime = 0;
		int exactBreakStartTime = 0;
		int exactBreakEndTime = 0;
		int exactEndTime = 0;
		int m;
		String inp,ampm;
		String[] ar=sCurrentLine.split(" ");
		String a1=ar[0];
		String a2=ar[1];
		String a3=ar[2];
		String a4=ar[3];
		exactStartTime=Integer.parseInt(a1.split(":")[0]);
		inp=a1.substring(2);
		ampm=inp.substring(inp.length()-2);
		inp=inp.replace(ampm, "");
		inp=inp.replace(":", "");
		m=Integer.parseInt(inp);
		ampm=ampm.toUpperCase();
		if(ampm.equals("AM") && exactStartTime==12)
			exactStartTime-=12;
		if(ampm.equals("PM") && exactStartTime!=12)
			exactStartTime+=12;
		exactStartTime=exactStartTime*100+m;
		exactBreakStartTime=Integer.parseInt(a2.split(":")[0]);
		inp=a2.substring(2);
		ampm=inp.substring(inp.length()-2);
		inp=inp.replace(ampm, "");
		inp=inp.replace(":", "");
		m=Integer.parseInt(inp);
		ampm=ampm.toUpperCase();
		if(ampm.equals("AM") && exactBreakStartTime==12)
			exactBreakStartTime-=12;
		if(ampm.equals("PM") && exactBreakStartTime!=12)
			exactBreakStartTime+=12;
		exactBreakStartTime=exactBreakStartTime*100+m;
		exactBreakEndTime=Integer.parseInt(a3.split(":")[0]);
		inp=a3.substring(2);
		ampm=inp.substring(inp.length()-2);
		inp=inp.replace(ampm, "");
		inp=inp.replace(":", "");
		m=Integer.parseInt(inp);
		ampm=ampm.toUpperCase();
		if(ampm.equals("AM") && exactBreakEndTime==12)
			exactBreakEndTime-=12;
		if(ampm.equals("PM") && exactBreakEndTime!=12)
			exactBreakEndTime+=12;
		exactBreakEndTime=exactBreakEndTime*100+m;
		exactEndTime=Integer.parseInt(a4.split(":")[0]);
		inp=a4.substring(2);
		ampm=inp.substring(inp.length()-2);
		inp=inp.replace(ampm, "");
		inp=inp.replace(":", "");
		m=Integer.parseInt(inp);
		ampm=ampm.toUpperCase();
		if(ampm.equals("AM") && exactEndTime==12)
			exactEndTime-=12;
		if(ampm.equals("PM") && exactEndTime!=12)
			exactEndTime+=12;
		exactEndTime=exactEndTime*100+m;
		
		processData(index,a1,a2,a3,a4,exactStartTime,exactBreakStartTime,exactBreakEndTime,exactEndTime,Double.parseDouble(ar[4]));
		
	}

	private static void processData(int index,String a1,String a2,String a3,String a4,int exactStartTime, int exactBreakStartTime,int exactBreakEndTime,int exactEndTime,double rate) {
		exactStartTime=validateMilitaryTime(exactStartTime);
		exactBreakStartTime=validateMilitaryTime(exactBreakStartTime);
		exactBreakEndTime=validateMilitaryTime(exactBreakEndTime);
		exactEndTime=validateMilitaryTime(exactEndTime);	
		if(exactStartTime >exactEndTime || exactStartTime >exactBreakStartTime || exactStartTime >exactBreakEndTime || exactBreakStartTime >exactEndTime ||exactBreakStartTime >exactEndTime || exactBreakStartTime >exactEndTime)
		{
			System.out.println("Values are not in Order...");
			System.out.println("Exiting Program...");
			return;
		}
		double recStartTime = change(exactStartTime);
		double recBreakStartTime = change(exactBreakStartTime);
		double recBreakEndTime = change(exactBreakEndTime);
		double recEndTime = change(exactEndTime);
		double res = (recEndTime - recStartTime) - (recBreakEndTime - recBreakStartTime);
		double hoursToPay = calculateHoursToPay(res);

		System.out.printf("%8s%20s%20s%10s\n","Day "+index,"Start","End","Hourly");
		
		System.out.printf("%8s%10s%10s%10s%10s%10s\n","Data:","Start","Break","Break","End","Rate");
		System.out.printf("%8s%10s%10s%10s%10s%10.2f\n","Inputs",a1,a2,a3,a4,rate);
		System.out.printf("%8s%10d%10d%10d%10d\n","Exact",exactStartTime,exactBreakStartTime,exactBreakEndTime,exactEndTime);
		System.out.printf("%8s%10.0f%10.0f%10.0f%10.0f\n\n","Recorded",recStartTime,recBreakStartTime,recBreakEndTime,recEndTime);
		System.out.printf("%16s\n","Total");
		System.out.printf("%8s%10.0f\n","hhmm",res);
		System.out.printf("%8s%10d\n","Hours &",((int) res) / 100);
		System.out.printf("%8s%10d\n","Minutes",((int) res) % 100);
		System.out.printf("%8s%10.2f\n","As hours",hoursToPay);
		System.out.printf("%8s%10.2f per hour\n","At rate",rate);
		System.out.printf("%8s%10.2f\n\n\n\n","Pay $",hoursToPay*rate);
		
	}

	private static int validateMilitaryTime(int hour) {
		if(hour<0)
		{
			hour=Math.abs(hour);
			System.out.println("Since a negative value is handled in the hour, then the exact minute values cannot be negative");
			int minutes=hour%100;
			hour=0;
			return minutes;
		}
		int minutes=hour%100;
		hour=hour/100;
		if(minutes>59)
		{
			int toAdd=minutes/60;
			minutes=minutes%60;
			hour=hour+toAdd;
			System.out.println("As minutes were more then 60,\nNumber of hours added to the hours : "+toAdd+"\nNew value for minutes :"+minutes);
		}
		if(hour==24)
		{
			hour=0;
			System.out.println("As hours were 24. Hourse set to 0");
		}
		if(hour>24)
		{
			hour=hour%24;
			System.out.println("Hours >24. So hours set to modulo of 24");
		}
		return hour*100+minutes;
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
