package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IReservationService;
import tn.esprit.autoloc.web.dto.ReservationRequestDTO;
import tn.esprit.autoloc.web.dto.ReservationResponseDTO;

import java.util.List;

@Tag(name = "Reservations", description = "Gestion des reservations")
@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;
    @Operation(summary = "Lister les reservations")
    @ApiResponse(responseCode = "200", description = "Liste des reservations")
    @GetMapping
    public List<ReservationResponseDTO> findAll() {
        return reservationService.findAll();}

    @Operation(summary = "Consulter une reservation")
    @ApiResponse(responseCode = "200", description = "Reservation trouvee")
    @ApiResponse(responseCode = "404", description = "Reservation introuvable")
    @GetMapping("/{id}")
    public ReservationResponseDTO findById(@Parameter(description = "Identifiant de la reservation") @PathVariable Long id) {
        return reservationService.findById(id);}

    @Operation(summary = "Creer une reservation")
    @ApiResponse(responseCode = "201", description = "Reservation creee")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponseDTO create(@RequestBody ReservationRequestDTO reservation) {
        return reservationService.create(reservation);}

    @Operation(summary = "Modifier une reservation")
    @ApiResponse(responseCode = "200", description = "Reservation modifiee")
    @ApiResponse(responseCode = "404", description = "Reservation introuvable")
    @ApiResponse(responseCode = "400", description = "Donnees invalides")
    @PutMapping("/{id}")
    public ReservationResponseDTO update(@Parameter(description = "Identifiant de la reservation") @PathVariable Long id,
                              @RequestBody ReservationRequestDTO reservation) {
        return reservationService.update(id, reservation);}

    @Operation(summary = "Supprimer une reservation")
    @ApiResponse(responseCode = "204", description = "Reservation supprimee")
    @ApiResponse(responseCode = "404", description = "Reservation introuvable")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant de la reservation") @PathVariable Long id) {
        reservationService.deleteById(id);}
}