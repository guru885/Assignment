package JPMCAssignment.Restassured;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class TestRestassured extends Baseclass{
 
	@Test
	public void TestPostRequest() {
		String apidata = config.getPropertyValue("createPost");
		Response resp = config.executeRequest(apidata);
		assertEquals(201, resp.getStatusCode());
		resp.then().log().all();
	}
	@Test
	public void TestcommentRequest() {
		String apidata = config.getPropertyValue("createComment");
		Response resp = config.executeRequest(apidata);
		assertEquals(201, resp.getStatusCode());
		resp.then().log().all();
	}
	@Test
	public void TestSingleUserRequest() {
		String apidata = config.getPropertyValue("getSingleUser");
		Response resp = config.executeRequest(apidata);
		assertEquals(200, resp.getStatusCode());
		resp.then().log().all();
	}
	@Test
	public void TestAllUsersRequest() {
		String apidata = config.getPropertyValue("getusers");
		Response resp = config.executeRequest(apidata);
		assertEquals(200, resp.getStatusCode());
		resp.then().log().all();
	}
}
