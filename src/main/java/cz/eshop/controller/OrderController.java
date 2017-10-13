package cz.eshop.controller;

import cz.eshop.dao.OrderRepository;
import cz.eshop.dao.ProductRepository;
import cz.eshop.model.Cart;
import cz.eshop.model.Feedback;
import cz.eshop.model.Order;
import cz.eshop.model.Produkt;
import cz.eshop.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private ProductRepository prodRepo;

    @RequestMapping("/order")
    public String loginError(Model model, HttpServletRequest request) {
        Cart c = Util.getOrCreateCartForSession(request);
        Order o = new Order(c.calculateTotalValue(), c.getProducts().size());
        model.addAttribute("order", o);
        return "order";
    }

    @RequestMapping("/createOrder")
    public String loginError(Model model, HttpServletRequest request, @Valid @ModelAttribute Order o, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) return "order";
            o = orderRepo.save(o);
            Cart c = Util.getOrCreateCartForSession(request);
            assignProductsToOrder(c.getProducts(), o.getId());
            model.addAttribute("success", true);
            model.addAttribute("feedback", new Feedback());
        } catch (Exception e) {
            model.addAttribute("message", "Nastal problém, zkontrolujte prosím zadané údaje.");
        }
        return "order";
    }

    private void assignProductsToOrder(List<Produkt> products, Long orderId) {
        for (Produkt p : products) {
            Produkt produkt = prodRepo.findOne(p.getId());
            produkt.setOrderId(orderId);
            prodRepo.save(produkt);
        }
    }
}
