//[백준] 개미
/*
 * 개미 여러 마리가 길이가 lcm인 막대기 위에 있다.
 * 개미가 막대의 마지막 까지 걸어간다면, 개미는 그 즉시 떨어지게 된다.
 * 또, 두 개미가 만나게 된다면, 방향을 반대로 바꾸어 걸어가게 된다.
 * 가장 처음에 막대 상에서 개미의 위치를 알고 있지만, 개미가 어느 방향으로 움직이는 지는 알 수가 없다.
 * 모든 개미가 땅에 떨어질 때까지 가능한 가장 빠른 시간과 가장 느린시간을 구하는 프로그램을 작성하시오
 *****************************************************************************
 * 풀이 방법
 * 중간에서 가장 가까운 개미가 떨어지는게  최소 시간
 * 모든 개미가 한 방향으로 갔을 때 양 끝에 있는 애들 중에 제일 오래 걸리는 애들이 최대 시간 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ants {
	static int n,l,min,max;
	static int [] ants;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine().trim());
			
			l = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			ants = new int[n];
			min=0;
			max=0;
			boolean flag = false;
			int leftmin=0, rightmin=0;
			
			for(int i=0;i<n;i++) {
				ants[i]=Integer.parseInt(br.readLine());
				if(ants[i]==l/2) {
					min = l/2;
					flag = true;
				}else if(ants[i]<l/2) {
					leftmin = Math.max(ants[i], leftmin);
				}else {
					rightmin = Math.max(l-ants[i],rightmin);
				}
			}
			
			if(flag) min=l/2;
			else min = Math.max(leftmin, rightmin);
			
			Arrays.sort(ants);
			max = Math.max(l-ants[0], ants[n-1]);
			
			System.out.println(min+" "+max);
		}
	}
}
