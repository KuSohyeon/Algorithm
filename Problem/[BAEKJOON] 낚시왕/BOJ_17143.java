//[백준] 낚시왕
// 낚시꾼 이동 -- 맨 오른쪽에 위치시키면 중지
// 상어 잡기 -- 땅에서 가장 가까운 상어
// 상어 이동 -- 주어진 속도, 방향이로 이동 상어가 한칸에 두개면 큰 상어로 넣기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17143 {
	static int [][] map;
	static int R,C,result;
	static List<Data> shark;
	static int [] dy = {-1,1,0,0}; // 상 하 우 좌
	static int [] dx = {0,0,1,-1};
	static class Data implements Comparable<Data>{
		int i;
		int j;
		int s; // 속력
		int d; // 방향
		int z; // 크기
		
		public Data(int i, int j, int s, int d, int z) {
			super();
			this.i = i;
			this.j = j;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Data o) {
			return Integer.compare(o.z, this.z);
		}

		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		shark = new ArrayList<Data>();
		map = new int[R+1][C+1];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine().trim());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c]=z;
			shark.add(new Data(r,c,s,d-1,z));
		}
		
		for(int j=1;j<=C;j++) {
			result += getShark(j); // 상어 잡기
			shark = moveShark(); // 상어 움직이기
		}
		
		System.out.println(result);
	}
	// 상어 움직 + 죽이는 메소드
	private static List<Data> moveShark() {
		List<Data> newShark = new ArrayList<Data>();
		// 내림차순으로 정렬해서 사용
		Collections.sort(shark);
		
		for(int i=0;i<=R;i++) {
			Arrays.fill(map[i], 0);
		}
		
		for(int k=0;k<shark.size();k++) {
			Data now = shark.get(k);
			int d = now.d;
			int ni=now.i,nj=now.j;
			int sec=0;
			// 모듈러 연산안하면 시간초과남
			switch(now.d) {
			case 0:
			case 1:
				sec = now.s %(2*R-2);
				break;
			case 2:
			case 3:
				sec = now.s %(2*C-2);
				break;
				
			}
			for(int s=0;s<sec;s++) {
				ni += dy[d];
				nj += dx[d];
				if(ni==0) { // 상 방향이었으니까 하로 바꿔주기
					ni = 2;
					d=1; 
				}else if(ni==R+1){ // 하 였으니까 상으로 바꿔주기
					ni = R-1;
					d=0;
				}
				if(nj==0) { // 좌 였으니까 우로 바꿔주기
					nj = 2;
					d=2;
				}else if(nj==C+1){ // 우였으니까 좌로 바꿔주기
					nj = C-1;
					d=3;
				}
			}
			// 1 칸 = 1 상어 큰 놈이 작은놈 잡아 먹음
			if(map[ni][nj]!=0) continue;
			map[ni][nj]=now.z;
			
			newShark.add(new Data(ni,nj,now.s,d,now.z));
			
		}
		return newShark;
		
	}
	// 낚시왕이 상어 잡기
	private static int getShark(int j) {
		int num=0;
		for(int i=1;i<=R;i++) {
			if(map[i][j]!=0) {
				num=map[i][j];
				map[i][j]=0;
				break;
			}
		}
		
		for(int i=0;i<shark.size();i++) {
			if(num==shark.get(i).z) {
				shark.remove(i);
				break;
			}
		}
		return num;
	}
}
