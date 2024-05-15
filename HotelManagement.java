import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;
import java.text.ParseException;



public class HotelManagement {

    private List<Room> rooms;
    private List<Customer> customers;
    private List<Reservation> reservations;

    public HotelManagement() {
        rooms = new ArrayList<>();
        customers = new ArrayList<>();
        reservations = new ArrayList<>();
        initRooms();
    }

    private void initRooms() {
        rooms.add(new StandardRoom(101, 100));
        rooms.add(new VIPRoom(102, 150));
        rooms.add(new SuiteRoom(103, 200));
        // Añade más habitaciones según sea necesario
    }

    public void showMenu() {
        String[] options = {"Manage Rooms", "Manage Customers", "Manage Reservations", "Exit"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(null, "Select an option", "Hotel Management System",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (choice) {
                case 0:
                    manageRooms();
                    break;
                case 1:
                    manageCustomers();
                    break;
                case 2:
                    manageReservations();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option");
                    break;
            }
        }
    }

    private void manageRooms() {
        String[] options = {"Add Room", "Update Room", "Delete Room", "Check Availability", "Back"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(null, "Manage Rooms", "Room Management",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (choice) {
                case 0:
                    addRoom();
                    break;
                case 1:
                    updateRoom();
                    break;
                case 2:
                    deleteRoom();
                    break;
                case 3:
                    checkRoomAvailability();
                    break;
                case 4:
                    return; // Return to main menu
            }
        }
    }

    private void addRoom() {
        String type = JOptionPane.showInputDialog("Enter room type (Standard/VIP/Suite):");
        int roomNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter room number:"));
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter price per night:"));
        Room room = null;
        switch (type.toLowerCase()) {
            case "standard":
                room = new StandardRoom(roomNumber, price);
                break;
            case "vip":
                room = new VIPRoom(roomNumber, price);
                break;
            case "suite":
                room = new SuiteRoom(roomNumber, price);
                break;
        }
        if (room != null) {
            rooms.add(room);
            JOptionPane.showMessageDialog(null, "Room added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid room type entered!");
        }
    }

    private void updateRoom() {
        int roomNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter the room number to update:"));
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter new price per night:"));
                Room.setPricePNight(newPrice); // Update the price
                Room.setPricePerNight(newPrice); // Update the price
                JOptionPane.showMessageDialog(null, "Room updated successfully!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Room not found!");
    }
    

    private void deleteRoom() {
        int roomNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter the room number to delete:"));
        if (rooms.removeIf(room -> room.getRoomNumber() == roomNumber)) {
            JOptionPane.showMessageDialog(null, "Room deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Room not found!");
        }
    }

    private void checkRoomAvailability() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date checkDate;
        try {
            checkDate = sdf.parse(JOptionPane.showInputDialog("Enter date (dd/mm/yyyy) to check availability:"));
            boolean foundAvailable = false;
            for (Room room : rooms) {
                boolean isAvailable = true;
                for (Reservation res : reservations) {
                    if (res.getRoom().getRoomNumber() == room.getRoomNumber() &&
                        !checkDate.before(res.getStartDate()) && !checkDate.after(res.getEndDate())) {
                        isAvailable = false;
                        break;
                    }
                }
                if (isAvailable) {
                    JOptionPane.showMessageDialog(null, "Room " + room.getRoomNumber() + " is available on " + sdf.format(checkDate));
                    foundAvailable = true;
                }
            }
            if (!foundAvailable) {
                JOptionPane.showMessageDialog(null, "No available rooms found for the given date.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid date format!");
        }
    }

    private void manageCustomers() {
        String[] options = {"Add Customer", "Update Customer", "Delete Customer", "View Customer", "Back"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(null, "Manage Customers", "Customer Management",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (choice) {
                case 0:
                    addCustomer();
                    break;
                case 1:
                    updateCustomer();
                    break;
                case 2:
                    deleteCustomer();
                    break;
                case 3:
                    viewCustomer();
                    break;
                case 4:
                    return; // Volver al menú principal
            }
        }
    }
    
    private void addCustomer() {
        String name = JOptionPane.showInputDialog("Enter customer's name:");
        String id = JOptionPane.showInputDialog("Enter customer's ID:");
        String contact = JOptionPane.showInputDialog("Enter customer's contact info:");
        Customer customer = new Customer(name, id, contact);
        customers.add(customer);
        JOptionPane.showMessageDialog(null, "Customer added successfully!");
    }
    
    private void updateCustomer() {
        String id = JOptionPane.showInputDialog("Enter customer's ID to update:");
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                String newName = JOptionPane.showInputDialog("Enter new name:", customer.getName());
                String newContact = JOptionPane.showInputDialog("Enter new contact info:", customer.getContactInfo());
                customer.setName(newName);
                customer.setContactInfo(newContact);
                JOptionPane.showMessageDialog(null, "Customer updated successfully!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Customer not found!");
    }
    
    private void deleteCustomer() {
        String id = JOptionPane.showInputDialog("Enter customer's ID to delete:");
        if (customers.removeIf(customer -> customer.getId().equals(id))) {
            JOptionPane.showMessageDialog(null, "Customer deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Customer not found!");
        }
    }
    
    private void viewCustomer() {
        String id = JOptionPane.showInputDialog("Enter customer's ID to view:");
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                String info = "Name: " + customer.getName() + "\nID: " + customer.getId() + "\nContact: " + customer.getContactInfo();
                JOptionPane.showMessageDialog(null, info);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Customer not found!");
    }
    

    private void manageReservations() {
        String[] options = {"Make Reservation", "Update Reservation", "Cancel Reservation", "View Reservations", "Back"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(null, "Manage Reservations", "Reservation Management",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (choice) {
                case 0:
                    makeReservation();
                    break;
                case 1:
                    updateReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    return; // Volver al menú principal
            }
        }
    }
    
    public void makeReservation() {
        String customerId = JOptionPane.showInputDialog("Enter customer's ID:");
        Customer customer = customers.stream()
            .filter(c -> c.getId().equals(customerId))
            .findFirst()
            .orElse(null);
        
        if (customer == null) {
            JOptionPane.showMessageDialog(null, "Customer not found!");
            return;
        }
    
        String roomType = JOptionPane.showInputDialog("Enter room type (Standard/VIP/Suite):");
        List<Room> availableRooms = rooms.stream()
            .filter(room -> room.getRoomType().equalsIgnoreCase(roomType))
            .collect(Collectors.toList());
        
        if (availableRooms.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No available rooms of type " + roomType);
            return;
        }
    
        Room selectedRoom = availableRooms.get(0);  // Or let user select
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null, endDate = null;
        try {
            startDate = sdf.parse(JOptionPane.showInputDialog("Enter start date (dd/mm/yyyy):"));
            endDate = sdf.parse(JOptionPane.showInputDialog("Enter end date (dd/mm/yyyy):"));
            if (isRoomAvailable(selectedRoom, startDate, endDate)) {
                // Proceed with reservation creation
            } else {
                JOptionPane.showMessageDialog(null, "Selected room is not available for the given dates.");
                return;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid date format!");
            return;
        }

        // Assuming you have a way to generate or define a unique ID for reservations
        String reservationId = UUID.randomUUID().toString();
        Reservation reservation = new Reservation(reservationId, customer, selectedRoom, startDate, endDate);
        reservations.add(reservation);
        JOptionPane.showMessageDialog(null, "Reservation made successfully!");
    }    

    public boolean isRoomAvailable(Room room, Date startDate, Date endDate) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().equals(room) && 
                !(endDate.before(reservation.getStartDate()) || startDate.after(reservation.getEndDate()))) {
                return false;  // The room is not available as it overlaps with an existing reservation
            }
        }
        return true;  // The room is available
    }

    private void updateReservation() {
        String reservationId = JOptionPane.showInputDialog("Enter reservation ID:");
        Reservation reservation = reservations.stream()
            .filter(r -> r.getId().equals(reservationId))
            .findFirst()
            .orElse(null);
    
        if (reservation == null) {
            JOptionPane.showMessageDialog(null, "Reservation not found!");
            return;
        }
    
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date newStartDate = sdf.parse(JOptionPane.showInputDialog("Enter new start date (dd/mm/yyyy):"));
            Date newEndDate = sdf.parse(JOptionPane.showInputDialog("Enter new end date (dd/mm/yyyy):"));
            reservation.setStartDate(newStartDate);
            reservation.setEndDate(newEndDate);
            JOptionPane.showMessageDialog(null, "Reservation updated successfully!");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid date format!");
        }
    }
    
    
    private void cancelReservation() {
        String reservationId = JOptionPane.showInputDialog("Enter reservation ID:");
        boolean removed = reservations.removeIf(r -> r.getId().equals(reservationId));
        if (removed) {
            JOptionPane.showMessageDialog(null, "Reservation cancelled successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Reservation not found!");
        }
    }
    
    
    private void viewReservations() {
        StringBuilder sb = new StringBuilder();
        for (Reservation r : reservations) {
            sb.append(r.getReservationDetails()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
    

    public static void main(String[] args) {
        HotelManagement hm = new HotelManagement();
        hm.showMenu();
    }
}


