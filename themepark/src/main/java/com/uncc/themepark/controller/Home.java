package com.uncc.themepark.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uncc.themepark.models.Customer;
import com.uncc.themepark.models.Employee;
import com.uncc.themepark.models.ItemSales;
import com.uncc.themepark.models.ItemSalesByDay;
import com.uncc.themepark.models.ItemSalesByStallAndDay;
import com.uncc.themepark.models.Items;
import com.uncc.themepark.models.Ride;
import com.uncc.themepark.models.Ticket;
import com.uncc.themepark.models.TicketSales;
import com.uncc.themepark.models.TicketSalesByDay;
import com.uncc.themepark.models.TicketSalesByTicetAndDay;
import com.uncc.themepark.repositories.EmployeeInfo;
import com.uncc.themepark.repositories.ItemSalesByDayInfo;
import com.uncc.themepark.repositories.ItemSalesByStallAndDayInfo;
import com.uncc.themepark.repositories.ItemSalesInfo;
import com.uncc.themepark.repositories.ItemsInfo;
import com.uncc.themepark.repositories.RideInfo;
import com.uncc.themepark.repositories.RideTicketInfo;
import com.uncc.themepark.repositories.TicketInfo;
import com.uncc.themepark.repositories.TicketSalesByDayInfo;
import com.uncc.themepark.repositories.TicketSalesByTicketAndDayInfo;
import com.uncc.themepark.repositories.TicketSalesInfo;
import com.uncc.themepark.repositories.customerInfo;

@Controller
public class Home {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	customerInfo customerInfo;

	@Autowired
	EmployeeInfo employeeInfo;

	@Autowired
	TicketInfo ticketInfo;

	@Autowired
	RideInfo rideInfo;

	@Autowired
	TicketSalesInfo ticketSalesInfo;

	@Autowired
	ItemsInfo itemsInfo;

	@Autowired
	ItemSalesInfo itemSalesInfo;
	
	@Autowired
	TicketSalesByTicketAndDayInfo ticketSalesByTicketAndDayInfo;
	
	@Autowired
	TicketSalesByDayInfo ticketSalesByDayInfo;
	
	@Autowired
	ItemSalesByStallAndDayInfo itemSalesByTicketAndDayInfo;
	
	@Autowired
	ItemSalesByDayInfo itemSalesByDayInfo;
	

	boolean isTcketBooked = false;

