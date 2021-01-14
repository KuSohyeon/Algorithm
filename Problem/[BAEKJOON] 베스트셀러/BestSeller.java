//[백준] 베스트셀러

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class BestSeller {
	static HashMap <String, Integer> map;
	public static void main(String[] args)  throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		map = new HashMap<String,Integer>();
		
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			if(!map.containsKey(s)) {
				map.put(s, 1);
			}else {
				map.replace(s, map.get(s)+1);
			}
		}
		
//		List<Entry<String, Integer>> list_entries = new ArrayList<>(map.entrySet());
//		
//		Collections.sort(list_entries, new Comparator<Entry<String,Integer>>(){
//
//			@Override
//			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
//				if(o1.getValue()==o2.getValue()) {
//					return (o1.getKey()).compareTo(o2.getKey());
//				}
//				return o2.getValue().compareTo(o1.getValue());
//			}
//			
//		});
//		
//		System.out.println(list_entries.get(0).getKey());
		
		int value = 0;
		String book ="";
		
		for(String key : map.keySet()) {
			if(value<map.get(key)) {
				value = map.get(key);
				book = key;
			}else if(value == map.get(key)){
				if(book.compareTo(key)>0) {
					value = map.get(key);
					book=key;
				}
			}
			
		}
		
		System.out.println(book);
	}
}
