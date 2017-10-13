package cz.eshop.utils;

import cz.eshop.dto.UserDto;
import cz.eshop.model.Cart;
import cz.eshop.model.Produkt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static Cart getOrCreateCartForSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart c = (Cart) session.getAttribute("cart");
        if (c == null) {
            c = new Cart(new ArrayList<Produkt>());
            session.setAttribute("cart", c);
        }
        return c;
    }

    public static List<UserDto> parseResult(List<Object> results) {
        final List<UserDto> users = new ArrayList<UserDto>();
        for (Object o : results) {
            users.add(new UserDto());
        }
        return users;
    }

    public static List<String> getImageFileNames() {
        List<String> images = new ArrayList<>();

        File folder = new File("C:\\Users\\jakoubek\\Downloads\\TNPW-master\\eshop\\src\\main\\webapp\\WEB-INF\\img\\product");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String name = "img/product/" + listOfFiles[i].getName();
                images.add(name);
            }
        }
        return images;
    }
}
