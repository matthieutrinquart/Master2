
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
import org.eclipse.uml2.*;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;

public class TP3 {
	public static void main(String[] args) {
		Model umlP = chargerModele("model/model.uml");
		
		
		String nomModele=  umlP.getName();
		
		Class classcible =  (Class)umlP.getPackagedElement("EtatA");
		ReturnStateMachine(classcible);
	}
	
	public static List<StateMachine> ReturnStateMachine(Class classe) {
		
		List<Behavior> k = classe.getOwnedBehaviors();
	//	System.out.println(k.get(0).getSta);
		
		
		return null;
		
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
