# BankingApi

 Projeto de uma api simples de contas e transferencias.
 
## Estrutura
- [models](https://github.com/eliabevces/BankingApi/tree/master/src/main/java/com/testemeutudo/bankingapi/models "models")
	- [Account](https://github.com/eliabevces/BankingApi/blob/master/src/main/java/com/testemeutudo/bankingapi/models/Account.java "Account")
	- [BankTransaction](https://github.com/eliabevces/BankingApi/blob/master/src/main/java/com/testemeutudo/bankingapi/models/BankTransaction.java "BankTransaction")


- [Controllers](https://github.com/eliabevces/BankingApi/tree/master/src/main/java/com/testemeutudo/bankingapi/controllers "controllers")
	- [Account Controller](https://github.com/eliabevces/BankingApi/blob/master/src/main/java/com/testemeutudo/bankingapi/controllers/AccountController.java "AccountController.java")
	- [Bank Transaction Controller](https://github.com/eliabevces/BankingApi/blob/master/src/main/java/com/testemeutudo/bankingapi/controllers/BankTransactionController.java "BankTransactionController.java")

- [Repositories](https://github.com/eliabevces/BankingApi/tree/master/src/main/java/com/testemeutudo/bankingapi/repos "repo")
	- [Account Repository](https://github.com/eliabevces/BankingApi/blob/master/src/main/java/com/testemeutudo/bankingapi/repos/AccountRepository.java "Account Repository")
	- [Bank Transaction Repository](https://github.com/eliabevces/BankingApi/blob/master/src/main/java/com/testemeutudo/bankingapi/repos/BankTransactionRepository.java "Bank Transaction Repository")



## Endpoints

### GET Contas
- http://localhost:8080/account/

    - input: Nada

	- Exemplo de retorno :
		
``` json
[
	{
		"name": "nome 1",
		"cpf": "111111111",
		"balance": 999500.0,
		"payments": [
			{
				"id": 3,
				"paymentDate": "2022-06-24",
				"value": 1000.0
			},
			{
				"id": 4,
				"paymentDate": "2022-07-24",
				"value": 1000.0
			}
		],
		"receive": [
			{
				"id": 5,
				"paymentDate": "2022-06-24",
				"value": 500.0
			}
		],
		"accountId": 1
	},
	{
		"name": "nome 2",
		"cpf": "22222222222",
		"balance": 200500.0,
		"payments": [
			{
				"id": 5,
				"paymentDate": "2022-06-24",
				"value": 500.0
			}
		],
		"receive": [
			{
				"id": 3,
				"paymentDate": "2022-06-24",
				"value": 1000.0
			},
			{
				"id": 4,
				"paymentDate": "2022-07-24",
				"value": 1000.0
			}
		],
		"accountId": 2
	}
]
```
### GET transferências
- http://localhost:8080/transaction/

    - input: Nada

	- Exemplo de retorno :
		
``` json
[
	{
		"id": "3",
		"sender": 1,
		"receiver": 2,
		"paymentDate": "2022-06-24",
		"value": 1000.0
	},
	{
		"id": "4",
		"sender": 1,
		"receiver": 2,
		"paymentDate": "2022-07-24",
		"value": 1000.0
	},
	{
		"id": "5",
		"sender": 2,
		"receiver": 1,
		"paymentDate": "2022-06-24",
		"value": 500.0
	}
]
```
### Consultar Saldo (GET)
- http://localhost:8080/account/balance/{id}
    - input: Id da conta na url

	- Exemplo de retorno :
		
``` 
999500.0
```

### Consultar transferência especifica
- http://localhost:8080/transaction/{id}
    - input: Id da transferência na url

	- Exemplo de retorno :
		
``` 
{
	"id": 3,
	"paymentDate": "2022-06-24",
	"value": 1000.0
}
```

### Adicionar conta (POST)
- http://localhost:8080/account/add

    - input: 

     ![image](https://user-images.githubusercontent.com/38759694/175658849-5cf862ea-dafc-4c2e-a1f3-4989e67e51e1.png)

	- Exemplo de retorno :
		
``` json
{
	"name": "nome 1",
	"cpf": "111111111",
	"balance": 1000000.0,
	"payments": [],
	"receive": [],
	"accountId": 1
}
```

### Adicionar transferência (POST)
- http://localhost:8080/transaction/add
    - input: 

      ![image](https://user-images.githubusercontent.com/38759694/175659062-e17c7be0-6cb7-4d54-b8bb-75db4ae11dc5.png)

	- Exemplo de retorno :
		
``` json
[
	{
		"id": 5,
		"paymentDate": "2022-06-24",
		"value": 500.0
	}
]
```

### Reverter transferência (POST)
- http://localhost:8080/transaction/revert
    - input: 

    ![image](https://user-images.githubusercontent.com/38759694/175659236-f93a60f5-33af-463b-b9ac-0605844f7d49.png)

	- Exemplo de retorno :
		
``` 
Transaction reversed
```

