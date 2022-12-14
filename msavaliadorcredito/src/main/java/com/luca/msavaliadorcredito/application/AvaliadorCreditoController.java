package com.luca.msavaliadorcredito.application;

import com.luca.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import com.luca.msavaliadorcredito.application.ex.ErroComunicacaoMicroservicesException;
import com.luca.msavaliadorcredito.domain.model.DadosAvaliacao;
import com.luca.msavaliadorcredito.domain.model.RetornoAvaliacaoCliente;
import com.luca.msavaliadorcredito.domain.model.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        try {
           SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundException e) {
           return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados){
        try {
          RetornoAvaliacaoCliente retornoAvaliacaoCliente =
                  avaliadorCreditoService.realizarAvaliacao(dados.getCpf(), dados.getRenda());
          return ResponseEntity.ok(retornoAvaliacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
