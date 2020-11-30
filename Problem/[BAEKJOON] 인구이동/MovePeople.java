//[백준] 인구이동

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MovePeople {
	static int N,L,R,result;
	static int [][] map;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0}; // 상 하 좌 우
	static int [] dx = {0,0,-1,1};
	static class Data{
		int i;
		int j;
		int people;
		
		public Data(int i, int j, int people) {
			super();
			this.i = i;
			this.j = j;
			this.people = people;
		}

		public Data(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + ", people=" + people + "]";
		}

	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		// 처리
		boolean flag = true;
		
		while(flag) {
			boolean check = false; // 인구 이동이 일어났는지 확인해줄 boolean형 변수
			v = new boolean[N][N]; // bfs에 사용할 방문 체크 배열
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!v[i][j]) {
						if(bfs(i,j)) check=true; // 인구 이동이 일어났다면 check를 true처리
					}
				}
			}
			if(!check) break; // 인구 이동이 일어나지 않았다면 break
			result++; // 인구 이동이 일어났다면 횟수 증가
		}
		
		
		// 출력
		System.out.println(result);
	}
	// 인구 이동이 일어남 여부를 boolean형으로 return
	private static boolean bfs(int y, int x) {
		Queue<Data> q = new LinkedList<Data>();
		q.offer(new Data(y,x,map[y][x]));
		
		List<Data> list = new ArrayList<Data>(); // 어떤 좌표가 인구 이동했는지 저장할 list
		list.add(new Data(y,x));
		
		v[y][x]=true;
		int total = map[y][x];
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=N || v[ni][nj]) continue; // 범위, 조건체크
				if(Math.abs(map[ni][nj]-cur.people)<L || Math.abs(map[ni][nj]-cur.people)>R) continue; // L이상 R 이하가 아니라면 continue
				
				total += map[ni][nj]; // 총 인구에 더해주기
				q.offer(new Data(ni,nj,map[ni][nj]));
				list.add(new Data(ni,nj));
				v[ni][nj]=true;
			}
			
		}
		
		// 인구 배분해주기
		int size = list.size();
		int people = total/size;
		for(int i=0;i<size;i++) {
			Data now = list.get(i);
			map[now.i][now.j]=people;
		}
		
		if(size>1) return true; // 인구 이동했으면 list 사이즈가 2 이상일 것 
		return false; // 인구 이동 없으면 false를 리턴
		
	}
}
