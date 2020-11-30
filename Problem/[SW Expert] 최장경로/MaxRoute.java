//[SW Expert] 2814. 최장경로

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MaxRoute {
	static int N,M,result;
	static List<Integer> list [];
	static boolean [] v;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine().trim());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			list = new List[N+1];
			
			for(int i=1;i<=N;i++) {
				list[i]=new ArrayList<Integer>();
			}
			
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine().trim());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				// 가중치가 없는 무방향 그래프
				list[from].add(to);
				list[to].add(from);
			}
			
			result = 1; //tc마다 초기화
			
			for(int i=1;i<=N;i++) {
				v = new boolean[N+1];
				v[i]=true; // 방문체크
				dfs(i,1); // 갈 수 있는 곳 다 가보기
				v[i]=false; // 백트래킹
			}
			
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void dfs(int num, int cnt) {
		result = Math.max(result, cnt); // 최대값 갱신
		for(int i=0;i<list[num].size();i++) { // 갈 수 있는 곳만 방문해보기
			int now = list[num].get(i);
			if(v[now]) continue;
			v[now]=true; // 방문체크
			dfs(now,cnt+1);
			v[now]=false; // 백트래킹
		}
	}
}
