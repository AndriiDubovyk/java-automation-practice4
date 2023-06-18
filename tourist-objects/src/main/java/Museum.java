public class Museum extends TouristObject {
    private double ticketPrice;

    public Museum(String name, String country, String location, String description, double ticketPrice) {
        super(name, country, location, description);
        this.ticketPrice = ticketPrice;
    }

    public static Museum getFromUserInput() {
        return TouristObjectUserInputReader.getMuseum();
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return name + ". " +
                country+ ", " + location +
                ". Ticket price($): " + ticketPrice + ". " +
                description;
    }
}