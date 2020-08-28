//[백준] 나이트의 이동
/*
3
8
0 0
7 0
100
0 0
30 50
10
1 1
1 1
출력 : 
5
28
0
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class MoveNight {
	static boolean [][] v;
	static int [] dy = {-1,-2,-2,-1,1,2,2,1};
	static int [] dx = {-2,-1,1,2,2,1,-1,-2};
	static int l,result;
	static int startI,startJ,finI,finJ;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			result = 0;
			l = sc.nextInt();
			
			v = new boolean[l][l];//방문체크 배열 만들어주기
			
			//출발좌표
			startI = sc.nextInt();
			startJ = sc.nextInt();
			//도착좌표
			finI = sc.nextInt();
			finJ = sc.nextInt();
			
			bfs();
			
			System.out.println(result);
		}
		sc.close();
	}
	private static void bfs() {
		Queue<Data>q = new LinkedList<>();
		
		q.offer(new Data(startI,startJ,0));
		v[startI][startJ]=true;
		
		Data cur;
		int ni,nj;
		int cnt =0;
		while(!q.isEmpty()) {
		//동시에 움직여야하니까 큐 size대로 cnt++해주기
			int size = q.size();
			for(int i=0;i<size;i++) {
				cur = q.poll();
				
				if(cur.i==finI && cur.j == finJ) {//도착하면 result에 cnt 넣어주고 return;
					result = cnt;
					return;
				}
				for(int d=0;d<8;d++) {//8방 탐색
					ni = cur.i+dy[d];
					nj = cur.j+dx[d];
					
					if(ni<0 || ni>=l || nj<0 || nj>=l || v[ni][nj]) continue; //범위 체크
					
					v[ni][nj]=true;
					q.offer(new Data(ni,nj,cnt)); //갈 수 있으면 큐에 넣어주기
					
				}
				
			}
			cnt++;//cnt증가
		}
		
		
	}
	static class Data{
		int i;
		int j;
		int cnt;
		
		public Data(int i, int j, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
		
	}
}
