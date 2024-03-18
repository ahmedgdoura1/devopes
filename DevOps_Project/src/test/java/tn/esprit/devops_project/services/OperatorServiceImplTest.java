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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class OperatorTest {
    @Mock
    private OperatorRepository repository;
    @InjectMocks
    private OperatorServiceImpl operatorMocksService;
    @SpringBootTest
    @Nested
    class OperatorServiceImplTest {
        @Autowired
        OperatorRepository operatorRepository;
        @Test
        void retrieveOperator() {
            Operator operator = new Operator(1L, "Operator1");
            operator.setIdOperateur(1L);
            operator.setFname("Test Operator");

            Mockito.when(operatorRepository.findById(1L)).thenReturn(Optional.of(operator));

            // Act
            Operator retrievedOperator = operatorMocksService.retrieveOperator(1L);

            // Assert
            assertNotNull(retrievedOperator);
            assertEquals(1L, retrievedOperator.getIdOperateur()); // Improved assertion for better readability
            assertEquals("test", retrievedOperator.getFname());

            Mockito.verify(repository, Mockito.times(1)).findById(1L);

        }
        @Test
        void AddOperator() {
            // Given
            Operator operatorToAdd = new Operator(1L, "Operator1");
            operatorToAdd.setIdOperateur(1L);
            operatorToAdd.setFname("Test Operator");

            // Configure mock repository
            when(operatorRepository.save(operatorToAdd)).thenReturn(operatorToAdd);

            // When
            Operator addedOperator = operatorMocksService.addOperator(operatorToAdd);

            // Then
            verify(operatorRepository, times(1)).save(operatorToAdd);
            assertEquals(operatorToAdd, addedOperator);
        }
        @Test
        void updateOperator() {
            // Given
            Operator operatorToUpdate = new Operator(1L, "Operator1");
            operatorToUpdate.setIdOperateur(1L);
            operatorToUpdate.setFname("Updated Operator");

            // Configure mock repository
            when(operatorRepository.save(operatorToUpdate)).thenReturn(operatorToUpdate);

            // When
            Operator updatedOperator = operatorMocksService.updateOperator(operatorToUpdate);

            // Then
            verify(operatorRepository, times(1)).save(operatorToUpdate);
            assertEquals(operatorToUpdate, updatedOperator);
        }
    }
    @Test
    void testDeleteOperator() {
        // Given
        long operatorIdToDelete = 1L;

        // When
        operatorMocksService.deleteOperator(operatorIdToDelete);

        // Then
        verify(repository, times(1)).deleteById(operatorIdToDelete);
    }

    @Test
    void testRetrieveAllOperators() {
        // Given
        List<Operator> operators = new ArrayList<>();
        operators.add(new Operator(1L, "Oper_1"));
        operators.add(new Operator(2L, "Oper_2"));
        operators.add(new Operator(3L, "Oper_3"));

        // Configure mock repository
        when(repository.findAll()).thenReturn(operators);

        // When
        List<Operator> retrievedOperators = operatorMocksService.retrieveAllOperators();

        // Then
        verify(repository, times(1)).findAll();
        assertEquals(3, retrievedOperators.size()); // Ensure correct number of operators is retrieved
    }
}