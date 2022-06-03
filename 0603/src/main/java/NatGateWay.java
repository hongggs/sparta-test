public class NatGateWay extends GateWay{
    private Subnet subnet;

    public NatGateWay(Subnet subnet) {
        this.subnet = subnet;
    }

    @Override
    public void send(String msg) {
        System.out.println(msg);
    }

    public Subnet getSubnet() {
        return subnet;
    }

    public void setSubnet(Subnet subnet) {
        this.subnet = subnet;
    }
}
