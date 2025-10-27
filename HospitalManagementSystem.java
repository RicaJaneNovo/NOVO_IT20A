package hospitalmanagementsystem;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;



public class HospitalManagementSystem {

      public static void main(String[] args) {
        Hospital hospital = new Hospital();

        hospital.addPatient("Anna", 3);
        hospital.addPatient("Bob", 1);
        hospital.addPatient("Carla", 2);
        hospital.addPatient("Dave", 1);

        hospital.showArrivalOrder();
        hospital.treatPatients();
    }
}

class Patient {
    String name;
    int severity;

    Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }

    public String toString() {
        return name + " (Severity: " + severity + ")";
    }
}

class Hospital {
    Queue<Patient> arrivalQueue;
    PriorityQueue<Patient> priorityQueue;

    Hospital() {
        arrivalQueue = new LinkedList<>();

        priorityQueue = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.severity, p2.severity));
    }

    void addPatient(String name, int severity) {
        Patient patient = new Patient(name, severity);
        arrivalQueue.add(patient);
        priorityQueue.add(patient);
    }

    void showArrivalOrder() {
        System.out.println("=== Arrival Order ===");
        System.out.println("Arrival Order: " + arrivalQueue);
        System.out.println();
    }

    void treatPatients() {
        System.out.println("=== Treatment Order ===");
        while (!priorityQueue.isEmpty()) {
            Patient p = priorityQueue.poll();
            System.out.println("Treating: " + p);
        }
    }
}
