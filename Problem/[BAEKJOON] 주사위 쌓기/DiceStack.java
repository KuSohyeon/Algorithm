import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiceStack {
	static ArrayList<Integer>[] list;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		list = new ArrayList[N];
		
		for(int i=0;i<N;i++) {
			list[i]=new ArrayList<Integer>();
			for(int j=0;j<5;j++) {
				list[i].add(sc.nextInt());
			}
			list[i].add(3, sc.nextInt()); // 4번째 위치에 넣어주기 -> 0이랑 윗면 밑면 관계임
		}
		
		int bottom=0,top=0,result=0,real=0;
	
			for(int c=0;c<list[0].size();c++) { // 1번째 주사위 밑면 경우 다 해보기
				bottom = (int) list[0].get(c);
				top = (int) list[0].get((c+3)%6);

				int max = 0;
				for(int m=0;m<6;m++) { // 윗면 밑면 빼고 옆면 중에서 가장 큰 수 구하기
					if((int)list[0].get(m)==bottom || (int)list[0].get(m)==top) continue;
					max = Math.max(max, (int) list[0].get(m));
				}
				result = max; //result에 넣어주기
				for(int r=1;r<N;r++) { // 1번째 주사위 윗면, 밑면 정했으면 나머지들은 다 정한것
					int tmp = list[r].indexOf(top);
					bottom = (int) list[r].get(tmp);
					top = (int) list[r].get((tmp+3)%6);

					max = 0;
					for(int m=0;m<6;m++) { // 옆면 중 가장 큰 수 구하기
						if((int)list[r].get(m)==bottom || (int)list[r].get(m)==top) continue;
						max = Math.max(max, (int) list[r].get(m));
					}
					result += max; // result에 더해주기
				
				}
				// 제일 큰 수 업데이트
				real = Math.max(real, result);
				
			}
			// 출력
			System.out.println(real);
		
	}
}
