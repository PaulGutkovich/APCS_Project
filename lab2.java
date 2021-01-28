/*
public class BasicTester{
  public static void main(String[]args){
    MyLinkedList words = new MyLinkedList();

    //add 0-9 in order
    for(int i = 0; i < 10; i++){
      words.add(i+"");
    }
    //insert 0x - 9x before each value 0-9
    for(int i = 0; i < 10; i++){
      words.add(2*i,i+"x");
    }
    //check the head/tail add
    words.add(0,"head");
    words.add(words.size(),"tail");

    try{
      words.add(-1,"");
      System.out.println("ERROR! Added to negative index");
    }catch(IndexOutOfBoundsException e){

    }

    try{
      words.add(words.size()+1,"");
      System.out.println("ERROR! Added to (size + 1) index");
    }catch(IndexOutOfBoundsException e){

    }

    System.out.println("Your result:\n"+words+" "+words.size());
    System.out.println("expected:\n[head, 0x, 0, 1x, 1, 2x, 2, 3x, 3, 4x, 4, 5x, 5, 6x, 6, 7x, 7, 8x, 8, 9x, 9, tail] 22");
    System.out.println("\nYour result:\n"+words.toStringReversed()+" "+words.size());
    System.out.println("expected:\n[tail, 9, 9x, 8, 8x, 7, 7x, 6, 6x, 5, 5x, 4, 4x, 3, 3x, 2, 2x, 1, 1x, 0, 0x, head] 22");
  }
}
*/

public class Main {
	public static void main(String[] args) {
		MyLinkedList l1 = new MyLinkedList();
		l1.add("a");
		l1.add("b");
		l1.add("c");
		l1.add("d");
		l1.add("e");
		System.out.print(l1);
		l1.set(1, "q");
		System.out.print(l1);
		l1.remove(2);
		System.out.print(l1);
		l1.set(3, "f");
		System.out.print(l1);
		l1.remove(0);
		System.out.print(l1);

		MyLinkedList l2 = new MyLinkedList();
		l2.add("1");
		l2.add("2");
		l2.add("3");
		System.out.println(l2);
		l2.remove(1);
		l2.remove(0);
		l2.remove(0);
		System.out.println(l2);
		l2.add("1");
		l2.add("2");
		l2.add("3");
		System.out.println(l2);


		l1.extend(l2);

		System.out.println(l1);
		System.out.println(l2);

		l2.add("1");
		l2.add("2");
		l2.add("3");
		System.out.println(l2);
	}
}

/*
Output:

[a, b, c, d, e]
[a, q, c, d, e]
[a, q, d, e]
[a, q, d, f]
[q, d, f]
[1, 2, 3]
[]

[1, 2, 3]

[q, d, f, 1, 2, 3]
[]

[1, 2, 3]

*/

public class Node {
	private String data;
	private Node next, prev;

	public Node(String value) {
		data = value;
	}

	public String getData() {
		return data;
	}

	public String setData(String data) {
		this.data = data;
		return this.data;
	}

	public Node getNext() {
		return next;
	}

	public Node setNext(Node next) {
		this.next = next;
		return this.next;
	}

	public Node getPrev() {
		return prev;
	}

	public Node setPrev(Node prev) {
		this.prev = prev;
		return this.prev;
	}
}

public class MyLinkedList {
	private int size;
	private Node start, end;

	public MyLinkedList() {
		;
	}

	public int size() {
		return size;
	}

	public boolean add(String value) {
		Node new_node = new Node(value);

		if (size == 0) {
			start = new_node;
		}
		else {
			end.setNext(new_node);
			new_node.setPrev(end);
		}

		end = new_node;
		size++;
		return true;
	}


	public void add(int index, String value) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index must be between 0 and "+size);
		}

		Node new_node = new Node(value);

		if (size == 0) {
			start = new_node;
			end = new_node;
		}

		if (index == 0) {
			new_node.setNext(start);
			start.setPrev(new_node);
			start = new_node;
		}
		else if (index == size) {
			new_node.setPrev(end);
			end.setNext(new_node);
			end = new_node;
		}
		else {
			Node prev = getNode(index-1);
			Node next = getNode(index); 
			new_node.setNext(next);
			next.setPrev(new_node);
			new_node.setPrev(prev);
			prev.setNext(new_node);
		}

		size++;
	}

	public String remove(int index) {
		if (index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException("Index must be between 0 and " + (size-1));
		}

		String s;
		Node prev;
		Node next;

		if (size == 1) {
			s = start.getData();
			start = null;
			end = null;
			size--;
			return s;
		}

		if (index == 0) {
			s = start.getData();
			start = getNode(1);
			start.setPrev(null);
			size--;
			return s;
		}
		else if (index == size-1) {
			s = end.getData();
			end = getNode(size-2);
			end.setNext(null);
			size--;
			return s;
		}
		else {
			s = getNode(index).getData();
			next = getNode(index+1);
			prev = getNode(index-1);
			prev.setNext(next);
			next.setPrev(prev);
			size--;
			return s;
		}
	}

	public void extend(MyLinkedList other) {
		if (other.size() == 0) {

		}
		else {
			if (size==0) {
				start = other.start;
				end = other.end;
				other.start = null;
				other.end = null;
				size = other.size();
				other.size = 0;
			}
			else {
				end.setNext(other.start);
				end = other.end;
				size += other.size();
				other.size = 0;
			}
		}
	}

	public String get(int index) {
		if (index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException("Index must be between 0 and " + (size-1));
		}

		return getNode(index).getData();
	}

	public String set(int index, String value) {
		if (index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException("Index must be between 0 and " + (size-1));
		}

		String data;
		Node node = getNode(index);

		data = node.getData();
		node.setData(value);
		return data;
	}

	public String toString() {
		Node node = start;
		String s = "\n[";

		if (size == 0) {
			s = "[]";
			return s;
		}

		s = s + node.getData();

		while (true) {
			node = node.getNext();
			if (!(node == null)) {
				s = s + ", ";
				s = s + node.getData();
			}
			else {
				break;
			}
		}

		s = s + "]";
		return s;
	}

	public String toStringReversed() {
		Node node = end;
		String s = "[";

		if (size == 0) {
			s = "[]";
			return s;
		}

		s = s + node.getData();

		while (true) {
			node = node.getPrev();
			if (!(node == null)) {
				s = s + ", ";
				s = s + node.getData();
			}
			else {
				break;
			}
		}

		s = s + "]";
		return s;
	}

	private Node getNode(int index) {
		if (index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException("Index must be between 0 and " + (size-1));
		}

		Node node = start;

		for (int i=0; i<index; i++) {
			node = node.getNext();
		}

		return node;
	}
}