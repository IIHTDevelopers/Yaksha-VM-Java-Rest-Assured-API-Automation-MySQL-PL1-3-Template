package testcases;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

import coreUtilities.utils.FileOperations;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import rest.ApiUtil;

public class RestAssured_TestCases_PL1 {

	FileOperations fileOperations = new FileOperations();

	private final String JSON_FILE_PATH = "src/main/resources/testData/addCurrency.json"; // Path to the JSON file
	private final String EXCEL_FILE_PATH = "src/main/resources/config.xlsx"; // Path to the Excel file
	private final String SHEET_NAME = "PostData"; // Sheet name in the Excel file
	private final String FILEPATH = "src/main/java/rest/ApiUtil.java";
	ApiUtil apiUtil;

	public static int appointmentId;
	
	@Test(priority = 1, groups = { "PL1" }, description = "1. Send a GET request to Get All Departments\n"
			+ "2. Validate that all the counter IDs are unique.\n" + "3. Verify the response status code is 200.")
	public void getAllDepartments() throws Exception {
		apiUtil = new ApiUtil();


		Response allDepartmentResponse = apiUtil.getAllDepartments("/AssetReports/GetAllDepartments", null);

		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(FILEPATH,
				"getBillingCountersWithAuth", List.of("given", "then", "extract", "response"));

		System.out.println("---------------------------------------------" + isValidationSuccessful
				+ "------------------------------");

		Assert.assertEquals(allDepartmentResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Validate the response fields for Registered Patients response
		String status = allDepartmentResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		List<Map<String, Object>> results = allDepartmentResponse.jsonPath().getList("Results");

		for (Map<String, Object> result : results) {
			String DepartmentId = result.get("DepartmentId").toString();
			String DepartmentName = result.get("DepartmentName").toString();

			System.out.println("DepartmentId: " + DepartmentId);
			System.out.println("DepartmentName: " + DepartmentName);
			System.out.println("\n");

			Assert.assertNotNull(DepartmentId, "The Department Id is null and the store doesn't exist.");
			Assert.assertNotNull(DepartmentName, "The Department Name is null and the store doesn't exist.");

		}

		// Print the response from canceling the appointment
		System.out.println("All Department Response:");
		allDepartmentResponse.prettyPrint();
	}
	
	
	
	@Test(priority = 2, groups = { "PL1" }, description = "1. Send a GET request to Get All Items\n"
			+ "2. Validate that the Item Id and Item name are not null.\n"
			+ "3. Verify the response status code is 200.")
	public void getAllItems() throws IOException {
		apiUtil = new ApiUtil();

		Response allDepartmentResponse = apiUtil.getAllItems("/AssetReports/GetAllItems", null);

		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(FILEPATH,
				"getBillingCountersWithAuth", List.of("given", "then", "extract", "response"));

		System.out.println("---------------------------------------------" + isValidationSuccessful
				+ "------------------------------");

		Assert.assertEquals(allDepartmentResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Validate the response fields for Registered Patients response
		String status = allDepartmentResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		List<Map<String, Object>> results = allDepartmentResponse.jsonPath().getList("Results");

		for (Map<String, Object> result : results) {
			String ItemId = result.get("ItemId").toString();
			String ItemName = result.get("ItemName").toString();

			System.out.println("ItemId: " + ItemId);
			System.out.println("ItemName: " + ItemName);
			System.out.println("\n");

			Assert.assertNotNull(ItemId, "The Item Id is null and the store doesn't exist.");
			Assert.assertNotNull(ItemName, "The Item Name is null and the store doesn't exist.");

		}

		// Print the response from canceling the appointment
		System.out.println("All Department Response:");
		allDepartmentResponse.prettyPrint();
	}
	
	
	@Test(priority = 3, groups = { "PL1" }, description = "1. Send a GET request to Incentive Summary Report\n"
			+ "2. Validate that the Prescriber Id and Prescriber name are not null.\n" + "3. Verify the response status code is 200.")
	
	public void getIncentiveSummary() throws Exception {
	    // Initialize the ApiUtil object
		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);

		apiUtil = new ApiUtil();

		String fromDate = searchResult.get("IncSummFromDate");
		String toDate = searchResult.get("IncSummToDate");
		String isRefferal = searchResult.get("IsRefferalOnly");
	    apiUtil = new ApiUtil();

	    // Fetch the response from the API
	    Response incentiveSummaryResponse = apiUtil.getIncentiveSummaryReport(
	            "https://healthapp.yaksha.com/BillingReports/INCTV_DocterSummary?FromDate="+fromDate+"&ToDate="+toDate+"&IsRefferalOnly="+isRefferal,
	            null);

	    // Validate the response status code
	    Assert.assertEquals(incentiveSummaryResponse.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the status field in the response
	    String status = incentiveSummaryResponse.jsonPath().getString("Status");
	    Assert.assertEquals(status, "OK", "Status should be OK.");

	    // Parse the JsonData field (stringified JSON) into a List of Maps
	    String jsonDataString = incentiveSummaryResponse.jsonPath().getString("Results.JsonData");
	    Assert.assertNotNull(jsonDataString, "JsonData field should not be null.");

	    // Deserialize JsonData into a List of Maps
	    List<Map<String, Object>> results = JsonPath.from(jsonDataString).getList("$");

	    // Iterate through each record in the results
	    for (Map<String, Object> result : results) {
	        // Extract and validate each field
	        String prescriberName = String.valueOf(result.get("PrescriberName"));
	        String prescriberId = String.valueOf(result.get("PrescriberId"));
	        String docTotalAmount = String.valueOf(result.get("DocTotalAmount"));
	        String tdsAmount = String.valueOf(result.get("TDSAmount"));
	        String netPayableAmount = String.valueOf(result.get("NetPayableAmount"));

	        // Print extracted fields
	        System.out.println("PrescriberName: " + prescriberName);
	        System.out.println("PrescriberId: " + prescriberId);
	        System.out.println("DocTotalAmount: " + docTotalAmount);
	        System.out.println("TDSAmount: " + tdsAmount);
	        System.out.println("NetPayableAmount: " + netPayableAmount);
	        System.out.println();

	        // Assert fields are not null
	        Assert.assertNotNull(prescriberName, "The Prescriber Name is null.");
	        Assert.assertNotNull(prescriberId, "The Prescriber ID is null.");
	        Assert.assertNotNull(docTotalAmount, "The DocTotal Amount is null.");
	        Assert.assertNotNull(tdsAmount, "The TDS Amount is null.");
	        Assert.assertNotNull(netPayableAmount, "The Net Payable Amount is null.");
	    }

	    // Print the entire API response
	    System.out.println("Full API Response:");
	    incentiveSummaryResponse.prettyPrint();
	}	

	@Test(priority = 4, groups = { "PL1" }, description = "1. Send a GET request to Incentive Refferal Summary Report\n"
			+ "2. Validate that the Prescriber name, PrescriberId, DocTotalAmount, TDSAmount and NetPayableAmount are not null.\n" 
			+ "3. Verify the response status code is 200.")
	
	public void getIncentiveReffSummary() throws Exception {
	    // Initialize the ApiUtil object
		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);

		apiUtil = new ApiUtil();

		String IncFromDate = searchResult.get("IncFromDate");
		String IncToDate = searchResult.get("IncToDate");
		String isRefferal = searchResult.get("DocSumIsRefferalOnly");

	    // Fetch the response from the API
	    Response incentiveSummaryResponse = apiUtil.getIncReffSummReport(
	            "https://healthapp.yaksha.com/BillingReports/INCTV_DocterSummary?FromDate="+IncFromDate+"&ToDate="+IncToDate+"&IsRefferalOnly="+isRefferal,
	            null);

	    // Validate the response status code
	    Assert.assertEquals(incentiveSummaryResponse.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the status field in the response
	    String status = incentiveSummaryResponse.jsonPath().getString("Status");
	    Assert.assertEquals(status, "OK", "Status should be OK.");

	    // Parse the JsonData field (stringified JSON) into a List of Maps
	    String jsonDataString = incentiveSummaryResponse.jsonPath().getString("Results.JsonData");
	    Assert.assertNotNull(jsonDataString, "JsonData field should not be null.");

	    // Deserialize JsonData into a List of Maps
	    List<Map<String, Object>> results = JsonPath.from(jsonDataString).getList("$");

	    // Iterate through each record in the results
	    for (Map<String, Object> result : results) {
	        // Extract and validate each field
	        String prescriberName = String.valueOf(result.get("PrescriberName"));
	        String prescriberId = String.valueOf(result.get("PrescriberId"));
	        String docTotalAmount = String.valueOf(result.get("DocTotalAmount"));
	        String tdsAmount = String.valueOf(result.get("TDSAmount"));
	        String netPayableAmount = String.valueOf(result.get("NetPayableAmount"));

	        // Print extracted fields
	        System.out.println("PrescriberName: " + prescriberName);
	        System.out.println("PrescriberId: " + prescriberId);
	        System.out.println("DocTotalAmount: " + docTotalAmount);
	        System.out.println("TDSAmount: " + tdsAmount);
	        System.out.println("NetPayableAmount: " + netPayableAmount);
	        System.out.println();

	        // Assert fields are not null
	        Assert.assertNotNull(prescriberName, "The Prescriber Name is null.");
	        Assert.assertNotNull(prescriberId, "The Prescriber ID is null.");
	        Assert.assertNotNull(docTotalAmount, "The DocTotal Amount is null.");
	        Assert.assertNotNull(tdsAmount, "The TDS Amount is null.");
	        Assert.assertNotNull(netPayableAmount, "The Net Payable Amount is null.");
	    }

	    // Print the entire API response
	    System.out.println("Full API Response:");
	    incentiveSummaryResponse.prettyPrint();
	}
	
	
	@Test(priority = 5, groups = { "PL1" }, description = "1. Send a GET request to Hospital Income Incentive Report\n"
			+ "2. Validate that the ServiceDepartmentName, ServiceDepartmentId, NetSales, ReferralCommission, GrossIncome, OtherIncentive and HospitalNetIncome are not null.\n" 
			+ "3. Verify the response status code is 200.")
	public void getHospitalIncomeIncReport() throws Exception {
	    // Initialize the ApiUtil object
		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);

		apiUtil = new ApiUtil();

		String IncFromDate = searchResult.get("IncFromDate");
		String IncToDate = searchResult.get("IncToDate");
		String ServiceDepartments = searchResult.get("ServiceDepartments");

	    // Fetch the response from the API
	    Response hospitalIncomeResponse = apiUtil.getHospIncIncReport(
	            "https://healthapp.yaksha.com/Reporting/HospitalIncomeIncentiveReport?FromDate="+IncFromDate+"&ToDate="+IncToDate+"&ServiceDepartments="+ServiceDepartments,
	            null);

	    // Validate the response status code
	    Assert.assertEquals(hospitalIncomeResponse.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Status" field in the response
	    String status = hospitalIncomeResponse.jsonPath().getString("Status");
	    Assert.assertEquals(status, "OK", "Status should be OK.");

	    // Parse the "Results" field directly as a list of maps
	    List<Map<String, Object>> results = hospitalIncomeResponse.jsonPath().getList("Results");
	    Assert.assertNotNull(results, "Results field should not be null.");

	    // Iterate through each record in the results
	    for (Map<String, Object> result : results) {
	        // Extract and validate each field
	        String serviceDepartmentId = String.valueOf(result.get("ServiceDepartmentId"));
	        String serviceDepartmentName = String.valueOf(result.get("ServiceDepartmentName"));
	        String netSales = String.valueOf(result.get("NetSales"));
	        String referralCommission = String.valueOf(result.get("ReferralCommission"));
	        String grossIncome = String.valueOf(result.get("GrossIncome"));
	        String otherIncentive = String.valueOf(result.get("OtherIncentive"));
	        String hospitalNetIncome = String.valueOf(result.get("HospitalNetIncome"));

	        // Print extracted fields
	        System.out.println("ServiceDepartmentId: " + serviceDepartmentId);
	        System.out.println("ServiceDepartmentName: " + serviceDepartmentName);
	        System.out.println("NetSales: " + netSales);
	        System.out.println("ReferralCommission: " + referralCommission);
	        System.out.println("GrossIncome: " + grossIncome);
	        System.out.println("OtherIncentive: " + otherIncentive);
	        System.out.println("HospitalNetIncome: " + hospitalNetIncome);
	        System.out.println();

	        // Assert fields are not null
	        Assert.assertNotNull(serviceDepartmentId, "ServiceDepartmentId should not be null.");
	        Assert.assertNotNull(serviceDepartmentName, "ServiceDepartmentName should not be null.");
	        Assert.assertNotNull(netSales, "NetSales should not be null.");
	        Assert.assertNotNull(referralCommission, "ReferralCommission should not be null.");
	        Assert.assertNotNull(grossIncome, "GrossIncome should not be null.");
	        Assert.assertNotNull(otherIncentive, "OtherIncentive should not be null.");
	        Assert.assertNotNull(hospitalNetIncome, "HospitalNetIncome should not be null.");
	    }

	    // Print the entire API response
	    System.out.println("Full API Response:");
	    hospitalIncomeResponse.prettyPrint();
	}
	
	@Test(priority = 6, groups = { "PL1" }, description = "1. Send a GET request to Incentive Employee Bill Items\n"
			+ "2. Validate that the EmployeeIncentiveInfoId, EmployeeId, FullName, TDSPercent,EmpTDSPercent,IsActive  are not null.\n" 
			+ "3. Verify the response status code is 200.")
	
	public void getIncenEmpBillItems() throws Exception {
	    // Initialize the ApiUtil object
		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);

		apiUtil = new ApiUtil();

		String EmployeeId = searchResult.get("employeeId");

	    // Fetch the response from the API
	    Response hospitalIncomeResponse = apiUtil.getEmpBillItem(
	            "/Incentive/EmployeeBillItems?employeeId="+EmployeeId,
	            null);

	    // Validate the response status code
	    Assert.assertEquals(hospitalIncomeResponse.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Status" field in the response
	    String status = hospitalIncomeResponse.jsonPath().getString("Status");
	    Assert.assertEquals(status, "OK", "Status should be OK.");

	    // Parse the "Results" field as a map
	    Map<String, Object> result = hospitalIncomeResponse.jsonPath().getMap("Results");
	    Assert.assertNotNull(result, "Results field should not be null.");

	    // Extract and validate each field
	    String employeeIncentiveInfoId = String.valueOf(result.get("EmployeeIncentiveInfoId"));
	    String employeeId = String.valueOf(result.get("EmployeeId"));
	    String fullName = String.valueOf(result.get("FullName"));
	    String tdsPercent = String.valueOf(result.get("TDSPercent"));
	    String empTdsPercent = String.valueOf(result.get("EmpTDSPercent"));
	    String isActive = String.valueOf(result.get("IsActive"));
	    List<Map<String, Object>> employeeBillItemsMap = (List<Map<String, Object>>) result.get("EmployeeBillItemsMap");

	    // Print extracted fields
	    System.out.println("EmployeeIncentiveInfoId: " + employeeIncentiveInfoId);
	    System.out.println("EmployeeId: " + employeeId);
	    System.out.println("FullName: " + fullName);
	    System.out.println("TDSPercent: " + tdsPercent);
	    System.out.println("EmpTDSPercent: " + empTdsPercent);
	    System.out.println("IsActive: " + isActive);
	    System.out.println("EmployeeBillItemsMap: " + employeeBillItemsMap);
	    System.out.println();

	    // Assert fields are not null
	    Assert.assertNotNull(employeeIncentiveInfoId, "EmployeeIncentiveInfoId should not be null.");
	    Assert.assertNotNull(employeeId, "EmployeeId should not be null.");
	    Assert.assertNotNull(fullName, "FullName should not be null.");
	    Assert.assertNotNull(tdsPercent, "TDSPercent should not be null.");
	    Assert.assertNotNull(empTdsPercent, "EmpTDSPercent should not be null.");
	    Assert.assertNotNull(isActive, "IsActive should not be null.");
	    Assert.assertNotNull(employeeBillItemsMap, "EmployeeBillItemsMap should not be null.");

	    // Print the entire API response
	    System.out.println("Response:");
	    hospitalIncomeResponse.prettyPrint();
	}
	
	

	@Test(priority = 7, groups = { "PL1" }, description = "1. Send a GET request to Inventory Fiscal Years\n"
			+ "2. Validate that the FiscalYearId, FiscalYearName are not null.\n" 
			+ "3. Verify the response status code is 200.")
	
	public void getInventoryFiscalYears() throws IOException {
	    // Initialize the ApiUtil object
	    apiUtil = new ApiUtil();

	    // Fetch the response from the API
	    Response response = apiUtil.getInvntryFiscalYrs(
	            "/Inventory/InventoryFiscalYears",
	            null);

	    // Validate the response status code
	    Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Results" field in the response
	    List<Map<String, Object>> results = response.jsonPath().getList("Results");
	    Assert.assertNotNull(results, "Results field should not be null.");
	    Assert.assertFalse(results.isEmpty(), "Results list should not be empty.");

	    // Loop through each fiscal year and extract fields
	    for (Map<String, Object> fiscalYear : results) {
	        Integer fiscalYearId = (Integer) fiscalYear.get("FiscalYearId");
	        String fiscalYearName = (String) fiscalYear.get("FiscalYearName");
	        String startDate = (String) fiscalYear.get("StartDate");
	        String endDate = (String) fiscalYear.get("EndDate");
	        Boolean isActive = (Boolean) fiscalYear.get("IsActive");

	        // Print extracted fields
	        System.out.println("FiscalYearId: " + fiscalYearId);
	        System.out.println("FiscalYearName: " + fiscalYearName);
	        System.out.println("StartDate: " + startDate);
	        System.out.println("EndDate: " + endDate);
	        System.out.println("IsActive: " + isActive);
	        System.out.println();

	        // Assert fields are not null
	        Assert.assertNotNull(fiscalYearId, "FiscalYearId should not be null.");
	        Assert.assertNotNull(fiscalYearName, "FiscalYearName should not be null.");
	        Assert.assertNotNull(startDate, "StartDate should not be null.");
	        Assert.assertNotNull(endDate, "EndDate should not be null.");
	        Assert.assertNotNull(isActive, "IsActive should not be null.");
	    }

	    // Print the entire API response
	    System.out.println("Response:");
	    response.prettyPrint();
	}

	
	@Test(priority = 8, groups = { "PL1" }, description = "1. Send a GET request to Activate Inventory/\n"
			+ "2. Validate that the StoreId, Name and StoreDescription are not null.\n" 
			+ "3. Verify the response status code is 200.")
	
	public void activateInventory() throws IOException {
	    // Initialize the ApiUtil object
	    apiUtil = new ApiUtil();

	    // Fetch the response from the API
	    Response response = apiUtil.getActInventory(
	            "/ActivateInventory/",
	            null);

	    // Validate the response status code
	    Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Results" field in the response
	    List<Map<String, Object>> results = response.jsonPath().getList("Results");
	    Assert.assertNotNull(results, "Results field should not be null.");
	    Assert.assertFalse(results.isEmpty(), "Results list should not be empty.");

	    // Loop through each fiscal year and extract fields
	    for (Map<String, Object> fiscalYear : results) {
	        Integer StoreId = (Integer) fiscalYear.get("StoreId");
	        String Name = (String) fiscalYear.get("Name");
	        String StoreDescription = (String) fiscalYear.get("StoreDescription");

	        // Print extracted fields
	        System.out.println("StoreId: " + StoreId);
	        System.out.println("Name: " + Name);
	        System.out.println("StoreDescription: " + StoreDescription);
	        System.out.println();

	        // Assert fields are not null
	        Assert.assertNotNull(StoreId, "Store Id should not be null.");
	        Assert.assertNotNull(Name, "Name should not be null.");
	        Assert.assertNotNull(StoreDescription, "StoreDescription should not be null.");
	    }

	    // Print the entire API response
	    System.out.println("Response:");
	    response.prettyPrint();
	}
	
	
	@Test(priority = 9, groups = { "PL1" }, description = "1. Send a GET request to Inventory Subcategory\n"
			+ "2. Validate that the SubCategoryName and SubCategoryId are not null.\n" 
			+ "3. Verify the response status code is 200.")
	
	public void inventorySubCategory() throws IOException {
	    // Initialize the ApiUtil object
	    apiUtil = new ApiUtil();

	    // Fetch the response from the API
	    Response response = apiUtil.getInvSubCat(
	            "/Inventory/SubCategories",
	            null);

	    // Validate the response status code
	    Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Results" field in the response
	    List<Map<String, Object>> results = response.jsonPath().getList("Results");
	    Assert.assertNotNull(results, "Results field should not be null.");
	    Assert.assertFalse(results.isEmpty(), "Results list should not be empty.");

	    // Loop through each fiscal year and extract fields
	    for (Map<String, Object> fiscalYear : results) {
	        Integer SubCategoryId = (Integer) fiscalYear.get("SubCategoryId");
	        String SubCategoryName = (String) fiscalYear.get("SubCategoryName");

	        // Print extracted fields
	        System.out.println("SubCategoryId: " + SubCategoryId);
	        System.out.println("SubCategoryName: " + SubCategoryName);
	        System.out.println();

	        // Assert fields are not null
	        Assert.assertNotNull(SubCategoryName, "SubCategoryName should not be null.");
	        Assert.assertNotNull(SubCategoryId, "SubCategoryId should not be null.");
	    }

	    // Print the entire API response
	    System.out.println("Response:");
	    response.prettyPrint();
	}
	
	
	
	@Test(priority = 10, groups = { "PL1" }, description = "1. Send a GET request to available Items by Store Id\n"
			+ "2. Validate that the ItemId, AvailableQuantity and StoreId are not null.\n" 
			+ "3. Verify the response status code is 200.")
	public void availableItems() throws Exception {
	    // Initialize the ApiUtil object
	    Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);

		apiUtil = new ApiUtil();

		String reqItemId = searchResult.get("itemId");
		String reqStoreId = searchResult.get("storeId");

	    // Fetch the response from the API
	    Response response = apiUtil.getAvlQtyByStoreId(
	            "/Inventory/AvailableQuantityByItemIdAndStoreId?itemId="+reqItemId+"&storeId="+reqStoreId,
	            null);

	    // Validate the response status code
	    Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Results" field in the response
	    Map<String, Object> results = response.jsonPath().getMap("Results");
	    Assert.assertNotNull(results, "Results field should not be null.");

	    // Extract fields from the "Results" object
	    Integer itemId = (Integer) results.get("ItemId");
	    Float availableQuantity =  (Float) results.get("AvailableQuantity");
	    Integer storeId = (Integer) results.get("StoreId");

	    // Print extracted fields
	    System.out.println("ItemId: " + itemId);
	    System.out.println("AvailableQuantity: " + availableQuantity);
	    System.out.println("StoreId: " + storeId);

	    // Assert fields are not null
	    Assert.assertNotNull(itemId, "ItemId should not be null.");
	    Assert.assertNotNull(availableQuantity, "AvailableQuantity should not be null.");
	    Assert.assertNotNull(storeId, "StoreId should not be null.");

	    // Print the entire API response
	    System.out.println("Response:");
	    response.prettyPrint();
	}
	

	
	@Test(priority = 11, groups = { "PL1" }, description = "1. Send a GET request to get Inventory Requistions Items For View by requistion Id.\n"
			+ "2. Validate that the RequisitionNo, CreatedByName are not null.\n" 
			+ "3. Verify the response status code is 200.")
	public void requisitionItems() throws Exception {
	    // Initialize the ApiUtil object
		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);

		apiUtil = new ApiUtil();

		String requisitionId = searchResult.get("requisitionId");

	    // Fetch the response from the API
	    Response response = apiUtil.getReqItemsById(
	            "/Inventory/RequisitionItemsForView?requisitionId="+requisitionId,
	            null);

	    // Validate the response status code
	    Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Results" field in the response
	    Map<String, Object> results = response.jsonPath().getMap("Results");
	    Assert.assertNotNull(results, "Results field should not be null.");

	    // Validate the "Requisition" field
	    Map<String, Object> requisition = (Map<String, Object>) results.get("Requisition");
	    Assert.assertNotNull(requisition, "Requisition field should not be null.");

	    // Extract fields from the "Requisition" object
	    String CreatedByName = (String) requisition.get("CreatedByName");
	    Integer requisitionNo = (Integer) requisition.get("RequisitionNo");
	    String requisitionStatus = (String) requisition.get("RequisitionStatus");

	    // Print extracted requisition details
	    System.out.println("CreatedByName: " + CreatedByName);
	    System.out.println("RequisitionNo: " + requisitionNo);
	    System.out.println("RequisitionStatus: " + requisitionStatus);

	    // Assert requisition fields are not null
	    Assert.assertNotNull(CreatedByName, "CreatedByName should not be null.");
	    Assert.assertNotNull(requisitionNo, "RequisitionNo should not be null.");
	    Assert.assertNotNull(requisitionStatus, "RequisitionStatus should not be null.");

	    // Validate the "RequisitionItems" list
	    List<Map<String, Object>> requisitionItems = (List<Map<String, Object>>) requisition.get("RequisitionItems");
	    Assert.assertNotNull(requisitionItems, "RequisitionItems field should not be null.");
	    Assert.assertFalse(requisitionItems.isEmpty(), "RequisitionItems list should not be empty.");

	    // Loop through each requisition item and extract fields
	    for (Map<String, Object> item : requisitionItems) {
	        String itemName = (String) item.get("ItemName");
	        String code = (String) item.get("Code");
	        Float pendingQuantity = (Float) item.get("PendingQuantity");
	        String requisitionItemStatus = (String) item.get("RequisitionItemStatus");

	        // Print extracted requisition item details
	        System.out.println("ItemName: " + itemName);
	        System.out.println("Code: " + code);
	        System.out.println("PendingQuantity: " + pendingQuantity);
	        System.out.println("RequisitionItemStatus: " + requisitionItemStatus);

	        // Assert requisition item fields are not null
	        Assert.assertNotNull(itemName, "ItemName should not be null.");
	        Assert.assertNotNull(code, "Code should not be null.");
	        Assert.assertNotNull(pendingQuantity, "PendingQuantity should not be null.");
	        Assert.assertNotNull(requisitionItemStatus, "RequisitionItemStatus should not be null.");
	    }

	    // Print the entire API response
	    System.out.println("Response:");
	    response.prettyPrint();
	}

