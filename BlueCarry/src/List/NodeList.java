package List;

public class NodeList {

	public Node root;
	Node end;
	Node next;
	NodeList(){
		root=new Node();
		onCreate();
	}
	void onCreate() {
		end=root;
	}
	void end_add(int data) {
		Node node=new Node();
		node.data=data;
		end.next=node;
		end=node;
	}
	void start_add(int data) {
		Node node=new Node();
		node.data=data;
		node.next=root.next;
		root.next=node;
	}
	void insert(int index,int data) {
		Node indx=root.next;
		Node pre=root;
		int i=0;
		while(indx!=null) {
			if(i==index) {
				Node node=new Node();
				node.data=data;
				node.next=indx;
				pre.next=node;
			}
			i++;
			pre=pre.next;
			indx=indx.next;
		}
	}
	Node search(int data) {
		Node point=root.next;
		while(point.data!=data) {
			point=point.next;
		}
		if(point==null) {
			System.out.println("无此元素");
		}
		return point;
	}
	
	void data_del(int data) {
		Node del_point_pre=root;
		while(del_point_pre.next.data!=data) {
			del_point_pre=del_point_pre.next;
		}
		Node del_point=del_point_pre.next;
		del_point_pre.next=del_point.next;
		del_point.next=null;
	}
	
}
