package DeliveryService;

import DeliveryService.Interfaces.DeliveryInfo;

public class ShippingInfo implements DeliveryInfo {
    private final String address;

    public ShippingInfo(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
