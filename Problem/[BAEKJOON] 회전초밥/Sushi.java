//[백준] 회전초밥 
/*
8 30 4 30
7
9
7
30
2
7
9
25
출력 : 5
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Sushi {
	static int N,d,k,c,max=0;
	static int [] sushi;
	static int [] check;
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());// 접시 수
		d = Integer.parseInt(st.nextToken());// 초밥의 가짓수
		k = Integer.parseInt(st.nextToken());// 연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken());// 쿠폰번호 c
		
		sushi = new int[N]; // 초밥 입력받을 배열
		check = new int[d+1]; // 먹은 초밥 확인하는 배열
		
		for(int i=0;i<N;i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		Do();//초밥 가지수 세는 메소드 호출

		System.out.println(max);
	}

	private static void Do() {
		// 큐 이용 앞에꺼 빼고 뒤에 넣어주니까
		Queue<Integer> q = new LinkedList<>();
		
		int cnt=0;//초밥 종류수
		
		//일단 처음 k개 넣어주기
		for(int i=0;i<k;i++) {
			q.offer(sushi[i]);
			
			if(check[sushi[i]]==0) {//큐에 넣은 스시가 먹은 적없으면 가지수 늘려주기
				cnt++;
			}
			
			check[sushi[i]]++; //먹은 초밥 배열에 표시
		}
		
		// 서비스 초밥
		if(check[c]==0) { //먹은적없으면 가지 수 늘려주기 (근데 어차피 또 빼줄꺼라서 그냥 먹은 표시 안해줄거야)
			cnt++;
		}
		max = Math.max(max, cnt);
		if(check[c]==0) {//나중에 다시 검사할거니까 되돌리기
			cnt--;
		}
		
		if(max==k+1) {//K+1개가 최대 가지수니까 더 안해줘도 됨 return;
			return;
		}
		
		for(int i=1;i<N;i++) {
			
			if(max==k+1) {//K+1개가 최대 가지수니까 더 안해줘도 됨 return;
				return;
			}
			
			int prev = q.poll(); //첫번째꺼 뺴주기
			
			if(check[prev] == 1) { // 만약 첫번째께 1이면 중복된게 없었던 거니까 가지수에서 뺴주기
				cnt--;
			}
			check[prev]--;
			
			q.offer(sushi[(i+k-1)%N]);//큐에 새로운거 넣어주기
			if(check[sushi[(i+k-1)%N]]==0) {//만약 새로운거면 가지수 늘려주기
				cnt++;
			}
			check[sushi[(i+k-1)%N]]++; //먹은 표시
			
			//다시 서비스 초밥 검사
			if(check[c]==0) {
				cnt++;
			}
			
			max = Math.max(max, cnt);
			
			if(check[c]==0) {//나중에 다시 검사할거니까 되돌리기
				cnt--;
			}
		}
		
	}


}
