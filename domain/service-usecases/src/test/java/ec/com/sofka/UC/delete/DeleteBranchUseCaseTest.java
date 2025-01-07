package ec.com.sofka.UC.delete;

import ec.com.sofka.branch.Branch;
import ec.com.sofka.gateway.repository.BranchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = DeleteBranchUseCaseTest.class)
public class DeleteBranchUseCaseTest {
    @Mock
    private BranchRepository repository;

    @InjectMocks
    private DeleteBranchUseCase deleteBranchUseCase;

    private Branch testBranch;

    @BeforeEach
    void setUp() {
        testBranch = new Branch("123 Main St", 1, "Test Branch", "123-456-7890");
    }

    @Test
    void testApply_ShouldReturnBranch() {
        when(repository.deleteById(Mockito.any(Mono.class)))
                .thenReturn(Mono.empty());
        Mono<Void> result = deleteBranchUseCase.apply(testBranch.getId());
        StepVerifier.create(result)
                .verifyComplete();
        Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.any(Mono.class));
    }

    @Test
    void testApply_ShouldReturnError_WhenRepositoryFails() {
        when(repository.deleteById(Mockito.any(Mono.class)))
                .thenReturn(Mono.error(new RuntimeException("Error saving branch")));
        Mono<Void> result = deleteBranchUseCase.apply(testBranch.getId());
        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
        verify(repository, times(1)).deleteById(Mockito.any(Mono.class));
    }
}