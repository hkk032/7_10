package _10Store.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
//	writing utilities to perform json operations like reading data
//	sending file path through TC, as the file path will be different for multiple files
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
//		reading json to string
		String jsonValues = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
//		string to hashmap
//		need new dependency called Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
//		readValues reads string values and convert it to hashmap
//		1 arg - which value to convert to hashmap
//		2 arg - how you want to convert it
		List<HashMap<String, String>> data = mapper.readValue(jsonValues, 
				new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
}
