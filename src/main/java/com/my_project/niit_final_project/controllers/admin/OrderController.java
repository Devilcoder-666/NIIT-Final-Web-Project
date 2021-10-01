package com.my_project.niit_final_project.controllers.admin;

import com.my_project.niit_final_project.entities.*;
import com.my_project.niit_final_project.services.OrderProductService;
import com.my_project.niit_final_project.services.OrderService;
import com.my_project.niit_final_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

@Controller
@RequestMapping("/admin/order")
public class OrderController implements ICRUD<Order> {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
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
    @GetMapping("/list")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "activePage", defaultValue = "0") int activePage) {
        int totalPage = orderService.getPageOrder(activePage,10).getTotalPages();
        if (page < 0 || page > totalPage - 1) {
            Page<Order> listProductPage = orderService.getPageOrder(activePage,10);
            model.addAttribute("listOrderPage", listProductPage);
            model.addAttribute("activePage", activePage);
        } else {
            Page<Order> listProductPage = orderService.getPageOrder(page,10);
            model.addAttribute("listOrderPage", listProductPage);
            model.addAttribute("activePage", page);
        }

        return "admin/order/list";
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

    @GetMapping("/show_detail")
    public String showDetail(Model model, long id) {
        Order order = orderService.getOrderById(id);
        User user= userService.getUserById(order.getUserId());
        Collection<OrderProduct> orderProducts=order.getOrderProducts();
        model.addAttribute("order", order);
        model.addAttribute("user", user);
        model.addAttribute("orderProducts",orderProducts);
        return "admin/order/detail";
    }
}
