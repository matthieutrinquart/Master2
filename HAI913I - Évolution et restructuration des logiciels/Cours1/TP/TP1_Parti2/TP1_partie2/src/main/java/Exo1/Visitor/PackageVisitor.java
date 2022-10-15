package Exo1.Visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.ArrayList;
import java.util.List;

public class PackageVisitor extends ASTVisitor {

    List<PackageDeclaration> packag = new ArrayList<>();

    @Override
    public boolean visit(PackageDeclaration node) {
        packag.add(node);
        return super.visit(node);
    }

    public int sizeList() {
        return packag.size();
    }

    public List<PackageDeclaration> getPackage(){
        return packag;
    }
}
