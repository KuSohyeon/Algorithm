//[백준] 연결 요소의 개수

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ConnectCnt {
	static int N,M;
	static List<Integer> [] list;
	static boolean [] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList [N+1];
		v = new boolean[N+1];
		
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		int cnt = 0;
		for(int i=1;i<=N;i++) {
			if(!v[i]) {
				dfs(i);
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	private static void dfs(int n) {
		v[n]=true;
		for(int i=0;i<list[n].size();i++) {
			int now = list[n].get(i);
			if(v[now]) continue;
			dfs(now);
		}
	}
}
