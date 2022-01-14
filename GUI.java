import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**

 * @author Smaranjeet Singh
 */
public class GUI extends JFrame implements ActionListener{

    int position;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private int updateIndex = 0;

    ArrayList<String> symbols;
    ArrayList<String> names;
    ArrayList<Double> prices;

    //filename
    private String file;
    private Portfolio investments = new Portfolio();

    //These are the Panels
    private JPanel PanelMain = new JPanel();
    private JPanel PanelBuy = new JPanel();
    private JPanel PanelSell = new JPanel();
    private JPanel PanelUpdate = new JPanel();
    private JPanel PanelGain = new JPanel();
    private JPanel PanelSearch = new JPanel();

    //Objects for buy
    private JTextField Box = new JTextField();
    private JTextField nameBox = new JTextField();
    private JTextField quantityBox = new JTextField();
    private JTextField priceBox = new JTextField();
    private JTextArea  TArea = new JTextArea();

    //Objects for sell
    private JTextField Box2 = new JTextField();
    private JTextField quantityBox2 = new JTextField();
    private JTextField priceBox2 = new JTextField();
    private JTextArea  TArea2 = new JTextArea();

    //These are the objects for Update Section
    private JTextField Box3 = new JTextField();
    private JTextField nameBox3 = new JTextField();
    private JTextField priceBox3 = new JTextField();
    private JTextArea  TArea3 = new JTextArea();

    //These are the objects for Gain Section
    private JTextField gainBox = new JTextField();
    private JTextArea  TArea4 = new JTextArea();

    //These are the objects for Search Section
    private JTextField Box5 = new JTextField();
    private JTextField nameBox5 = new JTextField();
    private JTextField lowPriceBox = new JTextField();
    private JTextField highPriceBox = new JTextField();
    private JTextArea  TArea5 = new JTextArea();

    private JMenuItem searchMenuItem;




    //menu and menu bar
    private JMenu menu;
    private JMenuBar menuBar;

    //menu items
    private JMenuItem menuBuy, menuSell, menuUpdate, menuGain, menuSearch, menuQuit;

    private JPanel make2X5Panel(JLabel left, JComponent right)
    {
        JPanel temp = new JPanel();
        temp.setLayout(new GridLayout(2,5));
        left.setHorizontalAlignment(JLabel.LEFT);
        temp.add(left);
        temp.add(right);

        return temp;
    }

    private JPanel make1x2Panel(JComponent left, JComponent right)
    {
        JPanel temp = new JPanel();
        temp.setLayout(new GridLayout(1,2));
        temp.add(left);
        temp.add(right);

        return temp;
    }

    //************************************************//
    //***This section is for the Constructor**********//
    //************************************************//

