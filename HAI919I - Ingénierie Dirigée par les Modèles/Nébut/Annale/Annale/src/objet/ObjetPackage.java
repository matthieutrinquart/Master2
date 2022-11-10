/**
 */
package objet;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see objet.ObjetFactory
 * @model kind="package"
 * @generated
 */
public interface ObjetPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "objet";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "objet";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "objet";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ObjetPackage eINSTANCE = objet.impl.ObjetPackageImpl.init();

	/**
	 * The meta object id for the '{@link objet.impl.GammePersonnalisableImpl <em>Gamme Personnalisable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see objet.impl.GammePersonnalisableImpl
	 * @see objet.impl.ObjetPackageImpl#getGammePersonnalisable()
	 * @generated
	 */
	int GAMME_PERSONNALISABLE = 0;

	/**
	 * The feature id for the '<em><b>Objets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAMME_PERSONNALISABLE__OBJETS = 0;

	/**
	 * The feature id for the '<em><b>Elementsdepersonnalisation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAMME_PERSONNALISABLE__ELEMENTSDEPERSONNALISATION = 1;

	/**
	 * The feature id for the '<em><b>Elementsvariables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAMME_PERSONNALISABLE__ELEMENTSVARIABLES = 2;

	/**
	 * The number of structural features of the '<em>Gamme Personnalisable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAMME_PERSONNALISABLE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Gamme Personnalisable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GAMME_PERSONNALISABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link objet.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see objet.impl.NamedElementImpl
	 * @see objet.impl.ObjetPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 4;

	/**
	 * The feature id for the '<em><b>Nom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NOM = 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link objet.impl.ObjetImpl <em>Objet</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see objet.impl.ObjetImpl
	 * @see objet.impl.ObjetPackageImpl#getObjet()
	 * @generated
	 */
	int OBJET = 1;

	/**
	 * The feature id for the '<em><b>Nom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJET__NOM = NAMED_ELEMENT__NOM;

	/**
	 * The number of structural features of the '<em>Objet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJET_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Objet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJET_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link objet.impl.ElementDePersonnalisationImpl <em>Element De Personnalisation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see objet.impl.ElementDePersonnalisationImpl
	 * @see objet.impl.ObjetPackageImpl#getElementDePersonnalisation()
	 * @generated
	 */
	int ELEMENT_DE_PERSONNALISATION = 2;

	/**
	 * The feature id for the '<em><b>Nom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_DE_PERSONNALISATION__NOM = NAMED_ELEMENT__NOM;

	/**
	 * The number of structural features of the '<em>Element De Personnalisation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_DE_PERSONNALISATION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Element De Personnalisation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_DE_PERSONNALISATION_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link objet.impl.CaracteristiqueVariableImpl <em>Caracteristique Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see objet.impl.CaracteristiqueVariableImpl
	 * @see objet.impl.ObjetPackageImpl#getCaracteristiqueVariable()
	 * @generated
	 */
	int CARACTERISTIQUE_VARIABLE = 3;

	/**
	 * The feature id for the '<em><b>Nom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARACTERISTIQUE_VARIABLE__NOM = NAMED_ELEMENT__NOM;

	/**
	 * The number of structural features of the '<em>Caracteristique Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARACTERISTIQUE_VARIABLE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Caracteristique Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARACTERISTIQUE_VARIABLE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link objet.GammePersonnalisable <em>Gamme Personnalisable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gamme Personnalisable</em>'.
	 * @see objet.GammePersonnalisable
	 * @generated
	 */
	EClass getGammePersonnalisable();

	/**
	 * Returns the meta object for the containment reference list '{@link objet.GammePersonnalisable#getObjets <em>Objets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Objets</em>'.
	 * @see objet.GammePersonnalisable#getObjets()
	 * @see #getGammePersonnalisable()
	 * @generated
	 */
	EReference getGammePersonnalisable_Objets();

	/**
	 * Returns the meta object for the containment reference list '{@link objet.GammePersonnalisable#getElementsdepersonnalisation <em>Elementsdepersonnalisation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elementsdepersonnalisation</em>'.
	 * @see objet.GammePersonnalisable#getElementsdepersonnalisation()
	 * @see #getGammePersonnalisable()
	 * @generated
	 */
	EReference getGammePersonnalisable_Elementsdepersonnalisation();

	/**
	 * Returns the meta object for the containment reference list '{@link objet.GammePersonnalisable#getElementsvariables <em>Elementsvariables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elementsvariables</em>'.
	 * @see objet.GammePersonnalisable#getElementsvariables()
	 * @see #getGammePersonnalisable()
	 * @generated
	 */
	EReference getGammePersonnalisable_Elementsvariables();

	/**
	 * Returns the meta object for class '{@link objet.Objet <em>Objet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Objet</em>'.
	 * @see objet.Objet
	 * @generated
	 */
	EClass getObjet();

	/**
	 * Returns the meta object for class '{@link objet.ElementDePersonnalisation <em>Element De Personnalisation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element De Personnalisation</em>'.
	 * @see objet.ElementDePersonnalisation
	 * @generated
	 */
	EClass getElementDePersonnalisation();

	/**
	 * Returns the meta object for class '{@link objet.CaracteristiqueVariable <em>Caracteristique Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Caracteristique Variable</em>'.
	 * @see objet.CaracteristiqueVariable
	 * @generated
	 */
	EClass getCaracteristiqueVariable();

	/**
	 * Returns the meta object for class '{@link objet.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see objet.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link objet.NamedElement#getNom <em>Nom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nom</em>'.
	 * @see objet.NamedElement#getNom()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Nom();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ObjetFactory getObjetFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link objet.impl.GammePersonnalisableImpl <em>Gamme Personnalisable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see objet.impl.GammePersonnalisableImpl
		 * @see objet.impl.ObjetPackageImpl#getGammePersonnalisable()
		 * @generated
		 */
		EClass GAMME_PERSONNALISABLE = eINSTANCE.getGammePersonnalisable();

		/**
		 * The meta object literal for the '<em><b>Objets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GAMME_PERSONNALISABLE__OBJETS = eINSTANCE.getGammePersonnalisable_Objets();

		/**
		 * The meta object literal for the '<em><b>Elementsdepersonnalisation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GAMME_PERSONNALISABLE__ELEMENTSDEPERSONNALISATION = eINSTANCE.getGammePersonnalisable_Elementsdepersonnalisation();

		/**
		 * The meta object literal for the '<em><b>Elementsvariables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GAMME_PERSONNALISABLE__ELEMENTSVARIABLES = eINSTANCE.getGammePersonnalisable_Elementsvariables();

		/**
		 * The meta object literal for the '{@link objet.impl.ObjetImpl <em>Objet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see objet.impl.ObjetImpl
		 * @see objet.impl.ObjetPackageImpl#getObjet()
		 * @generated
		 */
		EClass OBJET = eINSTANCE.getObjet();

		/**
		 * The meta object literal for the '{@link objet.impl.ElementDePersonnalisationImpl <em>Element De Personnalisation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see objet.impl.ElementDePersonnalisationImpl
		 * @see objet.impl.ObjetPackageImpl#getElementDePersonnalisation()
		 * @generated
		 */
		EClass ELEMENT_DE_PERSONNALISATION = eINSTANCE.getElementDePersonnalisation();

		/**
		 * The meta object literal for the '{@link objet.impl.CaracteristiqueVariableImpl <em>Caracteristique Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see objet.impl.CaracteristiqueVariableImpl
		 * @see objet.impl.ObjetPackageImpl#getCaracteristiqueVariable()
		 * @generated
		 */
		EClass CARACTERISTIQUE_VARIABLE = eINSTANCE.getCaracteristiqueVariable();

		/**
		 * The meta object literal for the '{@link objet.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see objet.impl.NamedElementImpl
		 * @see objet.impl.ObjetPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Nom</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NOM = eINSTANCE.getNamedElement_Nom();

	}

} //ObjetPackage
