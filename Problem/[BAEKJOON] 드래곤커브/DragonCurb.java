//[백준] 드래곤 커브
// n세대 드래곤 커브는 n-1세대 드래곤 커브를 끝점을 기준으로 시계방향으로 90도 회전시킨다음에 n-1세대 커브의 끝점에 붙인 것
// -> 시계방향으로 돌린 다음 뒤집은 것이 다음 방향 (뒤집는다 : 좌-우, 상-하 를 의미)
// -> 결국 (d+1)%4와 같음

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DragonCurb {
	static int N,result;
	static int [][] map;
	static ArrayList<Integer> [] arr;
	static int [] dy = {0,-1,0,1};//우 상 좌 하
	static int [] dx = {1,0,-1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[101][101];
		
		
		for(int i=0;i<N;i++) {
			// 입력을 받을 때 마다 map에 찍어주기
			arr = new ArrayList [11]; // 드래곤 커브를 관리할 어레이리스트배열
			for(int j=0;j<=10;j++) {
				arr[j]=new ArrayList<Integer>();
			}
			st = new StringTokenizer(br.readLine().trim());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			DragonCurb(x,y,d,g); // 드래곤 커브 만들러 가기
			
		}
		//1x1 사각형 개수 세리기
		checkSquare();
		
		// 출력
		System.out.println(result);
	}
	private static void checkSquare() {
		// 1x1 사각형 개수 세리기 -> arrayIndexBoundException 방지 차 99번까지 탐색
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(map[i][j]==0) continue; // 마킹 되어있는 경우만 밑에 내용 수행
				if(map[i+1][j]==1 && map[i][j+1]==1 && map[i+1][j+1]==1) {
					result++; // 사각형 늘려주기
				}
			}
		}
		
	}
	private static void DragonCurb(int x, int y, int d, int g) {
		// 현좌표 찍어주기 + 드래곤 커브 표시
		map[y][x] = 1;
		arr[0].add(d); // 0세대 드래곤 커브
		
		// n세대 드래곤 커브 만들기
		for(int i=1;i<=g;i++) {
			// i-1 드래곤 커브를 넣는다
			for(int a : arr[i-1]) {
				arr[i].add(a);
			}
			// 끝점에서 부터 시계방향으로 돌리고 뒤집기 -> 결국 (이전방향+1)%4
			for(int j = arr[i-1].size()-1;j>=0;j--) {
				int a = arr[i-1].get(j);
				arr[i].add((a+1)%4);
			}
		}
		
		// 만든 g세대 드래곤 커브로 map에 표시
		int ny = y;
		int nx = x;
		for(int a : arr[g]) {
			ny += dy[a];
			nx += dx[a];
			if(ny<0 || ny>=101 || nx<0 || nx>=101) continue; // 범위처리
			map[ny][nx]=1;
		}
		
	}
}
