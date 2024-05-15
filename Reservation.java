import java.util.Date;

public class Reservation {
    private String id;
    private Customer customer;
    private Room room;
    private Date startDate;
    private Date endDate;

    // Constructor
    // getters y setters
    public Reservation(String id, Customer customer, Room room, Date startDate, Date endDate) {
        this.id = id; // Unique identifier for each reservation
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // Metodo para obtener los detalles de la reserva
    public String getReservationDetails() {
        return "Reservation ID: " + id + "\nCustomer: " + customer.getName() +
               "\nRoom Number: " + room.getRoomNumber() +
               "\nRoom Type: " + room.getRoomType() +
               "\nStart Date: " + startDate +
               "\nEnd Date: " + endDate;
    }
}
