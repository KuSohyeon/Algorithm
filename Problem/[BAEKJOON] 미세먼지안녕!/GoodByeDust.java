//[백준] 미세먼지 안녕!
/*
7 8 1
0 0 0 0 0 0 0 9
0 0 0 0 3 0 0 8
-1 0 5 0 0 0 22 0
-1 8 0 0 0 0 0 0
0 0 0 0 0 10 43 0
0 0 5 0 15 0 0 0
0 0 40 0 0 0 20 0
출력 : 188
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class GoodByeDust {
	static int R,C,T;
	static int [][] A;
	static int [] cleaner;
	static int [] dy = {-1,1,0,0}; // 상 하 좌 우
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		A = new int[R][C];
		cleaner = new int[2];
		
		int cnt=0;
		for(int i=0;i<R;i++) {
			 st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<C;j++) {
				A[i][j]=Integer.parseInt(st.nextToken());
				if(A[i][j]==-1) {
					cleaner[cnt++]=i;
				}
			}
		}
		
		
		for(int t=0;t<T;t++) {
			goDust(); // 미세먼지 확산
			clean(); // 공기청정기 가동
		}
		
		int result =0; 
		// t초 후 남은 미세먼지양 계산
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(A[i][j]==0 || A[i][j]==-1) continue;
				result += A[i][j];
			}
		}
		// 출력
		System.out.println(result);
	}
	private static void goDust() { // 미세먼지 확산
		int [][] tmp = new int[R][C];
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(A[i][j]==0) continue; // 미세먼지 농도가 없거나 공기청정기가 있다면  넘겨주기
				if(A[i][j]==-1) {
					tmp[i][j]=-1;
					continue;
				}
				// 미세먼지가 있는 곳이라면
				int cnt=0;
				for(int d=0;d<4;d++) {
					int ni = i + dy[d];
					int nj = j + dx[d];
					if(ni<0 || ni>=R || nj<0 || nj>=C || A[ni][nj]==-1) continue;
					tmp[ni][nj] += A[i][j]/5; // A(r,c)/5만큼 확산해주기
					cnt++;
				}
				tmp[i][j] += A[i][j] - A[i][j]/5*cnt; // 남은 미세먼지의 양 업데이트
			}
		}
		
		// 미세먼지 업데이트
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				A[i][j]=tmp[i][j];
			}
		}
		
	}
	private static void clean() { // 공기청정기 작동
		int [] dr = new int[4];
		int [] dr0 = {0,-1,0,1}; // 우 상 좌 하
		int [] dc = {1,0,-1,0}; // 우 상 좌 하
		int [] dr1 = {0,1,0,-1}; // 우 하 좌 상
		
		for(int c=0;c<2;c++) {
			if(c==0) { // 위쪽은 반시계 방향으로 회전
				dr = Arrays.copyOfRange(dr0, 0, 4);
			}else { // 아래쪽은 시계 방향으로 회전
				dr = Arrays.copyOfRange(dr1, 0, 4);
			}
			// 현 위치값 세팅
			int nr = cleaner[c]; 
			int nc = 0;
			int before=0;
			int now=0;
			for(int d=0;d<4;d++) {
				while(true) {
					before=now; // 이전 값 업데이트
					nr += dr[d];
					nc += dc[d];
					if(nr<0 || nr>=R || nc<0 || nc>=C) break;
					if(A[nr][nc]==-1) break; // 공기청정기에 도달하면 stop
					now = A[nr][nc]; // 현재 값 넣어주기
					A[nr][nc]=before; // 한칸씩 밀어주기
				}
				nr -= dr[d]; 
				nc -= dc[d];
				
			}
		}
		
	}
	
}
