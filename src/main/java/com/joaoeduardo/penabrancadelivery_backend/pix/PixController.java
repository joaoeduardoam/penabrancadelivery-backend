package com.joaoeduardo.penabrancadelivery_backend.pix;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pix")
@RequiredArgsConstructor
public class PixController {

    private final PixService pixService;

    @GetMapping
    public ResponseEntity pixCreateEVP(){
        String response = pixService.pixCreateEVP();
        return ResponseEntity.ok(response);
    }

}
