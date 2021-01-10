//[백준] 프린터 큐 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class PrinterQueue {
	static int N,M,result;
	static List<Integer> list;
	static Queue<Data> q;
	static class Data{
		int num;
		int score;
		public Data(int num, int score) {
			super();
			this.num = num;
			this.score = score;
		}
		@Override
		public String toString() {
			return "Data [num=" + num + ", score=" + score + "]";
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			q = new LinkedList<Data>();
			list = new ArrayList<Integer>();
			result = 0; // tc마다 초기화
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 문서의 개수
			M = Integer.parseInt(st.nextToken()); // 몇 번째로 인쇄되었는지 궁금한 문서
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				int score = Integer.parseInt(st.nextToken());
				q.offer(new Data(i,score)); // 프린터기(큐)에 넣어주기
				list.add(score); // 중요도 관리하는 list
			}
			
			Collections.sort(list, Collections.reverseOrder()); // 내림차순으로 정렬
			
			
			while(!q.isEmpty()) {
				Data cur = q.poll();
				
				if(cur.score == list.get(0)) { // 남아 있는 문서 중 가장 중요도가 높은 문서랑 값이 똑같다면
					list.remove(0); // 인쇄
					result ++; // 순서 증가
					if(cur.num == M) break; // 궁금했던 문서면 break
				}else {
					q.offer(cur); // 아니면 맨 뒤로 다시 가기
				}
			}
			 // 출력
			System.out.println(result);
		}
	}
}
