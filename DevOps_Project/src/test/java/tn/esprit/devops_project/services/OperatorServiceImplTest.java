package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
    private OperatorRepository repositoryMock;
    @InjectMocks
    private OperatorServiceImpl serviceOperatorMock;
    @Test
    public void retrieveOperator(){
        Operator operator = new Operator();
        operator.setIdOperateur(1L);
        operator.setFname("test");

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(operator));

        // Act
        Operator retrievedOperator = serviceOperatorMock.retrieveOperator(1L);

        // Assert
        assertNotNull(retrievedOperator);
        assertEquals(1L, retrievedOperator.getIdOperateur()); // Improved assertion for better readability
        assertEquals("test", retrievedOperator.getFname());

        verify(repositoryMock, times(1)).findById(1L);

    }
    @Test
    public void addOperator() {
        // Create a sample operator to add
        Operator operatorToAdd = new Operator();
        operatorToAdd.setIdOperateur(1L);
        operatorToAdd.setFname("test");

        // Mock the behavior of the repository's save method
        when(repositoryMock.save(operatorToAdd)).thenReturn(operatorToAdd);

        // Call the method to add the operator
        Operator addedOperator = serviceOperatorMock.addOperator(operatorToAdd);

        // Verify that the repository's save method was called with the operatorToAdd
        verify(repositoryMock, times(1)).save(operatorToAdd);

        // Assert that the returned operator is the same as the one we added
        assertEquals(operatorToAdd, addedOperator);
    }
}
