package com.my_project.niit_final_project.controllers.client;

import com.my_project.niit_final_project.entities.*;
import com.my_project.niit_final_project.services.OrderService;
import com.my_project.niit_final_project.services.UserRoleService;
import com.my_project.niit_final_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private OrderService orderService;


    @GetMapping("/login")
    public String login(){
        return "/client/auth/login";
    }
    @GetMapping("/logout")
    public  String logout(){
        return "/client/home";
    }
    @GetMapping("/signup")
    public  String signup(Model model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles",userRoleService.getAll());
        return "/client/auth/signup";
    }
    @PostMapping("/do-signup")
    public  String doSignup(User user){
            Set<UserRole> roles= new HashSet<UserRole>();
            roles.add(userRoleService.getUserRoleById((long)2)) ;
            user.setUserRoles(roles);
            userService.save(user);
            return "redirect:/login";


    }

    @GetMapping("/checkout")
    public  String checkout( Model model){
        return "/client/auth/checkout";
    }
    @PostMapping("do-checkout")
    public  String doCheckout(HttpSession session, @RequestParam(name="address") String address){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUseByEmail(currentPrincipalName);
        Order order=new Order();
        order.setUserId(user.getId());
        ArrayList<CartProduct> cartProducts = (ArrayList<CartProduct>) session.getAttribute("CART");
        Collection<OrderProduct> orderProducts=new ArrayList<OrderProduct>();
        for (CartProduct cartProduct: cartProducts) {
            OrderProduct orderProduct=new OrderProduct();
            orderProduct.setName(cartProduct.getName());
            orderProduct.setPrice(cartProduct.getPrice());
            orderProduct.setQuantity(cartProduct.getQuantity());
            orderProduct.setProductId(cartProduct.getId());
            orderProducts.add(orderProduct);
        }
        order.setOrderProducts(orderProducts);
        order.setReceivedAddress(address);
        orderService.save(order);
        return "redirect:/client/home";
    }

    @GetMapping("/client/get-order")
    @ResponseBody
    public ResponseEntity<?> getCart(HttpSession session){
        if (session.getAttribute("CART")==null){
            return ResponseEntity.ok(new ArrayList<CartProduct>());
        }
        ArrayList<CartProduct> cartProducts = (ArrayList<CartProduct>) session.getAttribute("CART");
        return ResponseEntity.ok(cartProducts);

    }



}
