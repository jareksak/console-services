package artur.sanko.service;

import java.util.HashMap;
import java.util.Map;

public class BillingService {

    private static BillingService billingService;

    private BillingService() {
    }

    public static BillingService getInstance() {

        if (billingService == null) {

            billingService = new BillingService();
        }

        return billingService;
    }

    private Map<String, Integer> statistic = new HashMap<>();

    public void addBill(String service) {

        statistic.merge(service, 1, Integer::sum);
    }

    public String buildBillingStatistic() {

        StringBuilder builder = new StringBuilder();
        builder.append("Сервис - количество вызовов:\n");
        statistic.forEach((k, v) -> builder.append(k + " - " + v + "\n"));

        return builder.toString();
    }

}
