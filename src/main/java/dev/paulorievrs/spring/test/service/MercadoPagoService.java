package dev.paulorievrs.spring.test.service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MercadoPagoService {
  private final PreferenceClient preferenceClient;

  public MercadoPagoService() {
    MercadoPagoConfig.setAccessToken("ACCESS_TOKEN");
    preferenceClient = new PreferenceClient();
  }

  public Preference createPreference(String value) {
    List<PreferenceItemRequest> items = new ArrayList<>();
    PreferenceItemRequest item =
        PreferenceItemRequest.builder()
            .title("Dummy Title")
            .description("Dummy description")
            .quantity(1)
            .currencyId("BRL")
            .unitPrice(new BigDecimal(value))
            .build();
    items.add(item);

    PreferenceRequest request = PreferenceRequest.builder().items(items).build();

    try {
      return preferenceClient.create(request);
    } catch (MPApiException ex) {
      System.out.printf(
          "MercadoPago Error. Status: %s, Content: %s%n",
          ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
    } catch (MPException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
