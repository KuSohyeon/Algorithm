//[백준] 수열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sequence {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int [] input = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			input[i]=Integer.parseInt(st.nextToken());
		}
		


		int cnt=1;
		int increase = 1;
		int decrease = 1;
		
//		// 한꺼번에 체크		
//		int Icnt = 1;
//		int Dcnt = 1;		

//		for(int i=0;i<N-1;i++) {
//			int now = input[i];
//			int next = input[i+1];
//			
//			if(now == next) {
//				Icnt++;
//				Dcnt++;
//			}
//			else if(now<=next) {
//				Icnt++;
//				decrease = Math.max(decrease, Dcnt);
//				Dcnt=1;
//			}
//			else if(now>=next){
//				Dcnt++;
//				increase = Math.max(increase, Icnt);
//				Icnt=1;
//			}
//		}
//		increase = Math.max(increase, Icnt); // 반복문 끝날때도 increase랑 비교해주기
//		decrease = Math.max(decrease, Dcnt);
		
		// 커지는 거 체크
		for(int i=0;i<N-1;i++) {
			int now = input[i];
			int next = input[i+1];
			
			if(now<=next) { // 다음 수가 현재 보다 크거나 같다면 ++
				cnt++;
			}
			else { // 작아지면 현재까지의 cnt 저장
				increase = Math.max(increase, cnt); // 현 increase값보다 cnt가 크면 업데이트
				cnt=1; // cnt 초기화
			}
		}
		increase = Math.max(increase, cnt); // 반복문 끝날때도 increase랑 비교해주기
		
		cnt=1;
		
		// 작아지는거 체크
		for(int i=0;i<N-1;i++) {
			int now = input[i];
			int next = input[i+1];
			
			if(now>=next) { // 다음 수가 현재 보다 작거나 같다면 ++
				cnt++;
			}
			else {
				decrease = Math.max(decrease, cnt); // 현 decrease값보다 cnt가 크면 업데이트
				cnt=1;// cnt 초기화
			}
		}
		decrease = Math.max(decrease, cnt);// 반복문 끝날때도 decrease랑 비교해주기
		
		int result = Math.max(increase, decrease); //가장 큰 값 넣어주기
		System.out.println(result);
	}
}
