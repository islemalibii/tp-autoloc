package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.web.dto.VehiculeDTO;

import java.util.List;

@Tag(name = "Vehicules", description = "Gestion des vehicules")
@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;
    @Operation(summary = "Lister les vehicules")
    @ApiResponse(responseCode = "200", description = "Liste des vehicules")
    @GetMapping
    public List<VehiculeDTO> findAll() {
        return vehiculeService.findAll();}

    @Operation(summary = "Consulter un vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule trouve"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")})
    @GetMapping("/{id}")
    public VehiculeDTO findById(@Parameter(description = "Identifiant du vehicule") @PathVariable Long id) {
        return vehiculeService.findById(id);}

    @Operation(summary = "Creer un vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vehicule cree"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculeDTO create(@RequestBody VehiculeDTO vehicule) {
        return vehiculeService.create(vehicule);}

    @Operation(summary = "Modifier un vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule modifie"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")})
    @PutMapping("/{id}")
    public VehiculeDTO update(@Parameter(description = "Identifiant du vehicule") @PathVariable Long id,
                           @RequestBody VehiculeDTO vehicule) {
        return vehiculeService.update(id, vehicule);}

    @Operation(summary = "Supprimer un vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vehicule supprime"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant du vehicule") @PathVariable Long id) {
        vehiculeService.deleteById(id);}


    @Operation(summary = "Affecter un vehicule a une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vehicule affecte"),
            @ApiResponse(responseCode = "404", description = "Vehicule ou agence introuvable")
    })
    @PutMapping("/{vehiculeId}/affecter-agence/{agenceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void affecterAAgence(@PathVariable Long vehiculeId, @PathVariable Long agenceId) {
        vehiculeService.affecterVehiculeAAgence(vehiculeId, agenceId);
    }

    @Operation(summary = "Desaffecter un vehicule de son agence")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vehicule desaffecte"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable"),
            @ApiResponse(responseCode = "400", description = "Vehicule rattache a aucune agence")
    })
    @PutMapping("/{vehiculeId}/desaffecter-agence")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desaffecterDeAgence(@PathVariable Long vehiculeId) {
        vehiculeService.desaffecterVehiculeDeAgence(vehiculeId);
    }
}