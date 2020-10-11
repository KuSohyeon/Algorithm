//[백준] 최소비용 구하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class MinCost {
	static int N,M,result,start,end;
	static ArrayList<Data> [] arr;
	static class Data{
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
		
		int [] distance = new int[N+1];
		boolean [] v = new boolean[N+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start]=0;
		
		int min=0, current =0;;
		while(true) {
			min=Integer.MAX_VALUE;
			
			for(int i=1;i<=N;i++) {
				if(!v[i] && distance[i]<min) {
					min = distance[i];
					current=i;
				}
			}
			
			v[current]=true;
			if(current==end) {
				result = distance[end];
				return;
			}
			
			for(int i=0;i<arr[current].size();i++) {
				Data now = arr[current].get(i);
				if(!v[now.to] && distance[now.to]>distance[current]+now.weight) {
					distance[now.to]=distance[current]+now.weight;
				}
			}
			
		}
		
	}
}
