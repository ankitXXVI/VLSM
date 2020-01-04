import java.util.Scanner;

public class VLSM {
	
	public static void main(String[] args) {
		
		String ip;
		String netMask;
		double hosts;
		Scanner in = new Scanner(System.in);
		int operator = 2;
		int counter = 1;
		int bitsLent = 0;
		int newMask;
		int magicNum = 128;		
		
		System.out.println("Introduce your IPV4 Network address:");
		ip = in.nextLine();
		String[] stringIPAddr = ip.split("\\.");
		int[] ipAddr = new int[4];
		for(int i = 0; i < 4; i++) {
		    ipAddr[i] = Integer.parseInt(stringIPAddr[i]);
		}
		System.out.println("");
		System.out.println("Introduce your Network Mask:");
		netMask = in.nextLine();
		System.out.println();
		System.out.println("How many hosts do you want?:");
		hosts = in.nextDouble();
		System.out.println("");
		bitsLent = bitsLent(operator, counter, hosts, bitsLent);
		newMask = 32 - bitsLent;
		magicNum = magicNum(netMask, bitsLent, magicNum, newMask);
	
		if(hosts < 256) {
			shortIpRange(ipAddr, magicNum, ipAddr, newMask);
		}
		else if(hosts > 256) {
			largeIpRange(ipAddr, magicNum, newMask);
		}
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
	private static int magicNum(String netMask, int bitsLent, int magicNum, int newMask) {
		if(newMask == 1) {	
			magicNum = 128;
		}
		else if(newMask == 2) {	
			magicNum = 64;
		}
		else if(newMask == 3) {	
			magicNum = 32;
		}
		else if(newMask == 4) {	
			magicNum = 16;
		}
		else if(newMask == 5) {	
			magicNum = 8;
		}
		else if(newMask == 6) {	
			magicNum = 4;
		}
		else if(newMask == 7) {	
			magicNum = 2;
		}
		else if(newMask == 8) {	
			magicNum = 1;
		}
		else if(newMask == 9) {	
			magicNum = 128;
		}
		else if(newMask == 10) {	
			magicNum = 64;
		}
		else if(newMask == 11) {	
			magicNum = 32;
		}
		else if(newMask == 12) {	
			magicNum = 16;
		}
		else if(newMask == 13) {	
			magicNum = 8;
		}
		else if(newMask == 14) {	
			magicNum = 4;
		}
		else if(newMask == 15) {	
			magicNum = 2;
		}
		else if(newMask == 16) {	
			magicNum = 1;
		}
		else if(newMask == 17) {	
			magicNum = 128;
		}
		else if(newMask == 18) {	
			magicNum = 64;
		}
		else if(newMask == 19) {	
			magicNum = 32;
		}
		else if(newMask == 20) {	
			magicNum = 16;
		}
		else if(newMask == 21) {	
			magicNum = 8;
		}
		else if(newMask == 22) {	
			magicNum = 4;
		}
		else if(newMask == 23) {	
			magicNum = 2;
		}
		else if(newMask == 24) {	
			magicNum = 1;
		}
		else if(newMask == 25) {	
			magicNum = 128;
		}
		else if(newMask == 26) {	
			magicNum = 64;
		}
		else if(newMask == 27) {	
			magicNum = 32;
		}
		else if(newMask == 28) {	
			magicNum = 16;
		}
		else if(newMask == 29) {	
			magicNum = 8;
		}
		else if(newMask == 30) {	
			magicNum = 4;
		}
		else if(newMask == 31) {	
			magicNum = 2;
		}
		else if(newMask == 32) {	
			magicNum = 1;
		}
		return magicNum;
	}
	private static void shortIpRange(int[] number, int magicNum, int[] ipAddr, int newMask) {
		increment(ipAddr);
		System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask + " to ");
		if(number[3] == 0) {
			number[3] = (magicNum - 1);
		}
		else {
			number[3] = number[3] + (magicNum - 1);
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
		if(ipAddr[1] != 0 && ipAddr[2] == 0) {
			System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask + " to ");
			increment(ipAddr);
			ipAddr[2] += (magicNum - 1);
			ipAddr[3] = 255;
			System.out.println(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask);
		}
		else if(ipAddr[1] == 0 && ipAddr[2] != 0) {
			System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask + " to ");
			increment(ipAddr);
			ipAddr[2] += (magicNum - 1);
			ipAddr[3] = 255;
			System.out.println(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask);
		}
		else if(ipAddr[1] != 0 && ipAddr[2] != 0) {
			System.out.print(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask + " to ");
			increment(ipAddr);
			ipAddr[2] += (magicNum - 1);
			ipAddr[3] = 255;
			System.out.println(ipAddr[0] + "." + ipAddr[1] + "." + ipAddr[2] + "." + ipAddr[3] + "/" + newMask);
		}
	}
}
