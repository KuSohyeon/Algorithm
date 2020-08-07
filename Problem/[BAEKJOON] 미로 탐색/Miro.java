//백준(2178번) - 미로탐색
/*
4 6
101111
101010
101011
111011
출력 : 15

4 6
110110
110110
111111
111101
출력 : 9

2 25
1011101110111011101110111
1110111011101110111011101
출력 : 38

7 7
1011111
1110001
1000001
1000001
1000001
1000001
1111111

출력 : 13
 */




import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Miro {
	static boolean [][] v;
	static int [][] map;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int N,M,result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();//행
		M = sc.nextInt();//열
		
		map = new int[N][M];
		v = new boolean[N][M];
		
		//입력
		for(int i=0;i<N;i++) {
			char [] ch = sc.next().toCharArray();
			for(int j=0;j<M;j++) {
				map[i][j]=(int)(ch[j]-'0');
			}
		}
		
		
		bfs(new Data(0,0,1));
		
		
		System.out.println(result);
		
		sc.close();
	}
	private static void bfs(Data data) {
		Queue<Data> q = new LinkedList<>();
		
		q.offer(data);
		v[data.y][data.x]=true;
		
		Data cur;
		int ni,nj;
		while(!q.isEmpty()) {
			cur = q.poll();
			if(cur.y == N-1 && cur.x ==M-1) {
				result = cur.cnt;
			}
			
			for(int d=0;d<4;d++) {
				ni = cur.y + dy[d];
				nj = cur.x + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj] || map[ni][nj]!=1) {//조건처리
					continue;
				}
				q.offer(new Data(ni,nj,cur.cnt+1));
				v[ni][nj]=true;
			}
			
			
			
			
		}
		
		
	}
	static class Data{
		int y;
		int x;
		int cnt;
		
		public Data(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	
		@Override
		public String toString() {
			return "Data [y=" + y + ", x=" + x + ", cnt=" + cnt + "]";
		}
		
		
	}
	
}
