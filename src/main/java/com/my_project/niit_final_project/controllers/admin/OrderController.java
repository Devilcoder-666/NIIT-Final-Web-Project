package com.my_project.niit_final_project.controllers.admin;

import com.my_project.niit_final_project.entities.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/home")
public class OrderController implements ICRUD<Order> {
    @Override
    public String add(Model model) {
        return null;
    }

    @Override
    public String doAdd(Order order, RedirectAttributes flashSession) {
        return null;
    }

    @Override
    public String doAddWithImage(Order order, RedirectAttributes flashSession, MultipartFile multipartFile) {
        return null;
    }

    @Override
    public String list(Model model, int page, int activePage) {
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
    public String doEdit(Order order, RedirectAttributes flashSession) {
        return null;
    }

    @Override
    public String doEditWithImage(Order order, RedirectAttributes flashSession, MultipartFile multipartFile) {
        return null;
    }
}
