# README

## 使用方式

环境：ubuntu 16.06, python 3.7.3

进入code文件夹

```bash
cd python-sdk && bash init_env.sh -p

# 激活python-sdk虚拟环境
source ~/.bashrc && pyenv activate python-sdk && pip install --upgrade pip

#安装依赖
pip install -r requirements.txt

# 创建四节点区块链
bash ./nodes/127.0.0.1/start_all.sh

# 使用程序
python main.py
```



## 项目背景

项目任务是基于区块链平台fisco搭建一个供应链系统，供应链上的企业可以通过使用账款单据进行交易，账款单据可以从供应链的中上游向下游企业转移。

具体需要实现的四个功能为：

1. 实现采购商品—签发应收账款 交易上链。例如车企从轮胎公司购买一批轮胎并 签订应收账款单据。
2. 实现应收账款的转让上链，轮胎公司从轮毂公司购买一笔轮毂，便将于车企的
   应收账款单据部分转让给轮毂公司。轮毂公司可以利用这个新的单据去融资或者要求车企到 期时归还钱款。
3. 利用应收账款向银行融资上链，供应链上所有可以利用应收账款单据向银行申
   请融资。
4. 应收账款支付结算上链，应收账款单据到期时核心企业向下游企业支付相应的欠
   款。

## 方案设计

整体设计方案如图：

![image](https://github.com/RogerLrc/Blockchain/blob/master/report/structure.png)



### 用户端设计

针对给任务设计两种python对象，company和bank。

Company表示供应链中上下游的企业

```python
class Company:

    def __init__(self, name):
        
    # 获取当前正在进行的交易信息
    def getTransaction(self):
    
    # 创建贷款账单交易
    def makeDebt(self, to_name, value, duration):
	
    # 创建账单转移交易
    def makeTransfer(self, from_name, to_name, value, duration):
    
    # 创建金融融资交易
    def makeFinance(self, value, duration):
	
    # 确认交易
    def confirmTrade(self, from_name, value, duration):

```

Bank表示银行等提供融资服务的金融机构

```python
class Bank:

    def __init__(self, name):
       
    # 显示正在进行的融资交易
    def showFinance(self):
	
    # 创建金融融资
    def makeFinance(self, from_name, value, duration):
```

针对该具体的任务创建了四个账户分别为car，wheel，hub，bank分别代表供应链上中下游的企业和银行。

### 链端设计

该项目相较于一般的支付任务的关键特征是不会及时支付，付款方会创造一个账款单据，并且该单据可以在下游企业之间进行转让。为此设计了contract合约Book用来存储账款单据并进行相关操作

```c++
contract Book{
    // 账单创建者地址，银行融资时通过检查账单创建者是否为可信任账户决定是否发放融资
    address public debtMaker;
    
    // debt用来存储该账单中debtMaker到期应付给中下游企业的账单金额。
    // 如果没有账单转移，debt只存储应还给中游企业的金额。
    // 如果中游企业进行了账单转移，则debt按转移顺序依次添加相应账单。
    int[] debt;
    int value;
    
    // nameMap将企业用户地址映射为debt中的index
    mapping(address => uint) public nameMap;
    
    event DebtChanged();
    event DebtPaid();

    constructor() public{}
    function makeDebt(address debtOwner, int number) public returns(bool){}
    function transferDebt(address from, address to, int number) public returns(bool){}
    function payDebt(address debtOwner, int number) public returns(bool){}

}
```

基于该账款单据合约设计相应的交易合约Trade

```c++
pragma solidity ^0.4.4

contract Trade{
    //账款单据合约的地址
    address book;
   
    address buyer;
    address seller;
    int value;
    
    //账款单据有效期
    int expiration;
    
    //交易类型，分别为账单交易、账单转移交易和银行融资，依次对应功能一二三
    enum TradeType {Debt, Transfer, Finance}
    TradeType type;

    event TradeConfirmed();
    event TradeFinished();

    modifier onlySeller(){
        require(
            msg.sender == seller,
            "Only seller can call this."
        );
        _;
    }
	
    // 检查账单有效期，有效期过了之后卖方可以从核心企业获取相应欠款，对应归能四。
    modifier timeExpired(){
        require(
            now >= expiration,
            "Debt time hasn't expired."
        );
        _;
    }
	
    // 创建交易，并存储账款单据
   constructor(address _book, int _value, int duration, string _type) public{}
    
    // 确认交易金额，对应融资交易银行还要确认账款单据的有效性
    function confirmTrade() public returns(bool){}
    
    // 账单到期付款，调用Book里的payDebt函数完成付款
    function finishTrade() public onlySeller timeExpired returns(bool){}

    function toType(string) private returns(TradeType){}

}
```

### 数据存储设计

具体的存储内容为：

![image](https://github.com/RogerLrc/Blockchain/blob/master/report/storage.png)

存储分为链上存储和本地存储，链上存储使用了solidity自带的基本数据结构和fisco提供的表结构，而本地存储则使用了python以及相关的库做文件管理。

