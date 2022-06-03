public abstract class GateWay {
    private int id;

    abstract public void send(String msg);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
