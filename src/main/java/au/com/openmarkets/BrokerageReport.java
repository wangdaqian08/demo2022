package au.com.openmarkets;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class BrokerageReport {


    public void readCsvFile(String csvString) {

        if (csvString.trim().length() == 0) {
            throw new RuntimeException("empty string for csv file");
        }
        String[] csvLines = csvString.split("\\n");

        List<String> csvLineList = new ArrayList<>(Arrays.asList(csvLines).subList(1, csvLines.length));

        ValueConvertor valueConvertor = new ValueConvertor();


        List<ReportDetail> reportDetails = csvLineList.stream().map(valueConvertor::convert)
                .collect(Collectors.toList());
        Map<String, Double> sumValue = reportDetails.stream()
                .collect(Collectors.groupingBy(ReportDetail::getAdviser, Collectors.summingDouble(ReportDetail::getValue)));

        Map<String, List<ReportDetail>> minMaxRecords = reportDetails.stream()
                .collect(Collectors.toMap(ReportDetail::getAdviser, reportDetail -> Arrays.asList(reportDetail, reportDetail),
                        (r1, r2) -> Arrays.asList(
                                (r1.get(0).getTimestamp() > r2.get(0).getTimestamp() ? r2 : r1).get(0),
                                (r1.get(1).getTimestamp() < r2.get(1).getTimestamp() ? r2 : r1).get(1))));

        printReport(sumValue, minMaxRecords);


    }

    private void printReport(Map<String, Double> groupedSumValue, Map<String, List<ReportDetail>> reportDetails) {
        for (Map.Entry<String, Double> sumEntry : groupedSumValue.entrySet()) {
            String adviser = sumEntry.getKey();
            Double sumValue = sumEntry.getValue();
            StringBuilder valueString = formatValue(sumValue);

            System.out.println(adviser);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

            String dateString = reportDetails.entrySet().stream().filter(entry -> entry.getKey().equals(adviser))
                    .flatMap(entry -> entry.getValue().stream().map(reportDetail -> sdf.format(new Date(reportDetail.getTimestamp())).replaceAll("\\.","")))
                    .collect(Collectors.joining(" -> "));
            System.out.println(this.padding(10) + valueString);
            System.out.println(dateString);
            System.out.println();
        }
    }

    private StringBuilder formatValue(Double sumValue) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter2 = new Formatter(sb, Locale.US);
        formatter2.format("%(,.2f", sumValue);
        return sb;
    }

    public String padding(int size) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < size; i++) {
            string.append(" ");
        }
        return string.toString();
    }

//  ABC
//
//  5,712.00
//
//          12 Oct 2020 -> 15 Oct 2020
//
//
//
//    CBA
//
//    653.00
//
//            16 Oct 2020 -> 16 Oct 2020
//

}
