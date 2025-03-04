package com.example.exam;

import com.example.exam.model.Product;
import com.example.exam.model.ReceiptRequest;
import com.example.exam.model.ReceiptResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class ExamApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testGetReceiptUseCase1() throws Exception {

        ReceiptRequest req = new ReceiptRequest();
        req.setState("CA");

        Product book  = new Product();
        book.setQuantity(1);
        book.setPrice(BigDecimal.valueOf(17.99));
        book.setName("book");


        Product potatoChips =new Product();
        potatoChips.setCategory("food");
        potatoChips.setPrice(BigDecimal.valueOf(3.99));
        potatoChips.setQuantity(1);
        potatoChips.setName("potatoChips");

        ArrayList<Product> products = new ArrayList<>();
        products.add(potatoChips);
        products.add(book);

        req.setProducts(products);


        String requestJson = objectMapper.writeValueAsString(req);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/receipt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        ReceiptResponse response = objectMapper.readValue(jsonResponse, ReceiptResponse.class);

        List<Product> respProducts = response.getProducts();

        for (int i = 0 ; i <respProducts.size();i++){
            assertEquals(products.get(i).getName(),respProducts.get(i).getName());
            assertEquals(products.get(i).getQuantity(),respProducts.get(i).getQuantity());
            assertEquals(products.get(i).getPrice(),respProducts.get(i).getPrice());
        }

        assertEquals(new BigDecimal("21.98"), response.getSubtotal());
        assertEquals(new BigDecimal("1.80"), response.getSalesTax());
        assertEquals(new BigDecimal("23.78"), response.getTotal());
    }


    @Test
    void testGetReceiptUseCase2() throws Exception {

        ReceiptRequest req = new ReceiptRequest();
        req.setState("NY");

        Product book  = new Product();
        book.setQuantity(1);
        book.setPrice(BigDecimal.valueOf(17.99));
        book.setName("book");

        Product pencils = new Product();
        pencils.setQuantity(3);
        pencils.setName("pencil");
        pencils.setPrice(BigDecimal.valueOf(2.99));


        ArrayList<Product> products = new ArrayList<>();
        products.add(pencils);
        products.add(book);

        req.setProducts(products);


        String requestJson = objectMapper.writeValueAsString(req);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/receipt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        ReceiptResponse response = objectMapper.readValue(jsonResponse, ReceiptResponse.class);

        List<Product> respProducts = response.getProducts();

        for (int i = 0 ; i <respProducts.size();i++){
            assertEquals(products.get(i).getName(),respProducts.get(i).getName());
            assertEquals(products.get(i).getQuantity(),respProducts.get(i).getQuantity());
            assertEquals(products.get(i).getPrice(),respProducts.get(i).getPrice());
        }

        assertEquals(new BigDecimal("26.96"), response.getSubtotal());
        assertEquals(new BigDecimal("2.40"), response.getSalesTax());
        assertEquals(new BigDecimal("29.36"), response.getTotal());
    }

    @Test
    void contextLoads() {
    }

}
