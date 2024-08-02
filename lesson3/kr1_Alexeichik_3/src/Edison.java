import java.util.*;

public class Edison {
    static <T extends Lamp> List<T> getLampsInAscendingOrderOfCost(List<T> lamps) {
        return lamps.stream().sorted(new Comparator<T>() {
            @Override
            public int compare(T l1, T l2) {
                return Long.compare(l1.countCost(), l2.countCost());
            }
        }).toList();
    }

    static <T extends Lamp> List<T> getLampsInDescendingOrderOfCostToPowerRatio(List<T> lamps) {
        return lamps.stream().sorted(new Comparator<T>() {
            @Override
            public int compare(T l1, T l2) {
                return Double.compare(l2.countCost() / l2.getPower(), l1.countCost() / l1.getPower());
            }
        }).toList();
    }

    static <T extends Lamp> ArrayList<String> getManufacturersStartingWithCList(List<T> lamps) {
        ArrayList<String> manufacturers = new ArrayList<>();
        for (String s: getManufacturerList(lamps)) {
            if (s.toLowerCase().charAt(0) == 'c') manufacturers.add(s);
        }
        return manufacturers;
    }

    static <T extends Lamp> ArrayList<String> getManufacturerList(List<T> lamps) {
        List<String> manufacturers = new ArrayList<>();
        for (Lamp lamp: lamps) manufacturers.add(lamp.getManufacturer());
        return new ArrayList<>(new HashSet<>(manufacturers));
    }

    static <T extends Lamp> Double getAverageCost(List<T> lamps, String manufacturer) {
        double sum = 0;
        int count = 0;
        for (T lamp : lamps) {
            if (lamp.getManufacturer().equals(manufacturer)) {
                sum += lamp.countCost();
                count++;
            }
        }
        return sum / count;
    }
}