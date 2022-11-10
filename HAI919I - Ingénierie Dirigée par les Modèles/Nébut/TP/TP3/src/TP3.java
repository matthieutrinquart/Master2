
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLMapImpl;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.*;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.uml2.uml.VisibilityKind;

public class TP3 {
	public static void main(String[] args) {
		Model umlP = chargerModele("model/model.uml");
		
		
		String nomModele=  umlP.getName();
		
		Class classcible =  (Class)umlP.getPackagedElement("EtatA");
		List<StateMachine> t = ReturnStateMachine(classcible);
		for(StateMachine p :t) {
			System.out.println(IsOneRegion(p));
			for(State state : ReturnEtats(p)) {
				System.out.println(state.getName());
			}
			for(Operation operation : getoperation(p)) {
				System.out.println(operation.getName());
			}
		}
		
		PatronState(classcible);
		sauverModele("model/model.uml", umlP);
	}
	public static void sauverModele(String uri, EObject root) {
		   Resource resource = null;
		   try {
		      URI uriUri = URI.createURI(uri);
		      Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		      resource = (new ResourceSetImpl()).createResource(uriUri);
		      resource.getContents().add(root);
		      resource.save(null);
		   } catch (Exception e) {
		      System.err.println("ERREUR sauvegarde du modèle : "+e);
		      e.printStackTrace();
		   }
		}
	
	
	public static void PatronStateAllClass(String modelName) {
		
		Model umlP = chargerModele("model/" + modelName);
		
		ArrayList<PackageableElement> elements = new ArrayList<>();
		
		for(PackageableElement pe : umlP.getPackagedElements()) {
			elements.add(pe);
		}
		
		for(PackageableElement p : elements) {
			Class aClass = (Class) p;
			for(StateMachine m : ReturnStateMachine(aClass)) {
				PatronState(m);
			}
			
		}
		
		
		
		}
	public static void PatronState(Class classe) {
		
		UMLFactory uml=UMLFactory.eINSTANCE;
		Class c = uml.createClass();
		c.setName("Etat_" + classe.getName());
		c.setIsAbstract(true);
		c.setPackage(classe.getPackage());
		
		
		Association a = uml.createAssociation();
		a.setName("Association1");
		a.getEndTypes().add(c);
		a.getEndTypes().add(classe);
		
		
		
		List<StateMachine> p = ReturnStateMachine(classe);
		
		for(StateMachine s : p) {
			for(Operation o:getoperation(s)) {
				Operation operation = uml.createOperation();
				operation.setName(o.getName());
				operation.setIsAbstract(true);
				c.getOwnedOperations().add(operation);
			}
		}
		
		for(StateMachine s : p) {
			for(State o: ReturnEtats(s)) {
				Class state = uml.createClass();
				state.getSuperClasses().add(state);
				state.setName(o.getName());
				state.setPackage(c.getPackage());
				
				for(Operation i : c.getOperations()) {
					Operation operation = uml.createOperation();
					operation.setName(i.getName());
					state.getOwnedOperations().add(i);
				}
				
				
				
			}
		}
		
		
		
		
		
		
		
		
	}
	
	public static List<StateMachine> ReturnStateMachine(Class classe) {
		
		
		List<StateMachine> m = new ArrayList<>();
		List<Behavior> k = classe.getOwnedBehaviors();
		for(Behavior i :k) {
			if(i instanceof StateMachine) {
				m.add((StateMachine) i);
			}
		}
		return m;
		
	}
	
	public static List<Operation> getoperation(StateMachine statemachine) {
		
		
		List<Operation> operations = new ArrayList<>();
		for(Region r : statemachine.getRegions()) {
			for( Transition v: r.getTransitions()) {
				for(Trigger t : v.getTriggers()) {
					if(t.getEvent() instanceof CallEvent) {
						CallEvent c = (CallEvent)t.getEvent();
						operations.add(c.getOperation());	
					}
				}
				
			}
		}
		return operations;
		
	}
	
	public static List<State> ReturnEtats(StateMachine statemachine) {
		
		
		List<State> states = new ArrayList<>();
		for(Region r : statemachine.getRegions()) {
			for( Vertex v: r.getSubvertices()) {
				if(v instanceof State) {
					states.add((State)v);
				}
				
			}
		}
		return states;
		
	}
	public static boolean IsOneRegion(StateMachine statemachine) {
		
		List<Region> regions = statemachine.getRegions();
		
		return regions.size() ==1;
		
		
		
	}
	public static Model chargerModele(String uri) {
		   Resource resource = null;
		   EPackage pack=UMLPackage.eINSTANCE;
		   try {
		      URI uriUri = URI.createURI(uri);
		      Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("uml", new XMIResourceFactoryImpl());
		      resource = (new ResourceSetImpl()).createResource(uriUri);
		      XMLResource.XMLMap xmlMap = new XMLMapImpl();
		      xmlMap.setNoNamespacePackage(pack);
		      java.util.Map options = new java.util.HashMap();
		      options.put(XMLResource.OPTION_XML_MAP, xmlMap);
		      resource.load(options);
		   }
		   catch(Exception e) {
		      System.err.println("ERREUR chargement du modèle : "+e);
		      e.printStackTrace();
		   }
		   return (Model) resource.getContents().get(0);
		}
	
	
}
