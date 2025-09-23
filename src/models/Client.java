package models;

public class Client extends Person {
    private Advisor advisor;

    public Client() {
        super();
    }

    public Client(int id, String firstName, String lastName, String email, Advisor advisor) {
        super(id, firstName, lastName, email);
        this.advisor = advisor;
    }

    public Advisor getAdvisor() {
        return this.advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
}
