//[SWEA] 파핑파핑 지뢰찾기
/**
 * 
JAVA
언어
121,412 kb
메모리
648 ms
실행시간
3,376
코드길이
-------------------
조건 안걸어주면 시간초과!!
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Minesweeper {
	static int N, end, result;
	static char [][] map, number;
	static List<Data> list;
	static int [] dy = {-1,1,0,0,-1,-1,1,1}; // 상 하 좌 우 좌상 우상 좌하 우하
	static int [] dx = {0,0,-1,1,-1,1,-1,1};
	static class Data implements Comparable<Data>{
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
		@Override
		public int compareTo(Data o) {
			return this.num-o.num;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			result = 0; //tc마다 초기화
			N = Integer.parseInt(br.readLine());
			
			map = new char[N][N];
			number = new char[N][N];
			int [][] v = new int[N][N];
			list = new ArrayList<Data>();
			
			for(int i=0;i<N;i++) {
				char [] ch = br.readLine().toCharArray();
				for(int j=0;j<N;j++) {
					map[i][j]=number[i][j]=ch[j];

				}
			}
			
			// number 배열 숫자로 세팅
			SetNumber();
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]=='.') {
						list.add(new Data(i,j,number[i][j]-'0'));
					}
				}
			}
			
			end = list.size();
			Collections.sort(list); // 0부터 누르는게 좋음
			
			// 다 눌러봐야 하는 경우는 그냥 리스트 사이즈 출력하고 다음 테케로 넘겨주기
			if(list.get(0).num!=0) {
				System.out.println(end);
				continue;
			}
			
			// 뽑아보기
			int jumCnt=0;
			for(int i=0;i<end;i++) {
				if(jumCnt==end) break;
				Data now = list.get(i);
				if(map[now.i][now.j]!='.') continue;
				if(now.num==0) { // 0인 경우는 bfs 탐색 보내주고
					jumCnt += click(now.i,now.j);
				}else jumCnt++; // 그 외의 경우는 그냥 1 더해주기 -> 이거 안하면 시간초과
				result++;
			}
			
			// 출력
			System.out.println("#"+tc+" "+result);
		}
	}
	
	// 클릭했을 때 0이면 바로바로 
	private static int click(int r, int c) {
		Queue<Data> q = new LinkedList<Data>();
		boolean [][] v = new boolean[N][N];
		q.offer(new Data(r,c,0));
		v[r][c]=true;
		map[r][c]=number[r][c];
		int cnt=1;
		
		while(!q.isEmpty()) {
			Data now = q.poll();
			
			map[now.i][now.j]=number[now.i][now.j];
			
			if(number[now.i][now.j]=='0') {
				for(int d=0;d<8;d++) {
					int ni = now.i + dy[d];
					int nj = now.j + dx[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=N || map[ni][nj] !='.') continue;
					cnt++; // .에서 숫자로 바꾼거 갯수 늘려주기
					map[ni][nj]=number[ni][nj];
					v[ni][nj]=true;
					// 그 중 0인 거는 다시 큐에 넣어주고 8방위 찍어주게 하기
					if(map[ni][nj]=='0') q.offer(new Data(ni,nj,0)); 
				}
			}
		}
		
		
		return cnt;
	}
	// 주변에 지뢰의 개수를 세팅하는 메소드
	private static void SetNumber() {
		for(int i =0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(number[i][j]=='*') continue;
				int cnt=0;
				for(int d=0;d<8;d++) {
					int ni = i + dy[d];
					int nj = j + dx[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=N) continue;
					if(map[ni][nj]=='*') cnt++;
				}
				number[i][j]= Integer.toString(cnt).charAt(0);
			}
		}
		
	}
}
