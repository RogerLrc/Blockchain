from client.contractnote import ContractNote
from client.bcosclient import BcosClient
import os
from eth_utils import to_checksum_address
from client.datatype_parser import DatatypeParser
from client.common.compiler import Compiler
from client.bcoserror import BcosException, BcosError
from client_config import client_config



class Bank:

    def __init__(self, name):
        self.client = BcosClient()
        self.name = name
        self.path = './storage/' + name + '/'
    
    def showFinance(self):
        with open(self.path + 'transaction.txt','r') as f:
            content = f.read()
            if content == '':
                print('No finance in progress')
            else:
                print(content)
            return

    def makeFinance(self, from_name, value, duration):
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
            g.write('Finance ' + from_name + ' ' + str(value) + ' ' + str(duration) + ' ' +trade_address + '\n')
        with open(self.path + 'log.txt', 'a') as g:
            g.write(str(receipt))
            g.write('\n\n')

        return