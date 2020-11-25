//[백준] ATM

import java.util.Arrays;
import java.util.Scanner;

public class ATM {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int [] person = new int[N];
		
		for(int i=0;i<N;i++) {
			person[i]=sc.nextInt();
		}
		
		Arrays.sort(person);
		
		int result = 0;
		
		for(int i=0;i<N;i++) {
			result += person[i]*(N-i);
		}
		
		System.out.println(result);
	}
}
