//[프로그래머스] 크레인 인형 뽑기
/*
0 0 0 0 0
0 0 1 0 3
0 2 5 0 1
4 2 4 4 2
3 5 1 3 1
1 5 3 5 1 2 1 4
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrainSelectDoll {
	static int [][] board;
	static int [] moves;
	static List<Integer> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		board = new int[5][5];
		moves = new int[8];
		list = new ArrayList<Integer>();
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				board[i][j]=sc.nextInt();
			}
		}
		
		for(int i=0;i<8;i++) {
			moves[i]=sc.nextInt();
		}
		
		System.out.println(solution(board,moves));
		
		
	}
	private static int solution(int[][] board, int[] moves) {
		int answer = 0;
		
		for(int i=0;i<moves.length;i++) {
			int now = moves[i];
			
			for(int r=0;r<board.length;r++) {
				if(board[r][now-1]==0) continue;
				list.add(board[r][now-1]); // 뽑은거 담아주기
				if(list.size()>1) { // 처음 뽑은게 아니라면
					int before = list.get(list.size()-2); // 이전거를 뽑아서
					if(before==board[r][now-1]) { // 똑같은 인형이면
						answer +=2; // 2개 제거
						list.remove(list.size()-1);
						list.remove(list.size()-1);
					}
					
				}
				board[r][now-1]=0;
				break;
			}
		}
		return answer;
	}
}
