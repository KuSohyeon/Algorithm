//[백준] 다리만들기2-Kruskal

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MakeBridge2_Kruskal {
	static int N,M,result;
	static int [][] map;
	static boolean [][] v;
	static List<Data> list;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int [] p;
	static class Data implements Comparable<Data>{
		int from;
		int to;
		int weight;
		public Data(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Data [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		@Override
		public int compareTo(Data o) {
			return this.weight-o.weight;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new boolean[N][M];
		list = new ArrayList<Data>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int num=1;
		
		// 섬 번호 붙이기
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==1 && !v[i][j]) {
					bfs(i,j,num++);
				}
			}
		}
		
		makeSet(num);
		makeBridge(); // 다리 만들기
		Collections.sort(list); // 크루스칼 전에 꼭 sort!!
		
		int cnt=0; // 간선의 개수 세릴거
		result = 0;
		
		// 다리 연결하기
		for(int i=0;i<list.size();i++) {
			if(cnt==num-2) break;
			Data now = list.get(i);
			if(union(now.from,now.to)) {
				result += now.weight;
				cnt++;
			}
		}
		
		if(cnt!=num-2) result=-1; // 제대로 다리 연결 못할 경우 -1
		// 출력
		System.out.println(result);
		
	}
	// Kruskal
	private static void makeSet(int num) {
		p = new int[num];
		for(int i=0;i<p.length;i++) {
			p[i]=i;
		}
	}
	private static int findSet(int a) {
		if(a==p[a]) return a;
		return findSet(p[a]);
	}
	private static boolean union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if(a==b) return false;
		p[b]=a;
		return true;
	}
	private static void makeBridge() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]!=0) {
					for(int d=0;d<4;d++) {
						int ni = i + dy[d];
						int nj = j + dx[d];
						if(ni<0 || ni>=N || nj<0 || nj>=M || map[ni][nj]!=0 ) continue;// 바다가 아닌 경우(자기자신) 넘겨주기
						boolean flag = true;
						int cnt=1;
						while(flag) {
							ni += dy[d];
							nj += dx[d];
							if(ni<0 || ni>=N || nj<0 || nj>=M) break; // 교차 될 경우 break;
							cnt++;
							if(map[ni][nj]!=0) {
								if(cnt-1==1) break; // 다리길이는 최소 2 이상
								list.add(new Data(map[i][j],map[ni][nj],cnt-1)); // cnt-1을 해주는 이유는 섬에 도착했을 경우도 다리길이로 치기 때문
								break;
							}
						}
					}
				}
			}
		}
		
	}
	private static void bfs(int i, int j,int num) { // 섬 번호 붙이기
		Queue<Data> q = new LinkedList<Data>();
		
		q.offer(new Data(i,j,0));
		map[i][j]=num;
		v[i][j]=true;
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int ni = cur.from + dy[d];
				int nj = cur.to + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M || map[ni][nj]==0 || v[ni][nj]) continue;
				map[ni][nj]=num;
				v[ni][nj]=true;
				q.offer(new Data(ni,nj,0));
			}
			
		}
		
		
		
	}
}