	@Test(priority = 12, groups = { "PL1" }, description = 
			"1. Send a GET request to track by requisition Id.\n"
			+ "2. Validate that the RequisitionId, CreatedBy and Dispatchers are not null.\n" 
			+ "3. Verify the response status code is 200.")
	
	public void verifyRequisitionAndDispatchFields() throws Exception {
	    // Initialize the ApiUtil object
		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);

		apiUtil = new ApiUtil();

		String trackByRequisitionId = searchResult.get("trackByRequisitionId");

	    // Fetch the response from the API
	    Response response = apiUtil.trackReqItemById(
	            "/Inventory/TrackRequisition?requisitionId="+trackByRequisitionId,
	            null);

	    // Validate the response status code
	    Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Results" field in the response
	    Map<String, Object> results = response.jsonPath().getMap("Results");
	    Assert.assertNotNull(results, "Results field should not be null.");

	    // Validate the "CreatedBy" field
	    String createdBy = (String) results.get("CreatedBy");
	    Assert.assertNotNull(createdBy, "CreatedBy field should not be null.");
	    System.out.println("CreatedBy: " + createdBy);

	    // Validate the "Status" field
	    String status = (String) results.get("Status");
	    Assert.assertNotNull(status, "Status field should not be null.");
	    System.out.println("Status: " + status);

