//[백준] 페그 솔리테어
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9207 {
	static int minPin,minMove;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			if(tc!=1) {
				String tmp = br.readLine();
			}
			
			char [][] map = new char[5][9];
			for(int i=0;i<5;i++) {
				String s = br.readLine();
				for(int j=0;j<9;j++) {
					map[i][j]=s.charAt(j);
				}
			}
			
			minPin = Integer.MAX_VALUE;
			minMove = Integer.MAX_VALUE;
			
			dfs(0,map);
			
			System.out.println(minPin+" "+minMove);
		}
	}
	private static void dfs(int cnt, char[][] map) {
		if(check(map)) { // 기저조건 : 더 이상 이동할 핀이 없음
			int pinCnt = cntPin(map);
			if(minPin>pinCnt) { // 최소 핀 개수로 업데이트
				minPin = pinCnt;
				minMove = cnt;
			}else if(minPin == pinCnt) { // 최소 이동 개수로 업데이트
				if(minMove>cnt) {
					minMove=cnt;
				}
			}
			return;
		}
		for(int i=0;i<5;i++) {
			for(int j=0;j<9;j++) {
				if(map[i][j]=='o') {
					for(int d=0;d<4;d++) {
						int ni = i + dy[d];
						int nj = j + dx[d];
						if(ni<0 || ni>4 || nj<0 || nj>8) continue;
						if(map[ni][nj]!='o') continue; // 핀의 이동 조건 : 인접한 핀이 있어야함
						ni += dy[d];
						nj += dx[d];
						if(ni<0 || ni>4 || nj<0 || nj>8 || map[ni][nj]!='.') continue; // 이동할 수 있는 공간이 없을 경우 넘겨주기
						map[i][j]='.';
						map[ni-dy[d]][nj-dx[d]]='.';
						map[ni][nj]='o';
						dfs(cnt+1,map);
						// 백트래킹
						map[i][j]='o';
						map[ni-dy[d]][nj-dx[d]]='o';
						map[ni][nj]='.';
					}
				}
			}
		}
		
	}
	// dfs의 기저조건 method : 핀이 더이상 이동할 수 있는지 확인
	private static boolean check(char[][] map) {
		for(int i=0;i<5;i++) {
			for(int j=0;j<9;j++) {
				if(map[i][j]=='o') {
					for(int d=0;d<4;d++) {
						int ni = i + dy[d];
						int nj = j + dx[d];
						if(ni<0 || ni>4 || nj<0 || nj>8) continue; // 범위체크
						if(map[ni][nj]=='o') { // 만약 옆에 핀이 있을 경우만 확인하기
							ni += dy[d];
							nj += dx[d];
							if(ni<0 || ni>4 || nj<0 || nj>8 || map[ni][nj]!='.') continue; // 이동할 수 없으면 넘겨주기
							return false; // 이동할 수 있는 경우가 있으면 false를 리턴해주기
						}
					}
				}
			}
		}
		return true; // 더이상 이동할 수 있는 핀이 없으면 true를 리턴
	}
	// 핀의 개수를 확인하는 method
	private static int cntPin(char[][] map) {
		int cnt=0;
		for(int i=0;i<5;i++) {
			for(int j=0;j<9;j++) {
				if(map[i][j]=='o') cnt++;
			}
		}
		return cnt;
	}
}
