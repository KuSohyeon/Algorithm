//[백준] 텀 프로젝트 (82% 시간초과)
/*
7
6
2 3 4 5 6 2
5
2 5 4 5 2
6
1 3 4 3 2 6
13
2 4 5 2 4 1 8 9 10 11 9 10 10
10
2 5 7 1 6 8 8 3 5 10
10
2 7 10 5 3 3 9 10 6 3
6
2 1 1 2 6 3
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n,result;
	static int [] students,group;
	static boolean [] cycle;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			n = Integer.parseInt(br.readLine());
			result = 0;
			
			students = new int[n+1];
			group = new int[n+1];
			cycle = new boolean[n+1];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=1;i<=n;i++) {
				students[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1;i<=n;i++) {
				if(group[i]==0 || !cycle[i]) dfs(i,students[i]);
			}
			
			sb.append(n-result).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static boolean dfs(int first, int next) {
		if(group[next]!=0) {
			cycle[next]=true;
			return false;
		}
		group[next] = first;
		if(first == next) {
			result++;
			return true;
		}
		if(dfs(first, students[next])) {
			result++;
			return true;
		}else {
			group[next]=0;
			cycle[next]=true;
			return false;
		}
		
	}
}
