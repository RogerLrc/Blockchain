pragma solidity ^0.4.4;
import './Book.sol';
// 0xe6b68142b7e2c6a946f3465356ef42fed359e7a2
contract Trade{
    address public book;
    address public buyer;
    address public seller;
    int public value;
    uint public expiration;
    enum TradeType {Debt, Transfer, Finance}
    TradeType public tradeType;

    event TradeConfirmed();
    event TradeFinished();

    modifier onlySeller(){
        require(
            msg.sender == seller,
            "Only seller can call this."
        );
        _;
    }

    modifier timeExpired(){
        require(
            now >= expiration,
            "Debt time hasn't expired."
        );
        _;
    }

    // _type indicate trade type, 0-Debt, 1-Transfer, 2-Finace
    constructor() public{}

    function makeTrade(address _book, int _value, int duration, int _type) public returns(bool){
        book = _book;
        buyer = msg.sender;
        value = _value;
        expiration = now + uint(duration);
        tradeType = toType(_type);

        return true;
    }
    function confirmTrade(int _value) public returns(bool){
        if(value != _value){
            return false;
        }
        seller = msg.sender;

        if(tradeType == TradeType.Debt){
            emit TradeConfirmed();
            return Book(book).makeDebt(seller, value);
        }
        else if(tradeType == TradeType.Transfer){
            emit TradeConfirmed();
            return Book(book).transferDebt(buyer, seller, value);
        }
        else if(tradeType == TradeType.Finance){
            emit TradeConfirmed();
            return Book(book).transferDebt(buyer, seller, value);
        }
        return false;
    }
    function finishTrade() public onlySeller timeExpired returns(bool){
        emit TradeFinished();
        Book(book).payDebt(seller);
    }

    function toType(int _type) private pure returns(TradeType){
        TradeType tmp = TradeType.Debt;
        if(_type == 1){
            tmp = TradeType.Transfer;
        }
        else if(_type == 2){
            tmp = TradeType.Finance;
        }
        return tmp;
    }

}