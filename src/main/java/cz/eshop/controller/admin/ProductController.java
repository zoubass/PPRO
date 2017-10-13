package cz.eshop.controller.admin;

import cz.eshop.dao.ProductRepository;
import cz.eshop.dto.ProductDto;
import cz.eshop.model.Produkt;
import cz.eshop.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @RequestMapping(value = "/addProductsDb", method = RequestMethod.POST)
    public String addP(Model model, @Valid @ModelAttribute("product") ProductDto pDto, BindingResult bindingResult) {
        Produkt p = new Produkt();
        p.setCategory(pDto.getCategory());
        p.setPrice(pDto.getPrice());
        p.setType(pDto.getType());

        if (bindingResult.hasErrors()) {
            model.addAttribute("productDto", pDto);
            model.addAttribute("isValidInput", false);
            return "redirect:/product";
        }


        if (pDto.getImageFile() != null) {
            String imageName = uploadFile(pDto.getImageFile());
            p.setImage(imageName);
        }

        if (p.getId() != null) {
            if (productRepo.exists(p.getId())) productRepo.delete(p);
        }
        setModelAttributes(model, true);
        productRepo.save(p);
        model.addAttribute("succes", "Produkt uložen!");
        return "redirect:/product";
    }

    @RequestMapping(value = "/removeProductdb", method = RequestMethod.GET)
    public String removeP(Model model, @RequestParam("id") Long id) {
        productRepo.delete(id);
        setModelAttributes(model, true);
        model.addAttribute("images", Util.getImageFileNames());
        return "admin/product";
    }

    @RequestMapping(value = "/updateProducts", method = RequestMethod.GET)
    public String showEditForm(Model model, @RequestParam("id") Long id) {
        Produkt p = productRepo.findOne(id);
        model.addAttribute("product", p);
        return "admin/product";
    }

    @RequestMapping(value = "/searchProducts", method = RequestMethod.POST)
    public String search(Model model, @ModelAttribute Produkt p) {
        List<Produkt> products = productRepo.findSpecificProducts(p.getType(),/*, p.getPrice(),*/ p.getCategory());
        model.addAttribute("products", products);
        model.addAttribute("product", p);
        return "admin/product";
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("product", new ProductDto());
        return "admin/product";
    }

    private void setModelAttributes(Model model, boolean isValid) {
        model.addAttribute("isValidInput", isValid);
        model.addAttribute("productDto", new ProductDto());
    }


    private String uploadFile(MultipartFile file) {
        try {
            String name = file.getOriginalFilename();
            // String name = file.getName() + reportType.toString();
            byte[] bytes = file.getBytes();

            String rootPath = "C:\\work\\workspace\\eshop\\src\\main\\webapp\\WEB-INF\\img";
            File dir = new File(rootPath + File.separator + "tmpFiles");
            if (!dir.exists())
                dir.mkdirs();

            // Vytvoření souboru na serveru
            File serverFile = new File(dir.getAbsolutePath() + File.separator + name);

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

            stream.write(bytes);

            stream.close();
            return "img/tmpFiles/" + name;
        } catch (Exception e) {
            System.err.println("Failed to update");
        }

        return null;
    }

}
