//[백준] 해킹

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Hacking {
	static int n,d,c;
	static int time = 0, cmp = 0;
	static List<Data> [] list;
	static class Data implements Comparable<Data>{
		int node;
		int cost;
		public Data(int node, int cost) {
			super();
			this.node = node;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Data [node=" + node + ", cost=" + cost + "]";
		}
		@Override
		public int compareTo(Data o) { // 오름차순으로 정렬
			return Integer.compare(this.cost, o.cost);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken()); // 컴퓨터의 개수
			d = Integer.parseInt(st.nextToken()); // 의존성 개수
			c = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터 번호
			
			list = new ArrayList [n+1];
			cmp = 0; // 컴퓨터 수
			time = 0; // 감염 시간
			
			for(int i=1;i<=n;i++) {
				list[i] = new ArrayList<Data>();
			}
			
			for(int i=0;i<d;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				list[b].add(new Data(a,s)); // a는 b에 의존
			}
			
			dikstra();
			
			System.out.println(cmp+" "+time);
			
		}
	}
	private static void dikstra() {
		PriorityQueue<Data> pq = new PriorityQueue<Data>();
		boolean [] v = new boolean[n+1];
		int [] distance = new int[n+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[c]=0; // 해킹 당한 컴퓨터부터 시작
		pq.offer(new Data(c,distance[c]));
		
		while(!pq.isEmpty()) {
			Data cur = pq.poll();
			
			if(v[cur.node]) continue;
			v[cur.node]=true;
			
			time = Math.max(time, cur.cost); // 최대 시간으로 업데이트
			cmp++;// 컴퓨터 수 증가
			
			for(int i=0;i<list[cur.node].size();i++) {
				Data now = list[cur.node].get(i);
				if(!v[now.node] && distance[now.node]>cur.cost+now.cost) {// 방문한 적이 없고 원래 시간이 더 오래걸린다면
					distance[now.node] = cur.cost+now.cost; // 적게 걸리는걸로 갱신
					pq.offer(new Data(now.node, distance[now.node])); // 우선순위 큐에 넣어주기
				}
			}
			
			
		}
		
	}
}
