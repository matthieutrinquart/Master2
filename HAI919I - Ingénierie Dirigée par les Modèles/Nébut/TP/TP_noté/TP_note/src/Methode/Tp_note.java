package Methode;

import java.util.ArrayList;

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

import paa.Entity;
import paa.Field;
import paa.FieldDependantQuery;
import paa.PaAModel;
import paa.PaaPackage;
import paa.Query;
import paa.Repository;

import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Interface;
public class Tp_note {
	
	
	
	/*
	 * Instanciation des différents type primitive
	 * 
	 */
	private static UMLFactory factory=UMLFactory.eINSTANCE;
	private static PrimitiveType uml_int=factory.createPrimitiveType();
	private static PrimitiveType uml_string=factory.createPrimitiveType();
	private static PrimitiveType uml_float=factory.createPrimitiveType();
	static {
	uml_int.setName("int");
	uml_string.setName("string");
	uml_float.setName("float");
	}
	
	public static void main(String[] args) {
		
		
		/*
		 * Chargement du modelPaAModel
		 */
		PaAModel paamodel = chargerModelePaA("model/PaAModel.xmi");
		
		
		
		//Création du model avec les primitives Type.
		Model PrimitiveType = Question3_1();
		
		
		
		//Création du model UML via le modelPaAModel passé en paramètre.
		Model resultat = Question3_6(paamodel);
		
		
		
		//On met le model des types primitif dans le model résultat
		resultat.getPackagedElements().add(PrimitiveType);
		
		
	
		
		
		//écriture du shéma UML de modelPaAModel
		sauverModeleUML("resultat.uml", resultat);
		
		
		
	}
	
	
	/*
	 * Cette fonction permet de générer le model UML des types primitf
	 */
	public static Model Question3_1() {
		
		
		//création d'un Model
		Model model = factory.createModel();
		
		//On défini un nom a cet model
		model.setName("generatedModel");
		
		
		//On met dans ce model les diférents Types primitif
		model.getPackagedElements().add(uml_int);
		model.getPackagedElements().add(uml_string);
		model.getPackagedElements().add(uml_float);
		
		
		return model;
		
	}
	
	
	/*
	 * Cette méthode prend en paramètre un field et en retourne une méthode Property qui correspond à ce field
	 */
	public static Property Question3_2(Field field) {
		
		
		//création d'une propriété
		Property property = factory.createProperty();
		
		
		//le nom de l'attribut sera la meme que celui du field
		property.setName(field.getName());
		
		
		//Tout les attributs seront en privé
		property.setVisibility(VisibilityKind.get(VisibilityKind.PRIVATE));
		
		
		//On défini le type de l'attribut en fonction du type du field
		if(field.getType().getName().equals("int")) {
			property.setType(uml_int);	
		}else if(field.getType().getName().equals("String")) {
			property.setType(uml_string);	
		}else if(field.getType().getName().equals("float")) {
			property.setType(uml_float);
			
		}
		
		
		//On retourne l'attribut
		return property;
	}
	
	
	/*
	 * Cette fonction créer une classe en fonction d'une entité
	 */
	public static Class Question3_3(Entity entity) {
		
		
		//Création de la classe 
		Class classe = factory.createClass();
		
		
		//Le nom de la classe sera le même que l'entité passé en paramètre
		classe.setName(entity.getName());
		
		/*
		 * On parcourt tous les fields de l'entité et utilise la fonction précédente pour les convertirs en propriété 
		 * et ensuite le mettre dans la classe
		 */
		for(Field field : entity.getFields()) {
			Property property = Question3_2(field);
			classe.getOwnedAttributes().add(property);
			
		}
		
		//Création d'un commentaire sur la classe
		classe.createOwnedComment().setBody("@Entity");
		
		
		
		//retourne la classe
		return classe;
	}
	
	
	/*
	 * Cette fonction créer une opération en fonction d'un FieldDependantQuery passé en paramètre
	 */
	public static Operation Question3_4(FieldDependantQuery fielddependantquery) {
		
		
		//création d'une opération
		Operation operation = factory.createOperation();
		
		
		//On définie toutes les méthodes en public
		operation.setVisibility(VisibilityKind.get(VisibilityKind.PUBLIC));
		
		
		//Le nom de la méthode est le nom de type de la FieldDependantQuery concaténé au nom de la field 
		operation.setName(fielddependantquery.getType().getName() + fielddependantquery.getField().getName());
		
		
		
		//création d'un paramètre à mettre dans la méthode
		Parameter parameter = factory.createParameter();
		
		
		//Le nom de ce paramètre sera le nom du field 
		parameter.setName(fielddependantquery.getField().getName());
		
		
		
		//Le type du paramètre sera le même que celui du field 
		if(fielddependantquery.getField().getType().getName().equals("int")) {
			parameter.setType(uml_int);	
		}else if(fielddependantquery.getField().getType().getName().equals("String")) {
			parameter.setType(uml_string);	
		}else if(fielddependantquery.getField().getType().getName().equals("float")) {
			parameter.setType(uml_float);
			
		}
		
		//on met le paramètre dans la méthode
		operation.getOwnedParameters().add(parameter);
		
		
		//On retourne l'opération
		return operation;
	}
	
	
	/*
	 * Cette fonction créer une interface en fonction d'un Repository passé en paramètre
	 */
	
