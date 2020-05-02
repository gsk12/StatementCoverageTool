package StatementCoverageTool;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/* similar to hw2 added linenumber */
class MethodTransformVisitor extends MethodVisitor implements Opcodes {

    String mName;
    int line;
    private String className;

    public MethodTransformVisitor(final MethodVisitor mv, String name, String className) {
        super(ASM5, mv);
        this.mName = name;
        this.className = className;
    }

    @Override
    public void visitLabel(Label label) {
        if (line == 0) return;
        String temp = className;
        mv.visitLdcInsn(temp);
        mv.visitLdcInsn(line);
        mv.visitMethodInsn(INVOKESTATIC, "StatementCoverageTool/CoverageCollector", "visitLine", "(Ljava/lang/String;I)V", false);
        super.visitLabel(label);
    }

    @Override
    public void visitLineNumber(int linenumber, Label start) {
        this.line = linenumber;
        if (linenumber == 0) return;
        String temp = className;
        mv.visitLdcInsn(temp);
        mv.visitLdcInsn(linenumber);
        mv.visitMethodInsn(INVOKESTATIC, "StatementCoverageTool/CoverageCollector", "visitLine", "(Ljava/lang/String;I)V", false);
        super.visitLineNumber(linenumber, start);
    }




}