import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Omok {
	static int [] dy = {0,1,1,1}; //우 하 우하 좌하
	static int [] dx = {1,0,1,-1};
	static int N = 19;
	static int [][] grid = new int [N][N];
	static List<Data> black = new ArrayList<Data>();
	static List<Data> white = new ArrayList<Data>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//입력
		for(int i =0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j =0;j<N;j++) {
				grid[i][j]=Integer.parseInt(st.nextToken());
				if(grid[i][j]==1) {
					black.add(new Data(i,j));//검은돌 리스트 만들어서 관리
				}else if(grid[i][j]==2) {
					white.add(new Data(i,j));//흰돌 리스트 만들어서 관리
				}
			}
		}

		int bCnt=0; //오목 개수 세려주는 변수
		int ni,nj;
		boolean flag = true;
		//검은 돌
		for(int i=0;i<black.size();i++) {//일단 검은돌부터 다 뽑아서 확인
			Data cur = black.get(i);

			for(int d =0;d<4;d++) {//4방 탐색
				boolean overLap = false;
				switch(d) {
				case 0://우 -> 좌 체크
					if(cur.j-1>=0) {
						if(grid[cur.i][cur.j-1]==1)
							overLap=true;
					}
					break;
				case 1://하 -> 상 체크
					if(cur.i-1>=0) {
						if(grid[cur.i-1][cur.j]==1)
							overLap=true;
					}
					break;
				case 2://우하 -> 좌상 체크
					if(cur.j-1>=0 && cur.i-1>=0) {
						if(grid[cur.i-1][cur.j-1]==1)
							overLap=true;
					}
					break;
				case 3://좌하 -> 우상 체크
					if(cur.j+1<N && cur.i-1>=0) {
						if(grid[cur.i-1][cur.j+1]==1)
							overLap=true;
					}
					break;
				}
				if(overLap) continue;//중복되는 경우 카운트 안해줘도 되니까 continue;
				bCnt =1;//1개부터 갯수 세기
				ni = cur.i + dy[d];
				nj = cur.j + dx[d];

				if(!check(ni,nj) || grid[ni][nj]!=1) {//조건 벗어나면 continue;
					continue;
				}
				bCnt++;//조건 만족하면 까만돌 늘려주기

				while(flag) {//그 방향 계속 돌면서 확인해주기

					if(bCnt>5) {//5보다 크면 그냥 break걸어서 다음 바둑돌로 이동하기
						break;
					}
					ni += dy[d];
					nj += dx[d];
					if(!check(ni,nj) || grid[ni][nj]!=1) {
						break;
					}
					bCnt++;
					if(nj<cur.j) {
						cur = new Data(ni,nj);
					}

				}
				if(bCnt==5) {//오목이 5개가 되면 출력
					System.out.println(1);
					System.out.println((cur.i+1)+" "+(cur.j+1));
					return;
				}
			}
		}

		//흰 돌 - 위에랑 코드 똑같음 
		for(int i=0;i<white.size();i++) {
			Data cur = white.get(i);
			for(int d =0;d<4;d++) {
				boolean overLap = false;
				switch(d) {
				case 0://우 -> 좌 체크
					if(cur.j-1>=0) {
						if(grid[cur.i][cur.j-1]==2)
							overLap=true;
					}
					break;
				case 1://하 -> 상 체크
					if(cur.i-1>=0) {
						if(grid[cur.i-1][cur.j]==2)
							overLap=true;
					}
					break;
				case 2://우하 -> 좌상 체크
					if(cur.j-1>=0 && cur.i-1>=0) {
						if(grid[cur.i-1][cur.j-1]==2)
							overLap=true;
					}
					break;
				case 3://좌하 -> 우상 체크
					if(cur.j+1<N && cur.i-1>=0) {
						if(grid[cur.i-1][cur.j+1]==2)
							overLap=true;
					}
					break;
				}
				if(overLap) continue;//중복되는 경우 카운트 안해줘도 되니까 continue;
				bCnt =1;
				ni = cur.i + dy[d];
				nj = cur.j + dx[d];

				if(!check(ni,nj) || grid[ni][nj]!=2) {
					continue;
				}
				bCnt++;

				while(flag) {
					if(bCnt>5) {//5보다 크면 그냥 break걸어서 다음 바둑돌로 이동하기
						break;
					}
					ni += dy[d];
					nj += dx[d];
					if(!check(ni,nj)|| grid[ni][nj]!=2) {
						break;
					}
					bCnt++;
					if(nj<cur.j) {
						cur = new Data(ni,nj);
					}
				}
				if(bCnt==5) {
					System.out.println(2);
					System.out.println((cur.i+1)+" "+(cur.j+1));
					return;
				}
			}
		}
		if(bCnt!=5) {//승부 결정나지 않는 경우
			System.out.println(0);
		}

	}
	static boolean check(int i,int j) {
		if(i<0 || i>=N || j<0 || j>=N) {
			return false;
		}
		return true;
	}
	static class Data{
		int i;
		int j;
		public Data(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + "]";
		}

	}
}
