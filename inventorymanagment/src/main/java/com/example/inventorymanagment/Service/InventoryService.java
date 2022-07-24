package com.example.inventorymanagment.Service;

import com.example.inventorymanagment.Model.InventoryManagementModel;
import com.example.inventorymanagment.Repository.InventoryRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public String saveRecord(InputStream file) {

        List<InventoryManagementModel> list = saveCsvFileData(file);
        inventoryRepository.saveAll(list);
        return "save";

    }

    public List<InventoryManagementModel> saveCsvFileData(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<InventoryManagementModel> inventoryManagementModelList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                InventoryManagementModel inventoryManagementModel = new InventoryManagementModel(
                        csvRecord.get("code"),
                        csvRecord.get("name"),
                        csvRecord.get("batch"),
                        Float.parseFloat(csvRecord.get("stock")),
                        Float.parseFloat(csvRecord.get("deal")),
                        Float.parseFloat(csvRecord.get("free")),
                        Float.parseFloat(csvRecord.get("mrp")),
                        Float.parseFloat(csvRecord.get("rate")),
                        csvRecord.get("exp"),
                        csvRecord.get("company"),
                        csvRecord.get("supplier")
                );

                inventoryManagementModelList.add(inventoryManagementModel);
            }

            return inventoryManagementModelList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    private static final Object[] FILE_HEADER = {"code", "name", "batch", "stock", "deal", "free", "mrp", "rate",
            "exp", "company", "supplier"};
    private static final String NEW_LINE_SEPARATOR = "\n";

    public void getCSVFile(String value) throws IOException {

        List<InventoryManagementModel> recordList = inventoryRepository.findAllBySupplier(value);
        writeCsvFile(value, recordList);

    }

    public static void writeCsvFile(String value, List<InventoryManagementModel> recordList) throws IOException {

        String fileName = value + "-record.csv";
        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        fileWriter = new FileWriter(fileName);
        csvPrinter = new CSVPrinter(fileWriter, csvFileFormat);
        csvPrinter.printRecord(FILE_HEADER);

        try {

            for (InventoryManagementModel listItems : recordList) {
                List<String> dataRecord = new ArrayList<>();
                dataRecord.add(listItems.getCode());
                dataRecord.add(listItems.getName());
                dataRecord.add(listItems.getBatch());
                dataRecord.add(String.valueOf(listItems.getStock()));
                dataRecord.add(String.valueOf(listItems.getDeal()));
                dataRecord.add(String.valueOf(listItems.getFree()));
                dataRecord.add(String.valueOf(listItems.getMrp()));
                dataRecord.add(String.valueOf(listItems.getRate()));
                dataRecord.add(listItems.getExp());
                dataRecord.add(listItems.getCompany());
                dataRecord.add(listItems.getSupplier());
                csvPrinter.printRecord(dataRecord);
            }
        } catch (Exception e) {
            System.out.printf("Error", e.getMessage());
        }

        if (fileWriter != null) {
            try {
                fileWriter.flush();
            } catch (IOException e) {
                System.out.printf("Could not flush fileWriter");
            }

            try {
                fileWriter.close();
            } catch (IOException e) {
                System.out.printf("Could not flush fileWriter");
            }

        }
        if (csvPrinter != null) {
            try {
                csvPrinter.close();
            } catch (IOException e) {
                System.out.printf("Could not close csvFilePrinter");
            }
        }
    }
}
