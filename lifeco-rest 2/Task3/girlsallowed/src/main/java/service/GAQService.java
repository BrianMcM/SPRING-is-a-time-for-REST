//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package service;

import service.core.AbstractQuotationService;
import service.core.ClientInfo;
import service.core.Quotation;

public class GAQService extends AbstractQuotationService {
    public static final String PREFIX = "GA";
    public static final String COMPANY = "Girls Allowed Inc.";

    public GAQService() {
    }

    public Quotation generateQuotation(ClientInfo info) {
        double price = this.generatePrice(600.0, 400);
        int discount = info.gender == 'F' ? 50 : -30;
        discount += this.bmiDiscount(info);
        discount += this.medicalWeighting(info);
        return new Quotation("Girls Allowed Inc.", this.generateReference("GA"), price * (double)(100 - discount) / 100.0);
    }

    public int bmiDiscount(ClientInfo info) {
        double bmi = this.bmi(info.weight, info.height);
        if (bmi < 18.5) {
            return 20;
        } else if (bmi < 24.5) {
            return 10;
        } else if (bmi < 30.0) {
            return 0;
        } else {
            return bmi < 40.0 ? -20 : 40;
        }
    }

    public int medicalWeighting(ClientInfo info) {
        int weighting = 0;
        if (info.medicalIssues) {
            weighting -= 20;
        }

        if (info.smoker) {
            weighting -= 40;
        }

        return weighting;
    }
}