	    // Validate the "Dispatchers" field
	    List<Map<String, Object>> dispatchers = (List<Map<String, Object>>) results.get("Dispatchers");
	    Assert.assertNotNull(dispatchers, "Dispatchers field should not be null.");
	    Assert.assertFalse(dispatchers.isEmpty(), "Dispatchers list should not be empty.");

	    // Loop through each dispatcher and validate fields
	    for (Map<String, Object> dispatcher : dispatchers) {
	        Integer dispatchId = (Integer) dispatcher.get("DispatchId");
	        String name = (String) dispatcher.get("Name");

	        // Validate DispatchId and Name
	        Assert.assertNotNull(dispatchId, "DispatchId should not be null.");
	        Assert.assertNotNull(name, "Name should not be null.");

	        // Print dispatcher details
	        System.out.println("DispatchId: " + dispatchId);
	        System.out.println("Name: " + name);
	    }

	    // Print the entire API response
	    System.out.println("Response:");
	    response.prettyPrint();
	}

	
	@Test(priority = 13, groups = { "PL1" }, description = "1. Send a GET request to get inventory items by store Id.\n"
			+ "2. Validate that the ItemId, ItemName and AvailableQuantity are not null.\n" 
			+ "3. Verify the response status code is 200.")
	
	public void inventoryItemByStoreId() throws IOException {
	    // Initialize the ApiUtil object
	    apiUtil = new ApiUtil();

	    // Fetch the response from the API
	    Response response = apiUtil.getInvItem(
	            "/WardSupply/GetInventoryItemsByStoreId/7",
	            null);

	    // Validate the response status code
	    Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Results" field in the response
	    List<Map<String, Object>> results = response.jsonPath().getList("Results");
	    Assert.assertNotNull(results, "Results field should not be null.");
	    Assert.assertFalse(results.isEmpty(), "Results list should not be empty.");

	    // Loop through each item in the "Results" list and validate fields
	    for (Map<String, Object> item : results) {
	        Integer itemId = (Integer) item.get("ItemId");
	        Integer stockId = (Integer) item.get("StockId");
	        String itemName = (String) item.get("ItemName");
	        Float availableQuantity = item.get("AvailableQuantity") == null ? null : Float.parseFloat(item.get("AvailableQuantity").toString());
	        String code = (String) item.get("Code");
	        String itemType = (String) item.get("ItemType");

	        // Print extracted fields
	        System.out.println("ItemId: " + itemId);
	        System.out.println("StockId: " + stockId);
	        System.out.println("ItemName: " + itemName);
	        System.out.println("AvailableQuantity: " + availableQuantity);
	        System.out.println("Code: " + code);
	        System.out.println("ItemType: " + itemType);

	        // Assert fields are not null
	        Assert.assertNotNull(itemId, "ItemId should not be null.");
	        Assert.assertNotNull(stockId, "StockId should not be null.");
	        Assert.assertNotNull(itemName, "ItemName should not be null.");
	        Assert.assertNotNull(availableQuantity, "AvailableQuantity should not be null.");
	        Assert.assertNotNull(code, "Code should not be null.");
	        Assert.assertNotNull(itemType, "ItemType should not be null.");

	        // Additional validations (if needed)
	        // For example, checking if AvailableQuantity is greater than 0
	        Assert.assertTrue(availableQuantity > 0, "AvailableQuantity should be greater than 0.");
	    }

	    // Print the entire API response
	    System.out.println("Response:");
	    response.prettyPrint();
	}
	
	
	
	@Test(priority = 14, groups = { "PL1" }, description =
			"1. Send a GET request to get most sold medicine within date range.\n"
			+ "2. Validate that the ItemName and SoldQuantity are not null.\n" 
			+ "3. Verify the response status code is 200.")
	public void mostSoldMedicine() throws Exception {
	    // Initialize the ApiUtil object
		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);

		apiUtil = new ApiUtil();

		String FromDate = searchResult.get("soldMedFromDate");
		String IncToDate = searchResult.get("IncToDate");

	    // Fetch the response from the API
	    Response response = apiUtil.getMostSoldMed(
	            "https://healthapp.yaksha.com/PharmacyDashboard/GetPharmacyDashboardMostSoldMedicine?FromDate="+FromDate+"&ToDate="+IncToDate,
	            null);

	    // Validate the response status code
	    Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Results" field in the response
	    List<Map<String, Object>> results = response.jsonPath().getList("Results");
	    Assert.assertNotNull(results, "Results field should not be null.");
	    Assert.assertFalse(results.isEmpty(), "Results list should not be empty.");

	    // Loop through each item in the "Results" list and validate fields
	    for (Map<String, Object> item : results) {
	        String itemName = (String) item.get("ItemName");
	        Float soldQuantity = item.get("SoldQuantity") == null ? null : Float.parseFloat(item.get("SoldQuantity").toString());

	        // Print extracted fields
	        System.out.println("ItemName: " + itemName);
	        System.out.println("SoldQuantity: " + soldQuantity);

	        // Assert fields are not null
	        Assert.assertNotNull(itemName, "ItemName should not be null.");
	        Assert.assertNotNull(soldQuantity, "SoldQuantity should not be null.");

	        // Additional validation (if needed)
	        Assert.assertTrue(soldQuantity > 0, "SoldQuantity should be greater than 0.");
	    }

	    // Print the entire API response
	    System.out.println("Response:");
	    response.prettyPrint();
	}
	
	

	@Test(priority = 15, groups = { "PL1" }, description = "1. Send a GET request to get substore wise dispatch value.\n"
			+ "2. Validate that the Name and TotalDispatchValue are not null.\n" 
			+ "3. Verify the response status code is 200.")
	public void substoreWiseDisp() throws Exception {
	    // Initialize the ApiUtil object
		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);

		apiUtil = new ApiUtil();

		String FromDate = searchResult.get("soldMedFromDate");
		String IncToDate = searchResult.get("IncToDate");

	    // Fetch the response from the API
	    Response response = apiUtil.getSubDisp(
	            "https://healthapp.yaksha.com/PharmacyDashboard/GetPharmacyDashboardSubstoreWiseDispatchValue?FromDate="+FromDate+"&ToDate="+IncToDate,null);

	    // Validate the response status code
	    Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Results" field in the response
	    List<Map<String, Object>> results = response.jsonPath().getList("Results");
	    Assert.assertNotNull(results, "Results field should not be null.");
	    Assert.assertFalse(results.isEmpty(), "Results list should not be empty.");

	    // Loop through each item in the "Results" list and validate fields
	    for (Map<String, Object> item : results) {
	        String itemName = (String) item.get("Name");
	        Float TotalDispatchValue =  Float.parseFloat(item.get("TotalDispatchValue").toString());

	        // Print extracted fields
	        System.out.println("Name: " + itemName);
	        System.out.println("TotalDispatchValue: " + TotalDispatchValue);

	        // Assert fields are not null
	        Assert.assertNotNull(itemName, "Name should not be null.");
	        Assert.assertNotNull(TotalDispatchValue, "TotalDispatchValue should not be null.");
	    }

	    // Print the entire API response
	    System.out.println("Response:");
	    response.prettyPrint();
	}
	
	
	@Test(priority = 16, groups = { "PL1" }, description = "1. Send a GET request to get active suppliers.\n"
			+ "2. Validate that the SupplierId and SupplierName are not null.\n" 
			+ "3. Verify the response status code is 200.")
	public void activSupp() throws IOException {
	    // Initialize the ApiUtil object
	    apiUtil = new ApiUtil();

	    // Fetch the response from the API
	    Response response = apiUtil.getActSupp("/PharmacySettings/ActiveSuppliers",null);

	    // Validate the response status code
	    Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Results" field in the response
	    List<Map<String, Object>> results = response.jsonPath().getList("Results");
	    Assert.assertNotNull(results, "Results field should not be null.");
	    Assert.assertFalse(results.isEmpty(), "Results list should not be empty.");

	    // Loop through each item in the "Results" list and validate fields
	    for (Map<String, Object> item : results) {
	        String SupplierName = (String) item.get("SupplierName");
	        Integer SupplierId = (Integer) item.get("SupplierId");

	        // Print extracted fields
	        System.out.println("SupplierName: " + SupplierName);
	        System.out.println("SupplierId: " + SupplierId);

	        // Assert fields are not null
	        Assert.assertNotNull(SupplierName, "Supplier Name should not be null.");
	        Assert.assertNotNull(SupplierId, "SupplierId should not be null.");
	    }

	    // Print the entire API response
	    System.out.println("Response:");
	    response.prettyPrint();
	}
	
	
	
	@Test(priority = 17, groups = { "PL1" }, description = "1. Send a GET request to get unit of measurements.\n"
			+ "2. Validate that the UOMId and UOMName  are not null.\n" 
			+ "3. Verify the response status code is 200.")
	public void measureUnits() throws IOException {
	    // Initialize the ApiUtil object
	    apiUtil = new ApiUtil();

	    // Fetch the response from the API
	    Response response = apiUtil.getMeasureUnits("/PharmacySettings/UnitOfMeasurements",null);

	    // Validate the response status code
	    Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Results" field in the response
	    List<Map<String, Object>> results = response.jsonPath().getList("Results");
	    Assert.assertNotNull(results, "Results field should not be null.");
	    Assert.assertFalse(results.isEmpty(), "Results list should not be empty.");

	    // Loop through each item in the "Results" list and validate fields
	    for (Map<String, Object> item : results) {
	        String UOMName = (String) item.get("UOMName");
	        Integer UOMId = (Integer) item.get("UOMId");
	        

	        // Print extracted fields
	        System.out.println("UOMName: " + UOMName);
	        System.out.println("UOMId: " + UOMId);


	        // Assert fields are not null
	        Assert.assertNotNull(UOMName, "UOM Name should not be null.");
	        Assert.assertNotNull(UOMId, "UOMId should not be null.");
	    }

	    // Print the entire API response
	    System.out.println("Response:");
	    response.prettyPrint();
	}
	
	
	@Test(priority = 18, groups = { "PL1" }, description = "1. Send a GET request to get sales categories from pharmacy.\n"
			+ "2. Validate that the Name and SalesCategoryId are not null.\n" 
			+ "3. Verify the response status code is 200.")
	public void salesCat() throws IOException {
	    // Initialize the ApiUtil object
	    apiUtil = new ApiUtil();

	    // Fetch the response from the API
	    Response response = apiUtil.getSalesCat("/PharmacySettings/SalesCategories",null);

	    // Validate the response status code
	    Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK.");

	    // Validate the "Results" field in the response
	    List<Map<String, Object>> results = response.jsonPath().getList("Results");
	    Assert.assertNotNull(results, "Results field should not be null.");
	    Assert.assertFalse(results.isEmpty(), "Results list should not be empty.");

	    // Loop through each item in the "Results" list and validate fields
	    for (Map<String, Object> item : results) {
	        String Name = (String) item.get("Name");
	        Integer SalesCategoryId = (Integer) item.get("SalesCategoryId");
	        

	        // Print extracted fields
	        System.out.println("Name: " + Name);
	        System.out.println("SalesCategoryId: " + SalesCategoryId);


	        // Assert fields are not null
	        Assert.assertNotNull(Name, "Name should not be null.");
	        Assert.assertNotNull(SalesCategoryId, "SalesCategoryId should not be null.");
	    }

	    // Print the entire API response
	    System.out.println("Response:");
	    response.prettyPrint();
	}
	

}
