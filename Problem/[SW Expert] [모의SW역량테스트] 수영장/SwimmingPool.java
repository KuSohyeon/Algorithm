//[SWEA] [모의SW역량테스트] 수영장

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SwimmingPool {
	static int result;
	static int [] fee, month;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			
			fee = new int[4];
			month = new int[12];
			
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0;i<4;i++) {
				fee[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0;i<12;i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			
			result = fee[3]; // tc마다 초기화(1년권 기준으로)
			
			dfs(0,0);
			
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void dfs(int m, int cost) {
		if(m>11) { // 일년 다 확인하면 최소비용으로 리턴
			result = Math.min(result, cost);
			return;
		}
		if(month[m]==0) { // 돈안내도 되는 달이면 넘겨주기
			dfs(m+1,cost);
		}
		// 1일권 사용하는 경우
		dfs(m+1,cost + fee[0]*month[m]);
		// 1달권 사용하는 경우
		dfs(m+1,cost + fee[1]);
		// 3달권 사용하는 경우
		dfs(m+3,cost + fee[2]);
		
	}

}
