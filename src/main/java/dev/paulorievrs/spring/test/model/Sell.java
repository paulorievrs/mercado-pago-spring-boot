package dev.paulorievrs.spring.test.model;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "sells")
public class Sell {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "nickname")
  private String nickname;

  @Column(name = "date")
  private Date date;

  @Column(name = "product")
  private String product;

  public Sell() {}
  public Sell(String nickname, Date date, String product) {
    super();
    this.nickname = nickname;
    this.date = date;
    this.product = product;
  }
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public String getProduct() {
    return product;
  }
  public void setProduct(String product) {
    this.product = product;
  }
}
