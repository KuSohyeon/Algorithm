//[백준] 그대, 그머가 되어

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14496 {
	static int a,b,N,M,min = Integer.MAX_VALUE;
	static List<Integer> [] list;
	static class Node implements Comparable<Node>{
		int index;
		int cnt;
		public Node(int index, int cnt) {
			super();
			this.index = index;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Node [index=" + index + ", cnt=" + cnt + "]";
		}
		@Override
		public int compareTo(Node o) {
			return this.cnt-o.cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList [N+1];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			list[one].add(two);
			list[two].add(one);
		}
		
		
		// 다익스트라
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int [] distance = new int[N+1];
		boolean [] v = new boolean[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE); 
		distance[a]=0;
		pq.offer(new Node(a,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.index==b) { // 바꾸면 그만하기
				break;
			}
			
			v[cur.index]=true; // 최소의 경우니까 트루로 바꿔주기
			
			for(int i=0;i<list[cur.index].size();i++) {
				int tmp = list[cur.index].get(i);
				if(!v[tmp] && distance[tmp]>1+cur.cnt) { // 방문 X 최소거리로 갱신하면 바꿔주기
					distance[tmp]=1+cur.cnt;
					pq.offer(new Node(tmp, distance[tmp]));
				}
			}
		}
			
		System.out.println(distance[b]==Integer.MAX_VALUE?-1:distance[b]);
	}

}
