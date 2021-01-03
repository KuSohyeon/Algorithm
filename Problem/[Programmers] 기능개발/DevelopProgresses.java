import java.util.*;

public class DevelopProgresses {
    public static class Data{
        int progress;
        int speed;
        
        public Data(int progress, int speed){
            this.progress = progress;
            this.speed = speed;
        }
        
        @Override
        public String toString(){
            return "Data [ progress : "+this.progress+", speed : "+this.speed + " ]";
        }
    }
    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        Queue<Data> q = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        
        for(int i=0;i<progresses.length;i++){
            q.offer(new Data(progresses[i], speeds[i]));
        }
        
        while(!q.isEmpty()){
           int size = q.size(); 
            // 하루 동안 모든 작업 수행
            for(int i=0;i<size;i++){
                Data cur = q.poll();
                int nextProgress = cur.progress+cur.speed;
                q.offer(new Data(nextProgress, cur.speed));
            }
            // 오늘 배포할 수 있는 작업 추가
            int cnt = 0;
            while(!q.isEmpty()){
                Data now = q.peek();
                if(now.progress < 100) break;
                q.poll(); // 배포
                cnt++;
            }
            if(cnt!=0) list.add(cnt); // 배포할 수 있는 작업의 수 업데이트
        }
        // 배열에 copy
        answer = new int[list.size()];
        int idx = 0;
        
        for(Integer i : list){
            answer[idx++]=i;
        }
        
        return answer;
    }
    public static void main(String[] args) {
    	int [] progresses = {93,30,55};
    	int [] speeds = {1,30,5};
		int [] answer = solution(progresses, speeds);
		System.out.println(Arrays.toString(answer));
	}
}