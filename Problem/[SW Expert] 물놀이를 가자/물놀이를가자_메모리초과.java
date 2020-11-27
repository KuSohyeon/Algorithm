//[SWEA] 물놀이를 가자

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 물놀이를가자_메모리초과 {
	static int N,M,result;
	static int [][] map;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			result=0; // tc마다 초기화
			
			st = new StringTokenizer(br.readLine().trim());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			
			for(int i=0;i<N;i++) {
				String s = br.readLine();
				for(int j=0;j<M;j++) {
					if(s.charAt(j)=='W')
						map[i][j]=-1;
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j]==-1) {
						for(int d=0;d<4;d++) {
							int ni = i + dy[d];
							int nj = j + dx[d];
							if(ni<0||ni>=N||nj<0||nj>=M||map[ni][nj]==-1) continue;
							if(map[ni][nj]==0) {
								map[ni][nj]++;
							}
						}
					}
				}
			}
			
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j]==0) {
						int min = 987654321;
						for(int d=0;d<4;d++) {
							int ni = i + dy[d];
							int nj = j + dx[d];
							if(ni<0||ni>=N||nj<0||nj>=M||map[ni][nj]==-1 || map[ni][nj]==0) continue;
							min = Math.min(min, map[ni][nj]);
						}
						map[i][j]=min+1;
					}
					if(map[i][j]==-1) continue;
					result += map[i][j];
				}
			}
			
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			
			System.out.println("#"+tc+" "+result);
		}
	}
	
}
