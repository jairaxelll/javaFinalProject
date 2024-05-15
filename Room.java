public abstract class Room {
    protected int roomNumber;
    protected double pricePerNight;

    // Constructor
    // getters y setters
    public Room(int roomNumber, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
    }

    public abstract String getRoomType();

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }


    // este es el el setter que no funciona
    public void setPricePerNight(double price) {
        this.pricePerNight = price;
    }
}

// Clases hijas
class StandardRoom extends Room {
    public StandardRoom(int roomNumber, double pricePerNight) {
        super(roomNumber, pricePerNight);
    }

//  Obtener el tipo de habitacion
    @Override
    public String getRoomType() {
        return "Standard";
    }
}

class VIPRoom extends Room {
    public VIPRoom(int roomNumber, double pricePerNight) {
        super(roomNumber, pricePerNight);
    }

    @Override
    public String getRoomType() {
        return "VIP";
    }
}

class SuiteRoom extends Room {
    public SuiteRoom(int roomNumber, double pricePerNight) {
        super(roomNumber, pricePerNight);
    }

    @Override
    public String getRoomType() {
        return "Suite";
    }
}
