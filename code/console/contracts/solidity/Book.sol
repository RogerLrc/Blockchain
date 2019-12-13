pragma solidity ^0.4.4;

contract Book{
    uint[] debt;
    address debtMaker;
    uint value;
    mapping(address => uint) public nameMap;
    mapping(uint => address) public addressMap;
    
    event DebtChanged();
    event DebtPaid();
    event ValueAdded()

    modifier onlyMaker(){
        require(
            msg.sender == debtMaker,
            "Only debt maker can call this."
        );
        _;
    }


    constructor(uint _value) public{
        debtMaker = msg.sender;
        value = _value;
    }
    function makeDebt(address debtOwner, uint number) public returns(bool){
        emit DebtChanged();
        debt.push(number);
        nameMap[debtOwner] = debt.length - 1;
        return true;
    }
    function transferDebt(address from, address to, uint number) public returns(bool){
        uint index = nameMap[from];
        if(debt[index] < number){
            return false;
        }
        emit DebtChanged();
        debt[index] -= number;
        debt.push(number);
        nameMap[to] = debt.length - 1;
        return true;
    }
    
    function payDebt(address debtOwner) public returns(bool){
        emit DebtPaid();
        uint index = nameMap[debtOwner];
        debtOwner.transfer(debt[index]);
        debt[index] = 0;
    }

    function addValue(uint _add) public onlyMaker returns(uint){
        emit ValueAdded();
        value += _add;
        return value;
    }

}