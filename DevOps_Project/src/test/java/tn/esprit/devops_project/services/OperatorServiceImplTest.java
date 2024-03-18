package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class OperatorTest {
    @Mock
    private OperatorRepository repositoryMocks;
    @InjectMocks
    private OperatorServiceImpl serviceMocks;
    @SpringBootTest
    @Nested
    class OperatorServiceImplTest {
        @Autowired
        OperatorRepository operatorRepository;
        @Test
        void retrieveOperator() {
            Operator operator = new Operator();
            operator.setIdOperateur(1L);
            operator.setFname("test");

            Mockito.when(operatorRepository.findById(1L)).thenReturn(Optional.of(operator));

            // Act
            Operator retrievedOperator = serviceMocks.retrieveOperator(1L);

            // Assert
            assertNotNull(retrievedOperator);
            assertEquals(1L, retrievedOperator.getIdOperateur()); // Improved assertion for better readability
            assertEquals("test", retrievedOperator.getFname());

            Mockito.verify(repositoryMocks, Mockito.times(1)).findById(1L);

        }
    }
    @Test
    void addOperator() {
        // Prepare test data
        Operator operatorToAdd = new Operator();
        operatorToAdd.setFname("John");
        operatorToAdd.setLname("Doe");

        // Mock behavior
        when(repositoryMocks.save(any(Operator.class))).thenReturn(operatorToAdd);

        // Call the method from the service to add the operator
        Operator addedOperator = serviceMocks.addOperator(operatorToAdd);

        // Assert the result
        assertNotNull(addedOperator);
        assertEquals("Ahmed", addedOperator.getFname());
        assertEquals("Gdoura", addedOperator.getLname());

        // Verify that the repository method was called with the correct parameter
        verify(repositoryMocks, times(1)).save(operatorToAdd);
    }
}