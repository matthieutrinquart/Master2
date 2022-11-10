package org.example.Visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;

public class MethodeVisitor extends ASTVisitor {
    ArrayList<MethodDeclaration> methodes = new ArrayList<>();

    @Override
    public boolean visit(MethodDeclaration node) {
        methodes.add(node);
        return super.visit(node);

    }

    public ArrayList<MethodDeclaration> getMethodes() {
        return methodes;

    }
}