    /**
     * A constructor for a GUI to buy, sell, update, get the gain and search an investment portfolio
     * @param filename The filename to load the save the investments to
     */
    public GUI(String filename){

        super("Sam's Investment Portfolio");

        this.investments = new Portfolio();

        file = filename;

       boolean galat = false;
       boolean sahi = true ;

        this.setSize(new Dimension(WIDTH,HEIGHT));
        BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        //panels
        PanelSearch.setLayout(new GridLayout(2,1));
        PanelBuy.setLayout(new GridLayout(2,1));
        PanelGain.setLayout(new BorderLayout());
        PanelSell.setLayout(new GridLayout(2,1));
        PanelMain.setLayout(new GridLayout(1,1));
        PanelUpdate.setLayout(new GridLayout(2,1));


        //closing the application
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        JTextArea area = new JTextArea(1, 1);
        area.setFont(new Font("Roboto", Font.PLAIN, 16));
        area.setText("\n\n\n\n\n\n                                                     " +
                "Welcome to Investment Portfolio\n\n\n\n");
        area.append("Choose a command from the \"Commands\" menu to buy or sell an investment, update prices for all \ninvestments, get gain for the portfolio, search for relevant investments, or quit the program.");
        JScrollPane ascroll = new JScrollPane(area);

        menu = new JMenu("Commands");

        menuBuy = new JMenuItem("Buy");
        menuSell = new JMenuItem("Sell");
        menuUpdate = new JMenuItem("Update");
        menuGain = new JMenuItem("Gain");
        menuSearch = new JMenuItem("Search");
        menuQuit = new JMenuItem("Quit");

        menu.add(menuBuy);
        menu.add(menuSell);
        menu.add(menuUpdate);
        menu.add(menuGain);
        menu.add(menuSearch);
        menu.add(menuQuit);

        menuBar = new JMenuBar();
        menuBar.add(menu);


        menuQuit.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });


        //************************************************************//
        //***This section is to create the Buy Section panels********//
        //************************************************************//

        JLabel type;
        JLabel symbol;
        JLabel name;
        JLabel quantity;
        JLabel price;
        JLabel MessageBox;
        JTextArea MessageOutput;

        JPanel Scroll = new JPanel();

        String[] Components = new String[]{"MutualFund", "Stock"};
        JComboBox iSelect = new JComboBox(Components);

        EmptyBorder MBorder;
        EmptyBorder titleBorder;

        type = new JLabel("Type");
        symbol = new JLabel("Symbol");
        name = new JLabel("Name");
        quantity = new JLabel(" Quantity");
        price = new JLabel(" Price");
        MessageBox= new JLabel("  Message Box");

        MBorder = new EmptyBorder(30,30,30,30);
        titleBorder = new EmptyBorder(25,25,25,25);

        MessageBox.setFont(new Font("", Font.ITALIC, 28));
        MessageBox.setBorder(titleBorder);

        Scroll.setLayout(new BorderLayout());
        MessageOutput = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane(MessageOutput);

        JPanel temp = new JPanel();
        temp.setLayout(new GridLayout(2,5));
        new JLabel("Type").setHorizontalAlignment(JLabel.LEFT);
        temp.add(new JLabel("Type"));
        temp.add(iSelect);
        JPanel lInvestSelect = temp;

        Scroll.add(scrollPane, BorderLayout.NORTH);
        Scroll.add(MessageBox, BorderLayout.CENTER);

        JPanel lBox = make2X5Panel(new JLabel("Symbol"), Box);
        JPanel lNameBox = make2X5Panel(new JLabel("Name"), nameBox);
        JPanel lQuantityBox = make2X5Panel(new JLabel("Quantity"), quantityBox);
        JPanel lPriceBox = make2X5Panel(new JLabel("Price"), priceBox);

        JPanel Bought = new JPanel();
        Bought.add(new JLabel("Lets buy an investment!"));

        Bought.setLayout(new GridLayout(4, 2));


        JPanel buyRight = new JPanel();
        buyRight.setLayout(new GridLayout(3,1));

        JButton Scoobidoo = new JButton("Reset");
        JButton buy = new JButton("Buy");

        Bought.add(lInvestSelect);
        Bought.add(lBox);
        Bought.add(lNameBox);
        Bought.add(lQuantityBox);
        Bought.add(lPriceBox);
        buyRight.add(Scoobidoo);
        buyRight.add(buy);

        JPanel buyTop = make1x2Panel(Bought, buyRight);


        JPanel buyBot = new JPanel();
        buyBot.setLayout(new BorderLayout());

        MessageBox= new JLabel("Message Box");
        MessageBox.setHorizontalAlignment(JLabel.CENTER);
        buyBot.add(MessageBox, BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane(TArea);
        buyBot.add(scroll, BorderLayout.CENTER);
        TArea.setEditable(galat);

        PanelBuy.add(buyTop);
        PanelBuy.add(buyBot);

        buy.addActionListener((ActionEvent ae) -> {
            String buy1 = null;
            try {
                buy1 = investments.buy((String)iSelect.getSelectedItem(), Box.getText(), nameBox.getText(), quantityBox.getText(), priceBox.getText());
            } catch (BadInputException e) {
                e.printStackTrace();
            }
            TArea.setText(buy1 + "\n");
        });

        Scoobidoo.addActionListener((ActionEvent ae) -> {
            Box.setText("");
            nameBox.setText("");
            quantityBox.setText("");
            priceBox.setText("");
        });

        menuBuy.addActionListener((ActionEvent ae) -> {
            PanelMain.setVisible(galat);
            PanelSell.setVisible(galat);
            PanelUpdate.setVisible(galat);
            PanelGain.setVisible(galat);
            PanelSearch.setVisible(galat);
            PanelBuy.setVisible(sahi);
            TArea.setText("");
            iSelect.setSelectedIndex(0);
            Box.setText("");
            nameBox.setText("");
            quantityBox.setText("");
            priceBox.setText("");
        });

        //************************************************************//
        //***This section is to create the Sell Section panels********//
        //************************************************************//

        menuSell.addActionListener((ActionEvent ae) -> {
            PanelMain.setVisible(galat);
            PanelBuy.setVisible(galat);
            PanelUpdate.setVisible(galat);
            PanelGain.setVisible(galat);
            PanelSearch.setVisible(galat);
            PanelSell.setVisible(sahi);
            TArea2.setText("");
            Box2.setText("");
            quantityBox2.setText("");
            priceBox2.setText("");
        });

        JPanel OnLeft = new JPanel();
        JPanel lBox2 = make1x2Panel(new JLabel("Symbol"), Box2);
        JPanel lQuantityBox2 = make1x2Panel(new JLabel("Quantity"), quantityBox2);
        JPanel lPriceBox2 = make1x2Panel(new JLabel("Price"), priceBox2);
        JPanel OnRight = new JPanel();
        JPanel sellTop = make1x2Panel(OnLeft, OnRight);
        JPanel sellBot = new JPanel();
        JLabel TA2 = new JLabel("TA");

        //buttons
        JButton sell = new JButton("Sell");
        JButton Scoobidoo2 = new JButton("Reset");
        JScrollPane scroll2 = new JScrollPane(TArea2);

        OnLeft.setLayout(new GridLayout(4, 1));
        OnLeft.add(new JLabel("Selling an investment"));

        OnLeft.add(lBox2);
        OnLeft.add(lQuantityBox2);
        OnLeft.add(lPriceBox2);

        OnRight.setLayout(new GridLayout(2,1));
        OnRight.add(Scoobidoo2);
        OnRight.add(sell);

        sellBot.setLayout(new BorderLayout());

        TA2.setHorizontalAlignment(JLabel.CENTER);
        sellBot.add(TA2, BorderLayout.NORTH);
        sellBot.add(scroll2, BorderLayout.CENTER);
        TArea2.setEditable(galat);

        PanelSell.add(sellTop);
        PanelSell.add(sellBot);

        Scoobidoo2.addActionListener((ActionEvent ae) -> {
            Box2.setText("");
            quantityBox2.setText("");
            priceBox2.setText("");
        });

        sell.addActionListener((ActionEvent ae) -> {
            String sell1 = investments.sell(Box2.getText(), quantityBox2.getText(), priceBox2.getText());
            TArea2.setText(sell1 + "\n");
        });


        //************************************************************//
        //***This section is to create the Update Section panels********//
        //************************************************************//

        JPanel Uparleft = new JPanel();
        JPanel lBox3 = make1x2Panel(new JLabel("Symbol"), Box3);
        JPanel lnameBox3 = make1x2Panel(new JLabel("Name"), nameBox3);
        JPanel lpriceBox3 = make1x2Panel(new JLabel("Price"), priceBox3);
        JPanel UparRight = new JPanel();
        JPanel upTop = make1x2Panel(Uparleft, UparRight);
        JPanel uparkabot = new JPanel();
        JLabel TA3 = new JLabel("TA");
        JScrollPane scroll3 = new JScrollPane(TArea3);

        //buttons
        JButton prev = new JButton("Prev");
        JButton next = new JButton("Next");
        JButton save = new JButton("Save");

        Uparleft.add(lBox3);
        Uparleft.add(lnameBox3);
        Uparleft.add(lpriceBox3);
        Uparleft.add(new JLabel("Updating investments"));
        UparRight.add(prev);
        UparRight.add(next);
        UparRight.add(save);

        Uparleft.setLayout(new GridLayout(4, 1));
        UparRight.setLayout(new GridLayout(3, 1));
        Box3.setEditable(galat);
        nameBox3.setEditable(galat);
        uparkabot.setLayout(new BorderLayout());
        TA3.setHorizontalAlignment(JLabel.CENTER);
        uparkabot.add(TA3, BorderLayout.NORTH);
        uparkabot.add(scroll3, BorderLayout.CENTER);
        TArea3.setEditable(galat);

        PanelUpdate.add(upTop);
        PanelUpdate.add(uparkabot);


        next.addActionListener((ActionEvent ar) -> {
            position++;
            Box3.setText(symbols.get(position));
            nameBox3.setText(names.get(position));
            priceBox3.setText(prices.get(position).toString());
            TArea3.setText("");
            if(position == symbols.size() - 1)
            {
                next.setEnabled(galat);
            }
            prev.setEnabled(sahi);
        });
        save.addActionListener((ActionEvent ar) -> {
            String update1 = investments.update(position, priceBox3.getText());
            TArea3.setText(update1);
            prices = investments.Daam();
        });
        prev.addActionListener((ActionEvent ae) -> {
            position--;
            Box3.setText(symbols.get(position));
            nameBox3.setText(names.get(position));
            priceBox3.setText(prices.get(position).toString());
            TArea3.setText("");
            if(position == 0)
            {
                prev.setEnabled(galat);
            }
            next.setEnabled(sahi);
        });

        menuUpdate.addActionListener((ActionEvent ae) -> {
            PanelMain.setVisible(galat);
            PanelBuy.setVisible(galat);
            PanelSell.setVisible(galat);
            PanelGain.setVisible(galat);
            PanelSearch.setVisible(galat);
            PanelUpdate.setVisible(sahi);
            symbols = investments.gSymb();
            names = investments.Naam();
            prices = investments.Daam();
            position = 0;
            if (symbols.size() <= 0) {
                TArea3.setText("You don't have any investments.");
                Box3.setText("");
                nameBox3.setText("");
                priceBox3.setText("");
                prev.setEnabled(galat);
                next.setEnabled(galat);
            } else {
                Box3.setText(symbols.get(0));
                nameBox3.setText(names.get(0));
                priceBox3.setText(prices.get(0).toString());
                TArea3.setText("");
                prev.setEnabled(galat);
                next.setEnabled(sahi);
                if(symbols.size() == 1)
                {
                    next.setEnabled(galat);
                }
            }
        });

        //************************************************************//
        //***This section is to create the Gain Section panels********//
        //************************************************************//

        JPanel milaLeft = new JPanel();
        milaLeft.setLayout(new GridLayout(2,1));
        JPanel gainField = make1x2Panel(new JLabel("Total Amount of Gain"), gainBox);
        JPanel gainTop = make1x2Panel(milaLeft, new JPanel());
        JPanel gainBot = new JPanel();

        JLabel TA4 = new JLabel("Our own gains");
        JScrollPane scroll4 = new JScrollPane(TArea4);

        milaLeft.add(new JLabel("Lets find out our gain!"));
        milaLeft.add(gainField);
        gainBox.setEditable(galat);

        gainBot.setLayout(new BorderLayout());
        TA4.setHorizontalAlignment(JLabel.CENTER);
        gainBot.add(TA4, BorderLayout.NORTH);
        gainBot.add(scroll4, BorderLayout.CENTER);
        TArea4.setEditable(galat);

        PanelGain.add(gainTop, BorderLayout.NORTH);
        PanelGain.add(gainBot, BorderLayout.CENTER);

        menuGain.addActionListener((ActionEvent ae) -> {
            PanelMain.setVisible(galat);
            PanelBuy.setVisible(galat);
            PanelSell.setVisible(galat);
            PanelUpdate.setVisible(galat);
            PanelSearch.setVisible(galat);
            PanelGain.setVisible(sahi);

            String[] output = investments.getGain();

            gainBox.setText(output[1]);
            TArea4.setText(output[0].isEmpty() ? "You don't have any investments." : output[0]);


        });


        JTextField Box5;
        JTextField nameBox5;
        JTextField lowPriceBox;
        JTextField highPriceBox;

        JPanel lBox5;
        JPanel lNameBox5;
        JPanel llowPriceBox;
        JPanel lhighPriceBox;

        Box5 = new JTextField();
        nameBox5 = new JTextField();
        lowPriceBox = new JTextField();
        highPriceBox = new JTextField();

        lBox5 = make1x2Panel(new JLabel("Symbol"), Box5);
        lNameBox5 = make1x2Panel(new JLabel("Name keywords"), nameBox5);
        llowPriceBox = make1x2Panel(new JLabel("Low price"), lowPriceBox);
        lhighPriceBox = make1x2Panel(new JLabel("High price"), highPriceBox);

        //************************************************************//
        //***This section is to create the Search Section panels********//
        //************************************************************//

        JPanel searchLeft;
        searchLeft = new JPanel();
        JPanel searchRight = new JPanel();
        JPanel searchBot = new JPanel();

        //buttons
        JButton Scoobidoo5 = new JButton("Reset");
        JButton search = new JButton("Search");

        JPanel searchTop = make1x2Panel(searchLeft, searchRight);
        JLabel TA5 = new JLabel("Searching investments");

        searchLeft.setLayout(new GridLayout(4, 1));
        searchLeft.add(new JLabel("Search Investment"));
        searchLeft.add(lBox5);
        searchLeft.add(lNameBox5);
        searchLeft.add(llowPriceBox);
        searchLeft.add(lhighPriceBox);


        searchRight.setLayout(new GridLayout(2,1));
        searchRight.add(Scoobidoo5);
        searchRight.add(search);

        searchBot.setLayout(new BorderLayout());

        TA5.setHorizontalAlignment(JLabel.CENTER);
        searchBot.add(TA5, BorderLayout.NORTH);
        JTextArea TArea5 = new JTextArea();
        JScrollPane scroll5 = new JScrollPane(TArea5);
        searchBot.add(scroll5, BorderLayout.CENTER);
        TArea5.setEditable(galat);

        PanelSearch.add(searchTop);
        PanelSearch.add(searchBot);


        Scoobidoo5.addActionListener((ActionEvent ae) -> {
            Box5.setText("");
            nameBox5.setText("");
            lowPriceBox.setText("");
            highPriceBox.setText("");
        });
        search.addActionListener((ActionEvent ae) -> {
            String search1 = investments.search(Box5.getText(), nameBox5.getText(), lowPriceBox.getText(), highPriceBox.getText());
            TArea5.setText(search1);
        });

        menuSearch.addActionListener((ActionEvent ae) -> {
            PanelMain.setVisible(galat);
            PanelBuy.setVisible(galat);
            PanelUpdate.setVisible(galat);
            PanelGain.setVisible(galat);
            PanelSell.setVisible(galat);
            PanelSearch.setVisible(sahi);
            Box5.setText("");
            nameBox5.setText("");
            lowPriceBox.setText("");
            highPriceBox.setText("");
            TArea5.setText("");
        });


        PanelMain.add(ascroll);
        area.setEditable(galat);
        this.setJMenuBar(menuBar);
        this.add(PanelGain);
        this.add(PanelBuy);
        this.add(PanelSell);
        this.add(PanelUpdate);
        this.add(PanelSearch);
        this.add(PanelMain);

        PanelBuy.setVisible(galat);
        PanelSell.setVisible(galat);
        PanelUpdate.setVisible(galat);
        PanelGain.setVisible(galat);
        PanelSearch.setVisible(galat);
        PanelMain.setVisible(sahi);







    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}