public abstract class Room {
    protected int roomNumber;
    protected double pricePerNight;

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

    public void setPricePNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
    // Add a method to set the price per night
    public void setPricePerNight(double price) {
        this.pricePerNight = price;
    }
}

class StandardRoom extends Room {
    public StandardRoom(int roomNumber, double pricePerNight) {
        super(roomNumber, pricePerNight);
    }

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
