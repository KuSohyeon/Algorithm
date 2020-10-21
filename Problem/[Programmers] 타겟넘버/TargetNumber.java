//[프로그래머스] 타겟넘버

class TargetNumber {
    static int answer=0;
    static boolean [] v;
    static int [] copy;
    static int tg;
    public int solution(int[] numbers, int target) {
        v = new boolean[numbers.length];
        copy = new int[numbers.length];
       for(int i=0;i<numbers.length;i++){
            copy[i]=numbers[i]; 
       }
        tg = target;
        dfs(0);
        return answer;
    }
    private static void dfs(int cnt) {
		if(cnt==v.length) {
			int sum=0;
			for(int i=0;i<v.length;i++) {
				if(v[i]) {
					sum += copy[i];
				}else {
					sum -= copy[i];
				}
			}
			
			if(sum==tg) answer++;
			return;
		}
		v[cnt]=true;
		dfs(cnt+1);
		v[cnt]=false;
		dfs(cnt+1);
		
	}
}