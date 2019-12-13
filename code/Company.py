from client.contractnote import ContractNote
from client.bcosclient import BcosClient
import os
from eth_utils import to_checksum_address
from client.datatype_parser import DatatypeParser
from client.common.compiler import Compiler
from client.bcoserror import BcosException, BcosError
from client_config import client_config


class Company:

    def __init__(self, name):
        self.client = BcosClient()
        self.name = name
        self.path = 'storage/' + name + '/'
    
    def getTransaction(self):
        with open(self.path + 'transaction.txt','r') as f:
            content = f.read()
            if content == '':
                print('No transaction in progress')
            else:
                print(content)
        return
    
    def makeDebt(self, to_name, value, duration):
        Compiler.compile_file(self.path + 'contracts/Book.sol', output_path=self.path+'contracts')
        Compiler.compile_file(self.path + 'contracts/Trade.sol', output_path=self.path+'contracts')
        abi_book = self.path + 'contracts/Book.abi'
        abi_trade = self.path + 'contracts/Trade.abi'
        #data_parser1 = DatatypeParser()
        #data_parser1.load_abi_file(abi_book)
        data_parser2 = DatatypeParser()
        data_parser2.load_abi_file(abi_trade)
        #book_abi = data_parser1.contract_abi
        trade_abi = data_parser2.contract_abi

        # create a debt book
        with open(self.path + 'contracts/Book.bin', 'r') as f:
            book_bin = f.read()
            f.close()
        result = self.client.deploy(book_bin)
        log_str = '>Deploy Book' + '-'*20 + '\n'
        log_str += str(result)
        debt_address = result['contractAddress']
        with open('share/debt_address.txt', 'w') as g:
            g.write(debt_address)
            g.close()
        
        # use the debt book to make a trade
        with open(self.path + 'contracts/Trade.bin', 'r') as f:
            trade_bin = f.read()
            f.close()
        args = [to_checksum_address(debt_address), value, duration * 3600 * 24, 0]
        result = self.client.deploy(trade_bin)
        log_str += '>Deploy Trade' + '-'*20 + '\n'
        log_str += str(result)
        trade_address = result['contractAddress']
        print('Trade address: ', trade_address)
        self.client.sendRawTransactionGetReceipt(trade_address, trade_abi, "makeTrade", args)

        with open(self.path + 'transaction.txt', 'a') as g:
            g.write('OwedDebt ' + to_name + ' ' + str(value) + ' ' + str(duration) + ' ' +trade_address + '\n')
    
        with open(self.path + 'log.txt', 'a') as g:
            g.write(log_str)
            g.write('\n\n')
        
        with open('share/trade_address.txt', 'a') as g:
            g.write(self.name + ' ' + to_name + ' ' + trade_address + '\n')
        
        return

    def makeTransfer(self, from_name, to_name, value, duration):
        Compiler.compile_file(self.path + 'contracts/Trade.sol', output_path=self.path+'contracts')
        abi_trade = self.path + 'contracts/Trade.abi'
        data_parser = DatatypeParser()
        data_parser.load_abi_file(abi_trade)
        trade_abi = data_parser.contract_abi

        # get debt book's address
        with open('share/debt_address.txt', 'r') as f:
            debt_address = f.read()

        # use the debt book to transfer a trade
        with open(self.path + 'contracts/Trade.bin', 'r') as f:
            trade_bin = f.read()
            f.close()
        args = [to_checksum_address(debt_address), value, duration * 3600 * 24, 1]
        result = self.client.deploy(trade_bin)
        log_str = '>Deploy Trade' + '-'*20 + '\n'
        log_str += str(result)
        trade_address = result['contractAddress']
        print('Trade address: ', trade_address)
        self.client.sendRawTransactionGetReceipt(trade_address, trade_abi, "makeTrade", args)

        with open(self.path + 'transaction.txt', 'a') as g:
            g.write('TransferDebt ' + from_name + ' ' +  to_name + ' ' + str(value) + ' ' + str(duration) + ' ' +trade_address + '\n')
    
        with open(self.path + 'log.txt', 'a') as g:
            g.write(log_str)
            g.write('\n\n')
        
        with open('share/trade_address.txt', 'a') as g:
            g.write(self.name + ' ' + to_name + ' ' + trade_address + '\n')
        
        return
    
    def makeFinance(self, value, duration):
        Compiler.compile_file(self.path + 'contracts/Trade.sol', output_path=self.path+'contracts')
        abi_trade = self.path + 'contracts/Trade.abi'
        data_parser = DatatypeParser()
        data_parser.load_abi_file(abi_trade)
        trade_abi = data_parser.contract_abi


        with open('share/debt_address.txto_namet', 'r') as f:
            debt_address = f.read()

        with open(self.path + 'contracts/Trade.bin', 'r') as f:
            trade_bin = f.read()
            f.close()
        args = [to_checksum_address(debt_address), value, duration * 3600 * 24, 2]
        result = self.client.deploy(trade_bin)
        log_str = '>Deploy Trade' + '-'*20 + '\n'
        log_str += str(result)
        trade_address = result['contractAddress']
        print('Trade address: ', trade_address)
        self.client.sendRawTransactionGetReceipt(trade_address, trade_abi, "makeTrade", args)

        with open(self.path + 'transaction.txt', 'a') as g:
            g.write('Finance ' + ' ' + str(value) + ' ' + str(duration) + ' ' +trade_address + '\n')
    
        with open(self.path + 'log.txt', 'a') as g:
            g.write(log_str)
            g.write('\n\n')
        
        with open('share/trade_address.txt', 'a') as g:
            g.write(self.name + ' ' + 'bank' + ' ' + trade_address + '\n')
        
        return
    

    def confirmTrade(self, from_name, value, duration):
        from_path = './storage/' + from_name + '/'
        abi_trade = from_path + 'contracts/Trade.abi'
        data_parser = DatatypeParser()
        data_parser.load_abi_file(abi_trade)
        trade_abi = data_parser.contract_abi
        with open('share/trade_address.txt', 'r') as f:
            for line in f:
                word_list = line.split()
                if word_list[0] == from_name and word_list[1] == self.name:
                    trade_address = word_list[-1]
                    break
        args = [value]
        receipt = self.client.sendRawTransactionGetReceipt(trade_address, trade_abi, 'confirmTrade', args)
        with open(self.path + 'transaction.txt', 'a') as g:
            g.write('OwnedDebt ' + from_name + ' ' + str(value) + ' ' + str(duration) + ' ' +trade_address + '\n')
        with open(self.path + 'log.txt', 'a') as g:
            g.write(str(receipt))
            g.write('\n\n')
        
        return