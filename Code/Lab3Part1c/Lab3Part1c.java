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
public class Lab3Part1c {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here

		Scanner input=new Scanner(System.in);
		input.useDelimiter(":");
		int exactStartTime = 0;
		int exactBreakStartTime = 0;
		int exactBreakEndTime = 0;
		int exactEndTime = 0;
		int h,m;
		String a1,a2,a3,a4;
		String inp=null;
		String ampm;
		System.out.print("Exact Start Time (HH:MM(AM/PM)): ");
		h=input.nextInt();
		inp=input.nextLine();
		a1=h+inp;
		ampm=inp.substring(inp.length()-2);
		inp=inp.replace(ampm, "");
		inp=inp.replace(":", "");
		m=Integer.parseInt(inp);
		ampm=ampm.toUpperCase();
		if(ampm.equals("AM") && h==12)
			h-=12;
		if(ampm.equals("PM") && h!=12)
			h+=12;
		exactStartTime=h*100+m;
		System.out.print("Exact Break Start Time (HH:MM(AM/PM)): ");
		h=input.nextInt();
		inp=input.nextLine();
		a2=h+inp;
		ampm=inp.substring(inp.length()-2);
		inp=inp.replace(ampm, "");
		inp=inp.replace(":", "");
		m=Integer.parseInt(inp);
		ampm=ampm.toUpperCase();
		if(ampm.equals("AM") && h==12)
			h-=12;
		if(ampm.equals("PM") && h!=12)
			h+=12;
		exactBreakStartTime=h*100+m;
		System.out.print("Exact Break End Time (HH:MM(AM/PM)): ");
		h=input.nextInt();
		inp=input.nextLine();
		a3=h+inp;
		ampm=inp.substring(inp.length()-2);
		inp=inp.replace(ampm, "");
		inp=inp.replace(":", "");
		m=Integer.parseInt(inp);
		ampm=ampm.toUpperCase();
		if(ampm.equals("AM") && h==12)
			h-=12;
		if(ampm.equals("PM") && h!=12)
			h+=12;
		exactBreakEndTime=h*100+m;
		System.out.print("Exact End Time : (HH:MM(AM/PM))");
		h=input.nextInt();
		inp=input.nextLine();
		a4=h+inp;
		ampm=inp.substring(inp.length()-2);
		inp=inp.replace(ampm, "");
		inp=inp.replace(":", "");
		m=Integer.parseInt(inp);
		ampm=ampm.toUpperCase();
		if(ampm.equals("AM") && h==12)
			h-=12;
		if(ampm.equals("PM") && h!=12)
			h+=12;
		exactEndTime=h*100+m;
		
		System.out.printf("%8s%20s%10s\n","Time","Start","End");
		System.out.printf("%8s%10s%10s%10s%10s\n","Data:","Start","Break","Break","End");
		System.out.printf("%8s%10s%10s%10s%10s\n","Inputs",a1,a2,a3,a4);
		System.out.printf("%8s%10d%10d%10d%10d\n","Exact",exactStartTime,exactBreakStartTime,exactBreakEndTime,exactEndTime);
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
