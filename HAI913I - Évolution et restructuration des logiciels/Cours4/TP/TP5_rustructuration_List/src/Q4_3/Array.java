package Q4_3;

class ListeTableau implements ListInterface{
	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Object get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	private void secretLT(){}
	public static void staticLT() {}
	int nbLT;
}
class ListeChainee implements ListInterface,QueueInterface{
	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Object get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object poll() {
		// TODO Auto-generated method stub
		return null;
	}
	private void secretLC(){}
}
class QueueDoubleEntree implements QueueInterface{
	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object poll() {
		// TODO Auto-generated method stub
		return null;
	}
	private void secretQDE(){}
}
class QueueAvecPriorite implements QueueInterface{
	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object poll() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object comparator() {return null;}
	private void secretQAP(){}
}
