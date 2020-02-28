package assignment4;

public class Request implements Comparable<Request> {

    int requestOrder;
    int requestTaxiId;
    String originCompanyId;
    String destinationCompanyId;

    public Request(int requestOrder, int requestTaxiId, String originCompanyId, String destinationCompanyId) {

        this.requestOrder = requestOrder;
        this.requestTaxiId = requestTaxiId;
        this.originCompanyId = originCompanyId;
        this.destinationCompanyId = destinationCompanyId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestOrder=" + requestOrder +
                ", requestTaxiId=" + requestTaxiId +
                ", originCompanyId='" + originCompanyId + '\'' +
                ", destinationCompanyId='" + destinationCompanyId + '\'' +
                '}';
    }

    @Override
    public int compareTo(Request req) {
        if (this.requestOrder > req.requestOrder) {

            return 1;
        } else if (this.requestOrder < req.requestOrder) {

            return -1;
        } else {
            return 0;
        }
    }
}
