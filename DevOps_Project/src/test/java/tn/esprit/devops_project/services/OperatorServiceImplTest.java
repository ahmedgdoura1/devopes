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

        Mockito.when(repositoryMock.findById(1L)).thenReturn(Optional.of(operator));

        // Act
        Operator retrievedOperator = serviceOperatorMock.retrieveOperator(1L);

        // Assert
        assertNotNull(retrievedOperator);
        assertEquals(1L, retrievedOperator.getIdOperateur()); // Improved assertion for better readability
        assertEquals("test", retrievedOperator.getFname());

        Mockito.verify(repositoryMock, Mockito.times(1)).findById(1L);

    }
}