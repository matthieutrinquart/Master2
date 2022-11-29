package Q4_1;

class ListeTableau implements ListeTableauInterface{
@Override
public boolean add(Object o) {return true;}
@Override
public boolean isEmpty() {return true;}
@Override
public Object get(int i) {return null;}
private void secretLT(){}
public static void staticLT() {}
int nbLT;
}
class ListeChainee implements ListeChaineeInterface{
@Override
public boolean add(Object o) {return true;}
@Override
public boolean isEmpty() {return true;}
@Override
public Object get(int i) {return null;}
@Override
public Object peek() {return null;}
@Override
public Object poll() {return null;}
private void secretLC(){}
}
class QueueDoubleEntree implements QueueDoubleEntreeInterface{
@Override
public boolean add(Object o) {return true;}
@Override
public boolean isEmpty() {return true;}
@Override
public Object peek() {return null;}
@Override
public Object poll() {return null;}
private void secretQDE(){}
}
class QueueAvecPriorite extends QueueAvecPrioriteInterface{
private void secretQAP(){}
}
