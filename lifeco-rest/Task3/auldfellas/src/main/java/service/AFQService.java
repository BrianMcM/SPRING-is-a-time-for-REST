//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package service;

import service.core.AbstractQuotationService;
import service.core.ClientInfo;
import service.core.Quotation;

public class AFQService extends AbstractQuotationService {
    public static final String PREFIX = "AF";
    public static final String COMPANY = "Auld Fellas Ltd.";

    public AFQService() {
    }

    public Quotation generateQuotation(ClientInfo info) {
        double price = this.generatePrice(600.0, 600);
//        int discount = false;
        int discount;
        if (info.gender == 'M') {
            discount = 30;
            if (info.age > 50) {
                discount += 10;
            }

            if (info.age > 60) {
                discount += 10;
            }
        } else {
            discount = -20;
        }

        return new Quotation(COMPANY, this.generateReference(PREFIX), price * (double)(100 - discount) / 100.0);
    }
}
