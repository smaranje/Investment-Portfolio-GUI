import java.util.*;

/**
 * @author Smaranjeet Singh
 */
public class Portfolio {

    private ArrayList<Investment> list;
    private HashMap<String, ArrayList<Integer>> index;

    /**
     * Create a portfolio instance
     */
    public Portfolio() {
        list = new ArrayList<>();
        index = new HashMap<>();
    }

    // helper function 1

    public int checkFundUnique(String symbol) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSymbol().equals(symbol)) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @return The allSymbols of all the list
     */
    public ArrayList<String> gSymb() {
        ArrayList<String> allSymbols = new ArrayList<>();
        int i = 0;
        while (i < list.size()) {
            allSymbols.add(list.get(i).getSymbol());
            i++;
        }

        return allSymbols;
    }

    /**
     *
     * @return The allName of all the list
     */
    public ArrayList<String> Naam() {
        ArrayList<String> allName = new ArrayList<>();
        int i = 0;
        while (i < list.size()) {
            allName.add(list.get(i).getName());
            i++;
        }

        return allName;
    }

    /**

     *
     * @return The prices of all the list
     */
    public ArrayList<Double> Daam() {
        ArrayList<Double> prices = new ArrayList<>();
        int i = 0;
        while (i < list.size()) {
            Investment curInvest = list.get(i);
            prices.add(curInvest.getPrice());
            i++;
        }

        return prices;
    }


    /**

     *
     * @param type     Type of investment being bought
     * @param symbol   Symbol of investment
     * @param name     Name of investment
     * @param quantity Quantity being bought
     * @param price    Price being bought at
     * @return A string detailing the results of the buying
     */

    // -------------------------------------------------------------------------
    // --------------------------------FUNCTIONS--------------------------------
    // -------------------------------------------------------------------------

    public String buy(String type, String symbol, String name, String quantity, String price) throws BadInputException {

        Scanner s = new Scanner(System.in);
        boolean tf = false;
        int i = 0;
        double bValue = -1.0;
        String uChoice = null;
        int unique = -1;
        int unique2 = -1;
        String exists = null;

        // variables
        int getQuantity;
        String payment;


        uChoice = type;

        if (uChoice.equalsIgnoreCase("Stock")) {
            uChoice = "1";
        } else if (uChoice.equalsIgnoreCase("Mutual Fund")) {
            uChoice = "2";
        }

        //Ask user for input and checks if it is valid
        while (i < list.size()) {
            //If there is another investment with the same symbol
            if (checkFundUnique(symbol) != -1) {
                if (list.get(i) instanceof Stock) {
                    if (uChoice == "1") {
                        tf = true;
                        break;
                    } else {
                        return "\"Investment already exists as a Mutual Fund! Do you wish to update ?\n Warning: Investment Already Exists";
                    }
                } else if (list.get(i) instanceof MutualFund) {
                    if (uChoice == "2") {
                        tf = true;
                        break;
                    } else {
                        return "\"Investment already exists as a Mutual Fund! Do you wish to update ?\n Warning: Investment Already Exists";
                    }
                }
            }
            i++;
        }
        if (tf) {
            //Update the price of the investment and add the new shares to the investment

            //set price
            list.get(i).setPrice(price);

            //getQuantity
            getQuantity = list.get(i).getQuantity();

            // calculate price
            payment = list.get(i).calculatePrice(list.get(i).addQuantity(getQuantity, quantity));

            //Output how much they paid and how many shares they own
            return "Payment made: $" + payment + " Quantity owned " + getQuantity + " units\n" + list.get(i).toString();


        }
        //If it is a new stock
        else {

            switch (uChoice) {
                case "1":

                    Stock Tmp = new Stock(symbol, name, quantity, price);

                    list.add(Tmp);

                    break;
                case "2":
                    MutualFund tmp;

                    try {
                        tmp = new MutualFund(symbol,
                                name,
                                quantity,
                                price);
                        list.add(tmp);
                    } catch (BadInputException e) {
                        return "Invalid " + e.getMessage() + " input.";
                    }
                    break;
            }

            int position = list.size() - 1;

            String[] split = name.toLowerCase().split(" ");
            int j = 0;
            while (j < split.length) {
                String returnVal = split[j];
                ArrayList<Integer> current;
                current = new ArrayList<>();

                if (index.get(returnVal) == null) {
                } else {
                    current = index.get(returnVal);
                }
                 current.add(position);
                index.put(returnVal, current);
                j++;
            }

            Investment temp;
            temp = list.get(list.size() - 1);
            String payment2;
            payment2 = temp.calculatePrice(temp.getQuantity());
            String s1 = "Amount paid: $" + payment2 + " Quantity owned: " + quantity + " Units: s\n" + temp.toString();
            return s1;
        }
    }

    /**
     *
     * @param symbol   Symbol of investment being sold
     * @param quantity Quantity being sold
     * @param price    Price being sold for
     * @return A string detailing the results of the selling
     */
    public String sell(String symbol, String quantity, String price) {
        //If they don't own any stocks
        boolean b = false;


        //Find the stock they want to sell
        int i;
        i = 0;
        if (i >= list.size()) {
        } else {
            do {
                if (list.get(i).getSymbol().equals(symbol)) {
                    b = true;
                    break;
                } else {
                    i++;
                }
            } while (i < list.size());
        }

        if (list.isEmpty()) {
            String s = "You do not own any list";
            return s;
        }

        //If they entered a symbol thay doesn't correspond to a stuck
        if (b) {
            int soldQuantity;
            list.get(i).setPrice(price);
            try {
                soldQuantity = list.get(i).removeQuantity(quantity);
            } catch (BadInputException ex) {
                String message;
                message = ex.getMessage();
                if ("quantity".equals(message)) {
                    String s = "Quantity must be greater than zero.";
                    return s;
                } else if ("quantity".equals(message)) {
                    return "You do not own that many units.";
                }
                String s = "Invalid " + ex.getMessage() + " input";
                return s;
            }
            String output;
            //Tell them how much they made and how many shares they have left

            output = "You received $" + list.get(i).calculateProfit(soldQuantity) + " and have " + list.get(i).getQuantity() + " shares left\n" + list.get(i).toString();
            if (list.get(i).getQuantity() != 0) {
                return output;
            }
            Investment remove = list.remove(i);
            int position = 0;
            index.clear();
            int j = 0;
            while (j < list.size()) {
                String[] split = list.get(j).getName().toLowerCase().split(" ");
                for (int k = 0; k < split.length; k++) {
                    String word = split[k];
                    ArrayList<Integer> list = new ArrayList<>();
                    if (index.get(word) != null) {
                        list = index.get(word);
                    }
                    list.add(position);
                    ArrayList<Integer> put = index.put(word, list);
                }
                position = position + 1;
                j = j + 1;
            }
            return output;
        } else {
            return "You do not own a investment with that symbol. Please enter a new symbol";
        }
    }

    /**
     *
     * @param position Index of the investment being update
     * @param price    The new price of the investment
     * @return A string detailing the results of the update
     */
    public String update(int position, String price) {

        list.get(position).setPrice(price);

        String s1 = "Price changed to $\" + price + \"\\n\" + list.get(position).toString()";
        return s1;

    }

    /**
     *
     * @return A array containing the individual gains of all list and the total gain
     */
    public String[] getGain() {
        int i = 0;
        double tGain = 0;
        String gain = "";

        while (i < list.size()) {

            //Calculate the gain for the list then add it to the total gain
            double curGain = ((this.list.get(i).getQuantity() * this.list.get(i).getPrice()) - MutualFund.FEE) - this.list.get(i).getBookValue();
            //   curGain = investment.getGain();
            String temp = String.format("%.2f", curGain);
            gain = gain + ("The gain of " + list.get(i).getSymbol() + " is $" + temp + "\n");
            tGain = tGain + curGain;
            i++;
        }

        //System.out.println(index.toString());
        String[] output1 = {gain, String.format("$%.2f", tGain)};
        return output1;

    }

    /**
     *
     * @param symbol    The symbol being searched
     * @param keywords  The keywords being searched
     * @param lowPrice  The low price
     * @param highPrice The high price
     * @return A string detailing the results of the search
     */
    public String search(String symbol, String keywords, String lowPrice, String highPrice) throws NumberFormatException {

        String s1 = "Prices must be higher than zero.\n";
        String s2 = "Low price must be lower than high price.\n";

        ArrayList<Integer> RL;
        RL = new ArrayList<>();
        ArrayList<Integer> SL;
        SL = new ArrayList<>();
        boolean tf;
        boolean found = false;
        boolean left;
        boolean right;
        String output = "";
        String[] words;
        words = keywords.toLowerCase().split(" ");

        double low = !lowPrice.isEmpty() ? Double.parseDouble(lowPrice) : 0;
        double high;

        if (highPrice.isEmpty()) {
            high = Double.POSITIVE_INFINITY;
            if (high < 0 || low < 0) {
                return s1;
            }
            if (low > high) {
                return s2;
            }
            if (words[0].isEmpty()) {

                int i = 0;

                while (i < list.size()) {

                    if (!symbol.equals(list.get(i).getSymbol()) && !symbol.isEmpty()) {
                        tf = false;
                    } else {
                        //if it is within the price range
                        left = list.get(i).getPrice() >= low;
                        right = list.get(i).getPrice() <= high;
                        tf = left && right;

                    }
                    if (tf) {

                        output = output + (list.get(i).toString() + "\n");
                        found = true;
                    }
                    i = i + 1;
                }
            } else {

                if (index.get(words[0]) == null) {
                    return "Sorry, no list was found.\n";
                }
                SL.addAll(index.get(words[0]));

                int i = 0;
                while (i < words.length) {
                    String word;
                    word = words[i];
                    if (index.get(word) == null) {
                        return "Sorry, no list was found.\n";
                    } else {
                        int j = 0;
                        while (j < SL.size()) {
                            int x = SL.get(j);
                            if (!index.get(word).contains(x)) {
                                RL.add(x);
                            }
                            j++;
                        }
                    }
                    i++;
                }
                int j = 0;
                while (j < RL.size()) {
                    int x = RL.get(j);
                    if (!SL.contains(x)) {
                    } else {
                        SL.remove((Object) x);
                    }
                    j++;
                }
                int k = 0;
                while (k < SL.size()) {
                    int x = SL.get(k);
                    Investment curInvest = list.get(x);
                    if (!symbol.equals(curInvest.getSymbol()) && !symbol.isEmpty()) {
                        tf = false;
                    } else {
                        tf = curInvest.getPrice() >= low && curInvest.getPrice() <= high;
                    }
                    if (tf) {
                        output = output + (curInvest.toString() + "\n");
                        found = true;
                    }
                    k = k + 1;
                }
            }
        } else {
            high = Double.parseDouble(highPrice);
            if (high < 0 || low < 0) {
                return s1;
            }
            if (low > high) {
                 return s2;
            }
            if (words[0].isEmpty()) {

                int i = 0;

                while (i < list.size()) {

                    if (!symbol.equals(list.get(i).getSymbol()) && !symbol.isEmpty()) {
                        tf = false;
                    } else {
                        //if it is within the price range
                        left = list.get(i).getPrice() >= low;
                        right = list.get(i).getPrice() <= high;
                        tf = left && right;

                    }
                    if (tf) {

                        output = output + (list.get(i).toString() + "\n");
                        found = true;
                    }
                    i = i + 1;
                }
            } else {

                if (index.get(words[0]) == null) {
                    return "No list were found\n";
                }
                SL.addAll(index.get(words[0]));

                for (String word : words) {
                    if (index.get(word) != null) {
                        for (int x : SL) {
                            if (!index.get(word).contains(x)) {
                                RL.add(x);
                            }
                        }
                    } else {
                        return "No list were found\n";
                    }
                }
                for (int x : RL) {
                    if (SL.contains(x))
                        SL.remove((Object) x);
                }
                for (int x : SL) {
                    Investment curInvest = list.get(x);
                    if (symbol.equals(curInvest.getSymbol()) || symbol.isEmpty()) {
                        tf = curInvest.getPrice() >= low && curInvest.getPrice() <= high;
                    } else {
                        tf = false;
                    }
                    if (tf) {
                        output += curInvest.toString() + "\n";
                        found = true;
                    }
                }
            }
        }

        //Get the keywords and split them up into any array


        if (!found) {
            String s = "Sorry but we can find your search request\n";
            return s;
        }
        return output;
    }

    /**
     *
     * @return A formating string representation of the investment
     */
    @Override
    public String toString() {
        String port = "";
        int i = 0;
        while (i < list.size()) {
            port = port + list.get(i).toString();
            i++;
        }
        return port;
    }

}

