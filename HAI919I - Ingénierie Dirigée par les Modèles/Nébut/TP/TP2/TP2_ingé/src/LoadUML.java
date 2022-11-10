

import java.util.List;

import org.eclipse.emf.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLMapImpl;

import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Class;

public class LoadUML {

	public static void main(String[] args) {

			
		
		//Instruction récupérant le modèle sous forme d'arbre à partir de la classe racine "Model"
		Model umlP = chargerModele("model/model.uml");
		
		
		String nomModele=  umlP.getName();
	
		
		System.out.println("Ton modèle se nomme: \""+nomModele+"\"");
		
		Package p1 = (Package)umlP.getPackagedElement("p1");
		Package p2 = (Package)umlP.getPackagedElement("p2");
		Class a = (Class) p2.getPackagedElement("A");
		Class b = (Class) p2.getPackagedElement("B");
		Operation o = (Operation) a.getOwnedOperations().get(0);
		Property attribut = a.getAllAttributes().get(0);
		
		System.out.println(p1.getName());
		System.out.println(p2.getName());
		System.out.println(a.getName());
		System.out.println(attribut.getName());
		moveClassToPackage(a , p1);
		
		
		ChangeattributPrivateToPublic(attribut);
		
		
		RemonterMethode(a, b,o);
		
		//Modifier le nom du modèle UML
		umlP.setName("NewModelName");
		System.out.println("\""+nomModele+"\" Changé ! Le modèle se nomme: \""+umlP.getName()+"\"");
		
		
		
		//Sauvegarder le modèle avec son nouveau nom
		sauverModele("model/changerNom.uml", umlP);
		
	}
	
	public static void moveClassToPackage(Class classes , Package cible) {
		if(classes != null && cible != null) {
			cible.getPackagedElements().add(classes);	
		}
		
	}
	
	public static void ChangeattributPrivateToPublic(Property p) {
		if(p != null && p.getVisibility().equals(VisibilityKind.get(VisibilityKind.PUBLIC))) {
		
			p.setVisibility(VisibilityKind.get(VisibilityKind.PRIVATE));
			
			UMLFactory uml= UMLFactory.eINSTANCE;
			Operation getter = uml.createOperation();
			
			getter.setName("Get" + p.getName());
			getter.setType(p.getType());
			
			Class c = p.getClass_();
			c.getOwnedOperations().add(getter);
			
			
			p.setVisibility(VisibilityKind.get(VisibilityKind.PRIVATE));
			
			Operation setter = uml.createOperation();
			
			Parameter e = uml.createParameter();
			e.setName(p.getName());
			e.setType(p.getType());
			
			
			
			setter.setName("Set" + p.getName());
			setter.getOwnedParameters().add(e);
			
			c.getOwnedOperations().add(setter);
		}
		
		
	}
	
	
	public static void RemonterMethode(Class classOrigine, Class SuperClass,Operation cible) {
		
		List<Class> SuperClasses = classOrigine.getSuperClasses();
		boolean echoue = false;
		for(Class c : SuperClasses) {
			if(c.equals(SuperClass)) {
				for(Operation o : c.getOwnedOperations()) {
					if(o.equals(cible)) {
						echoue = true;
					}
				}
				if(!echoue) {
					SuperClass.getOwnedOperations().add(cible);
				}
			}
		}
		if(echoue) {
			System.out.println("La remonté à echoué");
		}
		
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
