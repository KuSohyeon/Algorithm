//[백준] 방 번호

import java.util.Scanner;

public class RoomNumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String number = sc.next();
		
		int [] numbers = new int[10];
		
		for(int i =0;i<number.length();i++) {
			int idx = number.charAt(i)-'0';
			numbers[idx]++;
		}
		
		int tmp = 0;
		tmp = numbers[6]+numbers[9];
		if(tmp%2==0) {
			numbers[6] = numbers[9] = tmp/2;
		}else {
			numbers[6]=numbers[9]=tmp/2+1;
		}
		
		int max =0;
		for(int i=0;i<10;i++) {
			max = Math.max(max, numbers[i]);
		}
		
		System.out.println(max);
	}
}
