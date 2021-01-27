class Demo {
	public static void main(String[] args) {
		SuperArray x = new SuperArray();

		SuperArray y = new SuperArray(2);
		y.add("a");
		y.add("b");
		y.toStrin();
		y.add(1, "c");
		y.toStrin();
		y.remove(2);

		SuperArray z = new SuperArray(7);
		z.add("d");
		z.add("e");
		z.add(0, "f");

		System.out.println(z.equals(y));

		zip(y, z).toStrin();
	}

	public static void removeDuplicates(SuperArray s) {
		int i = 0;
		int last_index;
		String e;

		while (true) {
			try {
				e = s.get(i);
			}
			catch(Exception ex) {
				break;
			}

			while (true) {
				last_index = s.lastIndexOf(e);

				if (i != last_index) {
					s.remove(last_index);
				}
			}
		}
	}

	public static SuperArray findOverlap(SuperArray a, SuperArray b) {
		int s = a.size();
		String e;
		SuperArray overlap = new SuperArray();

		for (int i=0; i<s; i++) {
			e = a.get(i);
			if (b.contains(e) && !overlap.contains(e)) {
				overlap.add(e);
			}
		}

		return overlap;
	}

	public static SuperArray zip(SuperArray a, SuperArray b) {
		int m;
		SuperArray zipped = new SuperArray();

		if (a.size() > b.size()) {
			m = a.size();
		}
		else {
			m = b.size();
		}

		for (int i=0; i<m; i++) {
			try {
				zipped.add(a.get(i));
			}
			catch (Exception ex) {
				;
			}

			try {
				zipped.add(b.get(i));
			}
			catch (Exception ex) {
				;
			}
		}

		return zipped;
	}
}

class SuperArray {
	private String[] data;
	private int size;

	public SuperArray() {
		data = new String[10];
	}

	public SuperArray(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Initial capacity must be positive");
		}

		data = new String[initialCapacity];
	}

	public int size() {
		return this.size;
	}

	public boolean add(String element) {
		if (size == data.length) {
			resize();
		}

		data[size] = element;
		size++;

		return true;
	}

	public void add(int index, String element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		if (size == data.length) {
			resize();
		}

		String[] temp = new String[data.length];

		for (int i=0; i<=size; i++) {
			if (i<index) {
				temp[i] = data[i];
			}
			else if (i==index) {
				temp[i] = element;
			}
			else {
				temp[i] = data[i-1];
			}
		}

		data = temp.clone();
		size++;
	}

	public String remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		String[] temp = new String[data.length];
		String a = data[index];

		for (int i=0; i<size-1; i++) {
			if (i<index) {
				temp[i] = data[i];
			}
			else {
				temp[i] = data[i+1];
			}
		}

		data = temp.clone();

		size--;
		return a;
	}

	public String get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		return data[index];
	}

	public String set(int index, String element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		String v = data[index];
		data[index] = element;
		return v;
	}

	public int indexOf(String s) {
		for (int i=0; i<size; i++) {
			if (data[i] == s) {
				return i;
			}
		}

		return -1;
	}

	public int lastIndexOf(String value) {
		int last_index = -1;
		for (int i=0; i<size; i++) {
			if (data[i] == value) {
				last_index = i;
			}
		}

		return last_index;
	}

	public String[] toArray() {
		String[] arr = new String[size];
		for (int i=0; i<size; i++) {
			arr[i] = data[i];
		}

		return arr;
	}

	public boolean equals(SuperArray other) {
		if (size != other.size()) {
			return false;
		}

		for (int i=0; i<size; i++) {
			if (data[i] != other.get(i)) {
				return false;
			}
		}

		return true;
	}

	public void toStrin() {
		System.out.printf("[");
		if (size > 0) {
			System.out.printf(data[0]);
		}
		for (int i=1; i<size; i++) {
			System.out.printf(", ");
			System.out.printf(data[i]);
		}
		System.out.printf("]");
	}

	private void resize() {
		String[] temp = new String[2*data.length+1];
		for (int i=0; i<size; i++) {
			temp[i] = data[i];
		}

		data = temp.clone();
	}

	public void clear() {
		size = 0;
		data = new String[10];
	}

	public boolean isEmpty() {
		return (size==0);
	}

	public boolean contains(String s) {
		for (String a : data) {
			if (a==s) {
				return true;
			}
		}

		return false;
	}
}