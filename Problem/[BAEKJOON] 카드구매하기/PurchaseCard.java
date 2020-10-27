//[백준] 카드구매하기

import java.util.Scanner;

public class PurchaseCard {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int [] P = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			P[i]=sc.nextInt();
		}
		
		int [] money = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			int max = P[i];
			for(int j=1;j<i;j++) {
				max = Math.max(max, money[i-j] + P[j]); // 최대값구하기
			}
			money[i]=max;// 최대값으로 넣어주기
		}
		System.out.println(money[N]);
	}
}
