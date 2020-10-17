//[백준] 게리맨더링2
// 구현, 브루트포스 알고리즘, 시뮬레이션, 백트래킹

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GaryMendaring2 {
	static int N,result;
	static int [][] map, copy;
	static boolean [][] v;
	static int [] len, people;
	static int [] dy = {1,1,-1,-1}; // 우하 좌하 좌상 우상
	static int [] dx = {1,-1,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		v = new boolean[N][N];
		len = new int[4];

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = Integer.MAX_VALUE;
		
		for(int i=0;i<N;i++) {
			for(int j=1;j<N;j++) {
				v[i][j]=true;
				dfs(i,j,0,i,j);
				Seperate(i,j);
				v[i][j]=false;
			}
		}

		
		System.out.println(result);
	}
	// 경계 나누기 - 백트래킹
	private static void dfs(int i, int j, int dir,int startI, int startJ) {

		int ni = i + dy[dir];
		int nj = j + dx[dir];

		if(ni==startI && nj == startJ) { // return 조건 한바퀴 돌면 끝
			Seperate(startI,startJ);
			return;
		}
		
		if(ni<0 || ni>=N || nj<0 || nj>=N || v[ni][nj]) return;

		v[ni][nj]=true;
		len[dir]++; // d 구하기
		if(dir!=3) { // 방향 전환할수 있는 경우
			dfs(ni,nj,dir+1,startI,startJ);
		}
		// 방향전환 안하는 경우
		dfs(ni,nj,dir,startI,startJ);
		len[dir]--; // d 구하기
		v[ni][nj]=false;

		

	}
	// 선거구 나눠주기
	private static void Seperate(int startI, int startJ) {
		copy = new int[N][N];
		people = new int[6];
		
		// 경계 표시
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(v[i][j]) {
					copy[i][j]=5;
					people[5] += map[i][j];
				}
			}
		}
		
		// 경계 채워주기
		boolean flag = false;
		if(startI+len[1]+len[2]-1!=0) { // 1일때는 안에 채울 필요없음
			for(int i=startI+1;i<=startI+len[1]+len[2]-1;i++) {
				for(int j= 0;j<N;j++) {
					
					if(copy[i][j]==5) {
						if(!flag) {
							flag=true;
						}else {
							flag=false;
						}
						continue;
					}
					if(flag) {
						copy[i][j]=5;
						people[5] += map[i][j];
					}
				}
			}
		}
		
		
		// 선거구 나누기
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(i<startI+len[1] && j<=startJ) {
					if(copy[i][j]==0) {
						copy[i][j]=1;
						people[1] += map[i][j];
					}
				}
				else if(i<=startI+len[2] && j>startJ) {
					if(copy[i][j]==0) {
						copy[i][j]=2;
						people[2] += map[i][j];
					}
				}
				else if(i>=startI+len[1] && j<startJ-len[1]+len[2]) {
					if(copy[i][j]==0) {
						copy[i][j]=3;
						people[3] += map[i][j];
					}
				}
				else if(i>startI+len[2] && j>=startJ-len[1]+len[2]) {
					if(copy[i][j]==0) {
						copy[i][j]=4;
						people[4] += map[i][j];
					}
				}
			}
		}
		
		// 인원수 가장 작은 차 구하기
		Arrays.sort(people);
		result = Math.min(result, Math.abs(people[1]-people[5]));
		
		

	}
}
