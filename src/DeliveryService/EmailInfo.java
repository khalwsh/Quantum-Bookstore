package DeliveryService;

import DeliveryService.Interfaces.DeliveryInfo;

public class EmailInfo implements DeliveryInfo {
    private final String email;
    public EmailInfo(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
