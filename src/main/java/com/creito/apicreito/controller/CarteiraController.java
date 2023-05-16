package com.creito.apicreito.controller;

import com.creito.apicreito.dto.CarteiraDTO;
import com.creito.apicreito.dto.CarteiraItemDTO;
import com.creito.apicreito.entity.Carteira;
import com.creito.apicreito.entity.CarteiraItem;
import com.creito.apicreito.response.Response;
import com.creito.apicreito.service.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

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
    @GetMapping(value = "/{carteira}")
    public ResponseEntity<Response<CarteiraDTO>> find(@PathVariable("carteira")Long carteira) {
        Response<CarteiraDTO> response = new Response<CarteiraDTO>();
        Optional<Carteira> c = service.find(carteira);
        CarteiraDTO dto = this.convertEntityToDto(c.get());
        response.setData(dto);
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
