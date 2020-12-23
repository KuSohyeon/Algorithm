//[백준] 통나무 건너뛰기
// 가장 큰 수를 중간에 배치 가장 작은 수를 끝에 배치 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JumpLog {
	static int N,result;
	static int [] before, after;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			result = 0;
			
			N = Integer.parseInt(br.readLine());
			before = new int[N];
			after = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				before[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(before); // 오름차순으로 정렬
			
			// 가운데로 갈 수록 커지게 배치
			int idx=0, low=0, high=N-1;
			while(low<=high) { // low와 high가 자리가 바뀌면 종료
				after[low++] = before[idx++]; 
				if(low>high) break; // 홀수일 경우 high=low  자리 바뀔때까지만 해주기
				after[high--] = before[idx++];
			}
			
			// 양 옆에 높이의 차 구하기
			for(int i=0;i<N;i++) {
				int before = Math.abs(after[i]-after[i-1==-1?N-1:i-1]);
				int next = Math.abs(after[i]-after[i+1==N?0:i+1]);
				if(before>result) {
					result = before;
				}
				if(next>result) {
					result = next;
				}
			}
			
			// 최댓값 출력
			System.out.println(result);
		}
		
	}
}
