//[프로그래머스] 호텔 방 배정

import java.util.Arrays;
//import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.LinkedHashSet;

public class HotelRoom {
	static Map<Long, Long> map = new LinkedHashMap<>();

    public static long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            answer[i] = findEmptyRoom(room_number[i]);
        }

        return answer;
    }
    private static long findEmptyRoom(long room) {
        if (!map.containsKey(room)) {
            map.put(room, room + 1);
            return room;
        }
        
        long nextRoom = map.get(room);
        long emptyRoom = findEmptyRoom(nextRoom);
        map.put(room, emptyRoom);
        return emptyRoom;
    }
	/** 배열 이용(효율성테스트 5개 : 시간초과, 런타임에러) */
//	public static long[] solution(long k, long[] room_number) {
//        long[] answer = new long[room_number.length];
//        boolean [] v = new boolean[(int) k+1];
//        
//        for(int i=0;i<room_number.length;i++) {
//        	if(!v[(int) room_number[i]]) {
//        		answer[i]=room_number[i];
//        		v[(int) room_number[i]]=true;
//        	}else {
//        		for(long j=room_number[i]+1;j<=k;j++) {
//        			if(!v[(int) j]) {
//        				answer[i]=j;
//        				v[(int) j]=true;
//        				break;
//        			}
//        		}
//        	}
//        }
//        return answer;
//	}
	/** LinkedHashSet 이용(효율성테스트 7개 : 시간초과) */
//	public static long[] solution(long k, long[] room_number) {
//		
//		HashSet<Long> set = new LinkedHashSet<Long>();
//		long[] answer = new long [room_number.length];
//		int idx= 0;
//		
//		for(long i=0;i<room_number.length;i++) {
//			if(!set.contains(room_number[(int) i])) {
//				set.add(room_number[(int) i]);
//				answer[idx++]=room_number[(int) i];
//			}else {
//				for(long j=room_number[(int) i]+1;j<=k;j++) {
//					if(!set.contains(j)) {
//						set.add(j);
//						answer[idx++]=j;
//						break;
//					}
//				}
//			}
//		}
//		
//		return answer;
//	}
	
	public static void main(String[] args) {
		long k = 10;
		long [] room = {1,3,4,1,3,1};
		long [] answer = solution(k,room);
		
		System.out.println(Arrays.toString(answer));
	}
}
