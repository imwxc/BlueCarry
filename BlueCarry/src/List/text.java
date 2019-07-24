package List;

import java.util.ArrayList;
import java.util.Random;

public class text {

	public static void main(String[] agrs) {
		NodeList list=new NodeList();
		Node root=list.root;
		
		
		
		for(int i=1;i<=10;i++) {
			list.end_add(i);
//			list.start_add(i);
		}
//		
		long time=System.currentTimeMillis();
		for (int i=0;i<1000000;i++) {
			list.search(new Random().nextInt(10));
		}
		System.out.println("链表耗费时间："+(System.currentTimeMillis()-time));
		ArrayList list1=new ArrayList();
		time=System.currentTimeMillis();
		for (int i=0;i<1000000;i++) {
			list1.get(new Random().nextInt(10));
		}
		System.out.println("ArrayList耗费时间："+(System.currentTimeMillis()-time));
//		list.insert(2, 20);
//		
//		Node point =root;
//		int count=1;
//		while(point!=null) {
//			count++;
//			System.out.println(point.data);
//			point=point.next;
//		}
//		System.out.println("-"+count);
//		
	}
}
