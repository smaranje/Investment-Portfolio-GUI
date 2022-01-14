# Investment-Portfolio-GUI

******************************************
Smaranjeet Singh
November 30, 2021	smaranje@uoguelph.ca
******************************************

*******************
Program description
*******************


-The purpose of this program is to create a GUI to comminucate with an investment portfolio as created in assignment 1 and 2. It implements the main and GUI for an Investment Portfolio using swing and awt package classes.
-The GUI has a drop down menu which consists of 5 commands for the user to choose from namely buy, sell, update, getgain and search.
-The static buy/sell commission fees for stocks is $9.99. The redemption fee for mutual funds are $45.00.
- Basically, we are creating an OOP program that manages a stock inverster's portfolio, and to make it easier for the Investor, a GUI is implemented for ease of use.

*******************
User Guide
*******************
GUI
	- Click buy in command menu to buy
             	- Choose stock to buy stock
			- Choose mutual fund to buy mutual funds
			- Enter symbol (string)
			- Enter name (string)
			- Enter quantity (int)
			- Enter price (double)
			- Click buy
			- Click reset to reset text fields
	- Click sell in command menu to sell
			- Enter symbol (string)
			- Enter quantity (int)
			- Enter price (double)
			- Click sell to sell
			- Click reset to reset text fields
	- Click update in command menu to update
			- Click Next to go to the next investment
			- Click Prev to go to the last investment
			- Enter price (double)
			- Click Save to update the investment displayed
	- Click getGain on command menu to get the gain
	- Click search on command menu to search
			- Enter search term (symbol, key words, 					highRange, lowRange) 
			- Click search to search
			- Click reset to reset fields
	- Click quit in the command menu to end program and save file
	- Clicking X on the window will not save the file but will end the program

******************************************
   Assumption and Limitations   
******************************************

The assumption made are:

1. Only one investment would have a certain symbol,
2. The purchase and sale of stocks and funds did not have any 
affect on the prices
3. No negative numbers would be entered, 
4. No punctuation in the symbol or names if the investments.

The limitation:

User cannot search the Investments by the "type", as in they cannot select that they want to search for a mutual fund or a stock.

NOTE: It is assumed that the user will read the on screen command properly and follow instructions. It is also assumed that the user understands how to navigate through the menu bar in the program and that they understand the basics of running a program. 

******************************************
           Test Plan         
******************************************

1. Test Plan for Main

- If user enters a choice that isnt in the menu options or anything apart from buy, sell, update, getGain, search or quit is being entered, the program ask for the user to enter a new choice.


2. Test Plan for Portfolio:

Some sample test cases:

Type of Fund: Stock, Name: Google Technology Company, Symbol: GOOGLE, Quantity: 450, Price: 80.6, Book Value: 36279.99
 
Type of Fund: Mutual Fund, Name: CI Signature Select Canadian, Symbol: CIG677, Quantity: 450, Price: 20.0, Book Value: 9000.0 

Type of Fund: Stock, Name: Facebook Inc, Symbol: FB, Quantity: 1000, Price: 205.0, Book Value: 205009.99 

Type of Fund: Stock, Name: Intel Corporation, Symbol: INTC, Quantity: 350, Price: 58.99, Book Value: 20656.49

Notes: Entering an input that is invalid either in quantity or type will
result in the program asking you to enter a valid input. Quantity must be 0 or greater

Attempting to buy a stock or mutual fund with the same symbol as that
of an investment that already exists in the list of the opposite type
will result in no investment being purchased as investment symbols
must be unique. (ie. buying stock with symbol TD then attempting to buy
mutual fund with symbol TD)

Attempting to sell a quantity greater than the quantity previously bought
will result in no investments being sold. (ie. buying 50 stocks then
attempting to sell 100)

Attempting to sell an investment that has not previously been bought will
result in the program stating "Investment not found."

Searching for a word that does not appear exactly in an investments
name instance variable will result in nothing being printed

Searching for a price range that does not exist will not print out anything and will return to the menu

If an empty string is searched for, the program will return all
investments.

Exit the program with the current inventory

Run the program in and enter an empty search, all the investments in the inventory previously should remain

Sell all current investments in a certain company and then search for the company using the keyword, it should no
longer exist.

Enter two companies with similar names (ex. Toronto Bank and Toronto Star) then in the search, enter a common
keyword (ex. Toronto). It should print out both investments. Now try searching for the full company name of one
(ex. Enter Toronto Star). The other company shall not display. (Toronto Bank will not display). If you then search
Toronto Bank Guelph, neither investment shall show.

On the Update interface if there are no investments all buttons should be disabled and nothing showing on the screen.



