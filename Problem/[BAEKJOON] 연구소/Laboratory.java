//[백준] 연구소 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Laboratory {
	static int N,M,result;
	static int [] loc;
	static int [][] map;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0}; // 상 하 좌 우
	static int [] dx = {0,0,-1,1};
	static class Virus{
		int i;
		int j;
		public Virus(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Virus [i=" + i + ", j=" + j + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		loc = new int[3];
		
		for(int i=0;i<N;i++){
			st= new StringTokenizer(br.readLine().trim());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0,0);
		
		System.out.println(result);
		
	}
	// 삼성은 완탐!! 벽 위치 놓을 수 있는 곳 다 놔두기
	private static void comb(int cnt, int start) {
		if(cnt==3) {
			GoVirus();
			return;
		}
		for(int i=start;i<N*M;i++) {
			loc[cnt]=i;
			comb(cnt+1,i+1);
		}
	}
	private static void GoVirus() {
		for(int l : loc) {
			int ni = l / M; //i 좌표
			int nj = l % M; //l 좌표
			if(map[ni][nj]!=0) return; //빈 칸이 아니라면 return;
		}
		
		Queue<Virus> q = new LinkedList<Virus>();
		
		v = new boolean[N][M];
		int [][] copy = new int [N][M];
		
		// 이 메소드에서 사용할 copy 세팅
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				copy[i][j]=map[i][j];
				if(copy[i][j]==2) { // 바이러스 큐에 넣어주기
					q.offer(new Virus(i,j));
				}
			}
		}
		
		// 벽 세팅
		for(int l : loc) {
			int ni = l / M; //i 좌표
			int nj = l % M; //l 좌표
			copy[ni][nj]=1;
		}
		
		// 바이러스 퍼뜨리기
		while(!q.isEmpty()) {
			Virus cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj] || copy[ni][nj]!=0) continue;
	
				v[ni][nj]=true;
				copy[ni][nj]=2;
				q.offer(new Virus(ni,nj));
			}
		}
		
		// 안전지역 검사
		int tmp=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(copy[i][j]==0) {
					tmp++;
				}
			}
		}
		result = Math.max(result, tmp);
	}
	
}
