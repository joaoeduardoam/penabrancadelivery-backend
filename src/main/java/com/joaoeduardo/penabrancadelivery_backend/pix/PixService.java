package com.joaoeduardo.penabrancadelivery_backend.pix;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PixService {

    @Value("${CLIENT_ID}")
    private String clientId;

    @Value("${CLIENT_SECRET}")
    private String clientSecret;

    public JSONObject pixCreateEVP() {

        JSONObject options = getJSONObjectOptions(new Credentials());

        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("pixCreateEvp", new HashMap<String, String>(), new JSONObject());
            System.out.println(response.toString());
            return response;
        } catch (EfiPayException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;

    }

    public JSONObject pixCreateCharge(PixChargeRequest pixChargeRequest) throws Exception {


        JSONObject options = getJSONObjectOptions(new Credentials());

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("txid", "7978c0c97ea847e78e8849634473c1f2");

        JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", 3600));
        body.put("devedor", new JSONObject().put("cpf", "12345678909").put("nome", "Francisco da Silva"));
        body.put("valor", new JSONObject().put("original", pixChargeRequest.value()));
        body.put("chave", pixChargeRequest.keyPix());
        body.put("solicitacaoPagador", "Serviço realizado.");

        JSONArray infoAdicionais = new JSONArray();
        infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));
        infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", "Informação Adicional2 do PSP-Recebedor"));
        body.put("infoAdicionais", infoAdicionais);


        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("pixCreateCharge", params, body);
            System.out.println(response);
            return response;
        } catch (EfiPayException e) {
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;

    }

    private JSONObject getJSONObjectOptions(Credentials credentials){

        JSONObject options = new JSONObject();
        options.put("client_id", clientId);
        options.put("client_secret", clientSecret);
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        return options;

    }


}
