//[백준] 한 줄로 서기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OneLine {
	static int n;
	static int [] people, answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		people = new int[n];
		answer = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<n;i++) {
			people[i]=Integer.parseInt(st.nextToken());
			answer[i] = n-1-i; // answer 내림차순으로  setting
		}
		
		for(int i=1;i<n;i++) {
			for(int j=i;j>0;j--) { // 내 앞것들이랑 비교
				if(check(answer[j],j)) break; // 자리 바꿔야하는지 안하는지 확인 true면 그만하기
				int tmp = answer[j-1];
				answer[j-1]=answer[j];
				answer[j] = tmp;
			}
		}
		
		// 출력
		for(int a : answer) {
			System.out.print(a+1+" ");
		}
	}
	// 내 앞에 나보다 큰 사람 몇 명인지 세리는 method
	private static boolean check(int num, int idx) {
		int cnt=0;
		for(int i=0;i<idx;i++) {
			if(answer[i]>num) cnt++; 
		}
		if(people[num]==cnt) return true; // 내 앞의 키 큰사람 수 = 입력이면 return true
		else return false; // 아니면 false
	}
	
}
