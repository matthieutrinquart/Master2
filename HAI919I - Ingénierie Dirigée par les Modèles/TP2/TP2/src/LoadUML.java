

import org.eclipse.emf.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLMapImpl;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
public class LoadUML {
	public static void main(String[] args) {

			
		
		//Instruction récupérant le modèle sous forme d'arbre à partir de la classe racine "Model"
		Model umlP = chargerModele("model/InputMove.uml");
		
		
		String nomModele=  umlP.getName();
		
		System.out.println("Ton modèle se nomme: \""+nomModele+"\"");
		
		
		//Modifier le nom du modèle UML
		umlP.setName("NewModelName");
		System.out.println("\""+nomModele+"\" Changé ! Le modèle se nomme: \""+umlP.getName()+"\"");
		
		
		Package packagecible = (Package)umlP.getPackagedElement("P2");
		Class classcible =  (Class)packagecible.getPackagedElement("A");
		
		Package Dest = (Package)umlP.getPackagedElement("P1");
		
		reunisage(classcible,Dest);
		
		
		//Sauvegarder le modèle avec son nouveau nom
		sauverModele("model/changerNom.uml", umlP);
		
	}
	
	public static void reunisage(Class UMLcible, Package dest) {
		
		dest.getPackagedElements().add(UMLcible);
		
		
		
	}
	
	public static void replacePublicToPrivate(Property attribut) {
		if(attribut.getVisibility().equals(VisibilityKind.PUBLIC)) {
			attribut.setVisibility(VisibilityKind.PRIVATE_LITERAL);
			Class mere = (Class)attribut.getOwner();
			UMLFactory facto = UMLFactory.eINSTANCE;
			Operation getter = facto.createOperation();
			getter.		des		dest.getPackagedElements().add(UMLcible);
		dest.getPackagedElements().add(UMLcible);
t.getPackagedElements().add(UMLcible);

			
			
			
			//getter.setName("geter");
		}else {
			System.out.println("Il est en privé connard!");
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
