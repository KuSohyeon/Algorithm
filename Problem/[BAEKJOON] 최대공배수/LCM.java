//[백준] 최대공배수

import java.util.Scanner;

public class LCM {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T  = sc.nextInt();
		for(int tc=1;tc<=T;tc++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			int gcd = gcd(A,B);// 최대 공약수
			int lcm = A/gcd * B/gcd * gcd; // 최대 공배수 
			
			System.out.println(lcm);
		}
	}

	private static int gcd(int a, int b) {
		if(b==0) return a;
		return gcd(b,a%b);
	}
}
		


