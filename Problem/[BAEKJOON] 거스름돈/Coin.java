//백준(5585번) - 거스름돈(Greedy)
/*
 * 380
 */

import java.util.Scanner;

public class Coin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt(); //산 물건의 가격
		int R = 1000-M; //거스름돈 계산
		
		int cnt=0;
		while(R!=0) {
			while(R-500>=0) {//500엔짜리 줄 수 있으면 최대한 주기
				R-=500; //남은 거스름돈
				cnt++;//거스름돈 주고 나서 카운트 ++
			}
			while(R-100>=0) {//100엔짜리 줄 수 있으면 최대한 주기
				R -=100;
				cnt++;
			}
			while(R-50>=0) {//50엔짜리 줄 수 있으면 최대한 주기
				R-=50;
				cnt++;
			}
			while(R-10>=0) {//10엔짜리 줄 수 있으면 최대한 주기
				R-=10;
				cnt++;
				
			}
			while(R-5>=0) {//5엔짜리 줄 수 있으면 최대한 주기
				R-=5;
				cnt++;
			}
			for(int i=0;i<R;i++) {//1엔짜리 줄 수 있으면 최대한 주기
				R -= 1;
				cnt++;
			}

		}
		
		System.out.println(cnt);
		sc.close();
	}
}
