## Zopa Calculation Quote Loan Application
This application provides prospective borrowers with a quote for a 36 month loan.

## Installation
Using Maven:
1) Install Maven (version >= 3.5.0)
2) Change console encoding to : UTF-8
3) Unzip the file
4) Change directory to unzipped directory
5) Run command: mvn clean package

## Usage
Using the jar create after building it with "mvn clean package", run the application with:

java -jar quotecalculator.jar [market_file_path] [loan_amount]

where [market_file] is the name of a CSV file listing lenders, rates and available amounts
	Ej: Market_Data.csv

and [loan_amount] is the value of the requested loan, must be between 1000 and 15000 in increments of 100
	Ej: 1000

## Example
java -jar target/quotecalculator.jar d:/filesMarket/Market_Data.csv 1000
# quotecalculator
