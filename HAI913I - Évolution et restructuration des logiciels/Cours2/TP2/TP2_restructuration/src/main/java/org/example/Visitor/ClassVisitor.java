package org.example.Visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.ArrayList;
import java.util.List;

public class ClassVisitor extends ASTVisitor {

    List<TypeDeclaration> classe = new ArrayList<>();

    @Override
    public boolean visit(TypeDeclaration node) {
        classe.add(node);
        return super.visit(node);
    }

    public List<TypeDeclaration> getClasses(){
        return classe;
    }
}
