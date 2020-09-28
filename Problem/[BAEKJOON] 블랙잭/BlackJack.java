//[백준] 블랙잭

import java.util.Scanner;

public class BlackJack {
	static int N, M , result;
	static int [] input,arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 카드 수
		M = sc.nextInt(); // 원하는 카드의 합
		
		input = new int[N];
		arr = new int[N];
		
		for(int i=0;i<N;i++) {
			input[i]=sc.nextInt();
		}
		
		result = 0;
		
		comb(0,0);
		
		System.out.println(result);
		
	}
	private static void comb(int cnt, int start) {
		if(cnt==3) {
			int tmp = 0;

			for(int s : arr) {
				tmp += s;
			}
			if(tmp>M) return; // 카드 3장의 합은 M을 넘으면 안됨
			
			if(Math.abs(M-tmp)<Math.abs(M-result)) { // M이랑 가장 가까운 해 찾기
				result=tmp;
			}
			if(result==M) { // 만약 M이랑 똑같으면 더 이상 볼 필요도 없음
				System.out.println(result); //출력 후
				System.exit(0); // 시스템 종료
			}
			return;
		}
		for(int i=start;i<N;i++) {
			arr[cnt]=input[i];
			comb(cnt+1,i+1);
		}
		
	}
	
}
