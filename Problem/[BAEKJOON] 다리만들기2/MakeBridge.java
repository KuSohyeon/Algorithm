//[백준] 다리만들기2(Kruskal)
/*
7 8
0 0 0 1 1 0 0 0
0 0 0 1 1 0 0 0
1 1 0 0 0 0 1 1
1 1 0 0 0 0 1 1
1 1 0 0 0 0 0 0
0 0 0 0 0 0 0 0
1 1 1 1 1 1 1 1
# 10
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MakeBridge {
	static int N,M;
	static int [][] map;
	static int [][] v;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static List<Bridge> list;
	static int [] p;
	static class Pair{
		int i;
		int j;
		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
	static class Bridge extends Pair implements Comparable<Bridge>{
		int dist;

		public Bridge(int i, int j, int dist) {
			super(i, j);
			this.dist = dist;
		}

		@Override
		public int compareTo(Bridge o) {
			return Integer.compare(this.dist, o.dist);
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new int[N][M];
		list = new ArrayList<>();
		
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//먼저 섬부터 만들기
		int cnt=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==0 || v[i][j] != 0) continue;
				makeIsland(new Pair(i,j),++cnt); 
			}
		}
		
		makeSet(cnt);
			
		// 연결할 수 있는 다리 연결 해보기
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 0) continue;
				makeBridge(i,j,map[i][j]); 
			}
		}
		
		Collections.sort(list);
		
		int len=0,distance=0; //MST 조건 : 간선의 개수 = 정점의 개수 -1, 경로의 합을 구해 줄 distance 변수 생성
		
		for(int i=0;i<list.size();i++) {
			if(len==cnt-1) {//MST 생성되면 return
				System.out.println(distance);
				return;
			}
			Bridge bridge = list.get(i); //리스트 받아오기
			
			if(bridge.dist==1) continue; //다리의 길이는 무조건 2 이상이니까 1이면 거르기
			//union 성공 유무에 따라 연결 유무 결정 됨 -> 싸이클이 발생하면 연결  X
			if(union(bridge.i,bridge.j)) {
				distance += bridge.dist;
				len++; //다리 개수 하나 늘려주기
			}
		}
		System.out.println(-1); //만약에 연결 못하면 -1 출력
		
	}
	// Kruskal 이용 
	private static void makeSet(int cnt) {
		p = new int[cnt+1];
		for(int i=0;i<=cnt;i++) {
			p[i]=i;
		}
	}
	private static int findSet(int a) {
		if(p[a]==a) {
			return a;
		}
		return findSet(p[a]);
	}
	private static boolean union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		
		if(a == b) return false;
		p[b] = a;
		return true;
	}
	// 연결할 수 있는 다리 다 만들어보기
	private static void makeBridge(int i, int j, int start) {
		
		for(int d=0;d<4;d++) {
			int cnt=0; //다리의 길이를 파악할 cnt
			int ni = i, nj=j;
			while(true) { //될때까지 쭉쭉 늘려가보기
				ni += dy[d]; 
				nj += dx[d];
				
				if(!check(ni,nj)) break;
				if(map[ni][nj] == start) break;
				
				cnt++; //다리 길이 늘려주기
				if(map[ni][nj]!=0) { //자기자신과 바다가 아닌곳에 도착했을 경우
					list.add(new Bridge(start,map[ni][nj],cnt-1)); //cnt-1를 해주는 이유 : 섬도 길이에 포함이 됨
					break;
				}
			}
		}

	}
	//섬 만들기
	private static void makeIsland(Pair pair,int cnt) { //bfs 를 이용하여 섬에 번호 달아주기
		
		Queue<Pair> q = new LinkedList<>();
		
		q.offer(pair);
		v[pair.i][pair.j]=cnt;
		map[pair.i][pair.j] = cnt;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();

			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(!check(ni,nj) || v[ni][nj] != 0 || map[ni][nj]==0) continue;
				
				map[ni][nj]=cnt;
				v[ni][nj]=cnt;
				q.offer(new Pair(ni,nj));
			}
		}
		
	}
	static boolean check(int i, int j) {
		if(i<0 || i>=N || j<0 || j>=M) return false;
		return true;
	}
}
