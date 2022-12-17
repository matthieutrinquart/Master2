package org.example;



import com.savoirtech.logging.slf4j.json.logger.Logger;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.declaration.*;
import spoon.reflect.reference.CtTypeReference;

public class ClassLoggin extends AbstractProcessor<CtClass> {


    @Override
    public void process(CtClass ctClass) {

        if(ctClass.getSimpleName().equals("Applicationrepository")){
            CtTypeReference<Logger> typelogger = getFactory().createCtTypeReference(Logger.class);


            CtField write = getFactory().createField();
            write.setType(typelogger);
            write.addModifier(ModifierKind.STATIC);
            write.addModifier(ModifierKind.PRIVATE);
            write.setSimpleName("write");

            CtField read = getFactory().createField();
            read.setType(typelogger);
            read.addModifier(ModifierKind.STATIC);
            read.addModifier(ModifierKind.PRIVATE);
            read.setSimpleName("read");


            CtField price = getFactory().createField();
            price.setType(typelogger);
            price.addModifier(ModifierKind.STATIC);
            price.addModifier(ModifierKind.PRIVATE);
            price.setSimpleName("price");

            String expressionwrite = "com.savoirtech.logging.slf4j.json.LoggerFactory.getLogger(\"Write\")";
            CtCodeSnippetExpression loggerExpression = getFactory().Code().createCodeSnippetExpression(expressionwrite);
            write.setDefaultExpression(loggerExpression);



            expressionwrite = "com.savoirtech.logging.slf4j.json.LoggerFactory.getLogger(\"Read\")";
            loggerExpression = getFactory().Code().createCodeSnippetExpression(expressionwrite);
            read.setDefaultExpression(loggerExpression);




            expressionwrite = "com.savoirtech.logging.slf4j.json.LoggerFactory.getLogger(\"Price\")";
            loggerExpression = getFactory().Code().createCodeSnippetExpression(expressionwrite);
            price.setDefaultExpression(loggerExpression);


            ctClass.addFieldAtTop(write);
            ctClass.addFieldAtTop(read);
            ctClass.addFieldAtTop(price);


            for(Object methodobject: ctClass.getAllMethods()){
                CtMethod method = (CtMethod) methodobject;
                String lignes ="";
                CtCodeSnippetStatement snippet = getFactory().Core().createCodeSnippetStatement();
                if(ReadMethode(method)){
                    lignes = lignes + "if(this.user != null){";
                    lignes = lignes +"read.trace().message("+'"'+method.getSimpleName()+'"'+")"+'\n';
                    lignes = lignes + " .field(\"User\",this.user.getNom())"+'\n';
                    lignes = lignes + " .field(\"UserID\", this.user.getId())"+'\n';
                    lignes = lignes + ".log(); "+'\n';
                    lignes = lignes + "}";

                }
                if(WriteMethode(method)){
                    lignes = lignes + "if(this.user != null){";
                    lignes = lignes +"write.trace().message("+'"'+method.getSimpleName()+'"'+")"+'\n';
                    lignes = lignes + " .field(\"User\",this.user.getNom())"+'\n';
                    lignes = lignes + " .field(\"UserID\", this.user.getId())"+'\n';
                    lignes = lignes + ".log(); "+'\n';
                    lignes = lignes + "}";
                }
                if(PriceMethode(method)){
                    lignes = lignes + "if(this.user != null && productRepository.getProductById(id) != null){";
                    lignes = lignes +"price.trace().message("+'"'+method.getSimpleName()+'"'+")"+'\n';
                    lignes = lignes + " .field(\"User\",this.user.getNom())"+'\n';
                    lignes = lignes + " .field(\"UserID\", this.user.getId())"+'\n';
                    lignes = lignes + " .field(\"Product\", productRepository.getProductById(id).getNom())"+'\n';
                    lignes = lignes + " .field(\"ProductID\", productRepository.getProductById(id).getId())"+'\n';
                    lignes = lignes + " .field(\"Price\", productRepository.getProductById(id).getPrix())"+'\n';
                    lignes = lignes + ".log(); "+'\n';
                    lignes = lignes + "}";
                }

                snippet.setValue(lignes);


                // Inserts the snippet at the beginning of the method body.
                if (method.getBody() != null) {
                    method.getBody().insertBegin(snippet);
                }

            }
        }
    }


    public boolean ReadMethode(CtMethod method){
        return method.getSimpleName().equals("connexion") ||
                method.getSimpleName().equals("getProducts")||
                method.getSimpleName().equals("getProductById")||
                method.getSimpleName().equals("getUser")
                ;
    }


    public boolean WriteMethode(CtMethod method){
        return method.getSimpleName().equals("register") ||
                method.getSimpleName().equals("addProduct")||
                method.getSimpleName().equals("removeProduct")||
                method.getSimpleName().equals("editProduct")
                ;
    }

    public boolean PriceMethode(CtMethod method){
        return method.getSimpleName().equals("getProductById");
    }
}
