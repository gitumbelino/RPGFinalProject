public class JobOffer extends HeroItem {
    private String companyName;
    private int salary;
    private String position;

    public JobOffer(String companyName, String position, int salary) {
        super("Job Offer from " + companyName, 0);  // Can't be bought
        this.companyName = companyName;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public void showDetails() {
        System.out.println("*** JOB OFFER ***");
        System.out.println("Company: " + companyName);
        System.out.println("Position: " + position);
        System.out.println("Salary: $" + salary + "/year");
    }
}