package assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RequestReader {

    String inputFileName = "taxiRequests.csv";
    static List<Request> data = new ArrayList<>();

    public static void readFile(String fileName) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        boolean firstLine = true;
        while ((line = bufferedReader.readLine()) != null) {

            if (firstLine) {

                firstLine = false;
                continue;
            }
            String[] lineElements = line.split("[ ;,]+");
            data.add(new Request(Integer.parseInt(lineElements[0]),
                    Integer.parseInt(lineElements[1]),
                    (lineElements[2]),
                    (lineElements[3])));
            Collections.sort(data);
        }
        bufferedReader.close();
    }

    void reading() {
        try {

            System.out.println(data);
            readFile(inputFileName);
            System.out.println("reading input data.");
        } catch (IOException e) {

            System.err.println("Provided file (via argument): " + inputFileName + " not found.");
            System.exit(-1);
        }
    }
}
