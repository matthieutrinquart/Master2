/**
 */
package objet.impl;

import objet.CaracteristiqueVariable;
import objet.ElementDePersonnalisation;
import objet.GammePersonnalisable;
import objet.NamedElement;
import objet.Objet;
import objet.ObjetFactory;
import objet.ObjetPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ObjetPackageImpl extends EPackageImpl implements ObjetPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gammePersonnalisableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass objetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementDePersonnalisationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass caracteristiqueVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see objet.ObjetPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ObjetPackageImpl() {
		super(eNS_URI, ObjetFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link ObjetPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ObjetPackage init() {
		if (isInited) return (ObjetPackage)EPackage.Registry.INSTANCE.getEPackage(ObjetPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredObjetPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ObjetPackageImpl theObjetPackage = registeredObjetPackage instanceof ObjetPackageImpl ? (ObjetPackageImpl)registeredObjetPackage : new ObjetPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theObjetPackage.createPackageContents();

		// Initialize created meta-data
		theObjetPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theObjetPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ObjetPackage.eNS_URI, theObjetPackage);
		return theObjetPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGammePersonnalisable() {
		return gammePersonnalisableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGammePersonnalisable_Objets() {
		return (EReference)gammePersonnalisableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGammePersonnalisable_Elementsdepersonnalisation() {
		return (EReference)gammePersonnalisableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGammePersonnalisable_Elementsvariables() {
		return (EReference)gammePersonnalisableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObjet() {
		return objetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementDePersonnalisation() {
		return elementDePersonnalisationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCaracteristiqueVariable() {
		return caracteristiqueVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Nom() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjetFactory getObjetFactory() {
		return (ObjetFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		gammePersonnalisableEClass = createEClass(GAMME_PERSONNALISABLE);
		createEReference(gammePersonnalisableEClass, GAMME_PERSONNALISABLE__OBJETS);
		createEReference(gammePersonnalisableEClass, GAMME_PERSONNALISABLE__ELEMENTSDEPERSONNALISATION);
		createEReference(gammePersonnalisableEClass, GAMME_PERSONNALISABLE__ELEMENTSVARIABLES);

		objetEClass = createEClass(OBJET);

		elementDePersonnalisationEClass = createEClass(ELEMENT_DE_PERSONNALISATION);

		caracteristiqueVariableEClass = createEClass(CARACTERISTIQUE_VARIABLE);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NOM);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		objetEClass.getESuperTypes().add(this.getNamedElement());
		elementDePersonnalisationEClass.getESuperTypes().add(this.getNamedElement());
		caracteristiqueVariableEClass.getESuperTypes().add(this.getNamedElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(gammePersonnalisableEClass, GammePersonnalisable.class, "GammePersonnalisable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGammePersonnalisable_Objets(), this.getObjet(), null, "objets", null, 0, -1, GammePersonnalisable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGammePersonnalisable_Elementsdepersonnalisation(), this.getElementDePersonnalisation(), null, "elementsdepersonnalisation", null, 0, -1, GammePersonnalisable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGammePersonnalisable_Elementsvariables(), this.getCaracteristiqueVariable(), null, "elementsvariables", null, 0, -1, GammePersonnalisable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(objetEClass, Objet.class, "Objet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(elementDePersonnalisationEClass, ElementDePersonnalisation.class, "ElementDePersonnalisation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(caracteristiqueVariableEClass, CaracteristiqueVariable.class, "CaracteristiqueVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Nom(), ecorePackage.getEString(), "nom", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ObjetPackageImpl
