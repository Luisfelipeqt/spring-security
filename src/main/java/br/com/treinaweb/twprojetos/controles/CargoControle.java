package br.com.treinaweb.twprojetos.controles;

import br.com.treinaweb.twprojetos.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import br.com.treinaweb.twprojetos.entidades.Cargo;
import br.com.treinaweb.twprojetos.repositorios.CargoRepositorio;

import java.util.List;

@RestController
@RequestMapping("/cargos")
public class CargoControle {

    @Autowired
    private CargoRepositorio cargoRepositorio;

    @GetMapping(value = "/find", produces = { MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML
    })
    public List<Cargo> find(){
        return cargoRepositorio.findAll();
    }

    @GetMapping(produces = {
             MediaType.APPLICATION_JSON ,
             MediaType.APPLICATION_XML ,
             MediaType.APPLICATION_YML})
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("cargo/home");

        modelAndView.addObject("cargos", cargoRepositorio.findAll());

        return modelAndView;
    }

    @GetMapping(value = "/cadastrar",
            produces = {
                    MediaType.APPLICATION_JSON ,
                    MediaType.APPLICATION_XML ,
                    MediaType.APPLICATION_YML
    })
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("cargo/formulario");

        modelAndView.addObject("cargo", new Cargo());

        return modelAndView;
    }

    @GetMapping(value = "/{id}/editar",
    produces = {
            MediaType.APPLICATION_JSON ,
            MediaType.APPLICATION_XML ,
             MediaType.APPLICATION_YML},
    consumes = {
            MediaType.APPLICATION_JSON ,
            MediaType.APPLICATION_XML ,
             MediaType.APPLICATION_YML
    })
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cargo/formulario");

        modelAndView.addObject("cargo", cargoRepositorio.getOne(id));

        return modelAndView;
    }

    @PostMapping(value = {"/cadastrar", "/{id}/editar"},
    produces = {
            MediaType.APPLICATION_JSON ,
            MediaType.APPLICATION_XML ,
             MediaType.APPLICATION_YML},
    consumes = {
            MediaType.APPLICATION_JSON ,
            MediaType.APPLICATION_XML ,
             MediaType.APPLICATION_YML
    })
    public String salvar(Cargo cargo) {
        cargoRepositorio.save(cargo);

        return "redirect:/cargos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        cargoRepositorio.deleteById(id);

        return "redirect:/cargos";
    }
    
}
