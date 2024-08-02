public abstract class Lamp {
    protected String manufacturer;
    protected Double power;
    Double getPower() { return power; }
    String getManufacturer() { return manufacturer; }
    abstract long countCost();
    public String toString() { return manufacturer + " - " + power; }
}
