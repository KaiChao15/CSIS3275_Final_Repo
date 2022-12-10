package com.example.csis3275_yukaichao_300355866.web;

import com.example.csis3275_yukaichao_300355866.entities.Customer;
import com.example.csis3275_yukaichao_300355866.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
class CustomerControllerTest {

    Customer customer;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    View mockView;

    @InjectMocks
    CustomerController customerController;

    @BeforeEach
    void setup() throws ParseException {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John");

        String sDate1 = "2012/11/11";
        Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
        customer.setTdate(date1);
        customer.setSeatno("2B");

        MockitoAnnotations.openMocks(this);

        mockMvc = standaloneSetup(customerController).setSingleView(mockView).build();

    }

    @Test
    public void display() throws Exception {
        List<Customer> list = new ArrayList<>();
        list.add(customer);
        list.add(customer);

        when(customerRepository.findAll()).thenReturn(list);
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("customerlist", list))
                .andExpect(view().name("seatReservation"))
                .andExpect(model().attribute("customerlist", hasSize(2)));

        verify(customerRepository, times(1)).findAll();
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void save() {
        when(customerRepository.save(customer)).thenReturn(customer);
        customerRepository.save(customer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void delete() {
        ArgumentCaptor<Long> idCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(customerRepository).deleteById(idCapture.capture());
        customerRepository.deleteById(1L);
        assertEquals(1L, idCapture.getValue());
        verify(customerRepository, times(1)).deleteById(1L);
    }
}