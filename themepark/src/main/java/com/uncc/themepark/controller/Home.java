package com.uncc.themepark.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
import com.uncc.themepark.models.Ticket;
import com.uncc.themepark.models.TicketSales;
import com.uncc.themepark.repositories.EmployeeInfo;
import com.uncc.themepark.repositories.RideInfo;
import com.uncc.themepark.repositories.TicketInfo;
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

	@RequestMapping("/test")
	@ResponseBody
	public String test(Model model) {
		
		customerInfo.getCustomerId("siva", "Reddy", "7046055367");
		
		return passwordEncoder.encode("themepark");
	}

	@RequestMapping("/homepage")
	public String welcome(Model model) {
		List<Ticket> tickets = ticketInfo.getTicketInfo();
		String loggedUserName = "";

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String authorities = authentication.getAuthorities().toString();
		
		

		HashMap<String, HashMap<String, String>> ticketsInformation = getTicketsInfoMap(tickets);
		loggedUserName = employeeInfo.getEmployeeNameByEmial(username);

		model.addAttribute("ticketsInformation", ticketsInformation);
		model.addAttribute("loggedUserName", " " + loggedUserName);
		if(isTcketBooked) {
			model.addAttribute("message","alert");
			isTcketBooked = false;
		}
		
		if(authorities.contains("ROLE_SALES_CLERK"))
			return "entry-tickets";
		else
			return "welcome";
	}

	public HashMap<String, HashMap<String, String>> getTicketsInfoMap(List<Ticket> tickets) {
		HashMap<String, HashMap<String, String>> result = new HashMap<String, HashMap<String, String>>();

		for (Ticket ticket : tickets) {
			if (result.containsKey(ticket.getTicketType())) {

			}

			String rides = rideInfo.getRidesStringByID(ticket.getTicketId());
			HashMap<String, String> ticketTypeInfo = new HashMap<String, String>();
			ticketTypeInfo.put("PRICE", String.valueOf(ticket.getPrice()));
			ticketTypeInfo.put("RIDES", rides);
			if (!ticket.getTicketType().equals("INDIVIDUAL"))
				result.put(ticket.getTicketType(), ticketTypeInfo);
		}

		return result;
	}

	@RequestMapping(value="/book-ticket", method=RequestMethod.POST)
	public String bookTicket(Model model, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("mobileNumber") String mobileNumber, 
			@RequestParam("ticketCategory") String ticketCategory, @RequestParam("quantity") String quantity, @RequestParam("price") String price) {
		
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLast_name(lastName);
		customer.setPhoneNumber(mobileNumber);
		Integer custId = customerInfo.getCustomerId(firstName, lastName, mobileNumber);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String employeeId = authentication.getName();
		int ticketId = ticketInfo.getTicketIdByTicketCategory(ticketCategory);
		
		if(custId == null)
			customerInfo.save(customer);
		
		ticketSalesInfo.insertTicketSalesData(employeeId, String.valueOf(custId), price, quantity, String.valueOf(ticketId));
		
		isTcketBooked = true;
		
		return "redirect:/homepage";
		
	}
	

}