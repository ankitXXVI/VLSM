import java.util.Scanner;
import java.util.regex.Pattern;

public class VLSM {
	
	public static void main(String[] args) {
		
		String ip = "";
		Pattern ipPattern = Pattern.compile("[0-9]{0,3}[.][0-9]{0,3}[.][0-9]{0,3}[.][0-9]{0,3}");
		String netMask = "";
		Pattern maskPattern = Pattern.compile("/[0-9]{0,2}");
		double hosts;
		Scanner in = new Scanner(System.in);
		int operator = 2;
		int counter = 1;
		int bitsLent = 0;
		int newMask;
		int magicNum = 1;		
		
		System.out.println("Introduce your IPV4 Network address:");
		if(in.hasNext(ipPattern)) {
			ip = in.nextLine();
		}
		else {
			System.out.println("");
			System.out.println("Invalid IP!");
			restart(args, in);
		}
		String[] stringIPAddr = ip.split("\\.");
		int[] ipAddr = new int[4];
		for(int i = 0; i < 4; i++) {
		    ipAddr[i] = Integer.parseInt(stringIPAddr[i]);
		}
		System.out.println("");
		System.out.println("Introduce your Network Mask prefix length:");
		if(in.hasNext(maskPattern)) {
			netMask = in.nextLine();
		}
		else {
			System.out.println("");
			System.out.println("Invalid Mask!");
			restart(args, in);
		}
		System.out.println();
		System.out.println("How many hosts do you want?:");
		hosts = in.nextDouble();
		System.out.println("");
		bitsLent = bitsLent(operator, counter, hosts, bitsLent);
		newMask = 32 - bitsLent;
		magicNum = magicNum(magicNum, newMask);
	
		if(hosts < 256) {
			shortIpRange(ipAddr, magicNum, ipAddr, newMask);
		}
		else if(hosts > 256) {
			largeIpRange(ipAddr, magicNum, newMask);
		}
		restart(args, in);
	}

	private static int bitsLent(int operator, int counter, double hosts, int bitsLent) {
		while((Math.pow(operator, counter) - 2) <= hosts || (Math.pow(operator, counter) - 2) < (hosts * 2)) {
			if((Math.pow(operator, counter) - 2) >= hosts) {
				bitsLent = counter;
			}
			counter++;
		}
		return bitsLent;
	}
	private static int magicNum(int magicNum, int newMask) {
		int y;
		int z = 128;
		for(int i = 1; i <= newMask; i++) {
			if(i == 9 || i == 17 || i == 25) {
				z = 128;
			}
			if(i <= 8) {
				magicNum = z;
				z /= 2;
			}
			else if(i > 8 && i <= 16) {
				magicNum = z;
				z /= 2;
			}
			else if(i > 16 && i <= 24) {
				magicNum = z;
				z /= 2;
			}
			else if(i > 24 && i < 32) {
				magicNum = z;
				z /= 2;
			}
		}
		return magicNum;
	}
	private static void shortIpRange(int[] number, int magicNum, int[] ipAddr, int newMask) {
		if(ipAddr[3] != 0) {
			increment(ipAddr);
		}
		System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask + " to ");
		if(ipAddr[3] == 0) {
			ipAddr[3] = (magicNum - 1);
		}
		else {
			ipAddr[3] = ipAddr[3] + (magicNum - 1);
			 if(ipAddr[3] >= 255) {
				 increment(ipAddr);
				 ipAddr[2] += 1;
				 ipAddr[3] = ipAddr[3] - 255;
			 }
		}
		System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask);
	}	
	private static void increment(int[] number) {
	    int carry = 1;
	    for(int i = 3; i >= 0; i--) {
	        number[i] = number[i] + carry;
	        if(number[i] == 256) {
	            carry = 1;
	            number[i] = 0;
	        } else {
	        	carry = 0;
	        }
	    }
	}
	private static void largeIpRange(int[] ipAddr, int magicNum, int newMask) {
		if(ipAddr[1] != 0 && ipAddr[2] == 0 && ipAddr[3] != 0) { //1-0-1
			increment(ipAddr);
			System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask + " to ");
			increment(ipAddr);
			ipAddr[2] += (magicNum - 1);
			ipAddr[3] = 255;
			System.out.println(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask);
		}
		else if(ipAddr[1] == 0 && ipAddr[2] != 0 && ipAddr[3] != 0) { //0-1-1
			increment(ipAddr);
			System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask + " to ");
			increment(ipAddr);
			ipAddr[2] += (magicNum - 1);
			ipAddr[3] = 255;
			System.out.println(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask);
		}
		else if(ipAddr[1] != 0 && ipAddr[2] != 0 && ipAddr[3] != 0) { //1-1-1
			increment(ipAddr);
			System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask + " to ");
			increment(ipAddr);
			ipAddr[2] += (magicNum - 1);
			ipAddr[3] = 255;
			System.out.println(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask);
		}
		else if(ipAddr[1] != 0 && ipAddr[2] != 0 && ipAddr[3] == 0) { //1-1-0
			System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask + " to ");
			increment(ipAddr);
			ipAddr[2] += (magicNum - 1);
			ipAddr[3] = 255;
			System.out.println(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask);
		}
		else if(ipAddr[1] == 0 && ipAddr[2] == 0 && ipAddr[3] != 0) { //0-0-1
			increment(ipAddr);
			System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask + " to ");
			increment(ipAddr);
			ipAddr[2] += (magicNum - 1);
			ipAddr[3] = 255;
			System.out.println(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask);
		}
		else if(ipAddr[1] == 0 && ipAddr[2] != 0 && ipAddr[3] == 0) { //0-1-0
			System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask + " to ");
			increment(ipAddr);
			ipAddr[2] += (magicNum - 1);
			ipAddr[3] = 255;
			System.out.println(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask);
		}
		else if(ipAddr[1] != 0 && ipAddr[2] == 0 && ipAddr[3] == 0) { //1-0-0
			System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask + " to ");
			increment(ipAddr);
			ipAddr[2] += (magicNum - 1);
			ipAddr[3] = 255;
			System.out.println(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask);
		}
		else if(ipAddr[1] == 0 && ipAddr[2] == 0 && ipAddr[3] == 0) { //0-0-0
			System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask + " to ");
			increment(ipAddr);
			ipAddr[1] += (magicNum - 1);
			ipAddr[2] = 255;
			ipAddr[3] = 255;
			System.out.println(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask);
		}
	}
	private static void restart(String[] args, Scanner in) {
		System.out.println("");
		System.out.println("Press <Enter> to try again");
		if(in.nextLine() != null) {
			in.nextLine();
			clearScreen();
			main(args);
		}	
	}
	private static void clearScreen() {
		for(int i = 0; i < 25; i++) {
			System.out.println("");
		}
		
	}
}
