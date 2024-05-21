package com.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		String jsonString = FileUtils
				.readFileToString(new File(".//src//test//java//com//data//PurchaseOrderData.json"));

		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, String>> data = objectMapper.readValue(jsonString,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

}
