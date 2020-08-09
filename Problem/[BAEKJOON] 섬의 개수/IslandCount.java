//백준(4963번) - 섬의 개수(BFS)
/*
1 1
0
2 2
0 1
1 0
3 2
1 1 1
1 1 1
5 4
1 0 1 0 0
1 0 0 0 0
1 0 1 0 1
1 0 0 1 0
5 4
1 1 1 0 1
1 0 1 0 1
1 0 1 0 1
1 0 1 1 1
5 5
1 0 1 0 1
0 0 0 0 0
1 0 1 0 1
0 0 0 0 0
1 0 1 0 1
0 0
출력 : 
0
1
1
3
1
9
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class IslandCount {
	static int [][] map;
	static boolean [][] v;
	static int w, h;
	static int [] dy = {-1,1,0,0,-1,-1,1,1};
	static int [] dx = {0,0,-1,1,-1,1,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			w = sc.nextInt();
			h = sc.nextInt();
			
			if(w==0 && h==0) {
				break;
			}
			
			map = new int[h][w];
			v = new boolean[h][w];
			
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					map[i][j]=sc.nextInt();
				}
			}

			int island=0;//tc마다 섬개수 초기화
			
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					if( map[i][j]==1 && !v[i][j]) {
						bfs(new Data(i,j));
						island++;
					}
				}
			}
			
			System.out.println(island);
		}
		sc.close();
	}
	private static void bfs(Data data) {
		Queue<Data> q = new LinkedList<>();
		
		q.offer(data);
		v[data.i][data.j]=true;
		
		Data cur;
		int ni,nj;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int d=0;d<8;d++) {
				ni = cur.i + dy[d];
				nj = cur.j + dx[d];
				
				if(ni<0 || ni>=h || nj<0 || nj>=w || v[ni][nj] || map[ni][nj]==0) {
					continue;
				}
				q.offer(new Data(ni,nj));
				v[ni][nj]=true;
				
			}
		}
		
		
		
		
	}
	static class Data{
		int i;
		int j;
		
		public Data(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + "]";
		}
		
		
	}
}
