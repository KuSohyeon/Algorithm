//[백준] 배열돌리기1
// 반시계 방향으로 돌리기
/*
4 4 2
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
출력:
3 4 8 12
2 11 10 16
1 7 6 15
5 9 13 14
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ArrayRotation1 {
	static int N,M,K,min=Integer.MAX_VALUE;
	static int [][] A;
	static Data [] cir,per;
	static boolean [] isSelected;
	static int [] dy = {1,0,-1,0};// 하 우 상 좌 
	static int [] dx = {0,1,0,-1};// 하 우 상 좌 
	static class Data{
		int r;
		int c;
		int s;
		public Data(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
		@Override
		public String toString() {
			return "Data [r=" + r + ", c=" + c + ", s=" + s + "]";
		}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N][M]; // A 배열
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<M;j++) {
				A[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		goToDo(); // 배열 돌리러 가기
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(A[i][j]+" ");
			}
			System.out.println();
		}
	}
	private static void goToDo() {
		int R = Math.min(N,M);
		// 배열 돌리기
		for(int i=0;i<K;i++) {

			int li = 0; // 왼쪽 위 i 좌표
			int lj = 0; // 왼쪽 위 j 좌표
			int before=0, tmp=0,C=0,CR=M,CL=N,cnt=0;
			for(int r=0;r<R/2;r++) { // K번 만큼 돌리면 됨
				int ni = li + r; // 껍데기씩 돌릴거야
				int nj = lj + r;
				tmp = A[ni][nj];

				for(int d=0;d<4;d++) {
					cnt=0;
					if(d==1 || d==3) C=CR;
					else C=CL;
					while(true) {
						if(++cnt==C) break; 
						before = tmp;
						ni += dy[d];
						nj += dx[d];
						if(ni<0 || ni>=N || nj<0 || nj>=M) break;
						tmp= A[ni][nj];
						A[ni][nj]=before;

					}
				}
				CR -= 2;
				CL -= 2;
			}
		}


	}
}
