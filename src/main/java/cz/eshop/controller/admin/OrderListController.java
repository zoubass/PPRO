package cz.eshop.controller.admin;

import com.google.common.collect.Lists;
import cz.eshop.dao.OrderRepository;
import cz.eshop.dao.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderListController {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private ProductRepository prodRepo;

	@RequestMapping(value = { "/ordersList" })
	public String showOrderPage(Model model) {
		model.addAttribute("orders", orderRepo.findAll());
		return "ordersList";
	}

	@RequestMapping(value = "/getOrderProducts", method = RequestMethod.GET)
	public String showOrderProducts(Model model, @RequestParam Long id) {
		model.addAttribute("products", Lists.newArrayList(prodRepo.findByOrderId(id)));
		model.addAttribute("orders", orderRepo.findAll());
		return "ordersList";
	}

	@RequestMapping(value = { "/removeOrder" })
	public String deleteOrder(@RequestParam Long id, Model model) {
		orderRepo.delete(id);
		model.addAttribute("orders", orderRepo.findAll());
		return "ordersList";
	}

}
