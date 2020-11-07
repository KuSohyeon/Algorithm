import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 모든 사람들이 계단을 내려가 아래층으로 이동할 최소 시간을 구하라
 * 계단은 최대 3명이 이용 가능
 * 계단 입구에 도착하면 1분 후 , 아래칸으로 내려갈 수 있다.
 * 계단을 올라간 후 완전히 내려가는데는 계단길이만큼의 분이 걸린다.
 * 1. 계단으로 이동한다.(걸리는 시간)
 * 2. 사람이 없다면 1분 후, 내려간다.
 * 3. 사람이 있다면 대기한다.
 * 조합 2C1 , 완탐
 * 49/50...........
 */
/*예제 테케
1
5
0 1 1 0 0
0 0 1 0 3
0 1 0 1 0
0 0 0 0 0
1 0 5 0 0
 */

public class Solution {
	static int N,result;
	static int [][] map;
	static List<Data> person;
	static List<Data> stair1;
	static List<Data> stair2;
	static Data [] stairs;
	static boolean [] v;
	static class Data implements Comparable<Data>{
		int i;
		int j;
		int time;
		public Data(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		public Data(int i, int j, int time) {
			super();
			this.i = i;
			this.j = j;
			this.time = time;
		}
		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + ", time=" + time + "]";
		}
		@Override
		public int compareTo(Data o) {
			return Integer.compare(this.time, o.time);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int tc=1;tc<=T;tc++) {
			result = 987654321; //tc마다 초기화

			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			stairs = new Data[2];
			person = new ArrayList<Data>();

			int index=0;

			for(int i=0;i<N;i++) {
				st = new StringTokenizer (br.readLine().trim());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]==1) {
						person.add(new Data(i,j));
					}else if(map[i][j]!=0) {
						stairs[index++]=new Data(i,j,map[i][j]);
					}
				}
			}

			v = new boolean[person.size()];
			subset(0);

			System.out.println("#"+tc+" "+result);
		}
	}
	private static void subset(int cnt) {
		if(cnt==person.size()) {
			useStairs();
			return;
		}
		v[cnt]=true;
		subset(cnt+1);
		v[cnt]=false;
		subset(cnt+1);
	}
	private static void useStairs() {
		stair1 = new ArrayList<Data>();
		stair2 = new ArrayList<Data>();

		for(int i=0;i<v.length;i++) {
			if(v[i]) {
				stair1.add(person.get(i));
			}else {
				stair2.add(person.get(i));
			}
		}
		
		setTime();

		int tmp = 0;
		tmp = calStair(1)-1;
		tmp = Math.max(tmp, calStair(2)-1);

		result = Math.min(tmp, result);

	}
	private static int calStair(int num) {
		
		List<Data> q = new ArrayList<Data>();
		int usingTime=0;
		if(num==1) {
			Collections.sort(stair1);
			usingTime = stairs[0].time; // 계단길이 K
			q = stair1;
		}else {
			Collections.sort(stair2);
			usingTime = stairs[1].time; // 계단길이 K
			q = stair2;
		}

		int [] use = new int[3];
		int size = q.size();
		if(size==0) return 0;
		int time = 0; // 계단도착 1분 후부터 계단 이용 가능
		int usingPerson = 0,index=0;
		boolean flag = true;

		while(flag) {
			
			if(index==size) {
				int cnt=0;
				time++;
				for(int i=0;i<3;i++) {
					if(use[i]==0) { 
						cnt++;
						continue;
					}
					if(--use[i]==0) {
						usingPerson--;
					}
				}
				if(cnt==3) return time;
				continue;
			}
			
			time++;
			
			if(usingPerson<3) {
				if(index!=0) {
					if(q.get(index).time == q.get(index-1).time) {
						for(int i=0;i<3;i++) {
							if(use[i]==0) {
								use[i]=usingTime;
								usingPerson++;
								index++;
								break;
							}
						}
						time--;
						continue;
					}
					
				}
			}
			
			for(int i=0;i<3;i++) {
				if(use[i]==0) continue;
				if(--use[i]==0) {
					usingPerson--;
				}
			}

			if(usingPerson==3) continue;

			if(time>q.get(index).time) {
				for(int i=0;i<3;i++) {
					if(use[i]==0) {
						use[i]=usingTime;
						usingPerson++;
						index++;
						break;
					}
				}
			}// end if

		}

		return time;
	}
	private static void setTime() {

		Data stair = stairs[0];
		for(int i=0;i<stair1.size();i++) {
			Data now = stair1.get(i);
			int time = Math.abs(now.i - stair.i) + Math.abs(now.j - stair.j);
			now.time = time;
		}

		stair = stairs[1];
		for(int i=0;i<stair2.size();i++) {
			Data now = stair2.get(i);
			int time = Math.abs(now.i - stair.i) + Math.abs(now.j - stair.j);
			now.time = time;
		}

	}
}
