package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ActivitySectorImplTest {
    @Autowired
    private ActivitySectorRepository activitySectorRepository;

    @Autowired
    private ActivitySectorImpl activitySectorService;

    @Test
    void retrieveActivitySector() {
        // Prepare test data
        ActivitySector sector = new ActivitySector();
        sector.setCodeSecteurActivite("code1");
        sector.setLibelleSecteurActivite("Test Sector");
        ActivitySector savedSector = activitySectorRepository.save(sector);

        // Call the method from the service
        ActivitySector retrievedSector = activitySectorService.retrieveActivitySector(savedSector.getIdSecteurActivite());

        // Assert the result
        assertNotNull(retrievedSector);
        assertEquals(savedSector.getIdSecteurActivite(), retrievedSector.getIdSecteurActivite());
        assertEquals(savedSector.getLibelleSecteurActivite(), retrievedSector.getLibelleSecteurActivite());
    }

    @Test
    void deleteActivitySector() {
        // Prepare test data
        ActivitySector sector = new ActivitySector();
        sector.setCodeSecteurActivite("code1");
        sector.setLibelleSecteurActivite("Test Sector");
        ActivitySector savedSector = activitySectorRepository.save(sector);

        // Call the method from the service to delete the sector
        activitySectorService.deleteActivitySector(savedSector.getIdSecteurActivite());

        // Verify the sector is deleted by attempting to retrieve it
        Optional<ActivitySector> deletedSector = activitySectorRepository.findById(savedSector.getIdSecteurActivite());
        assertFalse(deletedSector.isPresent());
    }

    @Test
    void updateActivitySector() {
        // Prepare test data
        ActivitySector sector = new ActivitySector();
        sector.setCodeSecteurActivite("code1");
        sector.setLibelleSecteurActivite("Test Sector");
        ActivitySector savedSector = activitySectorRepository.save(sector);

        // Modify the sector
        savedSector.setLibelleSecteurActivite("Updated Sector");

        // Call the method from the service to update the sector
        ActivitySector updatedSector = activitySectorService.updateActivitySector(savedSector);

        // Assert the result
        assertNotNull(updatedSector);
        assertEquals(savedSector.getIdSecteurActivite(), updatedSector.getIdSecteurActivite());
        assertEquals(savedSector.getLibelleSecteurActivite(), updatedSector.getLibelleSecteurActivite());

        // Verify the sector is updated by retrieving it from the database
        Optional<ActivitySector> retrievedSector = activitySectorRepository.findById(updatedSector.getIdSecteurActivite());
        assertTrue(retrievedSector.isPresent());
        assertEquals("Updated Sector", retrievedSector.get().getLibelleSecteurActivite());
    }
}