/**
 */
package Maps;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Road</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Maps.Road#getName <em>Name</em>}</li>
 *   <li>{@link Maps.Road#getLength <em>Length</em>}</li>
 *   <li>{@link Maps.Road#getMeet <em>Meet</em>}</li>
 *   <li>{@link Maps.Road#getBorder <em>Border</em>}</li>
 * </ul>
 *
 * @see Maps.MapsPackage#getRoad()
 * @model
 * @generated
 */
public interface Road extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Maps.MapsPackage#getRoad_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Maps.Road#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(int)
	 * @see Maps.MapsPackage#getRoad_Length()
	 * @model
	 * @generated
	 */
	int getLength();

	/**
	 * Sets the value of the '{@link Maps.Road#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(int value);

	/**
	 * Returns the value of the '<em><b>Meet</b></em>' reference list.
	 * The list contents are of type {@link Maps.Road}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Meet</em>' reference list.
	 * @see Maps.MapsPackage#getRoad_Meet()
	 * @model
	 * @generated
	 */
	EList<Road> getMeet();

	/**
	 * Returns the value of the '<em><b>Border</b></em>' reference list.
	 * The list contents are of type {@link Maps.PublicSpace}.
	 * It is bidirectional and its opposite is '{@link Maps.PublicSpace#getBoderedBy <em>Bodered By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Border</em>' reference list.
	 * @see Maps.MapsPackage#getRoad_Border()
	 * @see Maps.PublicSpace#getBoderedBy
	 * @model opposite="boderedBy"
	 * @generated
	 */
	EList<PublicSpace> getBorder();

} // Road
