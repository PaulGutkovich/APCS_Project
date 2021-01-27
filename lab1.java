// Part 4a
class Demo {
	// Part 4a
	public static void main(String[] args) {
		SuperArray x = new SuperArray();

		SuperArray y = new SuperArray(2);
		y.add("a");
		y.add("b");
		y.tostring();
		y.add(1, "c");
		y.tostring();
		y.remove(2);
		y.tostring();
		y.clear();
		y.tostring();
		System.out.println("\n"+y.isEmpty());
		y.add("a");
		y.add("b");
		y.add("c");

		SuperArray z = new SuperArray(7);
		z.add("d");
		z.add("e");
		z.add(0, "f");

		System.out.println(z.equals(y));

		zip(y, z).tostring();
	}

	// Part 4b
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

	// Part 4c
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

	// Part 4d (Written 4e in email)
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
	// Part 1a
	private String[] data;
	private int size;

	// Part 1b
	public SuperArray() {
		data = new String[10];
	}

	// Part 2f, 3a
	public SuperArray(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Initial capacity must be positive");
		}

		data = new String[initialCapacity];
	}

	// Part 1c
	public int size() {
		return this.size;
	}

	// Part 1d, 2b
	public boolean add(String element) {
		if (size == data.length) {
			resize();
		}

		data[size] = element;
		size++;

		return true;
	}

	// Part 2g, 3d
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

	// Part 2h, 3e
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

	// Part 1e, 3b
	public String get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		return data[index];
	}

	// Part 1f, 3c
	public String set(int index, String element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		String v = data[index];
		data[index] = element;
		return v;
	}

	// Part 2i
	public int indexOf(String s) {
		for (int i=0; i<size; i++) {
			if (data[i] == s) {
				return i;
			}
		}

		return -1;
	}

	// Part 2j
	public int lastIndexOf(String value) {
		int last_index = -1;
		for (int i=0; i<size; i++) {
			if (data[i] == value) {
				last_index = i;
			}
		}

		return last_index;
	}

	// Part 2k
	public String[] toArray() {
		String[] arr = new String[size];
		for (int i=0; i<size; i++) {
			arr[i] = data[i];
		}

		return arr;
	}

	// Part 2l
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

	// Part 1g
	public void tostring() {
		System.out.printf("\n[");
		if (size > 0) {
			System.out.printf(data[0]);
		}
		for (int i=1; i<size; i++) {
			System.out.printf(", ");
			System.out.printf(data[i]);
		}
		System.out.printf("]");
	}

	// Part 2a
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

	// Part 2d
	public boolean isEmpty() {
		return (size==0);
	}

	// Part 2e
	public boolean contains(String s) {
		for (String a : data) {
			if (a==s) {
				return true;
			}
		}

		return false;
	}
}