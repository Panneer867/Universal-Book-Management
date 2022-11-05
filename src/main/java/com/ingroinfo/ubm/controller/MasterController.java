package com.ingroinfo.ubm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/master")
public class MasterController {

	@GetMapping("/sales-order")
	public String salesOrder(Model model) {
		model.addAttribute("title", "Sales Order");
		return "/pages/sales_order";
	}

	@GetMapping("/purchase-order")
	public String purchaseOrder(Model model) {
		model.addAttribute("title", "Purchase Order");
		return "/pages/purchase_order";
	}

	@GetMapping("/order-view")
	public String orderView(Model model) {
		model.addAttribute("title", "Order View");
		return "/pages/order_view";
	}

	@GetMapping("/reject-order")
	public String rejectOrder(Model model) {
		model.addAttribute("title", "Reject Order");
		return "/pages/reject_order";
	}

	@GetMapping("/order-tracking")
	public String orderTracking(Model model) {
		model.addAttribute("title", "Order Tracking");
		return "/pages/order_tracking";
	}

	@GetMapping("/manual-billing")
	public String manualBilling(Model model) {
		model.addAttribute("title", "Manual Billing");
		return "/pages/manual_billing";
	}

	@GetMapping("/invoice")
	public String invoice(Model model) {
		model.addAttribute("title", "Invoice");
		return "/pages/invoice";
	}

	@GetMapping("/sales-return")
	public String salesReturn(Model model) {
		model.addAttribute("title", "Sales Return");
		return "/pages/sales_return";
	}

	@GetMapping("/purchase-return")
	public String purchaseReturn(Model model) {
		model.addAttribute("title", "Purchase Return");
		return "/pages/purchase_return";
	}

	@GetMapping("/estimate")
	public String estimate(Model model) {
		model.addAttribute("title", "Estimate");
		return "/pages/estimate";
	}

	@GetMapping("/delivery-chellan")
	public String deliveryChellan(Model model) {
		model.addAttribute("title", "Delivery Chellan");
		return "/pages/delivery_chellan";
	}

	@GetMapping("/debit-note")
	public String debitNote(Model model) {
		model.addAttribute("title", "Debit Note");
		return "/pages/debit_note";
	}

	@GetMapping("/credit-note")
	public String creditNote(Model model) {
		model.addAttribute("title", "Credit Note");
		return "/pages/credit_note";
	}

	@GetMapping("/opening-balance")
	public String openingBalance(Model model) {
		model.addAttribute("title", "Opening Balance");
		return "/pages/opening_balance";
	}

	@GetMapping("/stock_monthwise")
	public String stockMonthwise(Model model) {
		model.addAttribute("title", "Stock Monthwise");
		return "/pages/stock_monthwise";
	}

	@GetMapping("/stock-rejection-return")
	public String stockRejectionReturn(Model model) {
		model.addAttribute("title", "Stock Rejection Return");
		return "/pages/stock_rejection_return";
	}

	@GetMapping("/stock-rejection-non-return")
	public String stockRejectionNonReturn(Model model) {
		model.addAttribute("title", "Stock Rejection Non Return");
		return "/pages/stock_rejection_non_return";
	}

	@GetMapping("/payment-details")
	public String paymentDetails(Model model) {
		model.addAttribute("title", "Payment Details");
		return "/pages/payment_details";
	}

	@GetMapping("/payment-configutration")
	public String paymentConfigutration(Model model) {
		model.addAttribute("title", "Payment Configutration");
		return "/pages/payment_configutration";
	}

	@GetMapping("/barcode-generator")
	public String barcodeGenerator(Model model) {
		model.addAttribute("title", "Barcode Generator");
		return "/pages/barcode_generator";
	}

	@GetMapping("/cash-counter")
	public String cashCounter(Model model) {
		model.addAttribute("title", "Cash Counter");
		return "/pages/cash_counter";
	}

	@GetMapping("/consumer-master")
	public String consumerMaster(Model model) {
		model.addAttribute("title", "Consumer Master");
		return "/pages/consumer_master";
	}

	@GetMapping("/rate-master")
	public String rateMaster(Model model) {
		model.addAttribute("title", "Rate Master");
		return "/pages/rate_master";
	}

	@GetMapping("/excel/employee-log")
	public String employeeLogin(Model model) {
		model.addAttribute("title", "Employee Login / Logout");
		return "/pages/emp_log";
	}

	@GetMapping("/excel")
	public String importExcel(Model model) {
		model.addAttribute("title", "Import Excel");
		return "/pages/excel_import_export";
	}

	@GetMapping("/mis-report")
	public String misReport(Model model) {
		model.addAttribute("title", "MIS Report");
		return "/pages/mis_report";
	}

	@GetMapping("/website-management")
	public String websiteManagement(Model model) {
		model.addAttribute("title", "Website Management");
		return "/pages/website_management";
	}

	@GetMapping("/employee-management")
	public String employeeManagement(Model model) {
		model.addAttribute("title", "Employee Management");
		return "/pages/employee_management";
	}

	@GetMapping("/employee-management/attendance")
	public String attendance(Model model) {
		model.addAttribute("title", "Employee Attendance");
		return "/pages/attendance";
	}

	@GetMapping("/employee-management/salary")
	public String salary(Model model) {
		model.addAttribute("title", "Employee Salary");
		return "/pages/salary";
	}

	@GetMapping("/employee-management/leave")
	public String leave(Model model) {
		model.addAttribute("title", "Apply Leave");
		return "/pages/leave";
	}

	

	@GetMapping("/user-management/user-role")
	public String userRoles(Model model) {
		model.addAttribute("title", "User Roles");
		return "/pages/user_role";
	}

	@GetMapping("/user-management/change-password")
	public String changePassword(Model model) {
		model.addAttribute("title", "Change Password");
		return "/pages/change_password";
	}

	@GetMapping("/company/manage-company")
	public String manageCompany(Model model) {
		model.addAttribute("title", "Manage Company");
		return "/pages/manage_company";
	}

	@GetMapping("/company/suspend-company")
	public String suspendCompany(Model model) {
		model.addAttribute("title", "Suspend Company");
		return "/pages/suspend_company";
	}

	@GetMapping("/backup")
	public String backup(Model model) {
		model.addAttribute("title", "Backup");
		return "/pages/backup";
	}

	/* ====================================== */
	/* ====================================== */

	@GetMapping("/customer-orders")
	public String customerOrders(Model model) {
		model.addAttribute("title", "Customer Orders");
		return "/pages/customer_orders";
	}

	@GetMapping("/view-tracking")
	public String viewTracking(Model model) {
		model.addAttribute("title", "View Tracking");
		return "/pages/view_tracking";
	}

}
