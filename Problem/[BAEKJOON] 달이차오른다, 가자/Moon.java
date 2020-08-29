//[BAEKJOON] 달이차오른다 가자
/*
 * 1 7
f0.F..1
출력 : 7
 5 5
....1
#1###
.1.#0
....A
.1.#.
출력 : -1
7 8
a#c#eF.1
.#.#.#..
.#B#D###
0....F.1
C#E#A###
.#.#.#..
d#f#bF.1
출력 : 55
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Moon {
	static int N,M;
	static char [][] maze;
	static boolean [][][] v;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static Queue<Data> q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new char[N][M];
		v = new boolean[1<<6][N][M];

		
		for(int i=0;i<N;i++) {
			char [] ch = br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				maze[i][j]=ch[j];
				if(maze[i][j]=='0') {
					q.offer(new Data(i,j,0,0)); //민식이 현재 위치 큐에 넣어주기
					v[0][i][j]=true;
					continue;
				}
			}
		}
		
		bfs();
		
		br.close();
	}
	private static void bfs() {
		while(!q.isEmpty()) {
			Data cur = q.poll();
			//종료 조건
				if(maze[cur.i][cur.j]=='1') { 
					System.out.println(cur.cnt);
					return;
				}
			
			for(int d = 0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				int nk = cur.key;
				
				if(ni<0 || ni>=N || nj<0 || nj>=M || v[cur.key][ni][nj] ||maze[ni][nj] == '#') continue;
				
				if(maze[ni][nj]>='a' && maze[ni][nj]<='f') { //키 줍기
					nk = cur.key | (1<<(maze[ni][nj]-97)); 
				}
				
				if(maze[ni][nj]>='A' && maze[ni][nj]<='F') { //만약 문에 도착할 경우
					if((cur.key & (1<<(maze[ni][nj]-65))) == 0) { //키가 없으면 큐에 넣어주지 말기
						continue;
					}
				}
				v[nk][ni][nj]=true; //현재 상태에서 방문체크 해주기
				q.offer(new Data(ni,nj,cur.cnt+1,nk)); 
			}
		}
		//큐 다뻇는데 도착 못한거면 
		System.out.println("-1");
		
	}

	static class Data{
		int i;
		int j;
		int cnt;
		int key;
		public Data(int i, int j, int cnt,int key) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.key = key;
		}
		
	}
}
