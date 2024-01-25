package dev.paulorievrs.spring.test.controller;

import dev.paulorievrs.spring.test.service.MercadoPagoService;
import dev.paulorievrs.spring.test.service.MercadoPagoWebhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class MercadoPagoController {

  @Autowired
  private MercadoPagoService mercadoPagoService;

  @GetMapping("/mp")
  public String getCheckoutUrl(){
    // Returns the mercado pago checkout url
    return mercadoPagoService.createPreference("100.00").getInitPoint();
  }

  @PostMapping("/mp")
  public ResponseEntity<?> paymentHook(@RequestBody MercadoPagoWebhook payload) {
    // Prints the received webhook payload for payments
    System.out.println("Received Mercado Pago webhook: " + payload.getId());
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
