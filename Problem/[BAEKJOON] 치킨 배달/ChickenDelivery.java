//[백준] 치킨 배달
/*
5 3
0 0 1 0 0
0 0 2 0 1
0 1 2 0 0
0 0 1 0 0
0 0 0 0 2
# 5
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class ChickenDelivery {
	static int N,M;
	static int [][] map;
	static List<Data> home = new LinkedList<>();
	static List<Data> chicken = new LinkedList<>();
	static Data[] winner;
	static int total = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		winner = new Data[M];//살아남은 치킨집 넣어줄 배열
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) { //집 따로 챙겨두기
					home.add(new Data(i,j));
				}
				if(map[i][j]==2) { //치킨집 따로 챙겨두기
					chicken.add(new Data(i,j));
				}
			}
		}
		
		comb(0,0);
		
		System.out.println(total);
	}
	private static void comb(int cnt, int start) {//조합구하기
		if(cnt==M) {//조합 완성됐을때 선택된 치킨집의 치킨거리 구하기
			total = Math.min(total, calDist()); //최소값만 업데이트
			return;
		}
		for(int i=start;i<chicken.size();i++) {
			winner[cnt] = chicken.get(i);
			comb(cnt+1,i+1);
		}
		
	}
	private static int calDist() {//치킨 거리구하는 메소드
		int result =0;
		for(Data d : home) { //집마다 반복하면서
			int min = Integer.MAX_VALUE;
			for(int i=0;i<M;i++) {//치킨집 다 거리 계산해보기
				int dist = Math.abs(d.i-winner[i].i)+Math.abs(d.j-winner[i].j);
				min = Math.min(min, dist); //가장 작은 값만 넣어주기
			}
			result += min; //치킨 거리에 더해주기
		}
		return result;
		
	}
	static class Data{
		int i;
		int j;

		public Data(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
}
