//[SW Expert] 퍼펙트 셔플
/*
3
6
A B C D E F
4
JACK QUEEN KING ACE
5
ALAKIR ALEXSTRASZA DR-BOOM LORD-JARAXXUS AVIANA
 */

import java.util.Scanner;

public class PerfectShuffle {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			int N = sc.nextInt();
			
			String [] arr = new String[N]; //원본
			String [] brr = new String[N]; //결과
			
			for(int i=0;i<N;i++) {
				arr[i]=new String(sc.next());
			}
			
			int mid=0;
			//N이 짝수 홀수일때 중간 구분
			if(N%2==0) {
				mid = N/2;
			}
			else { mid = N/2+1;}
			
			int start =0;
			
			for(int i=0;i<N;i++) {
				if(i%2==0) {
					brr[i]=new String(arr[start++]); //짝수이면 처음부터 시작
				}
				else {
					brr[i]=new String(arr[mid++]);  //홀수이면 중간부터 시작
				}
			}
			
			
			
			System.out.print("#"+tc+ " ");
			for(int i=0;i<N;i++) {
				System.out.print(brr[i] + " ");
			}
			System.out.println();
		}
		sc.close();
	}
}
