//[Programmers] 더 맵게
import java.util.PriorityQueue;

public class MoreSpicy {

	private static int solution(int[] scoville, int K) {
		  int answer = 0;

          PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

          for(int i=0;i<scoville.length;i++) {
              pq.offer(scoville[i]);
          }

          int k =0;

          while(!pq.isEmpty()) {

              int now = pq.poll();
              if(now>=K) {
                  return answer;
              }
              if(pq.isEmpty()){
               break;
              }
              int next = pq.poll();

              k = now + (next*2);
               pq.offer(k);

              answer++;
          }
          return -1;
	}
}
