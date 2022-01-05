package common.visitors;

import common.nodes.*;

public interface NodeVisitor {
    void visit(RootNode toVisit);
    int visit(OperatorNode toVisit);
    void visit(WhileNode toVisit);
    void visit(SetNode toVisit);
    boolean visit(LogicalOperatorNode toVisit);
    void visit(BlockNode toVisit);
    void visit(ConditionalNode toVisit);
    boolean visit(RelationalOperatorNode toVisit);
    void visit(InputNode toVisit);
    void visit(PrintNode toVisit);
    Integer visit(VariableNode toVisit);
    String visit(InputVariableNode toVisit);
    int visit(NumberNode toVisit);
    boolean visit(BooleanNode toVisit);
}
