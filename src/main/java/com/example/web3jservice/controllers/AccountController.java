package com.example.web3jservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

@Api
@RestController
public class AccountController {

    @PostMapping("/accounts/balance")
    @ApiOperation(value = "Get Ether Balance for a single Address")
    public ResponseEntity<String> getEtherBalance(String address) throws ExecutionException, InterruptedException {
        Web3j web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/e4e6510f11544ac1aaa067eeff315655"));
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetBalance.getBalance();
        return ResponseEntity.ok(String.valueOf(nonce));
    }

    @GetMapping("contracts/balance")
    public ResponseEntity<String> getContractBalance(@RequestParam(value = "contractAddress") String contractAddress,
                                                     @RequestParam(value = "address") String address) throws IOException {
        Web3j web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/0f6634c64c214d03a633188b40f09bcf"));
        Transaction txnEthCall = Transaction.createEthCallTransaction(address,
                contractAddress,
                contractAddress + address.substring(2));

        Request<?, EthCall> ethCallRequest = web3.ethCall(txnEthCall, DefaultBlockParameterName.LATEST);
        return ResponseEntity.ok(ethCallRequest.send().getValue());
    }
}
