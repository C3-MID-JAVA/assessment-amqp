package ec.com.sofka.gateway.repository;

import ec.com.sofka.request.BranchRequestDTO;
import ec.com.sofka.response.BranchResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BranchRepository {

    Mono<BranchResponseDTO> createBranch(Mono<BranchRequestDTO> branch);

    Mono<BranchResponseDTO> findBranchById(Mono<Integer> id);

    Mono<BranchResponseDTO> existsById(Mono<Integer> id);

    Mono<BranchResponseDTO> findBranchByName(Mono<String> name);

    Flux<BranchResponseDTO> findAll();

    Mono<BranchResponseDTO> updateBranch(Mono<BranchRequestDTO> branch);

    Mono<Void> deleteById(Mono<Integer> id);
}
