package dev.paulorievrs.spring.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.paulorievrs.spring.test.exception.ResourceNotFoundException;
import dev.paulorievrs.spring.test.model.Sell;
import dev.paulorievrs.spring.test.repository.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class SellController {

  @Autowired
  private SellRepository sellRepository;

  @GetMapping("/sells")
  public List<Sell> getAllSells(){
    return sellRepository.findAll();
  }

  @PostMapping("/sells")
  public Sell createSell(@RequestBody Sell sell) {
    return sellRepository.save(sell);
  }

  @GetMapping("/sells/{id}")
  public ResponseEntity<Sell> getSellById(@PathVariable Long id) {
    Sell sell = sellRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Sell not exist with id :" + id));
    return ResponseEntity.ok(sell);
  }

  @PutMapping("/sells/{id}")
  public ResponseEntity<Sell> updateSell(@PathVariable Long id, @RequestBody Sell sellDetails){
    Sell sell = sellRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Sell not exist with id :" + id));

    sell.setNickname(sellDetails.getNickname());
    sell.setDate(sellDetails.getDate());
    sell.setProduct(sellDetails.getProduct());

    Sell updatedSell = sellRepository.save(sell);
    return ResponseEntity.ok(updatedSell);
  }

  @DeleteMapping("/sells/{id}")
  public ResponseEntity<Map<String, Boolean>> deleteSell(@PathVariable Long id){
    Sell sell = sellRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Sell not exist with id :" + id));

    sellRepository.delete(sell);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }
}
