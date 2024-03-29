public interface NodeVisitor {
    void visit(RootNode toVisit);
    long visit(OperatorNode toVisit);
    void visit(WhileNode toVisit);
    void visit(SetNode toVisit);
    boolean visit(LogicalOperatorNode toVisit);
    void visit(BlockNode toVisit);
    void visit(ConditionalNode toVisit);
    boolean visit(RelationalOperatorNode toVisit);
    void visit(InputNode toVisit);
    void visit(PrintNode toVisit);
    long visit(VariableNode toVisit);
    String visit(InputVariableNode toVisit);
    long visit(NumberNode toVisit);
    boolean visit(BooleanNode toVisit);
}
