//백준(2667번) - 단지번호 붙이기
/*
7
0110100
0110101
1110101
0000111
0100000
0111110
0111000
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Danji {
	
	static int [][] map;
	static boolean [][] visit;
	static int N;
	static int room;
	
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);
		
		N = sc.nextInt();//지도의 크기
		map = new int[N][N];
		visit = new boolean[N][N];
		
		//입력
		for(int i=0;i<N;i++) {
			char [] ch = sc.next().toCharArray();
			for(int j=0;j<N;j++) {
				map[i][j]=(int)(ch[j]-'0'); //int로 casting해서 넣어주기
			}
		}
		
		int cnt=0;//단지의 개수
		ArrayList<Integer> rooms = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==1 && !visit[i][j]) { //방문을 안한 1이라면~
					room=1; //dfs 할때마다 집  개수 초기화
					dfs(new Data(i,j));
					cnt++; //단지 개수 추가하기 dfs한번당 단지 하나
					rooms.add(room); //room 계산한거 arraylist에 넣어주기
				}
			}
		}
		
		Collections.sort(rooms, new Comparator<Integer>() {//어레이리스트 소트하는방법

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1-o2;//오름차순
			}
		});
		System.out.println(cnt);//단지 수 출력
		for(int room : rooms) {//for-each문으로 뽑아내기
			System.out.println(room);//단지 내 집의 수 출력
		}
		sc.close();
	}
	private static void dfs(Data data) {
		
		
		visit[data.y][data.x]=true;//방문처리
		
		int ni,nj;
		for(int d=0;d<4;d++) {//4방위로 집있나 확인해주기
			ni = data.y + dy[d];
			nj = data.x + dx[d];
			
			if(ni<0 || ni>=N || nj<0 || nj>=N) {//범위처리
				continue;
			}
			if(map[ni][nj]!=1 || visit[ni][nj]) {
				continue;
			}
			dfs(new Data(ni,nj));
			room++;//집 수 늘리기

		}
		
		
	}
	static class Data{
		int y;
		int x;
		
		public Data(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		@Override
		public String toString() {
			return "Data [y=" + y + ", x=" + x + "]";
		}
		
		
	}
}
