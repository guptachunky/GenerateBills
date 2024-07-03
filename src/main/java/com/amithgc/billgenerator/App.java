package com.amithgc.billgenerator;

import com.amithgc.billgenerator.pojo.PDFFIllData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        //just change time here
        // in case rate is updated change it in Json File
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.APRIL, 6);

        String templateName = "data/base-pdf/chunky.pdf";
        String jsonInputFile = "./data/json/Chunky.json";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        JsonReader reader = new JsonReader();
        List<PDFFIllData> data = reader.read(jsonInputFile);
        if (data != null) {
            Random random = new Random();
            String[] times = {"am", "pm"};

            PDFGenerator generator = new PDFGenerator();
            for (int i = 0; i < 10; i++) {
                String myDate = dateFormat.format(cal.getTime());
                String monthName = cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
                String outputPdfFile = "./data/output/chunky/" + cal.get(Calendar.DAY_OF_MONTH) + "-" + monthName + ".pdf";
                data.get(4).setText(myDate);
                data.get(5).setText(random.nextInt(12) + ":" + random.nextInt(60)
                        + " " + times[random.nextInt(2)]);
                generator.generate(data, templateName, outputPdfFile);
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 7);
            }
        }
    }
}