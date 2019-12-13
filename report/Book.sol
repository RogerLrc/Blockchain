pragma solidity ^0.4.4;
// 0xc968985d5dc90a011e426576485f87284293ddb6
//store accounts' debts
contract Book{
    uint[] debt;
    address public debtMaker;
    uint public value;
    mapping(address => uint) public nameMap;
    mapping(uint => address) public addressMap;
    
    event DebtChanged();
    event DebtPaid();

    constructor() public{
        debtMaker = msg.sender;
        value = msg.value;
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

}