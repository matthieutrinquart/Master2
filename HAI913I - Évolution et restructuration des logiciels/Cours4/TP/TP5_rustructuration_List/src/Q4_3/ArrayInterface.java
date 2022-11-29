package Q4_3;

interface ArrayInterface {
	 public boolean add(Object o) ;
	 public boolean isEmpty() ;
}

interface ListInterface extends ArrayInterface {
	public Object get(int i);
}

interface QueueInterface extends ArrayInterface {
	public Object peek();
	public Object poll();
}