package com.my_project.niit_final_project.controllers.admin;

import com.my_project.niit_final_project.entities.ProductType;
import com.my_project.niit_final_project.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/admin/product_type")

public class ProductTypeController implements ICRUD<ProductType>{
    @Override
    public String add(Model model) {
        return null;
    }

    @Override
    public String doAdd(ProductType productType, RedirectAttributes flashSession) {
        return null;
    }

    @Override
    public String doAddWithImage(ProductType productType, RedirectAttributes flashSession, MultipartFile multipartFile) {
        return null;
    }

    @Override
    public String list(Model model, int page) {
        return null;
    }

    @Override
    public String delete(long id, RedirectAttributes flashSession) {
        return null;
    }

    @Override
    public String edit(Model model, long id) {
        return null;
    }

    @Override
    public String doEdit(ProductType productType, RedirectAttributes flashSession) {
        return null;
    }

    @Override
    public String doEditWithImage(ProductType productType, RedirectAttributes flashSession, MultipartFile multipartFile) {
        return null;
    }
}
