import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class CastleDefence {
	static int N,M,D,result;
	static int [][] map;
	static int [] fighter;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		fighter = new int[3];
		result=0;

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		comb(0,0);

		System.out.println(result);

	}
	// 궁수 위치 정하기
	private static void comb(int cnt, int start) {
		if(cnt==3) {
			GoFight(); // 궁수 위치 다 뽑았으면 이제 싸우러 가기
			return;
		}
		for(int i=start;i<M;i++) {
			fighter[cnt]=i;
			comb(cnt+1,i+1);
		}

	}
	private static void GoFight() {
		List<Data> list = new ArrayList<Data>();
		int [][] copy = new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				copy[i][j]=map[i][j];
			}
		}

		for(int i=0;i<3;i++) {
			list.add(new Data(N,fighter[i]));
		}


		// 궁수 한명 마다 가장 가까운 적 찾기(가장 가까운 적이 여러명일 경우 제일 왼쪽 적) -> 공격하기 -> 공격당한 애는 true로 바꿔주기
		// 궁수 공격 턴이 다 끝나면 업데이트 해주고 한줄 내려주기
		

		int cnt=0;
		boolean flag=true;
		out: while(flag) {
			boolean [][] v = new boolean[N][M];
			for(int d=0;d<3;d++) {
				// 더 이상 공격할 적이 있는지 확인
				if(!check(copy)) break out;
				// 현재 궁수 턴
				Data now = list.get(d);
				int dist = D+1;
				Data enemy = null;
				// 공격할 가장 가까운 적 찾기
				for(int i=N-1;i>=0;i--) { // 밑에서 부터 적 찾기
					for(int j=0;j<M;j++) {
						if(copy[i][j]==1) { // 적인 경우
							int tmp = Math.abs(now.i-i)+Math.abs(now.j-j); 
							if(tmp<=D && dist>tmp) { // 만약 적 거리가 D 이내이고 최소이면
								dist=tmp; // 업데이트
								enemy = new Data(i,j);
							}
							if(dist==tmp) { // 만약 거리가 같을 경우에는 가장 왼쪽의 적부터 침
								if(enemy!=null && enemy.j>j) { // 자바는 null과의 전쟁...
									enemy = new Data(i,j);
								}
							}
						}
					}
				}
				// 공격
				if(enemy!=null) { // 자바는 null과의 전쟁...
					v[enemy.i][enemy.j]=true;
				}
			}
			// 모든 궁수의 공격이 끝난 후 업데이트
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(v[i][j]) {
						copy[i][j]=0; // 공격받았으니까 0으로 바꿔주기
						cnt++; // 공격 처리 받은 애들만 세기
					}
				}
			}
			// 한 칸 내려주기
			for(int i=N-1;i>0;i--) {
				for(int j=0;j<M;j++) {
					copy[i][j]=copy[i-1][j];
				}
			}
			Arrays.fill(copy[0], 0); // 0행은 내릴게 없으니까 0으로 채워주기
			
		}
		result = Math.max(result, cnt);
	}
	static boolean check(int [][] arr) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(arr[i][j]==1) return true;
			}
		}
		return false;
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
