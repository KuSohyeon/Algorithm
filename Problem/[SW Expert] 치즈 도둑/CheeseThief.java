// SW Expert 치즈도둑(BFS)
/*
- 입력
2
2
1 2
2 1
5
6 8 2 6 2
3 2 3 4 6
6 7 3 3 2
7 2 5 3 6
8 9 5 2 7

- 출력 
#1 2
#2 5
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CheeseThief {
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int [][] cheese;
	static boolean [][] v;
	static int max,T,N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for(int tc=1;tc<=T;tc++) {
			
			N = sc.nextInt();//행렬의 크기
			
			cheese = new int[N][N];//배열 생성해주기
			
			int amount=0;//치즈 맛있는 정도
			
			//입력
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					cheese[i][j] = sc.nextInt();
					if(amount<cheese[i][j]) {
						amount=cheese[i][j];//제일 맛있는 치즈 찾기
					}
				}
			}
			
			
			max=0; //최대값 tc마다 초기화
			for(int x=0;x<=amount;x++) {
				v = new boolean [N][N]; //경우마다 v 생성
				vSet(x); //방문 기록 해주기(x일 지난 치즈들 방문표시해서 탐색 방지)
				int cnt=0;//X일 마다 cnt 초기화
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(!v[i][j]) { //방문 처리 안된것만 할 수 있음
							bfs(new Data(i,j));
							cnt++;//치즈 덩어리세기
							}
					}
				}
				if(max<cnt) {//최대값 바꿔주기
					max=cnt;
				}
			}
			

			System.out.println("#"+tc+" "+ max);
		}
		sc.close();
	}
	private static void bfs(Data data) {
		Queue<Data> q = new LinkedList<>();
		
		q.offer(data); //매개변수로 전달받은 값 넣어주기
		v[data.y][data.x]=true;
		
		Data cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			int ni,nj;
			for(int d=0;d<4;d++) {//4방위 확인
				ni = cur.y + dy[d];
				nj = cur.x + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=N || v[ni][nj]) { //조건 처리
					continue;
				}
				
				q.offer(new Data(ni,nj));//통과한거 큐에 넣어주고
				v[ni][nj]=true; //방문처리
			}
			
			
			
			
			
		}
		
		
	}
	private static class Data{ 
		int y;
		int x;
		public Data(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "[y=" + y + ", x=" + x + "]";
		}
		
		
	}
	private static void vSet(int amount) {//X일쨰의 탐색조건 
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(amount>=cheese[i][j]) { //X번째 + 이전날이면
					v[i][j]=true;//방문처리
				}
			}
		}

	}
	
}
