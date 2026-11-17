
package tn.esprit.autoloc.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IContratRepository;
import tn.esprit.autoloc.repository.IPaiementRepository;
import tn.esprit.autoloc.service.IPaiementService;
import tn.esprit.autoloc.web.dto.PaiementDTO;
import tn.esprit.autoloc.web.mapper.PaiementMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;
    private final IContratRepository contratRepository;
    private final PaiementMapper paiementMapper;

    @Override
    public PaiementDTO create(PaiementDTO dto) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Un nouveau paiement ne doit pas avoir d'identifiant");
        }
        Paiement paiement = paiementMapper.toEntity(dto);
        paiement.setContrat(contratRepository.findById(dto.getContratId())
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", dto.getContratId())));
        return paiementMapper.toDto(paiementRepository.save(paiement));
    }

    @Override
    @Transactional(readOnly = true)
    public PaiementDTO findById(Long id) {
        return paiementMapper.toDto(trouver(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementDTO> findAll() {
        return paiementRepository.findAll().stream()
                .map(paiementMapper::toDto)
                .toList();
    }

    @Override
    public PaiementDTO update(Long id, PaiementDTO dto) {
        Paiement existant = trouver(id);
        existant.setMontant(dto.getMontant());
        existant.setDatePaiement(dto.getDatePaiement());
        existant.setModePaiement(dto.getModePaiement());
        // idPaiement et contrat ne sont pas modifies
        return paiementMapper.toDto(paiementRepository.save(existant));
    }

    @Override
    public void deleteById(Long id) {
        if (!paiementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paiement", id);
        }
        paiementRepository.deleteById(id);
    }

    private Paiement trouver(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement", id));
    }
}