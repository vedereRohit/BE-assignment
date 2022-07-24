package com.example.inventorymanagment.Controller;

import com.example.inventorymanagment.Model.InventoryManagementModel;
import com.example.inventorymanagment.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class InventoryManagementController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload_csv_file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file) throws IOException {

        // validate file
        if (file.isEmpty()) {
            System.out.printf("Please select a CSV file to upload.");
        } else {
            // parse CSV file to create a list of objects
            inventoryService.saveRecord(file.getInputStream());
        }
        return "saved_successfully";
    }

    @GetMapping(value = "/showForm")
    public String showForm(Model model) {
        model.addAttribute("InventoryManagementModel", new InventoryManagementModel());
        return "search";
    }

    @GetMapping(value = "/record/find")
    public String showForm(InventoryManagementModel request) throws IOException {
        inventoryService.getCSVFile(request.getValue());
        return "success";
    }
}
