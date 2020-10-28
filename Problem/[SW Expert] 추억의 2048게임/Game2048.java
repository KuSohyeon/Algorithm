//[SWEA] 추억의 2048게임

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Game2048 {
	static int N, result;
	static String dir;
	static int [][] map;
	static boolean [][] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine().trim());
			
			//입력
			N = Integer.parseInt(st.nextToken());
			
			dir = st.nextToken(); // 방향
			
			map = new int[N][N];
			v = new boolean [N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			// 하나로 합칠 수 있을까?
			switch(dir) {
				case "up":
					up();
					break;
				case "down":
					down();
					break;
				case "right":
					right();
					break;
				case "left":
					left();
					break;
			}
			

			
			
			System.out.println("#"+tc);
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
		}
		
	}
	private static void down() {
		for(int j=0;j<N;j++) {
			// 합쳐주기
			for(int i=N-1;i>=0;i--) {
				for(int k =i-1;k>=0;k--) {
					if(map[k][j]!=0 && map[i][j] !=map[k][j]) break;
					else if(map[i][j]==map[k][j]) {
						map[i][j] += map[k][j];
						map[k][j]=0;
						break;
					}
				}
			}
			// 빈칸 땡겨주기
			for(int i=N-1;i>0;i--) {
				if(map[i][j]==0) {
					for(int k=i-1;k>=0;k--) {
						map[i][j]=map[k][j];
						map[k][j]=0;
						if(map[i][j]!=0) break;
					}
				}
			}
		}
	}
	private static void up() {
		for(int j=0;j<N;j++) {
			// 합쳐주기
			for(int i=0;i<N;i++) {
				for(int k =i+1;k<N;k++) {
					if(map[k][j]!=0 && map[i][j] !=map[k][j]) break;
					else if(map[i][j]==map[k][j]) {
						map[i][j] += map[k][j];
						map[k][j]=0;
						break;
					}
				}
			}
			// 빈칸 땡겨주기
			for(int i=0;i<N;i++) {
				if(map[i][j]==0) {
					for(int k=i+1;k<N;k++) {
						map[i][j]=map[k][j];
						map[k][j]=0;
						if(map[i][j]!=0) break;
					}
					
				}
			}
		}
	}
	private static void right() {

		for(int i=0;i<N;i++) {
			// 합쳐주기
			for(int j=N-1;j>=0;j--) {
				for(int k =j-1;k>=0;k--) {
					if(map[i][k]!=0 && map[i][j] !=map[i][k]) break;
					else if(map[i][j]==map[i][k]) {
						map[i][j] += map[i][k];
						map[i][k]=0;
						break;
					}
				}
			}
			// 빈칸 땡겨주기
			for(int j=N-1;j>0;j--) {
				if(map[i][j]==0) {
					for(int k=j-1;k>=0;k--) {
						map[i][j]=map[i][k];
						map[i][k]=0;
						if(map[i][j]!=0) break;
					}
				}
			}
		}
		
	}
	private static void left() {
		for(int i=0;i<N;i++) {
			// 합쳐주기
			for(int j=0;j<N;j++) {
				for(int k =j+1;k<N;k++) {
					if(map[i][k]!=0 && map[i][j] !=map[i][k]) break;
					else if(map[i][j]==map[i][k]) {
						map[i][j] += map[i][k];
						map[i][k]=0;
						break;
					}
				}
			}
			// 빈칸 땡겨주기
			for(int j=0;j<N;j++) {
				if(map[i][j]==0) {
					for(int k=j+1;k<N;k++) {
						map[i][j]=map[i][k];
						map[i][k]=0;
						if(map[i][j]!=0) break;
					}
					
				}
			}
		}
	}

}
