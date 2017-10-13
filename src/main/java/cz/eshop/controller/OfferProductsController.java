package cz.eshop.controller;

import com.google.common.collect.Lists;
import cz.eshop.dao.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OfferProductsController {
    @Autowired
    private ProductRepository productRepo;

    @RequestMapping(value="/show", method = RequestMethod.GET)
    public String getProduct(Model model, @RequestParam("id") Long id) {
        model.addAttribute("product", productRepo.findOne(id));
        return "productView";
    }

    @RequestMapping(value = "/offer", method = RequestMethod.GET)
    public String showOfferPage(Model model) {
        model.addAttribute("products",Lists.newArrayList(productRepo.findAll()));
        return "offer";
    }
}
