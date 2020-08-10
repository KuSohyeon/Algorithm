//[SW Expert] 정사각형 방(BFS)
/*
2
2
1 2
3 4
3
9 3 4
6 1 5
7 8 2
출력:
#1 1 2
#2 3 3
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SquareRoom {
	static int [][] square;
	static boolean [][] v;
	static int N,result,max=0,count=0;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for(int tc=1;tc<=T;tc++) {

			N = sc.nextInt();//행렬의 크기

			square = new int[N][N];
			
			
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					square[i][j]=sc.nextInt();
				}
			}
			
			max=0;
			result=Integer.MAX_VALUE;
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					v = new boolean[N][N];
					bfs(new Data(i,j,1));

					if(max<count) {//일단 max와 count 비교해주고
						result = square[i][j];
						max = count;
					}
					if(max==count) {//같을 경우에는 방 숫자가 작은거 넣어주기
						if(result>square[i][j]) {
							result=square[i][j];
							max=count;
						}
					}
				}
			}



			System.out.println("#"+tc+" "+result+" "+max);
		}
		sc.close();
	}
	private static void bfs(Data data) {
		Queue <Data> q = new LinkedList<>();

		q.offer(data);
		v[data.i][data.j]=true;

		Data cur;
	
		while(!q.isEmpty()) {	
			int ni,nj;
			cur=q.poll();
			count=cur.cnt;
			int now = square[cur.i][cur.j];
			for(int d=0;d<4;d++) {
				ni = cur.i + dy[d];
				nj = cur.j + dx[d];

				if(ni>=0 && ni<N && nj>=0&& nj<N && !v[ni][nj]) {//조건처리

					if(square[ni][nj]==now+1) {// 4방위로 검사해서 현재 방크기보다 1 크면 이동하기
						q.offer(new Data(ni,nj,cur.cnt+1));
						v[ni][nj]=true;
					}
				}
			}
		}


	}
	static class Data{
		int i;
		int j;
		int cnt;

		public Data(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + ", cnt=" + cnt + "]";
		}


	}
}
