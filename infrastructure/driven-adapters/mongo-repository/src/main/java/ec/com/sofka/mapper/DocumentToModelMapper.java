package ec.com.sofka.mapper;

import ec.com.sofka.account.Account;
import ec.com.sofka.branch.Branch;
import ec.com.sofka.card.Card;
import ec.com.sofka.customer.Customer;
import ec.com.sofka.data.*;
import ec.com.sofka.transaction.Transaction;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class DocumentToModelMapper {
    public static final Function<Mono<AccountDocument>, Mono<Account>> toAccount = account ->
            account.map(accountDocument -> {
                Account accountModel = new Account();
                BeanUtils.copyProperties(accountDocument, accountModel);
                return accountModel;
            });

    public static final Function<Mono<BranchDocument>, Mono<Branch>> toBranch = branch ->
            branch.map(branchDocument -> {
                Branch branchModel = new Branch();
                BeanUtils.copyProperties(branchDocument, branchModel);
                return branchModel;
            });

    public static final Function<Mono<CardDocument>, Mono<Card>> toCard = card ->
            card.map(cardDocument -> {
                Card cardModel = new Card();
                BeanUtils.copyProperties(cardDocument, cardModel);
                return cardModel;
            });

    public static final Function<Mono<CustomerDocument>, Mono<Customer>> toCustomer = customer ->
            customer.map(customerDocument -> {
                Customer customerModel = new Customer();
                BeanUtils.copyProperties(customerDocument, customerModel);
                return customerModel;
            });

    public static final Function<Mono<TransactionDocument>, Mono<Transaction>> toTransaction = transaction ->
            transaction.map(transactionDocument -> {
                Transaction transactionModel = new Transaction();
                Branch branchModel = new Branch();
                Account sourceAccountModel = new Account();
                Account destinationAccountModel = new Account();
                BeanUtils.copyProperties(transactionDocument, transactionModel);
                BeanUtils.copyProperties(transactionDocument.getBranch(), branchModel);
                BeanUtils.copyProperties(transactionDocument.getSourceAccount(), sourceAccountModel);
                BeanUtils.copyProperties(transactionDocument.getDestinationAccount(), destinationAccountModel);
                transactionModel.setSourceAccount(sourceAccountModel);
                transactionModel.setDestinationAccount(destinationAccountModel);
                transactionModel.setBranch(branchModel);
                return transactionModel;
            });
}
