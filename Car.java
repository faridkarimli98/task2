import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Car {
    private int id;
    private String make;
    private String model;
    private int yearOfManufacture;
    private String color;
    private double price;
    private String registrationNumber;

    // Constructor
    public Car(int id, String make, String model, int yearOfManufacture, String color, double price, String registrationNumber) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
        this.price = price;
        this.registrationNumber = registrationNumber;
    }

    // Getters
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public double getPrice() {
        return price;
    }

    // Override toString method to easily print Car information
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", registrationNumber='" + registrationNumber + '\'' +
                '}';
    }

    public static void main(String[] args) {
        // Create an array of Car objects
        Car[] cars = {
            new Car(1, "Toyota", "Camry", 2018, "Blue", 20000, "ABC123"),
            new Car(2, "Honda", "Civic", 2017, "Red", 18000, "XYZ456"),
            new Car(3, "Ford", "Focus", 2020, "Black", 22000, "DEF789")
            // Add more cars as needed
        };

        // Save cars of a given brand to a file
        saveCarsByBrand("Toyota", cars);

        // Save cars of a given model that have been in use for more than n years
        saveCarsByModelAndYearsUsed("Civic", 4, cars);

        // Save cars of a given year of manufacture with a price higher than a specified one
        saveCarsByYearAndPrice(2018, 19000, cars);
    }

    // Method to save cars of a given brand to a file
    public static void saveCarsByBrand(String brand, Car[] cars) {
        List<Car> filteredCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getMake().equalsIgnoreCase(brand)) {
                filteredCars.add(car);
            }
        }
        saveCarsToFile("Brand_" + brand + ".txt", filteredCars);
    }

    // Method to save cars of a given model that have been in use for more than n years
    public static void saveCarsByModelAndYearsUsed(String model, int years, Car[] cars) {
        List<Car> filteredCars = new ArrayList<>();
        int currentYear = 2023; // You may want to calculate the current year dynamically
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model) &&
                currentYear - car.getYearOfManufacture() > years) {
                filteredCars.add(car);
            }
        }
        saveCarsToFile("Model_" + model + "_YearsUsed_" + years + ".txt", filteredCars);
    }

    // Method to save cars of a given year of manufacture with a price higher than a specified one
    public static void saveCarsByYearAndPrice(int year, double minPrice, Car[] cars) {
        List<Car> filteredCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getYearOfManufacture() == year && car.getPrice() > minPrice) {
                filteredCars.add(car);
            }
        }
        saveCarsToFile("Year_" + year + "_Price_" + minPrice + ".txt", filteredCars);
    }

    // Method to save a list of cars to a file
    public static void saveCarsToFile(String filename, List<Car> cars) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Car car : cars) {
                writer.println(car.toString());
            }
            System.out.println("Saved " + cars.size() + " cars to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving cars to file: " + e.getMessage());
        }
    }
}
