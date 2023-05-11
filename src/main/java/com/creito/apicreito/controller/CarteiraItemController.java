package com.creito.apicreito.controller;

import com.creito.apicreito.dto.CarteiraItemDTO;
import com.creito.apicreito.entity.Carteira;
import com.creito.apicreito.entity.CarteiraItem;
import com.creito.apicreito.repository.CarteiraItemRepository;
import com.creito.apicreito.response.Response;
import com.creito.apicreito.service.CarteiraItemService;
import com.creito.apicreito.util.enums.TypeEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("carteira-item")
public class CarteiraItemController {
    @Autowired
    private CarteiraItemService service;

    @PostMapping
    public ResponseEntity<Response<CarteiraItemDTO>> create(@Valid @RequestBody CarteiraItemDTO dto, BindingResult result){
        Response<CarteiraItemDTO> response = new Response<CarteiraItemDTO>();

        if(result.hasErrors()){
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        CarteiraItem ci = service.save(this.convertDtoToEntity(dto));
        response.setData(this.convertEntityToDto(ci));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/{carteira}")
    public ResponseEntity<Response<Page<CarteiraItemDTO>>> findBetweenDates(@PathVariable("carteira")Long carteira,
        @RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
        @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate,
        @RequestParam(name = "page", defaultValue = "0") int page) {
        Response<Page<CarteiraItemDTO>> response = new Response<Page<CarteiraItemDTO>>();
        Page<CarteiraItem> items = service.findBetweenDates(carteira, startDate, endDate, page);
        Page<CarteiraItemDTO> dto = items.map(i-> this.convertEntityToDto(i));
        response.setData(dto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/type/{carteira}")
    public ResponseEntity<Response<List<CarteiraItemDTO>>> findByCarteiraAndType(@PathVariable("carteira")Long carteira,
                                                                            @RequestParam("type") String type){
        Response<List<CarteiraItemDTO>> response = new Response<List<CarteiraItemDTO>>();
        List<CarteiraItem> list = service.findByCarteiraAndType(carteira, TypeEnum.getEnum(type));
        List<CarteiraItemDTO> dto = new ArrayList<>();
        list.forEach(i-> dto.add(this.convertEntityToDto(i)));
        response.setData(dto);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping(value = "/total/{carteira}")
    public ResponseEntity<Response<BigDecimal>> sumByCarteiraId(@PathVariable("carteira")Long carteira){
        Response<BigDecimal> response = new Response<BigDecimal>();
        BigDecimal value  = service.sumByCarteiraId(carteira);
        response.setData(value == null ? BigDecimal.ZERO : value);
        return ResponseEntity.ok().body(response);
    }
    @PutMapping
    public ResponseEntity<Response<CarteiraItemDTO>> update(@Valid @RequestBody CarteiraItemDTO dto, BindingResult result){
        Response<CarteiraItemDTO> response = new Response<>();
        Optional<CarteiraItem> ci = service.findById(dto.getId());
        if(!ci.isPresent()){
            result.addError(new ObjectError("CarteiraItem", "Carteira item não encontrado"));
        }else{
            if(ci.get().getCarteira().getId().compareTo(dto.getCarteira())!=0){
                result.addError(new ObjectError("CarteiraItemChanged", "Voce não pode alterar a carteira"));
            }
        }
        if(result.hasErrors()){
            result.getAllErrors().forEach(r->response.getErrors().add(r.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        CarteiraItem saved = service.save(this.convertDtoToEntity(dto));
        response.setData(this.convertEntityToDto(saved));
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping(value = "/{carteiraItemId}")
    public ResponseEntity<Response<String>> delete(@PathVariable("carteiraItemId")Long carteiraItemId){
        Response<String> response = new Response<>();
        Optional<CarteiraItem> ci = service.findById(carteiraItemId);
        if(!ci.isPresent()){
            response.getErrors().add("Carteira de id "+ carteiraItemId + " não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        service.deletById(carteiraItemId);
        response.setData("Carteira de id "+ carteiraItemId  + " apagada com sucesso");
        return ResponseEntity.ok().body(response);
    }

    private CarteiraItem convertDtoToEntity(CarteiraItemDTO dto){
        CarteiraItem ci = new CarteiraItem();
        ci.setDate(dto.getDate());
        ci.setDescription(dto.getDescription());
        ci.setId(dto.getId());
        ci.setType(TypeEnum.getEnum(dto.getType()));
        ci.setValue(dto.getValue());

        Carteira c = new Carteira();
        c.setId(dto.getCarteira());
        ci.setCarteira(c);
        return ci;
    }

    private CarteiraItemDTO convertEntityToDto(CarteiraItem ci){
        CarteiraItemDTO dto = new CarteiraItemDTO();
        dto.setDate(ci.getDate());
        dto.setDescription(ci.getDescription());
        dto.setId(ci.getId());
        dto.setType(ci.getType().getValue());
        dto.setValue(ci.getValue());
        dto.setCarteira(ci.getCarteira().getId());
        return dto;
    }
}
