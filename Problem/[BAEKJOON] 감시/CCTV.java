//[백준] 감시

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CCTV {
	static int total;
	static int N, M, result;
	static int[][] map;
	static int[] dy = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dx = { 0, 1, 0, -1 };
	static List<Data> list;

	static class Data {
		int i;
		int j;
		int num;

		public Data(int i, int j, int num) {
			super();
			this.i = i;
			this.j = j;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + ", num=" + num + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		list = new ArrayList<Data>();
		result = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6)
					list.add(new Data(i, j, map[i][j]));
				if(map[i][j]==6) { // cctv 감시 구역을 idx로 표현하므로 6은 겹칠 수 있으니 -1로 바꾸기
					map[i][j]=-1;
				}
			}
		}

		// 로직 처리
		dfs(0);

		// 출력
		System.out.println(result);
	}

	private static void dfs(int idx) {
		if(idx==list.size()) {
			result = Math.min(result, countMap());
			return;
		}
		Data now = list.get(idx);
		for(int dir=0;dir<4;dir++) {
			if(now.num==5) {
				if(dir>0) break;
			}else if(now.num==2) {
				if(dir>=2) break;
			}
			// 씨씨티비 감시 지역 표시
			for(int d=0;d<4;d++) {
				if(now.num==1) {
					if(dir==d) {
						go(idx,d); 
					}
				}else if(now.num==2) {
					if(d == dir || d == dir+2) {
						go(idx,d); 
					}
				}else if(now.num==3) {
					if(dir!=3) {
						if(d== dir || d == dir+1) {
							go(idx,d); 
						}
					}
					else{
						if(d==0 || d==3) {
							go(idx,d); 
						}
					}
				}else if(now.num==4) {
					if(d != dir) {
						go(idx,d); 
					}
				}else {
					go(idx,d);
				}
			}
			dfs(idx+1);
			// 백트래킹
			for(int d=0;d<4;d++) {
				if(now.num==1) {
					if(dir==d) {
						back(idx,d); 
					}
				}else if(now.num==2) {
					if(d == dir || d == dir+2) {
						back(idx,d); 
					}
				}else if(now.num==3) {
					if(dir!=3) {
						if(d== dir || d == dir+1) {
							back(idx,d); 
						}
					}
					else{
						if(d==0 || d==3) {
							back(idx,d);
						}
					}
				}else if(now.num==4) {
					if(d != dir) {
						back(idx,d); 
					}
				}else {
					back(idx,d);
				}
				
			}
		}
	}

	// 백트래킹 함수
	private static void back(int idx, int dir) {
		Data cur = list.get(idx);
		int ni = cur.i, nj = cur.j;
		while (true) {
			ni += dy[dir];
			nj += dx[dir];
			if (ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == -1)
				break; // 벽을 만나면 끝
			if (map[ni][nj] == idx+10) {
				map[ni][nj] = 0;
			}
		}

	}

	// 표시해주는 함수
	private static void go(int idx, int dir) {
		Data cur = list.get(idx);
		int ni = cur.i, nj = cur.j;
		while (true) {
			ni += dy[dir];
			nj += dx[dir];
			if (ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == -1)
				break; // 벽을 만나면 끝
			if (map[ni][nj] == 0)
				map[ni][nj] = idx+10; // cctv의 개수는 최대 8개를 넘지 않는다 -> idx+10을 해주면 겹칠일이 없음
		}
	}

	// 사각지대를 출력하는 함수
	private static int countMap() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}
}
