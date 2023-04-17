##### The application implements following method:

1) Get all available crypto currencies:

    URL:`localhost:8080/crypto-currency-watcher/available`
    
    Method: `Get`

    Response:```[
   {
   "id": 2,
   "externalId": 80,
   "symbol": "ETH"
   },
   {
   "id": 1,
   "externalId": 90,
   "symbol": "BTC"
   },
   {
   "id": 3,
   "externalId": 48543,
   "symbol": "SOL"
   }
   ]```
2) Get crypto currency price by id:

   URL:`localhost:8080/crypto-currency-watcher/2`

   Method: `Get`

   Response: `{
   "id": 2,
   "externalId": 80,
   "name": "Ethereum",
   "symbol": "ETH",
   "priceUsd": 2080.88,
   "updatedAt": "2023-04-18T01:57:05.285178"
   }`
3) Subscribe to price changes notifications

   URL:`localhost:8080/crypto-currency-watcher/subscribe`

   Method: `Post`
   
   Body: `{
   "symbol":"BTC",
   "username":"rodion"
   }`

   Response: `{
   "id": 1,
   "symbol": "BTC",
   "username": "rodion",
   "subscriptionPriceUsd": 29514.85
   }`


When current price for the specified currency has changed by more than 1%,
following message will be logged:
`rodion, price change of BTC is -1.655 percent`

All crypto currencies that the application works with are described in `currencies.json` 
file in the resources folder in the following format:
`
[
{
"externalId": "90",
"symbol": "BTC"
},
{
"externalId": "80",
"symbol": "ETH"
},
{
"externalId": "48543",
"symbol": "SOL"
}
]`
Where externalId is id for CoinLore API

