package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class ActivitySectorImplTest {
    @Mock
    private ActivitySectorRepository activitySectorRepository;

    @InjectMocks
    private ActivitySectorImpl activitySectorService;

    @Test
    void retrieveActivitySector() {
        // Prepare test data
        ActivitySector sector = new ActivitySector();
        sector.setIdSecteurActivite(1L);
        sector.setLibelleSecteurActivite("Test Sector");

        // Mock behavior
        when(activitySectorRepository.findById(1L)).thenReturn(Optional.of(sector));

        // Call the method from the service
        ActivitySector retrievedSector = activitySectorService.retrieveActivitySector(1L);

        // Assert the result
        assertNotNull(retrievedSector);
        assertEquals(sector.getIdSecteurActivite(), retrievedSector.getIdSecteurActivite());
        assertEquals(sector.getLibelleSecteurActivite(), retrievedSector.getLibelleSecteurActivite());
    }

    @Test
    void deleteActivitySector() {
        // Prepare test data
        ActivitySector sector = new ActivitySector();
        sector.setIdSecteurActivite(1L);
        sector.setLibelleSecteurActivite("Test Sector");

        // Mock behavior
        when(activitySectorRepository.findById(1L)).thenReturn(Optional.of(sector));

        // Call the method from the service to delete the sector
        activitySectorService.deleteActivitySector(1L);

        // Verify the sector is deleted by verifying repository method call
        verify(activitySectorRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateActivitySector() {
        // Prepare test data
        ActivitySector sector = new ActivitySector();
        sector.setIdSecteurActivite(1L);
        sector.setLibelleSecteurActivite("Test Sector");

        // Mock behavior
        when(activitySectorRepository.findById(1L)).thenReturn(Optional.of(sector));
        when(activitySectorRepository.save(sector)).thenReturn(sector);

        // Modify the sector
        sector.setLibelleSecteurActivite("Updated Sector");

        // Call the method from the service to update the sector
        ActivitySector updatedSector = activitySectorService.updateActivitySector(sector);

        // Assert the result
        assertNotNull(updatedSector);
        assertEquals(sector.getIdSecteurActivite(), updatedSector.getIdSecteurActivite());
        assertEquals(sector.getLibelleSecteurActivite(), updatedSector.getLibelleSecteurActivite());
    }
}