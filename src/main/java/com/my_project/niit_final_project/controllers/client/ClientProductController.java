package com.my_project.niit_final_project.controllers.client;
import com.my_project.niit_final_project.entities.Category;
import com.my_project.niit_final_project.entities.Product;
import com.my_project.niit_final_project.entities.ProductType;
import com.my_project.niit_final_project.services.CategoryService;
import com.my_project.niit_final_project.services.ProductService;
import com.my_project.niit_final_project.services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/client")
public class ClientProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/home")
    private String getHome(Model model) {
        try {
            Page<Product> listNewProduct = productService.getPageNewProduct(0, 8);
            Page<Product> listSaleProduct = productService.getPageSaleProduct(0, 8);
            Page<ProductType> listProductType = productTypeService.getPageProductType(0, 6);
            ProductType productType = new ProductType();
            int u = productTypeService.getProductTypeById(4).getCategories().size();
            model.addAttribute("listNewProduct", listNewProduct.iterator());
            model.addAttribute("listSaleProduct", listSaleProduct.iterator());
            model.addAttribute("listProductType", listProductType.iterator());

        } catch (ArithmeticException e) {
            //In ra màn hình tên ngoại lệ
            System.out.println(e);
        } finally
        { return "/client/home"; }



    }

    @GetMapping("/product/detail")
    private String getProductDetail(Model model, @RequestParam(name = "id") long id) {

        Product product = productService.getProductById(id);
        Page<ProductType> listProductType = productTypeService.getPageProductType(0, 6);
        model.addAttribute("listProductType", listProductType.iterator());
        model.addAttribute("product", product);
        return "/client/classify/product_detail";


    }

    @GetMapping("/category/product")
    private String getCategoryProduct(Model model, @RequestParam(name = "id") long id, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "activePage", defaultValue = "0") int activePage) {

        try {int totalPage = productService.getPageProductByCategoryId(page, 10, id).getTotalPages();
            //paging logic
            if (page > totalPage + 1) {
                Page<Product> listProductByCategory = productService.getPageProductByCategoryId(activePage, 10, id);
                Page<ProductType> listProductType = productTypeService.getPageProductType(0, 6);
                model.addAttribute("listProductType", listProductType.iterator());
                model.addAttribute("listProductByCategory", listProductByCategory);
                model.addAttribute("activePage", activePage);


            } else {
                Page<Product> listProductByCategory = productService.getPageProductByCategoryId(page, 10, id);
                Page<ProductType> listProductType = productTypeService.getPageProductType(0, 6);
                model.addAttribute("listProductType", listProductType.iterator());
                model.addAttribute("listProductByCategory", listProductByCategory);
                model.addAttribute("activePage", page);
            }} catch (Exception e){
            System.out.println(e);
        }
            finally{

            Category category = categoryService.getCategoryById(id);
            model.addAttribute("navCategory",category);
            model.addAttribute("id", id);
            return "/client/classify/category";}





    }


}
