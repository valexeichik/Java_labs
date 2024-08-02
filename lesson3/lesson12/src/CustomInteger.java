public class CustomInteger implements Element{
    Integer value;
    @Override
    public void accept(Visitor visitor) {
        visitor.visitCustomInteger(this);
    }
}
