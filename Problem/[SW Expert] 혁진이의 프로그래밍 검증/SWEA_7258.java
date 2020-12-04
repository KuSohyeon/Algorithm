//[SWEA] 7258번 혁신이의 프로그래밍 검증

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7258 {
	static int R,C;
	static String result;
	static char [][] map;
	static boolean [][][][] v;
	static int [] dy = {-1,1,0,0}; // 상 하 좌 우
	static int [] dx = {0,0,-1,1};
	static class Data{
		int i;
		int j;
		int memory;
		int dir;
		public Data(int i, int j, int memory, int dir) {
			super();
			this.i = i;
			this.j = j;
			this.memory = memory;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + ", memory=" + memory + ", dir=" + dir + "]";
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st= new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new char[R][C];
			v = new boolean[R][C][16][4];
			
			for(int i=0;i<R;i++) {
				String s = br.readLine();
				for(int j=0;j<C;j++) {
					map[i][j]=s.charAt(j);
				}
			}
			
			result = programming(tc);
			
			
			System.out.println("#"+tc+" "+result);
		}
	}
	private static String programming(int tc) {
		String result = "NO";
		Queue<Data> q = new LinkedList<Data>();
		int memory=0,dir=3;
		if(map[0][0]=='@') return "YES";
		switch(map[0][0]) {
		case '<':
				q.offer(new Data(0,0,0,2));
				v[0][0][0][2]=true;
			break;
		case '>':
				q.offer(new Data(0,0,0,3));
				v[0][0][0][3]=true;
			break;
		case '^':
				q.offer(new Data(0,0,0,0));
				v[0][0][0][0]=true;
			break;
		case 'v':
				q.offer(new Data(0,0,0,1));
				v[0][0][0][1]=true;
			break;
		case '_': // 메모리에 0이 저장되어 있으면 오른쪽, 아니면 왼쪽으로 방향 전환
				q.offer(new Data(0,0,0,3));
				v[0][0][0][3]=true;
			break;
		case '|':// 메모리에 0이 저장되어 있으면 아래쪽, 아니면 위쪼긍로 방향 전환 
				q.offer(new Data(0,0,0,1));
				v[0][0][0][1]=true;
			break;
		case '?':
			for(int d=0;d<4;d++) {
					q.offer(new Data(0,0,0,d));
					v[0][0][0][d]=true;
			}
			break;
		case '.':
				q.offer(new Data(0,0,0,dir));
				v[0][0][0][dir]=true;
			break;
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
				q.offer(new Data(0,0,map[0][0]-'0',dir));
				v[0][0][map[0][0]-'0'][dir]=true;
			break;
		case '+':
				q.offer(new Data(0,0,1,dir));
				v[0][0][1][dir]=true;
			break;
		case '-':
				q.offer(new Data(0,0,15,dir));
				v[0][0][15][dir]=true;
			break;
		}
		
		
		loop:while(!q.isEmpty()) {
			Data cur = q.poll();
			
			int ni = cur.i + dy[cur.dir];
			int nj = cur.j + dx[cur.dir];
			
			// 범위 밖으로 가면 반대편으로 넘겨주기
			if(ni<0) ni = R-1;
			if(ni==R) ni = 0;
			if(nj<0) nj = C-1;
			if(nj==C) nj = 0;
			
			switch(map[ni][nj]) {
			case '<':
				if(!v[ni][nj][cur.memory][2]) {
					q.offer(new Data(ni,nj,cur.memory,2));
					v[ni][nj][cur.memory][2]=true;
				}
				break;
			case '>':
				if(!v[ni][nj][cur.memory][3]) {
					q.offer(new Data(ni,nj,cur.memory,3));
					v[ni][nj][cur.memory][3]=true;
				}
				break;
			case '^':
				if(!v[ni][nj][cur.memory][0]) {
					q.offer(new Data(ni,nj,cur.memory,0));
					v[ni][nj][cur.memory][0]=true;
				}
				break;
			case 'v':
				if(!v[ni][nj][cur.memory][1]) {
					q.offer(new Data(ni,nj,cur.memory,1));
					v[ni][nj][cur.memory][1]=true;
				}
				break;
			case '_': // 메모리에 0이 저장되어 있으면 오른쪽, 아니면 왼쪽으로 방향 전환
				if(!v[ni][nj][cur.memory][cur.memory==0?3:2]){
					q.offer(new Data(ni,nj,cur.memory,cur.memory==0?3:2));
					v[ni][nj][cur.memory][cur.memory==0?3:2]=true;
				}
				break;
			case '|':// 메모리에 0이 저장되어 있으면 아래쪽, 아니면 위쪼긍로 방향 전환 
				if(!v[ni][nj][cur.memory][cur.memory==0?1:0]) {
					q.offer(new Data(ni,nj,cur.memory,cur.memory==0?1:0));
					v[ni][nj][cur.memory][cur.memory==0?1:0]=true;
				}
				break;
			case '?':
				for(int d=0;d<4;d++) {
					if(!v[ni][nj][cur.memory][d]) {
						q.offer(new Data(ni,nj,cur.memory,d));
						v[ni][nj][cur.memory][d]=true;
					}
				}
				break;
			case '.':
				if(!v[ni][nj][cur.memory][cur.dir]) {
					q.offer(new Data(ni,nj,cur.memory,cur.dir));
					v[ni][nj][cur.memory][cur.dir]=true;
				}
				break;
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				if(!v[ni][nj][map[ni][nj]-'0'][cur.dir]) { 
					q.offer(new Data(ni,nj,map[ni][nj]-'0',cur.dir));
					v[ni][nj][map[ni][nj]-'0'][cur.dir]=true;
				}
				break;
			case '+':
				if(!v[ni][nj][cur.memory==15?0:cur.memory+1][cur.dir]) {
					q.offer(new Data(ni,nj,cur.memory==15?0:cur.memory+1,cur.dir));
					v[ni][nj][cur.memory==15?0:cur.memory+1][cur.dir]=true;
				}
				break;
			case '-':
				if(!v[ni][nj][cur.memory==0?15:cur.memory-1][cur.dir]) {
					q.offer(new Data(ni,nj,cur.memory==0?15:cur.memory-1,cur.dir));
					v[ni][nj][cur.memory==0?15:cur.memory-1][cur.dir]=true;
				}
				break;
			case '@':
				result = "YES";
				break loop;
			}
		}
		
		return result;
	}
}
