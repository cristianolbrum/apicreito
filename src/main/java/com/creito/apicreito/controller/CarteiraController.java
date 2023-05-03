package com.creito.apicreito.controller;

import com.creito.apicreito.dto.CarteiraDTO;
import com.creito.apicreito.entity.Carteira;
import com.creito.apicreito.response.Response;
import com.creito.apicreito.service.CarteiraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carteira")
public class CarteiraController {

    @Autowired
    private CarteiraService service;
    @PostMapping
    public ResponseEntity<Response<CarteiraDTO>> create(@Valid @RequestBody CarteiraDTO dto, BindingResult result) {
        Response<CarteiraDTO> response = new Response<CarteiraDTO>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        Carteira w = service.save(this.convertDtoToEntity(dto));
        response.setData(this.convertEntityToDto(w));

        return ResponseEntity.ok().body(response);
    }

    private Carteira convertDtoToEntity(CarteiraDTO dto){
        Carteira w = new Carteira();
        w.setId(dto.getId());
        w.setName(dto.getName());
        w.setValue(dto.getValue());

        return w;
    }

    private CarteiraDTO convertEntityToDto(Carteira w){
        CarteiraDTO dto = new CarteiraDTO();
        dto.setId(w.getId());
        dto.setName(w.getName());
        dto.setValue(w.getValue());

        return dto;
    }
}
