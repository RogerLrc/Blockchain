from Company import Company
from Bank import Bank

def companyDisplay():
    print('-'*20)
    print('1 - Show transcation in progress')
    print('2 - Make a new transaction')
    print('3 - quit')
    print('-'*20)

def bankDisplay():
    print('-'*20)
    print('1 - Show finance in progress')
    print('2 - Make a new finance')
    print('3 - quit')
    print('-'*20)


def main():
    account_dic = {'car':'car', 'wheel':'wheel', 'hub':"hub", 'bank':'bank'}

    while True:
        name = input('Please Enter Your Account Name:  ')
        password = input('Please Enter Your Password:  ')
        if name not in password:
            print('invalid name')
        elif account_dic[name] != password:
            print('incorrect password')
        else:
            break

    if name != 'bank':
        company = Company(name)
        while True:
            companyDisplay()
            option = input('Please enter your option:  ')
            if option == '1':
                company.getTransaction()
            elif option == '2':
                print('Format: trade_type from to value duration')
                trade = input('Please enter your transaction:  ')
                # format: trade_type from to value duration
                [trade_type, from_name, to_name, value, duration] = trade.split()
                if trade_type == 'debt':
                    company.makeDebt(to_name, int(value), int(duration))
                elif trade_type == 'transfer':
                    company.makeTransfer(from_name, to_name, int(value), int(duration))
                elif trade_type == 'confirm':
                    company.confirmTrade(from_name, int(value), int(duration))
                print('Transaction deployed')
            elif option == '3':
                break
    
    if name == 'bank':
        bank = Bank(name)
        while True:
            bankDisplay()
            option = input('Please enter your option:  ')
            if option == '1':
                bank.showFinance()
            elif option == '2':
                print('Format: from value duration')
                trade = input('Please enter your finance: ')
                [from_name, value, duration] = trade.split()
                bank.makeFinance(from_name, int(value), int(duration))
            elif option == '3':
                break


if __name__ == '__main__':
    main()

