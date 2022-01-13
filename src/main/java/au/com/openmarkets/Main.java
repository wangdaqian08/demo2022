package au.com.openmarkets;



public class Main {

    private final static String CSV_STRING = "txnid,timestamp,account,adviser,value\n" +
            "1,2020-10-12T09:41:09.327899600Z,1234,ABC,125\n" +
            "2,2020-10-11T13:41:35.042901300Z,1111,ABC,5543\n" +
            "3,2020-10-15T09:41:47.965773400Z,1234,ABC,44\n" +
            "4,2020-10-16T09:41:56.194791Z,9999,CBA,653";

    public static void main(String[] args) {
        BrokerageReport brokerageReport = new BrokerageReport();
        brokerageReport.readCsvFile(CSV_STRING);
    }
}
