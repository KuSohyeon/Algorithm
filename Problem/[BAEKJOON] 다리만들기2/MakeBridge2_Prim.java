//[백준] 다리만들기2-Prim
/*
반례 : 
10 10
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 1
0 0 0 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1 1 1
6
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MakeBridge2_Prim {
	static int N,M,result;
	static int [][] map;
	static boolean [][] v;
	static List<Data> list;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
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
		
		makeBridge(); // 다리 만들기
		
		int cnt=0; // 간선의 개수 세릴거
		result = 0;
		
		// 다리 연결하기
		Prim(num);
		

		// 출력
		System.out.println(result);
		
	}
	
	
	private static void Prim(int num) {
		// 인접 행렬로 표현하기
		int [][] matrix = new int [num][num];
		
		for(int i=0;i<list.size();i++) {
			Data now = list.get(i);
			if(matrix[now.from][now.to]==0) {
				matrix[now.from][now.to]=matrix[now.to][now.from]=now.weight;
			}else { // 만약에 어떤 섬에서 다른 섬으로 가는 경로가 여러개 있을 경우에는 최소 경로로 넣는다.
				if(now.weight<matrix[now.from][now.to]) matrix[now.from][now.to]=matrix[now.to][now.from]=now.weight;
			}
		}
		
		int [] minEdge = new int[num];
		boolean [] visit = new boolean[num];
		int min=0, current=0,cnt=0;
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1]=0;
		
		for(int c=1;c<num;c++){
			min = Integer.MAX_VALUE;
			current=0;
			
			for(int i=1;i<num;i++) {
				if(min>minEdge[i] && !visit[i]) {
					min = minEdge[i];
					current = i;
				}
			}
			
			result += min;
			visit[current]=true;
			
			for(int i=1;i<num;i++) {
				if(!visit[i] && matrix[current][i]!=0 &&minEdge[i]>matrix[current][i]) {
					 minEdge[i]=matrix[current][i];
				}
			}
		}
		
		for(int i=1;i<visit.length;i++) {
			if(!visit[i]) {
				result = -1;
				break;
			}
		}
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
