import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// greedy, sorting, difficulty 1900
public class Q3bLorry {
    public static void main(String[] args) {
        String[] vehicleTypes = {"Kayak", "Catamaran"};
        Scanner scanner = new Scanner(System.in);
        int choiceNum = scanner.nextInt();
        int truckVolume = scanner.nextInt();
        Truck truck = new Truck(truckVolume);
        for (int i = 0; i < choiceNum; i++) {
            int type = scanner.nextInt();
            int capacity = scanner.nextInt();
            try {
                VehicleCreator vehicleCreator = (VehicleCreator) Class.forName(vehicleTypes[type - 1] + "Creator").newInstance();
                Vehicle vehicle = vehicleCreator.createVehicle(capacity);
                vehicle.setLabel(i + 1);
                truck.add(vehicle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        truck.printOptimalList();
    }
}

interface VehicleCreator {
    public Vehicle createVehicle(int capacity);
}

class Vehicle implements Comparable<Vehicle> {
    private int volume;
    private int capacity;
    private int label;

    public Vehicle(int volume, int capacity) {
        this.volume = volume;
        this.capacity = capacity;
    }

    public int getVolume() {
        return volume;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    @Override
    public int compareTo(Vehicle o) {
        return o.getCapacity() - this.getCapacity();
    }

//    @Override
//    public String toString() {
//        return this.getClass().getName() + "{volume :" + getVolume() + ", capacity :" + getCapacity() + "}";
//    }
}

class Kayak extends Vehicle {
    public Kayak(int volume, int capacity) {
        super(volume, capacity);
    }
}

class KayakCreator implements VehicleCreator {
    @Override
    public Vehicle createVehicle(int capacity) {
        return new Kayak(1, capacity);
    }
}

class Catamaran extends Vehicle {
    public Catamaran(int volume, int capacity) {
        super(volume, capacity);
    }
}

class CatamaranCreator implements VehicleCreator {
    @Override
    public Vehicle createVehicle(int capacity) {
        return new Catamaran(2, capacity);
    }
}

class Truck {
    private long capacity;
    private ArrayList<Vehicle> list = new ArrayList<>();
    private ArrayList<Vehicle> cList = new ArrayList<>();

    public Truck(int capacity) {
        this.capacity = capacity;
    }

    public void add(Vehicle vehicle) {
        switch (vehicle.getVolume()) {
            case 1: {
                list.add(vehicle);
            }
            break;
            case 2: {
                cList.add(vehicle);
            }
            break;
        }

    }

    public long getCapacity() {
        return capacity;
    }

    public void printOptimalList() {
        int space = 0;
        int indexK = 0;
        int indexC = 0;
        long total = 0;
        Collections.sort(list); // you may implement your own sorting
        Collections.sort(cList);// you may implement your own sorting
        StringBuilder stringBuilder = new StringBuilder();
        while (space < capacity) {
            int c;
            int k;
            int k1;
            if (space + 1 == capacity) {
                if (indexK < list.size()) {
                    total += list.get(indexK).getCapacity();
                    stringBuilder.append(list.get(indexK).getLabel());
                }
                break;
            }
            if (cList.size() > indexC) {
                c = cList.get(indexC).getCapacity();
            } else {
                c = 0;
            }
            if (list.size() > indexK) {
                k = list.get(indexK).getCapacity();
            } else {
                k = 0;
            }
            if (list.size() > indexK + 1) {
                k1 = list.get(indexK + 1).getCapacity();
            } else {
                k1 = 0;
            }
            if (c == 0 && k == 0) { // you miss this !!!!
                // exception, there are space left and choices arrays are empty.
                break;
            }
            if (c > k + k1) {
                stringBuilder.append(cList.get(indexC).getLabel() + " ");
                space += 2;
                total += cList.get(indexC).getCapacity();
                indexC++;
            } else {
                stringBuilder.append(list.get(indexK).getLabel() + " ");
                space += 1;
                total += list.get(indexK).getCapacity();
                indexK++;
            }
        }
        System.out.println(total);
        System.out.println(stringBuilder.toString());
    }
}