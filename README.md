# NBP REST API
___
### Purpose
This project is a RESTful API which uses the NBP API to fetch data as JSON.

It has two available endpoints:
- /api/exchange-rates/<b>[country code]</b>
- /api/gold-price/average

The first endpoint fetches the exchange rate for PLN to currency specified under the [country code] (<b>ISO 4217 ex. USD</b>) for the last 5 business days.

Second endpoint fetches the average gold price in PLN for the last 14 business days.

### Examples

Exchange rates

    /api/exchange-rates/EUR
    
    {
        "code" : "EUR",
        "rates" : [
            {
                "no" : "060/A/NBP/2022",
                "mid" : 4.7020,
                "effectiveDate" : "2022-03-28"
            }, 
            {
                "no" : "061/A/NBP/2022",
                "mid" : 4.7144,
                "effectiveDate" : "2022-03-29"
            },
            {
                "no" : "062/A/NBP/2022",
                "mid" : 4.6507,
                "effectiveDate" : "2022-03-30"
            },
            {
                "no" : "063/A/NBP/2022",
                "mid" : 4.6525,
                "effectiveDate" : "2022-03-31"
            },
            {
                "no" : "064/A/NBP/2022",
                "mid" : 4.6428,
                "effectiveDate" : "2022-04-01"
            }
        ],
        "currency" : "euro"
    }

Gold price

    /api/gold-price/average

    {
        "average_price_last_14_business_days": 265.62
    }
