package com.my_project.niit_final_project.controllers.admin;

import com.my_project.niit_final_project.entities.Banner;
import com.my_project.niit_final_project.entities.Product;
import com.my_project.niit_final_project.services.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/banner")
public class BannerController {
    @Autowired
   private BannerService bannerService;

    @GetMapping("/add")
    public String add(Model model){
        Banner  banner=new Banner();
        model.addAttribute("banner",banner );
        return "admin/banner/add";
    }

    @PostMapping("/do-add")
    public String doAddWithImage(Banner banner, RedirectAttributes flashSession, @RequestParam(name = "img") MultipartFile multipartFile) {
        if (bannerService.save(banner, multipartFile)) {
            flashSession.addFlashAttribute("success", "Add successfully");
        } else {
            flashSession.addFlashAttribute("failed", "Add failed");
        }
        return "redirect:/admin/banner/add";
    }

}
