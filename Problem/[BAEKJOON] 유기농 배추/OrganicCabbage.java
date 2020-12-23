//[백준] 유기농 배추

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class OrganicCabbage {
	static int M,N,K,result;
	static int [][] map;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0};//상 하 좌 우
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws Exception	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			result = 0; // tc마다 초기화
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 가로
			N = Integer.parseInt(st.nextToken()); // 세로
			K = Integer.parseInt(st.nextToken()); // 배추가 심어져 있는 위치의 개수
			
			map = new int[N][M];
			v = new boolean [N][M];
			
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				map[r][c]=1;
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(!v[i][j] && map[i][j]==1) {
						bfs(i,j);
						result++;
					}
				}
			}
			
			System.out.println(result);
		}
	}
	private static void bfs(int r, int c) {
		Queue<int []> q = new LinkedList<int []>();
		q.offer(new int [] {r,c});
		v[r][c]=true;
		
		while(!q.isEmpty()) {
			int [] arr = q.poll();
			
			for(int d=0;d<4;d++) {
				int ni = arr[0] + dy[d];
				int nj = arr[1] + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj] || map[ni][nj]==0) continue;
				v[ni][nj]=true;
				q.offer(new int [] {ni,nj});
			}
		}
		
	}
}
