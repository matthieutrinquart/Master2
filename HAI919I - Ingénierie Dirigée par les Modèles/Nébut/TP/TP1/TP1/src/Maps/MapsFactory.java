/**
 */
package Maps;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see Maps.MapsPackage
 * @generated
 */
public interface MapsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MapsFactory eINSTANCE = Maps.impl.MapsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Map</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Map</em>'.
	 * @generated
	 */
	Map createMap();

	/**
	 * Returns a new object of class '<em>Road</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Road</em>'.
	 * @generated
	 */
	Road createRoad();

	/**
	 * Returns a new object of class '<em>Pedestrian</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pedestrian</em>'.
	 * @generated
	 */
	Pedestrian createPedestrian();

	/**
	 * Returns a new object of class '<em>Street</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Street</em>'.
	 * @generated
	 */
	Street createStreet();

	/**
	 * Returns a new object of class '<em>Public Space</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Public Space</em>'.
	 * @generated
	 */
	PublicSpace createPublicSpace();

	/**
	 * Returns a new object of class '<em>Square</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Square</em>'.
	 * @generated
	 */
	Square createSquare();

	/**
	 * Returns a new object of class '<em>Garden</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Garden</em>'.
	 * @generated
	 */
	Garden createGarden();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	MapsPackage getMapsPackage();

} //MapsFactory
