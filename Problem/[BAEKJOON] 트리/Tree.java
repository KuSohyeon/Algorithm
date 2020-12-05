/*
 * 
7
3 6 6 -1 0 6 3
4
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tree {
	static int N, tree;
	static boolean [] node;
	static List<Integer> [] nodes;
	public static void main(String[] args) throws Exception {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		nodes = new ArrayList [N]; // 노드의 관계를 리스트형 배열로 관리
		node = new boolean[N]; // 삭제할 노드를 boolean형 배열로 관리
		
		for(int i=0;i<N;i++) {
			nodes[i] = new ArrayList<Integer>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int parents = Integer.parseInt(st.nextToken());
			if(parents==-1) continue; // -1일 경우에는 부모 노드
			nodes[parents].add(i);
		}
		
		tree = Integer.parseInt(br.readLine()); // 삭제할 노드
		
		node[tree]=true; // 먼저 지울 노드를 표시하고
		
		for(int i=0;i<nodes[tree].size();i++) { // 지울노드의 자식들을 모두 선택
			int now = nodes[tree].get(i);
			node[now]=true;
			dfs(now); // 재귀를 통해서 자식노드의 자식노드이런 손녀 손자 노드들도 모두 삭제
		}
		
		// 만약 지울 노드가 리프노드일 경우
		if(nodes[tree].size()==0) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<nodes[i].size();j++) {
					if(nodes[i].get(j)==tree) {
						nodes[i].remove(j); // 부모노드에 가서 해당 노드 지워주기 (자식노드가 지울 노드밖에 없다면 이 노드도 리프노드가 되기 때문에 표시 해줘야함)
					}
				}
			}
		}
		
		
		int result = 0;
		
		// 남은 리프노드 세리기
		for(int i=0;i<N;i++) { 
			if(nodes[i].size()==0 && !node[i]) result++;// 리프노드이면서 삭제된 노드가 아니라면 남아있는 리프노드 증가
		}
		
		System.out.println(result);
		
	}
	// 자식노드의 자식노드를 지워줄 dfs method
	private static void dfs(int now) {
		for(int i=0;i<nodes[now].size();i++){
			int child = nodes[now].get(i);
			node[child] = true;
			dfs(child);
		}
	}
}
