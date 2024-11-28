package rest;

import java.util.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiUtil {

	private static final String BASE_URL = "https://healthapp.yaksha.com/api";
	
	/**
	 * @Test1 This method fetches all departments from the API using the "/AssetReports/GetAllDepartments" endpoint.
	 * It validates the response status code, checks the "Status" field, and iterates through the "Results" 
	 * field to extract and validate DepartmentId and DepartmentName for each record. It ensures that neither 
	 * the DepartmentId nor DepartmentName is null. After validation, it prints the response for further inspection.
	 *
	 * @param endpoint - The API endpoint for fetching all departments.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response containing all the departments.
	 */

	
	 public Response getAllDepartments(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		return request.get(BASE_URL + endpoint).then().extract().response();
	}
	
	
	/**
	 * @Test2 This method fetches all items from the API using the "/AssetReports/GetAllItems" endpoint.
	 * It validates the response status code, checks the "Status" field, and iterates through the 
	 * "Results" field to extract and validate ItemId and ItemName for each record. It ensures that 
	 * neither the ItemId nor ItemName is null. After validation, it prints the response for further inspection.
	 *
	 * @param endpoint - The API endpoint for fetching all items.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response containing all the items.
	 */

	
	public Response getAllItems(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		return request.get(BASE_URL + endpoint).then().extract().response();
	}
	
	/**
	 *@Test3 This method fetches the incentive summary report for a specified date range and referral condition 
	 * from the API. It validates the response status code, checks the "Status" field, and extracts fields such as 
	 * PrescriberName, PrescriberId, DocTotalAmount, TDSAmount, and NetPayableAmount from the "JsonData" field (which 
	 * contains stringified JSON). It deserializes the JsonData into a List of Maps and iterates through the records to 
	 * validate and print the extracted fields.
	 *
	 * @param endpoint - The API endpoint for fetching the incentive summary report.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response containing the incentive summary report.
	 */

	
	public Response getIncentiveSummaryReport(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		return request.get(URL).then().extract().response();
	}
	
	/**
	 *@Test4 This method fetches the incentive referral summary report for a specified date range and referral condition 
	 * from the API. It validates the response status code, checks the "Status" field, and extracts fields such as 
	 * PrescriberName, PrescriberId, DocTotalAmount, TDSAmount, and NetPayableAmount from the "JsonData" field (which 
	 * contains stringified JSON). It deserializes the JsonData into a List of Maps and iterates through the records to 
	 * validate and print the extracted fields.
	 *
	 * @param endpoint - The API endpoint for fetching the incentive referral summary report.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response containing the incentive referral summary report.
	 */

	public Response getIncReffSummReport(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		return request.get(URL).then().extract().response();
	}
	
	
	/**
	 *@Test5 This method fetches the hospital income incentive report for a specified date range and service department 
	 * from the API. It validates the response status code, checks the "Status" field, and extracts fields such as 
	 * ServiceDepartmentId, ServiceDepartmentName, NetSales, ReferralCommission, GrossIncome, OtherIncentive, and 
	 * HospitalNetIncome from the "Results" array.
	 *
	 * @param endpoint - The API endpoint for fetching the hospital income incentive report.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response containing the hospital income incentive report.
	 */

	
	public Response getHospIncIncReport(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		return request.get(URL).then().extract().response();
	}
	
	public Response mapEmpBillItem(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		return request.get(BASE_URL+URL).then().extract().response();
	}
	
	/**
	 *@Test6 This method fetches employee bill items for a specific employee from the API.
	 * It validates the response status, checks the "Status" field, and extracts fields like 
	 * EmployeeIncentiveInfoId, EmployeeId, FullName, TDSPercent, EmpTDSPercent, IsActive, 
	 * and EmployeeBillItemsMap for validation.
	 *
	 * @param endpoint - The API endpoint for fetching employee bill items.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response containing employee bill items and related data.
	 */

	public Response getEmpBillItem(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		return request.get(BASE_URL+URL).then().extract().response();
	}
	
	
	/**
	 *@Test7 This method fetches inventory fiscal years from the API and validates various fields.
	 * It checks the fiscal year ID, name, start date, end date, and active status for each fiscal year.
	 *
	 * @param endpoint - The API endpoint for retrieving inventory fiscal years.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response containing the fiscal years and their details.
	 */

	
	public Response getInvntryFiscalYrs(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		return request.get(BASE_URL+URL).then().extract().response();
	}
	
	/**
	 *@Test8 This method activates inventory by retrieving store information from the API.
	 * It fetches a list of stores and validates each store's ID, name, and description.
	 *
	 * @param endpoint - The API endpoint for activating inventory.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response includes the HTTP status code and a list of stores.
	 */

	public Response getActInventory(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		return request.get(BASE_URL+URL).then().extract().response();
	}
	
	/**
	 *@Test9 This method retrieves and validates the subcategories of inventory from the API.
	 * It fetches the list of subcategories and validates each subcategory's ID and name.
	 *
	 * @param endpoint - The API endpoint that provides the subcategories information.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response includes the HTTP status code and a list of subcategories.
	 */

	
	
	public Response getInvSubCat(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		return request.get(BASE_URL+URL).then().extract().response();
	}
	
	
	/**
	 *@Test10 This method retrieves and validates the available quantity of an item in a specific store 
	 * based on the provided item ID and store ID.
	 *
	 * @param endpoint - The API endpoint that provides the available quantity of an item by item ID and store ID.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response includes the HTTP status code, item details, and available quantity information.
	 */

	public Response getAvlQtyByStoreId(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		return request.get(BASE_URL+URL).then().extract().response();
	}
	
	/**
	 *@Test11 This method retrieves and validates the requisition and its associated items 
	 * based on a specific requisition ID.
	 *
	 * @param endpoint - The API endpoint that provides the requisition and its items by ID.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response includes the HTTP status code, requisition details, 
	 *         and a list of requisition items.
	 */

	
	public Response getReqItemsById(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		return request.get(BASE_URL+URL).then().extract().response();
	}
	
	/**
	 * @Test12 This method retrieves and validates the fields for a specific requisition and its associated dispatchers.
	 * 
	 * @param endpoint - The API endpoint containing the requisition ID for fetching its details.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response includes the HTTP status code, status message, 
	 *         requisition details such as "CreatedBy", "Status", and a list of dispatchers 
	 *         in the "Results" field.
	 */

	
	public Response trackReqItemById(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}
		

		return request.get(BASE_URL+URL).then().extract().response();
	}
	
	
	/**
	 * @Test13 This method retrieves and validates the inventory items for a specific store ID.
	 * 
	 * @param endpoint - The API endpoint containing the store ID for fetching inventory items.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response includes the HTTP status code, status 
	 *         message, and a list of inventory items in the "Results" field.
	 */

	
	public Response getInvItem(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		return request.get(BASE_URL+URL).then().extract().response();
	}
	
	
	/**
	 * @Test14 This method retrieves and validates the most sold medicines from the Pharmacy Dashboard.
	 * 
	 * @param endpoint - The API endpoint with query parameters specifying the date range for fetching data.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response includes the HTTP status code, status 
	 *         message, and a list of most sold medicines in the "Results" field.
	 */

	
	public Response getMostSoldMed(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}
		

		return request.get(URL).then().extract().response();
	}
	
	
	/**
	 * @Test15 This method retrieves and validates the substore-wise dispatch values from the Pharmacy Dashboard.
	 * 
	 * @param endpoint - The API endpoint with query parameters specifying the date range for fetching data.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response includes the HTTP status code, status 
	 *         message, and a list of substore-wise dispatch values in the "Results" field.
	 */

	
	public Response getSubDisp(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}
		

		return request.get(URL).then().extract().response();
	}
	
	
	/**
	 * @Test16 This method retrieves and validates the list of Active Suppliers.
	 * 
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response includes the HTTP status code, status 
	 *         message, and a list of active suppliers in the "Results" field.
	 */

	public Response getActSupp(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}
		

		return request.get(BASE_URL+URL).then().extract().response();
	}
	
	
	
	
	/**
	 * @Test17 This method retrieves and validates the list of Units of Measurement.
	 * 
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response includes the HTTP status code, status 
	 *         message, and a list of measurement units in the "Results" field.
	 */

	
	public Response getMeasureUnits(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}
		

		return request.get(BASE_URL+URL).then().extract().response();
	}
	
	
	/**
	 * @Test18 This method retrieves and validates the list of Sales Categories.
	 * 
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return Response - The API response includes the HTTP status code, status 
	 *         message, and a list of sales categories in the "Results" field.
	 */

	
	public Response getSalesCat(String URL, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}
		

		return request.get(BASE_URL+URL).then().extract().response();
	}

}