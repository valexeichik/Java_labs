public class LedLamp extends Lamp {
    final static Integer CONST2 = 40;
    private Integer numberOfLeds;

    LedLamp(Double power, String manufacturer, Integer numberOfLeds) {
        this.power = power;
        this.manufacturer = manufacturer;
        this.numberOfLeds = numberOfLeds;
    }
    Integer getNumberOfLeds() { return numberOfLeds; }
    long countCost() {
        return Math.round(power / CONST2 * numberOfLeds);
    }

    public String toString() { return super.toString() + " - " + numberOfLeds + " (LED)";}
}
