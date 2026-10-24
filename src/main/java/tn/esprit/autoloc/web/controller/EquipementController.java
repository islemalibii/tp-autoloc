package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@Tag(name = "Equipements", description = "Gestion des equipements")
@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipementService equipementService;
    @Operation(summary = "Lister les equipements")
    @ApiResponse(responseCode = "200", description = "Liste des equipements")
    @GetMapping
    public List<Equipement> findAll() {
        return equipementService.findAll();}

    @Operation(summary = "Consulter un equipement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipement trouve"),
            @ApiResponse(responseCode = "404", description = "Equipement introuvable")})
    @GetMapping("/{id}")
    public Equipement findById(@Parameter(description = "Identifiant de l'equipement") @PathVariable Long id) {
        return equipementService.findById(id);}

    @Operation(summary = "Creer un equipement")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Equipement cree"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipement create(@RequestBody Equipement equipement) {
        return equipementService.create(equipement);}

    @Operation(summary = "Modifier un equipement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipement modifie"),
            @ApiResponse(responseCode = "404", description = "Equipement introuvable"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")})
    @PutMapping("/{id}")
    public Equipement update(@Parameter(description = "Identifiant de l'equipement") @PathVariable Long id,
                             @RequestBody Equipement equipement) {
        return equipementService.update(id, equipement);}

    @Operation(summary = "Supprimer un equipement")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Equipement supprime"),
            @ApiResponse(responseCode = "404", description = "Equipement introuvable")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant de l'equipement") @PathVariable Long id) {
        equipementService.deleteById(id);}
}