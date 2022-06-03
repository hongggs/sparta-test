public class Subnet {
    private int id;
    private String ip;
    private String region;
    private RouteTable routeTable;

    public Subnet(int id, String ip, String region) {
        this.id = id;
        this.ip = ip;
        this.region = region;
    }

    public void transfer(String msg){
        if (routeTable.getGateWay() instanceof InternetGateWay) {
            routeTable.getGateWay().send(msg);
        } else {
            if (routeTable.getSubnets().contains(this)) {
                routeTable.getGateWay().send(msg);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public RouteTable getRouteTable() {
        return routeTable;
    }

    public void setRouteTable(RouteTable routeTable) {
        this.routeTable = routeTable;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
