package com.example.csis3275_yukaichao_300355866.web;


import com.example.csis3275_yukaichao_300355866.entities.Customer;
import com.example.csis3275_yukaichao_300355866.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@SessionAttributes({"a","e"})
@Controller
@AllArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(path="/")
    public String customers(Model model) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String tdateString = dtf.format(now);

        SimpleDateFormat formatter1 =new SimpleDateFormat("yyyy-MM-dd");
        Date tdate=formatter1.parse(tdateString);

        Customer customer = new Customer();

        model.addAttribute("customer", customer);
        model.addAttribute("seatCounter", 20);

        // load customer data
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customerlist", customers);


        return "seatReservation";
    }

    @GetMapping(path="/index")
    public String customers2(Model model) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String tdateString = dtf.format(now);

        SimpleDateFormat formatter1 =new SimpleDateFormat("yyyy-MM-dd");
        Date tdate=formatter1.parse(tdateString);

        Customer customer = new Customer();
        customer.setTdate(tdate);
        model.addAttribute("customer", customer);

        // load customer data
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customerlist", customers);


        return "seatReservation";
    }

    @PostMapping(path="/addCustomer")
    public String save(Model model, Customer customer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "seatReservation";
        } else {
            customerRepository.save(customer);

            return "redirect:index";
        }
    }

    @GetMapping(path="/delete")
    public String delete(Long id, ModelMap mm){

        customerRepository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/editCustomer")
    public String editCustomer(Model model, Long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer==null)throw new RuntimeException("Customer does not exist");
        model.addAttribute("customer", customer);
        return "formCustomer";
    }
}
