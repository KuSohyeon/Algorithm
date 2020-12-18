//[백준] 텀 프로젝트 
/*
 * visit[]은 해당 노드를 방문한 적이 있는지 확인하는 배열. 방문한 곳을 재 방문했을 경우
 * 그 노드는 done이 되며 카운트 변수 cnt를 증가시킨다.(사이클이 형성되었으므로 팀이 완성됨.-> 팀원의 수를 카운트하기 위함)
 * 
 * 재귀 종료 후에도 done을 해주는 이유는 팀원외 됐든 안됐든 이미 확인이 끝났다는 의미로 표시
 * 
 * visit은 팀원을 꾸리기 시작하는 단계임을 표시하는 방법.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TermProject {
	static int n,result;
	static int [] students;
	static boolean [] done,visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			n = Integer.parseInt(br.readLine());
			result = 0;
			
			students = new int[n+1];
			done = new boolean[n+1];
			visit = new boolean[n+1];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=1;i<=n;i++) {
				students[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1;i<=n;i++) {
				if(!done[i]) dfs(i);
			}
			
			sb.append(n-result).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static void dfs(int v) {
		if(visit[v]) {
			done[v]=true;
			result++;
		}else visit[v]=true;
		
		if(!done[students[v]]) dfs(students[v]);
		visit[v]=false;
		done[v]=true;
	}
}
