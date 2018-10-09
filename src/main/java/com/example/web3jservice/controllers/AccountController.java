package com.example.web3jservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
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
}
