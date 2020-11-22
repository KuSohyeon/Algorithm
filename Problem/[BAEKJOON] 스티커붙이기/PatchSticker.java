//[백준] 스티커 붙이기
/*
 * 1. 스티커를 회전시키지 않고 모눈종이에서 떼어낸다.
 * 2. 다른스티커와 겹치거나 노트북을 벗어나지 않으면서 스티커를 붙일 수 있는 위치를 찾는다.
 *    위쪽부터 붙인다. 위쪽도 왼쪽부터
 * 3. 선택한 위치에 스티커를 붙인다. 스티커를 붙일 공간이 없다면 시계방향으로 돌려서 시도해본다
 * 4. 다 돌렸는데도 못붙이면 버린다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PatchSticker {
	static int N,M,K,R,C;
	static int [][] map,sticker;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int k=0;k<K;k++) {
			// 입력
			st = new StringTokenizer(br.readLine().trim());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			sticker = new int[R][C];
			for(int i=0;i<R;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<C;j++) {
					sticker[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			// 스티커 붙이기
			patch();
		}
		
		// 출력
		System.out.println(count());
	}
	// 스티커 개수 세는 method
	private static int count() {
		int cnt=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==1) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	// 스티커 붙이는 method
	private static void patch() {
		//1. 일단 그 상태로 붙인다
		//2. 붙일 곳이 없으면 돌린다
		//3. 다돌려도 없으면 버린다.
		for(int d=0;d<4;d++) { // 4방향으로 돌릴준비
			int [][] turn = stickerTurn(d);
			int I=R, J=C;
			if(d==1 || d==3) {
				I=C;
				J=R;
			}
			for(int i=0;i<=N-I;i++) {
				for(int j=0;j<=M-J;j++) {
					boolean flag = false;
					loop : for(int r=0;r<I;r++) {
						for(int c=0;c<J;c++) {
							if(turn[r][c]==1 
									&& map[i+r][j+c]==1) {
								flag= true;
								break loop;
							}
						}// end c
					}//end r
					// 스티커 붙이기
					if(!flag) {
						for(int r=0;r<I;r++) {
							for(int c=0;c<J;c++) {
								if(turn[r][c]==0) continue;
								map[i+r][j+c]=turn[r][c]; 
							}// end c
						}//end r
						return; // 스티커 붙이면 끝
					}
				}// end j
			}// end i
		}// end d
	}
	// 스티커 돌리는 method
	private static int[][] stickerTurn(int d) {
		int [][] temp = null;
		if(d==0) {
			return sticker;
		}else if(d==3) { // 시계 방향으로 270도 회전
			temp = new int[C][R];
			for(int i=0;i<C;i++) {
				for(int j=0;j<R;j++) {
					temp[i][j]=sticker[j][C-1-i];
				}
			}
		}else if(d==2) { // 시계 방향으로 180도 회전
			temp = new int[R][C];
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++) {
					temp[i][j]=sticker[R-1-i][C-1-j];
				}
			}
		}else if(d==1) { // 시계방향으로 90도 회전
			temp = new int[C][R];
			for(int i=0;i<C;i++) {
				for(int j=0;j<R;j++) {
					temp[i][j]=sticker[R-1-j][i];
				}
			}
		}
		return temp;
	}

	
}
