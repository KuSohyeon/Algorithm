//[백준] 녹색 옷 입은 애가 젤 다지?
// 최소 비용 구하기 -> 다익스트라
/*
3
5 5 4
3 9 1
3 2 7
5
3 7 2 0 1
2 8 0 9 1
1 2 1 8 1
9 8 9 2 0
3 6 5 1 5
7
9 0 5 1 1 5 3
4 1 2 1 6 5 3
0 7 6 1 6 8 5
1 1 7 8 3 2 3
9 4 0 7 6 4 1
5 8 3 2 4 8 3
7 4 8 4 8 3 4
0
Problem 1: 20
Problem 2: 19
Problem 3: 36
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class GreenIsJelDa {
	static int N;
	static int [][]map;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static class Data implements Comparable<Data>{
		int i;
		int j;
		int cost;
		public Data(int i, int j, int cost) {
			super();
			this.i = i;
			this.j = j;
			this.cost = cost;
		}
		@Override
		public int compareTo(Data o) {

			return Integer.compare(this.cost, o.cost);
		}	
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int cnt=1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N==0){//N 입력 0이 들어오면 종료
				return;
			}
			map = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			boolean [][] v = new boolean[N][N]; 
			int [][] distance = new int[N][N];
			for(int i=0;i<N;i++) { //distance 배열 최대값으로 초기화
				Arrays.fill(distance[i], Integer.MAX_VALUE);
			}
			distance[0][0]=map[0][0]; //시작 좌표 세팅
			PriorityQueue<Data> pq = new PriorityQueue<>();
			
			pq.offer(new Data(0,0,distance[0][0])); //pq에 넣어주기
			

			while(!pq.isEmpty()) { //pq가 빌때까지
				
				Data cur = pq.poll();
				
				int minIndexI = cur.i;
				int minIndexJ = cur.j;
				
				v[minIndexI][minIndexJ]=true; //방문처리
				if(v[N-1][N-1]) break;
				
				for(int d=0;d<4;d++) { //상하좌우만 이동가능
					int ni = minIndexI + dy[d];
					int nj = minIndexJ + dx[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=N) continue;
					if(!v[ni][nj] && distance[ni][nj]>map[ni][nj]+distance[minIndexI][minIndexJ]) {
						distance[ni][nj] = map[ni][nj]+distance[minIndexI][minIndexJ];
						pq.offer(new Data(ni,nj,distance[ni][nj]));
					}
				}
				
			}
			
			System.out.println("Problem "+(cnt++)+": "+distance[N-1][N-1]);
		}
	}
}
