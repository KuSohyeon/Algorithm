//[Programmers] 가장 먼 노드


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FurthestNode {
	static List<Node> [] list;
    static boolean [] v;
	static int [] distance;
	static class Node{
		int vertex;
		int cost;
		public Node(int vertex, int cost) {
			super();
			this.vertex = vertex;
			this.cost = cost;
		}
	}
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        list = new ArrayList [n+1];
        
        for(int i=1;i<=n;i++) {
        	list[i] = new ArrayList<Node>();
        }
        
        for(int i=0;i<edge.length;i++) {
        	int from = edge[i][0];
        	int to = edge[i][1];
        	
        	// 양방향 그래프
        	list[from].add(new Node(to,1));
        	list[to].add(new Node(from,1));
		}
        
        // Dijkstra이용
        v = new boolean[n+1];
		distance = new int[n+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		distance[1] = 0;
		
		int min=0, current=0;
		for(int c=1;c<=n;c++) {
			min = Integer.MAX_VALUE;
			
			for(int i=1;i<=n;i++) {
				if(!v[i] && min > distance[i]) {
					min = distance[i];
					current = i;
				}
			}
			
			v[current]=true;
			
			for(int i=0;i<list[current].size();i++) {
				Node now = list[current].get(i);
				if(!v[now.vertex] && distance[now.vertex]>distance[current]+now.cost) {
					distance[now.vertex]=distance[current]+now.cost;
				}
			}
		}
		
		// 가장 멀리 있는 노드의 수 찾기
		Arrays.sort(distance);
		
		int max= 0;
		for(int i=0;i<n;i++) {
			if(max<distance[i]) { // 새로운 max값으로 업데이트 
				max = distance[i];
				answer=1;
				continue;
			}
			if(max == distance[i]) { // max값이랑 같다면 answer++
				answer++;
			}
		}
        
        return answer;
    }
}
