package com.jap.sales;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SalesDataAnalyzer {
   public List<SalesRecord> readFile(String fileName) throws NumberFormatException,IOException{
       List<SalesRecord> list=new ArrayList<>();
           FileReader fileReader = new FileReader(fileName);
           BufferedReader bufferedReader = new BufferedReader(fileReader);
           String line;
           bufferedReader.readLine();
           while ((line = bufferedReader.readLine()) != null) {
               SalesRecord salesRecord = new SalesRecord();
               String[] split = line.split(",");
               salesRecord.setDate(split[0]);
               salesRecord.setCustomer_id(Integer.parseInt(split[1]));
               salesRecord.setProduct_category(Integer.parseInt(split[2]));
               salesRecord.setPayment_method(split[3]);
               salesRecord.setAmount(Double.parseDouble(split[4]));
               salesRecord.setTime_on_site(Double.parseDouble(split[5]));
               salesRecord.setClicks_in_site(Integer.parseInt(split[6]));
               list.add(salesRecord);
           }

       return list;
    }

    public List<SalesRecord> getAllCustomersSortedByPurchaseAmount(List<SalesRecord> salesData){

       Comparator<SalesRecord> comparator=(o1,o2) -> Double.compare(o2.getAmount(),o1.getAmount());
       Collections.sort(salesData,comparator);
      return salesData;
    }
    public SalesRecord getTopCustomerWhoSpentMaxTimeOnSite(List<SalesRecord> salesData){
       Comparator<SalesRecord> salesRecordComparator=(o1,o2) -> Double.compare(o2.getTime_on_site(),o1.getTime_on_site());
       Collections.sort(salesData,salesRecordComparator);
       SalesRecord salesRecord=salesData.get(0);

       return salesRecord;
    }


    public static void main(String[] args) {
        SalesDataAnalyzer sales=new SalesDataAnalyzer();
        try{
            System.out.println("sales.readFile(\"src/main/resources/purchase_details.csv\") = " + sales.readFile("src/main/resources/purchase_details.csv"));
        }catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
