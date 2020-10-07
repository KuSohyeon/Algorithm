//[백준] 배열돌리기4 - 런타임에러+틀렸음
/*
5 6 1
1 2 3 2 5 6
3 8 7 2 1 3
8 2 3 1 4 5
3 4 5 1 1 1
9 3 2 1 4 3
3 4 2
4 2 1
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ArrayRotation4 {
	static int N,M,K,min=Integer.MAX_VALUE;
	static int [][] A;
	static Data [] cir,per;
	static boolean [] isSelected;
	static int [] dy = {0,1,0,-1};// 우 하 좌 상
	static int [] dx = {1,0,-1,0};// 우 하 좌 상
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
		
		A = new int[N+1][M+1]; // A 배열
		cir = new Data[K]; // 회전 연산을 담을 배열
		per = new Data[K]; // 회전 연산을 담을 배열
		isSelected = new boolean[K]; // 회전 연산을 담을 배열

		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=1;j<=M;j++) {
				A[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine().trim());
			cir[i]=new Data(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		perm(0);
		
		System.out.println(min);
	}
	private static void perm(int cnt) { // 경우의 수 다 해보기
		if(cnt==K) {
			goToDo(); // 배열 돌리러 가기
			return;
		}
		for(int i=0;i<K;i++) {
			if(isSelected[i]) continue;
			per[cnt]=cir[i];
			isSelected[i]=true;
			perm(cnt+1);
			isSelected[i]=false;
		}
	}
	private static void goToDo() {
		// 원본을 망치면 안되니까 복사본 배열을 사용
		int [][] copy = new int[N+1][M+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				copy[i][j]=A[i][j];
			}
		}
		// 배열 돌리기
		for(int i=0;i<K;i++) {
			Data now = per[i];
			int li = now.r-now.s; // 왼쪽 위 i 좌표
			int lj = now.c-now.s; // 왼쪽 위 j 좌표
			int before=0, tmp=0,C=2*now.s+1,cnt=0;
			//왼쪽 꼭지점부터 시작해서 대각선으로 밑으로 내려갈거임(바깥쪽부터 돌리기)
			for(int r=0;r<now.s;r++) { // s번 만큼 돌리면 됨
				int ni = li + r; // 껍데기씩 돌릴거야
				int nj = lj + r;
				tmp = copy[ni][nj];
				
				for(int d=0;d<4;d++) {
					cnt=0; // 방향마다 시작할때 cnt=0으로 바꿔주기
					while(true) {
						if(++cnt==C) break; // 한 방향 다 끝났을 때 break해주기
						before = tmp; // 넣어주기전에 현재 값 담아놓기
						ni += dy[d];
						nj += dx[d];
						if(ni<1 || ni>N || nj<1 || nj>M) break; // 이거 하면 틀렸음 이거 안하면 런타임에러
						tmp= copy[ni][nj]; // 바뀌기 전에 값 업데이트
						copy[ni][nj]=before; // 바꿔주기
						
					}
				}
				C -= 2; // 한바퀴 돌면 양사이드 빼줘야하니까 -2
			}
		}
		// 각 행마다 최소값 업데이트
		for(int i=1;i<=N;i++) {
			int tmp =0;
			for(int j=1;j<=M;j++) {
				tmp += copy[i][j];
			}
			min = Math.min(tmp, min);
		}

		
	}
}
