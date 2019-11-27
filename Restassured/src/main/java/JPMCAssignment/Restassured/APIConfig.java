package JPMCAssignment.Restassured;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIConfig {

	private Properties pObj = new Properties();
	Response resp;
	String[] keyValue;
	
	/* This getPropertyValue function is used to fetch 
	 * the key value from the properties file
	 * Function takes input parameter as key and returns string value 
	 */
	public String getPropertyValue(String key) {
		try {
			FileInputStream fis = new FileInputStream("./apilist.properties");
			pObj.load(fis);
			return pObj.getProperty(key);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
		
	}
	
	/* The executeRequest function performs different http methods 
	 * on the uri and returns response 
	 * function takes string as input parameter and returns Response  
	 */
	
	public Response executeRequest(String apidata) {
		String[] arr = apidata.split(";");
		if(arr[0].equalsIgnoreCase("get")) {
			resp= RestAssured.get(arr[1]);
		}else if(arr[0].equalsIgnoreCase("post")) {
			String postObj = createJSONObject(arr[2]);
			resp= RestAssured.given().contentType(ContentType.JSON).body(postObj).when().post(arr[1]);
		}else if(arr[0].equalsIgnoreCase("put")) {
			String postObj = createJSONObject(arr[2]);
			resp= RestAssured.given().contentType(ContentType.JSON).body(postObj).when().put(arr[1]);
		}else if(arr[0].equalsIgnoreCase("patch")) {
			String postObj = createJSONObject(arr[2]);
			resp= RestAssured.given().contentType(ContentType.JSON).body(postObj).when().patch(arr[1]);
		}else if(arr[0].equalsIgnoreCase("delete")) {
			resp= RestAssured.delete(arr[1]);
		}
		return resp;
	}
	
	/*the function createJSONObject creates JSOn 0bject
	 * from the input string that is passed as key value object
	 * Takes String as input and returns the json string format
	 */
	public String createJSONObject(String data) {
		JSONObject json = new JSONObject();
		String[] arrJsondata = data.split(",");
		for(int i=0;i<arrJsondata.length;i++) {
			keyValue = arrJsondata[i].split(":");
			json.put(keyValue[0],keyValue[1]);
		}
		return json.toJSONString();
	}
}