	@RequestMapping(value = "/login")
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";

	}

	@RequestMapping("/get-password-encrypted")
	@ResponseBody
	public String test(@RequestParam("password") String password) {

		return passwordEncoder.encode(password);
	}
	
	@RequestMapping("/join-query")
	@ResponseBody
	public List<RideTicketInfo> getJoinQuery() {
		
		List<RideTicketInfo> rideTicketInfo = ticketInfo.getRideTicketInfo();

		return rideTicketInfo;
	}

	@RequestMapping("/homepage")
	public String welcome(Model model) {

		String loggedUserName = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String authorities = authentication.getAuthorities().toString();
		loggedUserName = employeeInfo.getEmployeeNameByEmial(username);
		model.addAttribute("loggedUserName", " " + loggedUserName);

		LinkedHashMap<String, HashMap<String, String>> ticketsInformation;
		
		List<RideTicketInfo> rideTicketInfo = ticketInfo.getRideTicketInfo();
		df(rideTicketInfo);

		if (isTcketBooked) {
			model.addAttribute("message", "alert");
			isTcketBooked = false;
		}

		if (authorities.contains("ROLE_SALES_CLERK")) {
			List<Ticket> tickets = ticketInfo.getEntryTicketsInfo();
			tickets = SortTickets(tickets);
			ticketsInformation = getEntryTicketsInfoMap(tickets);
			model.addAttribute("ticketsInformation", ticketsInformation);
			return "entry-tickets";
		} else if (authorities.contains("ROLE_RIDE_OPERATOR")) {
			List<Ticket> tickets = ticketInfo.getIndividualTicketsInfo();
			tickets = SortTickets(tickets);
			ticketsInformation = getIndividualTicketsInfoMap(tickets);
			model.addAttribute("ticketsInformation", ticketsInformation);
			return "individual-tickets";
		} else if (authorities.contains("ROLE_VENDOR")) {
			List<Items> foodItems = itemsInfo.getItemsInfoByType("FOOD");
			System.out.println(foodItems.toString());
			List<Items> merchandiseItems = itemsInfo.getItemsInfoByType("MERCHANDISE");
			model.addAttribute("foodItems", foodItems);
			model.addAttribute("merchandiseItems", merchandiseItems);
			return "vendors";
		} else if (authorities.contains("ROLE_MANAGER")) {
			return "data-analysis";
		}

		return "welcome";
	}

	public LinkedHashMap<String, HashMap<String, String>> getEntryTicketsInfoMap(List<Ticket> tickets) {
		LinkedHashMap<String, HashMap<String, String>> result = new LinkedHashMap<String, HashMap<String, String>>();

		for (Ticket ticket : tickets) {

			String rides = rideInfo.getRidesStringByID(ticket.getTicketId());
			HashMap<String, String> ticketTypeInfo = new HashMap<String, String>();
			ticketTypeInfo.put("PRICE", String.valueOf(ticket.getPrice()));
			ticketTypeInfo.put("RIDES", rides);

			result.put(ticket.getTicketType(), ticketTypeInfo);

		}

		return result;
	}

	public LinkedHashMap<String, HashMap<String, String>> getIndividualTicketsInfoMap(List<Ticket> tickets) {
		LinkedHashMap<String, HashMap<String, String>> result = new LinkedHashMap<String, HashMap<String, String>>();

		for (Ticket ticket : tickets) {

			String rides = rideInfo.getRidesStringByID(ticket.getTicketId());
			HashMap<String, String> ticketTypeInfo = new HashMap<String, String>();
			ticketTypeInfo.put("PRICE", String.valueOf(ticket.getPrice()));
			ticketTypeInfo.put("RIDES", rides);

			result.put(ticket.getTicketType().replace("_INDIVIDUAL", ""), ticketTypeInfo);

		}

		return result;
	}
	
	public void df(List<RideTicketInfo> rideTicketInfo) {
		
	}

	public List<Ticket> SortTickets(List<Ticket> tickets) {

		Collections.sort(tickets, new Comparator<Ticket>() {
			@Override
			public int compare(Ticket o1, Ticket o2) {

				int firstSortValue = getSortValue(o1.getTicketType());
				int secondSortValue = getSortValue(o2.getTicketType());

				if (firstSortValue > secondSortValue)
					return 1;
				else
					return -1;

			}
		});

		return tickets;
	}

	public int getSortValue(String ticketType) {

		ticketType = ticketType.replace("_INDIVIDUAL", "");

		if (ticketType.equals("PLATINUM"))
			return 10;
		else if (ticketType.equals("GOLD"))
			return 5;
		else
			return 0;
	}

	@RequestMapping(value = "/book-entry-ticket", method = RequestMethod.POST)
	public String bookEntryTicket(Model model, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("mobileNumber") String mobileNumber,
			@RequestParam("ticketCategory") String ticketCategory, @RequestParam("quantity") String quantity,
			@RequestParam("price") String price) {

		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLast_name(lastName);
		customer.setPhoneNumber(mobileNumber);
		Integer custId = customerInfo.getCustomerId(firstName, lastName, mobileNumber);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String employeeId = authentication.getName();
		int ticketId = ticketInfo.getTicketIdByTicketCategory(ticketCategory);

		if (custId == null) {
			customerInfo.save(customer);
			custId = customerInfo.getCustomerId(firstName, lastName, mobileNumber);
		}

		ticketSalesInfo.insertTicketSalesData(employeeId, String.valueOf(custId), price, quantity,
				String.valueOf(ticketId));

		isTcketBooked = true;

		return "redirect:/homepage";

	}

	@RequestMapping(value = "/book-individual-ticket", method = RequestMethod.POST)
	public String bookIndividualTicket(Model model, @RequestParam("ticketSaleId") String ticketSaleId,
			@RequestParam("mobileNumber") String mobileNumber, @RequestParam("ticketCategory") String ticketCategory,
			@RequestParam("quantity") String quantity, @RequestParam("price") String price) {

		Integer custId = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String employeeId = authentication.getName();
		int ticketCategoryId = ticketInfo.getTicketIdByTicketCategory(ticketCategory + "_INDIVIDUAL");

		if (!ticketSaleId.equals("")) {
			custId = ticketSalesInfo.getCustomerIdBySalesId(Integer.parseInt(ticketSaleId));
		} else {
			custId = customerInfo.getCustomerIdByMobileNumber(mobileNumber);
		}

		ticketSalesInfo.insertTicketSalesData(employeeId, String.valueOf(custId), price, quantity,
				String.valueOf(ticketCategoryId));

		isTcketBooked = true;

		return "redirect:/homepage";

	}

	@RequestMapping(value = "/bill-items", method = RequestMethod.POST)
	public String billItems(Model model, @RequestParam("ticketSaleId") String ticketSaleId,
			@RequestParam("mobileNumber") String mobileNumber, @RequestParam("itemCategory") String itemCategory,
			@RequestParam("stallName") String stallName, @RequestParam("amount") String amount) {

		Integer custId = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String employeeId = authentication.getName();
		int itemCategoryId = itemsInfo.getItemId(itemCategory, stallName);

		if (!ticketSaleId.equals("")) {
			custId = ticketSalesInfo.getCustomerIdBySalesId(Integer.parseInt(ticketSaleId));
		} else {
			custId = customerInfo.getCustomerIdByMobileNumber(mobileNumber);
		}

		itemSalesInfo.insertItemSalesData(String.valueOf(custId), String.valueOf(itemCategoryId), employeeId, amount);

		isTcketBooked = true;

		return "redirect:/homepage";

	}

	@RequestMapping("/ride-info")
	@ResponseBody
	public Ride getRideInfo(@RequestParam("rideName") String rideName) {

		Ride ride = rideInfo.getRidesInfoByRideName(rideName);

		return ride;
	}
	
	@RequestMapping("/get-ticket-day-t")
	@ResponseBody
	public List<TicketSalesByTicetAndDay> getTicketDayT(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		
		List<TicketSalesByTicetAndDay> ticketSalesByTicetAndDay;

		if(startDate.equals("") || endDate.equals("")) {
			ticketSalesByTicetAndDay = ticketSalesByTicketAndDayInfo.findAllInfo();
		}
		
		else {
			ticketSalesByTicetAndDay = ticketSalesByTicketAndDayInfo.filterTicketSales("TICKET_DAY", startDate, endDate);
		}
		
		

		return ticketSalesByTicetAndDay;
	}
	
	@RequestMapping("/get-day-t")
	@ResponseBody
	public List<TicketSalesByDay> getDayT(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		
		List<TicketSalesByDay> ticketSalesByDay;

		if(startDate.equals("") || endDate.equals("")) {
			ticketSalesByDay = ticketSalesByDayInfo.findAllTicketSalesByDay();
		}
		
		else {
			ticketSalesByDay = ticketSalesByDayInfo.filterTicketSales("DAY", startDate, endDate);
		}
		
		

		return ticketSalesByDay;
	}
	
	@RequestMapping("/get-items-day-i")
	@ResponseBody
	public List<ItemSalesByStallAndDay> getItemsDayT(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		
		List<ItemSalesByStallAndDay> itemSalesByStallAndDay;

		if(startDate.equals("") || endDate.equals("")) {
			itemSalesByStallAndDay = itemSalesByTicketAndDayInfo.findAllItemSalesByStallAndDay();
		}
		
		else {
			itemSalesByStallAndDay = itemSalesByTicketAndDayInfo.filterItemSales("STALL_DAY", startDate, endDate);
		}
		
		

		return itemSalesByStallAndDay;
	}
	
	@RequestMapping("/get-day-i")
	@ResponseBody
	public List<ItemSalesByDay> getDayI(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		
		List<ItemSalesByDay> itemSalesByDay;

		if(startDate.equals("") || endDate.equals("")) {
			itemSalesByDay = itemSalesByDayInfo.findAllItemSalesByDay();
		}
		
		else {
			itemSalesByDay = itemSalesByDayInfo.filterItemSales("DAY", startDate, endDate);
		}
		
		

		return itemSalesByDay;
	}
	
	@RequestMapping("/ticket-sales-data")
	@ResponseBody
	public List<TicketSales> getTicketSalesData() {

		List<TicketSales> ticketSales = (List<TicketSales>) ticketSalesInfo.findAll();

		return ticketSales;
	}
	
	@RequestMapping("/item-sales-data")
	@ResponseBody
	public List<ItemSales> getItemSalesData() {

		List<ItemSales> itemSales = (List<ItemSales>) itemSalesInfo.findAll();

		return itemSales;
	}
	
	@RequestMapping("/employee-info")
	public String employeeInfo(Model model) {
		
		String loggedUserName = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String authorities = authentication.getAuthorities().toString();
		
		
		if (authorities.contains("ROLE_MANAGER")) {
			
			loggedUserName = employeeInfo.getEmployeeNameByEmial(username);
			model.addAttribute("loggedUserName", " " + loggedUserName);
			
			String[] emailsList = employeeInfo.getEmployeeEmails().split(",");
			model.addAttribute("emailsList", emailsList);

			return "employee-info";
		}
		
		return null;
	}
	
	@RequestMapping("/get-employee-info")
	@ResponseBody
	public Employee getEmployeeInfo(@RequestParam("email") String email) {
		
		Employee employeeInformation;
		
		employeeInformation = employeeInfo.getEmployeeDetailsByEmail(email);
		
		return employeeInformation;
		
	}
	
	@RequestMapping("/update-employee-info")
	@ResponseBody
	public String updateEmployeeInfo(@RequestParam("email") String email, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("addressLine1") String addressLine1,
			@RequestParam("addressLine2") String addressLine2, @RequestParam("city") String city, @RequestParam("state") String state, @RequestParam("zipcode") String zipcode,
			@RequestParam("phoneNumber") String phoneNumber) {
		
		try {
		employeeInfo.updateEmployeeDetailsByEmail(email, firstName, lastName, addressLine1, addressLine2, city, state, zipcode, phoneNumber);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("\n\n Exception occured while updation due to trigger\n\n");
			return "failed";
		}
		
		return "success";
		
	}
	
	@RequestMapping("/places-list")
	@ResponseBody
	public String getPlacesList() {
		
		return "success";
		
	}
	
	

}