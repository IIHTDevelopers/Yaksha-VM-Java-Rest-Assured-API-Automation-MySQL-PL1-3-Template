package rest;

public class ApiUtil {

	private static final String BASE_URL = "https://healthapp.yaksha.com/api";

	/**
	 * @Test1 This method retrieves and verifies the list of departments.
	 * 
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @param body     - Optional
	 * @return CustomResponse - The API response includes HTTP status code, status
	 *         message, and a list of departments in the "Results" field, containing
	 *         details such as DepartmentId and DepartmentName.
	 */
	public CustomResponse getAllDepartments(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test2 This method retrieves and verifies the list of items.
	 * 
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @param body     - Optional
	 * @return CustomResponse - The API response includes HTTP status code, status
	 *         message, and a list of items in the "Results" field, containing
	 *         details such as ItemId and ItemName.
	 */
	public CustomResponse getAllItems(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test3 This method retrieves and verifies the incentive summary report.
	 * 
	 * @param URL  - The API endpoint to which the GET request is sent.
	 * @param body - Optional
	 * @return CustomResponse - The API response includes HTTP status code, status
	 *         message, and a list of incentive summary details in the "JsonData"
	 *         field, containing details such as PrescriberName, PrescriberId,
	 *         DocTotalAmount, TDSAmount, and NetPayableAmount.
	 */
	public CustomResponse getIncentiveSummaryReport(String URL, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <JsonData>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test4 This method retrieves and verifies the incentive referral summary
	 *        report.
	 * 
	 * @param URL  - The API endpoint to which the GET request is sent.
	 * @param body - Optional
	 * @return CustomResponse - The API response includes HTTP status code, status
	 *         message, and a list of incentive referral summary details in the
	 *         "JsonData" field, containing details such as PrescriberName,
	 *         PrescriberId, DocTotalAmount, TDSAmount, and NetPayableAmount.
	 */
	public CustomResponse getIncReffSummReport(String URL, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <JsonData>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test5 This method fetches the hospital income incentive report for a
	 *        specified date range and service department from the API. It validates
	 *        the response status code, checks the "Status" field, and extracts
	 *        fields such as ServiceDepartmentId, ServiceDepartmentName, NetSales,
	 *        ReferralCommission, GrossIncome, OtherIncentive, and HospitalNetIncome
	 *        from the "Results" array.
	 *
	 * @param endpoint - The API endpoint for fetching the hospital income incentive
	 *                 report.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and details such as ServiceDepartmentId,
	 *         ServiceDepartmentName, NetSales, ReferralCommission, GrossIncome,
	 *         OtherIncentive, and HospitalNetIncome in the "Results" field.
	 */
	public CustomResponse getHospIncIncReport(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test6 This method fetches employee bill items for a specific employee from
	 *        the API. It validates the response status, checks the "Status" field,
	 *        and extracts fields like EmployeeIncentiveInfoId, EmployeeId,
	 *        FullName, TDSPercent, EmpTDSPercent, IsActive, and
	 *        EmployeeBillItemsMap for validation.
	 *
	 * @param endpoint - The API endpoint for fetching employee bill items.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and details such as EmployeeIncentiveInfoId,
	 *         EmployeeId, FullName, TDSPercent, EmpTDSPercent, IsActive, and
	 *         EmployeeBillItemsMap in the "Results" field.
	 */
	public CustomResponse getEmpBillItem(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test7 This method fetches inventory fiscal years from the API and validates
	 *        various fields. It checks the fiscal year ID, name, start date, end
	 *        date, and active status for each fiscal year.
	 *
	 * @param endpoint - The API endpoint for retrieving inventory fiscal years.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and details such as FiscalYearId, FiscalYearName,
	 *         StartDate, EndDate, and IsActive in the "Results" field.
	 */
	public CustomResponse getInvntryFiscalYrs(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test8 This method activates inventory by retrieving store information from
	 *        the API. It fetches a list of stores and validates each store's ID,
	 *        name, and description.
	 *
	 * @param endpoint - The API endpoint for activating inventory.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and a list of stores in the "Results" field.
	 */
	public CustomResponse getActInventory(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test9 This method retrieves and validates the subcategories of inventory
	 *        from the API. It fetches the list of subcategories and validates each
	 *        subcategory's ID and name.
	 *
	 * @param endpoint - The API endpoint that provides the subcategories
	 *                 information.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and a list of subcategories in the "Results" field.
	 */
	public CustomResponse getInvSubCat(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test10 This method retrieves and validates the available quantity of an item
	 *         in a specific store based on the provided item ID and store ID.
	 *
	 * @param endpoint - The API endpoint that provides the available quantity of an
	 *                 item by item ID and store ID.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and details such as ItemID, StoreID, and
	 *         AvailableQuantity in the "Results" field.
	 */
	public CustomResponse getAvlQtyByStoreId(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test11 This method retrieves and validates the requisition and its
	 *         associated items based on a specific requisition ID.
	 *
	 * @param endpoint - The API endpoint that provides the requisition and its
	 *                 items by ID.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         requisition details, and a list of requisition items in the "Results"
	 *         field.
	 */
	public CustomResponse getReqItemsById(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test12 This method retrieves and validates the fields for a specific
	 *         requisition and its associated dispatchers.
	 * 
	 * @param endpoint - The API endpoint containing the requisition ID for fetching
	 *                 its details.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, requisition details such as "CreatedBy", "Status",
	 *         and a list of dispatchers in the "Results" field.
	 */
	public CustomResponse trackReqItemById(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test13 This method retrieves and validates the inventory items for a
	 *         specific store ID.
	 * 
	 * @param endpoint - The API endpoint containing the store ID for fetching
	 *                 inventory items.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and a list of inventory items in the "Results" field.
	 */
	public CustomResponse getInvItem(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test14 This method retrieves and validates the most sold medicines from the
	 *         Pharmacy Dashboard.
	 * 
	 * @param endpoint - The API endpoint with query parameters specifying the date
	 *                 range for fetching data.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and a list of most sold medicines in the "Results"
	 *         field.
	 */
	public CustomResponse getMostSoldMed(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test15 This method retrieves and validates the substore-wise dispatch values
	 *         from the Pharmacy Dashboard.
	 * 
	 * @param endpoint - The API endpoint with query parameters specifying the date
	 *                 range for fetching data.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and a list of substore-wise dispatch values in the
	 *         "Results" field.
	 */
	public CustomResponse getSubDisp(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test16 This method retrieves and validates the list of Active Suppliers.
	 * 
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and a list of active suppliers in the "Results"
	 *         field.
	 */
	public CustomResponse getActSupp(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test17 This method retrieves and validates the list of Units of Measurement.
	 * 
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and a list of measurement units in the "Results"
	 *         field.
	 */
	public CustomResponse getMeasureUnits(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}

	/**
	 * @Test18 This method retrieves and validates the list of Sales Categories.
	 * 
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @param body     - Optional request body (null in this case).
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and a list of sales categories in the "Results"
	 *         field.
	 */
	public CustomResponse getSalesCat(String endpoint, Object body) {
		// write your logic here

		// return new CustomResponse(<response>, <statusCode>, <status>, <results>);
		return new CustomResponse(null, null, null, null);
	}
}