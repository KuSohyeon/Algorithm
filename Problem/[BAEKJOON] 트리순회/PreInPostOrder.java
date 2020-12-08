//[백준] 트리순회

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PreInPostOrder {
	static int N;
	static List<Integer> [] node;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		node = new ArrayList [N];
		
		for(int i=0;i<N;i++) {
			node[i] = new ArrayList<Integer>();
		}
		for(int i=0;i<N;i++) {
			int parent = sc.next().charAt(0)-'A';
			char tmp = sc.next().charAt(0);
			int left = tmp=='.'?-1:tmp-'A';
			tmp = sc.next().charAt(0);
			int right = tmp=='.'?-1:tmp-'A';
			node[parent].add(left);
			node[parent].add(right);
		}
		
		
		preOrder(0,0);
		System.out.println();
		inOrder(0,0);
		System.out.println();
		postOrder(0,0);
	}
	// 중위 순회
	private static void inOrder(int cnt, int now) {
		if(cnt==N || now==-1) {
			return;
		}
		inOrder(cnt+1, node[now].get(0));
		System.out.print((char)(now+'A'));
		inOrder(cnt+1,node[now].get(1));
	}
	// 후위순회
	private static void postOrder(int cnt, int now) {
		if(cnt==N) {
			return;
		}
		for(int i=0;i<2;i++) {
			if(node[now].get(i)==-1) continue;
			postOrder(cnt+1,node[now].get(i));
		}
		System.out.print((char)(now+'A'));
		
	}
	// 전위순회
	private static void preOrder(int cnt, int parent) {
		if(cnt==N) {
			return;
		}
		System.out.print((char)(parent+'A'));
		for(int i=0;i<2;i++) {
			if(node[parent].get(i)==-1) continue;
			preOrder(cnt+1,node[parent].get(i));
		}
	}
}
