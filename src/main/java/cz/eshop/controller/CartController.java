package cz.eshop.controller;

import cz.eshop.dao.ProductRepository;
import cz.eshop.model.Cart;
import cz.eshop.model.Produkt;
import cz.eshop.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {
    private Cart cart;
    @Autowired
    private ProductRepository productRepo;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String loadCart(HttpServletRequest request, Model model) {
        cart = Util.getOrCreateCartForSession(request);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @RequestMapping("/addProduct")
    public String addProductToCart(HttpServletRequest request, Model model, @ModelAttribute Produkt p) {
        cart = Util.getOrCreateCartForSession(request);
        cart.addProduct(p);
        model.addAttribute("cart", cart);
        return loadCart(request,model);
    }

    @RequestMapping("/removeProduct")
    public String removeProductFromCart(HttpServletRequest request, Model model, @RequestParam Long id) {
        cart.removeProduct(productRepo.findOne(id));
        model.addAttribute("cart", cart);
        return "cart";
    }
}
