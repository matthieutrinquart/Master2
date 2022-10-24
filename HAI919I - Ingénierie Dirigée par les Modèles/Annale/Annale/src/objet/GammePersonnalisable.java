/**
 */
package objet;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gamme Personnalisable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link objet.GammePersonnalisable#getObjets <em>Objets</em>}</li>
 *   <li>{@link objet.GammePersonnalisable#getElementsdepersonnalisation <em>Elementsdepersonnalisation</em>}</li>
 *   <li>{@link objet.GammePersonnalisable#getElementsvariables <em>Elementsvariables</em>}</li>
 * </ul>
 *
 * @see objet.ObjetPackage#getGammePersonnalisable()
 * @model
 * @generated
 */
public interface GammePersonnalisable extends EObject {
	/**
	 * Returns the value of the '<em><b>Objets</b></em>' containment reference list.
	 * The list contents are of type {@link objet.Objet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objets</em>' containment reference list.
	 * @see objet.ObjetPackage#getGammePersonnalisable_Objets()
	 * @model containment="true"
	 * @generated
	 */
	EList<Objet> getObjets();

	/**
	 * Returns the value of the '<em><b>Elementsdepersonnalisation</b></em>' containment reference list.
	 * The list contents are of type {@link objet.ElementDePersonnalisation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elementsdepersonnalisation</em>' containment reference list.
	 * @see objet.ObjetPackage#getGammePersonnalisable_Elementsdepersonnalisation()
	 * @model containment="true"
	 * @generated
	 */
	EList<ElementDePersonnalisation> getElementsdepersonnalisation();

	/**
	 * Returns the value of the '<em><b>Elementsvariables</b></em>' containment reference list.
	 * The list contents are of type {@link objet.CaracteristiqueVariable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elementsvariables</em>' containment reference list.
	 * @see objet.ObjetPackage#getGammePersonnalisable_Elementsvariables()
	 * @model containment="true"
	 * @generated
	 */
	EList<CaracteristiqueVariable> getElementsvariables();

} // GammePersonnalisable
