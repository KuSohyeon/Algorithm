//[SW Expert] 1767번 [SW Test] 프로세서 연결하기
/*
3
7
0 0 1 0 0 0 0
0 0 1 0 0 0 0
0 0 0 0 0 1 0
0 0 0 0 0 0 0
1 1 0 1 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0
9
0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 1
1 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0
0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 1
11
0 0 1 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0 1 0 0
0 1 0 1 1 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 1 0 0
0 0 0 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ConnectProcessor {
	public static int [][] map;
	public static int N, max, min, totalCnt; // 멕시노스크기, 최대 코어 수, 최소 전선 길이, 처리할 코어 수
	static ArrayList<int[]> list;
	static int [] dr = {-1,1,0,0}; //상 하 좌 우 
	static int [] dc = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<int[]>(); //처리해야할 가장자리가 아닌 코어들을 저장할 리스트
			max = 0;
			min = Integer.MAX_VALUE;
			totalCnt=0;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i==0 || j==0 || i==N-1 || j==N-1) continue; //가장자리에 있는 코어는 리스트에 추가하지 않음
					// 가장자리에 있지 않은 코어 리스트에 추가
					if(map[i][j]==1) {
						list.add(new int[] {i,j}); //리스트에 1차원 배열 형태로 i,j값 넣어주기
						totalCnt++; //관리해야할 코어 개수 증가
					}
				}
			}//end input
			
			go(0,0,0);
		
			
			
			System.out.println("#"+tc+" "+min);
		}//end TC
		
		
	}//end main
	//부분집합 만드는 메소드
	private static void go(int index, int cCnt, int lCnt) { //index : 처리할 코어의 index, cCnt : 직전까지의 포함된 코어 수, lCnt : 직전까지의 연결한 전선 길이
		// 현재까지 연결된 코어 수 + 앞으로 처리해야할 남은 코어 수 : 기대할 수 있는 코어 수
		// 기대할 수 있는 최대코어 수가 임시 해보다 작다면 진행이 의미 없음
		if(cCnt + totalCnt - index < max) return;
		if(index == totalCnt) {
			if(max<cCnt) {
				max = cCnt;
				min = lCnt;
			}else if(max ==  cCnt) { //최대 코어갯수가 같다면 최소길이의 전선으로
				if(min>lCnt) {
					min = lCnt;
				}
			}
			
			return;
		}
		int [] cur = list.get(index);
		int r = cur[0];
		int c = cur[1];
		
		// 해당 코어 선택
			// 4방향의 직선으로 전선을 놓아보는 시도
		for(int d = 0; d < 4; d++) {
			// 해당 방향으로 전선 놓는게 가능한지 체크
			if(isAvailable(r,c,d)) {
			// 가능하다면 전선 놓기 : 멕시노스 판에 2로 세팅
				int len = setStatus(r,c,d,2);
			// 다음 코어로 넘어가기
				go(index+1, cCnt+1,lCnt+len);
			// 놓았던 전선 지우기(되돌리기) : 멕시노스 판에 0으로 세팅
				setStatus(r,c,d,0);  // 백트래킹
			}
		}
		// 해당 코어 비선택
			//아무런 전선도 놓지 않고 다음 코어로 넘어가기
		go(index+1,cCnt,lCnt);
		
		
	}
	//현 코어의 위치에서 해당 방향으로 전선을 놓는게 가능한지 체크
	private static boolean isAvailable(int r, int c, int d){
		int nr = r, nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break; // 가장자리까지 다 전선을 놓을 수 있는 상황
			if(map[nr][nc]>=1) return false; //1 : 코어, 2: 전선 
		}
		return true;
	}
	// 현 코어의 위치에서 해당 방향으로 전선을 놓거나(s = 2) 지우는(s = 0) 세팅
	private static int setStatus(int r, int c, int d, int s) {//s : 상태값
		int nr = r, nc = c;
		int cnt=0;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break; // 가장자리까지 다 전선을 놓을 수 있는 상황
			map[nr][nc] = s;
			cnt++;
		}
		return cnt;
	}

}
