package temp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
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
public class Book extends Contract {
    public static String BINARY = "608060405234801561001057600080fd5b5033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555034600281905550610711806100686000396000f30060806040526004361061008e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631e0029c8146100935780633fa4f245146100d4578063436f1712146100ff578063bbf722a214610164578063bf7b69ee146101d1578063c0ee8aec1461022c578063c378c0e214610283578063fe37d7be146102da575b600080fd5b34801561009f57600080fd5b506100be6004803603810190808035906020019092919050505061035f565b6040518082815260200191505060405180910390f35b3480156100e057600080fd5b506100e9610382565b6040518082815260200191505060405180910390f35b34801561010b57600080fd5b5061014a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610388565b604051808215151515815260200191505060405180910390f35b34801561017057600080fd5b5061018f60048036038101908080359060200190929190505050610438565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101dd57600080fd5b50610212600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061046b565b604051808215151515815260200191505060405180910390f35b34801561023857600080fd5b50610241610560565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561028f57600080fd5b506102c4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610586565b6040518082815260200191505060405180910390f35b3480156102e657600080fd5b50610345600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061059e565b604051808215151515815260200191505060405180910390f35b60008181548110151561036e57fe5b906000526020600020016000915090505481565b60025481565b60007fb9f8e1da1128befaeb8a1aded34e673b81468d97ff3b5a8841d2b14ee03d033060405160405180910390a16000829080600181540180825580915050906001820390600052602060002001600090919290919091505550600160008054905003600360008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506001905092915050565b60046020528060005260406000206000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000807f1e72f2e0e094c71cff20dc02252e8da139cb75fa23134e9fffbaf17d49e8aa2560405160405180910390a1600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205490508273ffffffffffffffffffffffffffffffffffffffff166108fc60008381548110151561050557fe5b90600052602060002001549081150290604051600060405180830381858888f1935050505015801561053b573d6000803e3d6000fd5b506000808281548110151561054c57fe5b906000526020600020018190555050919050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60036020528060005260406000206000915090505481565b600080600360008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050826000828154811015156105f357fe5b9060005260206000200154101561060d57600091506106dd565b7fb9f8e1da1128befaeb8a1aded34e673b81468d97ff3b5a8841d2b14ee03d033060405160405180910390a18260008281548110151561064957fe5b90600052602060002001600082825403925050819055506000839080600181540180825580915050906001820390600052602060002001600090919290919091505550600160008054905003600360008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600191505b5093925050505600a165627a7a7230582036f66bf4a098aeaf3cd4e047efda2661ebb467b9f4730d88f26d3578051432140029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"debt\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"value\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"debtOwner\",\"type\":\"address\"},{\"name\":\"number\",\"type\":\"uint256\"}],\"name\":\"makeDebt\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"addressMap\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"debtOwner\",\"type\":\"address\"}],\"name\":\"payDebt\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"debtMaker\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"nameMap\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"number\",\"type\":\"uint256\"}],\"name\":\"transferDebt\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[],\"name\":\"DebtChanged\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[],\"name\":\"DebtPaid\",\"type\":\"event\"}]";

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_DEBT = "debt";

    public static final String FUNC_VALUE = "value";

    public static final String FUNC_MAKEDEBT = "makeDebt";

    public static final String FUNC_ADDRESSMAP = "addressMap";

    public static final String FUNC_PAYDEBT = "payDebt";

    public static final String FUNC_DEBTMAKER = "debtMaker";

    public static final String FUNC_NAMEMAP = "nameMap";

    public static final String FUNC_TRANSFERDEBT = "transferDebt";

    public static final Event DEBTCHANGED_EVENT = new Event("DebtChanged", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event DEBTPAID_EVENT = new Event("DebtPaid", 
            Arrays.<TypeReference<?>>asList());
    ;

    @Deprecated
    protected Book(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Book(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Book(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Book(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<BigInteger> debt(BigInteger param0) {
        final Function function = new Function(FUNC_DEBT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> value() {
        final Function function = new Function(FUNC_VALUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> makeDebt(String debtOwner, BigInteger number) {
        final Function function = new Function(
                FUNC_MAKEDEBT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(debtOwner), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(number)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void makeDebt(String debtOwner, BigInteger number, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_MAKEDEBT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(debtOwner), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(number)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String makeDebtSeq(String debtOwner, BigInteger number) {
        final Function function = new Function(
                FUNC_MAKEDEBT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(debtOwner), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(number)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getMakeDebtInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_MAKEDEBT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getMakeDebtOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_MAKEDEBT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<String> addressMap(BigInteger param0) {
        final Function function = new Function(FUNC_ADDRESSMAP, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> payDebt(String debtOwner) {
        final Function function = new Function(
                FUNC_PAYDEBT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(debtOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void payDebt(String debtOwner, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_PAYDEBT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(debtOwner)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String payDebtSeq(String debtOwner) {
        final Function function = new Function(
                FUNC_PAYDEBT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(debtOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getPayDebtInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_PAYDEBT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Tuple1<Boolean> getPayDebtOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_PAYDEBT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<String> debtMaker() {
        final Function function = new Function(FUNC_DEBTMAKER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> nameMap(String param0) {
        final Function function = new Function(FUNC_NAMEMAP, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transferDebt(String from, String to, BigInteger number) {
        final Function function = new Function(
                FUNC_TRANSFERDEBT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(number)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transferDebt(String from, String to, BigInteger number, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFERDEBT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(number)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferDebtSeq(String from, String to, BigInteger number) {
        final Function function = new Function(
                FUNC_TRANSFERDEBT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(number)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<String, String, BigInteger> getTransferDebtInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFERDEBT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<String, String, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public Tuple1<Boolean> getTransferDebtOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TRANSFERDEBT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public List<DebtChangedEventResponse> getDebtChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEBTCHANGED_EVENT, transactionReceipt);
        ArrayList<DebtChangedEventResponse> responses = new ArrayList<DebtChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DebtChangedEventResponse typedResponse = new DebtChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerDebtChangedEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(DEBTCHANGED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerDebtChangedEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(DEBTCHANGED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<DebtPaidEventResponse> getDebtPaidEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEBTPAID_EVENT, transactionReceipt);
        ArrayList<DebtPaidEventResponse> responses = new ArrayList<DebtPaidEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DebtPaidEventResponse typedResponse = new DebtPaidEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerDebtPaidEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(DEBTPAID_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerDebtPaidEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(DEBTPAID_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static Book load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Book(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Book load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Book(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Book load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Book(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Book load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Book(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Book> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Book.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Book> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Book.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Book> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Book.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Book> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Book.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class DebtChangedEventResponse {
        public Log log;
    }

    public static class DebtPaidEventResponse {
        public Log log;
    }
}
