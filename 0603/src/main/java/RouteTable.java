import java.util.ArrayList;
import java.util.List;

public class RouteTable {
    private int id;
    private GateWay gateWay;
    private List<Subnet> subnets = new ArrayList<>();

    public void addSubnet(Subnet subnet) {
        subnets.add(subnet);
        route(subnet);
    }

    public void route(Subnet subnet) {
        subnet.setRouteTable(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GateWay getGateWay() {
        return gateWay;
    }

    public void setGateWay(GateWay gateWay) {
        this.gateWay = gateWay;
    }

    public List<Subnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<Subnet> subnets) {
        this.subnets = subnets;
    }
}
