package com.joaoeduardo.penabrancadelivery_backend.pix;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pix")
@RequiredArgsConstructor
public class PixController {

    private final PixService pixService;

    @GetMapping
    public ResponseEntity pixCreateEVP() {
        Optional<JSONObject> response = Optional.ofNullable(pixService.pixCreateEVP());

        if(response.isEmpty()){
            throw new PixCreateKeyException();
        }

        var rawResponse = response.get();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(rawResponse.toString());
    }

    @PostMapping
    public ResponseEntity pixCreateCharge(@RequestBody PixChargeRequest pixChargeRequest) throws Exception {
        Optional<JSONObject> response = Optional.ofNullable(pixService.pixCreateCharge(pixChargeRequest));

        if(response.isEmpty()){
            throw new PixCreateChargeException();
        }

        var rawResponse = response.get();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(rawResponse.toString());

    }

}
