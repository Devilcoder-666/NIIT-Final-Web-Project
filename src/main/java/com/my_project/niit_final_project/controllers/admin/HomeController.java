package com.my_project.niit_final_project.controllers.admin;


import com.my_project.niit_final_project.entities.User;
import com.my_project.niit_final_project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/home")
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductTypeService productTypeService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;



       @GetMapping("/")
    public String getAdminHome(Model model){
           Long totalUser= userService.countTotal();
           Long totalOrder=orderService.countTotal();
           Long totalProductType=productTypeService.countTotal();
           Long totalProduct=productService.countTotal();
           Long totalCategory=categoryService.countTotal();
          model.addAttribute("total_user",totalUser);
           model.addAttribute("total_order",totalOrder);
           model.addAttribute("total_product_type",totalProductType);
           model.addAttribute("total_product",totalProduct);
           model.addAttribute("total_category",totalCategory);
           return "/admin/home";
       }






}
