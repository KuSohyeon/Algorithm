//정올(1175번) - 주사위 던지기2
/*
 * 자연수 N과 M을 입력 받아서 주사위를 N번 던져서 나온 눈의 합이 M이 나올 수 있는 모든 경우를 출력하는 프로그램을 작성하시오.
3 10
 출력 :
 1 3 6
1 4 5
1 5 4
1 6 3
2 2 6
2 3 5
…
6 2 2
6 3 1
 */

import java.util.Scanner;

public class DiceTest2 {
	static int N,M;
	static int [] numbers;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //주사위 던지는 횟수
		M = sc.nextInt(); //눈의 합
		
		numbers = new int[N];
		
		perm(0,0);
		
		sc.close();
	}
	//중복 순열
	private static void perm(int cnt, int sum) { //cnt : 현재까지 던진 주사위 횟수, sum : 현재까지 던진 눈의 합
		if(cnt==N) {
			if(sum==M) {
				for(int num:numbers) {
					System.out.print(num+" ");
				}
				System.out.println();
			}
			return;
		}
		for(int i=1;i<=6;i++) {
			numbers[cnt]=i;
			perm(cnt+1,sum+i);
		}
		
	}
}
