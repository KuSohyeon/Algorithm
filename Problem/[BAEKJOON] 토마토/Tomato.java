//백준(7576번) - 토마토 (BFS)


/*
6 4
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 1

6 4
0 -1 0 0 0 0
-1 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 1

6 4
1 -1 0 0 0 0
0 -1 0 0 0 0
0 0 0 0 -1 0
0 0 0 0 -1 1

5 5
-1 1 0 0 0
0 -1 -1 -1 0
0 -1 -1 -1 0
0 -1 -1 -1 0
0 0 0 0 0

2 2
1 -1
-1 1
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Tomato {

	static int [][] box;
	static int N,M;
	static Queue<Data> queue = new LinkedList<>();
	
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		

		M = sc.nextInt();//가로칸 수
		N = sc.nextInt();//세로칸 수

		box = new int[N][M];

		//입력
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				box[i][j]=sc.nextInt();//박스 정보 받아오기
			}
		}

		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(box[i][j]==1) {//동시에 해당되는거 있을 수도 있으니까 큐로 바로 넣어주기
					queue.offer(new Data(i,j));
				}
			}
		}
		BFS(); //큐로 먼저 넣어놓고 BFS해주기 -> 그러면 동시에 가능
		
		//확인용 
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(box[i][j]+"\t");
//			}
//			System.out.println();
//		}
		
		int days=0;
		loop:for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(days<box[i][j]) {
					days=box[i][j];
				}
				if(box[i][j]==0) {
					days=0;
					break loop;
				}
			}
		}
		
		System.out.println(days-1);

		//
	}
	private static void BFS() {
		Data cur;
		int ni,nj;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			
			for(int d=0;d<4;d++) {
				ni = cur.y + dy[d];
				nj = cur.x + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M) { // 범위 밖으로 나가면 안됨
					continue;
				}
				if(box[ni][nj]!=0) { //0일때만 해야되니까 가지치기
					continue;
				}
				queue.offer(new Data(ni,nj));
				box[ni][nj] = box[cur.y][cur.x]+1; //cur에 근처에 있는거는 cur이 가지고 있는 값 +1(하루)해주기
			}
		}
		
	}
	static class Data{//구조체 만들어서 사용하기
		int y;
		int x;

		public Data(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Data [y=" + y + ", x=" + x + "]";
		}
	}
}
