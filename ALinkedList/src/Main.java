
public class Main {

	public static void main(String [] args){

		ALinkedList list = new ALinkedList();

		//Test clearList
		list.add(new Integer(1));
		list.add(new Integer(1));
		list.add(new Integer(1));
		list.add(new Integer(2));
		list.add(new Integer(3));
		list.add(new Integer(2));
		list.add(new Integer(-5));
		list.add(new Integer(-1));
		list.add(new Integer(0));
		list.add(new Integer(10));
		list.remove(2);
		System.out.println("clearList b4 " + list);
		list.clearList();
		System.out.println("clearList 4ft3r " + list);

		//Test insertSort
		list.add(new Integer(5));
		list.add(new Integer(2));
		list.add(new Integer(4));
		list.add(new Integer(120000));
		list.add(new Integer(1));
		list.add(new Integer(17));
		list.add(new Integer(21));
		list.add(new Integer(2));
		list.add(new Integer(3));
		list.add(new Integer(2));
		list.add(new Integer(-5));
		list.add(new Integer(-1));
		list.add(new Integer(0));
		list.add(new Integer(10));
		System.out.println("insertSort b4 " + list);
		list.insertSort();
		System.out.println("insertSort 4ft3r " + list);
		list.clearList();

		//Test deleteNonDuplicates
		list.add(new Integer(2));
		list.add(new Integer(6));
		list.add(new Integer(1));
		list.add(new Integer(4));
		list.add(new Integer(2));
		list.add(new Integer(3));
		list.add(new Integer(2));
		list.add(new Integer(-5));
		list.add(new Integer(-1));
		list.add(new Integer(0));
		list.add(new Integer(10));
		list.add(new Integer(5));
		list.add(new Integer(1));
		list.add(new Integer(3));
		list.add(new Integer(2));
		list.add(new Integer(1));
		list.add(new Integer(3));
		list.add(new Integer(2));
		list.add(new Integer(-5));
		list.add(new Integer(-1));
		list.add(new Integer(0));
		list.add(new Integer(10));
		list.add(new Integer(3));
		list.add(new Integer(2));
		list.add(new Integer(-5));
		list.add(new Integer(-1));
		list.add(new Integer(0));
		list.add(new Integer(10));
		list.add(new Integer(3));
		list.add(new Integer(2));
		list.add(new Integer(-5));
		list.add(new Integer(-1));
		list.add(new Integer(0));
		list.add(new Integer(10));
		list.add(new Integer(3));
		list.add(new Integer(2));
		list.add(new Integer(-5));
		list.add(new Integer(-1));
		list.add(new Integer(0));
		list.add(new Integer(10));
		
		System.out.println("deleteNonDuplicates b4" + list);
		list.deleteNonDuplicates();
		System.out.println("deleteNonDuplicates 4ft3r" + list);
		list.clearList();

		//Test shuffleMerge
		ALinkedList list1 = new ALinkedList();

		list.add(new Integer(5));
		list.add(new Integer(2));
		list.add(new Integer(4));
		list.add(new Integer(120000));
		list.add(new Integer(1));
		list.add(new Integer(3));
		list.add(new Integer(2));
		list.add(new Integer(1));
		list.add(new Integer(5));
		list.add(new Integer(2));
		list.add(new Integer(4));
		list.add(new Integer(120000));
		list.add(new Integer(1));
		list.add(new Integer(2));
		list.add(new Integer(3));
		list.add(new Integer(2));
		

		list1.add(new Integer(1));
		list1.add(new Integer(13));
		list1.add(new Integer(7));	
		list1.add(new Integer(5));
		list1.add(new Integer(2));
		list1.add(new Integer(4));
		list1.add(new Integer(120000));
		list1.add(new Integer(1));
		list1.add(new Integer(17));
		list1.add(new Integer(21));
		list1.add(new Integer(2));
		list1.add(new Integer(3));
		list1.add(new Integer(2));

		System.out.println("shuffleMerge list b4" + list);
		System.out.println("shuffleMerge list1 b4" + list1);
		list.shuffleMerge(list1);
		System.out.println("shuffleMerge list 4ft3r" + list);
		list.clearList();
		list1.clearList();

		//test sortedMerge
		list.add(new Integer(1));
		list.add(new Integer(3));
		list.add(new Integer(5));
		list.add(new Integer(1));
		list.add(new Integer(1));
		list.add(new Integer(2));
		list.add(new Integer(-5));
		list.add(new Integer(-1));
		list.add(new Integer(0));
		list.add(new Integer(10));

		list1.add(new Integer(2));
		list1.add(new Integer(4));
		list1.add(new Integer(6));	
		list1.add(new Integer(1));
		list1.add(new Integer(2));
		list1.add(new Integer(3));

		System.out.println("sortedMerge list b4"+list);
		System.out.println("sortedMerge list b4"+list1);
		list.sortedMerge(list1);
		System.out.println("sortedMerge list 4ft3r"+list);
		list.clearList();
		list1.clearList();


	}	
}
