(defclass Produit() 
(
    (nameproduit :initarg :nameproduit
     :accessor getNameProduit
    )

    (
   prix :initarg :prix
     :accessor getprix
    )
 )


 (defmethod toString(nil)
 
 (format "ICI")
 )
 
 


 
 )

 (setf p1(make-instance 'Produit :prix 10 :nameproduit "la grande vadrouille"))


(write (getNameProduit p1))
(write (getprix p1))


(toString p1)