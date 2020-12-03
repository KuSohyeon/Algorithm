//[SWEA] [모의SW역량테스트] 점심식사시간

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class LunchTime {
	static int N,result;
	static int [][] map;
	static Person [] people;
	static Stair [] stairs;
	static class Person implements Comparable<Person>{
		int i;
		int j;
		int sel;
		int [] dist;
		public Person(int i, int j) {
			super();
			this.i = i;
			this.j = j;
			dist = new int[2];
		}

		@Override
		public int compareTo(Person o) {
			return Integer.compare(this.dist[sel], o.dist[sel]);
		}

		@Override
		public String toString() {
			return "Person [i=" + i + ", j=" + j + ", sel=" + sel + ", dist=" + Arrays.toString(dist) + "]";
		}
		
		
	}
	static class Stair{
		int i;
		int j;
		int time;
		public Stair(int i, int j, int time) {
			super();
			this.i = i;
			this.j = j;
			this.time = time;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			people = new Person[10];
			stairs = new Stair[2];
			
			int pCnt=0, sCnt=0;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]==0) continue;
					else if(map[i][j]==1) {
						people[pCnt++]=new Person(i,j);
					}else {
						stairs[sCnt++]= new Stair(i,j,map[i][j]);
					}
				}
			}
			
			for(int i=0;i<pCnt;i++) {
				for(int j=0;j<sCnt;j++) {
					people[i].dist[j] = Math.abs(people[i].i - stairs[j].i)+ Math.abs(people[i].j - stairs[j].j)+1; // 각 계단별 이동 시간 넣어주기 +1 (계단에 올라가는데 걸리는 시간 1분 미리 더해주기)
				}
			}
			
			result = 987654321;
			powerSet(0,pCnt);
			
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void powerSet(int idx, int pCnt) {
		if(idx == pCnt) {
			int [][] stairTimeTable = new int[2][200];
			PriorityQueue<Person> [] pq = new PriorityQueue[2]; // 우선순위큐 배열을 이용하기
			pq[0] = new PriorityQueue<Person>(); // nullPointer!!!!! 객체 생성
			pq[1] = new PriorityQueue<Person>();
			for(int i=0;i<pCnt;i++) {
				pq[people[i].sel].add(people[i]);
			}
			int max=0;
			for(int i=0;i<2;i++) {
				int to = 0;
				while(!pq[i].isEmpty()) { 
					Person now = pq[i].poll();
					int from = now.dist[i]; // 계단을 이용하는 시간 = 도착했을 때 시간 
					to = now.dist[i]+stairs[i].time; // + 계단의 길이 시간
					for(int j=from;j<to;j++) {
						if(stairTimeTable[i][j]==3) { // 현재 시간에 계단을 이용하는 사람이 3명일 경우는 기다리기
							to++; // 기다리면 종료 시간도 같이 늘어남
							continue;
						}
						stairTimeTable[i][j]++; // 계단 이용하는 사람 수 늘려주기
					}
					
				}
				if(max<to) { // 두 계단을 이용한 시간 중 긴 시간이 최종 시간
					max = to;
				}
			}
			result = Math.min(result, max); // 최소 시간으로 업데이트
			return;
		}
		people[idx].sel = 0;
		powerSet(idx+1, pCnt);
		people[idx].sel=1;
		powerSet(idx+1, pCnt);
	}
}