	public static Interface Question3_5(Repository repository) {
		
		//on créait une interface
		Interface inter = factory.createInterface();
		
		
		//On défini le nom de l'interface avec le nom du type de l'entité concaténé a "Repository"
		inter.setName(repository.getTypeEntity().getName() + "Repository");
		
		
		
		//on vérifie que la Query est un FieldDependantQuery
		for(Query q : repository.getQueries()) {
			if(q instanceof FieldDependantQuery) {
				
				//Si oui on utilise la fonction précédente pour créer l'opération en fonction de la queries
				Operation o = Question3_4((FieldDependantQuery)q);
				//On met un commentaire sur l'Operation
				o.createOwnedComment().setBody("@Repository");
				
				//On met l'opération dans l'interface
				inter.getOwnedOperations().add(o);
				
				
			}	
		}
		
		
		
		//On retourne l'interface
	return inter;
		

	}
	
	
	/*
	 * Cette fonction créer un Model en fonction d'un PaAModel passé en paramètre
	 */
	public static Model Question3_6(PaAModel paamodel) {
		
		//On créer un model
		Model model = factory.createModel();
		
		
		//On parcourt toutes les entités duy PaAModel 
		for(Entity e : paamodel.getEntities()) {
			
			//Pour chaque entité on utilise une fonction précédente pour convertir l'entité en Class
			Class c = Question3_3(e);
			//On met la classe dans le model
			model.getPackagedElements().add(c);
		
		}
		
		
		
		//On reparcourt tout les entités du PaAModel
		for(Entity e : paamodel.getEntities()) {
			
			//On vérifie que l'entité a bien une super entité
			if(e.getSuperEntity() !=null) {
				
				//si l'entité à bien une super entité alors on définie dans la classe la super Entité qu'elle a.
				Class current = (Class) model.getPackagedElement(e.getName());
				current.getSuperClasses().add((Class) model.getPackagedElement(e.getSuperEntity().getName()));
			}
		}
		
		//On parcourt tous les Repositorys.
		for(Repository r : paamodel.getRepositories()) {
			//On utilisé la question précédente pour convertir les Repositorys en Interface et on met l'interface dans le model.
			model.getPackagedElements().add(Question3_5(r));
		}
		
		
		
		
		//On retourne le model.
	return model;
		

	}
	
	public static void sauverModeleUML(String uri, EObject root) {
		   Resource resource = null;
		   try {
		      URI uriUri = URI.createURI(uri);
		      Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("uml", new XMIResourceFactoryImpl());
		      resource = (new ResourceSetImpl()).createResource(uriUri);
		      resource.getContents().add(root);
		      resource.save(null);
		   } catch (Exception e) {
		      System.err.println("ERREUR sauvegarde du modèle : "+e);
		      e.printStackTrace();
		   }
		}

		public static PaAModel chargerModelePaA(String uri) {
			Resource resource = null;
			   try {
			      URI uriUri = URI.createURI(uri);
			      Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
			      resource = (new ResourceSetImpl()).createResource(uriUri);
			      XMLResource.XMLMap xmlMap = new XMLMapImpl();
			      xmlMap.setNoNamespacePackage(PaaPackage.eINSTANCE);
			      java.util.Map options = new java.util.HashMap();
			      options.put(XMLResource.OPTION_XML_MAP, xmlMap);
			      resource.load(options);
			   }
			   catch(Exception e) {
			      System.err.println("ERREUR chargement du modèle : "+e);
			      e.printStackTrace();
			   }
			   return (PaAModel) resource.getContents().get(0);
			}
}
