//[백준] 특정한 최단 경로
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SpecificShortDistance {
	static int N,E;
	static int INF = Integer.MAX_VALUE;
	static int [] arr;
	static ArrayList<Node> [] list;
	static int [] distance;
	static class Node implements Comparable<Node>{
		int vertex;
		int weight;
		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", weight=" + weight + "]";
		}
		@Override
		public int compareTo(Node o) {
			return this.weight-o.weight;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		arr = new int[4];
		list = new ArrayList [N+1];
		distance = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<Node>();
		}

		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			// 무방향 그래프
			list[from].add(new Node(to,weight));
			list[to].add(new Node(from,weight));
		}
		
		arr[0]=1;
		st = new StringTokenizer(br.readLine().trim());
		for(int i=1;i<3;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		arr[3]=N;
		
		// 꼭 첫번째 두번째 순서대로 가라는 법이 없음 첫번째, 마지막만 지켜주면 됨
		int [] brr = new int[4];
		brr[0]=1;
		brr[1]=arr[2];
		brr[2]=arr[1];
		brr[3]=arr[3];
		
		int result1 = 0,result2=0,result=0;
		for(int i=1;i<4;i++) { // 출발 ~ 도착까지 최단경로를 구해서 다 더하면 답
			int tmp = dijkstra(arr[i-1],arr[i]); // 첫번째 케이스
			int tmp2 = dijkstra(brr[i-1],brr[i]); // 두번째 케이스
			result1 += tmp;
			result2 += tmp2;
		}
		
		result = Math.min(result1, result2); // 둘 중에 min 값으로 결정
		
		if(distance[N]==INF) { // distance[N]이 무한대 = 도착하지 못하는 경우 -1 출력
			result = -1;
		}
		
		System.out.println(result);
	}
	private static int dijkstra(int start,int end) {

		PriorityQueue<Node> pq = new PriorityQueue<Node>(); // PQ 이용
		
		Arrays.fill(distance, INF); // 일단 INF 값으로 채워넣기
		distance[start]=0; // start는 무조건 0
		
		pq.offer(new Node(start,distance[start]));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.vertex==end) break; // 도착할 경우 break
			
			for(int i=0;i<list[cur.vertex].size();i++) { // 해당 정점에서 갈 수 있는 곳만 반복
				Node now = list[cur.vertex].get(i); // 현재 정점
				if(distance[now.vertex]>now.weight+distance[cur.vertex]) { // 최단경로 업데이트
					distance[now.vertex]=now.weight+distance[cur.vertex];
					pq.offer(new Node(now.vertex,distance[now.vertex]));
				}
			}
		}
		
		return distance[end]; // 경로값 리턴

	}
}
