import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N,result;
	static int [][] map;
	static List<Data> person;
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
	public static void main(String[] args) throws Exception{
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
	//부분집합을 이용해서 모든 경우의 수 다 해보기 -> 사람의 수는 최대 10 => 10^2
	private static void subset(int cnt) {
		if(cnt==person.size()) {
			calTime();
			return;
		}
		v[cnt]=true;
		subset(cnt+1);
		v[cnt]=false;
		subset(cnt+1);
	}
	private static void calTime() {

		//각 계단을 이용하는 시간 구하기
		int cal = 0,cal1=0,cal2=0;
		cal1 = usingStair(0);
		cal2 = usingStair(1);

		// 둘 중에 최대값이 최종 이용하는 시간
		cal = Math.max(cal1, cal2);

		// result와 비교해서 최소값으로 넣어주기
		result = Math.min(result, cal);
	}
	private static int usingStair(int num) {

		// 파라미터로 전달되는 값을 받아 해당 계단을 이용하는 사람 리스트를 만들기
		List<Data> list = new ArrayList<Data>();
		int len = stairs[num].time;  // 계단의 길이
		int time = 0, index=0;
		boolean flag = true;
		set(list,num);
		Collections.sort(list);

		// 이용가능한 계단 배열
		int [] available = new int[3];

		loop:while(flag) {
			// 마지막 사람까지 갔으면 이제 내려가는 시간만 더해주기
			if(index==list.size()) { 
				downStairs(available);
				time++;
				if(check(available)) break; // 다내려갔으면 끝내기
				continue;
			}
			// 만약 앞에사람하고 동시에 도착한 경우에는 계단 이용할 수 있으면 동시에 내려가기(시간을 안더해줌)
			if(index>0 && list.get(index).time==list.get(index-1).time) {
				if(isPossible(available)) {
					for(int i=0;i<3;i++) {
						if(available[i]==0) {
							available[i]=len;
							index++;
							continue loop;
						}
					}
				}
			}
			time++;
			downStairs(available); // 내려가기
			if(!isPossible(available)) continue; // 계단 이용못하면 넘겨주기
			if(time<list.get(index).time) continue; // 만약 아직 내가 내려갈 시간이 안됐으면 기다리기
			// 이용 가능한 계단이 있다면
			for(int i=0;i<3;i++) {
				if(available[i]==0) {
					available[i]=len;
					index++;
					break;
				}
			}
		}

		return time;
	}
	// 계단 이용 끝났는지 확인하는 메소드(젤 마지막에 사용)
	private static boolean check(int[] available) {
		for(int i=0;i<3;i++) {
			if(available[i]!=0) return false;
		}
		return true;
	}
	// 이용가능한 계단 유무를 알려주는 method
	private static boolean isPossible(int[] available) {
		for(int i=0;i<3;i++) {
			if(available[i]==0) return true;
		}
		return false;
	}
	// 계단 한칸 씩 내려주는 method
	private static void downStairs(int[] available) {
		for(int i=0;i<3;i++) {
			if(available[i]==0)
				continue;
			--available[i];
		}
	}
	// 사람 - 계단 거리 계산해서 리스트로 만들어주는 method
	private static void set(List<Data> list, int num) {
		int dist = 0, stairI = stairs[num].i, stairJ = stairs[num].j;
		for(int i=0;i<person.size();i++) {
			Data now = person.get(i);
			if(v[i] && num==0) {
				dist = Math.abs(now.i-stairI)+Math.abs(now.j-stairJ)+1; // 계단입구에 도착하면 1분 후 아래칸으로 내려갈 수 있다.
				list.add(new Data(now.i, now.j, dist));
			}else if(!v[i] && num==1) {
				dist = Math.abs(now.i-stairI)+Math.abs(now.j-stairJ)+1; // 계단입구에 도착하면 1분 후 아래칸으로 내려갈 수 있다.
				list.add(new Data(now.i, now.j, dist));
			}
		}

	}
}
