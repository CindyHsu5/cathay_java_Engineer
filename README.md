# cathay_java_Engineer

## About
A simple database service for maintaining coins

## Installation
專案運行方式是使用本機`Spring Boot App`

## Used
- Java:8
- Spring Boot:2.7.7
- 資料庫:H2(OpenJPA / Spring Data JPA)

## API URLs
1. 查詢幣別對應表資料API，並顯示其內容。
        - POST http://localhost:9100/cathay/coin/doCoinQuery
2. 新增幣別對應表資料API。
        - POST http://localhost:9100/cathay/coin/createCoin
3. 更新幣別對應表資料API，並顯示其內容。
        - POST http://localhost:9100/cathay/coin/updateCoin
4. 刪除幣別對應表資料API。
        - POST http://localhost:9100/cathay/coin/deleteCoin
5. coindesk API，並顯示其內容。
        - GET http://localhost:9100/cathay/coin/callCoindeskAPI
6. 資料轉換的API，並顯示其內容。
        - GET http://localhost:9100/cathay/coin/convertData