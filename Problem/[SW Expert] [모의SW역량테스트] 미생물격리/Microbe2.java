//[SWEA] [모의 SW 역량테스트] 미생물 격리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Microbe2 {
	static int result,N,M,K;
	static List<Data> list;
	static int [][] map;
	static int [] dy = {0,-1,1,0,0};// 상 하 좌 우
	static int [] dx = {0,0,0,-1,1};
	static class Data implements Comparable<Data>{
		int i;
		int j;
		int cnt;
		int dir;
		public Data(int i, int j, int cnt, int dir) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + ", cnt=" + cnt + ", dir=" + dir + "]";
		}
		@Override
		public int compareTo(Data o) {	
			return o.cnt-this.cnt; // 오름차순으로 정렬
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			result = 0; // tc마다 초기화
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());//map크기
			M = Integer.parseInt(st.nextToken());//M시간
			K = Integer.parseInt(st.nextToken());//군집 개수
			
			map = new int[N][N];
			list = new ArrayList<Microbe2.Data>();
			
			for(int k=0;k<K;k++) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[i][j]=cnt; 
				list.add(new Data(i,j,cnt,dir)); //리스트에 군집 정보 저장
			}

			Move(); // 처리
			
			// M 시간 후 미생물 수 구하기
			for(Data d : list) {
				result += d.cnt;
			}
			// 출력
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void Move() {
		// 동시에 움직이는데 하나씩 움직이니까 서로 교차할 때 문제가 생김 -> 얘는..? map에다가 그냥 더하니까 합쳐지는 군집에는 사용해야되는데 겹치는건 어떡해!! copy 배열을 사용
		// 두 군집이 아니라 세 군집, 네 군집이 합쳐질때 생각 못했음 -> list사용하면 해결 sort
		
		for(int m=0;m<M;m++) { // M 시간만큼 반복
			// 여기서 새로운 맵을 사용해서 찍고
			int [][] copy = new int[N][N];
			int size = list.size();// 군집 수 만큼 반복
			for(int s = 0; s<size; s++) {
				Data cur = list.get(0); // 0번째꺼 뽑아 쓰기
				list.remove(0); // 쓰고 난후는 지워주기
				
				if(map[cur.i][cur.j]==0) { // 만약 0이라면 군집이 합쳐졌는데 미생물 많은애 방향으로 움직이니까 무시해주기
					continue;
				}
				if(map[cur.i][cur.j]!=cur.cnt) {// 군집 합치는 작업 -> 오름차순으로 sort 했기 때문에 그냥 바로 이동해도 됨
					cur.cnt=map[cur.i][cur.j]; // 군집 합쳐서 이동해야지~
				}
				
				map[cur.i][cur.j]=0; // 이동할꺼니가 0으로 바꾸기 -> 다음 뽑았을 때 이동하면 안되기 때문에 표시
				
				int ni = cur.i + dy[cur.dir];
				int nj = cur.j + dx[cur.dir];
				
				if(ni==0 || ni==N-1 || nj==0 || nj==N-1) { // 특수 약품처리한 곳에 닿일 경우 이동 방향 반대, 군집 수 반으로 줄음
					switch(cur.dir) {
					case 1: // 상
						cur.dir=2;
						break;
					case 2:
						cur.dir=1;
						break;
					case 3: 
						cur.dir=4;
						break;
					case 4:
						cur.dir=3;
						break;
					}
					cur.cnt /=2;
					if(cur.cnt==0) continue; // 미생물이 남지 않을 경우는 소멸
				}
				copy[ni][nj]+=cur.cnt; // copy 배열에 표시해주기
				list.add(new Data(ni,nj,cur.cnt,cur.dir)); // 리스트에 다시 더해주기		
			}
			Collections.sort(list); // 한 시간 후에는 다시 sort
			// 원본에 업데이트
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					map[i][j]=copy[i][j];
				}
			}
		}
		
		
	}
}
