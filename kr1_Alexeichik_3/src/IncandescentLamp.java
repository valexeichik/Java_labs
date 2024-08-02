public class IncandescentLamp extends Lamp {
    final static Integer CONST1 = 25;
    private Integer operatingTime;

    IncandescentLamp(Double power, String manufacturer, Integer operatingTime) {
        this.power = power;
        this.manufacturer = manufacturer;
        this.operatingTime = operatingTime;
    }
    Integer getOperatingTime() { return operatingTime; }
    long countCost() {
        return Math.round(power * CONST1 * operatingTime);
    }

    public String toString() { return super.toString() + " - " + operatingTime + " (INC)";}
}
