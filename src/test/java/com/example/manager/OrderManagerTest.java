package com.example.manager;

import com.example.order.Check;
import com.example.order.Order;

import com.example.service.OrderService;
import com.example.parser.DataParser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderManagerTest {

    private List<Order> createOrderList(){
        List<Order> mockOrders = List.of(
                new Order(LocalDateTime.parse("2021-02-09T08:19:22"), "Recovery", 11340.0),
                new Order(LocalDateTime.parse("2021-02-09T08:42:59"), "Power Engineer", 17480.0),
                new Order(LocalDateTime.parse("2021-02-09T08:57:51"), "Preparatory", 21410.0),
                new Order(LocalDateTime.parse("2021-02-09T09:11:43"), "Pyramid", 10100.0),
                new Order(LocalDateTime.parse("2021-02-09T09:50:10"), "Fossil", 19600.0),
                new Order(LocalDateTime.parse("2021-02-09T10:48:34"), "Mosque", 33120.0),
                new Order(LocalDateTime.parse("2021-02-09T11:41:31"), "Atomic", 12500.0),
                new Order(LocalDateTime.parse("2021-02-09T12:32:48"), "Electricity", 3680.0),
                new Order(LocalDateTime.parse("2021-02-09T16:00:22"), "Industrial", 8800.0),
                new Order(LocalDateTime.parse("2021-02-09T17:39:17"), "Carryover", 29670.0),
                new Order(LocalDateTime.parse("2021-02-09T20:26:03"), "Resident", 5610.0),
                new Order(LocalDateTime.parse("2021-02-09T21:10:34"), "Ancillary", 30000.0),
                new Order(LocalDateTime.parse("2021-02-10T08:53:25"), "Power Engineer", 24600.0)
        );
        return mockOrders;

    }

    private List<Check> createCheckList(){

        List<Check> mockChecks = List.of(
                new Check("Pyramid", 32825.0),
                new Check("Electricity", 15640.0),
                new Check("Preparatory", 64230.0),
                new Check("Industrial", 39600.0),
                new Check("Power Engineer", 171070.0),
                new Check("Mosque", 124200.0),
                new Check("Carryover", 140932.5),
                new Check("Resident", 28050.0),
                new Check("Fossil", 68600.0),
                new Check("Atomic", 50000.0),
                new Check("Recovery", 28350.0),
                new Check("Ancillary", 150000.0)
        );

        return mockChecks;

    }

    @Mock
    private OrderService orderService;

    @Mock
    private FileManager fileManager;

    @InjectMocks
    private OrderManager orderManager;


    @Test
    void execution_TestTxtType(@TempDir Path tempDir) throws IOException {

        String inputPath = "discount_day.txt";
        Path outputFile = tempDir.resolve("test1.txt");

        List<Order> mockOrders = createOrderList();

        List<Check> mockChecks = createCheckList();


        when(fileManager.processImport(eq(inputPath), any(DataParser.class)))
                .thenReturn(mockOrders);


        when(orderService.calculatePrices(
                anyList(),
                anyDouble(),
                anyDouble(),
                anyDouble()
        )).thenReturn(mockChecks);


        orderManager.execution(inputPath, outputFile.toString(), 5.00, 50.00, 5.0);



        verify(fileManager).processImport(eq(inputPath), any(DataParser.class));
        verify(orderService).calculatePrices(eq(mockOrders), eq(5.00), eq(50.00), eq(5.0));
        verify(fileManager).processExport(eq(mockChecks), eq(outputFile.toString()));
    }








}