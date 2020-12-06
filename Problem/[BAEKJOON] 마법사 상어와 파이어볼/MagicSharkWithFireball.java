//[백준] [삼성 코테 기출]마법사 상어와 파이어볼

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MagicSharkWithFireball {
	static int N,M,K;
	static List<Fire> [] loc,arr;
	static int [] dy = {-1,-1,0,1,1,1,0,-1}; // 상 우상 우 우하 하 좌하 좌 좌상
	static int [] dx = {0,1,1,1,0,-1,-1,-1}; // 상 우상 우 우하 하 좌하 좌 좌상
	static class Fire{
		int r; 
		int c;
		int m; // 질량
		int s; // 속도
		int d; // 방향
		public Fire(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		@Override
		public String toString() {
			return "Fire [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 맵의 크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼의 갯수
		K = Integer.parseInt(st.nextToken()); // 반복 횟수
		
		int size = N*N;
		loc = new ArrayList [size]; // 현재 파이어볼의 위치를 관리해줄 리스트형 배열
		arr = new ArrayList [size]; // 파이어 볼의 도착지를 관리해줄 리스트형 배열
		
		for(int i=0;i<size;i++) {
			loc[i]=new ArrayList<Fire>();
			arr[i]=new ArrayList<Fire>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1; // 1<=r,c<=N 이기 때문에 0,0부터 시작하려면 1씩 빼줘야함 -> 런타임 에러 발생 위험제거
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int num = N*r+c;
			loc[num].add(new Fire(r,c,m,s,d));
		}
		
		for(int i=0;i<K;i++) {
			move();
		}
		
		
		System.out.println(count());
	}
	// 최종적으로 질량을 구하는 method
	private static int count() {
		int size = N*N;
		int result = 0;
		for(int i=0;i<size;i++) {
			for(int j=0;j<loc[i].size();j++) {
				result += loc[i].get(j).m;
			}
		}
		return result;
	}
	// 파이어볼의 이동
	private static void move() {
		int size = N*N;
		for(int i=0;i<size;i++) {
			for(int j=0;j<loc[i].size();j++) {
				Fire now = loc[i].get(j);
				int nr = now.r;
				int nc = now.c;

				int tmpR =  now.s * dy[now.d];
				int tmpC =  now.s * dx[now.d];
				
				// s 는 1이상 1000이하 이기 때문에 모듈러 연산을 해주는 것이 속도향상
				if(tmpR<0) {
					nr = nr-(-tmpR)%N;
					if(nr<0) nr += N;
				}else {
					nr = (nr+tmpR)%N;
				}
				if(tmpC<0) {
					nc = nc-(-tmpC)%N;
					if(nc<0) nc += N;
				}else {
					nc = (nc+tmpC)%N;
				}
				
				int arrive = nr*N + nc;
				arr[arrive].add(new Fire(nr,nc,now.m,now.s,now.d));
			}
		}
		
		copy(); // 파이어볼의 위치 세팅해주기
	}
	// 파이어볼의 도착지를 카피해주는 method
	private static void copy() {
		clear(loc);
		int size = N*N;
		for(int i=0;i<size;i++) {
			if(arr[i].size()==0) continue;
			if(arr[i].size()==1) {
				loc[i].add(arr[i].get(0));
			}else { // 만약 파이어볼이 여러개일 경우  4개로 나눠서 넣어줘야함
				int ball = arr[i].size();
				int r = arr[i].get(0).r, c = arr[i].get(0).c;
				int m = arr[i].get(0).m, s = arr[i].get(0).s, d = (arr[i].get(0).d)%2;
				boolean dir = true;
				for(int j=1;j<ball;j++) {
					m += arr[i].get(j).m;
					s += arr[i].get(j).s;
					if((arr[i].get(j).d)%2!=d) dir = false; // 방향이 모두 홀 짝이 아닌 경우
				}
				if(m/5==0) continue;
				for(int di=0;di<8;di++) {
					// 방향이 모두 홀 짝인 경우에는 0 2 4 6 
					if(dir) {
						if(di%2==1) continue;
					}else {
						if(di%2==0) continue;
					}
					loc[i].add(new Fire(r,c,m/5,s/ball,di));
				}
				
			}
		}
		clear(arr);
	}
	// list 클리어 method
	private static void clear(List<Fire>[] list) {
		int size = N*N;
		for(int i=0;i<size;i++) {
			list[i].clear();
		}
	}
}
