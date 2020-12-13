//[백준] [삼성SW기출] 마법사 상어와 토네이도

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MagicSharkWithTornado {
	static int N,result;
	static int [][] map;
	static boolean [][] v;
	static int [] dy = {0,1,0,-1};//좌 하 우 상
	static int [] dx = {-1,0,1,0};
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		v = new boolean [N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		// 토네이도 일으키기
		bingle();

		System.out.println(result);
		
	}
	// 토네이도 일으키는 method
	private static void bingle() {
		int ni = N/2, nj = N/2, d = 0;
		int cnt = 0, size = N*N;
		v[ni][nj]=true;
		
		while(++cnt<size) {
			boolean flag = false;
			ni += dy[d];
			nj += dx[d];
			
			if(v[ni][nj]) {
				ni -= dy[d];
				nj -= dx[d];
				if(--d==-1) d=3;
				flag = true;
				cnt--;
			}else {
				v[ni][nj]=true;
				moveSand(ni,nj,d);
			}
			if(!flag && ++d==4) d=0;
		}
	}
	// 모래 움직이는 method
	private static void moveSand(int ni, int nj, int dir) {
		
		int tmpI, tmpJ;
		// a는 다른 곳으로 이동하고 난 후 나머지 -> 소수점은 버린다 -- 이거 때문에 계속 못하고 있엇음..ㅜㅜ 
		int now = map[ni][nj];
		int s1 = (int) (now * 0.01);
		int s2 = (int) (now * 0.02);
		int s5 = (int) (now * 0.05);
		int s7 = (int) (now * 0.07);
		int s10 = (int) (now * 0.1);
		int a = now - 2*(s1+s2+s7+s10)-s5;
		
		tmpI = ni + dy[dir];
		tmpJ = nj + dx[dir];
		// a 
		if(check(tmpI,tmpJ)) {
			map[tmpI][tmpJ] += a;
		}else {
			result += a;
		}
		// a 위
		if(check(tmpI+dy[dir],tmpJ+dx[dir])) {
			map[tmpI+dy[dir]][tmpJ+dx[dir]] += s5;
		}else {
			result += s5;
		}
		// a 양 옆
		if(check(tmpI + dy[dir+1==4?0:dir+1],tmpJ + dx[dir+1==4?0:dir+1])) {
			map[tmpI + dy[dir+1==4?0:dir+1]][tmpJ + dx[dir+1==4?0:dir+1]] += s10;
		}else {
			result += s10;
		}
		if(check(tmpI + dy[dir-1==-1?3:dir-1],tmpJ + dx[dir-1==-1?3:dir-1])) {
			map[tmpI + dy[dir-1==-1?3:dir-1]][tmpJ + dx[dir-1==-1?3:dir-1]] += s10;
		}
		else {
			result += s10;
		}
		// map[ni][nj] 양 옆
		tmpI = ni + dy[dir+1==4?0:dir+1];
		tmpJ = nj + dx[dir+1==4?0:dir+1];
		if(check(tmpI,tmpJ)) {
			map[tmpI][tmpJ] += s7;
		}else {
			result += s7;
		}
		tmpI += dy[dir+1==4?0:dir+1];
		tmpJ += dx[dir+1==4?0:dir+1];
		if(check(tmpI,tmpJ)) {
			map[tmpI][tmpJ] += s2;
		}else {
			result += s2;
		}
		tmpI = ni + dy[dir-1==-1?3:dir-1];
		tmpJ = nj + dx[dir-1==-1?3:dir-1];
		if(check(tmpI,tmpJ)) {
			map[tmpI][tmpJ] += s7;
		}else {
			result += s7;
		}
		tmpI += dy[dir-1==-1?3:dir-1];
		tmpJ += dx[dir-1==-1?3:dir-1];
		if(check(tmpI,tmpJ)) {
			map[tmpI][tmpJ] += s2;
		}else {
			result += s2;
		}
		// x위치에서의 양옆
		tmpI = ni - dy[dir];
		tmpJ = nj - dx[dir];
		if(check(tmpI + dy[dir+1==4?0:dir+1],tmpJ + dx[dir+1==4?0:dir+1])) {
		map[tmpI + dy[dir+1==4?0:dir+1]][tmpJ + dx[dir+1==4?0:dir+1]] += s1;
		}
		else {
			result += s1;
		}
		if(check(tmpI + dy[dir-1==-1?3:dir-1],tmpJ + dx[dir-1==-1?3:dir-1])) {
		map[tmpI + dy[dir-1==-1?3:dir-1]][tmpJ + dx[dir-1==-1?3:dir-1]] += s1;
		}else {
			result += s1;
		}
		
		map[ni][nj]=0;
		
	}
	private static boolean check(int i, int j) {
		if(i<0 || i>=N || j<0 || j>=N) return false;
		return true;
	}
}
