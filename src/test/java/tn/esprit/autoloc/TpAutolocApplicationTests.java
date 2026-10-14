package tn.esprit.autoloc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.domain.enums.CategorieVehicule;
import tn.esprit.autoloc.domain.enums.StatutVehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IClientRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TpAutolocApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    IAgenceRepository agenceRepository;


    @Autowired
    IClientRepository clientRepository;

    @Autowired
    IVehiculeRepository vehiculeRepository;

    @Test
    @Order(1)
    void testAgenceRepository() {
        System.out.println("**Agenceee**");
        Agence agence = new Agence();
        agence.setNom("Agence1");
        agence.setTelephone("14522212");
        agence.setAdresse("esprit");
        agence.setVille("tunis");

        Agence saved = agenceRepository.save(agence);
        System.out.println("agence cree : " + saved);

        List<Agence> agences = agenceRepository.findAll();
        System.out.println("toutes les agences : " + agences);
        assertFalse(agences.isEmpty());

        Long id = saved.getIdAgence();
        Agence trouve = agenceRepository.findById(id).orElse(null);
        System.out.println("agence trouvee : " + trouve);
        assertEquals("Agence1", trouve.getNom());

        assertTrue(agenceRepository.existsById(id));

        long nb = agenceRepository.count();
        System.out.println("nombre d'agences : " + nb);
        assertTrue(nb >= 1);

        agenceRepository.deleteById(id);
        assertFalse(agenceRepository.existsById(id));
    }

    @Test
    @Order(2)
    void testClientRepository() {
        System.out.println("**Client**");
        Client client1 = new Client();
        client1.setNom("c1");
        client1.setPrenom("c1per");
        client1.setTelephone("14522212");
        client1.setEmail("c1@mail.com");

        Client client2 = new Client();
        client2.setNom("c2");
        client2.setPrenom("c2per");
        client2.setTelephone("1487812");
        client2.setEmail("c2@mail.com");

        Client s1 = clientRepository.save(client1);
        Client s2 = clientRepository.save(client2);
        System.out.println("Client 1 : " + s1);
        System.out.println("Client 2 : " + s2);

        List<Client> clients = clientRepository.findAll();
        System.out.println("Tous les clients : " + clients);
        assertFalse(clients.isEmpty());

        Client trouve = clientRepository.findById(s1.getIdClient()).orElse(null);
        System.out.println("Client trouve : " + trouve);
        assertNotNull(trouve);
        assertEquals("c1", trouve.getNom());

        assertTrue(clientRepository.existsById(s1.getIdClient()));
        assertFalse(clientRepository.existsById(9999L));

        long nb = clientRepository.count();
        System.out.println("nombre de clients : " + nb);
        assertTrue(nb >= 2);

    }

    @Test
    @Order(3)
    void testVehiculeRepository() {
        System.out.println("**Vehicule**");
        Vehicule v = new Vehicule();
        v.setImmatriculation("145bh45");
        v.setMarque("porche");
        v.setModele("jdida nabra");

        Vehicule saved = vehiculeRepository.save(v);
        System.out.println("vehicule cree : " + saved);

        List<Vehicule> vehicules = vehiculeRepository.findAll();
        System.out.println("Tous les vehicules : " + vehicules);
        assertFalse(vehicules.isEmpty());

        long nb = vehiculeRepository.count();
        System.out.println("nombre de vehicules : " + nb);
        assertTrue(nb >= 1);

        saved.setModele("mch jdida nabra");
        Vehicule modified = vehiculeRepository.save(saved);
        System.out.println("vehicule modifie : " + modified);
        assertEquals("mch jdida nabra", modified.getModele());
        assertEquals(saved.getIdVehicule(), modified.getIdVehicule());

        vehiculeRepository.deleteById(saved.getIdVehicule());
        assertFalse(vehiculeRepository.existsById(saved.getIdVehicule()));


    }








}
