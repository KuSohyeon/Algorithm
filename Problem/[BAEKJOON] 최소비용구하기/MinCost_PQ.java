import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class MinCost_PQ {
	static int N,M,result,start,end;
	static ArrayList<Data> [] arr;
	static class Data implements Comparable<Data>{
		int to;
		int weight;
		public Data(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Data [to=" + to + ", weight=" + weight + "]";
		}
		@Override
		public int compareTo(Data o) {
			return this.weight-o.weight;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		arr = new ArrayList [N+1];
		
		for(int i=0;i<=N;i++) {
			arr[i]=new ArrayList<Data>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			arr[from].add(new Data(to,weight));
		}
		
		st = new StringTokenizer(br.readLine().trim());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		Dijkstra();
		
		System.out.println(result);
		
	}
	private static void Dijkstra() {
		PriorityQueue<Data> pq = new PriorityQueue<Data>();
		int [] distance = new int[N+1];
		boolean [] v = new boolean[N+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start]=0;
		pq.add(new Data(start,0));
		
		while(!pq.isEmpty()) {
			
			Data cur = pq.poll();
			if(v[cur.to])continue;
			
			v[cur.to]=true;
			if(cur.to==end) {
				result = distance[end];
				return;
			}
			
			for(int i=0;i<arr[cur.to].size();i++) {
				Data now = arr[cur.to].get(i);
				if(!v[now.to] && distance[now.to]>distance[cur.to]+now.weight) {
					distance[now.to]=distance[cur.to]+now.weight;
					pq.offer(new Data(now.to,distance[now.to]));
				}
			}
			
		}
		
	}
}
