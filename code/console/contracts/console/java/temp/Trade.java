package temp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint8;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class Trade extends Contract {
    public static String BINARY = "608060405234801561001057600080fd5b50604051608080610df383398101806040528101908080519060200190929190805190602001909291908051906020019092919080519060200190929190505050836000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550826003819055508142016004819055506100fa81610125640100000000026401000000009004565b600560006101000a81548160ff0219169083600281111561011757fe5b021790555050505050610156565b60008060009050600183141561013e576001905061014d565b600283141561014c57600290505b5b80915050919050565b610c8e806101656000396000f30060806040526004361061008e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806305a8da721461009357806308551a53146100ea57806320db38ed146101415780633fa4f2451461017a5780634665096d146101a55780637150d8ae146101d0578063b90526af14610227578063c671173514610256575b600080fd5b34801561009f57600080fd5b506100a86102bb565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156100f657600080fd5b506100ff6102e0565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561014d57600080fd5b50610156610306565b6040518082600281111561016657fe5b60ff16815260200191505060405180910390f35b34801561018657600080fd5b5061018f610319565b6040518082815260200191505060405180910390f35b3480156101b157600080fd5b506101ba61031f565b6040518082815260200191505060405180910390f35b3480156101dc57600080fd5b506101e5610325565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561023357600080fd5b5061023c61034b565b604051808215151515815260200191505060405180910390f35b34801561026257600080fd5b506102a160048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506105d5565b604051808215151515815260200191505060405180910390f35b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600560009054906101000a900460ff1681565b60035481565b60045481565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610412576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601a8152602001807f4f6e6c792073656c6c65722063616e2063616c6c20746869732e00000000000081525060200191505060405180910390fd5b600454421015151561048c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260198152602001807f446562742074696d65206861736e277420657870697265642e0000000000000081525060200191505060405180910390fd5b7fab5451892b1414df16603f001bd25c491a51e094cee61da33dbab4f2a8eb1db460405160405180910390a16000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663bf7b69ee600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15801561059657600080fd5b505af11580156105aa573d6000803e3d6000fd5b505050506040513d60208110156105c057600080fd5b81019080805190602001909291905050505090565b6000826003541415156105eb5760009050610c5c565b33600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600281111561063957fe5b600560009054906101000a900460ff16600281111561065457fe5b14156107b0577f570dae88cad75ed3753bf550cbdfaa490146247364e0f4c23ec2284476f3321960405160405180910390a16000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663436f1712600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166003546040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050602060405180830381600087803b15801561076e57600080fd5b505af1158015610782573d6000803e3d6000fd5b505050506040513d602081101561079857600080fd5b81019080805190602001909291905050509050610c5c565b600160028111156107bd57fe5b600560009054906101000a900460ff1660028111156107d857fe5b141561098a577f570dae88cad75ed3753bf550cbdfaa490146247364e0f4c23ec2284476f3321960405160405180910390a16000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fe37d7be600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166003546040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050602060405180830381600087803b15801561094857600080fd5b505af115801561095c573d6000803e3d6000fd5b505050506040513d602081101561097257600080fd5b81019080805190602001909291905050509050610c5c565b60028081111561099657fe5b600560009054906101000a900460ff1660028111156109b157fe5b1415610c57578173ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663c0ee8aec6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610a5357600080fd5b505af1158015610a67573d6000803e3d6000fd5b505050506040513d6020811015610a7d57600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff161415610c56577f570dae88cad75ed3753bf550cbdfaa490146247364e0f4c23ec2284476f3321960405160405180910390a16000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fe37d7be600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166003546040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050602060405180830381600087803b158015610c1457600080fd5b505af1158015610c28573d6000803e3d6000fd5b505050506040513d6020811015610c3e57600080fd5b81019080805190602001909291905050509050610c5c565b5b600090505b929150505600a165627a7a72305820dd48018c43fbc1d4018ea24ec67fc8526b57bb877df116968d2bc2ae3efd938c0029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[],\"name\":\"book\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"seller\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"tradeType\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"value\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"expiration\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"buyer\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"finishTrade\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_value\",\"type\":\"uint256\"},{\"name\":\"verifiedAddress\",\"type\":\"address\"}],\"name\":\"confirmTrade\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"_book\",\"type\":\"address\"},{\"name\":\"_value\",\"type\":\"uint256\"},{\"name\":\"duration\",\"type\":\"uint256\"},{\"name\":\"_type\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[],\"name\":\"TradeConfirmed\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[],\"name\":\"TradeFinished\",\"type\":\"event\"}]";

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_BOOK = "book";

    public static final String FUNC_SELLER = "seller";

    public static final String FUNC_TRADETYPE = "tradeType";

    public static final String FUNC_VALUE = "value";

    public static final String FUNC_EXPIRATION = "expiration";

    public static final String FUNC_BUYER = "buyer";

    public static final String FUNC_FINISHTRADE = "finishTrade";

    public static final String FUNC_CONFIRMTRADE = "confirmTrade";

    public static final Event TRADECONFIRMED_EVENT = new Event("TradeConfirmed", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event TRADEFINISHED_EVENT = new Event("TradeFinished", 
            Arrays.<TypeReference<?>>asList());
    ;

    @Deprecated
    protected Trade(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Trade(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Trade(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Trade(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<String> book() {
        final Function function = new Function(FUNC_BOOK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> seller() {
        final Function function = new Function(FUNC_SELLER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> tradeType() {
        final Function function = new Function(FUNC_TRADETYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> value() {
        final Function function = new Function(FUNC_VALUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> expiration() {
        final Function function = new Function(FUNC_EXPIRATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> buyer() {
        final Function function = new Function(FUNC_BUYER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> finishTrade() {
        final Function function = new Function(
                FUNC_FINISHTRADE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void finishTrade(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_FINISHTRADE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String finishTradeSeq() {
        final Function function = new Function(
                FUNC_FINISHTRADE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<Boolean> getFinishTradeOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_FINISHTRADE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> confirmTrade(BigInteger _value, String verifiedAddress) {
        final Function function = new Function(
                FUNC_CONFIRMTRADE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(verifiedAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void confirmTrade(BigInteger _value, String verifiedAddress, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CONFIRMTRADE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(verifiedAddress)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String confirmTradeSeq(BigInteger _value, String verifiedAddress) {
        final Function function = new Function(
                FUNC_CONFIRMTRADE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(verifiedAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<BigInteger, String> getConfirmTradeInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CONFIRMTRADE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<BigInteger, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getConfirmTradeOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CONFIRMTRADE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public List<TradeConfirmedEventResponse> getTradeConfirmedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRADECONFIRMED_EVENT, transactionReceipt);
        ArrayList<TradeConfirmedEventResponse> responses = new ArrayList<TradeConfirmedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TradeConfirmedEventResponse typedResponse = new TradeConfirmedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerTradeConfirmedEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRADECONFIRMED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerTradeConfirmedEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRADECONFIRMED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<TradeFinishedEventResponse> getTradeFinishedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRADEFINISHED_EVENT, transactionReceipt);
        ArrayList<TradeFinishedEventResponse> responses = new ArrayList<TradeFinishedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TradeFinishedEventResponse typedResponse = new TradeFinishedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerTradeFinishedEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRADEFINISHED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerTradeFinishedEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRADEFINISHED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static Trade load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Trade(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Trade load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Trade(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Trade load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Trade(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Trade load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Trade(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Trade> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _book, BigInteger _value, BigInteger duration, BigInteger _type) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_book), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(duration), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_type)));
        return deployRemoteCall(Trade.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Trade> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _book, BigInteger _value, BigInteger duration, BigInteger _type) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_book), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(duration), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_type)));
        return deployRemoteCall(Trade.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Trade> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _book, BigInteger _value, BigInteger duration, BigInteger _type) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_book), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(duration), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_type)));
        return deployRemoteCall(Trade.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Trade> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _book, BigInteger _value, BigInteger duration, BigInteger _type) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_book), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(duration), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_type)));
        return deployRemoteCall(Trade.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class TradeConfirmedEventResponse {
        public Log log;
    }

    public static class TradeFinishedEventResponse {
        public Log log;
    }
}
