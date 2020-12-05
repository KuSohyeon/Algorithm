import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FindTreesParent {
	static int N;
	static List<Integer> [] nodes;
	static boolean [] v;
	static int [] child;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		nodes = new ArrayList[N+1] ;
		v = new boolean[N+1];
		child = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			nodes[i] = new ArrayList<Integer>();
		}
		
		for(int i=1;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			nodes[one].add(two);
			nodes[two].add(one);
		}
		

		v[1]=true;
		for(int i=1;i<=N;i++) {
			dfs(i); // 1부터 시작하면 무조건 자식노드들 정해짐
		}
		
		for(int i=2;i<=N;i++) {
			System.out.println(child[i]);
		}
		
	}
	private static void dfs(int num) {
		for(int i=0;i<nodes[num].size();i++) {
			int now = nodes[num].get(i);
			if(v[now]) continue;
			v[now]=true;
			child[now]=num; // 1부터 시작하기 때문에 해당 노드의 자식이 됨
			dfs(now);
		}
	}
}
