package ec.com.sofka.mapper;

import ec.com.sofka.account.Account;
import ec.com.sofka.branch.Branch;
import ec.com.sofka.card.Card;
import ec.com.sofka.customer.Customer;
import ec.com.sofka.data.response.*;
import ec.com.sofka.transaction.Transaction;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class DTOResponseMapper {

    public static final Function<Mono<Account>, Mono<AccountResponseDTO>> toAccountResponseDTO = account ->
            account.map(accountEntity -> {
                AccountResponseDTO accountDTO = new AccountResponseDTO();
                BeanUtils.copyProperties(accountEntity, accountDTO);
                return accountDTO;
            });

    public static final Function<Mono<Branch>, Mono<BranchResponseDTO>> toBranchResponseDTO = branch ->
            branch.map(branchEntity -> {
                BranchResponseDTO branchDTO = new BranchResponseDTO();
                BeanUtils.copyProperties(branchEntity, branchDTO);
                return branchDTO;
            });

    public static final Function<Mono<Card>, Mono<CardResponseDTO>> toCardResponseDTO = card ->
            card.map(cardEntity -> {
                CardResponseDTO cardDTO = new CardResponseDTO();
                BeanUtils.copyProperties(cardEntity, cardDTO);
                return cardDTO;
            });

    public static final Function<Mono<Customer>, Mono<CustomerResponseDTO>> toCustomerResponseDTO = customer ->
            customer.map(customerEntity -> {
                CustomerResponseDTO customerDTO = new CustomerResponseDTO();
                BeanUtils.copyProperties(customerEntity, customerDTO);
                return customerDTO;
            });

    public static final Function<Mono<Transaction>, Mono<TransactionResponseDTO>> toTransactionResponseDTO = transaction ->
            transaction.map(transactionEntity -> {
                TransactionResponseDTO transactionDTO = new TransactionResponseDTO();
                BranchResponseDTO branchDTO = new BranchResponseDTO();
                AccountResponseDTO sourceAccountDTO = new AccountResponseDTO();
                AccountResponseDTO destinationAccountDTO = new AccountResponseDTO();
                BeanUtils.copyProperties(transactionEntity, transaction);
                BeanUtils.copyProperties(transactionEntity.getSourceAccount(), sourceAccountDTO);
                BeanUtils.copyProperties(transactionEntity.getBranch(), branchDTO);
                BeanUtils.copyProperties(transactionEntity.getDestinationAccount(), destinationAccountDTO);
                transactionDTO.setSourceAccount(sourceAccountDTO);
                transactionDTO.setDestinationAccount(destinationAccountDTO);
                transactionDTO.setBranch(branchDTO);
                BeanUtils.copyProperties(transactionEntity, transactionDTO);
                return transactionDTO;
            });

    public static final Function<Mono<AccountResponseDTO>, Mono<Account>> toAccount = accountDTO ->
            accountDTO.map(DTO -> {
                Account account = new Account();
                BeanUtils.copyProperties(DTO, account);
                return account;
            });

    public static final Function<Mono<BranchResponseDTO>, Mono<Branch>> toBranch = branchDTO ->
            branchDTO.map(DTO -> {
                Branch branch = new Branch();
                BeanUtils.copyProperties(DTO, branch);
                return branch;
            });

    public static final Function<Mono<CardResponseDTO>, Mono<Card>> toCard = cardDTO ->
            cardDTO.map(DTO -> {
                Card card = new Card();
                BeanUtils.copyProperties(DTO, card);
                return card;
            });

    public static final Function<Mono<CustomerResponseDTO>, Mono<Customer>> toCustomer = customerDTO ->
            customerDTO.map(DTO -> {
                Customer customer = new Customer();
                BeanUtils.copyProperties(DTO, customer);
                return customer;
            });

    public static final Function<Mono<TransactionResponseDTO>, Mono<Transaction>> toTransaction = transactionDTO ->
            transactionDTO.map(DTO -> {
                Transaction transaction = new Transaction();
                Branch branch = new Branch();
                Account sourceAccount = new Account();
                Account destinationAccount = new Account();
                BeanUtils.copyProperties(DTO, transaction);
                BeanUtils.copyProperties(DTO.getSourceAccount(), sourceAccount);
                BeanUtils.copyProperties(DTO.getBranch(), branch);
                BeanUtils.copyProperties(DTO.getDestinationAccount(), destinationAccount);
                transaction.setSourceAccount(sourceAccount);
                transaction.setDestinationAccount(destinationAccount);
                transaction.setBranch(branch);
                return transaction;
            });
}