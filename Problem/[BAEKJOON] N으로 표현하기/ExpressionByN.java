import java.util.Scanner;

public class ExpressionByN {
	static int n, num, result=9, MAX=9;
    public static int solution(int N, int number) {
        n = N;
        num = number;
        dfs(0,0);
        return result>8?-1:result;
    }
    public static void dfs(int cnt, int numb){
        if(cnt>=MAX) return; // 8회 넘어가면 안해봐도 됨
        if(numb == num){ // 그 값에 도달하면 최소값으로 업데이트
            result = Math.min(cnt, result);
        }
        int nnn=0;
        for(int i=0;i<8;i++){
            nnn = (nnn*10)+n; // N을 n번 연속으로 사용 가능
            
            dfs(cnt+i+1,numb+nnn); // +
            dfs(cnt+i+1,numb-nnn); // -
            if(numb==0) continue; // 0을 곱하기 나누기는 의미가 없음
            dfs(cnt+i+1,numb*nnn); // *
            dfs(cnt+i+1,numb/nnn);// /
        }
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println(solution(sc.nextInt(), sc.nextInt()));
	}
	
}
