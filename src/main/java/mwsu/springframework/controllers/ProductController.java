package mwsu.springframework.controllers;

import mwsu.springframework.commands.CustomerForm;
import mwsu.springframework.commands.ProductForm;
import mwsu.springframework.domain.Product;
import mwsu.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by bingyang.wei on 5/6/2017.
 */
@RequestMapping("/product")
@Controller
public class ProductController {
    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"/list", "/"})
    public String listProducts(Model model){
        model.addAttribute("products", productService.listAll());
        return "product/list";
    }
    @RequestMapping("/show/{id}")
    public String getProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getById(id));
        return "product/show";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){

        model.addAttribute("productForm", productService.getForm(productService.getById(id)));
        return "product/productform";
    }

    @RequestMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("productForm", new ProductForm());
        return "product/productform";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ProductForm productFrom,
                                      BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "product/productform";
        }
        Product savedProduct = productService.saveOrUpdateForm(productFrom);

        return "redirect:/product/show/" + savedProduct.getId();
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        productService.delete(id);
        return "redirect:/product/list";
    }


}
