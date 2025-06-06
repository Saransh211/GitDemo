package testdata.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class dataReader {

    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        // Read the JSON file into a String
        String jsonContent = FileUtils.readFileToString(
            new File(System.getProperty("user.dir") + "//src//test//java//testdata//data//PurchaseOrder.json"),
            "UTF-8"
        );

        // Convert JSON String to List of HashMaps
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(
            jsonContent,
            new TypeReference<List<HashMap<String, String>>>() {}
        );

        return data;
    }
}
