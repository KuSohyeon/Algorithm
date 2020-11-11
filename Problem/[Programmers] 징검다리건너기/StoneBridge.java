//[프로그래머스] 징검다리건너기
// 효율성 테스트 0점...

public class StoneBridge {
	  public static int solution(int[] stones, int k) {
	        int answer = 0;
	        int len = stones.length;
	        boolean flag = true;
	        while(flag) {
	        	int min = 200000000; // 최대 값
	        	for(int i=0;i<len;i++) {
	        		if(stones[i]!=0 && min>stones[i]) {
	        			min = stones[i];
	        		}
	        	}
	        	
	        	for(int i=0;i<len;i++) {
	        		if(stones[i]==0) continue;
	        		stones[i] -= min;
	        	}

	        	answer += min;
	        	
	        	if(!check(stones,k)) break;
	        }
	       
	        return answer;
	 }
	 private static boolean check(int[] stones,int k) {
		 int len = stones.length;
		 int cnt=1;
		 for(int i=0;i<len;i++) {
			 if(stones[i]==0) {
				 if(++cnt>k) return false;
				 continue;
			 }
   		cnt=1;
   	}

		return true;
	}
	public static void main(String[] args) {
		 int [] arr = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int sol = solution(arr, 3);
		System.out.println(sol);
	}
}
