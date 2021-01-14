//[백준] 아우으 우아으이야!!

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15922 {
	static int N;
	static long result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		// 입력이 x 가 증가하는 순으로 주어짐
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(x>end) { // 새로 들어온 x 좌표가 그전 y 좌표보다 클 경우에는 
				result += end-start; // 길이 더해주고 새로운 좌표로 업데이트
				start = x;
				end = y;
			}
			else if(y > end) { // x가 end보다는 작지만 y가 end보다 크다면
				end = y; // 끝 길이 늘려주기
			}else if(y <= end) continue; // 만약 y가 end보다 작을 경우는 겹치는 곳이기 때문에 그냥 넘겨주기
			
		}
		
		// 마지막으로 최종 길이 더해주기
		result += (end - start);
		
		System.out.println(result);
	}
}
