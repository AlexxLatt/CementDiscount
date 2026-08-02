    package com.example.manager;

    import com.example.order.Check;
    import com.example.order.Order;

    import com.example.parser.DataParser;

    import com.example.parser.DataParserImpl;
    import org.junit.jupiter.api.Test;

    import javax.imageio.IIOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.time.LocalDateTime;
    import java.util.List;


    import static org.junit.jupiter.api.Assertions.assertEquals;

    public class FileManagerTest {

        FileManager fileManager = new FileManager();


        @Test
        void processImport_Test() {

            String filePath = "src/test/resources/discount_day.txt";

            DataParser realParser = new DataParserImpl();

            List<Order> result = fileManager.processImport(filePath, realParser);


            assertEquals(mockOrderList(),result);

        }


        @Test
        void processExport_Test() throws Exception {
            Path outputFile = Paths.get("test1.txt");

            List<Check> checks = mockCheckList();
            List<String> expectedLines = mockLines();

            fileManager.processExport(checks, outputFile.toString());

            List<String> actualLines = Files.readAllLines(outputFile);

            assertEquals(expectedLines, actualLines);

        }

        private List<Order> mockOrderList() {
            return List.of(
                    new Order(LocalDateTime.parse("2021-02-09T16:00:22"), "Industrial", 8800.0),
                    new Order(LocalDateTime.parse("2021-02-09T08:42:59"), "Power Engineer", 17480.0),
                    new Order(LocalDateTime.parse("2021-02-09T10:48:34"), "Mosque", 33120.0),
                    new Order(LocalDateTime.parse("2021-02-09T08:19:22"), "Recovery", 11340.0),
                    new Order(LocalDateTime.parse("2021-02-09T11:41:31"), "Atomic", 12500.0),
                    new Order(LocalDateTime.parse("2021-02-09T08:57:51"), "Preparatory", 21410.0),
                    new Order(LocalDateTime.parse("2021-02-09T20:26:03"), "Resident", 5610.0),
                    new Order(LocalDateTime.parse("2021-02-09T09:50:10"), "Fossil", 19600.0),
                    new Order(LocalDateTime.parse("2021-02-10T08:53:25"), "Power Engineer", 24600.0),
                    new Order(LocalDateTime.parse("2021-02-09T17:39:17"), "Carryover", 29670.0),
                    new Order(LocalDateTime.parse("2021-02-09T12:32:48"), "Electricity", 3680.0),
                    new Order(LocalDateTime.parse("2021-02-09T21:10:34"), "Ancillary", 30000.0),
                    new Order(LocalDateTime.parse("2021-02-09T09:11:43"), "Pyramid", 10100.0)
            );
        }

        private List<Check>  mockCheckList() {

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
        private List<String> mockLines(){
            List<String> lines = List.of(
                    "Pyramid | 32825.0",
                    "Electricity | 15640.0",
                    "Preparatory | 64230.0",
                    "Industrial | 39600.0",
                    "Power Engineer | 171070.0",
                    "Mosque | 124200.0",
                    "Carryover | 140932.5",
                    "Resident | 28050.0",
                    "Fossil | 68600.0",
                    "Atomic | 50000.0",
                    "Recovery | 28350.0",
                    "Ancillary | 150000.0"
            );
            return lines;
        }

    }
