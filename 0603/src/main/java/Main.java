public class Main {
    public static void main(String[] args) {
        //서브넷 생성
        Subnet subnet_a = new Subnet(1,"172.31.0.0/20","ap-northeast-2a");
        Subnet subnet_b = new Subnet(2,"172.31.16.0/20","ap-northeast-2b");
        Subnet subnet_c = new Subnet(3,"172.31.32.0/20","ap-northeast-2c");
        Subnet subnet_d = new Subnet(4,"172.31.48.0/20","ap-northeast-2d");

        //인터넷 게이트웨이에 연결된 서브넷을 퍼블릭 아닌 경우는, 프라이빗
        //인터넷 게이트웨이
        GateWay internetGateWay = new InternetGateWay();

        //NAT 게이트 웨이
        //NAT 게이트 웨이의 서브넷은 public 이어야함 고로 subnet_d는 불가
        GateWay natGateWay = new NatGateWay(subnet_a); // success
        //GateWay natGateWay = new NatGateWay(subnet_d); // infinity loop error

        // 라우팅 테이블
        RouteTable publicRouteTable = new RouteTable();
        RouteTable privateRouteTable = new RouteTable();

        //서브넷 연결
        publicRouteTable.setGateWay(internetGateWay);
        publicRouteTable.addSubnet(subnet_a);
        publicRouteTable.addSubnet(subnet_b);
        publicRouteTable.addSubnet(subnet_c);

        //사브넷 연결
        privateRouteTable.setGateWay(natGateWay);
        privateRouteTable.addSubnet(subnet_d);


        subnet_a.transfer("a send message!");
        subnet_d.transfer("d send message!");
    }
}