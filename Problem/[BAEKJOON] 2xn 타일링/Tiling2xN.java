//[백준] 2xn 타일링

import java.util.Scanner;

public class Tiling2xN {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int [] rect = new int[N+1];
		
		
		rect[0]=1;
		rect[1]=1;
		
		
		for(int i=2;i<=N;i++) {
			rect[i] = rect[i-1] + rect[i-2];
			rect[i]%=10007;
		}
		
		System.out.println(rect[N]);
	}
}
