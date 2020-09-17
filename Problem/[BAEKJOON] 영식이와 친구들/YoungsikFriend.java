import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class YoungsikFriend {
	static int N,M,L;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Queue<Integer> q = new LinkedList<Integer>();
		N = sc.nextInt(); // 사람 수
		M = sc.nextInt(); // 종료 조건(한 사람이 공 받은 횟수)
		L = sc.nextInt(); // 몇번째 있는 사람에게 공을 던질 것인가?
		
		int cnt = 0;//공 던지는 횟수
		int [] person = new int[N+1];
		int target = 0;
		q.offer(1);
		person[1]++;

		while(!q.isEmpty()) {
			// 루프 돌면서 한사람이  M번 받았는지 확인
			int cur = q.poll();
			
			//다음 타겟 정하기
			if(person[cur]%2!=0) { //현재 공을 던진 횟수가 홀수번이면 시계방향으로 L번째 있는사람에게 공던지기
				if(cur+L>N) {
					target = (cur+L)%N;
				}
				else {
					target = cur+L;
				}
				q.offer(target); // 공가지고 있는 사람 큐에 넣어주기
				cnt++; //공 던진 횟수 증가
				if(++person[target] == M) { // M번 받으면 게임 종료
					System.out.println(cnt);
					return;
				}
				
			}else {
				if(cur-L<=0) {
					target = (cur-L)+N;
				}
				else {
					target = cur-L;
				}
				q.offer(target);
				cnt++;
				if(++person[target] == M) { // M번 받으면 게임 종료
					System.out.println(cnt);
					return;
				}
			}

		}
	}
}
