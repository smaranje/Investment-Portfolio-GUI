public class Stock extends Investment {
    
    public static final double FEE = 9.99;

      /**
     * Creates a stock instance
     * @param symbol the symbol of the stock
     * @param name the name of the stock
     * @param quantity how many shares are owned
     * @param price the price of the stock per share
     * @throws efaguy_a3.BadInputException An exception thrown when an invalid input is detected 
     */
    public Stock(String symbol, String name, String quantity, String price) throws BadInputException
    {
        super(symbol, name, quantity, price);
        this.setBookValue(9.99 + (this.getQuantity() * this.getPrice()));
    }

    /**
     * Creates a stock instance with a set bookValue
     * @param symbol the symbol of the stock
     * @param name the name of the stock
     * @param quantity how many shares are owned
     * @param price the price of the stock per share
     * @param bookVal the bookValue of the stock
     * @throws efaguy_a3.BadInputException An exception thrown when an invalid input is detected 
     */
    public Stock(String symbol, String name, String quantity, String price, String bookVal) throws BadInputException
    {
        super(symbol, name, quantity, price);
        double b;
        try
        {
            b = Double.parseDouble(bookVal);
        }
        catch(NumberFormatException ex)
        {
            throw new BadInputException("book val");
        }
        this.setBookValue(b);
    }
    
    public Stock(Investment original)
    {
        super(original);
    }
    
    @Override
    public int addQuantity(int curQuantity, String newQuantity)
    {
        int q;

            q = Integer.parseInt(newQuantity);

        double price = this.getPrice();
        int quantity = this.getQuantity();
        double book = this.getBookValue();

        q = q + curQuantity;
        this.setBookValue(book + (price * (q - quantity) + 9.99));
        this.setQuantity(q);
        int i = q - curQuantity;
        return i;
    }
    
    @Override
    public String calculatePrice(int quantity) {
        double price = this.getPrice();
        String paid = String.format("%.2f", (quantity * price + 9.99));
        return paid;
    }
    
    @Override
    public String calculateProfit(int soldQuantity)
    {
        double price = this.getPrice();
        String profit = String.format("%.2f", ((price * soldQuantity) - 9.99));
        return profit;
    }
    
    @Override
    public double getGain()
    {
        double price = this.getPrice();
        int quantity = this.getQuantity();
        double book = this.getBookValue();

        double curGain = (quantity * price - 9.99) - book;
        return curGain;
    }
    
    @Override
    public boolean equals(Object otherObject)
    {
        if(otherObject == null)
        {
            return false;
        }
        else if (getClass() != otherObject.getClass())
        {
            return false;
        }
        else
        {
            Stock other = (Stock)otherObject;
            return this.getSymbol().equals(other.getSymbol());
        }
    }    
}