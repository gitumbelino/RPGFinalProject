
package Enums;

public enum VendorType {
    COFFEE_SHOP("Need a coding boost? I've got the strongest coffee in tech!"),
    TECH_STORE("Latest tech tools and gadgets! Perfect for your next interview!"),
    BOOTCAMP_VENDOR("Get your learning resources here! Special discount for career changers!");

    private final String greeting;

    VendorType(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }
}