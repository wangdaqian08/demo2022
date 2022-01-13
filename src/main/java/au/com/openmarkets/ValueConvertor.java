package au.com.openmarkets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ValueConvertor {
    public static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";

    public ReportDetail convert(String line) {

        String[] lineDetails = line.split(",");
        // hard code 5 columns
        if (lineDetails.length != 5) {
            throw new RuntimeException("invalid line" + Arrays.toString(lineDetails));
        }


        ReportDetail reportDetail = new ReportDetail();
        reportDetail.setTxnId(lineDetails[0]);
        Date sydneyTime = utcToSydneyTime(lineDetails[1]);
        reportDetail.setTimestamp(sydneyTime.getTime());
        reportDetail.setAccount(Integer.valueOf(lineDetails[2]));
        reportDetail.setAdviser(lineDetails[3]);
        reportDetail.setValue(Double.valueOf(lineDetails[4]));

        return reportDetail;
    }


    private Date utcToSydneyTime(String lineDetail) {

        lineDetail = aroundTimeString(lineDetail);
        SimpleDateFormat sdfOriginal = new SimpleDateFormat(FORMAT);
        sdfOriginal.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date1 = null;
        try {
            date1 = sdfOriginal.parse(lineDetail);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        return calendar.getTime();
    }

    private String aroundTimeString(String lineDetail) {
        return lineDetail.substring(0, 22) + "Z";
    }

}
