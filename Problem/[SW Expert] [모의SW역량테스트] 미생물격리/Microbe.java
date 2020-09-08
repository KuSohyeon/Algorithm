//[SW Expert] [모의SW역량테스트] 미생물격리
/*
10
7 2 9
1 1 7 1 
2 1 7 1
5 1 5 4
3 2 8 4 
4 3 14 1
3 4 3 3 
1 5 8 2 
3 5 100 1
5 5 1 1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Microbe {
	static int N,M,K,result;
	static int [] dy = {0,-1,1,0,0};
	static int [] dx = {0,0,0,-1,1};
	static Queue<Data> q = new LinkedList<Data>();
	static class Data implements Comparable<Data>{
		int num;
		int i;
		int j;
		int cnt;
		int dir;
		
		public Data(int num, int i, int j, int cnt, int dir) {
			super();
			this.num = num;
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.dir = dir;
		}

		@Override
		public int compareTo(Data o) { // 셀 번호 순으로 정렬
			if(this.num==o.num) { //만약 셀 번호가 같을 경우 미생물 수 많은 순으로 정렬
				return o.cnt - this.cnt;
			}
			return this.num - o.num;
		}

	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		
		for(int tc=1;tc<=T;tc++) {
			
			//입력
			st = new StringTokenizer(br.readLine().trim());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			List<Data> list = new ArrayList<Microbe.Data>();
			
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine().trim());
				// 미생물 다 리스트에 넣어주기
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				list.add(new Data(N*row+col,row,col,cnt,dir));
			}
			
			for(int t=0;t<M;t++) {
				for(int i=0;i<list.size();i++) {
					// 자리 이동
					Data cur = list.get(i);
					cur.i = cur.i + dy[cur.dir];
					cur.j = cur.j + dx[cur.dir];
					cur.num = (cur.i * N)+cur.j;
					
					if(cur.i==0 || cur.i==N-1 || cur.j==0||cur.j==N-1) { //만약 경계지점에 도착한다면
						cur.cnt /= 2; //미생물 수 반으로 줄이고
						cur.dir = (cur.dir%2==0)? cur.dir-1 : cur.dir+1; //방향 반대로 바꿔주기
						if(cur.cnt==0) { //만약 미생물 수가 0인 경우에는 리스트에서 빼주기
							list.remove(i); //리스트꺼 지우면
							i--; //인덱스도 같이 지워주기 (for문 안)
						}
					}
				}
				
				Collections.sort(list); //정렬 -> 같은 셀 안의 군집 합쳐주기 위함
				
				for(int idx=0;idx<list.size()-1;idx++) {
					Data now = list.get(idx); 
					Data next = list.get(idx+1);
					if(now.num == next.num) { //현재꺼랑 다음거랑 같다면
						now.cnt+=next.cnt; //현재의 미생물 수에 다음꺼 더해주기
						list.remove(idx+1); //그리고 작은 군집 지워주기
						idx--; //list 인덱스 맞춰주기 위해서 --
					}
				}
				
				// Java의 객체 지향적인 특성을 이용한 문제
				// Data cur = list.get(i)를 하면 리스트의 i번째 값을 참조하기 때문에 cur.i 와 같은 것을 변경시키면 그대로 바뀜!! 
			}
			
			result =0;
			//시간 종료 후 남은 미생물의 수 다 더해주기
			for(int i=0;i<list.size();i++) {
				result += list.get(i).cnt;
			}
			
			System.out.println("#"+tc+" "+result);
		}
	}
}
