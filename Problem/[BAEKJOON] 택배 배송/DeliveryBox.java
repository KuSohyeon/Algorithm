//[백준] 택배 배달

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DeliveryBox {
	static class Node implements Comparable<Node>{
		int Node;
		int totalDistance;
		public Node(int node, int totalDistance) {
			super();
			Node = node;
			this.totalDistance = totalDistance;
		}
		@Override
		public String toString() {
			return "Node [Node=" + Node + ", totalDistance=" + totalDistance + "]";
		}
		@Override
		public int compareTo(Node o) {
			return this.totalDistance-o.totalDistance;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Node> [] arr = new ArrayList [N+1];
		
		for(int i=1;i<=N;i++) {
			arr[i]=new ArrayList<Node>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			// 무방향 그래프
			arr[from].add(new Node(to,weight));
			arr[to].add(new Node(from,weight));
		}
		
		
		PriorityQueue<Node> pq = new PriorityQueue<>(); // PQ 사용
		int [] distance = new int[N+1];
		boolean [] v = new boolean[N+1];
		int start=1,end=N; // 출발 : 1 도착 : N
		Arrays.fill(distance, Integer.MAX_VALUE); // 일단 Max 값으로 채우고 시작
		distance[start]=0; // 시작 정점에는 가중치 0 주기
		pq.offer(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll(); // 현재 정점
			
			v[cur.Node]=true; // 방문 체크
			if(cur.Node==end) break; // 도착하면 break;
			
			for(int i=0;i<arr[cur.Node].size();i++) { // 현재 정점에서 갈 수 있는 정점 다 가보기
				Node now = arr[cur.Node].get(i);
				if(v[now.Node]) continue; // 방문했던 곳이라면 넘겨주기
				if(distance[now.Node]>now.totalDistance+distance[cur.Node]) { // 최소값 갱신
					distance[now.Node]=now.totalDistance+distance[cur.Node];
					pq.offer(new Node(now.Node,distance[now.Node])); // PQ에 넣어주기
				}
			}
		}
		// 출력
		System.out.println(distance[end]);
	}
}

