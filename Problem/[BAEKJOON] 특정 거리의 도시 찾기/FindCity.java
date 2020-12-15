//[백준] 특정거리의 도시찾기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FindCity {
	static int N,M,K,X;
	static List<Data> [] list;
	static class Data implements Comparable<Data>{
		int node;
		int dist;
		public Data(int node, int dist) {
			super();
			this.node = node;
			this.dist = dist;
		}
		@Override
		public String toString() {
			return "Data [node=" + node + ", dist=" + dist + "]";
		}
		@Override
		public int compareTo(Data o) {
			return this.dist-o.dist;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 도시 수
		M = Integer.parseInt(st.nextToken()); // 도로 수
		K = Integer.parseInt(st.nextToken()); // 거리 정보
		X = Integer.parseInt(st.nextToken()); // 출발 도시
		
		list = new ArrayList [N+1];
		
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<Data>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			list[from].add(new Data(to,1));
		}
		
		dikstra();
	}
	private static void dikstra() {
		int [] dist = new int[N+1];
		boolean [] v = new boolean[N+1];
		PriorityQueue<Data> pq = new PriorityQueue<Data>();
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[X]=0; /// 시작 노드의 거리를 0으로 초기화
		v[X]=true;
		pq.offer(new Data(X,dist[X]));
		
		while(!pq.isEmpty()) {
			// 내부 힙을 사용해서 최소 거리를 가진 정점 추출
			Data cur = pq.poll();
			
			v[cur.node]=true;
			
			for(int i=0;i<list[cur.node].size();i++) {
				Data now = list[cur.node].get(i);
				if(!v[now.node] && dist[now.node]>dist[cur.node]+now.dist) { // 최소 거리로 업데이트
					dist[now.node]=dist[cur.node]+now.dist;
					pq.offer(new Data(now.node, dist[now.node]));
				}
			}
		}
		
		boolean flag = false;
		for(int i=1;i<=N;i++) {
			if(dist[i]==K) {
				System.out.println(i);
				flag = true;
			}
		}
		
		// 만약 K 거리를 가진 도시가 없을 경우 -1 출력
		if(!flag) System.out.println(-1);
		
	}
}
